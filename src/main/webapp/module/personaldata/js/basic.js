/**
 * Created by GSN on 2017/5/10.
 * 暂无描述
 */

(function ($, w) {


    //加载layer其他弹出框样式
    layer.config({extend: 'moon/style.css'});

    var count = 0;

    if(w.userAllMessage.card != ''){

        $('#user-card').attr({
            'disabled' : 'disabled',
            'title' : '信息已完善无法修改'
        }).val(w.userAllMessage.card);

        count++;

    }

    if(w.userAllMessage.idCard != ''){

        $('#user-id-card').attr({
            'disabled' : 'disabled',
            'title' : '信息已完善无法修改'
        }).val(w.userAllMessage.idCard);

        count++;

    }

    if(w.userAllMessage.mail != ''){

        $('#user-mail').attr({
            'disabled' : 'disabled',
            'title' : '信息已完善无法修改'
        }).val(w.userAllMessage.mail);

        count++;

    }

    if(count === 3){
        $('#user-title').html('');
    }

    $('#user-btn').click(function () {

        if(count === 3){
            layer.alert('信息已全部完善', {icon: 0, skin: 'layer-ext-moon'});
            return;
        }

        var userCard =  $('#user-card').val(),
            userIdCard = $('#user-id-card').val(),
            userMail = $('#user-mail').val();

        if(userCard === null || userCard.trim() === ''){
            layer.alert('请填写姓名', {icon: 0, skin: 'layer-ext-moon'});
            return;
        }

        if(userIdCard === null || userIdCard.trim() === ''){
            layer.alert('请填写身份证', {icon: 0, skin: 'layer-ext-moon'});
            return;
        }

        if(userMail === null || userMail.trim() === ''){
            layer.alert('请填写邮箱', {icon: 0, skin: 'layer-ext-moon'});
            return;
        }

        var $btn = $(this).button('loading');

        $.ajax({
            'type' : 'post',
            'url' : '/user/imc.shtml',
            data : {
                'card' : userCard,
                'idCard' : userIdCard,
                'mail' : userMail
            },
            success : function (data) {

                $btn.button('reset');

                if(data === 'success'){

                    var index = layer.confirm('信息已修改请重新登录', {
                        btn: ['确定']
                    }, function () {
                        layer.close(index);
                        window.location.href="/logout";
                    },function () {

                    });

                }else{
                    layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                }

            },
            error : function () {
                $btn.button('reset');
                layer.alert('保存失败系统出现异常信息', {icon: 0, skin: 'layer-ext-moon'});
            }
        });


    });



})(jQuery, window);