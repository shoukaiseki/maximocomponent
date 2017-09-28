package org.shoukaiseki.app.autobsh;

import java.rmi.RemoteException;

import psdi.mbo.Mbo;
import psdi.mbo.MboServerInterface;
import psdi.mbo.MboSet;
import psdi.util.MXException;

/**
 * org.shoukaiseki.app.autobsh.AutoBeanShellSet <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-09 14:48:06<br>
 *         ブログ http://shoukaiseki.blog.163.com/<br>
 *         E-メール jiang28555@Gmail.com<br>
 **/

public class AutoBeanShellSet extends MboSet implements AutoBeanShellSetRemote {

	public AutoBeanShellSet(MboServerInterface ms) throws RemoteException {
		super(ms);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save() throws MXException, RemoteException {
		// TODO Auto-generated method stub
		super.save();
	}

	@Override
	protected Mbo getMboInstance(MboSet mboset) throws MXException,
			RemoteException {
		// TODO Auto-generated method stub
		return new AutoBeanShell(mboset);
	}

}
