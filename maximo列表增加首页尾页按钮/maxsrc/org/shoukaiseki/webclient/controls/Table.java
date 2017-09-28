package org.shoukaiseki.webclient.controls;

import org.shoukaiseki.webclient.utils.DataBeanUtils;
import psdi.util.MXException;
import psdi.webclient.system.beans.DataBean;

import java.rmi.RemoteException;

/**
 * org.shoukaiseki.webclient.controls.Table <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-28 10:28:48<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class Table extends psdi.webclient.controls.Table{
    DataBean tableBean = null;

    public Table(){
        super();

    }

    @Override
    public int nextpage() throws MXException {
        return super.nextpage();
    }

    public void initialize() {
        super.initialize();

    }


    public int startpage() throws Exception {
        this.forceFocusRow(-1);
        this.tableBean = this.getDataBean();
//        this.tableBean.scrollnext();
//        this.setCurrentRow(this.tableBean.getTableOffset());
        int count = tableBean.getMboSet().count();
        if(count>=1){
            DataBeanUtils.movetorow(1,tableBean);
        }
        return 1;
    }

    public int endpage() throws Exception {
        this.forceFocusRow(-1);
        this.tableBean = this.getDataBean();
//        this.tableBean.scrollnext();
//        this.setCurrentRow(this.tableBean.getTableOffset());
        int count = tableBean.getMboSet().count();
        if(count>=1) {
            DataBeanUtils.movetorow(count,tableBean);
        }
        return 1;
    }


}
