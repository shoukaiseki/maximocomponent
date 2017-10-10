package org.shoukaiseki.webclient.beans.migratetools.common

import com.alibaba.fastjson.JSONObject
import org.apache.log4j.Logger
import org.shoukaiseki.webclient.beans.migratetools.model.FilterModel
import org.shoukaiseki.webclient.beans.migratetools.model.LineData
import org.shoukaiseki.webclient.beans.migratetools.model.TableData
import org.shoukaiseki.webclient.beans.migratetools.model.RootTable
import psdi.mbo.MboRemote
import psdi.mbo.MboSetEnumeration
import psdi.mbo.MboSetRemote
import psdi.mbo.MboValueInfo
import psdi.util.MXException
import psdi.util.MaxType
import psdi.webclient.system.beans.DataBean
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.ArrayList

/**
 * org.shoukaiseki.webclient.beans.migratetools.common.AbstractMigrate <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-15 09:45:48<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
open class AbstractMigrate(val tableBean: DataBean, val request:HttpServletRequest,val response:HttpServletResponse) {

    private var filtermodel:FilterModel

    var jsonRoot=RootTable("")

    init {
        filtermodel =FilterModel()
        filtermodel.name=tableBean.mboName
        filtermodel.app=tableBean.mboSet.app
    }


    companion object {
        @JvmStatic val logger= Logger.getLogger("org.shoukaiseki.migrate")
    }

    open fun execute(): AbstractMigrate {
        //导出数据无需进行过滤
//        val atts=filtersAtts(tableBean.mboSet, filtermodel.getFilters())
        val atts=initAtts(tableBean.mboSet)

        jsonRoot.filters=this.filtermodel.getFilters()
        jsonRoot.filtermodel=this.filtermodel
        jsonRoot.app=tableBean.mboSet.app
        jsonRoot.name =tableBean.mboSet.name
        jsonRoot.tableid=this.filtermodel.getTableid()

        logger.debug("filtermodel=${JSONObject.toJSONString(filtermodel)}")

        val me=MboSetEnumeration(tableBean.mboSet)
        while (me.hasMoreElements()){
            val mbo=me.nextMbo()
            val data=getData(jsonRoot,mbo,atts)


            for(child in filtermodel.getChilds()){
//                logger.debug("child=${JSONObject.toJSONString(child)}")
                val rs=traversalFilterModel(mbo,child)
                data.childs.add(rs)
//                logger.debug("data=${JSONObject.toJSONString(data)}")
//                logger.debug("rs=${JSONObject.toJSONString(rs)}")
            }
            jsonRoot.datas.add(data)
        }
        return this
    }

    /**
     * 遍历
     * @param       mbo  要获取的mbo
     * @param       fm  过滤器
     */
    open fun traversalFilterModel(mbo:MboRemote,fm:FilterModel): TableData {
        val table = TableData(fm.getTableid())
        val mboSet = mbo.getMboSet(fm.name)
        table.relationName=mboSet.relationName
        table.name=mboSet.name
        val me=MboSetEnumeration(mboSet)
        val atts=initAtts(mboSet)
        while (me.hasMoreElements()){
            val mbo2=me.nextMbo()
            val data=getData(table,mbo2,atts)
            table.datas.add(data)
            fm.getChilds() .map { traversalFilterModel(mbo2, it) }
                    .forEach {
                        data.childs.add(it)
                    }
        }
//        logger.debug("json=${JSONObject.toJSONString(table)}")

        return table
    }

    open fun getData(rs:TableData,mbo: MboRemote,atts: List<MboValueInfo>):LineData{
        val data = LineData(rs)
        for (mvi in atts){
//            logger.debug("${mvi.attributeName}.iskey=${mvi.isKey}")
            val value:Any?=valueForMboByType(mbo,mvi)
            value?.let {
               data.put(mvi.attributeName,value)
            }
        }

        return data
    }

    open fun valueForMboByType(mbo: MboRemote,mvi: MboValueInfo):Any?{
        val attname=mvi.attributeName
        try {
            when(mvi.typeAsInt){
                MaxType.ALN,MaxType.UPPER,MaxType.LOWER -> {
                    return mbo.getString(attname)
                }
                MaxType.INTEGER,MaxType.BIGINT -> {
                    return mbo.getInt(attname)
                }
                MaxType.TIME,MaxType.DATE,MaxType.DATETIME -> {
                    return mbo.getDate(attname)
                }
                MaxType.YORN -> {
                    return mbo.getInt(attname)
                }
                else -> {
                    throw Exception("该类型没有定义,${mbo.name}.$attname")
                }
            }
        }catch (e: MXException){
            throw Exception("mboname=${mbo.name},relationship=${mbo.thisMboSet.relationName},iskey=${mvi.isKey}",e)
        }
        return null
    }

    /**
     * 获取mboSet的所有字段
     * @param       mboSet
     * @param       filters
     */
    open fun initAtts(mboSet:MboSetRemote): List<MboValueInfo> {
        val atts= arrayListOf<MboValueInfo>()
        val mboValuesInfo: Enumeration<MboValueInfo> = mboSet.mboSetInfo.mboValuesInfo as Enumeration<MboValueInfo>
        for (mvi in mboValuesInfo){
                atts.add(mvi)
        }
        return atts
    }

    /**
     * 获取mboSet的所有字段,并且过滤掉 filtermodel 数组里面的字段
     * @param       mboSet
     * @param       filters
     */
    open fun filtersAtts(mboSet:MboSetRemote,filters:ArrayList<String>): List<MboValueInfo> {
        val atts= arrayListOf<MboValueInfo>()
        val mboValuesInfo: Enumeration<MboValueInfo> = mboSet.mboSetInfo.mboValuesInfo as Enumeration<MboValueInfo>
        for (mvi in mboValuesInfo){
            if(!filters.contains(mvi.attributeName)){
               atts.add(mvi)
            }else{
                logger.debug("${mboSet.name}.filter.attributeName=${mvi.attributeName}")
            }
        }
        return atts
    }



    open fun download(): AbstractMigrate {
        val sks_download:String?=request.getParameter("sks_download")
        val encoding: String=if (request.characterEncoding == null) {
            "UTF-8"
        }else{
            request.characterEncoding
        }
        if(sks_download!=null&&(sks_download.equals("1",ignoreCase = true)||sks_download.equals("true",ignoreCase = true))){
            response.reset()
            val timeStr= SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val reqContentType =  "${tableBean.mboSet.app.toLowerCase()}_$timeStr.json"


            response.contentType = "application/vnd.ms-excel; charset=$encoding"
            response.setHeader("Pragma", "public")
            response.setHeader("Cache-Control", "max-age=0")
            response.setHeader("Content-Disposition", "attachment;filename=${reqContentType}" )
            val pw = response.writer
            val json=JSONObject.toJSONString(jsonRoot,true)
            pw.print(json)
//            logger.debug("json=$json")
            pw.close()
        }else{
            response.contentType = "text/html;charset=$encoding"
            /** 设置响应头允许ajax跨域访问 **/
            response.setHeader("Access-Control-Allow-Origin", "*")
            /* 星号表示所有的异域请求都可以接受， */
            response.setHeader("Access-Control-Allow-Methods", "GET,POST")
            val pw = response.writer
            val json=JSONObject.toJSONString(jsonRoot,true)
            pw.print(json)
//            logger.debug("json=$json")
            pw.close()

        }
        return this
    }



    fun getFilters():FilterModel{
        return this.filtermodel
    }

    fun setFilters(fm: FilterModel): AbstractMigrate {
        this.filtermodel = fm
        filtermodel.name=tableBean.mboName
        filtermodel.app=tableBean.mboSet.app
        return this
    }

}
