package org.shoukaiseki.app.interfaceconfig.task.base

import org.apache.log4j.Logger
import psdi.mbo.MboRemote
import psdi.mbo.MboSetRemote
import psdi.security.UserInfo
import psdi.server.MXServer
import java.io.PrintWriter
import java.io.StringWriter
import java.math.BigDecimal
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

/**
 * org.shoukaiseki.app.interfaceconfig.task.base.AbstractInterfaceTaskExecution <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-14 15:22:02<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */

open class AbstractInterfaceTaskExecution @Throws(Exception::class) constructor(var icMbo: MboRemote, var userInfo: UserInfo){
    var dataStr=StringBuffer()

    companion object {
        @JvmStatic val logger= Logger.getLogger("org.shoukaiseki.InterfaceTask")
        @JvmStatic val sdf= SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    }

    init {

    }

    open fun close(){

    }

    open fun execution(){
        dataStr= StringBuffer("所继承的类中的execution方法需要重写")
    }


    open fun close(st: Statement?){
        try {
            st?.close()
        } catch (e:Exception){
            e.printStackTrace()
        }
    }

    open fun close(rs: ResultSet?){
        try {
            rs?.close()
        } catch (e:Exception){
            e.printStackTrace()
        }
    }

    open fun close(conn: Connection?){
        try {
            conn?.close()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    open fun close(msr:MboSetRemote?){
        msr?.let {
            try {
                it.close()
            }catch (e:Exception){

            }
        }
    }


    open fun errorLog(name:String,t:Throwable,userInfo:UserInfo=this.userInfo){
        val result = StringWriter()
        val printWriter = PrintWriter(result)
        t.printStackTrace(printWriter)
        errorLog(name,t.localizedMessage,result.toString(),userInfo)
    }

    open fun errorLog(name:String,logtitle:String,runtimeerror:String,userInfo:UserInfo=this.userInfo){
        var iclogSet: MboSetRemote?=null
        try {
            iclogSet = MXServer.getMXServer().getMboSet("INTERFACECONFIGLOG", userInfo)
            iclogSet.where="1=2"
            iclogSet.reset()
            val iclog = iclogSet.add()
            iclog.setValue("NAME",name,11L)
            logger.debug("dataStr=${dataStr.toString()}")
            iclog.setValue("RUNTIMEERROR",dataStr.toString(),11L)
            iclog.setValue("DESCRIPTION_LONGDESCRIPTION",runtimeerror,11L)
            iclog.setValue("LOGTITLE",logtitle,11L)
            iclogSet.save()
        }catch (e:Exception){
            e.printStackTrace()
            try {
                iclogSet?.rollback()
            }catch (e1:Exception){
            }
        }finally {
            close(iclogSet)
        }
    }


    /**
     * 通过结果集,获取字符串,获取后如果不为空则进行前尾空格删除
     * @param   columnLabel    结果集的字段名
     * @param   rs              结果集
     */
    open fun getStringFromResultSet(columnLabel:String ,rs:ResultSet):String{
        val str:String? = rs.getString(columnLabel)
        if(str!=null){
            return str.trim()
        }
        return ""

    }

    /**
     * 获取max默认值
     * @param  dv           默认值的字符串
     * @param icst          接口配置子表行信息
     * @param mbo           要修改的当前行mbo
     * @param userInfo      userInfo
     */
    open fun valueFromMaxDefaultValue(dv:String, icst:MboRemote, mbo:MboRemote, userInfo: UserInfo=this.userInfo):Any{
        val reVal=when(dv){
            "&PERSONID&" -> userInfo.personId as Any
            "&USERNAME&" -> userInfo.userName as Any
            "&INSERTSITE&" -> userInfo.insertSite as Any
            "&SITEID&" -> icst.thisMboSet.profile.defaultSite as Any
            "&ORGID&" -> icst.thisMboSet.profile.defaultOrg as Any
            "&LOGINID&","&USERID&" -> userInfo.loginID as Any
            "&SYSDATE&","&DATETIME&","&DATE&" -> MXServer.getMXServer().date as Any
            "&YES&" -> true
            "&NO&" -> false
            else -> dv
        }
        return reVal
    }

    /**
     * 根据 mbo 的 MAXATTTYPE 属性获取相应类型的值
     * @param  mbo       要处理的行mbo
     * @param  icst     要获取的接口配置子表行mbo
     */
    open fun valueFromMboRemote(mbo:MboRemote,icst:MboRemote):Any{
        val maxatttype =icst.getString("MAXATTTYPE")
        val maxattname = if(icst.isNull("RELATIONSHIPNAME")){
            icst.getString("MAXATTNAME")
        }else{
            "${icst.getString("RELATIONSHIPNAME")}.${icst.getString("MAXATTNAME")}"
        }
        if(mbo.isNull(maxattname)){
            return ""
        }
        when(maxatttype){
            "String" -> return mbo.getString(maxattname)
            "Long" -> return mbo.getLong(maxattname)
            "Double" -> return mbo.getDouble(maxattname)
            "Date" -> return mbo.getDate(maxattname)
            else -> return ""
        }
    }

    /**
     * 根据 icst 的 IMATTTYPE 属性获取相应类型的值
     * @param  rs       查询的数据行信息
     * @param  icst     要获取的接口配置子表行mbo
     */
    open fun valueFromResultSet(rs:ResultSet,icst:MboRemote):Any{
        val imatttype=icst.getString("IMATTTYPE")
        val imattname=icst.getString("IMATTNAME")
        if(rs.getObject(imattname)==null){
            return ""
        }
        when(imatttype){
            "String" -> return getStringFromResultSet(imattname,rs)
            "Long" -> return rs.getLong(imattname)
            "Double" -> return rs.getDouble(imattname)
            "Date" -> return Date(rs.getTimestamp(imattname).time)
            else -> return ""
        }
    }

    /**
     *  设置mbo字段值
     * @param mbo            要修改的当前行mbo
     * @param icst          接口配置子表行信息
     */
    open fun valueSetToMboRemote(mbo:MboRemote,fromValue:Any,icst:MboRemote,imrs:ResultSet){
        var mboTemp=mbo
        val maxatttype=icst.getString("MAXATTTYPE")
        val maxattname=icst.getString("MAXATTNAME")
        val accessmodifier=icst.getLong("ACCESSMODIFIER")
        logger.debug("fromValue=$fromValue,isBlank=${fromValue.toString().isBlank()} ")
        if(fromValue.toString().isBlank()){
            mboTemp.setValueNull(maxattname,accessmodifier)
            return
        }

        if("String".equals(maxatttype,ignoreCase = true)){
            val value = anyToString(fromValue,icst)
            mboTemp.setValue(maxattname,value,accessmodifier)
        }else if("Long".equals(maxatttype,ignoreCase = true)){
            val value = anyToLong(fromValue,icst)
            mboTemp.setValue(maxattname,value,accessmodifier)
        } else if("Date".equals(maxatttype,ignoreCase = true)){
            val value = anyToDate(fromValue,icst)
            mboTemp.setValue(maxattname,value,accessmodifier)
        } else if("Double".equals(maxatttype,ignoreCase = true)){
            val value = anyToDouble(fromValue,icst)
            mboTemp.setValue(maxattname,value,accessmodifier)
        } else if("Integer".equals(maxatttype,ignoreCase = true)){
            val value = anyToInteger(fromValue,icst)
            mboTemp.setValue(maxattname,value,accessmodifier)
        }else if("Boolean".equals(maxatttype,ignoreCase=true)){
            val value = anyToBoolean(fromValue,icst)
            mboTemp.setValue(maxattname,value,accessmodifier)
        }
    }


    /**
     *  设置 ResultSet 字段值
     * @param rs            要修改的当前行 rs
     * @param icst          接口配置子表行信息
     */
    open fun valueSetToResultSet(rs: ResultSet, fromValue: Any, icst: MboRemote){
        val imatttype=icst.getString("IMATTTYPE")
        val imattname=icst.getString("IMATTNAME")
        if(fromValue.toString().isBlank()){
            rs.updateNull(imattname)
            return
        }
        when(imatttype){
            "String" -> {
                val value = anyToString(fromValue,icst)
                rs.updateString(imattname,value)
            }
            "Double" -> {
                val value = anyToDouble(fromValue,icst)
                rs.updateDouble(imattname,value)
            }
            "BigDecimal" -> {
                val value = anyToBigDecimal(fromValue,icst)
                rs.updateBigDecimal(imattname,value)
            }
            "Long" -> {
                val value = anyToLong(fromValue,icst)
                rs.updateLong(imattname,value)
            }
            "Integer" -> {
                val value = anyToInteger(fromValue,icst)
                rs.updateInt(imattname,value)
            }
            "Date" -> {
                val value = anyToDate(fromValue,icst)
                val value2= Timestamp(value.time)
                rs.updateTimestamp(imattname,value2)
            }
            "Boolean" -> {
                val value = anyToBoolean(fromValue,icst)
                rs.updateInt(imattname,anyToInteger(value,null))
            }

        }

    }

    /**
     * 任意类型转换为 Integer
     * @param value
     * @param icst          接口配置信息,可以获取字段名
     */
    open fun anyToInteger(value:Any,icst: MboRemote?): Int {
        return when(value){
            is Long -> value.toInt()
            is Double -> value.toInt()
            is String -> value.toInt()
            is Boolean -> if(value)1 else 0
            else -> value.toString().toInt()

        }
    }

    /**
     * 任意类型转换为 BigDecimal
     * @param value
     * @param icst          接口配置信息,可以获取字段名
     */
    open fun anyToBigDecimal(value:Any,icst: MboRemote?): BigDecimal {
        return BigDecimal(anyToDouble(value,icst))
    }

    /**
     * 任意类型转换为 Double
     * @param value
     * @param icst          接口配置信息,可以获取字段名
     */
    open fun anyToDouble(value:Any,icst: MboRemote?):Double{
        return when(value){
            is Long -> value.toDouble()
            is Int -> value.toDouble()
            is String -> value.toDouble()
            else -> value.toString().toDouble()

        }
    }

    /**
     * 任意类型转换为 Boolean
     * @param value
     * @param icst          接口配置信息,可以获取字段名
     */
    open fun anyToBoolean(value:Any,icst: MboRemote?):Boolean{

        when(value){
            is Long -> {
                return value == 0
            }
            is String -> {
                when(value.toUpperCase()){
                    "1","TRUE","YES" -> return true
                    else -> return false
                }
            }
            else -> {
                return value.toString().toInt()==0
            }
        }
    }

    /**
     * 任意类型转换为 Date
     * @param value
     * @param icst          接口配置信息,可以获取字段名
     */
    open fun anyToDate(value:Any,icst: MboRemote?):Date{
        return when(value){
            is Long -> Date(value.toLong())
            is Double -> Date(value.toLong())
            is Date -> value
            is String ->sdf.parse(value.toString())
            else -> sdf.parse(value.toString())
        }
    }

    /**
     * 任意类型转换为string
     * @param value
     * @param icst          接口配置信息,可以获取字段名
     */
    open fun anyToString(value:Any,icst: MboRemote?):String{
        return when(value){
            is Long -> value.toString()
            is Date -> sdf.format(value)
            is Boolean -> if(value){"1"}else{"0"}
            else -> value.toString()
        }
    }

    /**
     * 任意类型转换为long
     * @param value
     * @param icst          接口配置信息,可以获取字段名
     */
    open fun anyToLong(value:Any,icst: MboRemote?):Long{
        if(value is Long){
            return value
        }
        return value.toString().toLong()
    }


}
