package org.shoukaiseki.webclient.beans.migratetools.model

import com.alibaba.fastjson.JSONObject
import kotlin.collections.ArrayList

/**
 * org.shoukaiseki.webclient.beans.migratetools.model.FilterModel <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-15 13:14:40<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */

class FilterModel (name:String?=null,vararg filters: String= arrayOf()){

    /**
     * 应用名
     */
    var app:String?=null

    /**
     * 主表中为对象名,子表中为关系名
     */
    var name:String?=null

    private var filters:ArrayList<String> = arrayListOf()

    private var includes:ArrayList<String> = arrayListOf()


    init {
        this.name=name
        for (filter in filters){
            this.filters.add(filter)
        }
    }

    open fun getIncludes():ArrayList<String>{
        return includes
    }

    open fun getFilters():ArrayList<String>{
        return filters
    }


    open fun addIncludes(vararg names:String){
        for (name in names) {
            includes.add(name.toUpperCase())
        }
    }

    open fun addFilters(vararg names:String){
        for (name in names) {
            filters.add(name.toUpperCase())
        }
    }

    private var childs: ArrayList<FilterModel> = ArrayList()


    open fun getChilds():ArrayList<FilterModel>{
        return childs
    }

    open fun addChild(relationshipname:String,vararg filter:String){
        childs.add(FilterModel(relationshipname,*filter))
    }

    open fun addChilds(vararg names:FilterModel){
        for (name in names) {
           childs.add(name)
        }
        println("childs=${JSONObject.toJSONString(childs)}")
    }
}

