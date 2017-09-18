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
								<label id="lb4" cssclass="tht" image="nav_icon_export.gif" imagefalse="tablebtn_download_off.gif" mxevent="migrate" msggroup="tableinfo" msgkey="cntrlTableLblDownload" clickablestate="canDownload" hidewhen="@{download}==false" textcss="@{textcss}" includeuistatus="false" imagevalign="middle"/>
								<!--shoukaiseki add start-->
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
```Jsp
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
		link=wcs.getMaximoRequestContextURL() + "/webclient/help/migrate.jsp?sks_download=false&_tbldnld=" + control.getId() + "&" + wcs.getUISessionUrlParameter() + wcs.getCSRFTokenParameter();
		link=wcs.getMaximoRequestContextURL() + "/webclient/help/migrate.jsp?sks_download=true&_tbldnld=" + control.getId() + "&" + wcs.getUISessionUrlParameter() + wcs.getCSRFTokenParameter();
		mxevent="";
	}
	//shoukaiseki modify end
```

### 新增webclient/help/migrate.jsp

