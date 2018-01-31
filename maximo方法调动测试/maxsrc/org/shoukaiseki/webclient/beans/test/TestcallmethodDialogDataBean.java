package org.shoukaiseki.webclient.beans.test;

import org.apache.log4j.Logger;
import org.shoukaiseki.expand.CodeLineStackTrace;
import psdi.mbo.MboSetRemote;
import psdi.util.MXException;
import psdi.webclient.system.beans.DataBean;
import psdi.webclient.system.controller.ControlInstance;
import psdi.webclient.system.controller.WebClientEvent;
import psdi.webclient.system.runtime.WebClientRuntime;

import java.rmi.RemoteException;

public class TestcallmethodDialogDataBean extends DataBean{

    private final Logger logger=Logger.getLogger(this.getClass());

    public TestcallmethodDialogDataBean(){
        logger.debug("TestcallmethodDialogDataBean");
    }

    @Override
    public int callBeanMethod(WebClientEvent event) throws MXException, NoSuchMethodException, RemoteException {
        return super.callBeanMethod(event);
    }

    public int callMethod(WebClientEvent event) throws MXException {
        //只限于dialog 的databean使用, 因为 callMethod 只在 WebClientSession.handleDialogOK 会调用
        //其它地方调用的是 defaultHandler

        try {

        logger.debug("value="+event.getValue());
        if("houhou00".equals(event.getValue())){
            try {
                Class<?>[] paramTypes = new Class[]{String.class};
                //参数为改行代码的堆栈信息
                String str=CodeLineStackTrace.getStackTraceCurrentline().toString();
                logger.debug("str="+str);
                Object[] params = new Object[]{str};
                return this.callMethod(event, paramTypes, params);
            } catch (Exception var4) {
                if (var4 instanceof MXException) {
                    throw (MXException)var4;
                } else {
                    var4.printStackTrace();
                    return 0;
                }
            }
        }else if("houhou01".equals(event.getValue())||"houhou02".equals(event.getValue())) {
            return super.callMethod(event);
        }else if("houhou03".equals(event.getValue())||"houhou04".equals(event.getValue())) {
            try {
                Class<?>[] paramTypes = new Class[]{MboSetRemote.class};
                Object[] params = new Object[]{this.getMboSet()};
                return this.callMethod(event, paramTypes, params);
            } catch (Exception var4) {
                if (var4 instanceof MXException) {
                    throw (MXException) var4;
                } else {
                    var4.printStackTrace();
                    return 0;
                }
            }

        }else{
            return super.callMethod(event);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;

    }


}
