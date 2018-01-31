package org.shoukaiseki.app.test;

import com.sun.jna.StringArray;
import org.apache.log4j.Logger;
import org.shoukaiseki.expand.CodeLineStackTrace;
import psdi.mbo.MboRemote;
import psdi.mbo.MboSet;
import psdi.mbo.MboSetRemote;
import psdi.mbo.custapp.CustomMbo;
import psdi.util.MXException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Testcallmethod2Mbo extends CustomMbo {

    public final static Logger logger= Logger.getLogger("org.shoukaiseki");


    public Testcallmethod2Mbo(MboSet ms) throws RemoteException {
        super(ms);
    }

    /**
     * 方法一
     */
    public void houhou01() throws MXException, RemoteException{
        if(this.getOwner()!=null){
            this.getOwner().setValue("DESCRIPTION","houhou01被执行\n代码路径为"+ CodeLineStackTrace.getStackTraceCurrentline(),2L);
        }
        logger.debug("houhou01..");
    }

    /**
     * 方法三
     */
    public void houhou03(MboSetRemote msr) throws MXException, RemoteException{
        Vector selection = msr.getSelection();
        List<String> sb= new ArrayList<>();
        for (int i=0;i<selection.size();i++){
            MboRemote mbo = (MboRemote) selection.get(i);
            sb.add(mbo.getString("DESCRIPTION"));
        }
        if(this.getOwner()!=null){
            this.getOwner().setValue("DESCRIPTION","houhou03被执行.选择的行有"+sb+"\n代码路径为"+ CodeLineStackTrace.getStackTraceCurrentline(),2L);
        }
        logger.debug("houhou03.."+msr.getName());
    }

}
