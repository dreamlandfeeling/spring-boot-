<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>订单结算页 -宜立方商城</title>
    <!--结算页面样式-->
    <link rel="stylesheet" type="text/css" href="/css/jquery.alerts.css?v=20160713"/>
    <link rel="stylesheet" type="text/css" href="/css/head.css?v=20160713"/>
    <link rel="stylesheet" type="text/css" href="/css/order.css?v=20160713">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/icon.css">
    <script type="text/javascript" src="/js/jquery-1.5.1.min.js?v=20160713"></script>
    <script type="text/javascript" src="/js/jquery.alerts.js?v=20160713"></script>
    <script type="text/javascript" src="/js/cart.js?v=20160713"></script>
    <script type="text/javascript" src="/js/cookie.js?v=20160416222"></script>
    <script type="text/javascript" src="/js/shadow.js?v=20160416"></script>
    <script src="/js/common.js?v=20160713" type="text/javascript"></script>
    <script src="/js/jquery.region.js?v=20160713" type="text/javascript"></script>
    <script src="/js/order.js?v=20160713" type="text/javascript"></script>
    <script type="text/javascript" src="http://www.jeasyui.net/Public/js/easyui/jquery.easyui.min.js"></script>
    <style>
        .required{
            color:red;
        }
        .shipping {
            width: 120px;
            height: 20px;
        }

        #addressForm div {
            margin-top: 20px;
            margin-bottom: 20px;
        }

        #addressForm input {
            width: 200px;
        }

        .addrFont {
            display: inline-block;
            width: 100px;
            text-align: right;
        }
    </style>
    <script>
        $(function () {
            $('input.easyui-validatebox').validatebox('disableValidation')
                    .focus(function () { $(this).validatebox('enableValidation'); })
                    .blur(function () { $(this).validatebox('validate') });
        })
        function showAddAddressWindow() {
            $('#addAddress').window('open');
        }

        function closeAddAddressWindow() {
            $('#addAddress').window('close');
        }

        function submitAddressform() {
            if ($('#addressForm').form('validate')) {
                closeAddAddressWindow();
                var id = $('#update_id').val();
                if(id == "" || id == null || id == undefined){
                    $.post('/user/address', $('#addressForm').serialize(),
                            function (data) {
                                addAddress(data.data);
                                $('#addressForm input').val('');
                            }, 'json');
                }else{
                    $('#addr_' + id).remove();
                    $.ajax({type:"put",url:'/user/address/'+id, data:$('#addressForm').serialize(),
                        success:function (data) {
                            addAddress(data.data);
                            $('#addressForm input').val('');
                        }, dataType:'json'});
                }

            }
        }

        function addAddress(data) {
            var id = $('#update_id').val();
            if(id!=null|| id != undefined || id != ""){
                $('#addr_' + id).remove();
            }
            var name = $('#name').val();
            var phone = $('#phone').val();
            var province = $('#province').val();
            var city = $('#city').val();
            var district = $('#district').val();
            var address = $('#address').val();
            $('#addressList').append("<li id='addr_" + data + "'><input type='radio' value='" + data + "' name='addrId'> " +
                    name + "&nbsp;&nbsp;" + phone + "&nbsp;&nbsp;" + province + "&nbsp;&nbsp;" + city + "&nbsp;&nbsp;\n" +
                    district + "&nbsp;&nbsp;" + address + "&nbsp;&nbsp;<a><span class='update' style='display:inline;color:blue;cursor:pointer;' onclick='updateAddress(" + data + ")'>修改地址</span></a>" +
                    "&nbsp;&nbsp;<a><span class='delete' style='display:inline;color:red;cursor:pointer;' onclick='deleteAddress(" + data + ")'>删除</span></a></li>");
        }

        function deleteAddress(id) {
            if (confirm('是否删除该地址')) {
                $('#addr_' + id).remove();
                $.ajax({url: "/user/address/" + id, data: {"id": id}, type: "delete"});
            }
        }

        function updateAddress(id) {
            $.get("/user/address/" + id, {"id": id}, function (data) {
                if (data.status == 200) {
                    showUpdateAddress(data.data);
                }
            }, "json");
        }

        function showUpdateAddress(data) {
            $('#update_id').val(data.id);
            $('#name').val(data.name);
            $('#phone').val(data.phone);
            $('#province').val(data.province);
            $('#zipCode').val(data.zipCode);
            $('#city').val(data.city);
            $('#district').val(data.district);
            $('#address').val(data.address);
            showAddAddressWindow();
        }
    </script>
</head>
<body id="mainframe">
<#include "/commons/header.ftl" />
<div id="addAddress" class="easyui-window" title="添加/修改收货地址" closed="true" style="width:500px;height:500px;">
    <form id="addressForm" style="padding:20px 30px 20px 30px;">
        <input type="hidden" name="id" id="update_id">
        <div style="text-align: center;">收货人信息</div>
        <div><span class="addrFont">姓名:<span class="required">*</span></span>
            <input class="easyui-validatebox textbox shipping" name="name" id="name" required="true" '/></div>
        <div><span class="addrFont">联系方式:<span class="required">*</span></span>
            <input class="easyui-validatebox textbox shipping" placeholder="手机或电话号码" name="phone" id="phone"
                   ' required="true"/>
        </div>
        <div><span class="addrFont">邮政编码:</span>
            <input class="textbox shipping" name="zipCode" id="zipCode"/></div>
        <div><span class="addrFont">省份:<span class="required">*</span></span>
            <input class="easyui-validatebox textbox shipping" name="province" id="province" required="true"  '/></div>
        <div><span class="addrFont">城市:<span class="required">*</span></span>
            <input class="easyui-validatebox textbox shipping" name="city" required="true" id="city" '/></div>
        <div><span class="addrFont">县区:<span class="required">*</span></span>
            <input class="easyui-validatebox textbox shipping" name="district" required="true" id="district"/></div>
        <div><span class="addrFont">详细地址:<span class="required">*</span></span>
            <input class="easyui-validatebox textbox shipping" name="address" required="true" id="address"/></div>
        <div style="padding:5px;text-align:center;">
            <a href="#" class="easyui-linkbutton" style="height: 28px;width: 40px;" onclick="submitAddressform()">保存</a>
        </div>
    </form>
</div>
<div class="orderMain">
    <form id="orderForm" action="/order/" method="post">
        <input type="hidden" name="order.payment" value="${totalPrice!?c}"/>
        <input type="hidden" name="order.paymentType" value="2"/>
	<#list cartList as cart>
		<input type="hidden" name="orderItems[${cart_index}].itemId" value="${cart.id?c}"/>
		<input type="hidden" name="orderItems[${cart_index}].num" value="${cart.num?c }"/>
		<input type="hidden" name="orderItems[${cart_index}].price" value="${cart.price?c}"/>
		<input type="hidden" name="orderItems[${cart_index}].totalFee" value="${(cart.price * cart.num!)?c}"/>
		<input type="hidden" name="orderItems[${cart_index}].title" value="${cart.title}"/>
		<input type="hidden" name="orderItems[${cart_index}].picPath" value="${cart.images[0]}"/>
    </#list>

        <h3 class="orderHd">填写并核对订单信息</h3>
        <div id="userAddrId" class="orderTbody ">
            <h3 class="orderTitle">收货人信息：<a><span class="modify" style="display:inline;color:red;cursor:pointer;"
                                                  onclick="showAddAddressWindow()">[添加]</span></a></h3>
            <div class="orderItem">
                <div class="orderCurr" id="userAddress" style="display:block;">
                    <ul id="addressList">
                    <#list addrList! as addr>
                        <li id="addr_${addr.id}"><input type="radio" value="${addr.id}" name="addrId">
                            ${addr.name}&nbsp;&nbsp;${addr.phone}&nbsp;&nbsp;${addr.province}&nbsp;&nbsp;${addr.city}
                            &nbsp;&nbsp;
                            ${addr.district}&nbsp;&nbsp;${addr.address}&nbsp;&nbsp;
                            <a><span class='update' style='display:inline;color:blue;cursor:pointer;'
                                     onclick='updateAddress(${addr.id})'>修改地址</span></a>&nbsp;&nbsp;
                            <a><span class='delete' style='display:inline;color:red;cursor:pointer;'
                                     onclick='deleteAddress(${addr.id})'>删除</span></a></li>
                    </#list>
                    </ul>
                </div>
            </div>
        </div>
    </form>
    <div id="payDataId" class="orderTbody">
        <h3 class="orderTitle">支付及配送方式：
            <span class="addrAlter hide" style="display: inline;">[修改]</span>
        </h3>
        <div class="orderItem ">
            <div class="orderCurr" style="display: block;">
                <div class="payment" id="payshow">在线支付</div>
                <div class="shipment">
                    <div class="shipOrder" id="ylsorder2">
                        <div class="sOrders">
                            <span class="tOrder" na="chai1">订单1</span>
                            <div class="sOrders">
                                <div class="scon">
                                    <strong>宜立方速运</strong>负责配送。<br>商品下单后尽快为您发货
                                </div>
                            </div>
                            <span class="clear"></span>
                        </div>
                    </div>
                    <span class="clear"></span>
                </div>
            </div>
        </div>
    </div>

    <div id="userInvoiceId" class="orderTbody">
        <a name="userInvoice"></a>
        <h3 class="orderTitle">发票信息：<span class="addrAlter" id="invoice_edit" onclick="invoice_edit()">[修改]</span></h3>
        <div class="orderItem">
            <div class="orderCurr invMsg">
                <div id="p_inv_type">暂不需要发票</div>
                <div id="p_inv_title"></div>
                <div id="p_inv_content"></div>
            </div>
        </div>
    </div>
    <div class="orderTbody" style="width:958px;border-bottom:0 none;">
        <div class="backToCart"><a style=" text-decoration:underline;" target="" href="/cart/index/">返回修改购物车</a></div>
        <h3 class="orderTitle">商品信息：</h3>
        <span class="clear"></span>
        <div class="orderPItem">
            <div id="producthtml">

                <div id="chaidanorder2" class="">
                    <div class="orderItem">
                        <div class="dateShow">
                            <strong>商品下单后尽快为您发货</strong>
                        </div>
                        <div class="orderTbl">
                            <div class="orderThead">
                                <div class="tCol">商品图片</div>
                                <div class="tCol pGoods">商品名称</div>
                                <div class="tCol pPrice">单价</div>
                                <div class="tCol pPromotion">返现</div>
                                <div class="tCol pInventory">库存状态</div>
                                <div class="tCol pQuantity">数量</div>
                                <div class="tCol pWeight">重量(含包装)</div>
                                <div class="tCol pSubtotal">小计</div>
                            </div>
                            <form method="post" action="/order/">
                                <input type="hidden" value="${ids!}" name="ids">
                                <input type="hidden" name="addressId">
                                <#list cartList as cart>
                                    <div class="orderPInfo">
                                        <div class="pItem">
                                            <a href="http://localhost:8081/item/${cart.id?c}" target="_blank">
                                                <img src="${cart.images[0] }" style="width: 60px;height: 60px;"/>
                                            </a>
                                        </div>
                                        <div class="pItem pGoods">
                                            <a href="http://localhost:8081/item/${cart.id?c}"
                                               target="_blank">${cart.title }</a>
                                        </div>
                                        <div class="pItem pPrice" style="position: relative; z-index: 99">
                                            ¥${cart.price?c}
                                        </div>
                                        <div class="pItem pPromotion">¥0</div>
                                        <div class="pItem pInventory" pid="57111">现货</div>
                                        <div class="pItem pQuantity">${cart.num?c}</div>
                                        <div class="pItem pWeight">0.72kg</div>
                                        <div class="pItem pSubtotal">¥${(cart.price * cart.num!)?c}</div>
                                        <span class="clear"></span>
                                    </div>
                                </#list>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="orderTFoot">
        <div id="orderSaveTip" class="ct"></div>
        <div class="submitOrder">
            <div class="orderSubmit">
                <input id="save" name="save" onclick="$('#orderForm').submit()" class="submitBtn" value="提交订单 "
                       type="button"/>
            </div>
            <div class="orderAccount">
                <span class="t">应付总额：</span>
                <span class="p">¥</span>
                <span id="countPrice" class="p">${(totalPrice!)?c}
                </span>
            </div>
            <span class="clear"></span>
        </div>
    </div>
</div>
<!-- /main -->
<#include "/commons/footer.ftl" />
</body>
</html>