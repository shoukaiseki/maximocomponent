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
        MboSetRemote mboSet = getMboSet();
        MboRemote mbo =null;
        if(mboSet.isEmpty()){
            mbo = mboSet.add();
        }else{
            mbo = mboSet.getMbo(0);
        }

        ControlInstance ci = this.creatingEvent.getSourceControlInstance();

        if(ci.getDataBean()==null){
            log.debug("ci.dataBean is null");
        }else{
            DataBean dataBean = ci.getDataBean();
            String completeWhere = dataBean.getMboSet().getCompleteWhere();
            if(mbo!=null){
                mbo.setValue("COMPLETEWHERE",completeWhere,2L);
            }
            log.debug("completeWhere="+completeWhere);
        }
        setCurrentRecordData(mbo);
    }
}
