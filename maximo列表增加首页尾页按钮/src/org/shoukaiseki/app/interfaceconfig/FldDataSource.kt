package org.shoukaiseki.app.interfaceconfig

import psdi.mbo.MAXTableDomain
import psdi.mbo.MboValue

/**
 * org.shoukaiseki.app.interfaceconfig.FldDataSource <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-07 14:22:09<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
open class FldDataSource(mbv: MboValue) : MAXTableDomain(mbv) {
    init {
        val targetKeys = arrayOf("DATASOURCE")
        val sourceKeys = arrayOf("DATASOURCE")
        setLookupKeyMapInOrder(targetKeys, sourceKeys)
        setRelationship("INTERFACECONFIGDATASOURCE", "1=1")
    }
    /**
    init {
        val targetKeys = arrayOf("DATASOURCE")
        val sourceKeys = arrayOf("DISPLAYNAME")
        setLookupKeyMapInOrder(targetKeys, sourceKeys)
        setRelationship("PERSON", null)
    }
     **/

}
