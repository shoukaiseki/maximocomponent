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

    @Override
    public void setImportFilename(@NotNull String name) {

    }
}
