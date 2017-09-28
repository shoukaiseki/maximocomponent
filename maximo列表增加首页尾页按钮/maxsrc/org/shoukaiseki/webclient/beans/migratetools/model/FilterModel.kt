package org.shoukaiseki.webclient.beans.migratetools.model

import com.alibaba.fastjson.JSONObject
import java.util.*
import kotlin.collections.ArrayList

/**
 * org.shoukaiseki.webclient.beans.migratetools.model.FilterModel <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-15 13:14:40<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */

open class FilterModel (name:String?=null,vararg filters: String= arrayOf()){

    /**
     * tabledataid,位于标识所属明确的子表所使用的过滤器<br>
     * 该id不可重复,否则会造成表过滤混乱
     */
    private var tableid:String

    open fun getTableid():String{
        return this.tableid
    }

    open fun setTableid(id:String):FilterModel{
        this.tableid=id
        return this
    }

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
        this.tableid= UUID.randomUUID().toString()
        for (filter in filters){
            if(filter.isNotBlank()&&filter.isNotEmpty()){
                this.filters.add(filter.toUpperCase())
            }
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
            if(name.isNotBlank()&&name.isNotEmpty()){
                this.filters.add(name.toUpperCase())
            }
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
//        println("childs=${JSONObject.toJSONString(childs)}")
    }
}

