# maximo导入excel文件
maximo导入excel文件

<br>
主要 class 类由 kotlin编写 

例子使用java编写

1. 将 maximocomponent/maximolib 下面的 shoukaiseki.jar poi-3.8-20120326.jar
2. 将 maximocomponent/lib 下面的 kotlin-runtime.jar

3. 这几个jar添加到 maximo.ear/lib 目录下

4. 引入到  businessobjects.jar/META-INF/MANIFEST.MF 中添加  lib/kotlin-runtime.jar lib/shoukaiseki.jar lib/poi-3.8-20120326.jar 信息

5. 将 maximocomponent/importexcel/binversion 里面编译好的类(importexcel.jar里面的class为编译好的,eclipse中引入该包就好)放到 MAXIMO.ear/maximouiweb.war/WEB-INF/classes 下

6. 需要修改源代码,需要eclipse安装 kotlin插件,推荐使用 IntelliJ IDEA 进行开发,因为eclipse中kotlin插件并不太好用

签名添加:
```Sql
--导入excel
--SIGOPTION
OPTIONNAME:IMPORTE
DESCRIPTION:导入excle

--MAXMENU
KEYVALUE:IMPORTE
IMAGE:nav_icon_import.gif

--下载模板
--SIGOPTION
OPTIONNAME:EXPORTE
DESCRIPTION:下载模板

--MAXMENU
KEYVALUE:EXPORTE
IMAGE:nav_icon_export.gif
URL:模板的下载地址
```

## LIBRARY.xml 增加以下内容
```Xml
	<dialog beanclass="org.shoukaiseki.webclient.beans.ImportExcelDataBean" id="importe" label="导入excle">
		<section id="importexcle_1">
			<importapp id="importexcle_1_8" label="浏览文件:"/>
		</section>
		<buttongroup id="importexcle_2">
			<doclinkuploadbutton id="importexcle_file_2_1" label="确定"/>
			<pushbutton id="importexcle_2_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>
```
## 应用页面增加以下内容
```Xml
	<dialog id="exporte" label="下载模板">

		<section id="exporte_section">
			<helpgrid id="exporte_helpgrid01" innerhtml="&lt;font color=&quot;#FF0000&quot; &gt;excel中的工作表位置,标题都不可修改&lt;/font&gt;"/>
			<!--url 地址填写模板下载的地址-->
			<hyperlink id="exporte_hyperlink01" label="点击下载模板" url="http://xxx.xxx/doclinks/exceltemplate/user.xls"/>
		</section>
		<buttongroup id="exporte_2">
			<pushbutton default="true" id="exporte_2_1" label="确定" mxevent="dialogok"/>
		</buttongroup>
		
	</dialog>

```

### 页面引用
在页面中增加签名和工具条,或者按钮,签名名称/按钮事件设置为 importe 就好

在该应用的appbean中引入 ExcelBuildActionCall 接口

### 注意事项
设置mbo字段值的时候,先判断 excel中的值是否为空

否则容易引发错误

# 例子1
事例xml文件,应用xml在 sample/skstestie001 下

导入数据为子表数据

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie001/skstestie001a.png)
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie001/skstestie001b.png)
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie001/skstestie001c.png)
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie001/skstestie001d.png)

### 代码示例
```Java
package org.shoukaiseki.webclient.beans.test;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.shoukaiseki.excel.poi.ExcelBuildActionCall;
import org.shoukaiseki.excel.poi.ExcelBuildConfig;
import org.shoukaiseki.excel.poi.ExcelRow;
import org.shoukaiseki.excel.poi.ExcelTable;
import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.util.MXApplicationException;
import psdi.util.MXException;
import psdi.webclient.system.beans.AppBean;

import java.rmi.RemoteException;

/**
 * org.shoukaiseki.webclient.beans.test.SkstestieAppBean <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-22 16:48:51<br>
 *         ブログ http://shoukaiseki.blog.163.com/<br>
 *         E-メール jiang28555@Gmail.com<br>
 **/

public class SkstestieAppBean extends AppBean
        implements ExcelBuildActionCall{
    Logger log= Logger.getLogger("org.shoukaiseki");

MboSetRemote testimpexcelSet=null;

    @Override
    public void endTrigger(@NotNull ExcelTable<ExcelRow> list) throws MXException, RemoteException {
        MboSetRemote msr=getMboSet();
        try {
            log.debug("app.onListTab()="+app.onListTab());
            if(!app.onListTab()){
                MboRemote mbo=msr.getMbo();
                testimpexcelSet=mbo.getMboSet("TESTIMPEXCEL");
                for (ExcelRow row:list){
                    MboRemote testimpexcel=testimpexcelSet.add();
                    testimpexcel.setValue("TESTIMPEXCELPARENTNUM",mbo.getString("TESTIMPEXCELPARENTNUM"),2L);
                    testimpexcel.setValue("DESCRIPTION",row.getString("B"),2L);
                    testimpexcel.setValue("AMOUNT01",row.getDouble("A"),2L);
                    if(!row.isNull("C")){
                        testimpexcel.setValue("DATETIME01",row.getDate("C"),2L);
                        testimpexcel.setValue("DATE01",row.getDate("C"),2L);
                        testimpexcel.setValue("TIME01",row.getDate("C"),2L);
                    }
                    testimpexcel.setValue("BIGINT01",row.getDouble("D"),2L);
                    testimpexcel.setValue("INTEGER01",row.getDouble("D"),2L);
                    testimpexcel.setValue("SMALLINT01",row.getDouble("D"),2L);
                    testimpexcel.setValue("YORN01",row.getBoolean("E"),2L);
                    testimpexcel.setValue("DECIMAL01",row.getDouble("F"),2L);
                    testimpexcel.setValue("FLOAT01",row.getDouble("F"),2L);
                    testimpexcel.setValue("ALN01",row.getString("G"),2L);
                    testimpexcel.setValue("ALN02",row.getString("H"),2L);
                    log.debug("row="+row);
                }
                testimpexcelSet.save();
            }
            fireStructureChangedEvent();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MXApplicationException(this.getClass().getName(),e.getMessage());
        }

    }

    @Override
    public void errorTrigger(@NotNull Exception e) {
    //如果导入过程中出错则回滚
        if(testimpexcelSet!=null){
            try {
                testimpexcelSet.rollback();
            } catch (MXException e1) {
                e1.printStackTrace();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }

    @NotNull
    @Override
    public ExcelBuildConfig initialConfigure() {
        ExcelBuildConfig ebc=new ExcelBuildConfig();
        //第一个标签页
        ebc.setSheet(0);
        //标题行数为1行
        ebc.setTitlecount(1);
        return ebc;
    }

    @Override
    public boolean rowTrigger(@NotNull ExcelRow row) throws MXException, RemoteException { return true; }

    @Override
    public void startTrigger(@NotNull ExcelBuildConfig excelBuildConfig) throws MXException, RemoteException { }
}
```


# 例子2
事例xml文件,应用xml在 sample/skstestie002 下

导入数据为主表数据

如图skstestie002a.png
1. A,B列作为主表唯一标识,只有两值不相同时才进行另外增加主表
2. C,D,E,F,G,H 为子表数据,如果为空则不进行创建子表(例如22行)
3. A,B,G,H 如果为空,则定义为无效数据(例如23行)

#### skstestie002a.png
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie002/skstestie002a.png)
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie002/skstestie002b.png)
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie002/skstestie002c.png)
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie002/skstestie002d.png)
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie002/skstestie002e.png)
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie002/skstestie002f.png)
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie002/skstestie002g.png)

### 代码示例
```Java
package org.shoukaiseki.webclient.beans.test;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.shoukaiseki.excel.poi.*;
import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.util.MXApplicationException;
import psdi.util.MXException;
import psdi.webclient.system.beans.AppBean;

import java.rmi.RemoteException;

/**
 * org.shoukaiseki.webclient.beans.test.SkstestieAppBean <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-22 16:48:51<br>
 *         ブログ http://shoukaiseki.blog.163.com/<br>
 *         E-メール jiang28555@Gmail.com<br>
 **/

public class SkstestieAppBean2 extends AppBean
        implements ExcelBuildActionCall{
    Logger log= Logger.getLogger("org.shoukaiseki");

MboSetRemote testimpexcelSet=null;


    @Override
    public void endTrigger(@NotNull ExcelTable<ExcelRow> list) throws MXException, RemoteException {
        MboSetRemote msr=getMboSet();
        try {
            log.debug("app.onListTab()="+app.onListTab());
            if(!app.onListTab()){
            }else{
                MboRemote mbo=null;

                //缓存上一次的值
                String atemp=null;
                String btemp=null;
                for (ExcelRow ixrow:list){
                    //如果 A B G H 都为空,则当做次行无效
                    if(ixrow.isNulls("A","B","G","H")){
                        continue;
                    }
                    String[] colnames={"A","B"};
                    ixrow.paste(ixrow.getMergedRegionValue(colnames), colnames, false);
                    if(!IXFormat.equalsString(atemp,ixrow.getString("A"))
                            ||!IXFormat.equalsString(btemp,ixrow.getString("B"))){
                        //A,B列与上一行数据不一致才新建
                        mbo=msr.add();
                        atemp=ixrow.getString("A");
                        btemp=ixrow.getString("B");
                    }
                    mbo.setValue("DESCRIPTION",ixrow.getString("B"),2L);
                    mbo.setValue("NAME",ixrow.getString("A"),2L);
                    //G,H 列如果为空这忽略
                    if(ixrow.isNulls("G","H")){
                        continue;
                    }
                    testimpexcelSet=mbo.getMboSet("TESTIMPEXCEL");
                    MboRemote testimpexcel=testimpexcelSet.add();
                    testimpexcel.setValue("TESTIMPEXCELPARENTNUM",mbo.getString("TESTIMPEXCELPARENTNUM"),2L);
                    if(!ixrow.isNull("C")){
                        testimpexcel.setValue("DATETIME01",ixrow.getDate("C"),2L);
                    }
                    testimpexcel.setValue("BIGINT01",ixrow.getDouble("D"),2L);
                    testimpexcel.setValue("YORN01",ixrow.getBoolean("E"),2L);
                    testimpexcel.setValue("DECIMAL01",ixrow.getDouble("F"),2L);
                    testimpexcel.setValue("ALN01",ixrow.getString("G"),2L);
                    testimpexcel.setValue("ALN02",ixrow.getString("H"),2L);
                    log.debug("row="+ixrow);
                }
            }
            msr.save();
            fireStructureChangedEvent();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MXApplicationException(this.getClass().getName(),e.getMessage());
        }

    }

    @Override
    public void errorTrigger(@NotNull Exception e) {
        //如果导入过程中出错则回滚
        if(testimpexcelSet!=null){
            try {
                testimpexcelSet.rollback();
            } catch (MXException e1) {
                e1.printStackTrace();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }

    @NotNull
    @Override
    public ExcelBuildConfig initialConfigure() {
        ExcelBuildConfig ebc=new ExcelBuildConfig();
        //第一个标签页
        ebc.setSheet(0);
        //标题行数为1行
        ebc.setTitlecount(1);
        return ebc;
    }

    @Override
    public boolean rowTrigger(@NotNull ExcelRow row) throws MXException, RemoteException { return true; }

    @Override
    public void startTrigger(@NotNull ExcelBuildConfig excelBuildConfig) throws MXException, RemoteException { }
}
```
