package org.shoukaiseki.app.test;

import psdi.mbo.Mbo;
import psdi.mbo.MboServerInterface;
import psdi.mbo.MboSet;
import psdi.util.MXException;

import java.rmi.RemoteException;

public class TestcallmethodMboSet extends MboSet{

    public TestcallmethodMboSet(MboServerInterface ms) throws RemoteException {
        super(ms);
    }

    @Override
    protected Mbo getMboInstance(MboSet mboSet) throws MXException, RemoteException {
        return new TestcallmethodMbo(mboSet);
    }
}
