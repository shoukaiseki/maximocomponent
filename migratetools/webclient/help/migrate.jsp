<%--
* Licensed Materials - Property of IBM
* 
* 5724-U18
* 
* (C) COPYRIGHT IBM CORP. 2006, 2014 All Rights Reserved.
* 
* US Government Users Restricted Rights - Use, duplication or
* disclosure restricted by GSA ADP Schedule Contract with
* IBM Corp.
--%><%@ page buffer="128kb" import= "psdi.webclient.system.beans.*, psdi.webclient.controls.*,psdi.webclient.system.runtime.WebClientRuntime, psdi.webclient.system.runtime.WebClientConstants, psdi.webclient.system.session.WebClientSession, psdi.webclient.system.controller.*, psdi.util.MXFormat, psdi.mbo.*, java.util.*, java.io.*,org.shoukaiseki.webclient.beans.migratetools.*,org.shoukaiseki.webclient.beans.migratetools.model.*" %>
<%
	String encoding = (String)session.getAttribute("_encoding");

	if (encoding == null)
		encoding = "UTF-8";

	String tableId = request.getParameter("_tbldnld");

	WebClientRuntime wcr = WebClientRuntime.getWebClientRuntime();
	WebClientSession wcs = wcr.getWebClientSession(request);
	synchronized (wcs)
	{
		AppInstance app = wcs.getCurrentApp();
		
		Table tableControl = null;
		Hashtable passwordCols = new Hashtable();
		tableControl=(Table)wcs.getControlInstance(tableId);
		if(tableControl==null)
		{
			%> No Table found for download!<%
		}	
		else
		{
			DataBean tableBean = tableControl.getDataBean();
			String appName=tableBean.getMboSet().getApp();
			System.out.println("migrate.jsp.appName="+appName);
			if("CONFIGUR".equalsIgnoreCase(appName)){
				FilterModel fm=new FilterModel().setTableid("maxobjectcfg");
				fm.addChilds(new FilterModel("MAXATTRIBUTECFG",""));
				fm.addChilds(new FilterModel("MAXRELATIONSHIP",""));
				new org.shoukaiseki.webclient.beans.migratetools.ConfigurMigrate(tableBean,request,response).setFilters(fm).execute().download();
			}else{
				new org.shoukaiseki.webclient.beans.migratetools.common.AbstractMigrate(tableBean,request,response).execute().download();
			}
		}
	}%>
