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
--%><%
%><%@page contentType="text/html;charset=UTF-8" buffer="none"
%><%@page import="psdi.security.UserInfo"
%><%@page import="psdi.server.MXServer"
%><%@page import="psdi.webclient.components.Label"
%><%@page import="psdi.webclient.system.beans.DataBean"
%><%@page import="psdi.webclient.system.beans.ResultsBean"
%><%@page import="psdi.webclient.system.controller.AppInstance"
%><%@page import="psdi.webclient.system.controller.BoundComponentInstance"
%><%@page import="psdi.webclient.system.controller.ComponentInstance"
%><%@page import="psdi.webclient.system.controller.ControlInstance"
%><%@page import="psdi.webclient.system.controller.Hotkeys"
%><%@page import="psdi.webclient.system.controller.JCEncryptUtil"
%><%@page import="psdi.webclient.system.runtime.BidiClientUtils"
%><%@page import="psdi.webclient.system.session.WebClientSession"
%><%@page import="psdi.webclient.system.session.WebClientSession.Alignment"
%><%@page import="psdi.webclient.system.runtime.WebClientRuntime"
%><%@page import="psdi.util.BidiUtils"
%><%@page import="psdi.util.CalendarUtils"
%><%@page import="java.util.Date"
%><%@page import="java.util.Locale"
%><%@page import="psdi.util.MXFormat"
%><%@page import="java.util.Random"
%><%@page import="psdi.util.RichText"
%><%@page import="psdi.util.HTML"
%><%
ComponentInstance component = ComponentInstance.getCurrent(request);
ControlInstance control = component.getControl();
boolean onTableRow = control.isOnTableRow() || control.isOnTableFilterRow() || control.isOnTableTitleRow();
WebClientSession wcs = control.getWebClientSession();
String id = component.getRenderId();
Alignment alignment = wcs.getAlignment();
String controlType= control.getType();
AppInstance app = control.getPage().getAppInstance();
String IMAGE_PATH = "";
boolean componentVisible = component.isVisible();
String componentValue = null;
boolean hiddenFrame = Boolean.parseBoolean(request.getParameter("hiddenframe"));
boolean accessibilityMode = wcs.getAccessibilityMode();
boolean designmode = wcs.getDesignmode();
UserInfo userInfo = wcs.getUserInfo();
String langcode = userInfo.getLangCode();
String textcss = component.getProperty("textcss");
String cssclass = component.getCssClass();

boolean defaultRender = component.isDefaultRender();
if(defaultRender)
{
	%><%@ include file="../common/componentholder_begin.jsp" %><%
}

%><%@include file="../common/exceptioncontainericon.jsp"%><%

String isVisible = component.getProperty("visible");
boolean verticalLabels = wcs.useVerticalLabels();
if(component.getParentInstance() !=null) {
	verticalLabels = verticalLabels && !control.getProperty("labellayout").equals("horizontal") && component.getParentInstance().getBoolean("makevertical") && 
	!control.getProperty("hidelabel").equals("true");
}
else
{
	verticalLabels = false;
}
if(componentVisible && isVisible.equals("true"))
{
	String rownum = control.getProperty("rownum");
	String text = component.getProperty("title");
	String showblank = component.getProperty("showblank");
	boolean specialpreformattedtext = false;
	boolean preformattedtext = control.getProperty("preformattedtext").equalsIgnoreCase("true");
	if (preformattedtext)
	{
		specialpreformattedtext = true;
	}
	boolean htmltags = component.getProperty("accepthtmltags").equalsIgnoreCase("true");
	
	if(htmltags)
		preformattedtext = true;
	if(preformattedtext)
		htmltags = true;
	boolean hideWhenEmpty = component.getProperty("hidewhenempty").equalsIgnoreCase("true");
	String tooltip = component.getProperty("tooltip");
	if(WebClientRuntime.isNull(tooltip))
		tooltip = component.getProperty("mxevent_desc");
	String display = "display:block;";
	String padding = "padding-"+alignment.begin+":2px;padding-top:2px;";
	if(verticalLabels){
		padding = "";
	}
	String lineheight = "";
	if(designmode)
	{
		lineheight = "line-height:100%;";
	}
	int clickableState=component.getClickState();
	String designerEnabled = component.getProperty("designerenabled");
	String imageAlign = component.getProperty("imagealign");
	String imageVerticalAlign = component.getProperty("imagevalign");
	String displayStyle = component.getProperty("displaystyle");
	String align = component.getProperty("align");
	boolean includeuistatus = component.getProperty("includeuistatus").equalsIgnoreCase("true");
	String ariaRole = component.getProperty("ariarole");
	if(ariaRole.length()>0)
	{
		ariaRole=" role='"+ariaRole+"' ";
	}

	//Fix for Static Aligns
	if(alignment.rtl)
	{
		align = alignment.reverse(align);
	}

	boolean onInput = component.getProperty("oninput").equalsIgnoreCase("true");
	String ri_image = "";
	if(!displayStyle.equals(""))
		display = "display:" + displayStyle + ";";

	boolean isMasked = false;
	BoundComponentInstance boundComponent = null;
	if(component instanceof BoundComponentInstance)
	{
		boundComponent = (BoundComponentInstance)component;
		if(onInput)
		{
			boolean requiredLabel = !component.getProperty("requiredlabel").equalsIgnoreCase("false");
			if(requiredLabel)
			{
				String riImage = "";
				if(boundComponent.isRequired() && !boundComponent.isReadOnly())
					ri_image = "<img class='required_label' width='8px' height='8px' align='absmiddle' border='0' src='"+IMAGE_PATH+"required_label.gif' alt=''/>";				
			}
		}
		isMasked = boundComponent.isMasked();
	}
	String fieldSize = component.getProperty("size");
	if(fieldSize.equals(""))
		fieldSize = "0";

	int size = Integer.parseInt(fieldSize);
	if(component.isOnTableRow() && !preformattedtext)
	{
		if(size > 40)
			size = 40;
	}

	String intType= component.getProperty("inttype");
	int dataType = -1;
	if(!WebClientRuntime.isNull(intType))
		dataType = Integer.parseInt(intType);

	String tokennum = component.getProperty("tokennumber");
	String tokensep = component.getProperty("tokenseperator");

	boolean accessibleFocus = Boolean.valueOf(component.getProperty("accessiblefocus")).booleanValue();
	boolean heading = component.getProperty("useheading").equalsIgnoreCase("true") || accessibleFocus;
	boolean underline = component.getProperty("underlinelink").equalsIgnoreCase("true");
	String labelcss = component.getProperty("labelcss");
	if(!textcss.equals("") && labelcss.indexOf("text") == 0)
		labelcss = textcss + " " + labelcss.substring(4);
	boolean hidelabel = component.getProperty("hidelabel").equalsIgnoreCase("true");
	String colorStr = "";
	String imageSrc = "";
	boolean setsFocus = Boolean.valueOf(component.getProperty("setsfocus")).booleanValue();
	String disabled="";
	String msggroup = component.getProperty("msggroup");
	String msgkey = component.getProperty("msgkey");
	if(!WebClientRuntime.isNull(msggroup) && !WebClientRuntime.isNull(msgkey))
		text = wcs.getMaxMessage(msggroup, msgkey).getMessage();

	if (text == null)
		text = "";
	
	String cancelClickBubble = "";
	if(component.isOnTableRow())
		cancelClickBubble = " cancelEvent(event); ";
	boolean onTableDetails = control.getProperty("ontabledetails").equalsIgnoreCase("true");

	String[] bidiTagAttributes = {"","",""};
	if(BidiUtils.isBidiEnabled() && !controlType.equals("bulletinboardlink") && !controlType.equals("statictext") &&
			!controlType.equals("table-title") && !controlType.equals("breadcrumb")) 
	{
		bidiTagAttributes = BidiClientUtils.getTagAttributes(component,app,text,false);
		if(bidiTagAttributes[2] != null && bidiTagAttributes[2].length() > 0)
			text = BidiUtils.enforceBidiDirection(text,bidiTagAttributes[2]);
	}

	String elementType = "label";
	String helpImage = "";        
	if(component.isHoversActive())
	{
		%><%@ include file="../common/extendedtooltip.jsp" %><%
		tooltip = "";
	}
	if((controlType.equals("table-title") || controlType.equals("table-text")) && rownum != null && !rownum.equals(""))
	{
		display = "";
		padding = "padding:0px;padding-" + alignment.begin + ":1px;margin:0px;";
		if((Integer.parseInt(rownum)) >= 0)
		{
			text = boundComponent.getString();

			if(BidiUtils.isBidiEnabled() && !controlType.equals("table-title")  && !controlType.equals("breadcrumb")) {
				bidiTagAttributes = BidiClientUtils.getTagAttributes(component,app,text,false);
				if (BidiUtils.isChain(bidiTagAttributes[1])) {
					text = BidiUtils.processComplexexpression(text,bidiTagAttributes[2],bidiTagAttributes[1],WebClientRuntime.getMXSession(session));
				} else {
					text = BidiUtils.enforceBidiDirection(text,bidiTagAttributes[2]);
					text = BidiUtils.processComplexexpression(text,bidiTagAttributes[1],WebClientRuntime.getMXSession(session));
				}
			}

			if (dataType != MXFormat.DURATION && !control.getProperty("usefieldsizegroup").equals("false"))
			{
				if(text.length() > size)
				{
					elementType="div";
					padding="padding:0px;padding-"+alignment.begin+":1px;padding-top:5px;padding-bottom:5px;";
				}
			}
		}
	}
	if(tooltip.equals(""))
	{
		tooltip = text;
	}

	String image = component.getProperty("image");
	String tmpImage = component.getProperty("mxevent_icon");
	if(!WebClientRuntime.isNull(tmpImage))
		image=tmpImage;
	String imageFalse = component.getProperty("imagefalse");
	String mxevent = component.getProperty("mxevent");
	if(!mxevent.equals(""))
		mxevent = "click";
	if(clickableState == ComponentInstance.CLICKABLE_OFF)
	{
		colorStr = "color:#C0C0C0";
		disabled = "disabled='disabled'";
	}
	String targetId = component.getProperty("targetid");
	//The following allows us to target an event on something else if necessary
	if(targetId.indexOf(":") == 0)
		targetId = targetId.substring(1);
	else
		targetId = id;
	String eventValue = component.getProperty("eventvalue");
	String link = component.getProperty("href");

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

	// Story 06-13334
	if(component.isOnTableRow())
	{
		int curRow = (Integer.parseInt(rownum));
		DataBean curBean = component.getControl().getDataBean();
		if (curBean != null && curBean instanceof ResultsBean
			&& ((ResultsBean)curBean).isListTableRetain()
			&& ((ResultsBean)curBean).isRowDeleted(curRow))
		{
			clickableState =ComponentInstance.CLICKABLE_OFF;
		}
	}
	
	String makeLink = "";
	String accesskey = component.getProperty("accesskey");

	if (!WebClientRuntime.isNull(accesskey))
	{
		Hotkeys hotkeys = app.getHotkeys();
		accesskey = hotkeys.addHotkey(wcs,accesskey, id, "click");
		tooltip += " " + component.getProperty("accesskeytitle");
		// Hotkeys requiring only ALT + Key have the corresponding key highlighted in the label.
		// This should be only the main nav links (BBoard, Goto, Signout, Help, etc).
		if(hotkeys.getAltState(accesskey) == true && hotkeys.getCtrlState(accesskey) == false)
		{
			char ch = hotkeys.getAccessKey(accesskey);
			int index = text.toUpperCase().indexOf(ch);
			if(index >= 0)
			{
				ch = text.charAt(index);
				String labelBefore = text.substring(0, index);
				String labelAfter = text.substring(index + 1);
				text = "<span>"+labelBefore + "</span><span class='text hl hlak'>" + ch + "</span><span>" + labelAfter+ "</span>";
			}

			accesskey = "accesskey='" + ch + "'";
		}
	}

	String href = "";
	// Messages are already encoded in WebClientSession.generateMessageBox(), so decode first.
	tooltip = HTML.encodeTolerant(HTML.decode(tooltip));
	String imgAlt = "";
	if(!image.equals(""))
	{
		imgAlt = WebClientRuntime.removeQuotes(tooltip);
		padding = "";
		if(clickableState == ComponentInstance.CLICKABLE_OFF && !imageFalse.equals(""))
			image = imageFalse;

		if(image.contains("btn_help") && langcode.equalsIgnoreCase("HE"))
		{
			image = "../" + image;
		}

		imageSrc = IMAGE_PATH + image;
		String imagecss = component.getProperty("imagecss");
		if(!imagecss.equals(""))
			imagecss = "class='" + imagecss + "'";

		if(!imageVerticalAlign.equals(""))
			imageVerticalAlign = "vertical-align:" + imageVerticalAlign;
		else
			imageVerticalAlign = "vertical-align:top";
		image = "<img id='"+id+"_image' src='"+imageSrc+"' "+imagecss+" border='0' "+" style='"+imageVerticalAlign+";margin:0px;margin-"+alignment.begin+":3px;margin-"+alignment.end+":3px;' alt=''/>";
	}
	String ul= "";
	if(!link.equals("") && !isMasked)
	{
		elementType = "a";
		/**************************************************
		 * START Added/modified for  Attachment Security
		 **************************************************/
		String newlink = link;

		String securedattachment = MXServer.getMXServer().getConfig().getProperty("mxe.doclink.securedAttachment");
		if (securedattachment.equalsIgnoreCase("true")  && controlType.equals("table-text") )
		{
			if (link.indexOf("?_WWW") !=-1)
			{
				newlink = link.substring(0,link.length()-5);
			}
			else if (link.indexOf("?_tbldnld") == -1 && link.indexOf("javascript:") == -1)
			{
				if (request.getSession().getAttribute("randomSalt") == null)
				{
					Random random = new Random();
					byte salt[] = new byte[24];
					random.nextBytes(salt);
					request.getSession().setAttribute("randomSalt", salt);	
				}
				String encoding =  request.getCharacterEncoding();
				if (encoding == null)
					encoding = "UTF-8";
				JCEncryptUtil ec = new JCEncryptUtil((byte[])request.getSession().getAttribute("randomSalt"),encoding);
				String encServ = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/servlet/secureprovider";
				String fulllink="user=" + userInfo.getLoginID() + "\nurl=" + link;
				String encodedlink = ec.URLSafeEncrypt(fulllink);
				newlink = encServ + "?file=" + encodedlink + "&" + wcs.getUISessionUrlParameter() + wcs.getCSRFTokenParameter();
			}
		}

		if (newlink.indexOf("?_WWW") !=-1)
			newlink =  newlink.substring(0,link.length()-5);   

		newlink = HTML.encode(newlink.replace("\"", "\\\""));
		if (app.isdoclinkweblogic() && (!controlType.equals("statictext")) && (!link.startsWith("\\")) && (!link.startsWith("#")))
		{
			if(controlType.equals("hyperlink"))
			{
				href="href=\"" + "javascript:openURL('" + newlink +"')" +  "\" ";
			}
			else
			{
       			//IV51440: Allow apostrophes in attachment file names               
                            
                if(request.getHeader("User-Agent").indexOf("Chrome") >= 0 && newlink.startsWith("file")){
                    newlink=newlink.substring(5);
                }
				href="href=\"" + "javascript:openEncodedURL(&quot;" + newlink + "&quot;)" +  "\" ";
			}
		}
		else
		{
			if(link.startsWith("#")) {
				href="href=\"" + "Javascript: void(0);\" onclick=\"focusItemNow('" + newlink.substring(1) +"')" +  "\"; if(event.preventDefault) event.preventDefault(); else event.returnValue = false;";				
			}
			else {
				href = "href=\"" + newlink + "\" onmouseover=\"return noStatus();\"";
				href += " target=\"_blank\"";
			}
		}
		/************************************************
		 * END Added/modified for Attachment Security
		 ************************************************/
	}
	else if(!mxevent.equals("") && !isMasked)
	{
		if(accessibilityMode)
		{
			href = " noclick='1' onfocus='input_onfocus(event,this);' href='Javascript: setCurrentfocusFromId(event,\""+id+"\"); setClickPosition(document.getElementById(\""+id+"\"), event); sendEvent(\""+mxevent+"\",\""+id+"\",\""+eventValue+"\");' onclick='' ";
			elementType = "a";
		}
		else
			elementType = "span";
		href += " tabindex=\"0\" ";
		if(clickableState == ComponentInstance.CLICKABLE_ON)
		{
			makeLink += "cursor:pointer;";
			if((component.isOnTableRow() && !preformattedtext) || underline)
			{
				makeLink += "text-decoration:underline;";
				ul = "ul=\"1\"";
			}
		}
	}
	else
	{
		if(controlType.equals("section") && component.getParentInstance().getType().equals("headerbar") && onTableDetails)
			cssclass = "td" + cssclass;
	}
	// By default, labels accept HTML formatting
	if(!htmltags || component.isOnTableRow())
	{
		text = HTML.encodeTolerant(text);
	}
	else
	{
		if(WebClientRuntime.hasInvalidXMLChars(text))
		{
			text = WebClientRuntime.removeInvalidXMLChars(text);
		}
		// Clean any XSS code from the HTML
		text = RichText.cleanHtml(text, false, false, false);
	}
	
	// PP: This must come AFTER encodeTolerant, or <br> will be displayed literally if htmltags is false
	text = WebClientRuntime.replaceString(text, "\n", "<br/>");

	if((component.isOnTableRow() || showblank.equalsIgnoreCase("true")) && (text==null || text.trim().equals("")))
		text = "&nbsp;";

	if(!tokennum.equals(""))
	{
		text = WebClientRuntime.replaceString(text,tokensep," __ ");
		String tokens[] = text.split("__");
		for(int i=0;i < tokens.length;i++)
		{
			if(i == Integer.parseInt(tokennum))
			{
				text = tokens[i].trim();

				if(text.equals(""))
					text = "&nbsp;";
			}
		}
	}
	if(component.isOnTableRow() && text.equals("&nbsp;") && !image.equals(""))
		text = "";
	text = control.resolveParams(text);
	if(hidelabel)
		text = "&nbsp;";
	else if(!text.trim().equals("") && !text.trim().equals("&nbsp;"))
	{
		if(onInput || (controlType.equals("table-title") && dataType == MXFormat.YORN))
			text = component.formatLabel(text);
	}
	if(((!text.trim().equals("") && !text.equals("&nbsp;")) || showblank.equalsIgnoreCase("true")) || !ri_image.equals("") || !image.equals(""))
	{
		if(accessibilityMode)
		{
			String accessiblelabelkey = component.getProperty("accessiblelabelkey");
			if(!WebClientRuntime.isNull(accessiblelabelkey))
			{
				String accessiblelabelgroup = component.getProperty("accessiblelabelgroup");
				text = wcs.getMessage(accessiblelabelgroup, accessiblelabelkey, new String[]{text});
				if(htmltags || component.isOnTableRow())
				{
					tooltip = HTML.encodeTolerant(HTML.decode(text));
				}
				else
				{
					tooltip = text;
				}
			}
		
		}
		if((controlType.equals("table-title") || controlType.equals("table-text")) && rownum!=null && !rownum.equals("") || controlType.equals("longdescription"))
		{
			// 12-10876 - decode HTML characters for long description displayed on table row
			// 12-11755 - LONG DESC. ON LIST TAB SHOWS HTML FORMATTING
			if(control.getProperty("dataattribute").toLowerCase().indexOf("_longdescription")>0)
			{
				text = HTML.decode(text);
				text = HTML.toPlainText(text, true);
					
				tooltip = HTML.decode(tooltip);
				tooltip = HTML.toPlainText(tooltip, false);
			}
			boolean wrapText = component.getProperty("wraptext").equalsIgnoreCase("true") && Boolean.valueOf(WebClientRuntime.getWebClientProperty("webclient.wrapreadonlycolumns","true")).booleanValue();
			String wraplength = component.getProperty("wraplength");
			if(WebClientRuntime.isNull(wraplength))
				wraplength = WebClientRuntime.getWebClientProperty("webclient.wraplength", wraplength);
			if(WebClientRuntime.isNull(wraplength))//SAFETY
				wraplength = "40";
			if(wrapText)
			{
				String oldText = text;
				String upperText = text.toUpperCase();
				if(!upperText.contains("<BR>") && !upperText.contains("<BR/>"))
				{
					// IV38466 - Need to decode the text before wrapping it, then encode it again afterwards
					text=HTML.decode(text);
					text=((Label)component).wrapText(text,Integer.parseInt(wraplength));
					text=HTML.encodeTolerant(text);
				}
				// Do not modify alignment for text if in middle 
				if(!text.equals(oldText) && !"middle".equals(align))
				{
					align = alignment.begin;
					if (BidiUtils.isBidiEnabled() && BidiUtils.isLTRExpression(bidiTagAttributes[1]))
						align = "left";
					if(link.length()==0)
						elementType = "div";
					else
						elementType = "a";
					
					padding = "padding:0px;padding-"+alignment.begin+":1px;padding-top:5px;padding-bottom:5px;";
				}
			}
			else
			{
				if(HTML.decode(text).length() > Integer.parseInt(wraplength))
				{
					// Need to decode the text before truncating it, then encode it again afterwards
					text = HTML.decode(text);
					text = text.substring(0, Integer.parseInt(wraplength)-4) + "...";
					text = HTML.encodeTolerant(text);
				}
			}
		}

		cssclass+=" "+labelcss;
		if(cssclass.indexOf(" "+textcss)==-1 && cssclass.indexOf(textcss+" ")==-1)
			cssclass = textcss+" "+cssclass;
		String noclick = "";
		if(isMasked || clickableState==ComponentInstance.CLICKABLE_OFF || mxevent.equals(""))
		{
			noclick = "noclick=\"1\"";
			if(clickableState == ComponentInstance.CLICKABLE_OFF)
				href = "href=\"Javascript: void(0);\"";
		}
		if(noclick.length()==0) {
			cssclass+=" anchor";
		}
		if(!cssclass.trim().equals(""))
			cssclass = "class=\""+cssclass+"\"";
		if(!eventValue.equals(""))
			eventValue = "ev=\""+eventValue+"\"";
		if(!mxevent.equals(""))
			mxevent = "mxevent=\""+mxevent+"\"";
		if(!targetId.equals(""))
			targetId = "targetid=\""+targetId+"\"";
		if(designerEnabled.equals("true"))
			designerEnabled = "de=\"1\"";
		else
			designerEnabled = "";
		if(!align.equals("") && !verticalLabels)
			align = "align='"+align+"'";
		if(!hideWhenEmpty || !text.trim().equals("") || !text.trim().equals("&nbsp;"))
		{
			int controlTitleIndex = tooltip.indexOf("{control}");
			if(controlTitleIndex >= 0)
			{
				String fieldValue = ((BoundComponentInstance)component).getString();
				if(fieldValue.equals(""))
					fieldValue = "__";
				tooltip=WebClientRuntime.replaceString(tooltip, "{control}", HTML.encodeTolerant(fieldValue));
			}

			if(specialpreformattedtext)
			{
				text = WebClientRuntime.decodeSafevalue(text);
			}

			boolean isDate = (dataType == MXFormat.DATE || dataType == MXFormat.DATETIME);

			String dojoTypeInput = "";
			String userCalendar = userInfo.getCaltype().toLowerCase();
			boolean isNational = !CalendarUtils.GREGORIAN.equalsIgnoreCase(userCalendar);
			if(isDate && isNational)
			{
				Date date = null;
				Locale userLocale = userInfo.getLocale();
				String datevalue = boundComponent.getString();
				try
				{
					if(dataType == MXFormat.DATE)
					{
						MXFormat.stringToDate(text,userLocale);
						date = MXFormat.stringToDate(datevalue,userLocale);
					}
					else if(dataType == MXFormat.DATETIME)
					{
						MXFormat.stringToDateTime(text,userLocale);
						date = MXFormat.stringToDateTime(datevalue,userLocale);
					}

					String usrLocale = userLocale.toString().toLowerCase().replace('_', '-');
					String dojoDatePattern = "constraints=\"{";
					String dojoLang = "lang=\"" + usrLocale + "\"";
					String dojoPackage = psdi.util.CalendarUtils.getDojoDatePackage(userCalendar);
					String datePackage = "datePackage=\"" + dojoPackage + "\"";
%>					<script>
						dojo.require('<%=dojoPackage%>');
						dojo.require('<%=dojoPackage%>.locale');		
						//dojo.requireLocalization('dojo.cldr', '<%=userCalendar%>','<%=usrLocale%>','ROOT');
					</script>
<%					if(dataType == MXFormat.DATE)
					{
						dojoDatePattern += "datePattern:'" + MXFormat.getDatePattern(userLocale).replace("'","\\'") + "',selector:'date'";
					}
					else if(dataType == MXFormat.DATETIME)
					{
						dojoDatePattern += "datePattern:'" + MXFormat.getDatePattern(userLocale).replace("'","\\'") + "', timePattern:'" + MXFormat.getDisplayTimePattern(userLocale).replace("'","\\'") + "'";
					}
					
					dojoDatePattern+=",locale:'"+usrLocale+"'}\"";
					dojoTypeInput = dojoTypeInput + " " + dojoDatePattern;
					dojoTypeInput = dojoTypeInput + " " + datePackage + " " +dojoLang;
					if(date!=null)
					{
						dojoTypeInput = dojoTypeInput + "dojovalue='" + date.getTime() + "'";
					}
				}
				catch(Exception e)
				{
					isDate = false;
					dojoTypeInput = "";
				}
			}

			if(clickableState == ComponentInstance.CLICKABLE_ON)
				component.setProperty("takesfocus","true");	
			if(!component.getType().equals("tabletext") || href.length()>0){
				if(heading && component.needsRender())
				{
					%><h1 labelledby="<%=id%>_h1" style="display:inline"><%
				}
	            String presentation = "";
	            if(accessibilityMode && heading){
	                presentation = "role=\"presentation\" ";
	            }
				String topMargin = "";
				if(verticalLabels) { //used to space the vertical labels a little better
					topMargin = "margin-top: 5px;";
				}
				
				String action = "";
				if (controlType.equals("checkbox") && elementType.equals("label"))
				{
					display = "display:table;";
					//action = "onclick=document.getElementById('"+component.getLabelForRenderId()+"').click()";
				}
				
				String cType = component.getProperty("ctype");
				%><<%=elementType%> id="<%=id%>" <%if(!verticalLabels){%><%=align%> <%}%>ctype="<%=cType%>"<%
						%> <%=disabled%> <%=ul%> <%=href%> <%=designerEnabled%> <%=presentation%><%=noclick%><%
						%> <%=eventValue%> <%=action%> <%=targetId%> <%=ariaRole%> <%=mxevent%> <%=accesskey%> <%=cssclass%><%
						%> style="<%=display%><%=padding%><%=lineheight%><%=makeLink%><%=colorStr%><%=topMargin%>"<%
						if(elementType.equals("label"))
						{
							String labelFor = component.getLabelForRenderId();
							if(labelFor != null)
							{
								%> for="<%=labelFor%>"<%
							}
						}
						ControlInstance grandParent = (ControlInstance)control.getParentInstance().getParentInstance();
						boolean needsTabIndex = (grandParent!=null && grandParent.getType().equals("tableemptyrow"));
						if(accessibilityMode && (accessibleFocus || needsTabIndex))
						{
							%> tabindex="0"<%
						}
						if(wcs.isAutomationOn())
						{
							// for issue with webreplay. Caused by changing hyperlink in titlebar to a gotolink
							if(controlType.equals("gotolink"))
							{
								%> automationid="<%=WebClientRuntime.replaceString(component.getId(), "-gotolink_", "-hyperlink_")%>"<%
							}
							else
							{
								%> automationid="<%=component.getId()%>"<%
							}
						}
						if(imgAlt.length() > 0)
						{
							%> title="<%=imgAlt%>"<%
						}
						if(isDate&&isNational)
						{
							%><%=dojoTypeInput%><%
						}
				%>><%
					%><%=image%><%
					if(includeuistatus)
					{
						%><%@ include file="../common/uistatusindicator.jsp" %><%
					}
					%><%=ri_image%><%=error_icon%><%
					if(!(isDate&&isNational))
					{
						%><%if(!image.equals("")){%><span><%}%><%=text%><%if(!image.equals("")){ %></span><%}%><%
					}
				%></<%=elementType%>><%=helpImage%><%
	
				if(heading && component.needsRender())
				{
					%></h1><%
				}
			}
			else {
				%><%=text%><%=helpImage%><%
			}
			if(isDate&&isNational)
			{
%>			<script>
				addLoadMethod("formatCalendarLabel('<%=id%>');");
			</script>
<%			}
		}
	}
	else
	{
		if(component.isOnTableRow())
		{
		%> <%		
		}
	}
}
%><%@ include file="../common/componentfooter.jsp" %>
