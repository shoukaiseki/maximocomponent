package org.shoukaiseki.app.interfaceconfig

import psdi.mbo.Mbo
import psdi.mbo.MboServerInterface
import psdi.mbo.MboSet
import java.rmi.RemoteException

/** org.shoukaiseki.app.interfaceconfig.InterfaceconfigMboSet <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-06 11:58:58<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/

open class InterfaceconfigMboSet @Throws (RemoteException::class) constructor(ms: MboServerInterface) : MboSet(ms) {

    override fun getMboInstance(ms: MboSet): Mbo {
        return InterfaceconfigMbo(ms)
    }

}