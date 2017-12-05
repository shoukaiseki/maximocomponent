package org.shoukaiseki.webclient.beans.subtabuploadattachments;

import org.apache.log4j.Logger;
import psdi.util.MXException;
import psdi.webclient.system.beans.DataBean;

/**
 * org.shoukaiseki.webclient.beans.subtabuploadattachments.SubtabDataBean <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-12-05 15:22:37<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class SubtabDataBean extends DataBean{
    public static final Logger logger=Logger.getLogger("org.shoukaiseki");


    public SubtabDataBean() {
        super();
    }


    public void subtabuploadattachments() throws MXException {
            app.getAppBean().save();

    }


}
