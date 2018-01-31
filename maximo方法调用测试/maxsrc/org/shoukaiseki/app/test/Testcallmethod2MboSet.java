package org.shoukaiseki.app.test;

import psdi.mbo.Mbo;
import psdi.mbo.MboServerInterface;
import psdi.mbo.MboSet;
import psdi.util.MXException;

import java.rmi.RemoteException;

/**
 * org.shoukaiseki.app.test.Testcallmethod2MboSet <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2018-01-29 16:05:51<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class Testcallmethod2MboSet extends MboSet {

    public Testcallmethod2MboSet(MboServerInterface ms) throws RemoteException {
        super(ms);
    }

    @Override
    protected Mbo getMboInstance(MboSet mboSet) throws MXException, RemoteException {
        return new Testcallmethod2Mbo(mboSet) ;
    }

}
