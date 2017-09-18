# maximo迁移工具


### control-registry.xml 修改
```Xml
	<control-descriptor name="table" descriptor-class="psdi.webclient.system.runtime.DatasrcDescriptor" instance-class="${package}.Table">
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
								<toggleimage id="ti6" srctrue="tablebtn_previous_on.gif" srcfalse="tablebtn_previous_off.gif" mxevent="previouspage" statecheck="morePagesBefore" msgtrue="tableinfo#cntrlTableAltPrevPage" msgfalse="" />
								<label id="lb3" cssclass="tht tCount" title="@{tablecounter}" alwaysinclude="true" textcss="@{textcss}" hidewhen="{designmode}==true" includeuistatus="false" />
								<toggleimage id="ti7" srctrue="tablebtn_next_on.gif" srcfalse="tablebtn_next_off.gif" mxevent="nextpage" statecheck="morePagesAfter" msgtrue="tableinfo#cntrlTableAltNextPage" msgfalse="" />
								<blankline id="bl" cssclass="text ts" alwaysinclude="true" />
								<!--shoukaiseki add start-->
								<label id="lb4" cssclass="tht" image="nav_icon_export.gif" imagefalse="tablebtn_download_off.gif" mxevent="migrate" msggroup="tableinfo" msgkey="cntrlTableLblDownload" clickablestate="canDownload" hidewhen="@{download}==false" textcss="@{textcss}" includeuistatus="false" imagevalign="middle" visible="@{migratedownload}"/>
								<label id="lb4asus" cssclass="tht" image="tablebtn_download.gif" imagefalse="tablebtn_download_off.gif" mxevent="download" msggroup="tableinfo" msgkey="cntrlTableLblDownload" clickablestate="canDownload" hidewhen="@{download}==false" textcss="@{textcss}" includeuistatus="false" imagevalign="middle"/>
								<!--shoukaiseki add end-->
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

### 修改webclient/components/label.jsp
```Java
	//This is to fix a problem with table downloads (we force the component to be a direct lin to the jsp)
	if(component.getProperty("mxevent").equals("download"))
	{
		link=wcs.getMaximoRequestContextURL() + "/ui/" + System.currentTimeMillis() + "?_tbldnld=" + control.getId() + "&" + wcs.getUISessionUrlParameter() + wcs.getCSRFTokenParameter();
		mxevent="";
	}
	//shoukaiseki modify start
	if(component.getProperty("mxevent").equals("migrate"))
	{
		link=wcs.getMaximoRequestContextURL() + "/ui/" + System.currentTimeMillis() + "?responsetype="+System.currentTimeMillis()+".json&shoukaiseki=migrate&_tbldnld=" + control.getId() + "&" + wcs.getUISessionUrlParameter() + wcs.getCSRFTokenParameter();
		//调试时候 sks_download 设置为 false,直接在网页中预览
		link=wcs.getMaximoRequestContextURL() + "/webclient/help/migrate.jsp?sks_download=false&_tbldnld=" + control.getId() + "&" + wcs.getUISessionUrlParameter() + wcs.getCSRFTokenParameter();
		link=wcs.getMaximoRequestContextURL() + "/webclient/help/migrate.jsp?sks_download=true&_tbldnld=" + control.getId() + "&" + wcs.getUISessionUrlParameter() + wcs.getCSRFTokenParameter();
		mxevent="";
	}
	//shoukaiseki modify end
```

### 新增webclient/help/migrate.jsp
jsp对应有处理类,位于src目录




## 使用范例
### configur 数据库配置
在主列表table中增加属性 migratedownload="true"
```Xml
<?xml version="1.0" encoding="UTF-8"?>

<presentation apphelp="com.ibm.mbs.doc,configur/c_db_config.html" beanclass="psdi.webclient.beans.configur.ConfigurBean" id="configur" ismobile="false" mboname="MAXOBJECTCFG" resultstableid="results_showlist" synchronous="true" version="7.1.0.0">
	<page id="mainrec" scroll="false">
		<include controltoclone="pageHeader" id="INCLUDE-pageHeader"/>
		<clientarea id="clientarea">
			<tabgroup id="maintabs" style="form">
				<tab default="true" id="results" label="列表" type="list">
					<menubar event="search" id="actiontoolbar" sourcemethod="getAppSearchOptions"/>
					<table datasrc="results_showlist" id="results_showlist" inputmode="readonly" label="对象" mboname="MAXOBJECTCFG" selectmode="multiple" migratedownload="true">
						<tablebody displayrowsperpage="20" filterable="true" filterexpanded="true" id="results_showlist_tablebody">
							<tablecol dataattribute="OBJECTNAME" filterable="false" id="results_showlist_tablebody_1" mxevent="toggleselectrow" mxevent_desc="选择行{0}" sortable="false" type="event"/>
							<tablecol dataattribute="viewchanged" id="results_showlist_tablebody_2"/>
							<tablecol dataattribute="objectname" id="results_showlist_tablebody_3" mxevent="selectrecord" mxevent_desc="转到 %1" type="link"/>
							<tablecol dataattribute="description" id="results_showlist_tablebody_4"/>
							<tablecol dataattribute="maxtablecfg.exttablename" id="results_showlist_tablebody_7"/>
							<tablecol dataattribute="servicename" id="results_showlist_tablebody_5"/>
							<tablecol dataattribute="maxservice.description" id="results_showlist_tablebody_6"/>
							<tablecol filterable="false" id="results_bookmark" mxevent="BOOKMARK" mxevent_desc="添加到书签" mxevent_icon="btn_addtobookmarks.gif" sortable="false" type="event"/>
						</tablebody>
					</table>
				</tab>
				<tab id="main" label="对象" type="insert">
					<section border="true" id="main_header">
						<sectionrow id="main_header_row1">
							<sectioncol id="main_header_row1_col1">
								<section id="main_grid3">
									<multiparttextbox dataattribute="objectname" descdataattribute="description" id="main_grid3_1" ondatachange="resetchildren"/>
								</section>
							</sectioncol>
							<sectioncol id="main_header_row1_col2">
								<section id="main_grid3a">
									<textbox dataattribute="viewchanged" id="main_grid3a_1"/>
								</section>
							</sectioncol>
						</sectionrow>
					</section>
					<section id="main_section">
						<sectionrow id="main_section_row1">
							<sectioncol id="main_section_row1_col1">
								<section id="main_section_row1_col1_section" label="详细信息">
									<sectionrow id="details1">
										<sectioncol id="details2">
											<section id="details3">
												<textbox dataattribute="servicename" id="main_details3_1" lookup="maxservice"/>
												<textbox dataattribute="maxservice.description" id="main_details3_2"/>
												<textbox dataattribute="ENTITYNAME" id="main_details3_3" ondatachange="resetchildren"/>
												<textbox dataattribute="CLASSNAME" id="main_details3_4"/>
												<textbox dataattribute="EXTENDSOBJECT" id="main_details3_5" lookup="objectname" ondatachange="resetchildren"/>
												<textbox dataattribute="siteorgtype" id="main_details3_6" lookup="valuelist"/>
												<textbox dataattribute="textdirection" id="main_details3_7" lookup="valuelist"/>
											</section>
										</sectioncol>
										<sectioncol id="details4">
											<section id="details5">
												<checkbox dataattribute="MAINOBJECT" id="main_details5_1"/>
												<checkbox dataattribute="PERSISTENT" id="main_details5_2" ondatachange="resetchildren"/>
												<checkbox dataattribute="USERDEFINED" id="main_details5_3"/>
												<checkbox dataattribute="IMPORTED" id="main_details5_4"/>
												<checkbox dataattribute="INTERNAL" id="main_details5_5"/>
											</section>
										</sectioncol>
									</sectionrow>
								</section>
							</sectioncol>
							<sectioncol id="main_details_row1_col1">
								<section id="main_grid5" label="表">
									<sectionrow id="table1">
										<sectioncol id="table2">
											<section id="table3">
												<textbox dataattribute="STORAGEPARTITION" id="main_table3_1" lookup="valuelist"/>
												<combobox dataattribute="storagetypedesc" id="main_table3_2" sigoption="STORAGETYPE"/>
												<textbox dataattribute="uniquecolumnname" id="main_table3_3" ondatachange="resetchildren"/>
												<textbox dataattribute="langtablename" id="main_table3_4"/>
												<textbox dataattribute="langcolumnname" id="main_table3_5"/>
												<textbox dataattribute="altixname" id="main_table3_6" lookup="maxsysindexes"/>
												<textbox dataattribute="trigroot" id="main_table3_7"/>
												<textbox dataattribute="maxtablecfg.exttablename" id="main_table3_8" inputmode="readonly"/>
											</section>
										</sectioncol>
										<sectioncol id="table4">
											<section id="table5">
												<checkbox dataattribute="addrowstamp" id="main_table5_1"/>
												<checkbox dataattribute="islangtable" id="main_table5_2" inputmode="readonly"/>
												<checkbox dataattribute="isaudittable" id="main_table5_3" inputmode="readonly"/>
												<checkbox dataattribute="textsearchenabled" id="main_table5_4" ondatachange="resetchildren"/>
											</section>
										</sectioncol>
									</sectionrow>
								</section>
							</sectioncol>
						</sectionrow>
					</section>
					<section id="main_view" label="查看">
						<sectionrow id="main_details_row1t">
							<sectioncol id="main_details_row1_col1t">
								<section id="main_grid5t">
									<checkbox dataattribute="ISVIEW" id="main_grid8_1" ondatachange="resetchildren"/>
									<multilinetextbox columns="18" dataattribute="viewwhere" id="main_grid8_2" rows="3"/>
								</section>
							</sectioncol>
							<sectioncol id="main_details_row1_col2t">
								<section id="main_grid6t">
									<textbox dataattribute="joinobject" id="main_grid6t_1" lookup="objectname" ondatachange="resetchildren"/>
									<multilinetextbox columns="18" dataattribute="viewselect" id="main_grid6t_2" rows="3"/>
								</section>
							</sectioncol>
							<sectioncol id="main_details_row1_col3t">
								<section id="main_grid8t">
									<checkbox dataattribute="autoselect" id="main_grid8t_1" ondatachange="resetchildren"/>
									<multilinetextbox columns="18" dataattribute="viewfrom" id="main_grid8t_2" rows="3"/>
								</section>
							</sectioncol>
						</sectionrow>
					</section>
					<section id="main_audit" label="审计">
						<sectionrow id="main_details_row1s">
							<sectioncol id="main_details_row1_col1s">
								<section id="main_grid5s">
									<checkbox dataattribute="EAUDITENABLED" id="main_grid5s_1"/>
									<textbox dataattribute="EAUDITTBNAME" id="main_grid5s_2"/>
								</section>
							</sectioncol>
							<sectioncol id="main_details_row1_col2s">
								<section id="main_grid6s">
									<multilinetextbox columns="18" dataattribute="EAUDITFILTER" id="main_grid6s_1" rows="3"/>
								</section>
							</sectioncol>
							<sectioncol id="main_details_row1_col3s">
								<section id="main_grid8s">
									<multilinetextbox columns="18" dataattribute="ESIGFILTER" id="main_grid8s_1" rows="3"/>
								</section>
							</sectioncol>
						</sectionrow>
					</section>
				</tab>
				<tab id="columns" label="属性">
					<section border="true" id="columns_header">
						<sectionrow id="columns_header_row1">
							<sectioncol id="columns_header_row1_col1">
								<section id="columns_grid3">
									<multiparttextbox dataattribute="objectname" descdataattribute="description" id="columns_grid3_1"/>
								</section>
							</sectioncol>
							<sectioncol id="columns_header_row1_col2">
								<section id="columns_grid3a">
									<textbox dataattribute="viewchanged" id="columns_grid4_6"/>
								</section>
							</sectioncol>
						</sectionrow>
					</section>
					<table beanclass="psdi.webclient.beans.configur.AttributesBean" id="columns_columns_table" label="属性" relationship="MAXATTRIBUTECFG">
						<tablebody displayrowsperpage="10" filterable="true" id="columns_columns_table_tablebody">
							<tablecol filterable="false" id="columns_columns_table_tablebody_1" mxevent="toggledetailstate" mxevent_desc="显示详细信息" sortable="false" type="event"/>
							<tablecol dataattribute="viewchanged" filterable="false" id="columns_columns_table_tablebody_2" ondatachange="refreshtable" sortable="false"/>
							<tablecol dataattribute="attributename" id="columns_columns_table_tablebody_3"/>
							<tablecol dataattribute="remarks" id="columns_columns_table_tablebody_4"/>
							<tablecol dataattribute="maxtype" id="columns_columns_table_tablebody_5" linkedcontrolid="columns_basic_row1_col1_3" ondatachange="refreshtable"/>
							<tablecol dataattribute="length" id="columns_columns_table_tablebody_6" ondatachange="refreshtable"/>
							<tablecol dataattribute="CLASSNAME" id="columns_columns_table_tablebody_7" ondatachange="refreshtable"/>
							<tablecol dataattribute="primarykeycolseq" id="columns_columns_table_tablebody_11" ondatachange="refreshtable"/>
							<tablecol dataattribute="required" id="columns_columns_table_tablebody_8" linkedcontrolid="columns_basic_row1_col1_6"/>
							<tablecol filterable="false" id="columns_columns_table_tablebody_10" mxevent="maxlookupmap" mxevent_desc="编辑查找图" mxevent_icon="btn_edit.gif" sortable="false" type="event"/>
							<tablecol filterable="false" id="columns_columns_table_tablebody_9" mxevent="toggledeleterow" mxevent_desc="标记要删除的行" mxevent_icon="btn_garbage.gif" sortable="false" type="event"/>
						</tablebody>
						<tabledetails id="columns_columns_table_1">
							<section id="columns_columns_table_1_grid30" label="详细信息">
								<sectionrow id="columns_row1">
									<sectioncol id="columns_row1_col1">
										<section id="columns_basic_row1_col1">
											<textbox dataattribute="attributename" id="columns_basic_row1_col1_1"/>
											<textbox dataattribute="remarks" id="columns_basic_row1_col1_2"/>
											<textbox dataattribute="maxtype" id="columns_basic_row1_col1_3" lookup="valuelist" ondatachange="resetchildren"/>
											<textbox dataattribute="length" id="columns_basic_row1_col1_4"/>
											<textbox dataattribute="scale" id="columns_basic_row1_col1_5"/>
											<checkbox dataattribute="required" id="columns_basic_row1_col1_6"/>
										</section>
									</sectioncol>
									<sectioncol id="columns_row1_col2">
										<section id="columns_basic_row1_col2">
											<textbox dataattribute="title" id="columns_basic_row1_col2_1"/>
											<textbox dataattribute="classname" id="columns_basic_row1_col2_2"/>
											<textbox applink="domainadm" dataattribute="domainid" id="columns_basic_row1_col2_3" lookup="domain" menutype="normal"/>
											<textbox dataattribute="defaultvalue" id="columns_basic_row1_col2_4"/>
											<textbox dataattribute="alias" id="columns_basic_row1_col2_5"/>
											<textbox dataattribute="viewchanged" id="columns_basic_row1_col2_6"/>
										</section>
									</sectioncol>
								</sectionrow>
							</section>
							<section id="columns_advanced" label="高级">
								<sectionrow id="columns_advanced_row1">
									<sectioncol id="columns_advanced_row1_col1">
										<section id="columns_advanced_grid1">
											<textbox dataattribute="entityname" id="columns_advanced_row1_col1_1"/>
											<textbox dataattribute="columnname" id="columns_advanced_row1_col1_2"/>
											<textbox dataattribute="sameasobject" id="columns_advanced_row1_col1_3" lookup="objectname"/>
											<textbox dataattribute="sameasattribute" id="columns_advanced_row1_col1_4" lookup="attributename"/>
											<textbox dataattribute="autokeyname" id="columns_advanced_row1_col1_5" lookup="autokey"/>
											<textbox dataattribute="searchtype" id="columns_advanced_row1_col1_6" lookup="valuelist"/>
											<checkbox dataattribute="localizable" id="columns_advanced_row1_col1_7"/>
											<textbox dataattribute="textdirection" id="columns_advanced_row1_col1_8" lookup="valuelist"/>
										</section>
									</sectioncol>
									<sectioncol id="columns_advanced_row2_col1">
										<section id="columns_advanced_grid2">
											<checkbox dataattribute="persistent" id="columns_advanced_row1_col2_1"/>
											<checkbox dataattribute="mustbe" id="columns_advanced_row1_col2_2"/>
											<checkbox dataattribute="ispositive" id="columns_advanced_row1_col2_3"/>
											<checkbox dataattribute="userdefined" id="columns_advanced_row1_col2_4"/>
											<checkbox dataattribute="canautonum" id="columns_advanced_row1_col2_5"/>
											<checkbox dataattribute="isldowner" id="columns_advanced_row1_col2_6"/>
											<textbox dataattribute="sequencename" id="columns_advanced_row1_col2_7"/>
											<textbox dataattribute="complexexpression" id="columns_advanced_row1_col2_8" lookup="valuelist"/>
										</section>
									</sectioncol>
									<sectioncol id="columns_advanced_col3">
										<section id="columns_advanced_col3a">
											<checkbox dataattribute="eauditenabled" id="columns_advanced_row1_col3_1"/>
											<checkbox dataattribute="mlsupported" id="columns_advanced_row1_col3_2"/>
											<checkbox dataattribute="mlinuse" id="columns_advanced_row1_col3_3"/>
											<checkbox dataattribute="esigenabled" id="columns_advanced_row1_col3_4"/>
											<textbox dataattribute="primarykeycolseq" id="columns_advanced_row1_col3_5"/>
											<textbox dataattribute="attributeno" id="columns_advanced_row1_col3_6"/>
											<textbox dataattribute="nextsequenceno" id="columns_advanced_row1_col3_7"/>
										</section>
									</sectioncol>
								</sectionrow>
							</section>
						</tabledetails>
						<buttongroup id="columns_columns_table_2">
							<pushbutton default="true" id="columns_columns_table_2_1" label="新建行" mxevent="addrow"/>
						</buttongroup>
					</table>
				</tab>
				<tab id="indexes" label="索引">
					<section border="true" id="indexes_header">
						<sectionrow id="indexes_header_row1">
							<sectioncol id="indexes_header_row1_col1">
								<section id="indexes_grid3">
									<multiparttextbox dataattribute="objectname" descdataattribute="description" id="indexes_grid3_1"/>
								</section>
							</sectioncol>
							<sectioncol id="indexes_header_row1_col2">
								<section id="indexes_grid3a">
									<textbox dataattribute="viewchanged" id="indexes_grid4_6"/>
								</section>
							</sectioncol>
						</sectionrow>
					</section>
					<table beanclass="psdi.webclient.beans.configur.IndexesBean" id="indexes_indexes_table" label="索引" ondatachange="resetchildren" relationship="MAXSYSINDEXES">
						<tablebody displayrowsperpage="6" filterable="true" id="indexes_indexes_table_tablebody">
							<tablecol filterable="false" id="indexes_indexes_table_tablebody_1" mxevent="toggledetailstate" mxevent_desc="显示详细信息" sortable="false" type="event"/>
							<tablecol dataattribute="viewchanged" filterable="false" id="indexes_indexes_table_tablebody_6" linkedcontrolid="indexes_row1_col2_sec1_4" sortable="false"/>
							<tablecol dataattribute="name" id="indexes_indexes_table_tablebody_2" linkedcontrolid="indexes_row1_col1_sec1_1"/>
							<tablecol dataattribute="tbname" id="indexes_indexes_table_tablebody_3" linkedcontrolid="indexes_row1_col2_sec1_1"/>
							<tablecol dataattribute="unique" id="indexes_indexes_table_tablebody_4" linkedcontrolid="indexes_row1_col2_sec1_2"/>
							<tablecol dataattribute="required" id="indexes_indexes_table_tablebody_5" linkedcontrolid="indexes_row1_col2_sec1_3"/>
							<tablecol filterable="false" id="indexes_indexes_table_tablebody_7" mxevent="toggledeleterow" mxevent_desc="标记要删除的行" mxevent_icon="btn_garbage.gif" sortable="false" type="event"/>
						</tablebody>
						<tabledetails id="indexes_indexes_table_1">
							<section id="indexes_indexes_table_1_grid30" label="详细信息">
								<sectionrow id="indexes_row1">
									<sectioncol id="indexes_row1_col1">
										<section id="indexes_row1_col1_sec1">
											<textbox dataattribute="name" defaultfocus="true" id="indexes_row1_col1_sec1_1"/>
											<textbox dataattribute="tbname" id="indexes_row1_col2_sec1_1"/>
											<checkbox dataattribute="unique" id="indexes_row1_col2_sec1_2"/>
											<checkbox dataattribute="required" id="indexes_row1_col2_sec1_3"/>
										</section>
									</sectioncol>
									<sectioncol id="indexes_row1_col2">
										<section id="indexes_row1_col2_sec1">
											<checkbox dataattribute="clusterrule" id="indexes_row1_col1_sec1_3"/>
											<checkbox dataattribute="textsearch" id="indexes_row1_col1_sec1_4"/>
											<textbox dataattribute="storagepartition" id="indexes_row1_col1_sec1_2" lookup="valuelist"/>
											<textbox dataattribute="viewchanged" id="indexes_row1_col2_sec1_4"/>
										</section>
									</sectioncol>
								</sectionrow>
							</section>
						</tabledetails>
						<buttongroup id="indexes_indexes_table_2">
							<pushbutton default="true" id="indexes_indexes_table_2_1" label="新建行" mxevent="addrow"/>
						</buttongroup>
					</table>
					<table beanclass="psdi.webclient.beans.configur.IndexesBean" id="indexes_keys_table" label="{0}列" parentdatasrc="indexes_indexes_table" parentemptylabel="列" relationship="MAXSYSKEYS" titleattributes="name">
						<sectionheader id="indexes_keys_table_sectionheader">
							<paramvalues id="indexes_keys_table_params">
								<paramvalue dataattribute="name" datasrc="indexes_indexes_table" id="indexes_keys_table_param_1" position="0"/>
							</paramvalues>
						</sectionheader>
						<tablebody displayrowsperpage="6" filterable="true" id="indexes_keys_table_tablebody">
							<tablecol filterable="false" id="indexes_keys_table_tablebody_1" mxevent="toggledetailstate" mxevent_desc="显示详细信息" sortable="false" type="event"/>
							<tablecol dataattribute="colname" id="indexes_keys_table_tablebody_2" linkedcontrolid="keys_row1_col1_sec1_1"/>
							<tablecol dataattribute="colseq" id="indexes_keys_table_tablebody_3" linkedcontrolid="keys_row1_col2_sec1_1"/>
							<tablecol dataattribute="ascending" id="indexes_keys_table_tablebody_4" linkedcontrolid="keys_row1_col1_sec1_2"/>
							<tablecol filterable="false" id="indexes_keys_table_tablebody_5" mxevent="toggledeleterow" mxevent_desc="标记要删除的行" mxevent_icon="btn_garbage.gif" sortable="false" type="event"/>
						</tablebody>
						<tabledetails id="indexes_keys_table_1">
							<section id="indexes_keys_table_1_grid30" label="详细信息">
								<textbox dataattribute="colname" defaultfocus="true" id="keys_row1_col1_sec1_1" lookup="maxcolumn"/>
								<textbox dataattribute="colseq" id="keys_row1_col2_sec1_1"/>
								<checkbox dataattribute="ascending" id="keys_row1_col1_sec1_2"/>
							</section>
						</tabledetails>
						<buttongroup id="indexes_keys_table_2">
							<pushbutton default="true" id="indexes_keys_table_2_1" label="新建行" mxevent="addrow"/>
						</buttongroup>
					</table>
				</tab>
				<tab id="relationships" label="关系">
					<section border="true" id="relationships_header">
						<sectionrow id="relationships_header_row1">
							<sectioncol id="relationships_header_row1_col1">
								<section id="relationships_grid3">
									<multiparttextbox dataattribute="objectname" descdataattribute="description" id="relationships_grid3_1"/>
								</section>
							</sectioncol>
							<sectioncol id="relationships_header_row1_col2">
								<section id="relationships_grid3a">
									<textbox dataattribute="viewchanged" id="relationships_grid4_6"/>
								</section>
							</sectioncol>
						</sectionrow>
					</section>
					<table id="relationships_relationships_table" label="关系" relationship="MAXRELATIONSHIP">
						<tablebody displayrowsperpage="15" filterable="true" id="relationships_relationships_table_tablebody">
							<tablecol filterable="false" id="relationships_relationships_table_tablebody_1" mxevent="toggledetailstate" mxevent_desc="显示详细信息" sortable="false" type="event"/>
							<tablecol dataattribute="name" id="relationships_relationships_table_tablebody_2" linkedcontrolid="relationships_row1_col1_sec1_1"/>
							<tablecol dataattribute="child" id="relationships_relationships_table_tablebody_3" linkedcontrolid="relationships_row1_col2_sec1_1"/>
							<tablecol dataattribute="whereclause" id="relationships_relationships_table_tablebody_4"/>
							<tablecol dataattribute="remarks" id="relationships_relationships_table_tablebody_5"/>
							<tablecol filterable="false" id="relationships_relationships_table_tablebody_6" mxevent="toggledeleterow" mxevent_desc="标记要删除的行" mxevent_icon="btn_garbage.gif" sortable="false" type="event"/>
						</tablebody>
						<tabledetails id="relationships_relationships_table_1">
							<section id="relationships_relationships_table_1_grid30" label="详细信息">
								<sectionrow id="relationships_row1">
									<sectioncol id="relationships_row1_col1">
										<section id="relationships_row1_col1_sec1">
											<textbox dataattribute="name" defaultfocus="true" id="relationships_row1_col1_sec1_1"/>
											<multilinetextbox columns="30" dataattribute="whereclause" id="relationships_row1_col1_sec1_2" rows="4"/>
										</section>
									</sectioncol>
									<sectioncol id="relationships_row1_col2">
										<section id="relationships_row1_col2_sec1">
											<textbox dataattribute="child" id="relationships_row1_col2_sec1_1" lookup="objectname"/>
											<multilinetextbox columns="30" dataattribute="remarks" id="relationships_row1_col2_sec1_2" rows="4"/>
										</section>
									</sectioncol>
								</sectionrow>
							</section>
						</tabledetails>
						<buttongroup id="relationships_relationships_table_2">
							<pushbutton default="true" id="relationships_relationships_table_2_1" label="新建行" mxevent="addrow"/>
						</buttongroup>
					</table>
				</tab>
			</tabgroup>
		</clientarea>
		<include controltoclone="pageFooter" id="INCLUDE-pageFooter"/>
	</page>

	<dialog beanclass="psdi.webclient.beans.configur.SaveChildOnlyDialogBean" id="domainlink" label="域链接" relationship="MAXDOMAINLINK">
		<section border="true" datasrc="MAINRECORD" id="domainlink_header">
			<sectionrow id="domainlink_header_row1">
				<sectioncol id="domainlink_header_row1_col1">
					<section id="domainlink_grid3">
						<multiparttextbox dataattribute="objectname" descdataattribute="description" descinputmode="readonly" id="domainlink_grid3_1" inputmode="readonly"/>
					</section>
				</sectioncol>
			</sectionrow>
		</section>
		<table id="domainlink_table" label="域链接">
			<tablebody displayrowsperpage="20" filterable="true" id="domainlink_table_tablebody" orderby="source">
				<tablecol dataattribute="attributename" id="domainlink_tablebody_1" lookup="attributename"/>
				<tablecol dataattribute="domainid" id="domainlink_tablebody_2" lookup="domain"/>
				<tablecol filterable="false" id="domainlink_table_tablebody_delete" mxevent="toggledeleterow" mxevent_desc="标记要删除的行" mxevent_icon="btn_garbage.gif" sortable="false" type="event"/>
			</tablebody>
			<buttongroup id="domainlink_2">
				<pushbutton default="true" id="domainlink_columns_table_2_1" label="新建行" mxevent="addrow"/>
			</buttongroup>
		</table>
		<buttongroup id="domainlink_3">
			<pushbutton default="true" id="domainlink_3_1" label="确定" mxevent="dialogok"/>
			<pushbutton id="domainlink_3_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>

	<dialog beanclass="psdi.webclient.beans.configur.ExecuteRefreshBean" id="amountfmt" label="字段长度和格式" mboname="AMOUNTFORMAT" width="300">
		<section id="amountfmt_grid1">
			<sectionrow id="amountfmt_grid1_1">
				<sectioncol id="amountfmt_grid1_1_1">
					<section id="amountfmt_grid1_1_1_grid2">
						<textbox dataattribute="LENGTH" id="amountfmt_grid1_1_1_grid2_1"/>
						<textbox dataattribute="SCALE" id="amountfmt_grid1_1_1_grid2_2"/>
						<textbox dataattribute="INTEGERLENGTH" id="amountfmt_grid1_1_1_grid2_3"/>
						<textbox dataattribute="SMALLINTLENGTH" id="amountfmt_grid1_1_1_grid2_4"/>
					</section>
				</sectioncol>
			</sectionrow>
		</section>
		<buttongroup id="amountfmt_2">
			<pushbutton default="true" id="amountfmt_2_1" label="确定" mxevent="dialogok"/>
			<pushbutton id="amountfmt_2_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>

	<dialog beanclass="psdi.webclient.beans.configur.SaveChildOnlyDialogBean" id="maxlookupmap" label="查找关键地图" mboname="MAXLOOKUPMAP">
		<section border="true" id="maxlookupmap_header">
			<sectionrow id="maxlookupmap_header_row1">
				<sectioncol id="maxlookupmap_header_row1_col1">
					<section id="maxlookupmap_grid3">
						<multiparttextbox dataattribute="objectname" datasrc="MAINRECORD" descdataattribute="description" descinputmode="readonly" id="maxlookupmap_grid3_1" inputmode="readonly"/>
						<multiparttextbox dataattribute="attributename" datasrc="columns_columns_table" descdataattribute="title" descinputmode="readonly" id="maxlookupmap_grid3_2" inputmode="readonly"/>
					</section>
				</sectioncol>
			</sectionrow>
		</section>
		<table id="maxlookupmap_table" label="查找图">
			<tablebody displayrowsperpage="20" filterable="true" id="maxlookupmap_table_tablebody" orderby="source">
				<tablecol filterable="false" id="maxlookupmap_tablebody_0" mxevent="toggledetailstate" mxevent_desc="显示详细信息" sortable="false" type="event"/>
				<tablecol dataattribute="targetattr" id="maxlookupmap_tablebody_1" linkedcontrolid="maxlookupmap_detail_3" lookup="attributename"/>
				<tablecol dataattribute="source" id="maxlookupmap_tablebody_2" linkedcontrolid="maxlookupmap_detail_6" lookup="objectname"/>
				<tablecol dataattribute="sourcekey" id="maxlookupmap_tablebody_3" linkedcontrolid="maxlookupmap_detail_7" lookup="attributename"/>
				<tablecol dataattribute="seqnum" id="maxlookupmap_tablebody_4" linkedcontrolid="maxlookupmap_detail_8"/>
				<tablecol dataattribute="allownull" id="maxlookupmap_tablebody_5" linkedcontrolid="maxlookupmap_detail_9">
					<checkbox dataattribute="allownull" id="maxlookupmap_tablebody_5_box"/>
				</tablecol>
				<tablecol filterable="false" id="maxlookupmap_table_tablebody_delete" mxevent="toggledeleterow" mxevent_desc="标记要删除的行" mxevent_icon="btn_garbage.gif" sortable="false" type="event"/>
			</tablebody>
			<tabledetails id="maxlookupmap_table_tabledetail">
				<section border="true" height="300" id="maxlookupmap_table_tabledetail_1">
					<sectionrow id="maxlookupmap_detail_row_1">
						<sectioncol id="maxlookupmap_detail_row1_col1">
							<section id="maxlookupmap_detail_row1_col1_section">
								<textbox dataattribute="targetattr" id="maxlookupmap_detail_3" lookup="attributename"/>
								<textbox dataattribute="source" id="maxlookupmap_detail_6" lookup="objectname"/>
								<textbox dataattribute="sourcekey" id="maxlookupmap_detail_7" lookup="attributename"/>
								<textbox dataattribute="seqnum" id="maxlookupmap_detail_8"/>
								<checkbox dataattribute="allownull" id="maxlookupmap_detail_9"/>
							</section>
						</sectioncol>
					</sectionrow>
				</section>
			</tabledetails>
			<buttongroup id="maxlookupmap_2">
				<pushbutton default="true" id="maxlookupmap_columns_table_2_1" label="新建行" mxevent="addrow"/>
			</buttongroup>
		</table>
		<buttongroup id="maxlookupmap_3">
			<pushbutton default="true" id="maxlookupmap_3_1" label="确定" mxevent="dialogok"/>
			<pushbutton id="maxlookupmap_3_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>

	<dialog beanclass="psdi.webclient.system.beans.QbeBean" id="searchmore" inputmode="query" label="更多搜索字段">
		<section id="qbe_grid2">
			<multiparttextbox dataattribute="objectname" descdataattribute="description" descinputmode="query" id="qbe_grid2_1_1_grid3_1" inputmode="query"/>
			<multiparttextbox dataattribute="servicename" descdataattribute="maxservice.description" descinputmode="query" id="qbe_grid2_1_1_grid3_3" inputmode="query"/>
			<textbox dataattribute="maxattributecfg.attributename" id="qbe_grid2_1_1_grid3_4" inputmode="query"/>
			<textbox dataattribute="internal" id="qbe_grid2_1_1_grid3_5" inputmode="query"/>
		</section>
		<buttongroup id="qbe_grid5_1_1_grid6">
			<pushbutton default="true" id="qbe_grid5_1_1_grid6_1" label="查找" mxevent="dialogok"/>
			<pushbutton id="qbe_restoreappdefault_button" label="恢复应用程序缺省值" mxevent="qbeclear"/>
			<pushbutton id="qbe_revisebutton" label="修订" menutype="SEARCHMOREREVISE" targetid="searchmore"/>
			<pushbutton id="qbe_grid5_1_1_grid6_3" label="取消" mxevent="qbecancel"/>
		</buttongroup>
	</dialog>

	<dialog beanclass="psdi.webclient.beans.configur.RunConfigDBBean" id="configdb_nonstruct" label="非结构数据库配置" mboname="PROCESSMONITOR" width="500">
		<section id="cdb_nonstruct_grid1">
			<sectionrow id="cdb_nonstruct_grid1_row0">
				<sectioncol id="cdb_nonstruct_grid1_row0_col1">
					<section id="cdb_nonstruct_grid1_r0c1_section">
						<helpgrid id="cdb_nonstruct_grid1_1" innerhtml="单击下面的按钮可运行非结构数据库配置。"/>
					</section>
				</sectioncol>
			</sectionrow>
		</section>
		<section id="cdb_nonstruct_grid1a">
			<sectionrow id="cdb_nonstruct_grid1a_row0">
				<sectioncol id="cdb_nonstruct_grid1a_row0_col1">
					<section id="cdb_nonstruct_grid1a_r0c1_section">
						<pushbutton id="cdb_nonstruct_grid1_2" label="开始配置数据库" mxevent="configdb_nonstruct_start"/>
						<pushbutton id="cdb_nonstruct_grid1_3" label="刷新状态" mxevent="refreshStatus"/>
					</section>
				</sectioncol>
			</sectionrow>
		</section>
		<section id="cdb_nonstruct_grid2">
			<multilinetextbox columns="40" dataattribute="status" id="cdb_nonstruct_grid2_1" inputmode="readonly" rows="10"/>
		</section>
		<buttongroup id="cdb_nonstruct_2">
			<pushbutton default="true" id="cdb_nonstruct_2_1" label="确定" mxevent="dialogok"/>
		</buttongroup>
	</dialog>

	<dialog beanclass="psdi.webclient.beans.configur.ExecuteNumTaxBean" id="numtax" label="添加/修改税款类型" relationship="MAXVARINPUTNUMTAX">
		<section id="numtax_1">
			<textbox dataattribute="NUMBEROFTAXCODES0" id="numtax_1_0" inputmode="readonly"/>
			<textbox dataattribute="NUMBEROFTAXTEMP" id="numtax_1_1"/>
		</section>
		<buttongroup id="numtax_2">
			<pushbutton clickmessage="Add Tax Options have been saved." default="true" id="numtax_2_1" label="确定" mxevent="dialogok"/>
			<pushbutton id="numtax_2_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>

	<dialog beanclass="psdi.webclient.beans.configur.RunConfigDBBean" id="configdb_struct" label="结构数据库配置" mboname="PROCESSMONITOR" width="500">
		<section id="cdb_struct_grid1a">
			<sectionrow id="cdb_struct_grid1_row0">
				<sectioncol id="cdb_struct_grid1_row0_col1">
					<section id="cdb_struct_grid1_r0c1_section">
						<helpgrid id="cdb_struct_grid1_1" innerhtml="您必须具有当前备份才能配置数据库。 如果在执行结构变更时遇到任何问题，那么可能需要从备份恢复数据库。 单击下面的按钮可运行结构数据库配置。"/>
						<checkbox dataattribute="confirm" id="cdb_struct_havebackup_box" label="您是否具有当前备份"/>
					</section>
				</sectioncol>
			</sectionrow>
		</section>
		<section id="cdb_struct_grid1b">
			<sectionrow id="cdb_struct_grid1b_row0">
				<sectioncol id="cdb_struct_grid1b_row0_col1">
					<section id="cdb_struct_grid1b_r0c1_section">
						<pushbutton id="cdb_struct_grid1b_2" label="开始配置数据库" mxevent="configdb_struct_start"/>
						<pushbutton id="cdb_struct_grid1b_3" label="刷新状态" mxevent="refreshStatus"/>
					</section>
				</sectioncol>
			</sectionrow>
		</section>
		<section id="cdb_struct_grid2">
			<multilinetextbox columns="40" dataattribute="status" id="cdb_struct_grid2_1" inputmode="readonly" rows="10"/>
		</section>
		<buttongroup id="cdb_struct_2">
			<pushbutton default="true" id="cdb_struct_2_1" label="确定" mxevent="dialogok"/>
		</buttongroup>
	</dialog>

	<dialog id="services" label="服务" mboname="maxservice">
		<table id="maxservice" selectmode="single">
			<tablebody displayrowsperpage="10" filterable="true" filterexpanded="true" id="maxservice_tablebody">
				<tablecol filterable="false" id="maxservice_tablebody_col_1" mxevent="toggledetailstate" mxevent_desc="显示详细信息" sortable="false" type="event"/>
				<tablecol dataattribute="servicename" id="maxservice_tablebody_col_2" linkedcontrolid="maxservice_detail_2" sortable="true"/>
				<tablecol dataattribute="description" id="maxservice_tablebody_col_3" linkedcontrolid="maxservice_detail_3" sortable="true"/>
				<tablecol dataattribute="initorder" id="maxservice_tablebody_col_4" linkedcontrolid="maxservice_detail_4" sortable="true"/>
				<tablecol dataattribute="active" id="maxservice_tablebody_col_5" linkedcontrolid="maxservice_detail_5">
					<checkbox dataattribute="active" id="maxservice_tablebody_col_5_checkbox"/>
				</tablecol>
				<tablecol filterable="false" id="maxservice_tablebody_delete" mxevent="instantdelete" mxevent_desc="删除" mxevent_icon="btn_garbage.gif" sortable="false" type="event"/>
			</tablebody>
			<tabledetails id="maxservice_table_tabledetail">
				<section border="true" height="300" id="maxservice_table_tabledetail_1">
					<sectionrow id="maxservice_detail_row_1">
						<sectioncol id="maxservice_detail_row1_col1">
							<section id="maxservice_detail_row1_col1_section">
								<multiparttextbox dataattribute="servicename" descdataattribute="description" id="maxservice_detail_1"/>
								<textbox dataattribute="classname" id="maxservice_detail_3"/>
								<textbox dataattribute="initorder" id="maxservice_detail_4"/>
								<checkbox dataattribute="active" id="maxservice_detail_5"/>
								<checkbox dataattribute="internal" id="maxservice_detail_6"/>
							</section>
						</sectioncol>
					</sectionrow>
				</section>
			</tabledetails>
			<buttongroup id="maxservice_2">
				<pushbutton default="true" id="maxservice_columns_table_2_1" label="新建行" mxevent="addrow"/>
			</buttongroup>
		</table>
		<buttongroup id="maxservice_3">
			<pushbutton default="true" id="maxservice_3_1" label="确定" mxevent="dialogok"/>
		</buttongroup>
	</dialog>

	<dialog beanclass="psdi.webclient.beans.configur.RunConfigDBBean" id="configdb_configuring" label="查看数据库配置进度" mboname="PROCESSMONITOR" width="500">
		<section id="cdb_config_grid1">
			<sectionrow id="cdb_config_grid1_row0">
				<sectioncol id="cdb_config_grid1_row0_col1">
					<section id="cdb_config_grid1_r0c1_section">
						<pushbutton id="cdb_config_grid1_2" label="刷新状态" mxevent="refreshStatus"/>
					</section>
				</sectioncol>
			</sectionrow>
		</section>
		<section id="cdb_config_grid2">
			<multilinetextbox columns="40" dataattribute="status" id="cdb_config_grid2_1" inputmode="readonly" rows="10"/>
		</section>
		<buttongroup id="cdb_config_2">
			<pushbutton default="true" id="cdb_config_2_1" label="确定" mxevent="dialogok"/>
		</buttongroup>
	</dialog>

	<dialog id="esigoption" label="管理电子签名操作" mboname="MAXAPPS" orderby="description">
		<table id="esigoption_app_table" label="应用程序" orderby="description">
			<tablebody displayrowsperpage="7" filterable="true" id="esigoption_app_table_tablebody">
				<tablecol dataattribute="description" id="esigoption_app_table_tablebody_1" inputmode="readonly"/>
			</tablebody>
		</table>
		<table id="esigoption_sigo_table" label="“{0}”的选项" orderby="description" parentdatasrc="esigoption" parentemptylabel="选项" relationship="SIGOWITHOUTREAD">
			<sectionheader id="main_esigoption_sigo_table_sectionheader">
				<paramvalues id="main_esigoption_sigo_table_1_sec_params" property="label">
					<paramvalue dataattribute="description" id="main_esigoption_sigo_table_1_sec_param_0" position="0"/>
				</paramvalues>
			</sectionheader>
			<tablebody displayrowsperpage="7" filterable="true" id="esigoption_sigo_table_tablebody">
				<tablecol dataattribute="description" id="esigoption_sigo_table_tablebody_1" inputmode="readonly"/>
				<tablecol dataattribute="esigenabled" id="esigoption_sigo_table_tablebody_2" sortable="false">
					<checkbox dataattribute="esigenabled" id="esigoption_sigo_table_tablebody_2_box"/>
				</tablecol>
			</tablebody>
		</table>
		<buttongroup id="esigoption_table_buttongroup">
			<pushbutton default="true" id="esigoption_2_1" label="确定" mxevent="dialogok"/>
			<pushbutton id="esigoption_2_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>

	<dialog beanclass="psdi.webclient.beans.configur.GLExecuteRefreshBean" id="glconfig" label="GL 科目配置" mboname="GLCONFIGURE" orderby="glorder">
		<section id="glconfig_grid1">
			<sectionrow id="glconfig_grid1_1">
				<sectioncol id="glconfig_grid1_1_1">
					<table id="glconfig_table" label="GL 科目配置">
						<tablebody displayrowsperpage="15" id="glconfig_table_tablebody">
							<tablecol filterable="false" id="glconfig_table_tablebody_1" mxevent="toggledetailstate" mxevent_desc="显示详细信息" sortable="false" type="event"/>
							<tablecol dataattribute="glaccountfield" id="glconfig_table_tablebody_2" linkedcontrolid="glconfig_table_details_1_1"/>
							<tablecol dataattribute="gllength" id="glconfig_table_tablebody_3" linkedcontrolid="glconfig_table_details_1_2"/>
							<tablecol dataattribute="gltype" id="glconfig_table_tablebody_4" linkedcontrolid="glconfig_table_details_1_3"/>
							<tablecol dataattribute="mandatory" id="glconfig_table_tablebody_5" linkedcontrolid="glconfig_table_details_1_4"/>
							<tablecol dataattribute="delimiter" id="glconfig_table_tablebody_6" linkedcontrolid="glconfig_table_details_1_5"/>
							<tablecol filterable="false" id="glconfig_table_tablebody_7" mxevent="toggledeleterow" mxevent_desc="标记要删除的行" mxevent_icon="btn_garbage.gif" sortable="false" type="event"/>
						</tablebody>
						<tabledetails id="glconfig_table_details">
							<section id="glconfig_table_details_1" label="详细信息">
								<sectionrow id="glconfig_row1">
									<sectioncol id="glconfig_row1_col1">
										<section id="glconfig_row1_col1_sec1">
											<textbox dataattribute="glaccountfield" defaultfocus="true" id="glconfig_table_details_1_1"/>
											<textbox dataattribute="gllength" id="glconfig_table_details_1_2"/>
											<textbox dataattribute="gltype" id="glconfig_table_details_1_3" lookup="valuelist"/>
										</section>
									</sectioncol>
									<sectioncol id="glconfig_row1_col2">
										<section id="glconfig_row1_col2_sec1">
											<checkbox dataattribute="mandatory" id="glconfig_table_details_1_4"/>
											<textbox dataattribute="delimiter" id="glconfig_table_details_1_5"/>
											<textbox dataattribute="glorder" id="glconfig_table_details_1_6"/>
										</section>
									</sectioncol>
								</sectionrow>
							</section>
						</tabledetails>
						<buttongroup id="glconfig_table_buttongroup">
							<pushbutton default="true" id="glconfig_table_buttongroup_1" label="新建行" mxevent="addrow"/>
						</buttongroup>
					</table>
				</sectioncol>
			</sectionrow>
		</section>
		<buttongroup id="glconfig_2">
			<pushbutton default="true" id="glconfig_2_1" label="确定" mxevent="dolongop"/>
			<pushbutton id="glconfig_2_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>

	<dialog datasrc="MAINRECORD" id="skipatt" label="排除属性数据" width="500">
		<helpgrid id="skipatt_help" innerhtml="指定要在复制过程中排除的用户定义属性数据。通过使用表达式构建器，可以应用先前定义的任何条件以确定何时排除某个属性。您指定要从对象中排除的属性数据时，还将从所有扩展了该对象的数据库视图中排除该属性数据。例如，如果您复制工单活动对象并排除了工单对象的某个用户定义属性，那么由于工单活动对象扩展工单对象，因此将从工单活动对象中排除该属性数据。" morehelp="com.ibm.mbs.doc,configur/t_exclude_user-defined_attributes_duplicate_objects.html"/>
		<table id="skipatt_app_table" label="属性" orderby="attributename" relationship="SKIPATT">
			<tablebody displayrowsperpage="10" filterable="true" id="skipatt_app_table_tablebody">
				<tablecol filterable="false" id="skipatt_app_table_tablebody_1" mxevent="toggledetailstate" mxevent_desc="显示详细信息" sortable="false" type="event"/>
				<tablecol dataattribute="attributename" id="skipatt_app_table_tablebody_2" label="属性" lookup="attributename"/>
				<tablecol dataattribute="maxattributecfg.title" id="skipatt_app_table_tablebody_3" inputmode="readonly" label="描述"/>
				<tablecol applink="condexpmgr" dataattribute="conditionnum" id="skipatt_app_table_tablebody_4" label="条件" lookup="conditionexp" menutype="normal"/>
				<tablecol dataattribute="maxattributecfg.viewchanged" id="skipatt_app_table_tablebody_5" inputmode="readonly" label="属性状态"/>
				<tablecol filterable="false" id="skipatt_app_table_tablebody_6" mxevent="toggledeleterow" mxevent_desc="标记要删除的行" mxevent_icon="btn_garbage.gif" sortable="false" type="event"/>
			</tablebody>
			<tabledetails id="skipatt_table_tabledetail">
				<section border="true" id="skipatt_table_tabledetail_1">
					<sectionrow id="skipatt_detail_row_1">
						<sectioncol id="skipatt_detail_row1_col1">
							<section id="skipatt_detail_row1_col1_section">
								<textbox dataattribute="objectname" id="skipatt_detail_1" inputmode="readonly" label="对象"/>
								<textbox dataattribute="attributename" id="skipatt_detail_2" label="属性" lookup="attributename"/>
								<textbox dataattribute="maxattributecfg.title" id="skipatt_detail_3" inputmode="readonly" label="描述"/>
								<textbox applink="condexpmgr" dataattribute="conditionnum" id="skipatt_detail_4" label="条件" lookup="conditionexp" menutype="normal"/>
								<textbox dataattribute="maxattributecfg.viewchanged" id="skipatt_detail_5" inputmode="readonly" label="属性状态"/>
							</section>
						</sectioncol>
					</sectionrow>
				</section>
			</tabledetails>
			<buttongroup id="skipatt_table_buttongroup">
				<pushbutton id="skipatt_table_buttongroup_1" label="添加属性" mxevent="addskipatt"/>
				<pushbutton default="true" id="skipatt_table_buttongroup_2" label="新建行" mxevent="addrow"/>
			</buttongroup>
		</table>
		<buttongroup id="skipatt_buttongroup">
			<pushbutton default="true" id="skipatt_2_1" label="确定" mxevent="dialogok"/>
			<pushbutton id="skipatt_2_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>

	<dialog id="drpoldtab" label="删除备份表" mboname="DRPOLDTAB">
		<section id="drpoldtab_grid1">
			<statictext id="drpoldtab_grid1_1" label="下面的列表已使用它们的备份数据重新装入。"/>
			<statictext id="drpoldtab_grid1_2" label="选择其备份表应删除的表，然后单击“确定”即可删除。"/>
		</section>
		<table id="drpoldtab_table" inputmode="readonly" label="表名称" orderby="tablename" selectmode="multiple">
			<tablebody displayrowsperpage="15" filterable="true" id="drpoldtab_table_tablebody">
				<tablecol dataattribute="tablename" filterable="false" id="drpoldtab_table_tablebody_1" mxevent="toggleselectrow" mxevent_desc="选择行{0}" sortable="false" type="event"/>
				<tablecol dataattribute="tablename" id="drpoldtab_table_tablebody_2" mxevent="selectrecord" mxevent_desc="删除 %1 的备份表"/>
				<tablecol dataattribute="maxobjectcfg.description" id="drpoldtab_table_tablebody_3"/>
			</tablebody>
		</table>
		<buttongroup id="drpoldtab_table_buttongroup">
			<pushbutton default="true" id="drpoldtab_2_1" label="确定" mxevent="dialogok"/>
			<pushbutton id="drpoldtab_2_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>

	<dialog datasrc="MAINRECORD" id="restrict" label="限制属性">
		<section border="true" id="restrict_header">
			<sectionrow id="restrict_header_row1">
				<sectioncol id="restrict_header_row1_col1">
					<section id="restrict_grid3">
						<multiparttextbox dataattribute="objectname" descdataattribute="description" id="restrict_grid3_1"/>
					</section>
				</sectioncol>
			</sectionrow>
		</section>
		<table id="restrict_app_table" label="属性" relationship="MAXATTRIBUTECFG">
			<tablebody displayrowsperpage="7" filterable="true" id="restrict_app_table_tablebody">
				<tablecol dataattribute="attributename" id="restrict_app_table_tablebody_1" inputmode="readonly"/>
				<tablecol dataattribute="title" id="restrict_app_table_tablebody_2" inputmode="readonly"/>
				<tablecol dataattribute="restricted" id="restrict_app_table_tablebody_3">
					<checkbox dataattribute="restricted" id="restrict_app_table_tablebody_3_box"/>
				</tablecol>
			</tablebody>
		</table>
		<buttongroup id="restrict_table_buttongroup">
			<pushbutton default="true" id="restrict_2_1" label="确定" mxevent="dialogok"/>
			<pushbutton id="restrict_2_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>

	<dialog beanclass="psdi.webclient.system.beans.ViewBookmarksBean" id="searchbook" label="我的书签" mboname="MAXOBJECTCFG">
		<table id="bookmarks_table" inputmode="readonly" label="对象" selectmode="single">
			<tablebody displayrowsperpage="20" filterable="true" id="bookmarks_table_tablebody">
				<tablecol dataattribute="objectname" id="bookmarks_tablebody_1" mxevent="selectrecord" type="link"/>
				<tablecol dataattribute="description" id="bookmarks_table_tablebody_2"/>
				<tablecol filterable="false" id="bookmarks_table_tablebody_delete" mxevent="instantdelete" mxevent_desc="删除" mxevent_icon="btn_delete.gif" sortable="false" type="event"/>
			</tablebody>
		</table>
		<buttongroup id="bookmarks_2">
			<pushbutton default="true" id="bookmarks_2_1" label="确定" mxevent="dialogok"/>
		</buttongroup>
	</dialog>

	<dialog datasrc="MAINRECORD" id="addskipatt" label="选择属性">
		<table beanclass="psdi.webclient.beans.configur.DlgSkipAttributeSelect" id="addskipatt_table" label="属性" orderby="attributename" relationship="SKIPSELECT" selectmode="multiple">
			<tablebody displayrowsperpage="15" filterable="true" id="addskipatt_table_tablebody" inputmode="readonly">
				<tablecol filterable="false" id="addskipatt_table_tablebody_1" mxevent="toggleselectrow" sortable="false" type="event"/>
				<tablecol dataattribute="attributename" id="addskipatt_table_tablebody_2" label="属性"/>
				<tablecol dataattribute="title" id="addskipatt_table_tablebody_3" label="描述"/>
			</tablebody>
		</table>
		<buttongroup id="addskipatt_2">
			<pushbutton default="true" id="addskipatt_2_1" label="确定" mxevent="copyAttributes" targetid="addskipatt_table"/>
			<pushbutton id="addskipatt_2_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>
	<configurationblock id="datastore_configurationblock">
	</configurationblock>
</presentation>
```
