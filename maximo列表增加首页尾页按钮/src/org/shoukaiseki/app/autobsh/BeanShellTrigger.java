package org.shoukaiseki.app.autobsh;

import java.rmi.RemoteException;

import psdi.mbo.*;
import psdi.util.MXException;

public class BeanShellTrigger extends Mbo implements BeanShellTriggerRemote {

	public BeanShellTrigger(MboSet ms) throws RemoteException {
		super(ms);
		// TODO Auto-generated constructor stub
	}

    public MboRemote duplicate() throws MXException, RemoteException 
    { 
    	MboRemote newTestRemote = copy(); 
    	return newTestRemote; 
    }
    protected boolean skipCopyField(MboValueInfo mvi) throws MXException, RemoteException 
    { 
    	String name=mvi.getAttributeName();
    	return !("ORGID".equalsIgnoreCase(name)||"SITEID".equalsIgnoreCase(name))&&mvi.isKey(); 
    }

}













