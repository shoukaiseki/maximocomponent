package org.shoukaiseki.webclient.beans.searchtable

import org.apache.log4j.Logger
import org.shoukaiseki.webclient.utils.DataBeanUtils
import psdi.mbo.MboConstants
import psdi.util.MXException
import psdi.webclient.controls.Table
import psdi.webclient.system.beans.DataBean

import java.rmi.RemoteException

/**
 * org.shoukaiseki.webclient.beans.searchtable.JJumpToPageDataBean<br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-28 13:00:372017-09-28グ13:01:25shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
open class JumpToPageDataBean : DataBean() {

    var displayrowsperpage=-1

    companion object {
        val log:Logger = Logger.getLogger("org.shoukaiseki.showsearchtable")
    }

    override fun initialize() {
        super.initialize()
        log.debug("mboSet.isEmpty=${mboSet.isEmpty}")
        if(mboSet.isEmpty){
            val mbo = mboSet.add()
        }
        val ci = this.creatingEvent.sourceControlInstance
        if(ci.dataBean==null){
            log.debug("dataBean is null")
        }
        val dataBean:DataBean = ci.dataBean
        log.debug("currentRow=${dataBean.currentRow}")
        log.debug("pageEndRow=${dataBean.pageEndRow}")
        log.debug("pageRowCount=${dataBean.pageRowCount}")
        displayrowsperpage=getDisplayRowsPerPage()
        val currentRow = dataBean.currentRow+1
        log.debug("currentRow=${currentRow}")
        log.debug("currentRow=${currentRow/displayrowsperpage}")
        val page=if(currentRow%displayrowsperpage==0){
            currentRow/displayrowsperpage
        }else{
            currentRow/displayrowsperpage+1
        }
        log.debug("page=${page}")
        mbo?.setValue("PAGENUM",page,MboConstants.NOACCESSCHECK)
        setCurrentRecordData(mbo)
    }

    @Synchronized
    @Throws(MXException::class, RemoteException::class)
    override open  fun execute(): Int {
        log.debug("$javaClass.execute")
        val ci = this.creatingEvent.sourceControlInstance
        if(ci.dataBean==null){
            log.debug("dataBean is null")
            return 1
        }

        val dataBean:DataBean = ci.dataBean
        displayrowsperpage=getDisplayRowsPerPage()


        if(mbo!=null){
            val pagenum = mbo.getInt("PAGENUM")
            var rownum=pagenum*displayrowsperpage
            log.debug("pagenum=$pagenum,rownum=$rownum")
            DataBeanUtils.movetorow(rownum,dataBean)


        }
        return 1
    }

    /**
     * 获取每页的行数
     */
    open fun getDisplayRowsPerPage():Int{
        if(displayrowsperpage!=-1){
           return displayrowsperpage
        }
        val ci = this.creatingEvent.sourceControlInstance
        val dataBean:DataBean = ci.dataBean
        log.debug("ci=${ci.javaClass}")
        log.debug("dataBean=$dataBean,id=${dataBean?.id}")
        log.debug("showcount=${ci.getProperty("showcount")}")
        log.debug("displayrowsperpage=${ci.getProperty("displayrowsperpage")}")
        displayrowsperpage=dataBean.pageRowCount

        if(ci is Table){
            log.debug("body.displayrowsperpage=${ci.body.getProperty("displayrowsperpage")}")
            if(ci.body!=null){
                displayrowsperpage=ci.body.getInt("displayrowsperpage",displayrowsperpage)
            }
        }
        return displayrowsperpage
    }

}
