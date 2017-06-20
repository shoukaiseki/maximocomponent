package org.shoukaiseki.webclient.beans

import com.ibm.icu.impl.PluralRulesLoader.loader
import org.apache.log4j.Logger
import java.io.UnsupportedEncodingException
import java.rmi.RemoteException
import psdi.util.MXApplicationException
import psdi.util.MXException
import psdi.webclient.system.beans.DataBean
import psdi.webclient.system.controller.PresentationLoader
import psdi.webclient.system.controller.UploadFile

class ImportExcleDataBean : DataBean() {

    companion object {
        val log:Logger= Logger.getLogger("org.shoukaiseki")
    }

    @Throws(MXException::class, RemoteException::class)
    override fun execute(): Int {
        log.debug("ImportExcleDataBean.execute")
        if (!this.clientSession.hasLongOpStarted()) {
        }

        if (this.clientSession.runLongOp(this, "execute")) {
            val file = this.app.get("importfile") as UploadFile

            val mboSet=this.app.appBean.mboSet
            log.debug("mboname="+mboSet.name)

            var fileContent = ""

            try {
                fileContent = file.fileOutputStream.toString("UTF-8")
                log.debug("fileContent=$fileContent")
            } catch (var6: UnsupportedEncodingException) {
                log.debug("Unsupported encoding exception!!!")
                var6.printStackTrace()
            }

            var params: Array<String>?= arrayOf("appid")
            try {
                if (fileContent.indexOf("<presentationset") >= 0) {
                    params = arrayOf("presentation set")
                    this.clientSession.showMessageBox(this.clientSession.currentEvent, "#ImportExcleDataBean", "导入成功", params)
                } else {
                    this.clientSession.showMessageBox(this.clientSession.currentEvent, "#ImportExcleDataBean", "appdefloaded", params)
                }

                this.clientSession.currentApp.reload = true
            } catch (var7: MXApplicationException) {
                params = null
                if (var7.parameters.isArrayOf<String>()) {
                    params = var7.parameters as Array<String>
                }

                this.clientSession.showMessageBox(this.clientSession.currentEvent, var7.errorGroup, var7.errorKey, params)
            } catch (var8: Exception) {
                this.clientSession.showMessageBox(this.clientSession.currentEvent, "#ImportExcleDataBean", "导入失败", params)
            }

        }

        if (this.clientSession.hasLongOpCompleted()) {
        }

        return 1
    }
    @Throws(MXException::class, RemoteException::class)
     fun execute1(): Int {
        if (!this.clientSession.hasLongOpStarted()) {
        }

        if (this.clientSession.runLongOp(this, "execute")) {
            val file = this.app.get("importfile") as UploadFile
            var fileContent = ""

            try {
                fileContent = file.fileOutputStream.toString("UTF-8")
            } catch (var6: UnsupportedEncodingException) {
                println("Unsupported encoding exception!!!")
                var6.printStackTrace()
            }

            val loader = PresentationLoader()

            var params: Array<String>?
            try {
                if (fileContent.indexOf("<presentationset") >= 0) {
                    loader.importPresentationSet(this.clientSession, fileContent)
                    params = arrayOf("presentation set")
                    this.clientSession.showMessageBox(this.clientSession.currentEvent, "designer", "appdefloaded", params)
                } else {
                    loader.importApp(this.clientSession, fileContent)
                    params = arrayOf(loader.appID)
                    this.clientSession.showMessageBox(this.clientSession.currentEvent, "designer", "appdefloaded", params)
                }

                this.clientSession.currentApp.reload = true
            } catch (var7: MXApplicationException) {
                params = null
                if (var7.parameters.isArrayOf<String>()) {
                    params = var7.parameters as Array<String>
                }

                this.clientSession.showMessageBox(this.clientSession.currentEvent, var7.errorGroup, var7.errorKey, params)
            } catch (var8: Exception) {
                params = arrayOf(loader.appID)
                this.clientSession.showMessageBox(this.clientSession.currentEvent, "designer", "cannotparsefile", params)
            }

        }

        if (this.clientSession.hasLongOpCompleted()) {
        }

        return 1
    }
}
