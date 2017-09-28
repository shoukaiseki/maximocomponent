package org.shoukaiseki.expand;

import java.rmi.RemoteException;

import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.mbo.MboValueInfo;
import psdi.server.MXServer;
import psdi.util.MXException;

public class MboSetExpand {
	
	/**判断结果集是否为空,全部拥有delete标识的也做空处理,即msr.deleteAll()之后也作为空处理
	 * @param msr
	 * @return true 无有效行
	 * @throws RemoteException
	 * @throws MXException
	 */
	public static boolean isEmpty(MboSetRemote msr) throws RemoteException, MXException{
		if(msr!=null){
			for(int i=0;i<msr.count();i++){
				MboRemote mbo = msr.getMbo(i);
				if(!mbo.toBeDeleted()){
					return false;
				}
				
			}
		}
		
		return true;
	}

	/** 是否擁有 siteid 和 orgid 字段
	 * @return true 
	 * @throws RemoteException
	 */
	public static boolean hasSiteid(MboSetRemote ms) throws RemoteException{
		boolean hasOrgid=false;
		boolean hasSiteid=false;
		 ms.getMboSetInfo().getAttributes();
		 MboValueInfo attribute =MXServer.getMXServer().
				 getMaximoDD().getMboSetInfo(ms.getName()).getAttribute("SITEID");
			if(attribute!=null){
				hasSiteid=true;
			}
		 attribute =MXServer.getMXServer().
				 getMaximoDD().getMboSetInfo(ms.getName()).getAttribute("ORGID");
			if(attribute!=null){
				hasOrgid=true;
			}
		return hasOrgid&&hasSiteid;
	}

	/**
	 * 关闭mboset
	 * @param msr
	 */
	public static void closeMboSet(MboSetRemote msr){
		if(msr!=null){
			try {
				msr.close();
			} catch (MXException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}
}
