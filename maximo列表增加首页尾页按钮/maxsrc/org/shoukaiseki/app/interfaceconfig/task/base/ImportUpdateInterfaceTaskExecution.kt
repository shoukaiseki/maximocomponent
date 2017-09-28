package org.shoukaiseki.app.interfaceconfig.task.base

import com.sun.org.apache.xpath.internal.operations.Bool
import org.apache.commons.collections4.map.CaseInsensitiveMap
import org.apache.log4j.Logger
import psdi.mbo.MboRemote
import psdi.mbo.MboSetEnumeration
import psdi.mbo.MboSetRemote
import psdi.security.UserInfo
import psdi.server.MXServer
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.*
import java.io.StringWriter
import java.math.BigDecimal
import java.sql.*
import java.util.Date


/** org.shoukaiseki.app.interfaceconfig.task.base.ImportUpdateInterfaceTaskExecution <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-08 11:09:59<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
open class ImportUpdateInterfaceTaskExecution ( icMbo:MboRemote, userInfo: UserInfo ): AbstractInterfaceTaskExecution( icMbo, userInfo ) {
    val conn:Connection
    val icstSet:MboSetRemote
    var dbdriver:String
    var dbjdbcurl:String
    var dbuser:String
    var dbpassword:String
    var haserror=false
    var msrTemps= hashMapOf<String,MboSetRemote>()
    var maxmsr:MboSetRemote?=null
    var imst:Statement?=null
    var imrs:ResultSet?=null
    init {
        icstSet=icMbo.getMboSet("INTERFACECONFIGSUBTABLE")
        icstSet.orderBy="sn asc"
        icstSet.reset()
        dbdriver=icMbo.getString("INTERFACECONFIGDATASOURCE.DBDRIVER")
        dbjdbcurl=icMbo.getString("INTERFACECONFIGDATASOURCE.DBJDBCURL")
        dbuser=icMbo.getString("INTERFACECONFIGDATASOURCE.DBUSER")
        dbpassword=icMbo.getString("INTERFACECONFIGDATASOURCE.DBPASSWORD")
        Class.forName(dbdriver)
        conn= DriverManager.getConnection(dbjdbcurl, dbuser, dbpassword)
        conn.autoCommit=false
    }

    /**
     * 获取非共享中间库连接
     */
    open fun newUnsharedConnection(): Connection {
        var dbdriver=icMbo.getString("INTERFACECONFIGDATASOURCE.DBDRIVER")
        var dbjdbcurl=icMbo.getString("INTERFACECONFIGDATASOURCE.DBJDBCURL")
        var dbuser=icMbo.getString("INTERFACECONFIGDATASOURCE.DBUSER")
        var dbpassword=icMbo.getString("INTERFACECONFIGDATASOURCE.DBPASSWORD")
        Class.forName(dbdriver)
        var conn= DriverManager.getConnection(dbjdbcurl, dbuser, dbpassword)
        conn.autoCommit=false
        return conn
    }



    /**
     * 执行方案
     */
    override fun execution(){
        logger.debug("${javaClass}.action")
        logger.debug("------------------------------")
        try {
            when(icMbo.getString("TYPE").toUpperCase()){
                "IMPORT" -> {
                    imoprtExecution(false)
                }
                "IMPORTMAX_UPDATEIM" -> {
                    imoprtExecution(true)
                }
                "EXPORT" -> {
                    exportExecution()
                }
            }
        }catch (e:Exception){
            haserror=true
            throw e
        }
    }


    /**
     * 导入执行的方案
     */
    open fun imoprtExecution(updateim:Boolean){
        val sql=icMbo.getString("QUERYSQL")
        imst = if(updateim){
            conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
        }else{
            conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY)
        }
        imrs = imst!!.executeQuery(sql)
        maxmsr=MXServer.getMXServer().getMboSet(icMbo.getString("MAXIMOOBJECTNAME"),userInfo)
        while (imrs!!.next()){

            val mbo=getImportMbo(maxmsr!!)
            msrTemps.clear()
            setMboValuesFromResultSetByIcst(mbo,icstSet,imrs!!)
            importEndAction(mbo,imrs!!)
            msrTemps.forEach {
                it.value.save()
                close(it.value)
            }
            maxmsr?.save()
            if(updateim){
                imrs!!.updateRow()
            }
        }
        maxmsr?.close()
        logger.debug("name=${icMbo.getString("NAME")},MAXIMOOBJECTNAME=${icMbo.getString("MAXIMOOBJECTNAME")} 保存成功")
    }


    /**
     * 设置 mbo 的值,数据来源与 imrs ,配置来至 icstSet
     * @param   mbo      要设置值的mbo
     * @param   icstSet 设置值的配置
     * @param   imrs    数据来源
     */
    open fun setMboValuesFromResultSetByIcst(mbo:MboRemote,icstSet:MboSetRemote,imrs:ResultSet){
        val me=MboSetEnumeration(icstSet)
        while (me.hasMoreElements()){
            val icst=me.nextMbo()
            dataStr= StringBuffer()
            dataStr.append("rownum=${imrs.row},")
            dataStr.append("${icMbo.name}:{${icMbo.uniqueIDName}:${icMbo.uniqueIDValue},name:${icMbo.getString("NAME")}},${icst.name}:{${icst.uniqueIDName}:${icst.uniqueIDValue},sn:${icst.getInt("sn")}}")

            logger.debug("sn:${icst.getInt("SN")},isreverse:${icst.getBoolean("ISREVERSE")}")
            if(icst.getBoolean("ISREVERSE")){
                var fromValue:Any
                if(icst.isNull("MAXATTNAME")){
                    fromValue=valueFromMaxDefaultValue(icst.getString("DBDEFAULTVALUE"),icst,mbo)
                }else{
                    fromValue=valueFromMboRemote(mbo,icst)
                }
                logger.debug("fromValue=$fromValue")
                logger.debug("update.imattname=${icst.getString("IMATTNAME")},imatttype=${icst.getString("IMATTTYPE")},fromValue=${anyToString(fromValue,icst)}")
                dataStr.append("setim.${icst.getString("IMATTNAME")}=${anyToString(fromValue,icst)}")
                dataStr.append(",")
                valueSetToResultSet(imrs,fromValue,icst)

            }else{
                var fromValue:Any
                if(!icst.isNull("RELATIONSHIPNAME")&&icst.isNull("MAXATTNAME")){
                    addinitRelationShip(mbo,icst,imrs)
                    return
                }
                if(icst.isNull("IMATTNAME")){
                    fromValue=valueFromMaxDefaultValue(icst.getString("MAXDEFAULTVALUE"),icst,mbo)
                }else{
                    fromValue=valueFromResultSet(imrs,icst)
                }
                logger.debug("set.maxattname=${icst.getString("MAXATTNAME")},imatttype=${icst.getString("MAXATTTYPE")},fromValue=${anyToString(fromValue,icst)}")
                dataStr.append("setmbo.${icst.getString("MAXATTNAME")}=${anyToString(fromValue,icst)}")
                dataStr.append(",")
                valueSetToMboRemote(mbo,fromValue,icst,imrs)
            }
        }

    }

    /**
     * 获取导入时所需 Mbo ,通常使用 msr.add(),如果业务需要做更新,则自定义处理
     * @param   msr             需要导入的MboSet
     * @param   icMbo           接口配置mbo
     * @param   userInfo        userinfo
     */
    @Throws (Exception::class)
    open fun getImportMbo(msr:MboSetRemote,icMbo: MboRemote=this.icMbo,userInfo: UserInfo=this.userInfo):MboRemote{
        msr.where="1=2"
        msr.reset()
        return msr.add()
    }


    /**
     * 导到中间库所执行的方法
     */
    open fun exportExecution() {
//        val valueMap= CaseInsensitiveMap<String,Any?>()
//            exportEndAction()
    }

    /**
     * 导出到中间库最后执行的方法
     * @param mbo            查询的行记录mbo
     * @param valueMap      要处理的行记录Map
     * @param icstSet       接口配置子表信息
     * @param userInfo      userInfo
     */
    open fun exportEndAction(mbo:MboRemote,valueMap:CaseInsensitiveMap<String,Any?>,icstSet:MboSetRemote=this.icstSet,userInfo: UserInfo=this.userInfo){
        logger.debug("exportEndAction")
    }

    /**
     * 导入到maximo最后执行的方法
     * @param mbo            要修改的当前行mbo
     * @param rs             查询的当前行记录
     * @param icstSet       接口配置子表信息
     * @param userInfo      userInfo
     */
    @Throws(Exception::class)
    open  fun importEndAction(mbo:MboRemote, rs:ResultSet, icstSet:MboSetRemote=this.icstSet, userInfo: UserInfo=this.userInfo){
        logger.debug("importEndAction")
    }


    /**
     * 添加子表配置中关系对应的mbo
     * @param   rsSet   子表配置中对应的关系名所属的 MboSet
     */
    @Throws(Exception::class)
    open fun addinitRelationShip(mbo:MboRemote,icst:MboRemote,imrs:ResultSet) {
        val msrTemp=mbo.getMboSet(icst.getString("RELATIONSHIPNAME"))
        msrTemps.put(icst.getString("RELATIONSHIPNAME"),msrTemp)
       val mboTemp:MboRemote
       if(msrTemp.isEmpty){
           if(icst.isNull("ACCESSMODIFIER")){
               mboTemp=msrTemp.add()
           }else{
               mboTemp=msrTemp.add(icst.getLong("ACCESSMODIFIER"))
           }
       }else{
          mboTemp=msrTemp.getMbo(0)
       }
        val icstrsSet = icst.getMboSet("RELATIONSHIPSUBTABLE")
        icstrsSet.orderBy="sn asc"
        icstrsSet.reset()
        setMboValuesFromResultSetByIcst(mboTemp, icstrsSet,imrs)

    }







    override fun close(){
        logger.debug("InterfaceTaskExecution.close")
        if(haserror){
            rollback()
        }else{
            commit()
        }
        close(conn)
        close(imrs)
        close(imst)
        close(maxmsr)

//        closeMboSet(msr)
    }

    open fun commit(){
        try {
            conn.commit()
        }catch (e:Exception){
            e.printStackTrace()
        }
        msrTemps.forEach {
            try {
                it.value.commit()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        try {
            maxmsr?.commit()
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    open fun rollback(){
        try {
            conn.rollback()
        }catch (e:Exception){
            e.printStackTrace()
        }

        msrTemps.forEach {
            try {
                it.value.rollback()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        try {
            maxmsr?.rollback()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }



}

