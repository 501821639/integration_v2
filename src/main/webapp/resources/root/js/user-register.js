/**
 * Created by GSN on 2017/5/23.
 * user-register.html
 */


(function ($) {
    
    $('#get-user-auth').click(function () {

        var $btn = $(this).button('loading'),
            This = this;

        $.ajax({
            'type': 'post',
            'url': './../../user/auth.shtml',
            dataType: 'json',
            data: {
                'username': $('#user-name').val()
            },
            success: function (json) {

                $btn.button('reset');

                if (json.code === 'success') {

                    layer.alert('验证码已发送至您的手机', {icon: 1, skin: 'layer-ext-moon'});

                    var step = 60;
                    var index = setInterval(function () {
                        if(step <= 0 ){
                            alert(0);
                            clearTimeout(index);
                            $(This).html('获取验证码');
                            return;
                        }
                        step--;
                        $(This).html('获取验证码 ('+ step + ')');
                    },1000);
                } else {
                    layer.alert(json.result, {icon: 0, skin: 'layer-ext-moon'});
                }

            },
            error: function () {
                layer.close(index);
                layer.alert('系统出现异常信息,获取验证码失败', {icon: 0, skin: 'layer-ext-moon'});
            }
        });

    });

    $('#user-btn-save').click(function () {

        var username = $('#user-name').val(),
            password = $('#user-password').val(),
            password2 = $('#user-password2').val(),
            userAuth = $('#user-auth').val();

            if(username === null || username.trim() === ''){
                layer.alert('请输入手机号码', {icon: 0, skin: 'layer-ext-moon'});
                return;
            }

            username = username.trim();

            if(password === null || password === ''){
                layer.alert('请输入密码', {icon: 0, skin: 'layer-ext-moon'});
                return;
            }

            if(password !== password2){
                layer.alert('两次密码输入一致', {icon: 0, skin: 'layer-ext-moon'});
                return;
            }


        var $btn = $(this).button('loading');

        $.ajax({
            'type': 'post',
            'url': './../../user/register.shtml',
            data: {
                'username': username,
                'password' : password,
                'userAuth' : userAuth

            },
            success: function (data) {

                $btn.button('reset');

                if (data === 'success') {
                    layer.alert(username + '已成功注册', {icon: 0, skin: 'layer-ext-moon'});
                } else {
                    layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                }

            },
            error: function () {
                layer.alert('系统出现异常信息,注册失败', {icon: 0, skin: 'layer-ext-moon'});
            }
        });

    });

})(jQuery);
