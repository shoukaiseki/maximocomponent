package org.shoukaiseki.webclient.beans.test;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.shoukaiseki.excel.poi.ExcelBuildActionCall;
import org.shoukaiseki.excel.poi.ExcelBuildConfig;
import org.shoukaiseki.excel.poi.ExcelRow;
import org.shoukaiseki.excel.poi.ExcelTable;
import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
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


    @Override
    public void endTrigger(@NotNull ExcelTable<ExcelRow> list) throws MXException, RemoteException {
        MboSetRemote msr=getMboSet();
        log.debug("app.onListTab()="+app.onListTab());
        if(!app.onListTab()){
            MboRemote mbo=msr.getMbo();
            MboSetRemote testimpexcelSet=mbo.getMboSet("TESTIMPEXCEL");
            for (ExcelRow row:list){
               MboRemote testimpexcel=testimpexcelSet.add();
               testimpexcel.setValue("ALN01",row.getString("B"),2L);
                log.debug("row="+row);
            }
            testimpexcelSet.save();
        }

    }

    @Override
    public void errorTrigger(@NotNull Exception e) {

    }

    @NotNull
    @Override
    public ExcelBuildConfig initialConfigure() {
        ExcelBuildConfig ebc=new ExcelBuildConfig();
       ebc.setSheet(1);
       ebc.setTitlecount(1);
        return ebc;
    }

    @Override
    public boolean rowTrigger(@NotNull ExcelRow row) throws MXException, RemoteException { return false; }

    @Override
    public void startTrigger(@NotNull ExcelBuildConfig excelBuildConfig) throws MXException, RemoteException { }
}
