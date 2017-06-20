# maximo导入文件
class 类由 kotlin编写 

## LIBRARY.xml 增加以下内容
```
	<dialog beanclass="org.shoukaiseki.webclient.beans.ImportExcleDataBean" id="importexcle" label="导入excle">
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
例子中不做保存,只进行了文件内容输出
