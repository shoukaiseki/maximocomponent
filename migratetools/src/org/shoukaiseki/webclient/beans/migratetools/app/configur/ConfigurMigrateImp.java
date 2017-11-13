package org.shoukaiseki.webclient.beans.migratetools.app.configur;

import org.shoukaiseki.webclient.beans.migratetools.ImportMigrateToolsDataBean;
import org.shoukaiseki.webclient.beans.migratetools.common.AbstractMigrateImp;
import psdi.webclient.system.beans.DataBean;

/**
 * org.shoukaiseki.webclient.beans.migratetools.app.configur.ConfigurMigrateImp <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-10-11 14:02:05<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class ConfigurMigrateImp extends AbstractMigrateImp {

    public ConfigurMigrateImp(DataBean dataBean,String jsonStr){
        super(dataBean,jsonStr);
    }


    @Override
    public void execute() {
        getLogger().debug("jsonStr="+getJsonStr());
        super.execute();

    }
}
