<!DOCTYPE html >
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
<head>
<meta charset="UTF-8"/>
<title>商城后台管理系统</title>
<link href="/css/e3.css" rel="stylesheet"/>
<link href="/css/default.css" rel="stylesheet" />
<link href="/js/jquery-easyui-1.4.1/themes/gray/easyui.css" rel="stylesheet"/>
<link href="/js/jquery-easyui-1.4.1/themes/icon.css" rel="stylesheet"/>
<script src="/js/common.js?v=1"  ></script>
<script src="/js/jquery-easyui-1.4.1/jquery.min.js"  ></script>
<script src="/js/jquery-easyui-1.4.1/jquery.easyui.min.js"  ></script>
<script src="/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"  ></script>

<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>
<script>
    function layout() {
        if(confirm("是否退出")){
            window.location.href="/quit";
        }
    }
</script>
</head>
<body class="easyui-layout">
    <!-- 头部标题 -->
	<div data-options="region:'north',border:false" style="height:60px; padding:5px; background:#F3F3F3"> 
		<span class="northTitle">后台管理系统</span>
	    <span class="loginInfo">登录用户：<@shiro.principal property="name"/>&nbsp;&nbsp;
            <a class="easyui-linkbutton" onclick="layout()">退出</a>
        </span>
	</div>
    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>商品管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'/item/item-add'}">新增商品</li>
	         		<li data-options="attributes:{'url':'/item/item-list'}">查询商品</li>
	         		<li data-options="attributes:{'url':'/item/item-param-list'}">规格参数</li>
	         	</ul>
         	</li>
         	<li>
         		<span>网站内容管理</span>
         		<ul>
                    <li data-options="attributes:{'url':'/content/content-category'}">内容分类管理</li>
					<@shiro.hasRole name="超级管理员"><li data-options="attributes:{'url':'/content/content'}">内容管理</li></@shiro.hasRole>
	         	</ul>
         	</li>
            <#--<li>-->
                <#--<span>系统工具</span>-->
                <#--<ul>-->
                    <#--<li data-options="attributes:{'url':'/swagger-ui.html'}">swagger</li>-->
                    <#--<li data-options="attributes:{'url':'/druid'}">运行监控</li>-->
                <#--</ul>-->
            <#--</li>-->
         	<li>
         		<span>索引库管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'/item/index-item'}">solr索引库维护</li>
	         	</ul>
         	</li>
         </ul>
    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
		        	
		    </div>
		</div>
    </div>
    <!-- 页脚信息 -->
	<div data-options="region:'south',border:false" style="height:20px; background:#F3F3F3; padding:2px; vertical-align:middle;">
		<span id="sysVersion">系统版本：V1.0</span>
	    <span id="nowTime"></span>
	</div>
<script >
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});
setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
</script>
</body>
</html>