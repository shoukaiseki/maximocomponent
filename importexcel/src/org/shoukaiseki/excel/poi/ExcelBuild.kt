package org.shoukaiseki.excel.poi

import org.apache.log4j.Logger
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet
import org.shoukaiseki.file.excel.ExcelStaticHouhou
import java.io.InputStream
import java.util.*

/**
 * org.shoukaiseki.excel.poi.ExcelBuild <br></br>

 * @author 蒋カイセキ    Japan-Tokyo  2017-06-21 17:08:47<br></br>
 * *         ブログ http://shoukaiseki.blog.163.com/<br></br>
 * *         E-メール jiang28555@Gmail.com<br></br>
 */
open class ExcelBuild @Throws(Exception::class)
constructor(inputStream: InputStream, ebac: ExcelBuildActionCall) {
    companion object {

        val log = Logger.getLogger("org.shoukaiseki")
    }

    var ebac: ExcelBuildActionCall
    var fs: POIFSFileSystem
    var wb: HSSFWorkbook
    var sheet: HSSFSheet
    var row: HSSFRow
    var excelBuildConfig: ExcelBuildConfig
    //总列数
    val columnNum:Int

    init {
        this.fs = POIFSFileSystem(inputStream)
        this.wb = HSSFWorkbook(fs)
        this.ebac = ebac
        excelBuildConfig = ebac.initialConfigure()
        sheet = wb.getSheetAt(excelBuildConfig.sheet)
        row = sheet.getRow(0)
        columnNum = row.physicalNumberOfCells

    }

    /**
     * 读取Excel数据内容
     * @return Map 包含单元格数据内容的Map对象
     */
    fun readExcelContent(): ExcelTable<ExcelRow> {
        val datas= ExcelTable<ExcelRow>(excelBuildConfig)

        try {
            // 得到总行数
            val rowNum = sheet.lastRowNum
            excelBuildConfig.datacount=rowNum
            row = sheet.getRow(0)
            val colNum = row.physicalNumberOfCells
            ebac.startTrigger(excelBuildConfig)
            var datarow=1L
            for (i in excelBuildConfig.titlecount..rowNum) {
                row = sheet.getRow(i)
                var j = 0
                val row = ExcelRow(datas)
                val temprow=ExcelRow(datas)

                while (j < colNum) {
                    var colname= ExcelStaticHouhou.columnEgo(j).toUpperCase()
                    if(this.row.getCell(j.toShort())==null){
                        row[colname]= ""
                        j++
                        continue
                    }
                    var str = getMergedRegionValue(sheet,this.row.getCell(j.toShort()))
                    temprow[colname]= getCellFormatValue(this.row.getCell(j.toShort()))
                    log.debug("colname=$colname,str=$str")
                    row[colname]= str
                    j++
                }
                if(temprow.isNulls()){
                   continue
                }
                row.rownum=datarow++
                datas.add(row)
                if(!ebac.rowTrigger(row)){
                   break
                }
            }
            ebac.endTrigger(datas)
        }catch (e:Exception){
            log.error("",e)
            throw e;
            ebac.errorTrigger(e)

        }

        return datas
    }



    /**
     * 获取单元格数据内容为字符串类型的数据

     * @param cell Excel单元格
     * *
     * @return String 单元格数据内容
     */
    private fun getStringCellValue(cell: HSSFCell?): String {
        var strCell: String? = ""
        when (cell!!.cellType) {
            HSSFCell.CELL_TYPE_STRING -> strCell = cell.stringCellValue
            HSSFCell.CELL_TYPE_NUMERIC -> strCell = cell.numericCellValue.toString()
            HSSFCell.CELL_TYPE_BOOLEAN -> strCell = cell.booleanCellValue.toString()
            HSSFCell.CELL_TYPE_BLANK -> strCell = ""
            else -> strCell = ""
        }
        if (strCell == "" || strCell == null) {
            return ""
        }
        if (cell == null) {
            return ""
        }
        return strCell
    }

    /**
     * 获取单元格数据内容为日期类型的数据

     * @param cell
     * *            Excel单元格
     * *
     * @return String 单元格数据内容
     */
    private fun getDateCellValue(cell: HSSFCell): String {
        var result = ""
        try {
            val cellType = cell.cellType
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                val date: Date = cell.dateCellValue
                result = (date.year + 1900).toString() + "-" + (date.month + 1) +"-" + date.date
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                val date = getStringCellValue(cell)
                result = date.replace("[年月]".toRegex(), "-").replace("日", "").trim { it <= ' ' }
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = ""
            }
        } catch (e: Exception) {
            println("日期格式不正确!")
            e.printStackTrace()
        }

        return result
    }


    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * *
     * @return
     */
    private fun getCellFormatValue(cell: HSSFCell): Any {
        var cellvalue = Any()
        // 判断当前Cell的Type
        when (cell.cellType) {
        // 如果当前Cell的Type为NUMERIC
            HSSFCell.CELL_TYPE_NUMERIC, HSSFCell.CELL_TYPE_FORMULA -> {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式

                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();

                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    val date = cell.dateCellValue
                    cellvalue=date

                } else {
                    // 取得当前Cell的数值
                    cellvalue = cell.numericCellValue
                }// 如果是纯数字
            }
        // 如果当前Cell的Type为STRIN
            HSSFCell.CELL_TYPE_STRING ->
                // 取得当前的Cell字符串
                cellvalue = cell.richStringCellValue.toString().trim ({ it <= ' ' })
            HSSFCell.CELL_TYPE_BOOLEAN ->
                cellvalue= cell.booleanCellValue

            HSSFCell.CELL_TYPE_BLANK ->
                cellvalue= ""
        // 默认的Cell值
            else -> cellvalue = cell.stringCellValue
        }
        return cellvalue

    }

    /**
     * 如果为合并单元格则返回合并的内容
     *
     * @param sheet
     * @param cell
     * @return
     */
    fun getMergedRegionValue(sheet: HSSFSheet, cell: HSSFCell):Any {
        var value:Any
        // 得到一个sheet中有多少个合并单元格
        val sheetMergeddRegionCount = sheet.getNumMergedRegions()
        for (i in 0..sheetMergeddRegionCount - 1)
        {
            // 得出具体的合并单元格
            val ca = sheet.getMergedRegion(i)
            // 得到合并单元格的起始行, 结束行, 起始列, 结束列
            val firstColumn = ca.getFirstColumn()
            val lastColumn = ca.getLastColumn()
            val firstRow = ca.getFirstRow()
            val lastRow = ca.getLastRow()
            // 判断该单元格是否在合并单元格范围之内, 如果是, 则返回 true
            val Mcell = firstColumn
            if (cell.getColumnIndex() <= lastColumn && cell.getColumnIndex() >= Mcell)
            {
                if (cell.getRowIndex() <= lastRow && cell.getRowIndex() >= firstRow)
                {
                    sheet.getCellComment(Mcell, firstRow)
                    val row = sheet.getRow(firstRow)
                    val cell2 = row.getCell(firstColumn)
                    value = getCellFormatValue(cell2)
                    return value
                }
            }
        }
        value = getCellFormatValue(cell)
        return value
    }

}

