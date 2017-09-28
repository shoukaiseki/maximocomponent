package org.shoukaiseki.crontask;

import com.alibaba.fastjson.JSONObject;
import org.shoukaiseki.crontask.util.EnvirSend;
import psdi.app.system.CrontaskParamInfo;
import psdi.mbo.*;
import psdi.server.MXServer;
import psdi.util.MXException;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * org.shoukaiseki.crontask.EnvirConTask <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-21 09:15:23<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class EnvirConTask extends AbstractSimpleCronTask{

    public StringBuffer executeInterface(String filename)  {
        StringBuffer sb=new StringBuffer();
        MboSetRemote envirconfSet = null;
        try {
            envirconfSet=MXServer.getMXServer().getMboSet("ENVIRCONF", getRunasUserInfo());
            SqlFormat sf=new SqlFormat("filename=:1");
            sf.setObject(1,"ENVIRCONF","FILENAME",filename);
            String where=sf.format();
            log.debug("where="+where);
            envirconfSet.setWhere(where);
            envirconfSet.reset();
            MboRemote envirconf=envirconfSet.getMbo(0);
            if(envirconfSet.getMbo(0)==null){
                envirconf=envirconfSet.add();
                envirconf.setValue("FILENAME",filename, MboConstants.NOACCESSCHECK);
                envirconf.setValue("LINENUM",0, MboConstants.NOACCESSCHECK);
            }
            SyncModel sm=new SyncModel();
            sm.setLinenum(envirconf.getInt("LINENUM"));
            sm.setFilename(envirconf.getString("FILENAME"));
            if(!envirconf.isNull("MODIFYDATE")){
                sm.setModifydate(envirconf.getDate("MODIFYDATE"));
            }
            sm = EnvirSend.sendPost(getParams("Interfaceurl"), sm);
            if(SyncModel.SUCCESS.equalsIgnoreCase(sm.getStatus())){
                for (String line:sm.getData()) {
                    lineDealWith(line);
                }
                if(sm.getModifydate()!=null){
                    envirconf.setValue("LINENUM",sm.getLinenum(), MboConstants.NOACCESSCHECK);
                    envirconf.setValue("MODIFYDATE",sm.getModifydate(), MboConstants.NOACCESSCHECK);
                }
                envirconf.setValue("STATUSDATE",MXServer.getMXServer().getDate(), MboConstants.NOACCESSCHECK);
                envirconfSet.save();
                log.debug("save envirconf");
            }

        } catch (Exception e) {
            try {
                if(envirconfSet!=null){
                    envirconfSet.rollback();
                }
            } catch (MXException e1) {
                e1.printStackTrace();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            closeMboSet(envirconfSet);
        }
       return  sb;
    }

    private void lineDealWith(String line) {
        if(line==null){
            return;
        }
        String str=line.trim();
        if(line.indexOf("ERROR CODE")>-1){
            return;
        }

        //1个或多个空格
        String[] split = line.split("\\s+");
        log.debug("split="+JSONObject.toJSONString(split));

        String errorcode=null;
        Date date=null;
        if(split.length==3){
            errorcode=split[0];
            String datetime=split[1]+" "+split[2];
            log.debug("errorcode="+errorcode);
            log.debug("datetime="+datetime);
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            try {
                date=sdf.parse(datetime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else if(split.length==4){
            errorcode=split[0];
            String datetime=split[1]+" "+split[2]+" "+split[3];
            log.debug("errorcode="+errorcode);
            log.debug("datetime="+datetime);
            SimpleDateFormat sdf=new SimpleDateFormat("M/d/yyyy H:m:s");
            try {
                date=sdf.parse(datetime);
                if("PM".equalsIgnoreCase(split[3])){
                    log.debug("is pm");
                    Calendar cal=Calendar.getInstance();
                    cal.setTime(date);
                    cal.add(Calendar.HOUR_OF_DAY,12);
                    date=cal.getTime();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(date!=null){
            log.debug(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        }
        log.debug("errorcode="+errorcode+"\tdate="+date);

    }


    @Override
    public String action(StringBuffer sb) {
         StringBuffer msg=new StringBuffer();
        try {
            Date finddate = MXServer.getMXServer().getDate();
            Calendar cal=Calendar.getInstance();
            cal.setTime(finddate);
            String filename="Error"+(cal.get(Calendar.MONTH)+1)+"_"+cal.get(Calendar.YEAR)+".txt";
            cal.add(Calendar.MONTH,-1);
            String lastmonthfilename="Error"+(cal.get(Calendar.MONTH)+1)+"_"+cal.get(Calendar.YEAR)+".txt";
            log.debug("lastmonthfilename="+lastmonthfilename);
            log.debug("filename="+filename);
            executeInterface(lastmonthfilename);
            executeInterface(filename);

        } catch (RemoteException e) {
//            e.printStackTrace();
            log.debug("",e);
            msg.append(" \r\n 执行错误:" + getErrorInfoFromException(e));
        }

        return msg.toString();
    }

    public CrontaskParamInfo[] getParameters() throws MXException, RemoteException {
        try {
            String[] names = new String[]{"Interfaceurl"};
            String[] defs = new String[]{"http://localhost:8080/envirservice/envir/error"};
            String[][] descriptions = new String[][]{{"服务器接口网址","接口地址"}};
            CrontaskParamInfo[] ret = new CrontaskParamInfo[names.length];

            for(int i = 0; i < names.length; ++i) {
                ret[i] = new CrontaskParamInfo();
                ret[i].setName(names[i]);
                ret[i].setDefault(defs[i]);
                ret[i].setDescription(descriptions[i][0], descriptions[i][1]);
            }

            return ret;
        } catch (Exception var6) {
            if (this.getCronTaskLogger().isErrorEnabled()) {
                this.getCronTaskLogger().error(var6);
            }

            return null;
        }
    }
}
