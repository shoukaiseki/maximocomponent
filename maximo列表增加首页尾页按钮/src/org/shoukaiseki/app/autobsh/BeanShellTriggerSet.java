package org.shoukaiseki.app.autobsh;

import java.rmi.RemoteException;

import psdi.mbo.*;
import psdi.util.MXException;

/**
 * org.shoukaiseki.app.autobsh.BeanShellTriggerSet <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-09 14:47:33<br>
 *         ブログ http://shoukaiseki.blog.163.com/<br>
 *         E-メール jiang28555@Gmail.com<br>
 **/

public class BeanShellTriggerSet extends MboSet 
implements BeanShellTriggerSetRemote
{

	public BeanShellTriggerSet(MboServerInterface ms) throws RemoteException {
		super(ms);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Mbo getMboInstance(MboSet mboset) throws MXException,
			RemoteException {
		// TODO Auto-generated method stub
		return new BeanShellTrigger(mboset);
	}
	
	@Override
	public void save() throws MXException, RemoteException {
		// TODO Auto-generated method stub
		MboRemote mbo = getMbo();
		if(mbo!=null){
			if(mbo.toBeAdded()||mbo.toBeDeleted()||mbo.toBeUpdated()){
				if(mbo.toBeAdded()){
					mbo.setValue("version", 0);
				}else{
					mbo.setValue("version", mbo.getInt("version")+1);
				}
				WriteAutoBeanShellTriggerLog wabstl=new WriteAutoBeanShellTriggerLog();
				wabstl.addLogging(mbo);
			}
		}
		super.save();
	}
	
}