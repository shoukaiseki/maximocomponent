# maximo列表增加首页尾页按钮

在maximo的table中增加首页,尾页按钮图标
<br>
在第一次点击尾页时会有响应延迟,因为需要到数据库取下一页的数据缓存,之后就会很快
<br>
但是行数超过一定数量,转到尾页会报错,因为maximo有列表取值数据限制,默认为50000
<br>
事例如下图:

列表初始图片

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E5%88%97%E8%A1%A8%E5%A2%9E%E5%8A%A0%E9%A6%96%E9%A1%B5%E5%B0%BE%E9%A1%B5%E6%8C%89%E9%92%AE/img/001.png)

点击'尾页'按钮之后

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E5%88%97%E8%A1%A8%E5%A2%9E%E5%8A%A0%E9%A6%96%E9%A1%B5%E5%B0%BE%E9%A1%B5%E6%8C%89%E9%92%AE/img/002.png)

点击'首页'按钮之后

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E5%88%97%E8%A1%A8%E5%A2%9E%E5%8A%A0%E9%A6%96%E9%A1%B5%E5%B0%BE%E9%A1%B5%E6%8C%89%E9%92%AE/img/003.png)

点击'跳转到指定页'按钮之后

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E5%88%97%E8%A1%A8%E5%A2%9E%E5%8A%A0%E9%A6%96%E9%A1%B5%E5%B0%BE%E9%A1%B5%E6%8C%89%E9%92%AE/img/004.png)

'跳转指定页'效果图

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E5%88%97%E8%A1%A8%E5%A2%9E%E5%8A%A0%E9%A6%96%E9%A1%B5%E5%B0%BE%E9%A1%B5%E6%8C%89%E9%92%AE/img/005.png)


## 增加按钮标题

### DB2 数据库
```Sql
REM INSERTING into MAXMESSAGES
SET DEFINE OFF;
Insert into MAXMESSAGES (MSGKEY,MSGGROUP,VALUE,TITLE,DISPLAYMETHOD,OPTIONS,BUTTONTEXT,MAXMESSAGESID,MSGID,EXPLANATION,ADMINRESPONSE,OPERATORRESPONSE,SYSTEMACTION,PREFIX,ROWSTAMP) values ('cntrlTableAltEndRow','tableinfo','尾页',null,'STATUS',0,null, (next value for MAXMESSAGESSEQ),'BMXAA9106E',null,null,null,null,1,2900247);
Insert into MAXMESSAGES (MSGKEY,MSGGROUP,VALUE,TITLE,DISPLAYMETHOD,OPTIONS,BUTTONTEXT,MAXMESSAGESID,MSGID,EXPLANATION,ADMINRESPONSE,OPERATORRESPONSE,SYSTEMACTION,PREFIX,ROWSTAMP) values ('cntrlTableAltFirstRow','tableinfo','首页',null,'STATUS',0,null,(next value for MAXMESSAGESSEQ),'BMXAA9107E',null,null,null,null,1,2900246);
Insert into MAXMESSAGES (MSGKEY,MSGGROUP,VALUE,TITLE,DISPLAYMETHOD,OPTIONS,BUTTONTEXT,MAXMESSAGESID,MSGID,EXPLANATION,ADMINRESPONSE,OPERATORRESPONSE,SYSTEMACTION,PREFIX,ROWSTAMP) values ('ntrlTableJumpToPage','tableinfo','跳转到指定页',null,'STATUS',0,null,(next value for MAXMESSAGESSEQ),'BMXAA9108E',null,null,null,null,1,2900246);
```

### oracle 数据库

```Sql
SET DEFINE OFF;
Insert into MAXMESSAGES (MSGKEY,MSGGROUP,VALUE,TITLE,DISPLAYMETHOD,OPTIONS,BUTTONTEXT,MAXMESSAGESID,MSGID,EXPLANATION,ADMINRESPONSE,OPERATORRESPONSE,SYSTEMACTION,PREFIX,ROWSTAMP) values ('cntrlTableAltEndRow','tableinfo','尾页',null,'STATUS',0,null, (MAXMESSAGESSEQ.nextval),'BMXAA9106E',null,null,null,null,1,2900247);
Insert into MAXMESSAGES (MSGKEY,MSGGROUP,VALUE,TITLE,DISPLAYMETHOD,OPTIONS,BUTTONTEXT,MAXMESSAGESID,MSGID,EXPLANATION,ADMINRESPONSE,OPERATORRESPONSE,SYSTEMACTION,PREFIX,ROWSTAMP) values ('cntrlTableAltFirstRow','tableinfo','首页',null,'STATUS',0,null,(MAXMESSAGESSEQ.nextval),'BMXAA9107E',null,null,null,null,1,2900246);
Insert into MAXMESSAGES (MSGKEY,MSGGROUP,VALUE,TITLE,DISPLAYMETHOD,OPTIONS,BUTTONTEXT,MAXMESSAGESID,MSGID,EXPLANATION,ADMINRESPONSE,OPERATORRESPONSE,SYSTEMACTION,PREFIX,ROWSTAMP) values ('ntrlTableJumpToPage','tableinfo','跳转到指定页',null,'STATUS',0,null,(MAXMESSAGESSEQ.nextval),'BMXAA9108E',null,null,null,null,1,2900246);
```

## library.xml 增加
```Xml
	<dialog beanclass="org.shoukaiseki.webclient.beans.searchtable.JumpToPageDataBean" id="showsearchtable" mboname="SKSSHOWSEARCHTABLE" label="跳转到指定页">
		<section id="showsearchtable_1">
			<textbox dataattribute="pagenum" id="showsearchtable_textbox_001" inputmode="required"/>
		</section>
		<buttongroup id="showsearchtable_2">
			<pushbutton id="showsearchtable_2_1" label="转到" mxevent="dialogok"/>
			<pushbutton id="showsearchtable_2_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>
```

## control-registry.xml 修改
类名修改,按钮增加 按钮增加部分 含有 shoukaiseki 注释

```Xml
	<control-descriptor name="table" descriptor-class="psdi.webclient.system.runtime.DatasrcDescriptor" instance-class="org.shoukaiseki.webclient.controls.Table">
		<property-list>
			<property name="allowqualifiedrestriction">
				<flag name="nonconditional" />
				<flag name="final" />
			</property>
			<property name="apprestrictions" />
			<property name="beanclass">
				<flag name="nonconditional" />
			</property>
			<property name="border" />
			<property name="collapsed">
				<flag name="nonconditional" />
			</property>
			<property name="collapsedemptylabel" />
			<property name="collapsedlabel" />
			<property name="collapsewhenempty"/>
			<property name="cssclass" />
			<property name="currentrow">
				<default-value>-10</default-value>
			</property>
			<property name="dataattribute">
				<flag name="final" />
			</property>
			<property name="datarowheight" />
			<property name="datasrc">
				<flag name="nonconditional" />
				<flag name="global" />
			</property>
			<property name="debug">
				<flag name="global" />
			</property>
			<property name="defaultbutton" >
				<flag name="global" />
			</property>
			<property name="description">
				<flag name="translatable" />
			</property>
			<property name="designermboname">
				<flag name="nonconditional" />
			</property>
			<property name="display" type="xsd:boolean">
				<flag name="global" />
				<default-value>true</default-value>
			</property>
			<property name="displayrowsperpage" type="xsd:positiveInteger">
				<default-value>10</default-value>
			</property>
			<property name="download" />
			<!-- shoukaiseki modify start-->
			<property name="migratedownload" type="xsd:boolean">
				<default-value>false</default-value>
			</property>
			<!-- shoukaiseki modify end-->
			<property name="filterable" type="xsd:boolean">
				<flag name="final" />
				<default-value>true</default-value>
			</property>
			<property name="emptyonclear">
				<flag name="nonconditional" />
				<flag name="final" />
			</property>
			<property name="ermrelationship">
				<flag name="nonconditional" />
			</property>
			<property name="filterexpanded" type="xsd:boolean" />
			<property name="filterrowheight" />
			<property name="headercss">
				<default-value>hb thb</default-value>
			</property>
			<property name="headerheight" />
			<property name="headerlabel" />
			<property name="height" />
			<property name="id">
				<flag name="final" />
			</property>
			<property name="inputmode">
				<flag name="global" />
			</property>
			<property name="insertable" />
			<property name="label">
				<flag name="translatable" />
			</property>
			<property name="labelcss" />
			<property name="licensekey">
				<flag name="final" />
			</property>
			<property name="listeners" />
			<property name="maxrows" type="xsd:positiveInteger" />
			<property name="mboname">
				<flag name="nonconditional" />
			</property>
			<property name="norowlabel" />
			<property name="ondatachange" />
			<property name="orderby" />
			<property name="parentdatasrc">
				<flag name="final" />
				<flag name="nonconditional" />
			</property>
			<property name="parentemptylabel">
				<flag name="translatable" />
			</property>
			<property name="parentmbo" />
			<property name="relationship">
				<flag name="nonconditional" />
			</property>
			<property name="roundedheader" type="xsd:boolean">
				<default-value>false</default-value>
			</property>
			<property name="rowdetailsexpanded" type="xsd:boolean">
				<default-value>false</default-value>
			</property>
			<property name="rowsperpage" />
			<property name="selectmode" />
			<property name="showcount" />
			<property name="sigoption">
				<flag name="nonconditional" />
				<flag name="final" />
			</property>
			<property name="sigoptiondatasrc">
				<flag name="nonconditional" />
				<flag name="final" />
			</property>
			<property name="sortable" />
			<property name="spacecolumns" type="xsd:boolean">
				<default-value>false</default-value>
			</property>
			<property name="startempty" type="xsd:boolean" />
			<property name="startemptymsg" />
			<property name="startrow" />
			<property name="style" />
			<property name="subselectionon" />
			<property name="tablecounter">
				<flag name="final" />
			</property>
			<property name="textcss">
				<flag name="global" />
				<flag name="final" />
				<default-value>text</default-value>
			</property>
			<property name="titleattributes" />
			<property name="titlerowheight" />
			<property name="usenonpersistentmbofordownload" type="xsd:boolean">
				<default-value>false</default-value>
			</property>
			<property name="usesubselection" />
			<property name="whereclause" />
			<property name="width" />
			<property name="inlisttab">
				<flag name="system" />
			</property>
		</property-list>
		<component-list>
			<components id="co" layout="vertical">
				<components id="co2" layout="horizontal">
					<tableouter id="to" cssclass="@{cssclass}">
						<components id="co3" layout="vertical">
							<headerbar id="hb" align="left" width="@{width}" layout="horizontal" description="@{description}" cssclass="@{headercss}" rounded="@{roundedheader}" headerheight="@{headerheight}" hidewhen="{mobile}==true">
								<label id="lb" labelcss="@{labelcss}" cssclass="ttit" title="@{label}" alwaysrefresh="true" useheading="true" textcss="@{textcss}" erroricon="true" useforlabelledby="true" hidewhen="@{label}==null"/>
								<image id="img" src="tablebtn_divider.gif" ariahidden="true" hidewhen="@{label}==null"/>
								<toggleimage id="ti" srctrue="tablebtn_filter_on.gif" srcfalse="tablebtn_filter_off.gif" mxevent="togglefilter" statecheck="isFilterOpen" msgfalse="tableinfo#cntrlTablebodyOpenFltr" msgtrue="tableinfo#cntrlTablebodyCloseFltr" falseactive="true" clickablestate="isFilterable" hidewhen="@{filterable}==false" />
								<label id="lb2" cssclass="tht" mxevent="togglefilter" msggroup="tableinfo" msgkey="cntrlTableFilter" clickablestate="isFilterable" textcss="@{textcss}" hidewhen="@{filterable}==false" includeuistatus="false" />
								<image id="img2" src="img_arrow_quickfilter.gif" hidewhen="@{filterable}==false" />
								<toggleimage id="ti2" srctrue="qf_find.gif" srcfalse="qf_find_disabled.gif" mxevent="filterrows" statecheck="isFilterOpen" msgtrue="tableinfo#cntrlTablebodyFltrTable" msgfalse="tableinfo#cntrlTablebodyFltrTable" hidewhen="@{filterable}==false" />
								<image id="img3" src="tablebtn_divider.gif" hidewhen="@{filterable}==false" ariahidden="true"/>
								<toggleimage id="ti3" srctrue="qf_clear.gif" srcfalse="qf_clear_disabled.gif" mxevent="clearfilter" statecheck="isFiltered" msgtrue="tableinfo#cntrlTablebodyClearFltr" msgfalse="" hidewhen="@{filterable}==false" />
								<image id="img4" src="tablebtn_divider.gif" hidewhen="@{inlisttab}==false" ariahidden="true"/>
								<image id="img5" src="listab_refresh.gif" mxevent="reset" msggroup="tableinfo" msgkey="listTableRefresh" hidewhen="@{inlisttab}==false" />
								<image id="img6" src="tablebtn_divider.gif" hidewhen="@{filterable}==false" ariahidden="true"/>
								<toggleimage id="ti4" srctrue="tablebtn_nextup_on.gif" srcfalse="tablebtn_nextup_off.gif" mxevent="previousrow" statecheck="moreRowsBefore" msgtrue="tableinfo#cntrlTableAltPrevRow" msgreadertrue="tableinfo#cntrlTableCtrlAltPrevRow" msgfalse="" />
								<image id="img7" src="blank.gif" width="7px" height="7px" />
								<toggleimage id="ti5" srctrue="tablebtn_nextdown_on.gif" srcfalse="tablebtn_nextdown_off.gif" mxevent="nextrow" statecheck="moreRowsAfter" msgtrue="tableinfo#cntrlTableAltNextRow" msgreadertrue="tableinfo#cntrlTableCtrlAltNextRow" msgfalse="" />
								<image id="img8" src="tablebtn_divider.gif" ariahidden="true"/>
								<!--shoukaiseki add start-->
								<toggleimage id="ti6asus" srctrue="btn_first.gif" srcfalse="btn_first_off.gif" mxevent="startpage" statecheck="morePagesBefore" msgtrue="tableinfo#cntrlTableAltFirstRow" msgfalse="" />
								<!--shoukaiseki add end-->
								<toggleimage id="ti6" srctrue="tablebtn_previous_on.gif" srcfalse="tablebtn_previous_off.gif" mxevent="previouspage" statecheck="morePagesBefore" msgtrue="tableinfo#cntrlTableAltPrevPage" msgfalse="" />
								<label id="lb3" cssclass="tht tCount" title="@{tablecounter}" alwaysinclude="true" textcss="@{textcss}" hidewhen="{designmode}==true" includeuistatus="false" />
								<toggleimage id="ti7" srctrue="tablebtn_next_on.gif" srcfalse="tablebtn_next_off.gif" mxevent="nextpage" statecheck="morePagesAfter" msgtrue="tableinfo#cntrlTableAltNextPage" msgfalse="" />
								<!--shoukaiseki add start-->
								<toggleimage id="ti7asus" srctrue="btn_last.gif" srcfalse="btn_last_off.gif" mxevent="endpage" statecheck="morePagesAfter" msgtrue="tableinfo#cntrlTableAltEndRow" msgfalse="" />
								<image id="img5asus" src="img_menu.gif" mxevent="showsearchtable" msggroup="tableinfo" msgkey="ntrlTableJumpToPage" hidewhen="@{inlisttab}==false" />
								<!--shoukaiseki add end-->
								<blankline id="bl" cssclass="text ts" alwaysinclude="true" />
								<label id="lb4" cssclass="tht" image="nav_icon_export.gif" imagefalse="tablebtn_download_off.gif" mxevent="migrate" msggroup="tableinfo" msgkey="cntrlTableLblDownload" clickablestate="canDownload" hidewhen="@{download}==false" textcss="@{textcss}" includeuistatus="false" imagevalign="middle" visible="@{migratedownload}"/>
								<image id="img9" src="tablebtn_divider.gif" hidewhen="@{download}==false" ariahidden="true"/>
								<toggleimage id="ti8" srctrue="minimize.gif" srcfalse="maximize.gif" statecheck="isExpanded" msgreplace="@{label}" msgtrue="tableinfo#cntrlTableAltHideTbl" msgfalse="tableinfo#cntrlTableAltShowTbl" mxevent="togglecollapse" falseactive="true" synchronous="false" />
							</headerbar>
							<headerbar id="hb2" align="left" width="@{width}" layout="horizontal" description="@{description}" cssclass="@{headercss}" rounded="@{roundedheader}" headerheight="@{headerheight}" hidewhen="{mobile}==false">
								<label id="lb5" labelcss="@{labelcss}" cssclass="ttit" title="@{label}" alwaysrefresh="true" useheading="true" textcss="@{textcss}" hidewhen="{mobile}==false" erroricon="true" useforlabelledby="true" mxevent="togglecollapse"/>
								<toggleimage id="ti9" srctrue="qf_find.gif" srcfalse="qf_find_disabled.gif" mxevent="filterrows" statecheck="isFilterOpen" msgtrue="tableinfo#cntrlTablebodyFltrTable" msgfalse="tableinfo#cntrlTablebodyFltrTable" hidewhen="@{filterable}==false" />
								<toggleimage id="ti10" srctrue="tablebtn_previous_on.gif" srcfalse="tablebtn_previous_off.gif" mxevent="previouspage" statecheck="morePagesBefore" msgtrue="tableinfo#cntrlTableAltPrevPage" msgfalse="" />
								<label id="lb6" cssclass="tht" title="@{tablecounter}" alwaysinclude="true" textcss="@{textcss}" />
								<toggleimage id="ti11" srctrue="tablebtn_next_on.gif" srcfalse="tablebtn_next_off.gif" mxevent="nextpage" statecheck="morePagesAfter" msgtrue="tableinfo#cntrlTableAltNextPage" msgfalse="" />
								<blankline id="bl2" cssclass="text ts" alwaysinclude="true" />
								<toggleimage id="ti12" srctrue="minimize.gif" srcfalse="maximize.gif" statecheck="isExpanded" msgreplace="@{label}" msgtrue="tableinfo#cntrlTableAltHideTbl" msgfalse="tableinfo#cntrlTableAltShowTbl" mxevent="togglecollapse" falseactive="true" synchronous="false" />
							</headerbar>
							<components id="co4" layout="horizontal">
								<children id="chld" cellspacing="0" width="100%" cssclass="table" />
							</components>
							<headerbar id="hb3" align="left" width="@{width}" layout="horizontal" description="@{description}" cssclass="@{headercss}" rounded="@{roundedheader}" headerheight="@{headerheight}" hidewhen="{mobile}==false" inverted="true">
								<toggleimage id="ti13" srctrue="tablebtn_previous_on.gif" srcfalse="tablebtn_previous_off.gif" mxevent="previouspage" statecheck="morePagesBefore" msgtrue="tableinfo#cntrlTableAltPrevPage" msgfalse="" />
								<label id="lb7" cssclass="tht" title="@{tablecounter}" alwaysinclude="true" textcss="@{textcss}" />
								<toggleimage id="ti14" srctrue="tablebtn_next_on.gif" srcfalse="tablebtn_next_off.gif" mxevent="nextpage" statecheck="morePagesAfter" msgtrue="tableinfo#cntrlTableAltNextPage" msgfalse="" />
								<blankline id="bl3" cssclass="text ts" alwaysinclude="true" />
							</headerbar>
						</components>
					</tableouter>
				</components>
			</components>
		</component-list>
		<containers>
			<container name="dialog" />
			<container name="section" />
			<container name="sectioncol" />
			<container name="tab" />
		</containers>
	</control-descriptor>
```

## java类代码 
### org.shoukaiseki.webclient.controls.Table.java
```Java
package org.shoukaiseki.webclient.controls;

import org.shoukaiseki.webclient.utils.DataBeanUtils;
import psdi.util.MXException;
import psdi.webclient.system.beans.DataBean;

import java.rmi.RemoteException;

/**
 * org.shoukaiseki.webclient.controls.Table <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-28 10:28:48<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class Table extends psdi.webclient.controls.Table{
    DataBean tableBean = null;

    public Table(){
        super();

    }

    @Override
    public int nextpage() throws MXException {
        return super.nextpage();
    }

    public void initialize() {
        super.initialize();

    }


    public int startpage() throws Exception {
        this.forceFocusRow(-1);
        this.tableBean = this.getDataBean();
//        this.tableBean.scrollnext();
//        this.setCurrentRow(this.tableBean.getTableOffset());
        int count = tableBean.getMboSet().count();
        if(count>=1){
            DataBeanUtils.movetorow(1,tableBean);
        }
        return 1;
    }

    public int endpage() throws Exception {
        this.forceFocusRow(-1);
        this.tableBean = this.getDataBean();
//        this.tableBean.scrollnext();
//        this.setCurrentRow(this.tableBean.getTableOffset());
        int count = tableBean.getMboSet().count();
        if(count>=1) {
            DataBeanUtils.movetorow(count,tableBean);
        }
        return 1;
    }


}
```

### JumpToPageDataBean.kt

```Kt
package org.shoukaiseki.webclient.beans.searchtable

import org.apache.log4j.Logger
import org.shoukaiseki.webclient.utils.DataBeanUtils
import psdi.mbo.MboConstants
import psdi.util.MXException
import psdi.webclient.controls.Table
import psdi.webclient.system.beans.DataBean

import java.rmi.RemoteException

/**
 * org.shoukaiseki.webclient.beans.searchtable.JJumpToPageDataBean<br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-28 13:00:372017-09-28グ13:01:25shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
open class JumpToPageDataBean : DataBean() {

    var displayrowsperpage=-1

    companion object {
        val log:Logger = Logger.getLogger("org.shoukaiseki.showsearchtable")
    }

    override fun initialize() {
        super.initialize()
        log.debug("mboSet.isEmpty=${mboSet.isEmpty}")
        if(mboSet.isEmpty){
            val mbo = mboSet.add()
        }
        val ci = this.creatingEvent.sourceControlInstance
        if(ci.dataBean==null){
            log.debug("dataBean is null")
        }
        val dataBean:DataBean = ci.dataBean
        log.debug("currentRow=${dataBean.currentRow}")
        log.debug("pageEndRow=${dataBean.pageEndRow}")
        log.debug("pageRowCount=${dataBean.pageRowCount}")
        displayrowsperpage=getDisplayRowsPerPage()
        val currentRow = dataBean.currentRow+1
        log.debug("currentRow=${currentRow}")
        log.debug("currentRow=${currentRow/displayrowsperpage}")
        val page=if(currentRow%displayrowsperpage==0){
            currentRow/displayrowsperpage
        }else{
            currentRow/displayrowsperpage+1
        }
        log.debug("page=${page}")
        mbo?.setValue("PAGENUM",page,MboConstants.NOACCESSCHECK)
        setCurrentRecordData(mbo)
    }

    @Synchronized
    @Throws(MXException::class, RemoteException::class)
    override open  fun execute(): Int {
        log.debug("$javaClass.execute")
        val ci = this.creatingEvent.sourceControlInstance
        if(ci.dataBean==null){
            log.debug("dataBean is null")
            return 1
        }

        val dataBean:DataBean = ci.dataBean
        displayrowsperpage=getDisplayRowsPerPage()


        if(mbo!=null){
            val pagenum = mbo.getInt("PAGENUM")
            var rownum=pagenum*displayrowsperpage
            log.debug("pagenum=$pagenum,rownum=$rownum")
            DataBeanUtils.movetorow(rownum,dataBean)


        }
        return 1
    }

    /**
     * 获取每页的行数
     */
    open fun getDisplayRowsPerPage():Int{
        if(displayrowsperpage!=-1){
           return displayrowsperpage
        }
        val ci = this.creatingEvent.sourceControlInstance
        val dataBean:DataBean = ci.dataBean
        log.debug("ci=${ci.javaClass}")
        log.debug("dataBean=$dataBean,id=${dataBean?.id}")
        log.debug("showcount=${ci.getProperty("showcount")}")
        log.debug("displayrowsperpage=${ci.getProperty("displayrowsperpage")}")
        displayrowsperpage=dataBean.pageRowCount

        if(ci is Table){
            log.debug("body.displayrowsperpage=${ci.body.getProperty("displayrowsperpage")}")
            if(ci.body!=null){
                displayrowsperpage=ci.body.getInt("displayrowsperpage",displayrowsperpage)
            }
        }
        return displayrowsperpage
    }

}
```
### DataBeanUtils.kt

```Kt
package org.shoukaiseki.webclient.utils

import org.apache.log4j.Logger
import psdi.webclient.system.beans.DataBean

/**
 * org.shoukaiseki.webclient.utils.DataBeanUtils <br></br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-28 13:39:16<br></br>
 * ブログ http://shoukaiseki.blog.163.com/<br></br>
 * E-メール jiang28555@Gmail.com<br></br>
 */
open class DataBeanUtils {


    companion object {
        @JvmStatic var log: Logger = Logger.getLogger("org.shoukaiseki.utils")
        /**
         * 跳转到指定行
         * @param rownum
         * @param dataBean
         * @throws Exception
         */
        @Throws(Exception::class)
        @JvmStatic fun movetorow(inrownum: Int, dataBean: DataBean) {
            var pageEndRowTemp = -1
            var rownum=inrownum
            if (dataBean.mboSet.count() <rownum ) {
                rownum=dataBean.mboSet.count()
            }
            while (dataBean.pageEndRow < rownum || dataBean.pageEndRow - dataBean.pageRowCount > rownum) {
                if (dataBean.pageEndRow < rownum) {
                    dataBean.scrollnext()
                } else {
                    dataBean.scrollprev()
                }
                if (pageEndRowTemp == dataBean.pageEndRow) {
                    break
                }
                pageEndRowTemp = dataBean.pageEndRow
                log.debug("rownum=" + rownum + ",PageEndRow=" + dataBean.pageEndRow + ",pageStartRow=" + (dataBean.pageEndRow - dataBean.pageRowCount))
            }
            dataBean.currentRow = rownum - 1
        }
    }

}

```
