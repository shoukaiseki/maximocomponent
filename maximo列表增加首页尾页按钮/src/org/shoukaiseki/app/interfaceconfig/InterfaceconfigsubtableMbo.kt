package org.shoukaiseki.app.interfaceconfig

import org.apache.log4j.Logger
import psdi.mbo.MboConstants
import psdi.mbo.MboSet
import psdi.mbo.custapp.CustomMbo
import psdi.util.MXException

import java.rmi.RemoteException

/**
 * org.shoukaiseki.app.interfaceconfig.InterfaceconfigsubtableMbo <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-22 11:35:11<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
class InterfaceconfigsubtableMbo @Throws(RemoteException::class)
constructor(ms: MboSet) : CustomMbo(ms) {

    companion object {
        @JvmStatic val logger= Logger.getLogger("org.shoukaiseki")
    }

    @Throws(MXException::class)
    override fun init() {
        super.init()
        initFieldFlag()
    }

    override fun initFieldFlagsOnMbo(attrName: String?) {
        super.initFieldFlagsOnMbo(attrName)
//        logger.debug("initFieldFlagsOnMbo($attrName)")
    }


    fun initFieldFlag(){
        try {
            thisMboSet?.relationName?.let {
                when(it){
                    "INTERFACECONFIGSUBTABLE" -> {
                        owner?.name?.let {
                            if("INTERFACECONFIG".equals(it)){
                                val atts= arrayOf("MAXATTNAME")
                                if(!isNull("ISREVERSE")&&getBoolean("ISREVERSE")){
                                    setFieldFlag(atts,MboConstants.READONLY,false)
                                    setFieldFlag(arrayOf("RELATIONSHIPNAME"),MboConstants.READONLY,false)
                                    return
                                }
                                if(isNull("RELATIONSHIPNAME")){
                                    setFieldFlag(atts,MboConstants.READONLY,false)
                                }else{
                                    setFieldFlag(atts,MboConstants.READONLY,true)
                                    if(getMboSet("RELATIONSHIPSUBTABLE").isEmpty){
                                        setFieldFlag(arrayOf("RELATIONSHIPNAME"),MboConstants.READONLY,false)
                                    }else{
                                        setFieldFlag(arrayOf("RELATIONSHIPNAME"),MboConstants.READONLY,true)
                                    }

                                }
                            }
                        }

                    }
                    "RELATIONSHIPSUBTABLE" -> {

                    }
                    else -> {
                    }
                }
            }
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

    }
}
