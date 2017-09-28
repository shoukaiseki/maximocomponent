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
