package org.shoukaiseki.webclient.beans.test;

import org.shoukaiseki.expand.CodeLineStackTrace;
import psdi.mbo.MboSetRemote;
import psdi.util.MXException;
import psdi.webclient.system.beans.AppBean;
import psdi.webclient.system.controller.WebClientEvent;

import java.rmi.RemoteException;

/**
 * org.shoukaiseki.webclient.beans.test.TTestcallmethodAppBean<br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2018-01-29 16:47:272018-01-29グ16:47:42shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class TestcallmethodAppBean extends AppBean {

    public TestcallmethodAppBean(){

    }

    public int houhou05() throws RemoteException, MXException {
        this.getMbo().setValue("DESCRIPTION","houhou05被执行,\n代码路径为"+ CodeLineStackTrace.getStackTraceCurrentline(),2L);
        //刷新方法一
//        setCurrentRecordData(this.getMbo());
        //刷新方法二
        refreshTable();
        return 1;
    }
}
