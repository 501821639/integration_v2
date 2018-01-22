<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/resources/utils/taglibs.jsp" %>
<!doctype html>
<html lang="en" class="fixed">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>Helsinki</title>
    <link rel="apple-touch-icon" sizes="120x120" href="../../adminAsx/favicon/apple-icon-120x120.png">
    <link rel="icon" type="image/png" sizes="192x192" href="../../adminAsx/favicon/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32" href="../../adminAsx/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="../../adminAsx/favicon/favicon-16x16.png">
    <link rel="stylesheet" type="text/css" href="../../adminAsx/vendor/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../../adminAsx/stylesheets/font-awesome.4.7.0.css">
    <link rel="stylesheet" href="../../adminAsx/vendor/animate.css/animate.css">
    <link rel="stylesheet" href="../../adminAsx/stylesheets/css/style.css">


    <link href="css/user_listen_webscoket.css" rel="stylesheet">

</head>
<body>
<div class="wrap">

    <jsp:include page="/module/public/page-header.jsp"/>

    <div class="page-body">

        <jsp:include page="/module/public/left-sidebar.jsp"/>

        <div class="content">
            <div class="content-header">
                <div class="leftside-content-header">
                    <ul class="breadcrumbs">
                        <li><i class="fa fa-home" aria-hidden="true"></i><a href="#">push</a></li>
                        <li><a>web socket</a></li>
                    </ul>
                </div>
            </div>
            <div class="row" id="model-1">

                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel">
                        <div class="panel-content">

                            <div class="container-fluid" id="cf-1">

                                <div class="row">
                                    <div class="col-md-3">

                                        <div class="container-fluid" id="cf-2">
                                            <%--好友列表--%>
                                        </div>

                                    </div>

                                    <div class="col-md-8">

                                        <div class="container-fluid message-view scroll-1" id="cf-3">
                                            <%--消息列表--%>
                                        </div>

                                        <div class="container-fluid message-view" id="cf-4">

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <textarea class="user-content form-control"></textarea>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <%--<div class="col-md-3 col-md-offset-3">
                                                    <button class="btn btn-default btn-block user-on" type="button">
                                                        建立连接
                                                    </button>
                                                </div>
                                                <div class="col-md-3">
                                                    <button class="btn btn-default btn-block user-off" type="button">
                                                        断开连接
                                                    </button>
                                                </div>--%>
                                                <div class="col-md-3 col-md-offset-9">
                                                    <button class="btn btn-default btn-block user-submit" type="button">
                                                        发送
                                                    </button>
                                                </div>
                                            </div>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>

    <jsp:include page="/module/public/right-sidebar.jsp"/>
    <jsp:include page="/module/public/scroll-to-top.jsp"/>

</div>


<script src="../../adminAsx/vendor/jquery/jquery-1.12.3.min.js"></script>
<script src="../../adminAsx/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="../../adminAsx/vendor/nano-scroller/nano-scroller.js"></script>
<script src="../../adminAsx/javascripts/template-script.min.js"></script>
<script src="../../adminAsx/javascripts/template-init.min.js"></script>

<script type="text/javascript" src="../user/js/user-message.js"></script>
<script type="text/javascript" src="../../resources/plugin/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script type="text/javascript" src="../../resources/plugin/layer/layer.js"></script>
<script type="text/javascript" src="../../resources/plugin/laypage/laypage.js"></script>
<script type="text/javascript" src="../../resources/utils/paging.js"></script>
<script type="text/javascript" src="js/user_listen_webscoket.js"></script>


</body>
</html>