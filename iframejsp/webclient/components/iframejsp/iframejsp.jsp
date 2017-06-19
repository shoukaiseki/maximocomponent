<%@ include file="../../common/simpleheader.jsp" %>
<%
	String path = request.getContextPath();
	String thisbasePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String includejsp=null;
	String iframesrc = component.getProperty("iframesrc");
	includejsp = component.getProperty("includejsp");
	org.apache.log4j.Logger log=org.apache.log4j.Logger.getLogger("wmc.sso.ldap");
	log.debug("includejsp="+includejsp);



	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	
	String height=component.getProperty("height");
	if("pageheight".equalsIgnoreCase(height)){
		height="";
	}
	%>

	

    
    <base href="<%=thisbasePath%>">
	<%
		if(!"samples.jsp".equals(includejsp)){
		log.debug("is samples");
		request.setAttribute("id",id);
			
	%>
		<div id="<%=id%>" width="<%=component.getProperty("width")%>"  style="height:<%=height%>">
		<jsp:include page="<%=includejsp%>"  flush="true">   
			<jsp:param name="id" value="<%=id%>"/> 
		</jsp:include>
		</div>
	<%
		}else{
		log.debug("not is samples");
		//component.getProperty("height")
	%>
	    <iframe  id="<%=id%>" width="<%=component.getProperty("width")%>" height="<%=height%>" style="height:<%=height%>" src="<%=component.getProperty("iframesrc")%>"></iframe>
	<%
		}
		if("pageheight".equalsIgnoreCase(component.getProperty("height"))){
	%>
	
		<script type="text/javascript">
		function setiframejsp_iframe(){
			console.log("asus11")
			console.log(document.body.offsetHeight)
			console.log("<%=id%>")
			document.getElementById("<%=id%>").style.height=(document.body.offsetHeight-10)+"px";
		}
		setiframejsp_iframe()
		</script>
	<%
		}
		%>
<%@ include file="../../common/componentfooter.jsp" %>
