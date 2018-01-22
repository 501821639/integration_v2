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
    <link rel="stylesheet" href="css/region.css">

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
                        <li><i class="fa fa-home" aria-hidden="true"></i><a href="#">Common Manage</a></li>
                        <li><a>Region</a></li>

                        <%--添加--%>
                        <shiro:hasPermission name="region:save">
                            <li><a href="javascript:;" id="region-add">Add</a></li>
                        </shiro:hasPermission>

                        <%--修改--%>
                        <shiro:hasPermission name="region:update">
                            <li><a href="javascript:;" id="region-update">Update</a></li>
                        </shiro:hasPermission>

                        <%--删除--%>
                        <shiro:hasPermission name="region:delete">
                            <li><a href="javascript:;" id="region-delete">Delete</a></li>
                        </shiro:hasPermission>

                    </ul>
                </div>
            </div>

            <div class="row" id="region-save-update" style="display: none;">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <form class="form-horizontal form-stripe">
                                        <input type="hidden" id="region-id">
                                        <div class="form-group">
                                            <label for="region-name" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">区域名称:</label>
                                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                                                <input type="text" class="form-control" id="region-name">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="region-p-id" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">上级节点:</label>
                                            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                                                <input type="text" class="form-control" id="region-p-id" placeholder="默认最高节点" disabled="disabled">
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                                <button type="button" class="btn btn-wide btn-block" id="region-p-id-empty">清空节点</button>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="region-postcode" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">邮编:</label>
                                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                                                <input type="text" class="form-control" id="region-postcode" placeholder="选填项">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2 col-lg-10 col-md-10 col-sm-10 col-xs-10">
                                                <button type="button" class="btn btn-primary" id="region-btn-save">保&nbsp;&nbsp;存</button>
                                                <button type="button" class="btn btn-primary" id="region-btn-update">更&nbsp;&nbsp;改</button>
                                                <button type="button" class="btn btn-wide" id="region-btn-close">关&nbsp;&nbsp;闭</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" id="tree-show" style="display:none;">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel">
                        <div class="panel-content">
                            <div style="width: 100%; height: 500px; overflow:auto;">
                                <ul id="tree" class="ztree"></ul>
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
<script type="text/javascript" src="../../resources/plugin/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="../../resources/plugin/layer/layer.js"></script>
<script type="text/javascript" src="js/region.js"></script>

</body>
</html>