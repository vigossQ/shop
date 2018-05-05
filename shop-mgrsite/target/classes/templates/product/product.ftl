<script type="application/javascript">
    $(function () {
        $("#productList").load("/product/page")

        $("#query").click(function () {
            var param = $("#searchForm").serialize();
            $("#productList").load("/product/page?"+param);
        })
    })
</script>
<div class="container-fluid cm-container-white">
    <div class="panel panel-default">
        <div class="panel-heading">
            <!--提交分页的表单-->
            <form id="searchForm" class="form-inline" method="post" action="/product/page">
                <input type="hidden" name="currentPage" id="currentPage" value="1"/>

                <div class="form-group">
                    <input class="form-control" type="text" name="keyword" value="" placeholder="商品名称或者商品编号">
                </div>
                <div class="form-group">
                    <button id="query" type="button" class="btn btn-primary"><i class="icon-search"></i> 查询</button>
                </div>
            </form>
        </div>
        <div class="panel-body" id="productList">


        </div>
    </div>
</div>



