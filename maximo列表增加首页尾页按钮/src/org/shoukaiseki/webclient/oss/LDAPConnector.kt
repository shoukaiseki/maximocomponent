package org.shoukaiseki.webclient.oss

import org.apache.log4j.Logger
import psdi.mbo.MboRemote
import psdi.security.UserInfo

import java.io.IOException
import java.lang.reflect.Constructor
import java.util.HashMap
import java.util.Hashtable

import javax.naming.Context
import javax.naming.NameClassPair
import javax.naming.NamingEnumeration
import javax.naming.NamingException
import javax.naming.directory.DirContext
import javax.naming.directory.InitialDirContext
import javax.naming.directory.SearchControls
import javax.naming.directory.SearchResult
import javax.naming.ldap.Control
import javax.naming.ldap.InitialLdapContext
import javax.naming.ldap.LdapContext
import javax.naming.ldap.SortControl


/**
 * org.shoukaiseki.webclient.oss.LDAPConnector <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-19 13:35:40<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
open class LDAPConnector(propMap: Map<String, String>) {

    private var ldap_url: String? = null
    private var baseDN: String? = null
    private var principal: String? = null
    private var adminPassword: String? = null
    private var ldap_save_user_class: String? = null
    private lateinit var search_filter:String
    private val env = Hashtable<String, String>()
    private val sortConnCtls = arrayOfNulls<SortControl>(1)

    init {
        try {
            sortConnCtls[0] = SortControl("sAMAccountName", Control.CRITICAL)
        } catch (ex: IOException) {
        }

    }

    init {
        try {
            ldap_url = propMap["sks.ldap.provider_url"]
            baseDN = propMap["sks.ldap.base_dn"]
            principal = propMap["sks.ldap.security_principal"]
            adminPassword = propMap["sks.ldap.security_credentials"]
            search_filter = propMap["sks.ldap.search_filter"]!!
            ldap_save_user_class=propMap["sks.ldap.ldap_save_user_class"]

            // set up environment for creating initial context
            env.put(Context.SECURITY_AUTHENTICATION, "simple")
            env.put(Context.PROVIDER_URL, ldap_url!!)
            env.put(Context.SECURITY_PRINCIPAL, principal!!)
            env.put(Context.SECURITY_CREDENTIALS, adminPassword!!)
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory")

            env.put("java.naming.batchsize", "50")
            env.put("com.sun.jndi.ldap.connect.timeout", "3000")
            env.put("com.sun.jndi.ldap.connect.pool", "true")
            // the following pool parameters doesn't work
            // must setup as java init parameters
            env.put("com.sun.jndi.ldap.connect.pool.maxsize", "3")
            env.put("com.sun.jndi.ldap.connect.pool.prefsize", "1")
            env.put("com.sun.jndi.ldap.connect.pool.timeout", "300000")
            env.put("com.sun.jndi.ldap.connect.pool.initsize", "1")
            env.put("com.sun.jndi.ldap.connect.pool.authentication", "simple")

        } catch (e: Exception) {
            // ignore error
            e.printStackTrace()
        }

    }

    fun validateUser(username: String, password: String): String {
        var dirContext: LdapContext? = null
        val logid = System.currentTimeMillis()
//        logger.debug("logid=$logid:{username=$username,password=$password}")
        logger.debug("logid=$logid:{username=$username}")
        try {
            // create initial context
            dirContext = InitialLdapContext(env, sortConnCtls)
            logger.debug("logid=$logid:{管理员账户验证成功}")
            //            dirContext = new InitialLdapContext(env, null);
            dirContext.requestControls = sortConnCtls
            val controls = SearchControls()
            controls.searchScope = SearchControls.SUBTREE_SCOPE
            var filter = search_filter.replace("\${user}",username)
            logger.debug("logid=$logid:{filter=$filter}")
            val answer = dirContext.search(baseDN, filter, controls)
            logger.debug("logid=$logid:{进行用户DN查询用于LDAP验证}")
            var userDN: String? = null
            while (answer.hasMoreElements()) {
                val obj = answer.next()
                userDN = "${obj.name},$baseDN"
                logger.debug("logid=$logid:{member=${obj.attributes.get("member")},memberOf=${obj.attributes.get("memberOf")}}")

                logger.debug("logid=$logid:{ldap_save_user_class=$ldap_save_user_class}")
                ldap_save_user_class?.let {
                    logger.debug("logid=$logid:{ldap_save_user_class let}")
                    if( it.isNotEmpty()){
                        logger.debug("logid=$logid:{ldap_save_user_class it.isNotEmpty}")
                        try{
                            val clazz=Class.forName(ldap_save_user_class)
                            val dc: Constructor<LDAPSaveUser> = clazz.getDeclaredConstructor(SearchResult::class.java, java.lang.Long::class.java) as Constructor<LDAPSaveUser>
                            dc.isAccessible=true
                            var iteTemp=dc.newInstance(obj,logid)
                            logger.debug("logid=$logid:{iteTemp ${iteTemp.javaClass}}")
                            iteTemp.execution()
                        }catch (e:Exception){
                            logger.debug("logid=$logid",e)
                            if(e is LDAPException){
                                closeDirContext(dirContext)
                                return e.message!!
                            }

                        }
                    }
                }

            }
            logger.debug("logid=$logid:{userDN=$userDN}")
            if(userDN==null){
                closeDirContext(dirContext)
               return "LDAP中用户不存在或者没有加入Gas_App组"
            }
            // set up environment for creating initial context
            val env = Hashtable<String, String>()
            env.put(Context.PROVIDER_URL, ldap_url!!)
            env.put(Context.SECURITY_PRINCIPAL, userDN )
            env.put(Context.SECURITY_CREDENTIALS, password)
            env.put(Context.SECURITY_AUTHENTICATION, "simple")
            //            env.put("com.sun.jndi.ldap.connect.timeout", "1000");
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory")

            // create initial context
            val context = InitialDirContext(env)
            closeDirContext(context)
            return "SUCCESS"
        } catch (e: NamingException) {
            // ignore error
//            e.printStackTrace()
            logger.debug("",e)
        } finally {
            closeDirContext(dirContext)

        }
        return "LDAP密码验证错误"
    }

    open fun closeDirContext(ctx:Context?){
        if (ctx != null) {
            try {
                ctx.close()
            } catch (e: NamingException) {
//                e.printStackTrace()
                logger.debug("closeDirContext.error",e)
            }

        }
    }

    companion object {
        @JvmStatic var logger: Logger = Logger.getLogger("org.shoukaiseki.ldap")

        @JvmStatic
        fun main(args: Array<String>) {
            val prpoMap = HashMap<String, String>()
            prpoMap.put("sks.ldap.base_dn", "DC=slbcopower,DC=com")
            prpoMap.put("sks.ldap.provider_url", "ldap://10.2.6.11:389")
            //        prpoMap.put("sks.ldap.security_principal","yongbowang@slbcopower.com");
            prpoMap.put("sks.ldap.security_principal", "CN=wangyongbo,OU=信息应用,OU=SCPUSER,OU=SCP,DC=slbcopower,DC=com")
            prpoMap.put("sks.ldap.security_credentials", "slbSCP0000")
            prpoMap.put("sks.ldap.ldap_save_user_class","com.scp.mam.webclient.sso.CopowerLDAPSaveUser")
            var filter = "(&(objectClass=user)(sAMAccountName=\${user})(memberOf=CN=Gas_App,OU=采气工程部,OU=SCPUSER,OU=SCP,DC=slbcopower,DC=com))"
            prpoMap.put("sks.ldap.search_filter",filter)
            val ldapConnector = LDAPConnector(prpoMap)
            var reStr = ldapConnector.validateUser("liangli", "slbSCP0000")
            println(reStr)
//            reStr = ldapConnector.validateUser("langw", "slbSCP0000")
//            println(reStr)
        }
    }
}
