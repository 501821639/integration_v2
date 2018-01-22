/**
 * Created by GSN on 2017/4/28.
 * permissions.js
 */

(function ($) {

    //加载layer其他弹出框样式
    layer.config({extend: 'moon/style.css'});

    var maup = {

        jQueryObject: $('#modal-au-permission'),//弹出层

        init: function () {
            this.typeChange();
            this.pidBlur();
            return this;
        },

        //pid失去焦点事件
        pidBlur: function () {
            var This = this;
            this.jQueryObject.find('.permission-pid').blur(function () {
                var pId = This.jQueryObject.find('.permission .permission-pid').val();
                if (pId === null || pId.trim() === '' || pId === '0') {
                    This.jQueryObject.find('.permission .pidName').val('最高节点');
                } else {
                    var permissionJson = permission.getPermission(pId.trim());
                    if (permissionJson !== null) {
                        This.jQueryObject.find('.permission .pidName').val(permissionJson.name);
                    } else {
                        This.jQueryObject.find('.permission .pidName').val('未查询到该权限ID相关信息');
                    }
                }
            });
        },

        //类型改变事件
        typeChange: function () {

            var This = this;

            this.jQueryObject.find(".permission .type").change(function () {
                var val = $(this).val();
                if (val === 'permission') {
                    This.jQueryObject.find('.permission .permission-code').removeAttr('disabled');
                    This.jQueryObject.find('.permission .control').attr('disabled', 'disabled');
                    This.jQueryObject.find('.permission .icon').attr('disabled', 'disabled');
                } else if (val === 'menu') {
                    This.jQueryObject.find('.permission .permission-code').attr('disabled', 'disabled');
                    This.jQueryObject.find('.permission .control').removeAttr('disabled');
                    This.jQueryObject.find('.permission .icon').removeAttr('disabled');
                }
            });
        }

    }.init();

    var permission = {

        pJsonArray: null,//所有权限的json数组

        update: $('#permission-update').length,//修改权限

        delete: $('#permission-delete').length,//删除权限

        addOrUpdate: null,//添加(add)或修改(update)

        init: function () {
            this.addPermission();
            this.confirmClick();
            this.findPermissions();
            this.sl();
            this.permissionDelete();
            this.permissionUpdate();
            return this;
        },

        //添加权限按钮事件
        addPermission: function () {
            var This = this;
            $('#btn-permission-add').click(function () {
                This.addOrUpdate = 'add';
                var $zk = $('#content').find('.zk');
                if ($zk.length !== 0) {
                    maup.jQueryObject.find('.permission').find('.permission-pid').val($zk.html());
                }
                maup.jQueryObject.modal('show');
                maup.jQueryObject.find('.permission-pid').blur();
            });
        },

        //添加,修改弹出层确定按钮事件
        confirmClick: function () {

            var This = this;

            maup.jQueryObject.find('.confirm').click(function () {

                var $permission = maup.jQueryObject.find('.permission');

                var name = $permission.find('.name').val(),
                    pId = $permission.find('.permission-pid').val(),
                    order = $permission.find('.order').val(),
                    type = $permission.find('.type').val(),
                    permissionCode = $permission.find('.permission-code').val(),
                    control = $permission.find('.control').val(),
                    icon = $permission.find('.icon').val();

                if (pId === null || pId.trim() === '') {
                    pId = '0';
                }

                if (order === null || order.trim() === '') {
                    order = 0;
                }

                var $btn = $(this).button('loading');

                //添加
                if (This.addOrUpdate === 'add') {

                    $.ajax({
                        'type': 'post',
                        'url': './../../permission/save.shtml',
                        data: {
                            'name': name,
                            'pId': pId,
                            'order': order,
                            'type': type,
                            'permissionCode': permissionCode,
                            'control': control,
                            'icon': icon
                        },
                        success: function (data) {
                            $btn.button('reset');
                            if (data === 'success') {
                                This.findPermissions();
                                layer.alert('已添加 ', {icon: 1, skin: 'layer-ext-moon'});
                            } else {
                                layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                            }
                        },
                        error: function () {
                            $btn.button('reset');
                            layer.alert('添加权限失败!系统出现异常 ', {icon: 0, skin: 'layer-ext-moon'});
                        }
                    });
                }

                //修改
                if (This.addOrUpdate === 'update') {
                    var id = $permission.find('.permission-id').val();
                    $.ajax({
                        'type': 'post',
                        'url': './../../permission/update.shtml',
                        data: {
                            'id': id,
                            'name': name,
                            'pId': pId,
                            'order': order,
                            'type': type,
                            'permissionCode': permissionCode,
                            'control': control,
                            'icon': icon
                        },
                        success: function (data) {
                            $btn.button('reset');
                            if (data === 'success') {
                                This.findPermissions();
                                layer.alert('已修改 ', {icon: 1, skin: 'layer-ext-moon'});
                            } else {
                                layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                            }
                        },
                        error: function () {
                            $btn.button('reset');
                            layer.alert('修改权限失败!系统出现异常 ', {icon: 0, skin: 'layer-ext-moon'});
                        }
                    });
                }

            });
        },

        //修改事件
        permissionUpdate: function () {

            var This = this;

            $('#content').on('click', '.permission-update', function () {

                var id = $(this).attr('data-pid');

                var permissionJson = This.getPermission(id);

                if (permissionJson === null) {
                    layer.alert('数据出错啦！！！ ', {icon: 0, skin: 'layer-ext-moon'});
                    return;
                }

                var $permission = maup.jQueryObject.find('.permission');

                $permission.find('.permission-id').val(permissionJson.id);
                $permission.find('.name').val(permissionJson.name);

                $permission.find('.permission-pid').val(permissionJson.pId);
                $permission.find('.permission-pid').blur();

                $permission.find('.order').val(permissionJson.order);

                if (permissionJson.type === 'menu') {
                    $permission.find('.type').find('option[value="menu"]').attr('selected', 'selected');
                }
                if (permissionJson.type === 'permission') {
                    $permission.find('.type').find('option[value="permission"]').attr('selected', 'selected');
                }
                $permission.find('.type').change();

                $permission.find('.permission-code').val(permissionJson.permissionCode);
                $permission.find('.control').val(permissionJson.control);
                $permission.find('.icon').val(permissionJson.icon);

                //修改标记参数
                This.addOrUpdate = 'update';

                maup.jQueryObject.modal('show');

            });
        },

        //删除事件
        permissionDelete: function () {

            var This = this;

            $('#content').on('click', '.permission-delete', function () {

                var id = $(this).attr('data-pid'),
                    index = layer.confirm('确定删除(' + permission.getPermission(id).name + ') 这个权限？', {
                        btn: ['确定', '取消']
                    }, function () {

                        layer.close(index);
                        index = layer.msg('正在删除权限请稍后...', {icon: 16, shade: 0.01, time: 0});

                        $.ajax({
                            'type': 'get',
                            'url': './../../permission/delete.shtml',
                            data: {
                                'permissionId': id
                            },
                            success: function (data) {
                                if (data === 'success') {
                                    This.findPermissions();
                                    layer.alert('已删除', {icon: 1, skin: 'layer-ext-moon'});
                                } else {
                                    layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                                }
                                layer.close(index);
                            },
                            error: function () {
                                layer.close(index);
                                layer.alert('删除角色失败!系统出现异常 ', {icon: 0, skin: 'layer-ext-moon'});
                            }
                        });

                    }, function () {
                    });
            });
        },

        //id、pid省略 点击显示省略部分
        sl: function () {
            $('#content').on('click', '.sl', function () {
                $('#content').find('.zk').html('...').removeClass('zk');
                $(this).addClass('zk').html($(this).attr('title'));
            });
        },


        //查询所有权限列表
        findPermissions: function () {

            var This = this;

            $.ajax({
                'type': 'get',
                'url': './../../permission/find.shtml',
                dataType: 'json',
                success: function (jsonArray) {

                    This.pJsonArray = jsonArray;

                    var tidyArray = [],
                        syArray = [],
                        lsArray = [];

                    start(0);

                    for (var i = 0; i < tidyArray.length; i++) {
                        if (tidyArray[i].pId == 0 && i != 0) {
                            syArray.push(lsArray);
                            lsArray = [];
                        }
                        lsArray.push(tidyArray[i]);
                        if (i === tidyArray.length - 1) {
                            syArray.push(lsArray);
                        }
                    }

                    var tabs = [];

                    for (var i = 0; i < syArray.length; i++) {

                        var content = '',
                            tabName = null;

                        for (var j = 0; j < syArray[i].length; j++) {

                            if(j === 0){
                                tabName = syArray[i][j].name;
                            }

                            var cz = '';

                            if (This.update) {
                                cz += '<button type="button" class="btn btn-warning btn-xs permission-update" data-pid="' + syArray[i][j].id + '">修改</button>&nbsp;';
                            }

                            if (This.delete) {
                                cz += '<button type="button" class="btn btn-danger btn-xs permission-delete" data-pid="' + syArray[i][j].id + '">删除</button>';
                            }

                            var backgroundTr = '';

                            if(j !== 0 && syArray[i][j].type !== 'permission'){
                                backgroundTr = ' style="background-color:#f9f9f9;"';
                            }

                            content += '<tr'+ backgroundTr +'>' +
                                '<td title="' + syArray[i][j].id + '" style="cursor: pointer" class="sl">...</td>' +
                                '<td>' + syArray[i][j].name + '</td>' +
                                '<td title="' + syArray[i][j].pId + '" style="cursor: pointer" class="sl">...</td>' +
                                '<td>' + syArray[i][j].order + '</td>' +
                                '<td>' + syArray[i][j].type + '</td>' +
                                '<td>' + syArray[i][j].permissionCode + '</td>' +
                                '<td>' + syArray[i][j].control + '</td>' +
                                '<td>' + syArray[i][j].icon + '</td>' +
                                '<td>' + cz + '</td>' +
                                '</tr>';
                        }

                        var hb =
                            '<div class="table-responsive">' +
                            '<table class="table table-hover table-bordered text-center">' +
                            '<thead>' +
                            '<tr>' +
                            '<th>ID</th>' +
                            '<th>权限名称</th>' +
                            '<th>PID</th>' +
                            '<th>排序</th>' +
                            '<th>资源类型</th>' +
                            '<th>权限码</th>' +
                            '<th>URL</th>' +
                            '<th>图标</th>' +
                            '<th>操作</th>' +
                            '</tr>' +
                            '</thead>' +
                            '<tbody>' + content + '</tbody>' +
                            '</table>' +
                            '</div>';

                        tabs.push({
                            'id' : 'nav-' + i,
                            'name' : tabName,
                            'str' : hb
                        });

                    }

                    var navJustified = '',
                        tabContent = '';

                    for(var i = 0; i < tabs.length; i++){

                        navJustified += '<li><a href="#'+ tabs[i].id +'" data-toggle="tab">'+ tabs[i].name +'</a></li>';
                        tabContent += '<div class="tab-pane fade" id="'+ tabs[i].id +'">'+ tabs[i].str +'</div>';

                    }

                    var $tabs1 = $('#tabs-1');
                    $tabs1.find('.nav').html(navJustified);
                    $tabs1.find('.tab-content').html(tabContent);
                    $tabs1.find('.nav li:eq(0)').find('a').click();

                    function start(id) {
                        var arr = getChildrenNode(id);
                        for (var i = 0; i < arr.length; i++) {
                            tidyArray.push(arr[i]);
                            start(arr[i].id);
                        }
                    }

                    //递归子节点
                    function getChildrenNode(id) {
                        var arr = [];
                        for (var i = 0; i < jsonArray.length; i++) {
                            if (jsonArray[i].pId == id) {
                                arr.push(jsonArray[i]);
                            }
                        }
                        return arr;
                    }

                },
                error: function () {
                    layer.alert('查询权限列表失败!系统出现异常 ', {icon: 0, skin: 'layer-ext-moon'});
                }
            });

        },

        //根据id获取对应的对象
        getPermission: function (id) {
            for (var i = 0; i < this.pJsonArray.length; i++) {
                if (this.pJsonArray[i].id === id) {
                    return this.pJsonArray[i];
                }
            }
            return null;
        }

    }.init();

})(jQuery);
