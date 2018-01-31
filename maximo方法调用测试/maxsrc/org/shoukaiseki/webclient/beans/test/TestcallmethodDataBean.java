package org.shoukaiseki.webclient.beans.test;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.shoukaiseki.expand.CodeLineStackTrace;
import psdi.mbo.MboSetRemote;
import psdi.util.MXException;
import psdi.webclient.system.beans.DataBean;
import psdi.webclient.system.controller.WebClientEvent;

import java.rmi.RemoteException;

/**
 * org.shoukaiseki.webclient.beans.test.TestcallmethodDataBean <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2018-01-29 16:47:50<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class TestcallmethodDataBean extends DataBean{

    private final Logger logger=Logger.getLogger(this.getClass());

    public TestcallmethodDataBean(){
        logger.setLevel(Level.DEBUG);
    }

    @Override
    public int addrow() throws MXException {
        //该方法调用来自于 psdi.webclient.controls.Table.addrow
        logger.debug(CodeLineStackTrace.getStackTrace());
        int reint = super.addrow();
        try {
            getMbo().setValue("PARENTID",getMboSet().getOwner().getUniqueIDValue(),2L);
        } catch (RemoteException e) {
            logger.error("",e);
            e.printStackTrace();
        }
        return reint;
    }
}
