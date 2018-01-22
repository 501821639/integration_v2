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

    <link rel="stylesheet" type="text/css" href="../../resources/plugin/zTree_v3/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="css/roles.css">

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
                        <li><a>role</a></li>
                    </ul>
                </div>
            </div>
            <div class="row">

                <%--查询角色所拥有权限的权限--%>
                <shiro:hasPermission name="rolepermission:findpermission">
                    <input type="hidden" id="rolepermission-findpermission">
                </shiro:hasPermission>

                <%--查询权限列表权限--%>
                <shiro:hasPermission name="permission:find">
                    <input type="hidden" id="permission-find">
                </shiro:hasPermission>

                <%--删除角色权限--%>
                <shiro:hasPermission name="role:delete">
                    <input type="hidden" id="role-permission-delete">
                </shiro:hasPermission>

                <%--修改角色权限--%>
                <shiro:hasPermission name="role:update">
                    <input type="hidden" id="role-permission-update">
                </shiro:hasPermission>

                <%--角色分配权限--%>
                <shiro:hasPermission name="rolepermission:save">
                    <input type="hidden" id="role-permission-save">
                </shiro:hasPermission>

                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="role-add" style="display: none;">
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <input type="text" class="form-control role-name" placeholder="角色名长度限制20汉字、字符">
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                    <button class="btn btn-wide btn-info role-btn-submit">添加</button>
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                    <button class="btn btn-wide role-btn-close">关闭</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="role-update" style="display: none">
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <input type="text" class="form-control role-name" placeholder="角色名长度限制20汉字、字符(角色名不能重复)">
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                    <button class="btn btn-wide btn-info role-btn-submit">修改</button>
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                    <button class="btn btn-wide role-btn-close">关闭</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="role-show">
                    <div class="panel">
                        <div class="panel-content">

                            <shiro:hasPermission name="role:save">
                                <button type="button" class="btn btn-primary btn-role-add animated fadeInRight">&nbsp;&nbsp添&nbsp加&nbsp角&nbsp色&nbsp;&nbsp;</button>
                            </shiro:hasPermission>

                            <div class="table-responsive animated fadeInUp">
                                <table class="table table-hover table-bordered text-center table-1">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>ID</th>
                                        <th>角色</th>
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

<!-- 分配权限弹出层 -->
<div class="modal fade" id="modal-permission" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document" style="margin: 140px auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">权限列表</h4>
            </div>
            <div class="modal-body">
                <div style="width: 100%; height: 300px; overflow:auto;">
                    <ul id="tree" class="ztree"></ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary confirm">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
<script type="text/javascript" src="../../resources/plugin/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="../../resources/plugin/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="../../resources/plugin/layer/layer.js"></script>
<script type="text/javascript" src="js/roles.js"></script>


</body>
</html>