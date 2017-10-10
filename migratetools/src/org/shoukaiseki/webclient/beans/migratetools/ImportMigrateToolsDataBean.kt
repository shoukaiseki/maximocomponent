package org.shoukaiseki.webclient.beans.migratetools

import org.apache.log4j.Logger
import org.shoukaiseki.excel.poi.ExcelBuild
import org.shoukaiseki.excel.poi.ExcelBuildActionCall
import psdi.util.MXApplicationException
import psdi.util.MXException
import psdi.webclient.system.beans.DataBean
import psdi.webclient.system.controller.UploadFile
import java.io.ByteArrayInputStream
import java.io.UnsupportedEncodingException
import java.rmi.RemoteException

/**
 * org.shoukaiseki.webclient.beans.migratetools.ImportMigrateToolsDataBean <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-10-10 17:11:08<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
class ImportMigrateToolsDataBean : DataBean(){

    companion object {
        val log: Logger = Logger.getLogger("org.shoukaiseki")
    }

    @Throws(MXException::class, RemoteException::class)
    override fun execute(): Int {
        log.debug("ImportMigrateToolsDataBean.execute")
        if (!this.clientSession.hasLongOpStarted()) {
        }

        if (this.clientSession.runLongOp(this, "execute")) {
            val file = this.app.get("importfile") as UploadFile

            val mboSet=this.app.appBean.mboSet
            log.debug("mboname=${mboSet.name},appname=${this.app.app}")

            var fileContent = ""

            try {
                fileContent = file.fileOutputStream.toString("UTF-8")
                log.debug("fileContent=$fileContent")
                println("fileContent=$fileContent")
            } catch (var6: UnsupportedEncodingException) {
                log.debug("Unsupported encoding exception!!!")
                var6.printStackTrace()
            }


            /**
            try {
                var inputStream= ByteArrayInputStream(file.fileOutputStream.toByteArray());
                val appbean=app.appBean
                if(appbean is ExcelBuildActionCall){
                    var eb= ExcelBuild(inputStream,appbean)
                    eb.readExcelContent()
                }else{
                    throw MXApplicationException("不支持导入功能","${appbean.javaClass.name} 没有继承 ExcelBuildActionCall 接口")
                }
                log.debug("excel import end")
                this.clientSession.showMessageBox(this.clientSession.currentEvent, "ImportExcelDataBean", "导入成功", arrayOf())
            } catch (var6: UnsupportedEncodingException) {
                log.error("导入出错",var6)
                var6.printStackTrace()
                throw MXApplicationException("excel","导入出错",var6)
            }
             */
        }


        return 1
    }

}
