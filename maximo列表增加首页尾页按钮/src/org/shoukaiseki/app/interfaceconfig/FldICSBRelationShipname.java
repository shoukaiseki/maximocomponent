package org.shoukaiseki.app.interfaceconfig;

import org.apache.log4j.Logger;
import psdi.mbo.MboValue;
import psdi.mbo.MboValueAdapter;
import psdi.util.MXException;

import java.rmi.RemoteException;

/**
 * org.shoukaiseki.app.interfaceconfig.FldICSBRelationShipname <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-22 12:00:55<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class FldICSBRelationShipname extends MboValueAdapter {

    public FldICSBRelationShipname(MboValue mbv){
        super(mbv);

    }


    @Override
    public void initValue() throws MXException, RemoteException {
        super.initValue();
    }

    @Override
    public void action() throws MXException, RemoteException {
        super.action();
        (( InterfaceconfigsubtableMbo)getMboValue().getMbo() ).initFieldFlag();
    }
}
