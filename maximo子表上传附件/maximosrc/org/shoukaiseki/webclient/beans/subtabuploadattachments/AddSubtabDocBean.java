package org.shoukaiseki.webclient.beans.subtabuploadattachments;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import psdi.app.doclink.DoclinkServiceRemote;
import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.mbo.SqlFormat;
import psdi.mbo.Translate;
import psdi.server.MXServer;
import psdi.util.MXApplicationException;
import psdi.util.MXException;
import psdi.util.MXSession;
import psdi.webclient.beans.doclinks.RegisterDocBean;
import psdi.webclient.system.beans.AppBean;
import psdi.webclient.system.beans.DataBean;
import psdi.webclient.system.controller.ControlHandler;
import psdi.webclient.system.controller.UploadFile;
import psdi.webclient.system.controller.Utility;
import psdi.webclient.system.controller.WebClientEvent;
import psdi.webclient.system.runtime.WebClientRuntime;

/**
 * org.shoukaiseki.webclient.beans.subtabuploadattachments.AddSubtabDocBean <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-11-13 15:13:39<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class AddSubtabDocBean extends RegisterDocBean {

    public static final Logger logger=Logger.getLogger("org.shoukaiseki");


    public AddSubtabDocBean() {
        super();
    }


    public void initialize() throws MXException, RemoteException {
        logger.debug("initialize");
        super.initialize();
    }

    protected MboSetRemote getMboSetRemote() throws MXException, RemoteException {
        ControlHandler handler = this.creatingEvent.getSourceControl();
        String dataSrc = handler.getProperty("datasrc");
        DataBean dataBean = this.app.getDataBean(dataSrc);
        return dataBean.getMboSet().getMbo().getMboSet("DOCLINKS");
    }

    public synchronized void insert() throws MXException, RemoteException {
        logger.debug("insert.mbo="+getMboSet().getName());
        logger.debug("owner.name="+getMboSet().getOwner().getName());
        super.insert();
        this.setValue("ADDINFO", "TRUE");
        this.setValue("document", this.getMbo().getMboSet("DOCINFO").getMbo().getString("document"));
        this.dataChangedEvent(this);
    }

    public synchronized void close() {
        if (this.dialogReferences > 0) {
            --this.dialogReferences;
        } else {
            this.resetDataBean();
            this.cleanup();
        }
    }

    public int execute() throws MXException, RemoteException {
        int retVal = super.execute();
        this.validate();

        try {
            if (this.app.getAppBean() != null) {
                ((AppBean)this.app.getAppBean()).saveattachment();
            } else {
                this.save();
            }
        } catch (MXException var4) {
            WebClientEvent currentEvent = this.sessionContext.getCurrentEvent();
            Utility.showMessageBox(currentEvent, var4);
            this.getMboSet().reset();
            return 1;
        }

        if (this.parent != null) {
            this.parent.fireStructureChangedEvent();
        }

        return retVal;
    }

    public int cancelDialog() throws MXException, RemoteException {
        this.getMboSet().reset();
        int ret = super.cancelDialog();

        try {
            this.validate();
        } catch (MXException var3) {
            this.mboSetRemote.deleteAndRemove(this.getMbo(), 2L);
        }

        return ret;
    }

    public int passSuberExecute() throws MXException, RemoteException {
        int retVal = super.execute();
        return retVal;
    }




}
