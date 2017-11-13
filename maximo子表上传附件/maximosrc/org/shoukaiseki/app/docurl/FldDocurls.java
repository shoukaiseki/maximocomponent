package org.shoukaiseki.app.docurl;

import com.wmc.app.defect.FldAssetnum;
import psdi.mbo.*;
import psdi.util.MXException;

import java.rmi.RemoteException;

/**
 * org.shoukaiseki.app.docurl.FldDocurls <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-11-13 15:36:29<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class FldDocurls extends MboValueAdapter {

    public FldDocurls(MboValue mbv) {
        super(mbv);
    }


    @Override
    public void initValue() throws MXException, RemoteException {
        super.initValue();
        MboSetRemote doclinksSet = getMboValue().getMbo().getMboSet("DOCLINKS");
        MboSetEnumeration mse=new MboSetEnumeration(doclinksSet);
        StringBuffer sb=new StringBuffer();
        while (mse.hasMoreElements()){
            MboRemote doclink = mse.nextMbo();
            if(!sb.toString().isEmpty()){
                sb.append("<br>");
            }
            sb.append(doclink.getString("document"));
        }
        getMboValue().setFlag(16L,true);
        getMboValue().setValue(sb.toString());

    }
}
