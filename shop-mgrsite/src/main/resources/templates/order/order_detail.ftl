<script type="application/javascript">
    $(function () {
        $(".btn-primary").click(function () {
            var type = $(this).data("type");
            $("#changeType").val(type);
            $("#changeStatusForm").ajaxSubmit({
                success:function (data) {
                    if(data.success){
                        $.messager.popup("订单状态修改成功");
                        $("#content").load("/order/detail?id=${orderInfo.id}")
                    }else{
                        $.messager.alert("提示",data.errorMsg);
                    }
                }
            })
        })
    })
</script>
<div class="container-fluid cm-container-white">
    <div class="panel panel-default">
        <div class="panel-heading">
            订单详细信息
        </div>
        <div class="panel-body">
            <table class="table table-bordered">
                <thead>
                    <tr class="active">
                        <th colspan="6" style="text-align:center;">用户详情</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>用户昵称</th>
                        <td>${userInfo.nickName!""}</td>
                        <th>联系电话</th>
                        <td>${userInfo.phone!""}</td>
                        <th>注册时间</th>
                        <td>${userInfo.registTime?string("yyyy-MM-dd")}</td>
                    </tr>
                    <tr>
                        <th>所在省</th>
                        <td>${userInfo.province!""}</td>
                        <th>所在市</th>
                        <td>${userInfo.city!""}</td>
                        <th>所在区</th>
                        <td>${userInfo.district!""}</td>
                    </tr>
                </tbody>
            </table>

            <table class="table table-bordered">
                <thead>
                <tr class="active">
                    <th colspan="6" style="text-align:center;">订单详情</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>订单编号</th>
                        <td>${orderInfo.orderSn}</td>
                        <th>下单时间</th>
                        <td>${orderInfo.orderTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <th>支付方式</th>
                        <td>${orderInfo.payTypeStr}</td>
                    </tr>
                    <tr>
                        <th>订单状态</th>
                        <td>${orderInfo.orderStatusStr}</td>
                        <th>物流状态</th>
                        <td>${orderInfo.flowStatusStr}</td>
                        <th>付款状态</th>
                        <td>${orderInfo.payStatusStr}</td>
                    </tr>
                    <tr>
                        <th>收货人</th>
                        <td>${orderInfo.consignee}</td>
                        <th>联系电话</th>
                        <td>${orderInfo.phone}</td>
                        <th>收货地址</th>
                        <td>${orderInfo.country}${orderInfo.province}${orderInfo.city}${orderInfo.district}${orderInfo.address}</td>
                    </tr>
                    <tr>
                        <td colspan="6">
                            <form id="changeStatusForm" class="form-inline" method="post" action="/order/changeStatus">
                                <input class="form-control" type="text" name="note" value="" placeholder="备注">
                                <input type="hidden" name="changeType" id="changeType">
                                <input type="hidden" name="orderId" value="${orderInfo.id}">
                            </form>
                            
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6">
                                <#switch orderInfo.status>
                                    <#case "未确认">
                                        <button type="button" class="btn btn-primary" data-type="1">确认订单 </button>
                                        <#break>
                                    <#case "已确认">
                                        <button type="button" class="btn btn-primary" data-type="2">发货</button>
                                        <#break>
                                    <#case "已发货">
                                            <button type="button" class="btn btn-primary" data-type="4">售后</button>
                                        <#break>
                                    <#case "已退货">
                                            <button type="button" class="btn btn-primary" data-type="3">订单完成</button>
                                        <#break>
                                    <#case "已完成">
                                            <button type="button" class="btn btn-primary" data-type="4">售后</button>
                                        <#break>
                                    <#default>
                                            <button type="button" class="btn btn-primary" data-type="4">售后</button>
                                </#switch>
                        </td>
                    </tr>


                </tbody>
            </table>

            <table class="table table-bordered">
                <thead>
                <tr class="active">
                    <th colspan="6" style="text-align:center;">商品明细</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>商品名称</th>
                    <th>商品规格</th>
                    <th>商品价格</th>
                    <th>购买数量</th>
                    <th>小计</th>
                </tr>
                <#list orderInfo.orderProductList as orderPorduct>
                <tr>
                    <td>${orderPorduct.productName}</td>
                    <td>
                        <#list orderPorduct.orderProductPropertyList as property>
                        ${property.name}:${property.value}</br>
                        </#list>
                    </td>
                    <td>${orderPorduct.productPrice}元</td>
                    <td>${orderPorduct.productNumber}</td>
                    <td>${orderPorduct.totalPrice}元</td>
                </tr>
                </#list>
                <tr>
                    <td colspan="4"></td>
                    <td><strong>订单总额：</strong>${orderInfo.orderAmount}元</td>
                </tr>
                </tbody>
            </table>

            <table class="table table-bordered">
                <thead>
                    <tr class="active">
                        <th colspan="7" style="text-align:center;">订单操作记录</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>操作人</th>
                        <th>操作地点</th>
                        <th>操作时间</th>
                        <th>订单状态</th>
                        <th>物流状态</th>
                        <th>付款状态</th>
                        <th>备注</th>
                    </tr>
                    <#list orderActionList as orderAction>
                    <tr>
                        <td>${orderAction.actionUser}</td>
                        <td>${orderAction.actionPlace}</td>
                        <td>${orderAction.actionTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td>${orderAction.orderStatusStr}</td>
                        <td>${orderAction.flowStatusStr}</td>
                        <td>${orderAction.payStatusStr}</td>
                        <td>${orderAction.actionNote}</td>
                    </tr>
                    </#list>

                </tbody>
            </table>



        </div>
    </div>
</div>



