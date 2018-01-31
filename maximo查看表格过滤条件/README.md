# maxiom查看表格过滤条件

<br>
事例如下图:

列表初始图片

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%9F%A5%E7%9C%8B%E8%A1%A8%E6%A0%BC%E8%BF%87%E6%BB%A4%E6%9D%A1%E4%BB%B6/img/002.png)

点击之后效果图

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%9F%A5%E7%9C%8B%E8%A1%A8%E6%A0%BC%E8%BF%87%E6%BB%A4%E6%9D%A1%E4%BB%B6/img/003.png)


例如查看出库单中选择库房的过滤条件

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%9F%A5%E7%9C%8B%E8%A1%A8%E6%A0%BC%E8%BF%87%E6%BB%A4%E6%9D%A1%E4%BB%B6/img/004.png)

过滤信息如下

```Sql
mboname=LOCATIONS
completeWhere=((status not in ( '中断' , '停止使用' , '不活动' , '缺少' , '封存' , '取消' , '报废' ) and ((( 'XJM'  is null or  'XJM' ='') or (( 'XJM'  is not null or  'XJM'  !='') and siteid= 'XJM' ))   and type in ( '库房' ,  '承运人' ,  '员工' ,  '保存' ))) and ((siteid= 'XJM' )) and location in (select location from inventory where locations.siteid = inventory.siteid and locations.location = inventory.location and itemnum= '00600400090'  and status in ( '活动' ,  '暂挂折旧' ))) AND (rownum<=1000)


ownermbo=MATUSETRANS
relationname=__LOCATIONSSTORELOClist
relationship=(status not in ( '中断' , '停止使用' , '不活动' , '缺少' , '封存' , '取消' , '报废' ) and ((( 'XJM'  is null or  'XJM' ='') or (( 'XJM'  is not null or  'XJM'  !='') and siteid= 'XJM' ))   and type in ( '库房' ,  '承运人' ,  '员工' ,  '保存' ))) and ((siteid= 'XJM' )) and location in (select location from inventory where locations.siteid = inventory.siteid and locations.location = inventory.location and itemnum= '00600400090'  and status in ( '活动' ,  '暂挂折旧' ))
```


### 另外一个实用的例子

检修单位字段,使用的是alndomain域,但是没有在数据库配置中绑定域名,而是在类中指定的,如下图查看字段信息

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%9F%A5%E7%9C%8B%E8%A1%A8%E6%A0%BC%E8%BF%87%E6%BB%A4%E6%9D%A1%E4%BB%B6/img/005.png)

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%9F%A5%E7%9C%8B%E8%A1%A8%E6%A0%BC%E8%BF%87%E6%BB%A4%E6%9D%A1%E4%BB%B6/img/006.png)

上图字段帮助为加强版的效果,能看到很多信息 [下载地址](https://github.com/shoukaiseki/maximocomponent/blob/master/maximo%E5%AD%97%E6%AE%B5%E5%B8%AE%E5%8A%A9%E5%8A%A0%E5%BC%BA%E7%89%88/README.md)

能够看出,班组字段是能看到绑定的domainid为'QXFXZB',也绑定了class

检修单位字段却没有在数据库配置中绑定domainid,而是在类中设置了

这种情况下,我们可以用查看表格过滤条件组件去查看,效果如下

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%9F%A5%E7%9C%8B%E8%A1%A8%E6%A0%BC%E8%BF%87%E6%BB%A4%E6%9D%A1%E4%BB%B6/img/007.png)

我们可以发现,domainid为 DA_JXDW

```Sql
mboname=ALNDOMAIN
completeWhere=(domainid='DA_JXDW' and (siteid is null or siteid='NMDAGF')) AND (rownum<=1000)


ownermbo=WORKORDER
relationname=__ALNDOMAINDA_JXDWlist
relationship= domainid='DA_JXDW' and (siteid is null or siteid='NMDAGF')
```




## 增加按钮标题


### oracle 数据库

```Sql
SET DEFINE OFF;
Insert into MAXMESSAGES (MSGKEY,MSGGROUP,VALUE,TITLE,DISPLAYMETHOD,OPTIONS,BUTTONTEXT,MAXMESSAGESID,MSGID,EXPLANATION,ADMINRESPONSE,OPERATORRESPONSE,SYSTEMACTION,PREFIX,ROWSTAMP) values ('showCompleteWhere','shoukaiseki','查看过滤条件',null,'STATUS',0,null, (MAXMESSAGESSEQ.nextval),'BMXAA9123E',null,null,null,null,1,2900247);
```

## 建立虚拟表 SHOWCOMPLETEWHERE(显示过滤条件)

表中只需要有个 COMPLETEWHERE(过滤条件) 字段即可

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E6%9F%A5%E7%9C%8B%E8%A1%A8%E6%A0%BC%E8%BF%87%E6%BB%A4%E6%9D%A1%E4%BB%B6/img/001.png)

## library.xml 增加
```Xml
	<dialog beanclass="org.shoukaiseki.webclient.beans.showcompletewhere.ShowCompleteWhereDataBean" id="showcompletewhere" mboname="SHOWCOMPLETEWHERE" label="查看过滤条件">
		<section id="showcompletewhere_1">
			<multilinetextbox dataattribute="COMPLETEWHERE" id="showcompletewhere_textbox_001" inputmode="readonly" columns="30" rows="5"/>
		</section>
		<buttongroup id="showcompletewhere_2">
			<pushbutton id="showcompletewhere_2_2" label="关闭" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>
```

## control-registry.xml 修改
类名修改,按钮增加 按钮增加部分 含有 shoukaiseki 注释

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

								<!--shoukaiseki add start-->
								<image id="lb4asuswhere" src="filesearch.gif" mxevent="showcompletewhere" msggroup="shoukaiseki" msgkey="showCompleteWhere" hidewhen="false" />
								<!--shoukaiseki add end-->

								<label id="lb4" cssclass="tht" image="tablebtn_download.gif" imagefalse="tablebtn_download_off.gif" mxevent="download" msggroup="tableinfo" msgkey="cntrlTableLblDownload" clickablestate="canDownload" hidewhen="@{download}==false" textcss="@{textcss}" includeuistatus="false" imagevalign="middle"/>
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

## java类 查看 maxsrc 目录	
