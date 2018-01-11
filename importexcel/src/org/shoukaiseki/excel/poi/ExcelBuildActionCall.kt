package org.shoukaiseki.excel.poi

import psdi.util.MXException
import java.rmi.RemoteException

/** org.shoukaiseki.excel.poi.ExcelBuildActionCall <br>
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-22 09:15:31<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/

interface ExcelBuildActionCall {

    /**
     * excel文件名,优先被执行
     */
    fun setImportFilename(name:String)

    /**
     *  初始化excel表格数据列配置 位于哪个 sheet ,标题行数为几行
     *  <br>
     *  第一个 sheet 位置为0
     *
     */
    fun initialConfigure():ExcelBuildConfig

    //结束触发,返回表集合
    @Throws(MXException::class, RemoteException::class)
    fun endTrigger(list: ExcelTable<ExcelRow>){}


    //开始触发
    @Throws(MXException::class, RemoteException::class)
    fun  startTrigger(excelBuildConfig: ExcelBuildConfig) {}


    /** 行处理触发
     *  @return true:继续往下执行,false:终止执行
     */
    @Throws(MXException::class, RemoteException::class)
    fun  rowTrigger(row: ExcelRow):Boolean {return true}

    //执行错误时触发,用于rollback/close 执行中的 mboset
    fun errorTrigger(e:Exception) {}


}