package org.shoukaiseki.app.interfaceconfig

import org.apache.log4j.Logger
import psdi.mbo.*
import psdi.util.MXException
import java.rmi.RemoteException
import java.util.HashSet

/** org.shoukaiseki.app.interfaceconfig.InterfaceconfigMbo <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-07 11:58:28<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/

open class InterfaceconfigMbo @Throws (RemoteException::class) constructor(ms:MboSet):Mbo(ms){

    /**
     *  需要过滤的字段map
     */
    private var skipFieldCopy: MutableSet<String> = HashSet()

    /**
     *  确保 loadSkipFieldCopyHashSet 方法只会执行一次
     */
    private var isHashSetLoaded = false

    companion object {
        @JvmStatic val logger= Logger.getLogger(InterfaceconfigMbo::class.java)
    }

    init {
        logger.debug("InterfaceconfigMbo()")

    }

    override fun init() {
        super.init()
        if(!isNull("NAME")){
            if(getMboSet("INTERFACECONFIGSUBTABLE").count()>0){
                setFieldFlag("NAME",MboConstants.READONLY,true)
            }
        }
        setFieldFlag("NAME",MboConstants.REQUIRED,true)
    }

    override fun duplicate(): MboRemote {
        println("duplicate.isHashSetLoaded=${isHashSetLoaded}")
        if (!isHashSetLoaded) {
            loadSkipFieldCopyHashSet()
        }
        return this.copy()
    }

    @Throws(RemoteException::class, MXException::class)
    override fun skipCopyField(mvi: MboValueInfo): Boolean {
        mboLogger.debug("skipCopyField")
        println("skipCopyField.name=${mvi.name}")
        if (skipFieldCopy.contains(mvi.name)) {
            return true
        }
        when(mvi.attributeName.toUpperCase()){
           "SITEID","ORGID" -> return  false
        }
        return  mvi.isKey
    }

    fun loadSkipFieldCopyHashSet(){
        isHashSetLoaded = true
        skipFieldCopy.add("NAME")
        skipFieldCopy.add("ENABLED")
        skipFieldCopy.add(uniqueIDName)
    }
}
