package org.shoukaiseki.app.interfaceconfig

import psdi.mbo.Mbo
import psdi.mbo.MboServerInterface
import psdi.mbo.MboSet
import psdi.mbo.custapp.CustomMboSet
import psdi.util.MXException

import java.rmi.RemoteException

/**
 * org.shoukaiseki.app.interfaceconfig.InterfaceconfigsubtableMboSet <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-22 11:35:46<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
class InterfaceconfigsubtableMboSet @Throws(RemoteException::class)
constructor(ms: MboServerInterface) : CustomMboSet(ms) {

    @Throws(MXException::class, RemoteException::class)
    override fun getMboInstance(mboSet: MboSet): Mbo {
        return InterfaceconfigsubtableMbo(mboSet)
    }
}
