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
                        <li><i class="fa fa-home" aria-hidden="true"></i><a href="javascript:;">Company Manage</a></li>
                        <li><a>Type</a></li>
                    </ul>
                </div>
            </div>
            <div class="row">

                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="show-company-type-add" style="display: none;">
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <input type="text" class="form-control company-type-name" placeholder="单位类型名长度限制40汉字、字符">
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                    <button class="btn btn-wide btn-info btn-submit">添加</button>
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                    <button class="btn btn-wide btn-close">关闭</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="show-company-type-update" style="display: none">
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <input type="text" class="form-control company-type-name" placeholder="单位类型名长度限制40汉字、字符">
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                    <button class="btn btn-wide btn-info btn-submit">修改</button>
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                    <button class="btn btn-wide btn-close">关闭</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel">
                        <div class="panel-content">

                            <%--添加单位类型权限--%>
                            <shiro:hasPermission name="companytype:save">
                                <input type="button" class="btn btn-primary animated fadeInRight" id="company-type-add"
                                       value="添加单位类型">
                            </shiro:hasPermission>

                            <%--删除单位类型权限--%>
                            <shiro:hasPermission name="companytype:delete">
                                <input type="hidden" id="company-type-delete">
                            </shiro:hasPermission>

                            <%--修改单位类型权限--%>
                            <shiro:hasPermission name="companytype:update">
                                <input type="hidden" id="company-type-update">
                            </shiro:hasPermission>

                            <div class="table-responsive animated fadeInUp">
                                <table class="table table-hover table-bordered text-center" id="tab-1">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>ID</th>
                                        <th>单位类型名称</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody></tbody>
                                </table>
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
<script type="text/javascript" src="../../resources/plugin/layer/layer.js"></script>
<script type="text/javascript" src="js/company-type.js"></script>

</body>
</html>