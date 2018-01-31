# maximo字段帮助加强版

<br>
如下图所示:

能看到字段绑定的 domainid(域名),classname(类名),lookup(应用程序设计器的查找名),和字段长度等信息

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E5%AD%97%E6%AE%B5%E5%B8%AE%E5%8A%A9%E5%8A%A0%E5%BC%BA%E7%89%88/img/001.png)

![image](https://raw.githubusercontent.com/shoukaiseki/maximocomponent/master/maximo%E5%AD%97%E6%AE%B5%E5%B8%AE%E5%8A%A9%E5%8A%A0%E5%BC%BA%E7%89%88/img/002.png)

## 加强版安装说明
maximo7.5or7.6 目录下的jsp文件复制到 maximouiweb 对应路径即可,适用于maximo7.5和7.6


### 加强版不生效处理方式
在fieldhelp.jsp复制过去的时候,如果不生效则因为jsp没有被重新编译成class,那么只需要用编辑器(推荐使用notepad++)打开这个jsp文件

然后在第4行行末加个回车

```
* 5724-U18
* 
* (C) COPYRIGHT IBM CORP. 2006, 2011 All Rights Reserved.

改成

* 5724-U18

* 
* (C) COPYRIGHT IBM CORP. 2006, 2011 All Rights Reserved.

或者中间随便加点其他字符

* 5724-U18
* kjkgsdlgksdhgksdhg胜多负少的看法拉萨路开发商法
* (C) COPYRIGHT IBM CORP. 2006, 2011 All Rights Reserved.
```

然后保存即可生效(如果不生效则采用加其他字符的方式)

因为jsp只有改动了之后才会重新编译,而覆盖的并不会被重新编译,而我们只需要覆盖文件之后再进行改动即可
