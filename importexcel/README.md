# maximo导入excel文件
maximo导入excel文件

<br>
主要 class 类由 kotlin编写 

例子使用java编写

1. 将 maximocomponent/maximolib 下面的 shoukaiseki.jar poi-3.8-20120326.jar
   将 maximocomponent/lib 下面的 kotlin-runtime.jar

3. 这几个jar添加到 maximo.ear/lib 目录下

4. 引入到  businessobjects.jar/META-INF/MANIFEST.MF 中添加  lib/kotlin-runtime.jar lib/shoukaiseki.jar lib/poi-3.8-20120326.jar 信息

5. 将 maximocomponent/importexcel/binversion 里面编译好的类放到 MAXIMO.ear/maximouiweb.war/WEB-INF/classes 下


## LIBRARY.xml 增加以下内容
```
	<dialog beanclass="org.shoukaiseki.webclient.beans.ImportExcelDataBean" id="importe" label="导入excle">
		<section id="importexcle_1">
			<importapp id="importexcle_1_8" label="浏览文件:"/>
		</section>
		<buttongroup id="importexcle_2">
			<doclinkuploadbutton id="importexcle_file_2_1" label="确定"/>
			<pushbutton id="importexcle_2_2" label="取消" mxevent="dialogcancel"/>
		</buttongroup>
	</dialog>
```
### 页面引用
在页面中增加签名和工具条,或者按钮,签名名称/按钮事件设置为 importe 就好

在该应用的appbean中引入 ExcelBuildActionCall 接口


# 例子1
事例xml文件,应用xml在 sample/skstestie001 下
导入数据为子表数据

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie001/skstestie001a.png)
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie001/skstestie001b.png)
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie001/skstestie001c.png)
![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/importexcel/sample/skstestie001/skstestie001d.png)