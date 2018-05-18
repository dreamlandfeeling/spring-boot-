<div>
    <a class="easyui-linkbutton" onclick="startSolr()">启动solr</a>
    <a class="easyui-linkbutton" onclick="stopSolr()">停止solr</a>
</div>
<div>
    <a class="easyui-linkbutton" onclick="importItems()">一键导入商品数据到索引库</a>
    <a class="easyui-linkbutton" onclick="deleteItems()">清空索引库</a>
</div>
<script type="text/javascript">

    function importItems() {
        $.post("/solr/", null, function (data) {
            if (data.status == 200) {
                $.messager.alert('提示', '导入索引库成功！');
            } else {
                $.messager.alert('提示', '导入索引库失败！(solr未启动)');
            }
        },"json");
    }

    function startSolr() {
        $.post("/solr/status", {'solr':'start'}, function (data) {
            if (data.status == 200) {
                $.messager.alert('提示', 'solr启动！');
            }
        },"json");
    }
    function stopSolr() {
        $.post("/solr/status", {'solr':'stop'}, function (data) {
            if (data.status == 200) {
                $.messager.alert('提示', 'solr关闭！');
            }
        },"json");
    }
    function deleteItems() {
        if(confirm("是否删除所有索引")){
            $.ajax({type:"delete",url:"/solr/", success:function (data) {
                if (data.status == 200) {
                    $.messager.alert('提示', '清理完毕！');
                }
            },dataType:"json"});
        }
    }
</script>