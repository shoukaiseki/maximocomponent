package org.shoukaiseki.webclient.oss

import org.apache.log4j.Logger

import javax.naming.directory.Attributes
import javax.naming.directory.SearchResult

/**
 * org.shoukaiseki.webclient.oss.LLDAPSaveUser<br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-19 17:14:292017-09-19グ17:14:39shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
open class LDAPSaveUser(protected var sr: SearchResult, protected var logid: Long?) {
    protected var attributes: Attributes

    init {
        this.attributes = sr.attributes
    }

    @Throws(LDAPException::class)
    open fun execution(){}

    fun getStringByAttr(attrID: String): String {
        return if (attributes.get(attrID) == null||attributes.get(attrID).get()==null) {
            ""
        } else attributes.get(attrID).get().toString()
    }

    companion object {
        @JvmStatic val logger = Logger.getLogger("org.shoukaiseki.ldap")
    }

    fun debug(str:Any){
        logger.debug("logid=$logid:{$str}")
    }

}
