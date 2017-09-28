package org.shoukaiseki.app.interfaceconfig.task.base

import psdi.mbo.MboRemote
import psdi.mbo.MboSetEnumeration
import psdi.mbo.MboSetRemote
import psdi.security.UserInfo
import psdi.server.MXServer
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

/**
 * org.shoukaiseki.app.interfaceconfig.task.base.ExportUpdateInterfaceTaskExecution <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-22 10:13:02<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
open class ExportUpdateInterfaceTaskExecution @Throws(Exception::class)
constructor(icMbo: MboRemote, userInfo: UserInfo) : AbstractInterfaceTaskExecution(icMbo, userInfo){
    val conn: Connection
    val icstSet: MboSetRemote
    var dbdriver:String
    var dbjdbcurl:String
    var dbuser:String
    var dbpassword:String
    var haserror=false
    var msrTemps= hashMapOf<String,MboSetRemote>()
    var maxmsr:MboSetRemote?=null
    var imst: Statement?=null
    var imrs: ResultSet?=null
    /**
     * 0 表示 insertRow
     * 1 表示 updateRow
     * 其它 表示 insertRow
     */
    var rsInsertOrUpdateFlagMap= hashMapOf<ResultSet,Int>()
    init {
        icstSet=icMbo.getMboSet("INTERFACECONFIGSUBTABLE")
        dbdriver=icMbo.getString("INTERFACECONFIGDATASOURCE.DBDRIVER")
        dbjdbcurl=icMbo.getString("INTERFACECONFIGDATASOURCE.DBJDBCURL")
        dbuser=icMbo.getString("INTERFACECONFIGDATASOURCE.DBUSER")
        dbpassword=icMbo.getString("INTERFACECONFIGDATASOURCE.DBPASSWORD")
        Class.forName(dbdriver)
        conn= DriverManager.getConnection(dbjdbcurl, dbuser, dbpassword)
        conn.autoCommit=false
    }

    /**
     * 执行方案
     */
    override fun execution(){
        logger.debug("${javaClass}.action")
        try {
            when(icMbo.getString("TYPE").toUpperCase()){
                "IMPORT" -> {
                    dataStr= StringBuffer("接口类型 IMPORT 值不适应与该接口")
                }
                "IMPORTMAX_UPDATEIM" -> {
                    dataStr= StringBuffer("接口类型 IMPORTMAX_UPDATEIM 值不适应与该接口")
                }
                "EXPORT" -> {
                    exportExecution()
                }
            }
        }catch (e:Exception){
            haserror=true
            throw e
        }
    }

    /**
     * 导到中间库所执行的方法
     */
    open fun exportExecution() {
        imst =conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
        maxmsr = MXServer.getMXServer().getMboSet(icMbo.getString("MAXIMOOBJECTNAME"), userInfo)
        maxmsr?.where = icMbo.getString("QUERYSQL")
        maxmsr?.reset()
        while (maxmsr?.getMbo(0)!=null )
        {
            val mbo:MboRemote=maxmsr!!.getMbo(0)
            msrTemps.clear()
            imrs= getExportResultSet(imst!!,icMbo.getString("UPDATESQL"),mbo)
            setResultSetValuesFromMboByIcst(mbo,icstSet,imrs!!)
            exportEndAction(mbo,imrs!!)

            msrTemps.forEach {
                it.value.save()
                close(it.value)
            }
            when(rsInsertOrUpdateFlagMap.get(imrs!!)){
                1 -> {
                    imrs?.updateRow()
                }
                0 -> {
                    imrs?.insertRow()
                }
                else ->{
                    imrs?.insertRow()
                }


            }
            conn.commit()
            close(imrs)
            maxmsr?.save()
            maxmsr?.reset()
        }

//        val valueMap= CaseInsensitiveMap<String,Any?>()
//            exportEndAction()
    }


    /**
     * 导出到中间库最后执行的方法
     * @param mbo            查询的当前行mbo
     * @param rs             要修改的当前行记录
     * @param icstSet       接口配置子表信息
     * @param userInfo      userInfo
     */
    @Throws(Exception::class)
    open  fun exportEndAction(mbo:MboRemote, rs:ResultSet, icstSet:MboSetRemote=this.icstSet, userInfo: UserInfo=this.userInfo){
        logger.debug("importEndAction")
    }

    open fun setResultSetValuesFromMboByIcst(mbo: MboRemote, icstSet: MboSetRemote, imrs: ResultSet) {
        val me=MboSetEnumeration(icstSet)
        while (me.hasMoreElements()) {
            val icst = me.nextMbo()
            dataStr= StringBuffer()
            dataStr.append("${mbo.name}:{${mbo.uniqueIDName}:${mbo.uniqueIDValue}}\n")
            dataStr.append("${icMbo.name}:{${icMbo.uniqueIDName}:${icMbo.uniqueIDValue},name:${icMbo.getString("NAME")}},${icst.name}:{${icst.uniqueIDName}:${icst.uniqueIDValue},sn:${icst.getInt("sn")}}")
            logger.debug("sn:${icst.getInt("SN")},isreverse:${icst.getBoolean("ISREVERSE")}")
            if(icst.getBoolean("ISREVERSE")){
                var fromValue:Any
                if(icst.isNull("IMATTNAME")){
                    fromValue=valueFromMaxDefaultValue(icst.getString("MAXDEFAULTVALUE"),icst,mbo)
                }else{
                    fromValue=valueFromResultSet(imrs,icst)
                }
                logger.debug("set.maxattname=${icst.getString("MAXATTNAME")},imatttype=${icst.getString("MAXATTTYPE")},fromValue=${anyToString(fromValue,icst)}")
                dataStr.append("setmbo.${icst.getString("MAXATTNAME")}=${anyToString(fromValue,icst)}")
                dataStr.append(",")
                valueSetToMboRemote(mbo,fromValue,icst,imrs)

            }else{
                var fromValue:Any
                if(icst.isNull("MAXATTNAME")){
                    fromValue=valueFromMaxDefaultValue(icst.getString("DBDEFAULTVALUE"),icst,mbo)
                }else{
                    fromValue=valueFromMboRemote(mbo,icst)
                }
                logger.debug("update.imattname=${icst.getString("IMATTNAME")},imatttype=${icst.getString("IMATTTYPE")},fromValue=${anyToString(fromValue,icst)}")
                dataStr.append("setim.${icst.getString("IMATTNAME")}=${anyToString(fromValue,icst)}")
                dataStr.append(",")
                valueSetToResultSet(imrs,fromValue,icst)
            }
        }

    }

    @Throws(Exception::class)
    open fun getExportResultSet(imst: Statement,sql:String,mbo:MboRemote):ResultSet {
        val rs = imst.executeQuery(sql)
        rs.moveToInsertRow()
        return rs
    }


    override fun close() {
        if(haserror){
            logger.debug("rollback")
            rollback()
        }else{
            logger.debug("commit")
            commit()
        }
        close(imrs)
        close(imst)
        close(conn)
        close(maxmsr)
    }

    open fun rollback(){
        try {
            conn.rollback()
        }catch (e:Exception){
            e.printStackTrace()
        }

        msrTemps.forEach {
            try {
                it.value.rollback()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        try {
            maxmsr?.rollback()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    open fun commit(){
        try {
            conn.commit()
        }catch (e:Exception){
            e.printStackTrace()
        }
        msrTemps.forEach {
            try {
                it.value.commit()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        try {
            maxmsr?.commit()
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}
