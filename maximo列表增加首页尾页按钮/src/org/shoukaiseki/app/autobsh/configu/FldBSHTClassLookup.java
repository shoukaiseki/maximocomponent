package org.shoukaiseki.app.autobsh.configu;

import psdi.mbo.*;

/**
 * org.shoukaiseki.app.autobsh.configu.FldBSHTClassLookup <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-12 09:39:30<br>
 *         ブログ http://shoukaiseki.blog.163.com/<br>
 *         E-メール jiang28555@Gmail.com<br>
 **/
public class FldBSHTClassLookup  extends MAXTableDomain{

	public FldBSHTClassLookup(MboValue mbv) {
		super(mbv);
		// TODO Auto-generated constructor stub
		String objectName="BEANSHELLCLASSFUNCTION";
		String whereClause="ISPARENT=1 and class=:"+getMboValue().getName();
		setMultiKeyWhereForLookup("ISPARENT=1"); 
		setRelationship(objectName, whereClause);
        setLookupKeyMapInOrder(new String[] {
            getMboValue().getName()
        }, new String[] {
            "class"
        });
	}

}
