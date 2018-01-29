package org.shoukaiseki.webclient.beans.showcompletewhere;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.util.MXException;
import psdi.webclient.system.beans.DataBean;
import psdi.webclient.system.controller.ControlInstance;

import java.rmi.RemoteException;

/**
 * org.shoukaiseki.webclient.beans.showcompletewhere.ShowCompleteWhereDataBean <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2018-01-22 15:57:16<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class ShowCompleteWhereDataBean extends DataBean{

    Logger log = Logger.getLogger("org.shoukaiseki.showcompletewhere");

    @Override
    protected void initialize() throws MXException, RemoteException {
        super.initialize();
        log.setLevel(Level.DEBUG);
       log.debug("ShowCompleteWhereDataBean.initialize");
        MboRemote mbo =null;
        if(getMboSet().isEmpty()){
            mbo = getMboSet().add();
        }else{
            mbo = getMboSet().getMbo(0);
        }


        ControlInstance ci = this.creatingEvent.getSourceControlInstance();

        if(ci.getDataBean()==null){
            log.debug("ci.dataBean is null");
        }else{
            DataBean dataBean = ci.getDataBean();
            StringBuffer sb = new StringBuffer();
            sb.append("mboname=").append(dataBean.getMboSet().getMboSetInfo().getObjectName());
            sb.append("\n");
            sb.append("completeWhere=").append(dataBean.getMboSet().getCompleteWhere());
            sb.append("\n");
            sb.append("\n");
            sb.append("\n");

            if(!isBlank(dataBean.getAppWhere())){
                sb.append("appwhere=").append(dataBean.getAppWhere());
                sb.append("\n");
            }

            if(!isBlank(dataBean.getUserWhere())){
                sb.append("userwhere=").append(dataBean.getUserWhere());
                sb.append("\n");
            }

            if(!isBlank(dataBean.getMboSet().getSelectionWhere())){
                sb.append("mboset.selectionwhere=").append(dataBean.getMboSet().getSelectionWhere());
                sb.append("\n");
            }

            if(!isBlank(dataBean.getMboSet().getWhere())){
                sb.append("mboset.where=").append(dataBean.getMboSet().getWhere());
                sb.append("\n");
            }

            MboSetRemote mboSet = dataBean.getMboSet();
            MboRemote ownerMbo = mboSet.getOwner();
            if(dataBean.getMboSet().getOwner()!=null){
                log.debug("ownermbo="+ownerMbo.getName());
                sb.append("ownermbo=").append(ownerMbo.getName());
                sb.append("\n");
                String relationName = mboSet.getRelationName();
                log.debug("relationname="+relationName);
                sb.append("relationname=").append(relationName);
                sb.append("\n");
                log.debug("sql="+mbo.getMboSet("","",""));
                if(ownerMbo.getThisMboSet().getMboSetInfo().getRelationInfo(relationName)!=null){
                    sb.append("relationinfo=").append(ownerMbo.getThisMboSet().getMboSetInfo().getRelationInfo(relationName).getSqlExpr());
                    sb.append("\n");
                }else{
                    sb.append("relationship=").append(mboSet.getRelationship());
                    sb.append("\n");
                }

            }
            if(mbo!=null){
                mbo.setValue("COMPLETEWHERE",sb.toString(),2L);
            }
            log.debug(sb.toString());
        }
        setCurrentRecordData(mbo);
    }


    /**
     * 是否为空
     * @param str
     * @return
     */
    public boolean isBlank(String str){
        if(str==null||str.isEmpty()){
            return true;
        }
        if(str.trim().isEmpty()){
            return true;
        }
        return false;
    }

}
