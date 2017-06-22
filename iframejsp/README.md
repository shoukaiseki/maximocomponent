# maximocomponent
自定义maximo组件

## iframejsp(html的内联ifram框架,载入自定义jsp)
支持height设置为当前页面高度(maximo的page总高度)

```
<?xml version="1.0" encoding="utf-8"?>

<presentation id="testttt" ismobile="false" mboname="TESTBSHT" resultstableid="results_showlist" version="6.0.0" > 
  <page id="mainrec" scroll="false" height="100%" label="ASUS"> 
	  <!--
    <include controltoclone="pageHeader" id="INCLUDE-pageHeader"/>  
-->
    <clientarea id="clientarea" height="100%"> 
					<section id="2497536816057"  height="100%">
						<iframejsp id="iframejsp_001" height="pageheight" iframesrc="http://maximodecryption.duapp.com/"/>
						<!--height 如果为 pageheight 则显示高度为 整个页面的高度 js为 document.body.offsetHeight-10-->
						<!--
						<iframejsp id="iframejsp_001" width="100%" height="1000px" includejsp="../tuuyou/test.jsp"/>
						     -->
					</section>
    </clientarea>  
    <!--
    <include controltoclone="pageFooter" id="INCLUDE-pageFooter"/> 
-->
  </page>  
  <!--
  <configurationblock id="datastore_configurationblock"></configurationblock> 
-->
</presentation>

```

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/iframejsp/image/001.png)

```
<?xml version="1.0" encoding="utf-8"?>

<presentation id="testttt" ismobile="false" mboname="TESTBSHT" resultstableid="results_showlist" version="6.0.0" > 
  <page id="mainrec" scroll="false" height="100%" label="ASUS"> 
	  <!--
    <include controltoclone="pageHeader" id="INCLUDE-pageHeader"/>  
-->
    <clientarea id="clientarea" height="100%"> 
					<section id="2497536816057"  height="100%">
						<iframejsp id="iframejsp_001" width="100%" height="pageheight" includejsp="../../tuuyou/test.jsp"/>
					</section>
    </clientarea>  
  </page>  
</presentation>


```
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/iframejsp/image/002.png)
