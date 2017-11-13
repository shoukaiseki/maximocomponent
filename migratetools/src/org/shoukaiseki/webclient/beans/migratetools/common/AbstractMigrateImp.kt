package org.shoukaiseki.webclient.beans.migratetools.common

import org.apache.log4j.Logger
import psdi.mbo.MboSetRemote
import psdi.webclient.system.beans.DataBean

/**
 * org.shoukaiseki.webclient.beans.migratetools.common.AbstractMigrateImp <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-10-11 13:53:35<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
open class AbstractMigrateImp(val tableBean: DataBean,val jsonStr:String){

    companion object {
        @JvmStatic val logger= Logger.getLogger("org.shoukaiseki.migrate")
    }

    open fun execute(){

    }


    open fun close(ob:Object){
        when(ob){
            is MboSetRemote ->{
                try{
                ob.close()
                }catch (e:Exception){
                }
            }
            else ->{

            }
        }

    }

}
