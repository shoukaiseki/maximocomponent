package org.shoukaiseki.webclient.beans.migratetools.model

import com.alibaba.fastjson.annotation.JSONField

/**
 * key 不区分大小写
 * org.shoukaiseki.webclient.beans.migratetools.model.LineData <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-18 09:47:36<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
open class LineData(tabledata:TableData?=null){
    @JSONField(serialize=false)
    var tabledata:TableData?


    var data:HashMap<String,Any?>
    var childs:ArrayList<TableData>

    init {
        data= hashMapOf()
        childs= arrayListOf()
        this.tabledata=tabledata
    }

    /**
     * 对象子集
     */
    @JSONField(serialize=true)


    fun put(key: String, value: Any?): Any? {
        return data.put(key.toUpperCase(), value)
    }

    fun get(key: String): Any? {
        return data.get(key.toUpperCase())
    }

}
