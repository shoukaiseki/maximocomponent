package org.shoukaiseki.app.autobsh.configu;

import java.rmi.RemoteException;

import psdi.mbo.*;
import psdi.util.MXApplicationException;
import psdi.util.MXException;

/**
 * org.shoukaiseki.app.autobsh.configu.FldBSHTFunctionLookup <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-12 09:39:18<br>
 *         ブログ http://shoukaiseki.blog.163.com/<br>
 *         E-メール jiang28555@Gmail.com<br>
 **/

public class FldBSHTFunctionLookup  extends MAXTableDomain{
	
	public FldBSHTFunctionLookup(MboValue mbv) {
		super(mbv);
		// TODO Auto-generated constructor stub
		String objectName="BEANSHELLCLASSFUNCTION";
		String whereClause="ISPARENT=0 and FUNCTION=:"+getMboValue().getName();
		setMultiKeyWhereForLookup("ISPARENT=0"); 
		setRelationship(objectName, whereClause);
        setLookupKeyMapInOrder(new String[] {
            getMboValue().getName()
        }, new String[] {
            "FUNCTION"
        });
	}
	
	@Override
	public MboSetRemote getList() throws MXException, RemoteException {
		// TODO Auto-generated method stub
		MboSetRemote list = super.getList();
		if(!getMboValue().getMbo().isValid()){
			return list;
		}
		if(!getMboValue().getMbo().isNull("CLASS")){
			list.setWhere("CLASS='"+getMboValue().getMbo().getString("CLASS")+"'");
		}else{
			list.setWhere("");
            throw new MXApplicationException("请先选择触发类!", "");
		}
		list.reset();
		return list;
	}

}
