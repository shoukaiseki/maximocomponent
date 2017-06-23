package org.shoukaiseki.excel.poi;



import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.Column;
import psdi.mbo.MboRemote;
import psdi.util.MXApplicationException;
import psdi.util.MXException;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

/**
 **/
public class ExcelRow extends LinkedHashMap {

    public static final Logger log= Logger.getLogger("org.shoukaiseki");
    public static final SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ExcelTable table;
    private long rownum=0;

    public ExcelRow(ExcelTable table){
       this.table=table;
    }

    public ExcelTable getTable() {
        return table;
    }

    public long getRownum() {
        return rownum;
    }

    public void setRownum(long rownum) {
        this.rownum = rownum;
    }

    public void setTable(ExcelTable table) {
        this.table = table;
    }


    public Boolean getBoolean(Object colname1){
        String colname=IXFormat.trimUpperCase(colname1.toString());
        Object value=get(colname);
        if(value instanceof Boolean){
            return (Boolean)get(colname);
        }
       return Boolean.parseBoolean(getString(colname));
    }

    public String getString(Object colname1){
        String colname=IXFormat.trimUpperCase(colname1.toString());
        Object value=get(colname);
        if(value instanceof Date){
            return sdf.format(value);
        }else if(value instanceof  Boolean){
            return ((boolean)value)?"1":"0";
        }
        return get(colname).toString();
    }

    public Date getDate(Object colname1)
    throws Exception
    {
        String colname=IXFormat.trimUpperCase(colname1.toString());
        Object value=get(colname);
        if(value instanceof Date){
            return (Date)value;
        }
        try {
            return sdf.parse(value.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            MessageFormat fmt = new MessageFormat("ExcelRow.getDate 转换出错:第 {0} 行 第 {1} 列", Locale.getDefault());
            Object[] params={rownum+table.getExcelBuildConfig().getTitlecount(),colname};
            throw new Exception(fmt.format(params));
        }

    }

    public Double getDouble(Object colname1){
        String colname=IXFormat.trimUpperCase(colname1.toString());
        Object value=get(colname);
        if(value instanceof Double){
            return (Double)value;
        }
        return new BigDecimal(value.toString()).doubleValue();

    }


    /** 獲取合併區域的首行
     *  一般用於像 excle 層次結構,比如B,C,D,E為一個主表,F,G為子表
     *  第一行這幾列都會有值,而第2-5行B,C,D,E沒有值,F,G有值,實際上,B,C,D,E的值應該要跟第1行一樣,相當與這些區域是合併的
     *  該方法即是可以在第2-5行獲取第1行的某字段值
     * @param attNames						區域字段集,這些字段都為空時,判定為非新區域首行,會向上查找區域首行,
     * 				比如:主表的某行C列為空,而D,E列都有值得,該行屬於一個新區域,如果單單判定C列會誤認為該行不作為一條新記錄,所以採用多字段為空判定主表(區域首行)
     * @return	行
     */
    public ExcelRow getMergedRegionValue(String...  attNames){
        ExcelRow ixr=this;
        ExcelTable<ExcelRow> ixTable = ixr.getTable();
        for (int index=ixTable.indexOf(ixr);true;) {
            if(!ixr.isNulls(attNames)){
                return ixr;
            }
            if(index>0){
                ixr=  ixTable.get(--index);
            }else{
                return ixr;
            }
        }
    }


    public boolean isNulls(){
        for (Object attName : keySet()) {
            if(!this.isNull(attName.toString())){
                return false;
            }
        }
        return true;
    }

    /** 這些字段是否都為空
     * @param attNames
     * @return true:都為空或null,false:有不為空的
     */
    public boolean isNulls(String... attNames){
        for (String attName : attNames) {
            if(!this.isNull(attName)){
                return false;
            }
        }
        return true;
    }

    public boolean isNull(String attName) {
        // TODO Auto-generated method stub
        if(get(attName.toUpperCase())==null||getString(attName).isEmpty()){
            return true;
        }else{
            return get(attName.toUpperCase()).toString().isEmpty();
        }
    }


    /** 粘貼,將某一行對應字段(attNames)的值粘貼到該行中
     * @param ixr
     * @param attNames			字段數組
     * @param force					是否強制覆蓋,為 false 時如果該行某字段有值將會保留,為 true 時強制替換
     * @throws Exception
     */
    public void paste(ExcelRow ixr,String[] attNames,boolean force) throws Exception{
        for (Object key : keySet()) {
            key=IXFormat.trimUpperCase(key.toString());
            if(IXFormat.isIn(key.toString(), attNames)){
				log.info("複製字段值["+key+"]{"+get(key)+"}");
				log.info("源字段值["+key+"]{"+ixr.get(key)+"}");
            }
        }
        for (String attName : attNames) {
            attName=IXFormat.trimUpperCase(attName);
            if((isNull(attName))&&!this.equals(ixr)){
                put(attName,ixr.get(attName));
				log.info("copy "+attName+" ["+ixr.getString(attName)+"] to ["+getString(attName)+"]");
            }else{
				log.info("not copy");
            }
        }
    }



    /**
     * Set value.
     *
     * @param mbo
     *         要设置值得字段名
     * @param mboattname
     *         mbo 中的字段名
     * @param value
     *         要设置的值
     * @param l
     *         mbo.setValue 的 第三个参数
     */
    public void setValue(MboRemote mbo,String mboattname,Object value,long l) throws MXApplicationException {
        String mboname=null;
            try {
                mboname=mbo.getName();
                if(value instanceof  String){
                    mbo.setValue(mboattname,((String) value).toString(),l);
                }
            } catch (Exception e) {
                e.printStackTrace();
                MessageFormat fmt = new MessageFormat("字段{0}设置值出错:{1}", Locale.getDefault());
                Object[] params={mboattname,e.getMessage()};
                throw new MXApplicationException(mboname,fmt.format(params));
            }
    }

}
