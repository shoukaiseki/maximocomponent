set define off;


----------------ApplicationDeleteScript----------------

delete from maxmodules  where MODULE='SD';
delete from maxapps where APP= 'TESTCM';
delete from maxpresentation where APP= 'TESTCM';
delete from maxlabels where app='TESTCM';
delete from Maxmenu where KEYVALUE='TESTCM' or MODULEAPP='TESTCM';
delete from Sigoption where APP='TESTCM';


----------------ApplicationInsertScript----------------



----------------CreateModuleScript----------------

insert into maxmodules ( MODULE, DESCRIPTION, MAXMODULESID) values ( 'SD', '服务台', MAXMODULESSEQ.nextval);
insert into maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'MODULE', 'SD', 10990, 0, 'MODULE', 'SD', null, null, 1, 'modimg_sd.gif', null, null, MAXMENUSEQ.nextval);


----------------CreateAppScript----------------

insert into maxapps ( APP, DESCRIPTION, APPTYPE, RESTRICTIONS, ORDERBY, ORIGINALAPP, CUSTAPPTYPE, MAINTBNAME, MAXAPPSID, ISMOBILE, LOCKENABLED) values ( 'TESTCM', '测试方法调用', 'RUN', null, null, 'CUSTAPP', null, 'TESTCALLMETHOD', MAXAPPSSEQ.nextval, null, 0);


----------------CreateMaxmenuScript----------------

insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'APPMENU', 'TESTCM', 12, 0, 'OPTION', 'DELETE', null, null, 1, null, null, '主要', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'APPMENU', 'TESTCM', 13, 0, 'OPTION', 'BOOKMARK', null, null, 1, null, null, '主要', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'APPTOOL', 'TESTCM', 2, 0, 'SEP', 'AT2', null, null, 1, null, null, null, MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'SEARCHMENU', 'TESTCM', 10, 0, 'HEADER', 'SM10', '高级搜索', null, 1, 'atb_search.gif', null, '所有', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'APPMENU', 'TESTCM', 11, 0, 'OPTION', 'DUPLICATE', null, null, 1, null, null, '主要', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'SEARCHMENU', 'TESTCM', 20, 0, 'HEADER', 'SM20', '保存查询', null, 1, 'atb_save.gif', null, '所有', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'SEARCHMENU', 'TESTCM', 10, 10, 'OPTION', 'SEARCHMORE', null, null, 1, null, null, '所有', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'SEARCHMENU', 'TESTCM', 30, 0, 'OPTION', 'SEARCHBOOK', null, null, 1, 'atb_bookmark.gif', null, '所有', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'APPTOOL', 'TESTCM', 1, 2, 'OPTION', 'SAVE', null, null, 1, 'nav_icon_save.gif', 'CTRL+ALT+S', '主要', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'APPTOOL', 'TESTCM', 1, 1, 'OPTION', 'INSERT', null, null, 1, 'nav_icon_insert.gif', 'CTRL+ALT+I', '所有', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'APPTOOL', 'TESTCM', 1, 3, 'OPTION', 'CLEAR', null, null, 1, 'nav_icon_clear.gif', 'CTRL+ALT+C', '主要', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'APPTOOL', 'TESTCM', 2, 1, 'OPTION', 'PREVIOUS', null, null, 1, 'nav_icon_previous.gif', 'CTRL+ALT+P', '主要', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'APPTOOL', 'TESTCM', 2, 2, 'OPTION', 'NEXT', null, null, 1, 'nav_icon_next.gif', 'CTRL+ALT+N', '主要', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'SEARCHMENU', 'TESTCM', 10, 20, 'OPTION', 'SEARCHWHER', null, null, 1, null, null, '所有', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'SEARCHMENU', 'TESTCM', 20, 20, 'OPTION', 'SEARCHVMQR', null, null, 1, null, null, '所有', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'SEARCHMENU', 'TESTCM', 10, 30, 'OPTION', 'SEARCHTIPS', null, null, 1, null, null, '所有', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'SEARCHMENU', 'TESTCM', 20, 10, 'OPTION', 'SEARCHSQRY', null, null, 1, null, null, '所有', MAXMENUSEQ.nextval);
insert into Maxmenu ( MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID) values ( 'MODULE', 'SD', 11051, 0, 'APP', 'TESTCM', null, null, 1, null, null, null, MAXMENUSEQ.nextval);


----------------CreateSigoptionScript----------------

insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'BMXVIEWMANAGEWHER', '用于控制“查看/管理查询”对话框中“详细信息”部分的可视性的签名选项', 0, 1, null, null, null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'BMXVIEWMANAGEWHERRO', '用于控制“查看/管理查询”对话框中“子句”字段的可编辑性的签名选项', 0, 1, null, null, null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'BOOKMARK', '添加到书签', 0, 0, null, null, null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'CLEAR', '清除变更', 0, 0, null, null, null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'DELETE', '删除 TESTCM', 0, 1, null, null, 'SAVE', SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'DUPLICATE', '复制 TESTCM', 0, 1, null, null, 'INSERT', SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'INSERT', '新建 TESTCM', 0, 1, 'SAVE', 'DUPLICATE', null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'NEXT', '下一个 TESTCM', 0, 0, null, null, null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'PREVIOUS', '上一个 TESTCM', 0, 0, null, null, null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'READ', '读取 TESTCM', 0, 1, 'CLEAR,BOOKMARK,NEXT,PREVIOUS', 'ALL', null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'SAVE', '保存 TESTCM', 0, 1, null, 'INSERT,DUPLICATE, DELETE', null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'SEARCHBOOK', '书签', 0, 1, null, null, null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'SEARCHMORE', '更多搜索字段', 0, 1, null, null, null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'SEARCHSQRY', '保存当前查询', 0, 1, null, null, null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'SEARCHTIPS', '查看搜索提示', 0, 1, null, null, null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'SEARCHVMQR', '查看/管理查询', 0, 1, null, null, null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);
insert into Sigoption ( APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD, MEMO) values ( 'TESTCM', 'SEARCHWHER', 'WHERE 子句', 0, 1, null, null, null, SIGOPTIONSEQ.nextval, 'ZH', 0, null);


----------------CreatePresentationScript----------------

insert into maxpresentation (app, maxpresentationid, presentation)
 Values ('TESTCM',maxpresentationseq.nextval,
'<?xml version="1.0" encoding="UTF-8"?> <presentation id="testcm" ismobile="false" mboname="TESTCALLMETHOD" 
resultstableid="results_showlist" version="6.0.0" viewport="1041x600" 
beanclass="org.shoukaiseki.webclient.beans.test.TestcallmethodAppBean"> <page id="mainrec" scroll="false"> <include 
controltoclone="pageHeader" id="INCLUDE-pageHeader"/> <clientarea id="clientarea"> <tabgroup id="maintabs" style="form"> <tab 
default="true" id="results" label="List" type="list"> <menubar event="search" id="actiontoolbar1" 
sourcemethod="getAppSearchOptions"/> <table id="results_showlist" inputmode="readonly" label="TABLE TITLE" mboname="TESTCALLMETHOD" 
selectmode="multiple"> <tablebody displayrowsperpage="20" filterable="true" filterexpanded="true" id="results_showlist_tablebody"> 
<tablecol dataattribute="TESTCALLMETHODID" filterable="false" id="results_showlist_column_select" mxevent="toggleselectrow" 
mxevent_desc="Select Row {0}" sortable="false" type="event"/> <tablecol dataattribute="TESTCALLMETHODID" 
id="results_showlist_column_link" mxevent="selectrecord" mxevent_desc="Go To %1" type="link" usefieldsizegroup="false"/> <tablecol 
dataattribute="TESTCALLMETHODID" id="results_showlist_column1"/> <tablecol filterable="false" id="results_bookmark" 
mxevent="BOOKMARK" mxevent_desc="Add to Bookmarks" mxevent_icon="btn_addtobookmarks.gif" sortable="false" type="event"/> 
</tablebody> </table> </tab> <tab id="main" label="Main" type="insert"> <section id="maintab_section"> <textbox 
dataattribute="TESTCALLMETHODID" id="maintab_attr1" inputmode="readonly"/> <multilinetextbox dataattribute="DESCRIPTION" 
id="maintab_attr3" rows="5" columns="80"/> </section> <table id="1517214304044" relationship="Testcallmethod2" 
beanclass="org.shoukaiseki.webclient.beans.test.TestcallmethodDataBean" label="测试2"> <tablebody id="1517214304055"> <tablecol 
dataattribute="PARENTID" id="1517214263948" inputmode="readonly"/> <tablecol dataattribute="TESTCALLMETHOD2ID" id="1517214304058" 
inputmode="readonly"/> ');


--------------------------------

update maxpresentation set presentation=presentation ||
'<tablecol dataattribute="description" id="1517214363948"/> <tablecol filterable="false" id="14834124338725" 
mxevent="toggledeleterow" mxevent_desc="标记要删除的行" mxevent_icon="btn_garbage.gif" sortable="false" type="event"/> </tablebody> 
<tabledetails id="1517214304063"/> <buttongroup id="selresitems_2aa"> <pushbutton id="selresitems_aa_houhou05" label="新建行" 
mxevent="addrow"/> </buttongroup> </table> <buttongroup id="selresitems_2"> <pushbutton id="selresitems_houhou05" 
label="testhouhou" mxevent="testhouhou" value="testhouhou"/> </buttongroup> </tab> </tabgroup> </clientarea> <include 
controltoclone="pageFooter" id="INCLUDE-pageFooter"/> </page> <dialog 
beanclass="org.shoukaiseki.webclient.beans.test.TestcallmethodDialogDataBean" id="testhouhou" label="测试方法调用" 
parentdatasrc="MAINRECORD" relationship="Testcallmethod2A"> <helpgrid id="1517292890982" innerhtml="mxevent为dialogok时,value是执行
的方法,默认为执行无参数方法,如果需要带参,则在对话框的databean重写callMethod方法,进行自定义需要传递的参数"/> <table 
id="selresitems_select_table" inputmode="readonly" label="子表" selectmode="multiple"> <tablebody displayrowsperpage="15" 
filterable="true" id="selresitems_select_table_tablebody"> <tablecol filterable="false" hidden="false" 
id="selresitems_select_table_tablebody_1" mxevent="toggleselectrow" sortable="false" type="event"/> <tablecol 
dataattribute="PARENTID" id="1517214263948a" inputmode="readonly"/> <tablecol dataattribute="TESTCALLMETHOD2ID" id="1517214304058a" 
inputmode="readonly"/> <tablecol dataattribute="description" id="1517214363948a"/> </tablebody> </table> <helpgrid 
id="1517292890982a" innerhtml="houhou03,houhou04点击前需勾选子表的行"/> <buttongroup id="testhouhou_selresitems_2"> <pushbutton 
id="selresitems_2_0" label="houhou00" mxevent="dialogok" value="houhou00"/> <pushbutton default="true" id="selresitems_2_1" 
label="houhou01" mxevent="dialogok" value="houhou01"/> <pushbutton id="selresitems_2_2" label="houhou02" mxevent="dialogok" 
value="houhou02"/> <pushbutton id="selresitems_2_3" label="houhou03" mxevent="dialogok"'
where app='TESTCM';


--------------------------------

update maxpresentation set presentation=presentation ||
' value="houhou03"/> <pushbutton id="selresitems_2_4" label="houhou04" mxevent="dialogok" value="houhou04"/> <pushbutton 
id="selresitems_2_5" label="houhou05" mxevent="dialogok" value="houhou05"/> <pushbutton id="testhouhou_button_02" label="取消" 
mxevent="dialogcancel"/> </buttongroup> </dialog> <dialog beanclass="psdi.webclient.system.beans.QbeBean" id="searchmore" 
inputmode="query" label="More Search Fields"> <section id="searchmore_1"> <textbox dataattribute="TESTCALLMETHODID" 
id="searchmore_attr1"/> <textbox dataattribute="DESCRIPTION_LONGDESCRIPTION" id="searchmore_attr2"/> <textbox 
dataattribute="DESCRIPTION" id="searchmore_attr3"/> </section> <buttongroup id="searchmore_buttongroup"> <pushbutton default="true" 
id="searchmore_buttongroup_1" label="Find" mxevent="dialogok"/> <pushbutton id="qbe_restoreappdefault_button" label="恢复应用程序缺
省值" mxevent="qbeclear"/> <pushbutton id="qbe_revisebutton" label="修订" menutype="SEARCHMOREREVISE" targetid="searchmore"/> 
<pushbutton id="searchmore_buttongroup_3" label="Cancel" mxevent="qbecancel"/> </buttongroup> </dialog> <dialog 
beanclass="psdi.webclient.system.beans.ViewBookmarksBean" id="searchbook" label="我的书签" mboname="TESTCALLMETHOD"> <table 
id="bookmarks_table" inputmode="readonly" selectmode="single"> <tablebody displayrowsperpage="20" filterable="true" 
id="bookmarks_table_tablebody"> <tablecol dataattribute="TESTCALLMETHODID" id="bookmarks_tablecolumn_1" mxevent="selectrecord" 
type="link"/> <tablecol dataattribute="description" id="bookmarks_tablecolumn_2"/> <tablecol filterable="false" 
id="bookmarks_tablecolumn_3" mxevent="instantdelete" mxevent_desc="Delete" mxevent_icon="btn_delete.gif" sortable="false" 
type="event"/> </tablebody> </table> <buttongroup id="bookmarks_2"> <pushbutton default="true" id="bookmarks_2_1" label="确定" 
mxevent="dialogok"/> </buttongroup> </dialog> <configurationblock id="datastore_configurationblock"> </configurationblock> 
</presentation>'
where app='TESTCM';
commit;


----------------CreateMaxlabelsScript----------------

insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', '14834124338725', 'mxevent_desc', '标记要删除的行', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', '1517214304044', 'label', '测试2', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', '1517292890982', 'innerhtml', 'mxevent为dialogok时,value是执行的方法,默认为执行无参数方法,如果需要带参,则在对话框的databean重写callMethod方法,进行自定义需要传递的参数', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', '1517292890982a', 'innerhtml', 'houhou03,houhou04点击前需勾选子表的行', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'bookmarks_tablecolumn_3', 'mxevent_desc', 'Delete', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'main', 'label', 'Main', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'results', 'label', 'List', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'results_bookmark', 'mxevent_desc', 'Add to Bookmarks', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'results_showlist', 'label', 'TABLE TITLE', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'results_showlist_column_link', 'mxevent_desc', 'Go To %1', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'results_showlist_column_select', 'mxevent_desc', 'Select Row {0}', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'searchmore', 'label', 'More Search Fields', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'searchmore_buttongroup_1', 'label', 'Find', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'searchmore_buttongroup_3', 'label', 'Cancel', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'selresitems_2_0', 'label', 'houhou00', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'selresitems_2_1', 'label', 'houhou01', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'selresitems_2_2', 'label', 'houhou02', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'selresitems_2_3', 'label', 'houhou03', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'selresitems_2_4', 'label', 'houhou04', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'selresitems_2_5', 'label', 'houhou05', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'selresitems_aa_houhou05', 'label', '新建行', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'selresitems_houhou05', 'label', 'testhouhou', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'selresitems_select_table', 'label', '子表', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'testhouhou', 'label', '测试方法调用', MAXLABELSSEQ.nextval);
insert into maxlabels ( APP, ID, PROPERTY, VALUE, MAXLABELSID) values ( 'TESTCM', 'testhouhou_button_02', 'label', '取消', MAXLABELSSEQ.nextval);
commit;

