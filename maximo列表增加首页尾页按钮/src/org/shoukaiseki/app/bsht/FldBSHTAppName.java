package org.shoukaiseki.app.bsht;

import psdi.mbo.MAXTableDomain;
import psdi.mbo.MboValue;

/**
 * org.shoukaiseki.app.bsht.FldBSHTAppName <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-09 14:50:30<br>
 *         ブログ http://shoukaiseki.blog.163.com/<br>
 *         E-メール jiang28555@Gmail.com<br>
 **/

public class FldBSHTAppName  extends MAXTableDomain{

	public FldBSHTAppName(MboValue mbv) {
		super(mbv);
		// TODO Auto-generated constructor stub
		String objectName="MAXAPPS";
		String whereClause=" APP=:"+getMboValue().getName();
		setMultiKeyWhereForLookup(" "); 
		setRelationship(objectName, whereClause);
        setLookupKeyMapInOrder(new String[] {
            getMboValue().getName()
        }, new String[] {
            "APP"
        });
	}

}
