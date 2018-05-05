<script type="application/javascript">
    $(function () {
        $("#pagination_container").twbsPagination({
            totalPages:${page.totalPage},
            visiblePages:${page.pageSize},
            startPage:${page.currentPage},
            first:"首页",
            prev:"上一页",
            next:"下一页",
            last:"末页",
            onPageClick:function(event,page){
                $("#currentPage").val(page);
                var param = $("#searchForm").serialize();
                $("#orderList").load("/order/page?"+param);
            }
        });
    })
function orderDetali(id) {
    $("#content").load("/order/detail?id="+id)
}
</script>
<table class="table table-hover">
    <thead>
    <tr>
        <th>订单编号</th>
        <th>付款方式</th>
        <th>状态</th>
        <th>商品明细</th>
        <th>订单总额</th>
        <th>创建时间</th>
        <th>收货人</th>
        <th>联系电话</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody id="tbody">
    <#list page.data as orderInfo>
        <tr>
            <td>${orderInfo.orderSn}</td>
            <td>${orderInfo.payTypeStr}</td>
            <td>${orderInfo.status}</td>
            <td>
                <#list orderInfo.orderProductList as orderProduct>
                    ${orderProduct.productName}<br/>
                </#list>
            </td>
            <td>${orderInfo.orderAmount}元</td>
            <td>${orderInfo.orderTime?string('yyyy-MM-dd HH:mm:ss')}</td>
            <td>${orderInfo.consignee}</td>
            <td>${orderInfo.phone}</td>
            <td>
                <a href="javascript:orderDetali(${orderInfo.id})">订单详细</a>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

<!--分页代码块-->
<div style="text-align: center;" id="pagination_container">

</div>