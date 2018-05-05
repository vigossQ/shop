<div class="panel panel-default">
    <div class="panel-body">
        <h4>分类属性：</h4>
        <div class="modal-body">
            <table class="table table-hover">
                <tr>
                    <th>属性名</th>
                    <th>属性类型</th>
                    <th>操作</th>
                </tr>
                <tbody id="propertyTbody">
                    <#list skuPropertyList as property>
                        <tr>
                            <td>${property.name}</td>
                            <td>
                                <#if property.type == 0>
                                    输入框
                                <#else>
                                    下拉列表
                                    <a href="javascript:showPropertyValue(${property.id});">查看属性值</a>
                                </#if>
                            </td>
                            <td>
                                <a href="javascript:;"onclick="editProperty(this)" data-json='${property.jsonData!""}'>编辑</a>
                                /
                                <a href="javascript:;" onclick="deleteProperty(${property.id})">删除</a>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>