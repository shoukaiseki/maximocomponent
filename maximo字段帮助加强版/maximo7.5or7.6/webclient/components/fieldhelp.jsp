<%--
* Licensed Materials - Property of IBM
* 
* 5724-U18
* 
* (C) COPYRIGHT IBM CORP. 2006, 2011 All Rights Reserved.
* 
* US Government Users Restricted Rights - Use, duplication or
* disclosure restricted by GSA ADP Schedule Contract with
* IBM Corp.
--%><%@ include file="../common/simpleheader.jsp" %>
<%
	ComponentInstance fhComponent=originalEvent.getSourceComponentInstance();
	LabelCache syscache = LabelCacheMgr.getSystemLabelCache(wcs);	
	String fieldLabel = syscache.getString("fieldhelp_ctrl", "fieldlabel");
	String fhLabel = fhComponent.getProperty("title");
	if(fieldLabel.equals(""))
		fieldLabel="Field:";
	String boundLabel = syscache.getString("fieldhelp_ctrl", "boundlabel");
	if(boundLabel.equals(""))
		boundLabel="Table.Column:";
	String boundTo= fhComponent.getProperty("objectname")+"."+fhComponent.getProperty("attributename");
	String helpRemarks = fhComponent.getProperty("remarks");

%>
<tr>
	<td colspan="6" width="100%" align="center">
<table id="<%=id%>" <%=automationId%> width="300px" align="center" border="0" summary="">
	<!-- start 显示XML绑定的 dataattribute-->
	<!--
	<tr>
		<td align="center" colspan="3" style="color:red">
		<input type="button" id="tj" value="display all" onclick="if(document.getElementById('fieldhelp_jsp_xml').style.visibility=='hidden'){mo=document.getElementById('fieldhelp_jsp_xml').style;mo.visibility='visible';mo.height='10px'}else{mo=document.getElementById('fieldhelp_jsp_xml').style;mo.visibility='hidden';}" style="vertical-align: center;text-align: center;width: 100px;">
		</td>
	</tr>
	<tr style="visibility:hidden;height:10px" id="fieldhelp_jsp_xml" >
	-->
	<tr style="height:10px" id="fieldhelp_jsp_xml" >
		<td align="center" colspan="3" style="color:red">
			<!--<%=fhComponent.getProperty("lookup")%>-->
			<%@ include file="../help/fieldhelpxml.jsp" %>
		</td>
	</tr>
		<!--
		-->
	<tr>
		<td align="center" colspan="3" style="color:red">
		<!--

			<%=fhComponent.getProperty("dataattribute")%>
		-->
			<input   style="font-weight: normal; background:#E0E0E0; color:#100;width:300" type="text" value="<%=fhComponent.getProperty("dataattribute")%>" readonly="true" onfocus="this.select();" onmouseup="this.select();" />
		</td>
	</tr>
	<!--  end  显示XML绑定的 dataattribute-->
	<tr>
		<td nowrap align='<%=reverseAlign%>'>
			<span class="<%=textcss%> label"><%=fieldLabel%></span>
		</td>
		<td nowrap width="10px">&nbsp;</td>
		<td nowrap align='<%=defaultAlign%>'>
			<span class="<%=textcss%> fhd"><%=fhLabel%></span>
		</td>
	</tr>
	<tr>
		<td nowrap align='<%=reverseAlign%>'>
			<span class="<%=textcss%> label"><%=boundLabel%></span>
		</td>
		<td nowrap width="10px">&nbsp;</td>
		<td nowrap align='<%=defaultAlign%>'>
			<span class="<%=textcss%> fhd"><%=boundTo%></span>
		</td>
	</tr>
	<tr>
		<td colspan="3" class="<%=textcss%> fhd fhrb">
			<%=helpRemarks%>
		</td>
	</tr>
</table>
</td>
</tr>
<%	//输出textbox的参数信息
	//System.out.println("alwaysinclude="+fhComponent.getProperty("alwaysinclude"));
	//System.out.println("asyncevents="+fhComponent.getProperty("asyncevents"));
	//System.out.println("attributename="+fhComponent.getProperty("attributename"));
	//System.out.println("borderalways="+fhComponent.getProperty("borderalways"));
	//System.out.println("columnname="+fhComponent.getProperty("columnname"));
	//System.out.println("cssclass="+fhComponent.getProperty("cssclass"));
	//System.out.println("dataattribute="+fhComponent.getProperty("dataattribute"));
	//System.out.println("defaultbutton="+fhComponent.getProperty("defaultbutton"));
	//System.out.println("defaultrender="+fhComponent.getProperty("defaultrender"));
	//System.out.println("entityname="+fhComponent.getProperty("entityname"));
	//System.out.println("erroricon="+fhComponent.getProperty("erroricon"));
	//System.out.println("forceclear="+fhComponent.getProperty("forceclear"));
	//System.out.println("haslongdescription="+fhComponent.getProperty("haslongdescription"));
	//System.out.println("hidetooltip="+fhComponent.getProperty("hidetooltip"));
	//System.out.println("hidewhen="+fhComponent.getProperty("hidewhen"));
	//System.out.println("id="+fhComponent.getProperty("id"));
	//System.out.println("input="+fhComponent.getProperty("input"));
	//System.out.println("inputmode="+fhComponent.getProperty("inputmode"));
	//System.out.println("instance-class="+fhComponent.getProperty("instance-class"));
	//System.out.println("internalvalue="+fhComponent.getProperty("internalvalue"));
	//System.out.println("inttype="+fhComponent.getProperty("inttype"));
	//System.out.println("isnumeric="+fhComponent.getProperty("isnumeric"));
	//System.out.println("ispersistent="+fhComponent.getProperty("ispersistent"));
	//System.out.println("isunbound="+fhComponent.getProperty("isunbound"));
	//System.out.println("jsp-filename="+fhComponent.getProperty("jsp-filename"));
	//System.out.println("length="+fhComponent.getProperty("length"));
	//System.out.println("longdesc="+fhComponent.getProperty("longdesc"));
	//System.out.println("longdescreadonly="+fhComponent.getProperty("longdescreadonly"));
	//System.out.println("lookup="+fhComponent.getProperty("lookup"));
	//System.out.println("menutype="+fhComponent.getProperty("menutype"));
	//System.out.println("msggroup="+fhComponent.getProperty("msggroup"));
	//System.out.println("msgkey="+fhComponent.getProperty("msgkey"));
	//System.out.println("notifyportlets="+fhComponent.getProperty("notifyportlets"));
	//System.out.println("numericalignment="+fhComponent.getProperty("numericalignment"));
	//System.out.println("numericonly="+fhComponent.getProperty("numericonly"));
	//System.out.println("objectname="+fhComponent.getProperty("objectname"));
	//System.out.println("onfilterrow="+fhComponent.getProperty("onfilterrow"));
	//System.out.println("prepend="+fhComponent.getProperty("prepend"));
	//System.out.println("remarks="+fhComponent.getProperty("remarks"));
	//System.out.println("rownum="+fhComponent.getProperty("rownum"));
	//System.out.println("scale="+fhComponent.getProperty("scale"));
	//System.out.println("selected="+fhComponent.getProperty("selected"));
	//System.out.println("size="+fhComponent.getProperty("size"));
	//System.out.println("staticname="+fhComponent.getProperty("staticname"));
	//System.out.println("synchronous="+fhComponent.getProperty("synchronous"));
	//System.out.println("tabindex="+fhComponent.getProperty("tabindex"));
	//System.out.println("tablereadonly="+fhComponent.getProperty("tablereadonly"));
	//System.out.println("takesfocus="+fhComponent.getProperty("takesfocus"));
	//System.out.println("textcss="+fhComponent.getProperty("textcss"));
	//System.out.println("title="+fhComponent.getProperty("title"));
	//System.out.println("usefieldsizegroup="+fhComponent.getProperty("usefieldsizegroup"));
	//System.out.println("usereditable="+fhComponent.getProperty("usereditable"));
	//System.out.println("value="+fhComponent.getProperty("value"));
	//System.out.println("valuepart="+fhComponent.getProperty("valuepart"));
	//System.out.println("visible="+fhComponent.getProperty("visible"));
	//System.out.println("width="+fhComponent.getProperty("width"));
%>
<%@ include file="../common/componentfooter.jsp" %>
