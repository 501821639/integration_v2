;(function ($) {

    //加载layer其他弹出框样式
    layer.config({extend: 'moon/style.css'});

    //常用变量
    var
        //模板1
        $m1 = $('#model-1'),

        //角色列表弹出层
        $mrl = $('#modal-role-list'),

        //是否拥有为用户分配角色权限
        permissionUesrRoleSave = $('#userrole-save').length,

        //角色容器
        arrayJsonRole = null,

        //获取角色是否出现异常
        isRoleList = false,

        //是否拥有查询角色权限
        roleFindPermission = $('#role-find').length,

        //是否拥有锁定账号权限
        permissionUesrUserLocked0 = $('#user-locked-0').length,

        //是否拥有解锁账号权限
        permissionUesrUserLocked1 = $('#user-locked-1').length;

    //加载layer其他弹出框样式
    layer.config({extend: 'moon/style.css'});

    //是否拥有查询角色权限
    if (roleFindPermission) {
        //加载角色列表
        $.ajax({
            'type': 'get',
            'url': './../../role/find.shtml',
            dataType: 'json',
            success: function (jsonArray) {
                arrayJsonRole = jsonArray;
            },
            error: function () {
                isRoleList = true;
            }
        });
    }

    //清除shiro授权缓存
    $('#clear-cached').click(function () {

        var index = layer.msg('请稍后...', {icon: 16, shade: 0.01, time: 0});

        $.ajax({
            'type': 'get',
            'url': '../../user/clear-cached.shtml',
            success: function (data) {

                if (data === 'success') {
                    setTimeout(function () {
                        layer.close(index);
                        layer.alert('shiro授权缓存已被清理 ', {icon: 1, skin: 'layer-ext-moon'});
                    }, 1000);
                } else {
                    layer.alert(data, {icon: 1, skin: 'layer-ext-moon'});
                }

            },
            error: function () {
                layer.close(index);
                layer.alert('清理缓存失败,系统出现异常', {icon: 1, skin: 'layer-ext-moon'});
            }
        });
    });

    //点击用户角色按钮事件
    $m1.find('.tab-1').on('click', '.role', function () {

        if(!roleFindPermission){
            layer.alert('没有查询角色权限', {icon: 0, skin: 'layer-ext-moon'});
            return;
        }

        if (isRoleList) {
            layer.alert('获取角色异常,请刷新页面重新尝试', {icon: 0, skin: 'layer-ext-moon'});
            return;
        }
        if (!arrayJsonRole) {
            layer.alert('角色正在加载中...', {icon: 0, skin: 'layer-ext-moon'});
            return;
        }
        if (!arrayJsonRole.length) {
            layer.alert('系统中还未生成角色', {icon: 0, skin: 'layer-ext-moon'});
            return;
        }

        if(!$('#userrole-findrole').length){
            layer.alert('无权限查询该用户所拥有的角色', {icon: 0, skin: 'layer-ext-moon'});
            return;
        }

        var index = layer.msg('正在获取用户角色请稍后...', {icon: 16, shade: 0.01, time: 0}),
            userId = $(this).attr('data-user-id');

        $.ajax({
            'type': 'post',
            'url': './../../userrole/findrole.shtml',
            dataType: 'json',
            data: {
                'userId': userId
            },
            success: function (jsonArray) {

                setTimeout(function () {

                    var s = '';

                    $.each(arrayJsonRole, function (i) {

                        var checkbox = '';

                        $.each(jsonArray, function (j) {
                            if (jsonArray[j].id === arrayJsonRole[i].id) {
                                checkbox = 'checked="checked"';
                                return false;
                            }
                        });

                        s += '<div class="row">' +
                            '<div class="col-lg-8 col-md-8 col-sm-18 col-xs-8 text-center">' + arrayJsonRole[i].name + '</div>' +
                            '<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"><label><input type="checkbox" name="role-checkbox" value="' + arrayJsonRole[i].id + '" ' + checkbox + '></label></div>' +
                            '</div>';
                    });

                    $mrl.find('.container-fluid').html(s);

                    layer.close(index);
                    $mrl.attr('user-id', userId).modal('show');

                }, 500);
            },
            error: function () {
                layer.close(index);
                layer.alert('获取用户角色失败,无法显示用户拥有角色', {icon: 0, skin: 'layer-ext-moon'});
            }
        });
    });

    //为用户分配角色之后点击确定事件
    $mrl.find('.confirm').click(function () {

        var userId = $mrl.attr('user-id'),
            arrayJson = [];

        $('input[name="role-checkbox"]:checked').each(function () {

            arrayJson.push(JSON.stringify({
                roleId : $(this).val()
            }));

        });

        $mrl.modal('hide');
        var index = layer.msg('请稍后...', {icon: 16, shade: 0.01, time: 0});

        $.ajax({
            'type': 'post',
            'url': './../../userrole/save.shtml',
            data: {
                'userId': userId,
                'arrayRoleId': '[' + arrayJson.toString() + ']'
            },
            success: function (data) {
                if(data === 'success') {
                    layer.close(index);
                    layer.alert('角色已分配完成', {icon: 1, skin: 'layer-ext-moon'});
                }else{
                    layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                }
            },
            error: function () {
                layer.close(index);
                layer.alert('系统出现异常信息,无法为用户分配角色', {icon: 0, skin: 'layer-ext-moon'});
            }
        });

    });

    $( "#user-locked-dialog" ).dialog({
        autoOpen: false,
        width: 400,
        show : 'fade',
        draggable : false,
        modal : true,
        buttons: [
            {
                text: "Ok",
                click: function() {

                    var userId = $( "#user-locked-dialog" ).attr('data-user-id'),
                        lockedRemark = $(this).find('.c1').val().replace(/\s+/g,""),
                        index = layer.msg('请稍后...', {icon: 16, shade: 0.01, time: 0}),
                        $this =  $( this );


                    $.ajax({
                        'type': 'post',
                        'url': './../../user/locked0.shtml',
                        data: {
                            'userId': userId,
                            'lockedRemark':lockedRemark
                        },
                        success: function (data) {

                            layer.close(index);

                            if(data === 'success') {

                                $this.dialog( "close" );

                                index = layer.msg('账号已锁定3秒后从新加载用户信息...', {icon: 16, shade: 0.01, time: 0});

                                setTimeout(function () {
                                    layer.close(index);
                                    loadPage();
                                },3000);
                            }else{
                                layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                            }

                        },
                        error: function () {
                            layer.close(index);
                            layer.alert('系统出现异常信息,无法锁定账号', {icon: 0, skin: 'layer-ext-moon'});
                        }
                    });

                }
            },
            {
                text: "Cancel",
                click: function() {
                    $( this ).dialog( "close" );
                }
            }
        ]
    });

    //锁定账号
    $m1.find('.tab-1').on('click', '.locked-0', function () {
        $( "#user-locked-dialog" ).attr({
            'data-user-id': $(this).attr('data-user-id')
        }).dialog( "open" ).find('.c1').attr({
            'placeholder' : '请在这里输入原因、锁定账号:'+$(this).attr('data-user-name')
        }).val('');
    });

    //解锁账号
    $m1.find('.tab-1').on('click', '.locked-1', function () {

        var userId = $(this).attr('data-user-id'),
            index = null;

        index = layer.confirm('确定解锁(' + $(this).attr('data-user-name') + ') 账号？', {
            btn: ['确定', '取消']
        }, function () {

            layer.close(index);
            index = layer.msg('正在解封账号请稍后...', {icon: 16, shade: 0.01, time: 0})

            $.ajax({
                'type': 'post',
                'url': './../../user/locked1.shtml',
                data: {
                    'userId': userId
                },
                success: function (data) {

                    layer.close(index);

                    if(data === 'success') {

                        index = layer.msg('账号已解锁3秒后从新加载用户信息...', {icon: 16, shade: 0.01, time: 0});

                        setTimeout(function () {
                            layer.close(index);
                            loadPage();
                        },3000);

                    }else{
                        layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                    }

                },
                error: function () {
                    layer.close(index);
                    layer.alert('系统出现异常信息,无法解锁账号', {icon: 0, skin: 'layer-ext-moon'});
                }
            });

        }, function () {});
    });

    //layerPage分页二次封装插件
    function loadPage() {

        //每页显示条数
        var length = 15;

        $('#laypage').paging({
            jQueryAjaxUrlDate: './../../../user/pageuser.shtml',
            jQueryAjaxUrlNumber: './../../../user/numberuser.shtml',
            jQueryAjaxData: {
                length: length
            },
            callbackFindJson: function (jsonArray, page) {

                var userStr = '';

                $.each(jsonArray, function (i) {

                    var locked = '否',
                        cz = '';

                    if (jsonArray[i].locked === 0) {

                        locked = '是';

                        if(permissionUesrUserLocked1){
                            cz += '<button type="button" class="btn btn-danger btn-xs locked-1" data-user-id="' + jsonArray[i].id + '" data-user-name="'+ jsonArray[i].username +'">解锁</button>&nbsp;';
                        }

                    }else{

                        if(permissionUesrUserLocked0){
                            cz += '<button type="button" class="btn btn-danger btn-xs locked-0" data-user-id="' + jsonArray[i].id + '" data-user-name="'+ jsonArray[i].username +'">锁定</button>&nbsp;';
                        }

                    }

                    if (permissionUesrRoleSave) {
                        cz += '<button type="button" class="btn btn-info btn-xs role" data-user-id="' + jsonArray[i].id + '">角色</button>';
                    }

                    userStr += '<tr>' +
                        '<td>' + (page * length + (i + 1)) + '</td>' +
                        '<td>' + jsonArray[i].username + '</td>' +
                        '<td title="'+ jsonArray[i].lockedRemark +'" style="cursor:help;">' + locked + '</td>' +
                        '<td>' + jsonArray[i].card + '</td>' +
                        '<td>' + jsonArray[i].regTime + '</td>' +
                        '<td>' + cz + '</td>' +
                        '</tr>';
                });

                $('#model-1').find('.tab-1 tbody').html(userStr);
            }
        });
    }

    loadPage();

})(jQuery);