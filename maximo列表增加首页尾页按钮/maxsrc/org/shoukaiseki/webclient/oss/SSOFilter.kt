package org.shoukaiseki.webclient.oss

import org.shoukaiseki.webclient.mxpassword.MXCipherX
import org.shoukaiseki.webclient.mxpassword.PropertiesUtil
import org.apache.log4j.Logger
import org.springframework.web.filter.OncePerRequestFilter
import psdi.server.DBManager
import psdi.server.MXServer
import psdi.webclient.system.runtime.WebClientRuntime
import java.io.IOException
import java.rmi.RemoteException
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/** org.shoukaiseki.webclient.oss.SSOFilter <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-19 15:47:17<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
class SSOFilter : OncePerRequestFilter() {

    val prpoMap = HashMap<String, String>()
    var authentication:String=""

    companion object{
        @JvmStatic var log: Logger = Logger.getLogger("org.shoukaiseki.ldap")
    }

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        var errormsg =""
        try {
            val username:String? = request.getParameter("username")
            var password:String?= request.getParameter("password")
            if (!hasComeFromPortal(username, password)) {
                errormsg="用户名和密码不能为空"
                renderError(errormsg,request,response)
                return
            }

            authentication= request.getParameter("authentication")?:"ldap"
            var strs: Array<String?>
            when(authentication){
                "ldap","ldaplogin" -> {
                    if("ldaplogin"==authentication){
                        log.debug("username="+username)
                        log.debug("password="+password)
                        log.debug("sso开始验证")


                        if (prpoMap.isEmpty()) {
                            val baseDN= MXServer.getMXServer().getProperty("sks.ldap.base_dn")
                            val principal = MXServer.getMXServer().getProperty("sks.ldap.security_principal")
                            log.debug("principal=" + principal)
                            val adminPassword = MXServer.getMXServer().getProperty("sks.ldap.security_credentials")
                            log.debug("adminPassword=" + adminPassword)
                            val ldap_url = MXServer.getMXServer().getProperty("sks.ldap.provider_url")
                            log.debug("ldap_url=" + ldap_url)
                            val searchFilter= MXServer.getMXServer().getProperty("sks.ldap.search_filter")
                            val ldap_save_user_class= MXServer.getMXServer().getProperty("sks.ldap.ldap_save_user_class")
                            log.debug("ldap_save_user_class=" + ldap_save_user_class)

                            prpoMap.put("sks.ldap.base_dn", baseDN)
                            prpoMap.put("sks.ldap.provider_url", ldap_url)
                            prpoMap.put("sks.ldap.security_principal", principal)
                            prpoMap.put("sks.ldap.security_credentials", adminPassword)
                            prpoMap.put("sks.ldap.search_filter",searchFilter)
                            prpoMap.put("sks.ldap.ldap_save_user_class",ldap_save_user_class)
                        }

                        val ldapStatus= LDAPConnector(prpoMap).validateUser(username!!, password!!)
                        if(!"SUCCESS".equals(ldapStatus,true)){
                            errormsg="LDAP验证失败:$ldapStatus"
                            renderError(errormsg,request,response)
                            return
                        }
                    }
                    strs = verification(username!!, password!!)
                }
                "maximo" -> {
                    strs = arrayOfNulls<String>(2)
                    strs[0]=username
                    strs[1]=password
                }
                else ->{
                    strs = arrayOfNulls<String>(2)
                    strs[0]=username
                    strs[1]=password
                }
            }


                log.debug("username=${strs[0]},password=${strs[1]} ")

                if (strs[1] != null && strs[0] != null) {
                    val ms = WebClientRuntime.getMXSession(request
                            .session)
                    if (!ms.isConnected) {
                        ms.userName = strs[0]
                        ms.setPassword(strs[1])
                        ms.clientAddr = request.remoteAddr
                        ms.clientHost = request.remoteHost
                        ms.connect()
                    }
                    log.debug(" ================= SSO登录成功：$username")
                    response.sendRedirect( "${request.contextPath}/ui/")
                    return
                } else {
                    log.debug(" SSO验证不通过：用户[$username]")
                    errormsg="maximo中不存在该用户[$username]"

                }
        } catch (e: Exception) {
            e.printStackTrace()
            log.error(" ================= SSO登录失败：",e )
            errormsg="验证出现错误[${e.localizedMessage}]"
        } finally {
        }
        renderError(errormsg,request,response)
    }

    fun renderError(errormsg:String, request: HttpServletRequest, response: HttpServletResponse){
        request.setAttribute("errormsg",errormsg)
        request.setAttribute("authentication",authentication)
//        request.getRequestDispatcher("/webclient/sso/login.jsp").forward(request,response)
        request.getRequestDispatcher("/webclient/login/login.jsp").forward(request,response)
    }

    private fun hasComeFromPortal(username: String?, password: String?): Boolean {
        return username != null && username != "" &&
                password != null && password != ""
    }

    fun verification(username: String, password: String): Array<String?> {
        var strs = arrayOfNulls<String>(2)
        for (i in strs.indices) {
            strs[i] = null
        }
        initOracleConn()
        val st: Statement
        try {
            st = conn!!.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)
            var sql = "select * from maxuser where '${username.toUpperCase()}' =upper(loginid)"
            log.debug(sql)
            var rs = st.executeQuery(sql)
            if (rs.next()) {
                var password = rs.getString("PASSWORD")
                log.debug("max.password=$password")
                val pu = PropertiesUtil()
                val dmx = MXCipherX(false, pu)
                try {
                    password = dmx.decData(password)
                    log.debug("max.decode.password=$password")
                } catch (e: Exception) {
                    e.printStackTrace()
                    log.debug("解码出错",e)
                    throw e
                }

                strs[1] = password
                strs[0] = rs.getString("loginid")
            }
            rs.close()
        } catch (e: SQLException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
            log.error("",e)
        }

        try {
            conn?.close()
        } catch (e: SQLException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
            log.error("",e)
        }



        return strs
    }


    internal var conn: Connection?=null


    fun initOracleConn(): Connection {
        try {
            var dbManager: DBManager? = null
            try {
                if (conn == null || conn!!.isClosed) {
                    dbManager = psdi.server.MXServer.getMXServer().dbManager
                    conn = dbManager!!.sequenceConnection
                }

            } catch (e: SQLException) {
                // TODO Auto-generated catch block
                dbManager = psdi.server.MXServer.getMXServer().dbManager
                if (dbManager != null) {
                    conn = dbManager.sequenceConnection
                }
            }

        } catch (e: RemoteException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return conn!!
    }


}