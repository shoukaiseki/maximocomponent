package org.shoukaiseki.app.autobsh;

import java.rmi.RemoteException;

import psdi.mbo.*;
import psdi.server.MXServer;
import psdi.util.MXException;

public class WriteAutoBeanShellTriggerLog {
	public WriteAutoBeanShellTriggerLog() {
		// TODO Auto-generated constructor stub
	}
	

	public void addLogging(MboRemote mbo) {
		// TODO Auto-generated method stub
		try {
			MboSetRemote logSet =MXServer.getMXServer().getMboSet("BeanShellTriggerLog", mbo.getUserInfo());
			logSet.setWhere("1=2");
			logSet.reset();
			MboRemote log = logSet.add();
			log.setValue("description", mbo.getString("description"));
			log.setValue("DESCRIPTION_LONGDESCRIPTION", mbo.getString("DESCRIPTION_LONGDESCRIPTION"));
			log.setValue("appname", mbo.getString("appname"));
			log.setValue("class", mbo.getString("class"));
			log.setValue("function", mbo.getString("function"));
			log.setValue("when", mbo.getString("when"));
			log.setValue("source", mbo.getString("source"));
			log.setValue("Sequence", mbo.getString("Sequence"));
			log.setValue("TABLEFIELD", mbo.getString("TABLEFIELD"));
			log.setValue("sequence", mbo.getString("sequence"));
			log.setValue("version", mbo.getString("version"));
			log.setValue("BeanShellTriggerID", mbo.getLong("BeanShellTriggerID"));
			log.setValue("datetime", MXServer.getMXServer().getDate());
			log.setValue("personid", mbo.getUserInfo().getPersonId());
			log.setValue("displayname", mbo.getUserInfo().getDisplayName());
			log.setValue("ClientHost", mbo.getUserInfo().getClientHost());
			if(mbo.toBeAdded()){
				log.setValue("changed", "新建");
			}else if(mbo.toBeDeleted()){
				log.setValue("changed", "删除");
			}else{
				log.setValue("CHANGED", "修改");
				
			}
			logSet.save();
			logSet.close();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
