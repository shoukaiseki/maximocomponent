package org.shoukaiseki.app.test;

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

public class TestcallmethodMbo extends CustomMbo {

    public final static Logger logger=Logger.getLogger("org.shoukaiseki");

    public TestcallmethodMbo(MboSet ms) throws RemoteException {
        super(ms);
    }

    public void houhou00(String str) throws MXException, RemoteException{
//        setValue("DESCRIPTION","houhou00被执行.参数传递来自于"+str,11L);
        setValue("DESCRIPTION","houhou00被执行,参数传递来自于"+str+
                "\n代码路径为"+ CodeLineStackTrace.getStackTraceCurrentline(),2L);

    }

    /**
     * 方法二
     */
    public void houhou02() throws MXException, RemoteException{
        setValue("DESCRIPTION","houhou02被执行\n代码路径为"+ CodeLineStackTrace.getStackTraceCurrentline(),2L);
        logger.debug("houhou02..");
    }

    /**
     * 方法四
     */
    public void houhou04(MboSetRemote msr) throws MXException, RemoteException{
        Vector selection = msr.getSelection();
        List<String> sb= new ArrayList<>();
        for (int i=0;i<selection.size();i++){
            MboRemote mbo = (MboRemote) selection.get(i);
            sb.add(mbo.getString("DESCRIPTION"));
        }
        setValue("DESCRIPTION","houhou04被执行.选择的行有"+sb,2L);
        setValue("DESCRIPTION","houhou04被执行,选择的行有"+sb+"\n代码路径为"+ CodeLineStackTrace.getStackTraceCurrentline(),2L);
        logger.debug("houhou04.."+msr.getName());
    }



}
