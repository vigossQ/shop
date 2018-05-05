<!DOCTYPE html>
<html>
<head>
    <title>商城后台</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="/assets/css/bootstrap-clearmin.min.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/roboto.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/material-design.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/small-n-flat.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/font-awesome.min.css">
    <script src="/assets/js/lib/jquery-2.1.3.min.js"></script>
    <script src="/assets/js/jquery.mousewheel.min.js"></script>
    <script src="/assets/js/jquery.cookie.min.js"></script>
    <script src="/assets/js/fastclick.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/clearmin.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#content").load("/index")
            $(".cm-menu-items li").click(function (e) {
                $(".cm-menu-items li").each(function () {
                    $(this).removeClass("active")
                })
                var url = $(e.target).data("url");
                if(url){
                    $("#content").load(url)
                    $(e.target).parent().addClass("active")
                    $("#pageTitle").html($(e.target).html());
                }
            })
        })

    </script>
</head>
<body class="cm-no-transition cm-1-navbar">
<div id="cm-menu">
    <nav class="cm-navbar cm-navbar-primary">
        <div class="cm-flex"><a href="/" class="cm-logo"></a></div>
        <div class="btn btn-primary md-menu-white" data-toggle="cm-menu"></div>
    </nav>
    <div id="cm-menu-content">
        <div id="cm-menu-items-wrapper">
            <div id="cm-menu-scroller">
                <ul class="cm-menu-items">
                    <li><a href="#" data-url="/index" class="sf-house">主页</a></li>
                    <li class="cm-submenu">
                        <a class="sf-window-layout">商品管理<span class="caret"></span></a>
                        <ul>
                            <li><a href="#" data-url="/catalog">商品分类</a></li>
                            <li><a href="#" data-url="/property">商品分类属性</a></li>
                            <li><a href="#" data-url="/product">商品列表</a></li>
                            <li><a href="#" data-url="/product/add">商品录入</a></li>
                            <li><a href="#" data-url="/skuProperty">sku属性管理</a></li>
                        </ul>
                    </li>
                    <li class="cm-submenu">
                        <a class="sf-cat">订单管理 <span class="caret"></span></a>
                        <ul>
                            <li><a href="#" data-url="/order">订单管理</a></li>
                            <li><a href="#">订单管理2</a></li>
                            <li><a href="#">订单管理3</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<header id="cm-header">
    <nav class="cm-navbar cm-navbar-primary">
        <div class="btn btn-primary md-menu-white hidden-md hidden-lg" data-toggle="cm-menu"></div>
        <div class="cm-flex">
            <h1 id="pageTitle">扣丁商城管理平台</h1>
            <form id="cm-search" action="index.html" method="get">
                <input type="search" name="q" autocomplete="off" placeholder="Search...">
            </form>
        </div>
    </nav>
</header>
<div id="global">
    <div id="content">

    </div>
    <footer class="cm-footer">
        <span class="pull-left">客服电话：400-664-8000</span><span class="pull-right">&copy; 版权所有：2017广州狼码教育科技有限公司 ICP备案 ：粤ICP备字17147191号-1</span>
    </footer>
</div>

</body>
</html>