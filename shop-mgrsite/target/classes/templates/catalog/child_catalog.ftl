<script type="application/javascript">

    var sortable;
    $(function () {
        //给需要做拖拽排序的dom对象实例化出拖拽排序的对象
        var el = $("#items")[0];
        sortable = new Sortable(el,{
            onUpdate:updateSort
        });
    })
    
    function updateSort() {
        var ids = sortable.toArray();
        $.ajax({
            url:"/catalog/updateSort",
            type:"POST",
            data:JSON.stringify(ids),
            contentType:"application/json",
            dataType:"json",
            success:function (data) {
                if(data){
                    refresh();
                }
            }
        })
    }
</script>
<div class="panel panel-default">
    <div class="panel-body">

        <div class="table-responsive">
            <table class="table">
                <thead>
                <th>分类名称</th>
                <th>分类编号</th>
                <th>分类排序</th>
                <th>商品个数</th>
                <th>属性个数</th>
                <th>子分类操作</th>
                </thead>
            </table>

            <div id="childCatalog">

            </div>
            <ul class="list-group" id="items">
            <#list catalogList as catalog>
                <li class="list-group-item" data-id="${catalog.id}">
                    <table>
                        <tr>
                            <td width="215px;">${catalog.name}</td>
                            <td width="215px;">${catalog.code}</td>
                            <td width="215px;">${catalog.sort}</td>
                            <td width="215px;">${catalog.productCount!""}</td>
                            <td width="215px;">${catalog.propertyCount!""}</td>
                            <td width="215px;">
                                <button type="button" class="btn btn-primary" id="editCatalog" onclick="editCatalog(this)" data-json='${catalog.jsonData}'>编辑</button>
                                <button type="button" class="btn btn-primary" id="deleteCatalog" onclick="deleteCatalog(${catalog.id})">删除</button>
                            </td>
                        </tr>
                    </table>
                </li>
            </#list>
            </ul>
        </div>
    </div>
</div>