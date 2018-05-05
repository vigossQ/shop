<table class="table table-bordered catalog-property-table" id="table${skuProperty.id}" data-id="${skuProperty.id}">
    <thead>
    <tr class="active">
        <th colspan="3">${skuProperty.name}</th>
    </tr>
    <tr>
        <th></th>
        <th>值</th>
        <th>图片</th>
    </tr>
    </thead>
    <tbody>
    <#list skuPropertyValueList as skuPropertyValue>
        <tr>
            <td>
                <button class="btn btn-default" type="button" onclick="$(this).closest('tr').remove()">
                    — 移除
                </button>
            </td>
            <td>
                <input type="text" class="form-control sku-pro-val" value="${skuPropertyValue.value}"/>
            </td>
            <td>
                <input type="file"/>
            </td>
        </tr>
    </#list>
    </tbody>
    <tfoot>
    <tr>
        <th colspan="3">
            <button class="btn btn-default add-tr" type="button" onclick='$(this).closest("tfoot").prev().append($("#skuPropertyValueTr").html())'>
                + 添加
            </button>
        </th>
    </tr>
    </tfoot>
</table>