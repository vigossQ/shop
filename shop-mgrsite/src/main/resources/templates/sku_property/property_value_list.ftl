<div class="modal-body">

    <input type="hidden" name="propertyId" value="${propertyId}"/>
    <div class="form-group">
        <label>
            属性名
        </label>
        <div id="valueDiv">
            <#list skuPropertyValueList as propertyValue>
            <div style="height: 50px;" class="propertyValueDiv">
                <input type="hidden" name="id" value="${propertyValue.id}"/>
                <input type="text" class="form-control" name="value" value="${propertyValue.value}" style="width: 200px;margin-bottom: 5px;float: left;">
                <h3 style="float: left;margin: 0;">
                    <span class="label label-primary" style="cursor: pointer;" onclick="deletePropertyValue(this)" data-id="${propertyValue.id}">-</span>
                </h3>
            </div>
            </#list>
        </div>
        <h3><span class="label label-primary" style="cursor: pointer;" onclick="addLine()">+</span></h3>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary" onclick="savePropertyValue()">保存</button>
        </div>
    </div>
</div>