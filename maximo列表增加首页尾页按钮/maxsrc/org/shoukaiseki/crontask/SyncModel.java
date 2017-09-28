package org.shoukaiseki.crontask;


import java.util.ArrayList;
import java.util.Date;

/**
 * com.demo.common.SyncModel <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-20 21:53:16<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class SyncModel {

    public static final String ERROR = "error";
    public static final String SUCCESS = "success";

    /**
     * 文件名
     */
    private String filename;

    /**
     * 读取的当前行数
     */
    private int linenum;

    /**
     * 修改时间
     */
    private Date modifydate;


    /**
     * 修改时间格式化之后数值
     */
    private String modifydatestring;

    /**
     *  本次读取的数据
     */
    private ArrayList<String> data= new ArrayList<String>();

    /**
     * 状态,成功返回 SUCCESS
     */
    private  String status=SUCCESS;

    //错误信息提示
    private String errorinfo = "NONE";

    /**
     * exception 转换为 String 格式信息
     */
    private String errorexception;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getLinenum() {
        return linenum;
    }

    public void setLinenum(int linenum) {
        this.linenum = linenum;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getModifydatestring() {
        return modifydatestring;
    }

    public void setModifydatestring(String modifydatestring) {
        this.modifydatestring = modifydatestring;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorinfo() {
        return errorinfo;
    }

    public void setErrorinfo(String errorinfo) {
        this.errorinfo = errorinfo;
    }

    public String getErrorexception() {
        return errorexception;
    }

    public void setErrorexception(String errorexception) {
        this.errorexception = errorexception;
    }
}
