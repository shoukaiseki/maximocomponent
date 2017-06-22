# maximo导入文件
maximo导入文件,只提供使用方式,因为这些文件只做处理,代码更新没有必要了,更新之后会在另外目录更新完整的excle导入方案
<br>
class 类由 kotlin编写 

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
在页面中增加按钮,事件设置为 importexcle 就好
<br />
例子中不做保存,只进行了文件内容输出
