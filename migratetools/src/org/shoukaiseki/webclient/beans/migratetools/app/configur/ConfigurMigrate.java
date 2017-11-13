package org.shoukaiseki.webclient.beans.migratetools.app.configur;

import org.jetbrains.annotations.NotNull;
import org.shoukaiseki.webclient.beans.migratetools.common.AbstractMigrate;
import psdi.webclient.system.beans.DataBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * org.shoukaiseki.webclient.beans.migratetools.app.configur.ConfigurMigrate <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-15 09:48:21<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/

public class ConfigurMigrate extends AbstractMigrate {


    public ConfigurMigrate(@NotNull DataBean tableBean, @NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        super(tableBean, request, response);
    }

    @Override
    public AbstractMigrate download() {
        super.download();
        return this;
    }

}
