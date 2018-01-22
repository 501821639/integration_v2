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

    <link rel="stylesheet" href="css/permissions.css">

</head>
<body>
<div class="wrap">

    <jsp:include page="/module/public/page-header.jsp"/>

    <div class="page-body">

        <jsp:include page="/module/public/left-sidebar.jsp"/>

        <div class="content" id="content">
            <div class="content-header">
                <div class="leftside-content-header">
                    <ul class="breadcrumbs">
                        <li><i class="fa fa-home" aria-hidden="true"></i><a href="javascript:;">User Manage</a></li>
                        <li><a>Permission</a></li>
                        <%--<shiro:hasPermission name="permission:save">
                            <li><a href="javascript:;" id="btn-permission-add">添加权限</a></li>
                        </shiro:hasPermission>--%>
                    </ul>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12">
                <div class="panel">
                    <div class="panel-content">
                        <div class="row">
                            <div class="col-md-12">

                                <shiro:hasPermission name="permission:save">
                                    <div class="row">
                                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                            <button type="button" class="btn btn-primary btn-block" id="btn-permission-add">&nbsp添&nbsp加&nbsp权&nbsp限</button>
                                        </div>
                                    </div>
                                </shiro:hasPermission>

                                <div class="tabs" id="tabs-1">
                                    <ul class="nav nav-tabs nav-justified">
                                        <%--<li class="active"><a href="#home2" data-toggle="tab">Home</a></li>
                                        <li><a href="#profile2" data-toggle="tab">Profile</a></li>
                                        <li><a href="#messages2" data-toggle="tab">Messages</a></li>--%>
                                    </ul>
                                    <div class="tab-content">
                                        <%--<div class="tab-pane fade in active" id="home2">
                                            <p><b>Home</b> content</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vitae tellus tincidunt, mattis odio eu, accumsan quam. Duis ultricies, erat nec suscipit mattis, risus est efficitur enim, sed finibus lacus nisi et mauris. Ut sed accumsan ipsum. Aliquam vel nibh et turpis euismod porttitor. In diam odio, cursus eget faucibus quis, efficitur id erat. Aliquam euismod in justo sit amet ornare. Quisque eu fringilla libero. Donec iaculis sit amet nibh non laoreet.
                                            </p>
                                        </div>
                                        <div class="tab-pane fade" id="profile2">
                                            <p><b>Profile</b> content</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vitae tellus tincidunt, mattis odio eu, accumsan quam. Duis ultricies, erat nec suscipit mattis, risus est efficitur enim, sed finibus lacus nisi et mauris. Ut sed accumsan ipsum. Aliquam vel nibh et turpis euismod porttitor. In diam odio, cursus eget faucibus quis, efficitur id erat. Aliquam euismod in justo sit amet ornare. Quisque eu fringilla libero. Donec iaculis sit amet nibh non laoreet.
                                            </p>
                                        </div>
                                        <div class="tab-pane fade" id="messages2">
                                            <p><b>Message</b> content</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vitae tellus tincidunt, mattis odio eu, accumsan quam. Duis ultricies, erat nec suscipit mattis, risus est efficitur enim, sed finibus lacus nisi et mauris. Ut sed accumsan ipsum. Aliquam vel nibh et turpis euismod porttitor. In diam odio, cursus eget faucibus quis, efficitur id erat. Aliquam euismod in justo sit amet ornare. Quisque eu fringilla libero. Donec iaculis sit amet nibh non laoreet.
                                            </p>
                                        </div>--%>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>

            <%--修改--%>
            <shiro:hasPermission name="permission:update">
                <input type="hidden" id="permission-update">
            </shiro:hasPermission>

            <%--删除--%>
            <shiro:hasPermission name="permission:delete">
                <input type="hidden" id="permission-delete">
            </shiro:hasPermission>

    </div>

    <jsp:include page="/module/public/right-sidebar.jsp"/>
    <jsp:include page="/module/public/scroll-to-top.jsp"/>

</div>

<!-- 添加权限弹出层 -->
<div class="modal fade" id="modal-au-permission" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="margin:100px auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">权限信息</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid permission">

                    <input type="hidden" class="permission-id">

                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <label>权限名称：</label>
                            <input type="text" class="form-control name" placeholder="限制20汉字、字符(名称不能重复)">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <label>上级权限ID：</label>
                            <input type="text" class="form-control permission-pid" placeholder="默认空最高节点">
                            <input type="text" class="form-control pidName" disabled="disabled" style="margin-top: 5px;" placeholder="上级权限名称">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <label>排序：</label>
                            <input type="text" class="form-control order" placeholder="从小到大排序(不填写默认为0)">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <label>资源类型：</label>
                            <select class="form-control type" title="资源类型">
                                <option value="menu" selected="selected">菜单</option>
                                <option value="permission">权限</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <label>权限码：</label>
                            <input type="text" class="form-control permission-code" placeholder="长度限制50字符" disabled="disabled">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <label>URL：</label>
                            <input type="text" class="form-control control" placeholder="长度限制30字符">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <label>图标：</label>
                            <input type="text" class="form-control icon" placeholder="长度限制60字符">
                        </div>
                    </div>
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
<script type="text/javascript" src="../../resources/plugin/layer/layer.js"></script>
<script type="text/javascript" src="../../resources/plugin/laypage/laypage.js"></script>
<script type="text/javascript" src="../../resources/utils/paging.js"></script>
<script type="text/javascript" src="js/permissions.js"></script>

</body>
</html>