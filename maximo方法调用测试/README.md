# maxiom方法调用测试

<br>
事例如下图:

子表新建3行数据,保存之后点击 testhouhou 按钮

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8%E6%B5%8B%E8%AF%95/img/001.png)

点击之后对话框如下图所示

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8%E6%B5%8B%E8%AF%95/img/002.png)

houhou00 按钮点击

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8%E6%B5%8B%E8%AF%95/img/houhou00.png)


houhou01 按钮点击

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8%E6%B5%8B%E8%AF%95/img/houhou01.png)


houhou02 按钮点击

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8%E6%B5%8B%E8%AF%95/img/houhou02.png)


houhou03 按钮点击

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8%E6%B5%8B%E8%AF%95/img/houhou03.png)


houhou04 按钮点击

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8%E6%B5%8B%E8%AF%95/img/houhou04.png)

houhou05 按钮点击

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8%E6%B5%8B%E8%AF%95/img/houhou05.png)


#### Testcallmethod2Mbo
```Java
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
```


#### TestcallmethodMbo
```Java
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
```

#### TestcallmethodAppBean
```Java
package org.shoukaiseki.webclient.beans.test;

import org.shoukaiseki.expand.CodeLineStackTrace;
import psdi.util.MXException;
import psdi.webclient.system.beans.AppBean;

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
```


#### TestcallmethodDataBean
```Java
package org.shoukaiseki.webclient.beans.test;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.shoukaiseki.expand.CodeLineStackTrace;
import psdi.util.MXException;
import psdi.webclient.system.beans.DataBean;

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
```

#### TestcallmethodDialogDataBean
```Java
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

```

### java类 位于 maxsrc 目录

[maxsrc](https://github.com/shoukaiseki/maximocomponent/tree/master/maximo%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8%E6%B5%8B%E8%AF%95/maxsrc)

### 建表/应用语句查看 resources 目录

[resources](https://github.com/shoukaiseki/maximocomponent/tree/master/maximo%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8%E6%B5%8B%E8%AF%95/resources)
