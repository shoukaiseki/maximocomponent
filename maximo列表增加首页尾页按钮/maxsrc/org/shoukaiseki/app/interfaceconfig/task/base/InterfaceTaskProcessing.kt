package org.shoukaiseki.app.interfaceconfig.task.base

import org.apache.log4j.Logger
import psdi.mbo.MboRemote
import psdi.mbo.MboSetEnumeration
import psdi.mbo.MboSetRemote
import psdi.security.UserInfo
import psdi.server.MXServer
import java.lang.reflect.Constructor

/** org.shoukaiseki.app.interfaceconfig.task.base.InterfaceTaskProcessing <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-07 17:07:03<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/

open class InterfaceTaskProcessing(val userInfo:UserInfo,val where:String="1=1") {
    companion object {
        @JvmStatic val logger= Logger.getLogger("org.shoukaiseki.InterfaceTask")
    }

    open fun action(){
        val icSet:MboSetRemote = MXServer.getMXServer().getMboSet("INTERFACECONFIG",userInfo)
        icSet.where=where
        icSet.reset()
        val mse=MboSetEnumeration(icSet)
        while(mse.hasMoreElements()){
            var mbo=mse.nextMbo()
            logger.debug("icname=${mbo.getString("NAME")},enabled=${mbo.getBoolean("ENABLED")}")
            if(!mbo.getBoolean("ENABLED")){
                continue
            }
            var ite: AbstractInterfaceTaskExecution?=null
            try {

                if(mbo.isNull("CLASSNAME")){
                    ite= AbstractInterfaceTaskExecution(mbo,userInfo)
                }else{
                    val clazz=Class.forName(mbo.getString("CLASSNAME"))
                    logger.debug("${MboRemote::class.java},${UserInfo::class.java}")
                    val dc:Constructor<AbstractInterfaceTaskExecution> = clazz.getDeclaredConstructor(*arrayOf<Class<*>>(MboRemote::class.java, UserInfo::class.java)) as Constructor<AbstractInterfaceTaskExecution>
                    dc.isAccessible=true
                    var iteTemp=dc.newInstance(*arrayOf<Any>(mbo,userInfo))
                    if(iteTemp is AbstractInterfaceTaskExecution){
                        logger.debug("${mbo.getString("CLASSNAME")} is extends InterfaceTaskExecution")
                        ite=iteTemp

                    }else{
                        logger.debug("${mbo.getString("CLASSNAME")} is not extends InterfaceTaskExecution")
                        ite= AbstractInterfaceTaskExecution(mbo,userInfo)
                    }
                }
                ite.execution()
                ite.errorLog(mbo.getString("NAME"),"成功","成功")
            }catch (e:Exception){
                logger.error("${mbo.name}.${mbo.uniqueIDName}=${mbo.uniqueIDValue}",e)
                ite?.errorLog(mbo.getString("NAME"),e)
            }finally {
                ite?.close()
            }
        }
        icSet.close()

    }




}