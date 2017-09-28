package org.shoukaiseki.webclient.beans.interfaceconfig

import org.apache.log4j.Logger
import psdi.mbo.MboConstants
import psdi.mbo.MboSetEnumeration
import psdi.mbo.MboSetRemote
import psdi.webclient.system.beans.AppBean

/**
 * org.shoukaiseki.webclient.beans.interfaceconfig.IInterfaceConfigAppBean<br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-08 11:27:472017-09-08グ11:28:21shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */

open class InterfaceConfigAppBean : AppBean() {

    companion object {
        @JvmStatic val logger= Logger.getLogger("org.shoukaiseki.InterfaceTask")
    }


    open fun CLEANLOG(){
        SAVE()
        mbo.getMboSet("INTERFACECONFIGLOG").deleteAll()
        SAVE()
    }

    open fun SORTINGSN(){
        SAVE()
        val msr = mbo.getMboSet("sortingsn_temp_interfaceconfigsubtable_${System.currentTimeMillis()}", "INTERFACECONFIGSUBTABLE", mbo.getMboSet("INTERFACECONFIGSUBTABLE").relationship)
        msr.orderBy="SN asc"
        msr.reset()
        setSn(msr)
        SAVE()
    }

    open fun setSn(msr:MboSetRemote){
        var mse=MboSetEnumeration(msr)
        var snnum=0
        while (mse.hasMoreElements()){
            var mbo = mse.nextMbo()
            snnum += 10
            mbo.setValue("SN",snnum,MboConstants.NOACCESSCHECK)
            var mboSetTemp = mbo.getMboSet("RELATIONSHIPSUBTABLE")
            println("mbo.uniqueIDValue=${mbo.uniqueIDValue},sn=${mbo.getInt("SN")},mboSetTemp.relationName=${mboSetTemp.relationName},mboSetTemp.completeWhere=${mboSetTemp.completeWhere}")
            mboSetTemp.orderBy="SN asc"
            mboSetTemp.reset()
            println("mboSetTemp.count=${mboSetTemp.count()}")
            if(!mboSetTemp.isEmpty){
                setSn(mboSetTemp)
            }
        }
        msr.save()
    }

}
