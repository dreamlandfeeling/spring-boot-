<!DOCTYPE html >
<html xmlns:th="http://www.thymeleaf.org">
<input id="search" class="easyui-searchbox" style="width:250px,height:100px" data-options="searcher:search,prompt:'请输入搜索内容'"></input>
<div id="toolbar" style="height:auto">
   <@shiro.hasPermission name="add"><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a></@shiro.hasPermission>
   <@shiro.hasPermission name="update"><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">编辑</a></@shiro.hasPermission>
   <@shiro.hasPermission name="delete"><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="remove()">删除</a></@shiro.hasPermission>
   <@shiro.hasPermission name="delete"><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="downItem()">下架</a></@shiro.hasPermission>
   <@shiro.hasPermission name="delete"><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="upItem()">上架</a></@shiro.hasPermission>
</div>
<table class="easyui-datagrid" id="itemList" title="商品列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/item/',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id',width:60">商品ID</th>
        <th data-options="field:'title',width:200">商品标题</th>
        <th data-options="field:'cid',width:40,hidden:'true'">类目</th>
        <th data-options="field:'itemCat',width:100,align:'center'"formatter="formatItemCatName">商品类型</th>
        <th data-options="field:'sellPoint',width:150">卖点</th>
        <th data-options="field:'price',width:80,align:'right',formatter:E3.formatPrice">价格</th>
        <th data-options="field:'num',width:70,align:'right'">库存数量</th>
        <th data-options="field:'barcode',width:80">条形码</th>
        <th data-options="field:'status',width:60,align:'center',formatter:E3.formatItemStatus">状态</th>
        <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
        <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
    </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑商品"
     data-options="modal:true,closed:true,iconCls:'icon-save',href:'/item/item-edit'"
     style="width:80%;height:80%;padding:10px;">
</div>

<script>
    function formatItemCatName(val,row,index){
        return row.itemCat.name;
    }

    function search(){
        <!-- 给表格增加请求参数 -->
        var data = $('#search').val();
        $('#itemList').datagrid({
            queryParams:{
                search:data
            }
        })
        $('#itemList').datagrid('reload');
    }

    function getSelectionsIds() {
        var itemList = $("#itemList");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

    var toolbar = $("toolbar");
    function append() {
        $(".tree-title:contains('新增商品')").parent().click();
    }
    function update() {
        var ids = getSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '必须选择一个商品才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一个商品!');
            return;
        }

        $("#itemEditWindow").window({
            onLoad: function () {
                //回显数据
                var data = $("#itemList").datagrid("getSelections")[0];
                data.priceView = E3.formatPrice(data.price);
                $("#itemeEditForm").form("load", data);
                // 加载商品描述
                $.getJSON('/item/desc/' + data.id, function (_data) {
                    if (_data.status == 200) {
                        //UM.getEditor('itemeEditDescEditor').setContent(_data.data.itemDesc, false);
                        itemEditEditor.html(_data.data.itemDesc);
                    }
                });
                E3.init({
                    "pics": data.image,
                    "cid": data.cid,
                    fun: function (node) {
                        E3.changeItemParam(node, "itemeEditForm");
                    }
                });
            }
        }).window("open");
    }
    function remove(){
        var ids = getSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '未选中商品!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的商品吗？', function (r) {
            if (r) {
                var params = {"ids": ids};
                $.ajax({
                    type: "delete", url: "/item/batch", data: params, success: function (data) {
                        if (data.status == 200) {
                            $.messager.alert('提示', '删除商品成功!', undefined, function () {
                                $("#itemList").datagrid("reload")
                            });
                        }
                    },dataType:"json"
                });
            }
        });
    }
function downItem() {
    var ids = getSelectionsIds();
    if (ids.length == 0) {
        $.messager.alert('提示', '未选中商品!');
        return;
    }
    $.messager.confirm('确认', '确定下架ID为 ' + ids + ' 的商品吗？', function (r) {
        if (r) {
            var params = {"ids": ids,"status":2};
            $.ajax({
                type: "patch", url: "/item/"+ids, data: params, success: function (data) {
                    if (data.status == 200) {
                        $.messager.alert('提示', '下架商品成功!', undefined, function () {
                            $("#itemList").datagrid("reload");
                        });
                    }
                },dataType:"json"
            });
        }
    });
}
function upItem() {
    var ids = getSelectionsIds();
    if (ids.length == 0) {
        $.messager.alert('提示', '未选中商品!');
        return;
    }
    $.messager.confirm('确认', '确定上架ID为 ' + ids + ' 的商品吗？', function (r) {
        if (r) {
            var params = {"ids": ids,"status":1};
            $.ajax({
                type: "patch", url: "/item/"+ids, data: params, success: function (data) {
                    if (data.status == 200) {
                        $.messager.alert('提示', '上架商品成功!', undefined, function () {
                            $("#itemList").datagrid("reload");
                        });
                    }
                },dataType:"json"
            });
        }
    });
}
</script>
</html>