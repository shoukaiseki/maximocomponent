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
--%>
<%@ include file="../common/componentheader.jsp" %>
<%@ page import="org.shoukaiseki.webclient.components.ImgLibUserComponents,psdi.mbo.*" %>
<%
	//String servPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String servPath = wcs.getMaximoRequestContextURL();
    String imagePath = "";
	String imgviewwidth="120px";
	String imgviewheight= "120px";
	String width = component.getProperty("width");
	String height = component.getProperty("height");
	imgviewwidth=width;
	imgviewheight=height;
	String thumbnailflag = component.getProperty("thumbnail");
	String label = component.getProperty("label");
	String scrollText= component.getProperty("scroll");
	String imgwidth="120";
	String imgheight="120";
	imgwidth=component.getProperty("imgwidth");
	imgheight=component.getProperty("imgheight");
	int imgx=Integer.parseInt(component.getProperty("imgx"));;
	int imglibuserSet_count=-1;
	int forcount=0;
	//System.out.println("---------------imglibuserview.jsp");
	
	MboSetRemote imglibuserSet = ((ImgLibUserComponents)component).getImglibuserSet();
	if(imglibuserSet!=null){
		imglibuserSet_count=imglibuserSet.count();
		if(imglibuserSet.count()<imgx){
			imgx=imglibuserSet.count();
		}
		if(imgx>0){
			if(imglibuserSet.count()%imgx==0){
			  forcount=imglibuserSet.count();
			}else{
			  forcount=(imglibuserSet.count()+(imgx-imglibuserSet.count()%imgx));
			}
		}
	}
	//System.out.println("---------------forcount="+forcount);
	if(component.needsRender()) 
	{
			%>
			<!--
				<div style="overflow:scroll; overflow-x:hidden;width:<%=imgviewwidth%>;height:<%=imgviewheight%>">
				<div id="<%=id%>" <%=automationId%> style="width:<%=imgviewwidth%>;height:<%=imgviewheight%>;" >
			-->
				<div id="<%=id%>" <%=automationId%> style="<%=scrollText%>;width:<%=imgviewwidth%>;height:<%=imgviewheight%>;" >
			<%
		String newAutomationId = "";
        if(automation){
            newAutomationId="automationid=\""+id+"_img\"";
		}
	//System.out.println("---------------imglibuserSet.count()="+imglibuserSet.count());
	if(imglibuserSet!=null){
		if(imgx==0){
			%>
					<center>无图片可预览</center>
			<%
		}else{
			%>
				<center>
					<table  border="0" sytle="border:1px solid #000;text-align:center;">
			<%
			
			
			for(int i=0;i<forcount;i++){
				int test2=(i+1)%imgx;
				 if(i%imgx==0){
				 %>
					<tr> 
				 <%
					 
				 } 
				 if(i<imglibuserSet.count()){
						MboRemote imglibuser= imglibuserSet.getMbo(i);
					String imageName = imglibuser.getString("IMAGENAME");
					String imageId = imglibuser.getLong("IMGLIBUSERID")+"";
					if (imageName == null || imageName.equals("")) {
						imageName=imageId;
					}
						String imageSrc = "/maximo/imglibuserview/" + component.getId() + "/" + imageId + "/" + imageName+"?" +wcs.getUISessionUrlParameter();;
						////System.out.println(imageSrc);
						%>
									<!--id="<%=i%>" name="<%=test2%>"-->
									<td   style="width:<%=imgwidth%>;height:<%=imgheight%>;">
								<table  style="width:<%=imgwidth%>;height: 100% !important;">
									<tr> 
										<td>
											<img style="width:100%;" src="<%=imageSrc%>" >
										</td>
									</tr>
									<tr height="20px"> 
										<td align="center">
											<!--<a href="<%=imageSrc%>"  target="_blank"></a>
											-->
											<label ><%=imageName%></label>
										</td>
									</tr>
								</table >
							</td>
						<%
				%>
				 <%
				 }else{
				 %>
									<!--id="<%=i%>" name="<%=test2%>"-->
					<td   style="width:<%=imgwidth%>;height:<%=imgheight%>;height:<%=imgviewheight%>;"></td>
				<%
			
					}
				 if(test2==0){
				 %>
				 <!--
					add tr 
				 -->
					</tr> 
				 <%
				 }
			}
			%>
					</table>
					</center>
			<%
		}
	}else{
		%>
					<center>加载图片出错</center>
	<%	}
	
			%>
				</div>
			<%
		
	}else{
		
		%>
		<component id="<%=id%>_holder"<%=compType%>><%="<![CDATA["%><script>
		el=document.getElementById("<%=id%>");
		 while(el.hasChildNodes()) //当elem下还存在子节点时 循环继续  
		{  
			el.removeChild(el.firstChild);  
		}  
		<%
			if(imglibuserSet!=null){
				if(imgx==0){
					%>
					el.innerHTML="<center>无图片可预览</center>";
					<%
				}else{
					%>
					var htmltext='<center>';
					htmltext+='<table  border="0" sytle="border:1px solid #000;text-align:center;">';
					<%

					for(int i=0;i<forcount;i++){
						//System.out.println("---------------forcount="+forcount+",i="+i);
						
						int test2=(i+1)%imgx;
						 if(i%imgx==0){
							 %>
							 htmltext+="<tr>";
							 <%
						 }
						 if(i<imglibuserSet.count()){
							MboRemote imglibuser= imglibuserSet.getMbo(i);
							//System.out.println("---------------forcount="+forcount+",i="+i+",imglibuser="+imglibuser);
							String imageName = imglibuser.getString("IMAGENAME");
							String imageId = imglibuser.getLong("IMGLIBUSERID")+"";
							if (imageName == null || imageName.equals("")) {
								imageName=imageId;
							}
							String imageSrc = "/maximo/imglibuserview/" + component.getId() + "/" + imageId + "/" + imageName+"?" +wcs.getUISessionUrlParameter();;
							//System.out.println(imageSrc);
							%>
							htmltext+='<td style="width:<%=imgwidth%>;height:<%=imgheight%>;">';
							htmltext+='<table style="width:<%=imgwidth%>;height: 100% !important;">';
							htmltext+='<tr> ';
							htmltext+='<td>';
							htmltext+='<img style="width:100%;" src="<%=imageSrc%>" >';
							htmltext+='</td>';
							htmltext+='</tr>';
							htmltext+='<tr height="20px">';
							htmltext+='<td align="center">';
							htmltext+='<label><%=imageName%></label>';
							htmltext+='</td>';
							htmltext+='</tr>';
							htmltext+='</table>';
							htmltext+='</td>';

						   <%
						 }else{
							 %>
							 htmltext+='<td   style="width:<%=imgwidth%>;height:<%=imgheight%>;"></td>';
							 <%
						 }
						 if(test2==0){
						%>
							htmltext+='</tr>'; 
						 <%
						 }
					}
					%>
					htmltext+='</table>';
					htmltext+='</center>';
					el.innerHTML=htmltext;
					<%
					
				}
			}else{
				%>
				el.innerHTML="<center>加载图片出错</center>";
				<%
			}
		%>
		
		
	</script><%="]]>"%></component>
	<%
	}
	%><%@ include file="../common/componentfooter.jsp" %>
