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

    <link rel="stylesheet" href="css/company-update.css">
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
                        <li><i class="fa fa-home" aria-hidden="true"></i><a href="#">Company Manage</a></li>
                        <li><a>Update</a></li>
                    </ul>
                </div>
            </div>

            <div class="row" id="row-1">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel">
                        <div class="panel-content">
                            <div class="table-responsive animated fadeInUp">
                                <table class="table table-hover table-bordered text-center" id="tab-company-1">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>单位名称</th>
                                        <th>审核状态</th>
                                        <th>注册时间</th>
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

            <div class="row" id="row-2">
                <div class="col-md-12 col-md-offset-0">
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-md-12">
                                    <form id="inline-validation" class="form-horizontal form-stripe" novalidate="novalidate">

                                        <input type="hidden" id="company-id">

                                        <div class="form-group">
                                            <label for="company-name" class="col-sm-3 control-label">单位名称：</label>
                                            <div class="col-sm-6">
                                                <input class="form-control" id="company-name" type="text">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="company-p-id" class="col-sm-3 control-label">上级单位：</label>
                                            <div class="col-sm-6">
                                                <div class="input-group">
                                                    <input class="form-control" id="company-p-id" type="text" data-company-id="0" disabled="disabled" placeholder="未选择上级单位">
                                                    <span class="input-group-btn">
                                                        <button class="btn btn-wide btn-o btn-info" id="company-p-id-search" type="button">搜索</button>
                                                        <button class="btn btn-wide btn-o btn-info" id="company-p-id-reset" type="button">重置</button>
                                                   </span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="company-type" class="col-sm-3 control-label">单位类型：</label>
                                            <div class="col-sm-6">
                                                <select class="form-control" id="company-type"></select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="company-nature" class="col-sm-3 control-label">单位性质：</label>
                                            <div class="col-sm-6">
                                                <select class="form-control" id="company-nature"></select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="company-region" class="col-sm-3 control-label">省市直辖区：</label>
                                            <div class="col-sm-6">
                                                <div class="input-group">
                                                    <select class="form-control" id="company-region"></select>
                                                    <span class="input-group-btn">
                                                        <button class="btn btn-wide btn-o btn-info" id="company-region-reset" type="button">重选</button>
                                                   </span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="company-code" class="col-sm-3 control-label">社会信用代码：</label>
                                            <div class="col-sm-6">
                                                <input class="form-control" id="company-code" type="text">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="company-phone" class="col-sm-3 control-label">座机：</label>
                                            <div class="col-sm-6">
                                                <input class="form-control" id="company-phone" type="text">
                                            </div>
                                        </div>

                                        <div class="form-group" id="company-business-license">
                                            <label  class="col-sm-3 control-label">
                                                <a href="javascript:;" id="delete-cbl">删除</a>&nbsp;
                                                <a href="javascript:;" id="upload-cbl">上传</a>&nbsp;
                                                单位凭证：
                                            </label>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-sm-offset-3 col-sm-9">
                                                <button type="button" class="btn btn-primary" id="submit-company">提交</button>
                                                <button type="button" class="btn btn-primary" id="return-company-list">返回列表</button>
                                            </div>
                                        </div>
                                    </form>
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

<!-- 单位列表弹出层 -->
<div class="modal fade" id="modal-company-list" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="margin:140px auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">单位列表</h4>
            </div>
            <div class="modal-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>单位名称</th>
                        <th>审核状态</th>
                        <th>上级单位</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
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

<script type="text/javascript" src="../user/js/user-message.js"></script>
<script type="text/javascript" src="../../resources/plugin/layer/layer.js"></script>
<script type="text/javascript" src="../../resources/utils/html5Upload.js"></script>
<script type="text/javascript" src="js/company-update.js"></script>

</body>
</html>