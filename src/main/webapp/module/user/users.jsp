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

    <link href="../../resources/plugin/jquery-ui-1.12.1.custom/jquery-ui.css" rel="stylesheet">
    <link rel="stylesheet" href="css/users.css">

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
                        <li><i class="fa fa-home" aria-hidden="true"></i><a href="#">User Manage</a></li>
                        <li><a>User</a></li>
                    </ul>
                </div>
            </div>
            <div class="row" id="model-1">

                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel">
                        <div class="panel-content">

                            <%--锁定账号--%>
                            <shiro:hasPermission name="user:locked0">
                                <input type="hidden" id="user-locked-0">
                            </shiro:hasPermission>

                            <%--解锁账号--%>
                            <shiro:hasPermission name="user:locked1">
                                <input type="hidden" id="user-locked-1">
                            </shiro:hasPermission>

                            <%--用户所拥有的角色--%>
                            <shiro:hasPermission name="userrole:findrole">
                                <input type="hidden" id="userrole-findrole">
                            </shiro:hasPermission>

                            <%--查询角色--%>
                            <shiro:hasPermission name="role:find">
                                <input type="hidden" id="role-find">
                            </shiro:hasPermission>

                            <%--清除shiro授权缓存--%>
                            <shiro:hasPermission name="user:clearcached">
                                <input type="button" class="btn btn-primary animated fadeInRight" id="clear-cached"
                                       value="清除shiro授权缓存">
                            </shiro:hasPermission>

                            <%--为用户分配角色权限--%>
                            <shiro:hasPermission name="userrole:save">
                                <input type="hidden" id="userrole-save">
                            </shiro:hasPermission>

                            <div class="table-responsive animated fadeInUp">
                                <table class="table table-hover table-bordered text-center tab-1">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>账号</th>
                                        <th>锁定</th>
                                        <th>姓名</th>
                                        <th>注册时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody></tbody>
                                </table>
                                <div id="laypage"></div>
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

<!-- 分配角色弹出层 -->
<div class="modal fade" id="modal-role-list" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document" style="margin:140px auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">角色列表</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary confirm">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<%--锁定账号弹出层--%>
<div id="user-locked-dialog" title="锁定账号原因">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <textarea class="form-control c1" style="width: 100%; height: 200px; resize: none; background: #000000; color: #F1F5FA;" placeholder="请在这里输入原因"></textarea>
            </div>
        </div>
    </div>
</div>

<script src="../../adminAsx/vendor/jquery/jquery-1.12.3.min.js"></script>
<script src="../../adminAsx/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="../../adminAsx/vendor/nano-scroller/nano-scroller.js"></script>
<script src="../../adminAsx/javascripts/template-script.min.js"></script>
<script src="../../adminAsx/javascripts/template-init.min.js"></script>

<script type="text/javascript" src="js/user-message.js"></script>
<script type="text/javascript" src="../../resources/plugin/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script type="text/javascript" src="../../resources/plugin/layer/layer.js"></script>
<script type="text/javascript" src="../../resources/plugin/laypage/laypage.js"></script>
<script type="text/javascript" src="../../resources/utils/paging.js"></script>
<script type="text/javascript" src="js/users.js"></script>

</body>
</html>