项目简介
以springboot为基础的初级商城项目
后台：商品管理，首页内容管理，导入商品solr索引
前台：商品搜索，添加购物车，登录，订单(未完善)
技术：springboot+mybatis+redis+freemarker+solr+druid+

项目部署
修改application.properties 的数据库配置
图片上传路径默认IMG.UPLOAD.PATH=D:/test/
前台部分需要redis  如果redis有密码也在application.properties中修改(默认windows下没有密码)
运行application类
访问路径http://localhost:8080 和 http://localhost:8081

前台第一次登录的时候会比较慢可能要等20秒。。这里还不懂该怎么改卡在redis存用户信息的时候
还有就是数据库里的商品图片路径都使访问不到的。。你自己添加商品把

操作过程
后台：首页-商品添加——商品搜索——商品增删改查-修改前台轮播图的内容-导入商品solr索引
前台：首页-商品搜索-商品详情-加入购物车-提交订单


该项目原本是在视频中学习的，学了springboot后自己重新写了一遍，所以页面里有些坑