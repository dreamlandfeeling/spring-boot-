<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
   <meta http-equiv="pragma" content="no-cache">
   <meta http-equiv="cache-control" content="no-cache">
   <meta http-equiv="expires" content="0"> 
   <meta name="format-detection" content="telephone=no">  
   <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
   <meta name="format-detection" content="telephone=no">
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" type="text/css" href="/css/jquery.alerts.css?v=20160713" />
<link rel="stylesheet" type="text/css" href="/css/head.css?v=20160713" />
<link rel="stylesheet" type="text/css" href="/css/cart.css?v=20160630">
<link rel="stylesheet" type="text/css" href="/css/common.css?v=201321222" />
<script type="text/javascript" src="/js/jquery-1.5.1.min.js?v=20160713"></script>
<script type="text/javascript" src="/js/jquery.price_format.2.0.min.js"></script>
<script type="text/javascript" src="/js/jquery.alerts.js?v=20160713"></script>
<script type="text/javascript" src="/js/cart.js?v=20160713"></script>
 <script type="text/javascript" src="/js/cookie.js?v=20160416222"></script>
<script type="text/javascript" src="/js/shadow.js?v=20160416"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script>
</script>
   <title>我的购物车 - 宜立方商城</title>
<body> 
<#include "/commons/header.ftl" />
<form id="cartForm" action="/order/" method="get">
    <input type="hidden" id='ids' name="ids">
</form>
<div class="cartMain">
	<div class="cartHead">
		<h3 class="cartMy">我的购物车</h3>
		<div class="clear"></div>
	</div>
	<div>
        您的购物车还是空的
    </div>
    <div>
        <input onclick="javascript:document.location.href='http://localhost:8081'" class="goshop" value="继续购物" type="button">
    </div>
</div>
<!-- footer start -->
<#include "/commons/footer.ftl" />
<!-- footer end -->

</html>