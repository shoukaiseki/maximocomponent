	<style type="text/css">
		.jyouhoutable{border-collapse:collapse;border-spacing:0;border-bottom:1px solid #888;border-right:1px solid #888;border-left:1px solid #888;border-top:1px solid #888;valign:top;}
		.jyouhoutrHeader{border-collapse:collapse;border-spacing:0;border-bottom:1px solid #888;border-right:1px solid #888;border-left:1px solid #888;border-top:1px solid #888;height:10px;valign:top;}
		.jyouhoutrtd{border-collapse:collapse;border-spacing:0;border-bottom:1px solid #888;border-right:1px solid #888;border-left:1px solid #888;border-top:1px solid #888;height:10px;valign:top;text-align:center;}
		.tddata{}
	</style>

<table  class="jyouhoutable" width="100%">
<%
	String[] fieldhelpxmls={"lookup","domain","domainid","DOMAINID","length"};
	//fieldhelpxmls=new String[]{"alwaysinclude","asyncevents","attributename","borderalways","columnname","cssclass","dataattribute","defaultbutton","defaultrender","entityname","erroricon","forceclear","haslongdescription","hidetooltip","hidewhen","id","input","inputmode","instance-class","internalvalue","inttype","isnumeric","ispersistent","isunbound","jsp-filename","length","longdesc","longdescreadonly","lookup","menutype","msggroup","msgkey","notifyportlets","numericalignment","numericonly","objectname","onfilterrow","prepend","remarks","rownum","scale","selected","size","staticname","synchronous","tabindex","tablereadonly","takesfocus","textcss","title","usefieldsizegroup","usereditable","value","valuepart","visible","width"};
	for(String fieldhelpxml:fieldhelpxmls){
		if(fhComponent.getProperty(fieldhelpxml)!=null&&!fhComponent.getProperty(fieldhelpxml).isEmpty()){
%>
	<tr  class="jyouhoutrHeader" >
		<td class="jyouhoutrtd" ><%=fieldhelpxml%></td><td class="jyouhoutrtd" ><%=fhComponent.getProperty(fieldhelpxml)%></td>
	</tr>
<%
		}
	}
%>
<!--
	<tr  class="jyouhoutrHeader" >
		<td class="jyouhoutrtd" >alwaysinclude</td><td class="jyouhoutrtd" ><%=fhComponent.getProperty("alwaysinclude")%></td>
	</tr>
	<tr  class="jyouhoutrHeader" >
		<td class="jyouhoutrtd" >lookup</td><td class="jyouhoutrtd" ><%=fhComponent.getProperty("lookup")%></td>
	</tr>
-->
<%
	psdi.mbo.MboValueInfo attribute =psdi.server.MXServer.getMXServer().getMaximoDD().getMboSetInfo(fhComponent.getProperty("objectname")).getAttribute(fhComponent.getProperty("attributename"));
	java.util.HashMap<String, String> attInfos=new java.util.HashMap<String, String>();
	if(attribute.getAutoKeyName()!=null&&!attribute.getAutoKeyName().isEmpty()){
		attInfos.put("AutokeyName", attribute.getAutoKeyName());
	}
	if(attribute.getClassName()!=null&&!attribute.getClassName().isEmpty()){
		attInfos.put("ClassName", attribute.getClassName());
	}
	if(attribute.getDefaultValue()!=null&&!attribute.getDefaultValue().isEmpty()){
		attInfos.put("DefaultValue", attribute.getDefaultValue());
	}
	if(attribute.getDomainId()!=null&&!attribute.getDomainId().isEmpty()){
		attInfos.put("DomainId", attribute.getDomainId());
	}
	if(attribute.getSearchType()!=null&&!attribute.getSearchType().isEmpty()){
		attInfos.put("SearchType", attribute.getSearchType());
	}
	if(attribute.getMaxType()!=null&&!attribute.getMaxType().isEmpty()){
		attInfos.put("MaxType", attribute.getMaxType());
	}
	/**
	**/
	for (Object key : attInfos.keySet()) {
		Object value=attInfos.get(key);
%>
	<tr  class="jyouhoutrHeader" >
		<td class="jyouhoutrtd" ><%=key%></td><td class="jyouhoutrtd" ><%=value%></td>
	</tr>
<%
	}
%>
</table>
<!--
"alwaysinclude="+fhComponent.getProperty("alwaysinclude")
"asyncevents="+fhComponent.getProperty("asyncevents")
"attributename="+fhComponent.getProperty("attributename")
"borderalways="+fhComponent.getProperty("borderalways")
"columnname="+fhComponent.getProperty("columnname")
"cssclass="+fhComponent.getProperty("cssclass")
"dataattribute="+fhComponent.getProperty("dataattribute")
"defaultbutton="+fhComponent.getProperty("defaultbutton")
"defaultrender="+fhComponent.getProperty("defaultrender")
"entityname="+fhComponent.getProperty("entityname")
"erroricon="+fhComponent.getProperty("erroricon")
"forceclear="+fhComponent.getProperty("forceclear")
"haslongdescription="+fhComponent.getProperty("haslongdescription")
"hidetooltip="+fhComponent.getProperty("hidetooltip")
"hidewhen="+fhComponent.getProperty("hidewhen")
"id="+fhComponent.getProperty("id")
"input="+fhComponent.getProperty("input")
"inputmode="+fhComponent.getProperty("inputmode")
"instance-class="+fhComponent.getProperty("instance-class")
"internalvalue="+fhComponent.getProperty("internalvalue")
"inttype="+fhComponent.getProperty("inttype")
"isnumeric="+fhComponent.getProperty("isnumeric")
"ispersistent="+fhComponent.getProperty("ispersistent")
"isunbound="+fhComponent.getProperty("isunbound")
"jsp-filename="+fhComponent.getProperty("jsp-filename")
"length="+fhComponent.getProperty("length")
"longdesc="+fhComponent.getProperty("longdesc")
"longdescreadonly="+fhComponent.getProperty("longdescreadonly")
"lookup="+fhComponent.getProperty("lookup")
"menutype="+fhComponent.getProperty("menutype")
"msggroup="+fhComponent.getProperty("msggroup")
"msgkey="+fhComponent.getProperty("msgkey")
"notifyportlets="+fhComponent.getProperty("notifyportlets")
"numericalignment="+fhComponent.getProperty("numericalignment")
"numericonly="+fhComponent.getProperty("numericonly")
"objectname="+fhComponent.getProperty("objectname")
"onfilterrow="+fhComponent.getProperty("onfilterrow")
"prepend="+fhComponent.getProperty("prepend")
"remarks="+fhComponent.getProperty("remarks")
"rownum="+fhComponent.getProperty("rownum")
"scale="+fhComponent.getProperty("scale")
"selected="+fhComponent.getProperty("selected")
"size="+fhComponent.getProperty("size")
"staticname="+fhComponent.getProperty("staticname")
"synchronous="+fhComponent.getProperty("synchronous")
"tabindex="+fhComponent.getProperty("tabindex")
"tablereadonly="+fhComponent.getProperty("tablereadonly")
"takesfocus="+fhComponent.getProperty("takesfocus")
"textcss="+fhComponent.getProperty("textcss")
"title="+fhComponent.getProperty("title")
"usefieldsizegroup="+fhComponent.getProperty("usefieldsizegroup")
"usereditable="+fhComponent.getProperty("usereditable")
"value="+fhComponent.getProperty("value")
"valuepart="+fhComponent.getProperty("valuepart")
"visible="+fhComponent.getProperty("visible")
"width="+fhComponent.getProperty("width")
-->
