package org.shoukaiseki.webclient.beans.migratetools

import org.apache.log4j.Logger
import org.shoukaiseki.webclient.beans.migratetools.common.AbstractMigrateImp
import psdi.mbo.SqlFormat
import psdi.server.MXServer
import psdi.util.MXException
import psdi.webclient.system.beans.DataBean
import psdi.webclient.system.controller.UploadFile
import java.io.UnsupportedEncodingException
import java.lang.reflect.Constructor
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
        val logger: Logger = Logger.getLogger("org.shoukaiseki")
    }

    @Throws(MXException::class, RemoteException::class)
    override fun execute(): Int {
        logger.debug("ImportMigrateToolsDataBean.execute")
        if (!this.clientSession.hasLongOpStarted()) {
        }

        if (this.clientSession.runLongOp(this, "execute")) {
            val file = this.app.get("importfile") as UploadFile

            val mboSet=this.app.appBean.mboSet
            logger.debug("mboname=${mboSet.name},appname=${this.app.app}")

            var fileContent = ""

            try {
                fileContent = file.fileOutputStream.toString("UTF-8")
//                logger.debug("fileContent=$fileContent")
//                println("fileContent=$fileContent")
                logger.debug("appname=${this.app.app}")
                this.creatingEvent!!.sourceControlInstance!!.dataBean!!.let{
                    val alnSet=MXServer.getMXServer().getMboSet("ALNDOMAIN",MXServer.getMXServer().systemUserInfo)
                    val sf=SqlFormat("domainid='ABSTRACTMIGRATEIMP' and value=:1")
                    sf.setObject(1,"ALNDOMAIN","VALUE",app.app)
                    alnSet.where=sf.format()
                    alnSet.reset()
                    val ami:AbstractMigrateImp
                    if(alnSet.getMbo(0)!=null){
                        val clazz=Class.forName(alnSet.getMbo(0).getString("DESCRIPTION"))
                        val dc:Constructor<AbstractMigrateImp> = clazz.getDeclaredConstructor(*arrayOf<Class<*>>(DataBean::class.java, String::class.java)) as Constructor<AbstractMigrateImp>
                        dc.isAccessible=true
                        var amiTemp =dc.newInstance(*arrayOf<Any>(it,fileContent))
                        if(amiTemp is AbstractMigrateImp){
                            ami= amiTemp
                        }else{
                            logger.debug("${mbo.getString("CLASSNAME")} is not extends InterfaceTaskExecution")
                            ami=AbstractMigrateImp(it,fileContent)
                        }

                    }else{
                        ami=AbstractMigrateImp(it,fileContent)
                    }
                    ami.execute()

                    try{
                        alnSet.close()
                    }catch (e:Exception){
                    }
                }
                //ABSTRACTMIGRATEIMP


            } catch (var6: UnsupportedEncodingException) {
                logger.debug("Unsupported encoding exception!!!")
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
                logger.debug("excel import end")
                this.clientSession.showMessageBox(this.clientSession.currentEvent, "ImportExcelDataBean", "导入成功", arrayOf())
            } catch (var6: UnsupportedEncodingException) {
                logger.error("导入出错",var6)
                var6.printStackTrace()
                throw MXApplicationException("excel","导入出错",var6)
            }
             */
        }


        return 1
    }

}
