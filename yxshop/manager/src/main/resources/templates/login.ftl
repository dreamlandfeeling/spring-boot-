<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
<link href="/js/jquery-easyui-1.4.1/themes/gray/easyui.css" rel="stylesheet"/>
<link href="/js/jquery-easyui-1.4.1/themes/icon.css" rel="stylesheet"/>
<script src="/js/jquery-easyui-1.4.1/jquery.min.js"  ></script>
<script src="/js/jquery-easyui-1.4.1/jquery.easyui.min.js"  ></script>
</head>
<body style="background-color: #F3F3F3">
    <div class="easyui-dialog" title="管理员登录" data-options="closable:false,draggable:false" style="width:400px;height:300px;padding:10px;">
       	<div style="margin-left: 50px;margin-top: 50px;">
            <form id="loginForm" action="/login" method="post">
       		<div style="margin-bottom:20px;">
	            <div>
	            	用户名: <input name="username" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" value="admin"/>
	            </div>
	        </div>
	        <div style="margin-bottom:20px">
	            <div>
	            	密&nbsp;&nbsp;&nbsp;码: <input name="password" class="easyui-textbox" type="password" style="width:200px;height:32px" data-options="" value="admin"/>
	            </div>
	        </div>
	        <div>
	            <a id="login" class="easyui-linkbutton" iconCls="icon-ok" style="width:100px;height:32px;margin-left: 50px">登录</a>
	        </div>
			<div style="color: red;">
				${error!}
			</div>
            </form>
       	</div>
    </div>
    
    <script type="text/javascript">
    	$("#login").click(function(){
    		$("#loginForm").submit();
    	});
    </script>
</body>
</html>