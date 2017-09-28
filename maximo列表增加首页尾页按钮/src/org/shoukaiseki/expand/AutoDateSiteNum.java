package org.shoukaiseki.expand;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.mbo.SqlFormat;
import psdi.server.MXServer;
import psdi.util.MXException;

/**
 * org.shoukaiseki.expand.AutoDateSiteNum <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-09 14:51:31<br>
 *         ブログ http://shoukaiseki.blog.163.com/<br>
 *         E-メール jiang28555@Gmail.com<br>
 **/

public class AutoDateSiteNum {
	private MboSetRemote msr=null;
	
	public AutoDateSiteNum(MboSetRemote msr) {
		// TODO Auto-generated constructor stub
		this.msr=msr;
	}

	public int getNextAutoDateSiteNum(String siteid,String orgid,String ownerTable,String ownerAttributeName,Date date) throws RemoteException, MXException{
			int num=1;
			Calendar cal=Calendar.getInstance();
			cal.setTime(date);
			MboSetRemote mboSet = MXServer.getMXServer().getMboSet("AUTODATESITENUM", msr.getUserInfo());
			SqlFormat sqf=new SqlFormat(" SITEID=:1  and ORGID=:2 and OWNERTABLE=:3 " +
					" and OWNERATTRIBUTENAME=:4 and YEAR=:5 and MONTH=:6");
			sqf.setObject(1, "AUTODATESITENUM", "SITEID", siteid);
			sqf.setObject(2, "AUTODATESITENUM", "ORGID", orgid);
			sqf.setObject(3, "AUTODATESITENUM", "OWNERTABLE", ownerTable);
			sqf.setObject(4, "AUTODATESITENUM", "OWNERATTRIBUTENAME", ownerAttributeName);
			sqf.setInt(5,cal.get(Calendar.YEAR));
			sqf.setInt(6,cal.get(Calendar.MONTH)+1);
			mboSet.setWhere(sqf.format());
			mboSet.reset();
			if(mboSet.count()==0){
				MboRemote mbo = mboSet.add();
				mbo.setValue("SITEID", siteid);
				mbo.setValue("ORGID", orgid);
				mbo.setValue("OWNERTABLE", ownerTable);
				mbo.setValue("OWNERATTRIBUTENAME", ownerAttributeName);
				mbo.setValue("YEAR", cal.get(Calendar.YEAR));
				mbo.setValue("MONTH", cal.get(Calendar.MONTH)+1);
				mbo.setValue("NUM", num);
			}else{
				MboRemote mbo = mboSet.getMbo(0);
				num=mbo.getInt("NUM");
				mbo.setValue("NUM", ++num);
			}
			mboSet.save();
			mboSet.close();
		return num;
	}

}

















