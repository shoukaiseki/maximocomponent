package org.shoukaiseki.expand;

import java.rmi.RemoteException;
import java.util.HashMap;

import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.mbo.SqlFormat;
import psdi.server.MXServer;
import psdi.util.MXException;

/**
 * org.shoukaiseki.app.expand.SiteLinkShort <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-09 14:53:33<br>
 *         ブログ http://shoukaiseki.blog.163.com/<br>
 *         E-メール jiang28555@Gmail.com<br>
 **/

public class SiteLinkShort {
	private static HashMap<String, String> sls=null;
	MboSetRemote msr=null;
	
	public SiteLinkShort(MboSetRemote msr){
		this.msr=msr;
	}

	/** 獲取site的英文簡稱,用与生成操作票之类的编号
 	  *     首次調用會自動調用 initSiteLinkShort 
	 *  @see #initSiteLinkShort()
	 * @param site		要獲取簡稱的站點名稱
	 * @return				site的英文簡稱
	 * @throws RemoteException
	 * @throws MXException
	 */
	public String getSiteEnShort(String site) throws RemoteException, MXException{
		if(sls==null){
			initSiteLinkShort();
		}
		return sls.get(site);
	}
	
	/** 將獲取域中 SITELINKSHORT  站點名簡寫映射,存放到 HashMap 中,用於下次調用,以免下次調用時還要檢索數據庫而造成的效率降低
	 * @throws RemoteException
	 * @throws MXException
	 */
	public void initSiteLinkShort() throws RemoteException, MXException{
			sls=new HashMap<String, String>();
			MboSetRemote mboSet = MXServer.getMXServer().getMboSet("ALNDOMAIN", msr.getUserInfo());
			SqlFormat sqf=new SqlFormat("domainid=:1");
			sqf.setObject(1, "ALNDOMAIN","DOMAINID","SITELINKSHORT");
			mboSet.setWhere(sqf.format());
			for (int i = 0; i < mboSet.count(); i++) {
				MboRemote mbo = mboSet.getMbo(i);
				String value = mbo.getString("VALUE");
				String description = mbo.getString("DESCRIPTION");
				sls.put(value, description);
			}
			mboSet.close();
	}

}
