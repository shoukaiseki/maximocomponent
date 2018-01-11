package org.shoukaiseki.webclient.beans.test;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.shoukaiseki.excel.poi.*;
import psdi.util.MXException;

import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 **/
public class TestExcel2  implements ExcelBuildActionCall {



    @Override
    public void endTrigger(@NotNull ExcelTable<ExcelRow> list) throws MXException, RemoteException {

        for (ExcelRow row :list){
            System.out.println("a="+row.getString("A"));
            for (Object key:row.keySet()){
                System.out.println("key="+key+",value="+row.get(key));
            }
        }

    }

    @Override
    public void errorTrigger(@NotNull Exception e) {
    }

    @NotNull
    @Override
    public ExcelBuildConfig initialConfigure() {
        ExcelBuildConfig ebc=new ExcelBuildConfig();
        ebc.setSheet(0);
        ebc.setTitlecount(1);
        return ebc;
    }

    @Override
    public boolean rowTrigger(@NotNull ExcelRow row) throws MXException, RemoteException {
        return true;
    }

    @Override
    public void startTrigger(@NotNull ExcelBuildConfig excelBuildConfig) throws MXException, RemoteException {

    }


    public static void main(String[] args) throws Exception {
        System.out.println("start ");
        TestExcel2 te=new TestExcel2();
        FileInputStream inputStream=new FileInputStream("I:/maximotemp/testimp/user001.xls");
        ExcelBuild eb=new ExcelBuild(inputStream ,te);
        eb.readExcelContent();
    }

    @Override
    public void setImportFilename(@NotNull String name) {

    }
}
