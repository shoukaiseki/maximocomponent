package org.shoukaiseki.webclient.beans

import org.apache.log4j.Logger
import org.shoukaiseki.excel.poi.ExcelBuild
import org.shoukaiseki.excel.poi.ExcelBuildActionCall
import java.io.UnsupportedEncodingException
import java.rmi.RemoteException
import psdi.util.MXApplicationException
import psdi.util.MXException
import psdi.webclient.system.beans.DataBean
import psdi.webclient.system.controller.UploadFile
import java.io.ByteArrayInputStream

/** org.shoukaiseki.webclient.beans.ImportExcelDataBean <br>
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-22 09:16:00<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/

class ImportExcelDataBean : DataBean() {

    companion object {
        val log:Logger= Logger.getLogger("org.shoukaiseki")
    }

    @Throws(MXException::class, RemoteException::class)
    override fun execute(): Int {
        log.debug("ImportExcelDataBean.execute")
        if (!this.clientSession.hasLongOpStarted()) {
        }

        if (this.clientSession.runLongOp(this, "execute")) {
            val file = this.app.get("importfile") as UploadFile

            val mboSet=this.app.appBean.mboSet
            log.debug("mboname=${mboSet.name},appname=${this.app.app}")

            var fileContent = ""

            try {
                var inputStream=ByteArrayInputStream(file.fileOutputStream.toByteArray());
                val appbean=app.appBean
                if(appbean is ExcelBuildActionCall){
                    var eb= ExcelBuild(inputStream,appbean)
                    eb.readExcelContent()
                }
                log.debug("excel import end")
                this.clientSession.showMessageBox(this.clientSession.currentEvent, "ImportExcelDataBean", "导入成功", arrayOf())
            } catch (var6: UnsupportedEncodingException) {
                log.error("导入出错",var6)
                var6.printStackTrace()
                throw MXApplicationException("excel","导入出错",var6)
            }
        }


        return 1
    }
}
