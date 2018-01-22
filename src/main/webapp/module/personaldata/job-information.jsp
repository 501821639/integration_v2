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

        <div class="content" id="model-1">
            <div class="content-header">
                <div class="leftside-content-header">
                    <ul class="breadcrumbs">
                        <li><i class="fa fa-home" aria-hidden="true"></i><a href="#">Personal Data</a></li>
                        <li><a>Job</a></li>
                    </ul>
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
<script type="text/javascript" src="js/job-information.js"></script>

</body>
</html>