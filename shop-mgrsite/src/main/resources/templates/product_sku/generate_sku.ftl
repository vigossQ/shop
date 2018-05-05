<script type="application/javascript">
    $(function(){
        $("#skuProDiv span").click(function(){
            var skuPropertyId = $(this).data("id");
            var clazz = $(this).attr("class");
            //判断此次选择的sku属性是否选择状态
            if(clazz == "label label-success"){
                //如果是选择状态，就改为不选的状态，并且把该sku属性对应的sku属性值div移除
                $(this).attr("class","label label-default");
                $("#table"+skuPropertyId).remove();
            }else{
                //如果是不选择状态，就改为选择状态，并且把该sku属性对应的sku属性值查询出来并添加到对应div中
                $(this).attr("class","label label-success");
                $.ajax({
                    url:"/productSku/getSkuPropertyValue?skuPropertyId="+skuPropertyId,
                    dataType:"html",
                    success:function(data){
                        $("#skuProValDiv").append(data);
                    }
                })
            }

        })

        $(".generate-sku-btn").click(function () {
            //定义一个对象，用户封装选择的sku属性
            var skuPropetyArray = new Array();
            //获取选择的sku属性
            $(".label-success").each(function () {
                skuPropetyArray.push({id:$(this).data("id"),name:$(this).html()})
            })
            if(skuPropetyArray.length == 0){
                $.messager.alert("提示","请选择sku属性");
            }
            console.info(JSON.stringify(skuPropetyArray))

            //定义一个sku属性值数组，用户封装编辑的sku属性值
            var skuPropertyValueArray = new Array();
            $(".sku-pro-val").each(function () {
                var skuPropertyId = $(this).closest("table").data("id");
                skuPropertyValueArray.push({skuPropertyId:skuPropertyId,value:$(this).val()})
            })
            console.info(JSON.stringify(skuPropertyValueArray))

            //定义一个最后需要提交的对象
            var obj = {
                product:{id:"${product.id}",name:"${product.name}",basePrice:"${product.basePrice?c}",catalog:{id:${product.catalog.id}}},
                skuPropertyList:skuPropetyArray,
                skuPropertyValueList:skuPropertyValueArray
            }

            $.ajax({
                url:"/productSku/generateSku",
                dataType:"html",
                contentType:"application/json",
                type:"POST",
                data:JSON.stringify(obj),
                success:function (data) {
                    $("#skuDiv").html(data);
                }
            })
        })

        $("#saveButton").click(function () {
            $("#saveSku").ajaxSubmit({
                type:"POST",
                success:function (data) {
                    if(data.success){
                        $.messager.popup("保存成功！");
                        $("#content").load("/productSku?productId=${product.id}")
                    }else{
                        $.messager.alert("提示",data.errorMsg)
                    }
                }
            })
        })
    })
</script>
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
            <form action="/productSku/save" id="saveSku">
            <#--隐藏的商品id-->
            <input name="product.id" value="${product.id}" type="hidden"/>

            <#--sku属性的div-->
            <div id="skuProDiv">
                <#--<span style="cursor: pointer; font-size: 13px;margin-right: 10px;" class="label label-success">颜色</span>-->
                <#list skuPropertyList as skuProperty>
                    <span style="cursor: pointer; font-size: 13px;margin-right: 10px;" class="label label-default" data-id="${skuProperty.id}">${skuProperty.name}</span>
                </#list>
            </div>

            <#--属性值div-->
            <div id="skuProValDiv" style="margin-top: 20px;">

            </div>

	    <h2>
                <button type="button" class="btn btn-success generate-sku-btn">生成SKU数据</button>
            </h2>

            <#--最终生成出来的sku Div-->
            <div id="skuDiv">

            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saveButton">保存</button>
            </div>
            </form>
        </div>
    </div>
</div>
<script type="text/html" id="skuPropertyValueTr">
    <tr>
        <td>
            <button class="btn btn-default" type="button" onclick="$(this).closest('tr').remove()">
                — 移除
            </button>
        </td>
        <td>
            <input type="text" class="form-control sku-pro-val" value=""/>
        </td>
        <td>
            <input type="file"/>
        </td>
    </tr>
</script>



