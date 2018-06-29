package org.shoukaiseki.webclient.components;

import java.rmi.RemoteException;
import java.util.HashMap;

import psdi.mbo.MboRemote;
import psdi.mbo.MboSetEnumeration;
import psdi.mbo.MboSetRemote;
import psdi.mbo.SqlFormat;
import psdi.util.MXException;
import psdi.webclient.controls.TableDataRow;
import psdi.webclient.system.beans.DataBean;
import psdi.webclient.system.controller.AppInstance;
import psdi.webclient.system.controller.BoundComponentInstance;
import psdi.webclient.system.controller.ControlInstance;
import psdi.webclient.system.runtime.WebClientRuntime;
import psdi.webclient.system.session.WebClientSession;

/**
 * org.shoukaiseki.webclient.components.ImgLibUserComponents <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2018-01-05 19:27:23<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class ImgLibUserComponents extends BoundComponentInstance {


    public ImgLibUserComponents() {
    }

    public void initialize() {
        super.initialize();

    }

    public MboSetRemote getImglibuserSet() {
        MboSetRemote imglibuserSet=null;
        String dataSrc = this.getProperty("datasrc");
        WebClientSession clientSession = this.getWebClientSession();
        AppInstance app = clientSession.getCurrentApp();
        DataBean imagedb = app.getDataBean(dataSrc);

        try {
            MboRemote mbo;
                mbo = imagedb.getMbo();

            if (mbo != null) {
                SqlFormat sf=new SqlFormat("REFOBJECT=:1 and REFOBJECTID=:2");
                sf.setObject(1,"IMGLIBUSER","REFOBJECT",mbo.getName());
                sf.setLong(2,mbo.getUniqueIDValue());
                imglibuserSet = mbo.getMboSet("$IMGLIBUSER_VIEW", "IMGLIBUSER", sf.format());
                imglibuserSet.setOrderBy("SN");
                imglibuserSet.reset();
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        }


        return imglibuserSet;
    }

    public String getId(boolean useRow) {

        return this.getProperty("id");
    }

    public String getRenderId() {
        return this.getProperty("id");
    }

    public int render() {
        try {
            super.render();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return 1;
    }

}

