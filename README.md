## 项目简介

### 以springboot为基础的初级商城项目

后台：商品管理，首页内容管理，导入商品solr索引,(新增)shiro权限控制

前台：商品搜索，添加购物车，登录，(新增)用户收货地址,生成订单

技术：springboot+mybatis+druid+redis+freemarker+solr+shiro

开发环境: jdk1.8、idea、maven、mysql

## 注意事项
1.	前台登录需要redis,前台搜索需要solr
2.	solr 地址https://github.com/dreamlandfeeling/solr 因为有70m所以独立出来好了
3.	可暂时关闭solr功能  在solr索引库管理那有关闭solr.因为商品的增删改会同步更新索引，如果没有启动solr,
增删改会卡顿一会，并且程序内报错
4.	图片上传路径默认IMG.UPLOAD.PATH=D:/test/


## 项目部署
1.	修改application.properties 的数据库配置
2.	前台部分需要redis,如果你的redis有密码,也在application.properties中修改(默认windows下没有密码)
3.  如果用我的solr,需要配置'CATALINA_HOME8_8100'环境变量 tomcat的根目录
修改solrhome:tomcat下webapps/solr/WEB-INF的web.xml,solrhome在solr项目里.
再启动tomcat 路径http://localhost:8100/solr/index.html
4.	运行application类或者在项目根目录使用命令行mvn spring-boot:run
5.	访问路径http://localhost:8080 和 http://localhost:8081

数据库里的商品图片路径都使访问不到的。。你自己添加商品把

## 操作过程
后台：登陆-首页-商品添加——商品搜索——商品增删改查-修改前台轮播图的内容-导入商品solr索引

前台：首页-商品搜索-商品详情-加入购物车-设置收货地址-提交订单

项目第一次启动有solr的情况下，需要一键导入solr索引。
如果没有solr 就跳过搜索部分了，在地址栏输入localhost:8081/item/商品id

## shiro权限
1.	权限上加的暂时只有商品的增删改和内容管理的可见不可见
2.	默认admin admin	拥有全部权限
3.	普通commom common  只有商品增加   

## 其他
该项目原本是在视频中学习的，学了springboot后，就找了以前ssm项目给的静态资源和sql语句再自己重新写了一遍,再看情况加些功能

如果有其他问题可以发送邮件到876719539@qq.com或者加这个qq