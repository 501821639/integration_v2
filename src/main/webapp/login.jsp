<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/resources/utils/taglibs.jsp" %>
<!doctype html>
<html lang="en" class="fixed accounts sign-in">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>Helsinki</title>
    <link rel="apple-touch-icon" sizes="120x120" href="${ctx}/adminAsx/favicon/apple-icon-120x120.png">
    <link rel="icon" type="image/png" sizes="192x192" href="${ctx}/adminAsx/favicon/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32" href="${ctx}/adminAsx/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="${ctx}/adminAsx/favicon/favicon-16x16.png">
    <link rel="stylesheet" type="text/css" href="${ctx}/adminAsx/vendor/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/adminAsx/stylesheets/font-awesome.4.7.0.css">
    <link rel="stylesheet" href="${ctx}/adminAsx/vendor/animate.css/animate.css">
    <link rel="stylesheet" href="${ctx}/adminAsx/stylesheets/css/style.css">
</head>
<body>

<div class="wrap">
    <div class="page-body animated slideInDown">
        <div class="logo">
            <img alt="logo" src="${ctx}/adminAsx/images/logo-dark.png"/>
        </div>
        <div class="box">
            <div class="panel mb-none">
                <div class="panel-content bg-scale-0">
                    <form href="/user/login.shtml" method="post" id="user">
                        <div class="form-group mt-md">
                            <span class="input-with-icon">
                                <input type="text" class="form-control" name="username" value="18686480242" placeholder="手机号">
                                <i class="fa fa-mobile-phone fa-2x"></i>
                            </span>
                        </div>
                        <div class="form-group">
                                <span class="input-with-icon">
                                    <input type="password" class="form-control" name="password" value="11111111" placeholder="密码">
                                    <i class="fa fa-key"></i>
                                </span>
                        </div>
                        <%--<div class="form-group">
                            <div class="checkbox-custom checkbox-primary">
                                <input type="checkbox" id="remember-me" value="option1" checked>
                                <label for="remember-me">Remember me</label>
                            </div>
                        </div>--%>
                        <div class="form-group">
                            <a href="javascript:void(0);" class="btn btn-primary btn-block sign-in">登录</a>
                        </div>
                        <div class="form-group text-center">
                            <a href="#">忘记密码？</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/adminAsx/vendor/jquery/jquery-1.12.3.min.js"></script>
<script src="${ctx}/adminAsx/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/adminAsx/vendor/nano-scroller/nano-scroller.js"></script>
<script src="${ctx}/adminAsx/javascripts/template-script.min.js"></script>
<script src="${ctx}/adminAsx/javascripts/template-init.min.js"></script>
<script>
    $('#user').find('.sign-in').click(function () {
        $(this).button('loading');
        $('#user').submit();
    });
</script>

</body>
</html>