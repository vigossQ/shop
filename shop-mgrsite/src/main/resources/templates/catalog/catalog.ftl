<link rel="stylesheet" href="/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/js/plugins/ztree/js/jquery.ztree.core.js"></script>
<script src="/js/plugins/sortable/Sortable.min.js"></script>
<script type="text/javascript">

    var zTreeObj;

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

    var zNodes = ${allCatalogJson}

    function clickCatalog(event, treeId, treeNode) {
        console.info(treeNode.id)
        $("#childCatalog").load("/catalog/getChildCatalog?catalogId="+treeNode.id);
    }
    $(function(){

        //初始化ztree
        zTreeObj = $.fn.zTree.init($("#catalogTree"), setting, zNodes);
        //获取id为1的节点分类（根节点）
        var treeNode = zTreeObj.getNodeByParam("id",1 );
        //选中该节点分类
        zTreeObj.selectNode(treeNode,true);
        //展开该节点分类
        zTreeObj.expandNode(treeNode, true, false);
        //获取该节点下的子分类
        $("#childCatalog").load("/catalog/getChildCatalog?catalogId="+treeNode.id);

        //保存按钮
        $("#saveButton").click(function () {
            $("#catalogSaveForm").ajaxSubmit({
                type:"POST",
                success:function (data) {
                    if(data.success){
                        $.messager.popup("保存成功");
                        refresh();
                    }else{
                        $.messager.alert("提示",data.errorMsg);
                    }
                    $("#myModal").modal("hide");
                }
            })
        })

        //新增分类按钮
        $("#addButton").click(function () {
            editCatalog(null)
        })

    })

    //回显分类数据用于编辑分类
    function editCatalog(obj) {
        var json;
        //判断obj是否为null
        // 如果if表达式中的值是null、0、undefined就返回false，否则返回true
        if(!obj){
            //自定义json数据
            json = {"code":"","isParent":0,"name":"","id":"","sort":0,"pId":""}
        }else{
            //获取分类对象的属性
            json = $(obj).data("json");
        }

        $("#myModal").modal("show");
        //设置上级分类的名字
        var nodes = zTreeObj.getSelectedNodes();
        if(nodes.length == 0){
            $.messager.alert("提示","请选择分类");
        }
        $("#parentCatalog").val(nodes[0].name);
        //回显分类数据
        $("#id").val(json.id);
        $("#name").val(json.name);
        $("#sort").val(json.sort);
        $("#parentId").val(nodes[0].id);
        $("#isParent").val(json.isParent);
        $("#code").val(json.code);

    }

    //删除分类
    function deleteCatalog(catalogId) {
        $.messager.confirm("提示","是否确定删除",function () {
            $.ajax({
                url:"/catalog/delete?catalogId="+catalogId,
                success:function (data) {
                    if(data.success){
                        $.messager.popup("删除成功");
                    }else{
                        $.messager.alert("提示",data.errorMsg);
                    }
                    refresh();
                }
            })
        })

    }

    //更新一下界面（树结构，和子分类的div代码块）
    function refresh() {
        var nodes = zTreeObj.getSelectedNodes();
        if (nodes.length>0) {
            zTreeObj.reAsyncChildNodes(nodes[0], "refresh");
            $("#childCatalog").load("/catalog/getChildCatalog?catalogId="+nodes[0].id);
        }
    }
</script>


<div class="container-fluid cm-container-white">
    <div class="row">
        <div class="col-sm-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <button type="button" class="btn btn-primary" id="addButton">添加分类</button>
                    <ul id="catalogTree" class="ztree"></ul>
                </div>
            </div>

        </div>
        <div class="col-sm-9" id="childCatalog">

        </div>
    </div>

    <!-- 添加分类模态框 -->
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
                <form action="/catalog/save" method="post" id="catalogSaveForm">
                    <input type="hidden" name="id" id="id"/>
                    <input type="hidden" name="sort" id="sort"/>
                    <input type="hidden" name="isParent" id="isParent"/>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="exampleInputEmail1">上级分类</label> <input
                                type="hidden" name="pId" id="parentId"/> <input
                                class="form-control" value="顶级分类" readonly="readonly"
                                id="parentCatalog">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">分类名</label> <input
                                class="form-control" name="name" id="name">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">分类编号</label> <input
                                class="form-control" name="code" id="code"/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                            <button type="button" class="btn btn-primary" id="saveButton">保存</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


