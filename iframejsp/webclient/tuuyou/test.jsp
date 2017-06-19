<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id=request.getParameter("id")+"_iframejsptable";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<table  width="100%" style="height:100%" border="1" class=" " >
	<tr height="100%">
		<td  id="<%=id%>" height="100%" align="center"> 
			Hello World <br>
			JVM版本= <%=System.getProperty("java.version", "not specified")%><br>
			JVM缺省路径=<%=System.getProperty("java.home", "not specified")%>
			<br>
			java.version=<%=System.getProperty("java.version")%>
			<br>
			java.class.version=<%=System.getProperty("java.class.version")%>
			<br>
			Java 虚拟机中的内存总量=<%=Runtime.getRuntime().totalMemory()/1024/1024%>MB
			<br>
			Java 虚拟机试图使用的最大内存量=<%=Runtime.getRuntime().maxMemory()/1024/1024%>MB
			<br>
			Java 虚拟机中的空闲内存量=<%=Runtime.getRuntime().freeMemory()/1024/1024%>MB
		</td>
	</tr>
</table>

	
		<script type="text/javascript">
		function setiframejsp_iframe_test(){
			document.getElementById("<%=id%>").style.height=(document.body.offsetHeight-10)+"px";
		}
		setiframejsp_iframe_test()
		</script>
</html>