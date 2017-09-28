package org.shoukaiseki.webclient.beans.interfaceconfig

import org.apache.log4j.Logger
import org.shoukaiseki.app.interfaceconfig.task.base.InterfaceTaskProcessing
import psdi.util.MXApplicationYesNoCancelException
import psdi.util.MXException
import psdi.webclient.system.beans.DataBean

import java.rmi.RemoteException

/**
 * org.shoukaiseki.webclient.beans.interfaceconfig.ExecutionDataBean <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-14 17:18:59<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */

class ExecutionDataBean : DataBean() {

    companion object {
        @JvmStatic val logger= Logger.getLogger("org.shoukaiseki.InterfaceTask")
    }

    @Synchronized
    @Throws(MXException::class, RemoteException::class)
    override fun execute(): Int {
        logger.debug("$javaClass.execution...${this.clientSession.hasLongOpStarted()}")
        if (!this.clientSession.hasLongOpStarted()) {
        }

        /**
        var currentEvent=this.clientSession.currentEvent;
        when(currentEvent.messageReturn){
            MXApplicationYesNoCancelException.NULL ->{
                throw MXApplicationYesNoCancelException("runiconfexecution","iconf", "execution");
            }
            MXApplicationYesNoCancelException.OK -> {

            }
        }
         *
         */

        val itp: InterfaceTaskProcessing
        val runLongOp = this.clientSession.runLongOp(this, "execute")
        logger.debug("runLongOp=$runLongOp")
        if (runLongOp) {
            logger.debug("$javaClass.execution...execute")
            val dataBean = app.getDataBean("results_showlist")
            if(app.onListTab()){

                logger.debug("dataBean.isSubSelect=${ dataBean.isSubSelect}")
                //选择记录模式
                if(dataBean.isSubSelect){
                    val select=dataBean.selection
                    var sb=StringBuffer()
                    for (mboTemp in select){
                        if(!mboTemp.isNull("NAME")){
                            if(sb.isNotBlank()){
                                sb.append(",")
                            }
                            sb.append("'${mboTemp.getString("NAME")}'")
                        }
                    }
                    if(sb.isBlank()){
                        sb.append("1=1")
                    }else{
                        sb=StringBuffer("name in (").append(sb).append(")")
                    }
                    logger.debug("where=$sb")
                    itp=InterfaceTaskProcessing(dataBean.mboSet.userInfo,sb.toString())

                }else{
                    itp=InterfaceTaskProcessing(dataBean.mboSet.userInfo)
                }

            }else{
                itp=InterfaceTaskProcessing(dataBean.mboSet.userInfo,"name='${dataBean.mbo.getString("NAME")}'")
            }
            itp.action()
        }
        return 1
    }
}
