# maximo子表上传附件

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E5%AD%90%E8%A1%A8%E4%B8%8A%E4%BC%A0%E9%99%84%E4%BB%B6/img/001.png)

点击子表最右边的按钮进行上传附件,对话框与主表上传附件的一样

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E5%AD%90%E8%A1%A8%E4%B8%8A%E4%BC%A0%E9%99%84%E4%BB%B6/img/002.png)

点击确定之后上传,就能看到'子表附件'中已经有这个文件了

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E5%AD%90%E8%A1%A8%E4%B8%8A%E4%BC%A0%E9%99%84%E4%BB%B6/img/003.png)

再添加个虚拟字段来显示子表包含的附件

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E5%AD%90%E8%A1%A8%E4%B8%8A%E4%BC%A0%E9%99%84%E4%BB%B6/img/004.png)

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E5%AD%90%E8%A1%A8%E4%B8%8A%E4%BC%A0%E9%99%84%E4%BB%B6/img/005.png)

### 数据库配置中子表添加关系名 DOCLINKS
```
NAME:DOCLINKS
CHILD:DOCLINKS
WHERECLAUSE:ownertable='TEST2A' and ownerid=:test2aid
```

### 字段类 org.shoukaiseki.app.docurl.FldDocurls 
```Java
package org.shoukaiseki.app.docurl;

import com.wmc.app.defect.FldAssetnum;
import psdi.mbo.*;
import psdi.util.MXException;

import java.rmi.RemoteException;

/**
 * org.shoukaiseki.app.docurl.FldDocurls <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-11-13 15:36:29<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class FldDocurls extends MboValueAdapter {

    public FldDocurls(MboValue mbv) {
        super(mbv);
    }


    @Override
    public void initValue() throws MXException, RemoteException {
        super.initValue();
        MboSetRemote doclinksSet = getMboValue().getMbo().getMboSet("DOCLINKS");
        MboSetEnumeration mse=new MboSetEnumeration(doclinksSet);
        StringBuffer sb=new StringBuffer();
        while (mse.hasMoreElements()){
            MboRemote doclink = mse.nextMbo();
            if(!sb.toString().isEmpty()){
                sb.append("<br>");
            }
            sb.append(doclink.getString("document"));
        }
        getMboValue().setFlag(16L,true);
        getMboValue().setValue(sb.toString());

    }
}

```

### Bean类 org.shoukaiseki.webclient.beans.subtabuploadattachments.AddSubtabDocBean
```Java
package org.shoukaiseki.webclient.beans.subtabuploadattachments;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import psdi.app.doclink.DoclinkServiceRemote;
import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.mbo.SqlFormat;
import psdi.mbo.Translate;
import psdi.server.MXServer;
import psdi.util.MXApplicationException;
import psdi.util.MXException;
import psdi.util.MXSession;
import psdi.webclient.beans.doclinks.RegisterDocBean;
import psdi.webclient.system.beans.AppBean;
import psdi.webclient.system.beans.DataBean;
import psdi.webclient.system.controller.ControlHandler;
import psdi.webclient.system.controller.UploadFile;
import psdi.webclient.system.controller.Utility;
import psdi.webclient.system.controller.WebClientEvent;
import psdi.webclient.system.runtime.WebClientRuntime;

/**
 * org.shoukaiseki.webclient.beans.subtabuploadattachments.AddSubtabDocBean <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-11-13 15:13:39<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class AddSubtabDocBean extends RegisterDocBean {

    public static final Logger logger=Logger.getLogger("org.shoukaiseki");


    public AddSubtabDocBean() {
        super();
    }


    public void initialize() throws MXException, RemoteException {
        logger.debug("initialize");
        super.initialize();
    }

    protected MboSetRemote getMboSetRemote() throws MXException, RemoteException {
        ControlHandler handler = this.creatingEvent.getSourceControl();
        String dataSrc = handler.getProperty("datasrc");
        DataBean dataBean = this.app.getDataBean(dataSrc);
        return dataBean.getMboSet().getMbo().getMboSet("DOCLINKS");
    }

    public synchronized void insert() throws MXException, RemoteException {
        logger.debug("insert.mbo="+getMboSet().getName());
        logger.debug("owner.name="+getMboSet().getOwner().getName());
        super.insert();
        this.setValue("ADDINFO", "TRUE");
        this.setValue("document", this.getMbo().getMboSet("DOCINFO").getMbo().getString("document"));
        this.dataChangedEvent(this);
    }

    public synchronized void close() {
        if (this.dialogReferences > 0) {
            --this.dialogReferences;
        } else {
            this.resetDataBean();
            this.cleanup();
        }
    }

    public int execute() throws MXException, RemoteException {
        int retVal = super.execute();
        this.validate();

        try {
            if (this.app.getAppBean() != null) {
                ((AppBean)this.app.getAppBean()).saveattachment();
            } else {
                this.save();
            }
        } catch (MXException var4) {
            WebClientEvent currentEvent = this.sessionContext.getCurrentEvent();
            Utility.showMessageBox(currentEvent, var4);
            this.getMboSet().reset();
            return 1;
        }

        if (this.parent != null) {
            this.parent.fireStructureChangedEvent();
        }

        return retVal;
    }

    public int cancelDialog() throws MXException, RemoteException {
        this.getMboSet().reset();
        int ret = super.cancelDialog();

        try {
            this.validate();
        } catch (MXException var3) {
            this.mboSetRemote.deleteAndRemove(this.getMbo(), 2L);
        }

        return ret;
    }

    public int passSuberExecute() throws MXException, RemoteException {
        int retVal = super.execute();
        return retVal;
    }




}
```

### library.xml 增加
```Xml
	<dialog beanclass="org.shoukaiseki.webclient.beans.subtabuploadattachments.AddSubtabDocBean" id="subtabuploadattachments" label="创建文件附件" mboname="DOCLINKS" savemode="ONLOAD">
		<helpgrid id="subtabuploadattachments_help" innerhtml="只能打印以下格式的文件：.pdf、.xls、.csv、.txt、.doc、.gif、.jpg、.ppt。 如果所附的文件不属于这些格式，请清除“打印报告和所附文档（如为可打印类型）”选项（高级选项）旁的复选框。"/>
		<defaultvalue dataattribute="urltype" id="subtabuploadattachments_file_d1" value="!FILE!"/>
		<section id="subtabuploadattachments_file_1">
			<combobox dataattribute="doctype" displayattribute="doctype" id="subtabuploadattachments_file_1_2" label="选择文件夹"/>
			<attachdoc id="subtabuploadattachments_file_1_8" label="指定文件"/>
			<textbox dataattribute="urlname" id="subtabuploadattachments_file_1_9" label="指定文件" width="200"/>
			<multiparttextbox dataattribute="document" descdataattribute="description" id="subtabuploadattachments_file_1_4" label="命名文档"/>
		</section>
		<section id="subtabuploadattachments_file_2" label="高级选项">
			<checkbox dataattribute="upload" id="subtabuploadattachments_file_2_row2_col1_1_1" label="将文档复制到管理员设置的缺省位置（建议的位置）。" mxeventjshandler="cbupld"/>
			<checkbox dataattribute="show" id="subtabuploadattachments_file_2_row3_col1_1_1" label="将文档添加至文档库，以供他人使用" sigoption="MANAGELIB" sigoptiondatasrc="MAINRECORD"/>
			<checkbox dataattribute="printthrulink" id="subtabuploadattachments_file_2_1" label="打印报告和所附文档（如为可打印类型）"/>
		</section>
		<buttongroup id="subtabuploadattachments_file_3">
			<doclinkuploadbutton default="true" id="subtabuploadattachments_file_3_1" label="确定"/>
			<pushbutton id="subtabuploadattachments_file_3_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>

```


### 应用程序xml

#### test2uploa003.xml
解决子表未保存出现对话框字段红叉错误,添加子表绑定DataBean,调用dialog之前进行appbean.save操作

```Xml
<?xml version="1.0" encoding="UTF-8"?>

<presentation beanclass="psdi.webclient.system.beans.AppBean" id="test2uploa" ismobile="false" mboname="TEST2" resultstableid="results_showlist" version="6.0.0" viewport="1041x600">
	<page id="mainrec" scroll="false">
		<include controltoclone="pageHeader" id="INCLUDE-pageHeader"/>
		<clientarea id="clientarea">
			<tabgroup id="maintabs" style="form">
				<tab default="true" id="results" label="List" type="list">
					<menubar event="search" id="actiontoolbar1" sourcemethod="getAppSearchOptions"/>
					<table id="results_showlist" inputmode="readonly" label="TABLE TITLE" mboname="TEST2" selectmode="multiple">
						<tablebody displayrowsperpage="20" filterable="true" filterexpanded="true" id="results_showlist_tablebody">
							<tablecol dataattribute="TEST2ID" filterable="false" id="results_showlist_column_select" mxevent="toggleselectrow" mxevent_desc="Select Row {0}" sortable="false" type="event"/>
							<tablecol dataattribute="TEST2ID" id="results_showlist_column_link" mxevent="selectrecord" mxevent_desc="Go To %1" type="link" usefieldsizegroup="false"/>
							<tablecol dataattribute="TEST2ID" id="results_showlist_column1"/>
							<tablecol dataattribute="DESCRIPTION" id="results_showlist_column2"/>
							<tablecol filterable="false" id="results_bookmark" mxevent="BOOKMARK" mxevent_desc="Add to Bookmarks" mxevent_icon="btn_addtobookmarks.gif" sortable="false" type="event"/>
						</tablebody>
					</table>
				</tab>
				<tab id="main" label="Main" type="insert">
					<section id="maintab_section">
						<textbox dataattribute="TEST2ID" id="maintab_attr1"/>
						<textbox dataattribute="DESCRIPTION" id="maintab_attr2" lookup="longdesc"/>
						<textbox dataattribute="type" id="1508231454907" inputmode="required" lookup="valuelist"/>
						<textbox dataattribute="ORGID" id="maintab_attr4" inputmode="readonly"/>
						<attachments id="1510298930496"/>
						<textbox dataattribute="SITEID" id="maintab_attr6" inputmode="readonly"/>
					</section>
					<table beanclass="org.shoukaiseki.webclient.beans.subtabuploadattachments.SubtabDataBean" id="table_showgia" label="子表" relationship="TEST2A">
						<defaultvalue dataattribute="TEST2ID" defaulttype="insert" fromattribute="TEST2ID" fromdatasrc="mainrecord" id="1369664492323"/>
						<tablebody id="1508230861965">
							<tablecol id="1510393207667" mxevent="toggledetailstate"/>
							<tablecol dataattribute="TEST2AID" id="1508230861968" inputmode="readonly"/>
							<tablecol dataattribute="TEST2ID" id="1508230903351" inputmode="readonly"/>
							<tablecol dataattribute="B" id="1508231496372"/>
							<tablecol dataattribute="description" id="1508230900682"/>
							<tablecol dataattribute="docurls" id="1538230900682" inputmode="readonly"/>
							<tablecol id="1510304896846" mxevent="subtabuploadattachments" mxevent_icon="img_attachments.gif"/>
							<tablecol id="1512458857678" mxevent="toggledeleterow" type="event"/>
						</tablebody>
						<buttongroup id="1369664594114">
							<pushbutton id="1369664594122" label="新建行" mxevent="addrow"/>
						</buttongroup>
						<tabledetails id="1508230861971">
							<section id="1518230861971"/>
						</tabledetails>
					</table>
					<table id="lookdoc_table" inputmode="readonly" label="子表附件" parentdatasrc="table_showgia" relationship="DOCLINKS">
						<tablebody id="lookdoc_tablebody">
							<tablecol dataattribute="document" id="lookdoc_table_tablebody_4" label="文档" type="openurl" urlattribute="weburl"/>
							<tablecol dataattribute="docinfo.description" id="lookdoc_table_tablebody_6" label="描述" sortable="false"/>
							<tablecol dataattribute="doctype" id="lookdoc_table_tablebody_3" label="文档文件夹"/>
							<tablecol dataattribute="docversion" id="lookdoc_table_tablebody_5" label="文档版本"/>
							<tablecol dataattribute="printthrulink" id="lookdoc_table_tablebody_9" label="打印"/>
							<tablecol dataattribute="ownertable" id="lookdoc_table_tablebody_1" label="应用程序"/>
							<tablecol filterable="false" id="lookdoc_table_tablebody_7" mxevent="linkproperties" mxevent_desc="附件属性" mxevent_icon="img_information.gif" sortable="false" type="event"/>
							<tablecol filterable="false" id="lookdoc_table_tablebody_8" mxevent="instantdelete" mxevent_desc="删除行" mxevent_icon="btn_delete.gif" sortable="false" type="event"/>
						</tablebody>
					</table>
				</tab>
			</tabgroup>
		</clientarea>
		<include controltoclone="pageFooter" id="INCLUDE-pageFooter"/>
	</page>

	<dialog beanclass="psdi.webclient.system.beans.QbeBean" id="searchmore" inputmode="query" label="More Search Fields">
		<section id="searchmore_1">
			<textbox dataattribute="TEST2ID" id="searchmore_attr1"/>
			<textbox dataattribute="DESCRIPTION" id="searchmore_attr2"/>
			<textbox dataattribute="DESCRIPTION_LONGDESCRIPTION" id="searchmore_attr3"/>
			<textbox dataattribute="ORGID" id="searchmore_attr4"/>
			<checkbox dataattribute="HASLD" id="searchmore_attr5"/>
			<textbox dataattribute="SITEID" id="searchmore_attr6"/>
		</section>
		<buttongroup id="searchmore_buttongroup">
			<pushbutton default="true" id="searchmore_buttongroup_1" label="Find" mxevent="dialogok"/>
			<pushbutton id="qbe_restoreappdefault_button" label="恢复应用程序缺省值" mxevent="qbeclear"/>
			<pushbutton id="qbe_revisebutton" label="修订" menutype="SEARCHMOREREVISE" targetid="searchmore"/>
			<pushbutton id="searchmore_buttongroup_3" label="Cancel" mxevent="qbecancel"/>
		</buttongroup>
	</dialog>

	<dialog beanclass="psdi.webclient.system.beans.ViewBookmarksBean" id="searchbook" label="我的书签" mboname="TEST2">
		<table id="bookmarks_table" inputmode="readonly" selectmode="single">
			<tablebody displayrowsperpage="20" filterable="true" id="bookmarks_table_tablebody">
				<tablecol dataattribute="TEST2ID" id="bookmarks_tablecolumn_1" mxevent="selectrecord" type="link"/>
				<tablecol dataattribute="description" id="bookmarks_tablecolumn_2"/>
				<tablecol filterable="false" id="bookmarks_tablecolumn_3" mxevent="instantdelete" mxevent_desc="Delete" mxevent_icon="btn_delete.gif" sortable="false" type="event"/>
			</tablebody>
		</table>
		<buttongroup id="bookmarks_2">
			<pushbutton default="true" id="bookmarks_2_1" label="确定" mxevent="dialogok"/>
		</buttongroup>
	</dialog>
	<configurationblock id="datastore_configurationblock">
	</configurationblock>
</presentation>
```

