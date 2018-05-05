<div class="container-fluid cm-container-white">
    <div class="panel panel-default">
        <div class="panel-heading">
            生成${product.name}商品的SKU
        </div>
        <div class="panel-body" >
            <table class="table table-bordered">
                <tr>
                    <td><b>商品名：</b>${product.name}</td>
                    <td><b>商品编号：</b>${product.code}</td>
                    <td><b>市场价：</b>${product.marketPrice}</td>
                    <td><b>基本价：</b>${product.basePrice}</td>
                    <td><b>所属分类：</b>${product.catalog.name}</td>
                    <td><b>品牌：</b>${product.brand.name}</td>
                </tr>
            </table>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>sku编号</th>
                    <th>价格</th>
                    <#list skuPropertyList as skuProperty>
                        <th>${skuProperty}</th>
                    </#list>
                </tr>
                </thead>
                <tbody id="tbody">
                <#list skuList as sku>
                <tr>
                    <td>${sku.code}</td>
                    <td>${sku.price}</td>
                    <#list sku.productSkuPropertyList as skuPro>
                        <td>${skuPro.value}</td>
                    </#list>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>




