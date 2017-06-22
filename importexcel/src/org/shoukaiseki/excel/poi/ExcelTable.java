package org.shoukaiseki.excel.poi;

import org.apache.poi.ss.formula.functions.T;

import java.util.LinkedList;

/**
 **/
public class ExcelTable<ExcelRow> extends LinkedList<ExcelRow>{
    private ExcelBuildConfig excelBuildConfig;
    public ExcelTable( ExcelBuildConfig excelBuildConfig) {
       this.excelBuildConfig=excelBuildConfig;
    }

    public ExcelBuildConfig getExcelBuildConfig() {
        return excelBuildConfig;
    }

    public void setExcelBuildConfig(ExcelBuildConfig excelBuildConfig) {
        this.excelBuildConfig = excelBuildConfig;
    }
}
