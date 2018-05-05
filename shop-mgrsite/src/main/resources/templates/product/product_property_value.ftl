<#list propertyList as property>
<tr>
    <td>
    ${property.name}
        <input type="hidden" name="productPropertyValueList[${property_index}].name" value="${property.name}">
    </td>
    <td>
        <#if property.type == 1>
            <select class="form-control" name="productPropertyValueList[${property_index}].value">
                <#list property.propertyValueList as propertyValue>
                    <option value="${propertyValue.value}">${propertyValue.value}</option>
                </#list>
            </select>
        <#else>
            <input class="form-control" name="productPropertyValueList[${property_index}].value"/>
        </#if>
    </td>
</tr>
</#list>
