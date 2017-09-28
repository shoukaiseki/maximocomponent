package org.shoukaiseki.webclient.utils

import org.apache.log4j.Logger
import psdi.webclient.system.beans.DataBean

/**
 * org.shoukaiseki.webclient.utils.DataBeanUtils <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-28 13:39:16<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
open class DataBeanUtils {


    companion object {
        @JvmStatic var log: Logger = Logger.getLogger("org.shoukaiseki.utils")
        /**
         * 跳转到指定行
         * @param rownum
         * @param dataBean
         * @throws Exception
         */
        @Throws(Exception::class)
        @JvmStatic fun movetorow(inrownum: Int, dataBean: DataBean) {
            var pageEndRowTemp = -1
            var rownum=inrownum
            if (dataBean.mboSet.count() <rownum ) {
                rownum=dataBean.mboSet.count()
            }
            while (dataBean.pageEndRow < rownum || dataBean.pageEndRow - dataBean.pageRowCount > rownum) {
                if (dataBean.pageEndRow < rownum) {
                    dataBean.scrollnext()
                } else {
                    dataBean.scrollprev()
                }
                if (pageEndRowTemp == dataBean.pageEndRow) {
                    break
                }
                pageEndRowTemp = dataBean.pageEndRow
                log.debug("rownum=" + rownum + ",PageEndRow=" + dataBean.pageEndRow + ",pageStartRow=" + (dataBean.pageEndRow - dataBean.pageRowCount))
            }
            dataBean.currentRow = rownum - 1
        }
    }

}

