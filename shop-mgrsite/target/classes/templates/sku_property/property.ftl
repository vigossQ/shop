<link rel="stylesheet" href="/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/js/plugins/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript">

    //ztree插件setting配置
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: clickCatalog
        }
    };

    var zNodes = ${allCatalog}

    //点击分类节点，加载该分类的下级分类（远程载入HTML的形式）
    function clickCatalog(event, treeId, treeNode) {
        $("#propertyList").load("/skuProperty/get/"+treeNode.id);
    }

    var treeObj;
    $(document).ready(function () {

        //初始化ztree
        treeObj = $.fn.zTree.init($("#catalogTree"), setting, zNodes);
        //获取id为1的节点分类（根节点）
        var treeNode = treeObj.getNodeByParam("id",1 );
        //选中该节点分类
        treeObj.selectNode(treeNode,true);
        //展开该节点分类
        treeObj.expandNode(treeNode, true, false);
        //获取该节点下的商品属性
        $("#propertyList").load("/skuProperty/get/"+treeNode.id);

        $(".add-property").click(function () {

            editProperty(null);

        })

        $(".property-save").click(function () {
            var form = $("#propertyForm")
            $("#myModal").modal("hide");
            form.ajaxSubmit(function (data) {
                if(data.success){
                    $.messager.popup("保存成功")
                    refreshPropertyList();
                }else{
                    $.messager.popup(data.errorMsg)
                }
            })
        })
    })

    //设置编辑数据回显
    function editProperty(obj) {
        var json;
        if(obj == null){
            json = JSON.parse('{"catalogId":"","name":"","id":"","sort":0,"type":0}')
        }else{
            json = $(obj).data("json");
        }
        var nodes = treeObj.getSelectedNodes();
        if(nodes.length == 0){
            $.messager.popup("请选择分类")
        }

        $("#id").val(json.id);
        $("#name").val(json.name);
        $("#sort").val(json.sort);
        $("#type").val(json.type);
        $("#catalogId").val(nodes[0].id);
        $("#myModal").modal("show");
    }

    //删除属性
    function deleteProperty(id) {
        $.messager.confirm("提示", "确认删除吗？", function () {
            $.ajax({
                url: "/skuProperty/delete/" + id,
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        $.messager.popup("删除成功！");
                        refreshPropertyList();
                    } else {
                        $.messager.popup(data.errorMsg);
                    }
                }
            })
        });
    }

    //查看属性值
    function showPropertyValue(id) {
        $("#propertyValueModal").modal("show");
        $("#propertyValueForm").load("/skuPropertyValue/get/"+id)
    }

    //刷新属性列表
    function refreshPropertyList() {
        var nodes = treeObj.getSelectedNodes();
        $("#propertyList").load("/skuProperty/get/"+nodes[0].id);
    }

    //新增一行属性值
    function addLine() {
        $("#valueDiv").append($("#propertyValueLine").html());
    }

    //保存属性值
    function savePropertyValue() {
        var propertyId = $("input[name='propertyId']").val();
        console.info(propertyId)
        //1、获取表单input的dom对象
        var $divArray = $(".propertyValueDiv");
        //2、拼成json对象
        //先定义一个属性值数组对象
        var propertyValueArray = [];
        for(var i=0;i<$divArray.length;i++){
            var div = $divArray[i];
            //取出div中的属性值id
            var id = $(div).children("input[name='id']").val();
            //取出div中的属性值
            var value = $(div).children("input[name='value']").val();
            propertyValueArray.push({"id":id,"value":value,"propertyId":propertyId})
        }
        console.info(JSON.stringify(propertyValueArray))
        //3、用ajax提交json数据
        $.ajax({
            url:"/propertyValue/save",
            contentType:"application/json",
            type:"POST",
            data:JSON.stringify(propertyValueArray),
            success:function (data) {
                if(data.success){
                    $.messager.popup("保存成功")
                }else{
                    $.messager.popup(data.errorMsg);
                }
            }
        })
        $("#propertyValueModal").modal("hide");
    }

    //删除属性值
    function deletePropertyValue(obj) {
        var id = $(obj).data("id");
        if(id){
            //如果id不为0，那就删除该属性值
            $.get("/skuPropertyValue/delete/"+id,function (data) {
                if(data.success){
                    $.messager.popup("删除成功！");
                }else{
                    $.messager.popup(data.errorMsg);
                }
            })
        }
        $(obj).closest("div").remove();
    }
</script>

<div class="container-fluid cm-container-white">
    <div class="row">
        <div class="col-sm-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <button type="button" class="btn btn-primary add-property">新增属性</button>
                    <ul id="catalogTree" class="ztree"></ul>
                </div>
            </div>
        </div>
        <div class="col-sm-9" id="propertyList">
            
        </div>
    </div>

    <!-- 添加属性模态框 -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">添加分类</h4>
                </div>
                <form action="/skuProperty/save" method="post" id="propertyForm">
                    <div class="modal-body">
                        <input type="hidden" name="catalogId" id="catalogId"/>
                        <input type="hidden" name="id" id="id"/>
                        <input type="hidden" name="sort" id="sort"/>
                        <div class="form-group">
                            <label for="exampleInputEmail1">分类属性名</label> <input
                                class="form-control" id="name" name="name" value=""/>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">分类属性类型</label>
                            <select name="type" id="type">
                                <option value="0">输入框</option>
                                <option value="1">下拉列表</option>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary property-save">保存</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- 新增分类属性值模态框 -->
    <div class="modal fade" id="propertyValueModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">分类属性值</h4>
                </div>

                <!-- 提交添加分类属性值的表单 -->
                <form action="#" id="propertyValueForm">
                   
                </form>
            </div>
        </div>
    </div>

    <#--定义一个属性值行的模板-->
    <script type="text/html" id="propertyValueLine">
        <div style="height: 50px;" class="propertyValueDiv">
            <input type="hidden" name="id" value=""/>
            <input type="text" class="form-control" name="value" value="" style="width: 200px;margin-bottom: 5px;float: left;">
            <h3 style="float: left;margin: 0;">
                <span class="label label-primary" style="cursor: pointer;" onclick="deletePropertyValue(this)" data-id="0">-</span>
            </h3>
        </div>
    </script>
</div>

