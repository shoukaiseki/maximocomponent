package org.shoukaiseki.webclient.beans.migratetools.model

/**
 * org.shoukaiseki.webclient.beans.migratetools.model.TableData <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-15 13:03:33<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
open class TableData (var tableid:String){

    /**
     * 应用名
     */
    var app:String?=null

    /**
     *  对象名
     */
    var name: String? = null

    /**
     * 关系名
     */
    var relationName:String?=null

    /**
     * 对象子集
     */
    var childs= arrayListOf<TableData>()

    /**
     * 子集需要过滤的字段
     */
    var filters= arrayListOf<String>()

    var datas= arrayListOf<LineData>()
}
