package org.shoukaiseki.webclient.beans.interfaceconfig

import psdi.mbo.MboConstants
import psdi.mbo.MboRemote
import psdi.util.MXException
import psdi.webclient.system.beans.DataBean

import java.rmi.RemoteException

/**
 * org.shoukaiseki.webclient.beans.interfaceconfig.InterfaceConfigSubTableDataBean <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-07 14:46:30<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */

class InterfaceConfigSubTableDataBean : DataBean() {

    @Throws(MXException::class)
    override fun addrow(): Int {
        val addrow = super.addrow()
        try {
            val wosequence = nextSn(mbo)

            mbo.setValue("sn", wosequence, MboConstants.NOACCESSCHECK or MboConstants.NOVALIDATION_AND_NOACTION)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        return addrow
    }


    @Throws(RemoteException::class, MXException::class)
    private fun nextSn(mbo: MboRemote): Int {
        val count = mbo.thisMboSet.count()
        val maxwosequnce = mbo.thisMboSet.max("sn").toInt()
        val wosequence: Int
        /**
         * 例如:如果为 10-15 则自动序列为 20
         * 如果为16-19 则自动序列为 30
         * 因为为19时,下一个为20的话,中间就无法再插入
         */
        if (maxwosequnce % 10 < 6) {
            wosequence = (maxwosequnce / 10 + 1) * 10
        } else {
            wosequence = (maxwosequnce / 10 + 2) * 10
        }
        return wosequence
    }


    @Throws(MXException::class)
    override fun copytonewrow(): Int {
        super.copytonewrow()
        try {
            val newMbo=mbo
            val wosequence = nextSn(newMbo)
            newMbo.setValue("sn", wosequence, MboConstants.NOACCESSCHECK or MboConstants.NOVALIDATION_AND_NOACTION)
            this.fireStructureChangedEvent()
            this.fireChildChangedEvent()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        return 1
    }
}
