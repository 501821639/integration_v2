/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
;(function ($) {

    //加载layer其他弹出框样式
    layer.config({extend: 'moon/style.css'});

    var role = {

        //删除权限
        permissionDelete: $('#role-permission-delete').length,

        //修改权限
        permissionUpdate: $('#role-permission-update').length,

        //为角色分配权限
        rolePermissionSave: $('#role-permission-save').length,

        //查询权限列表
        permissionFind : $('#permission-find').length,

        //查询角色所拥有权限的权限
        rolepermissionFindPermission : $('#rolepermission-findpermission').length,

        init: function () {
            this.getRoles();
            this.addRole();
            this.deleteRole();
            this.updateRole();
            this.fppRole();
            return this;
        },

        //添加角色
        addRole: function () {

            var This = this,
                $roleAdd = $('#role-add');

            $('#role-show').find('.btn-role-add').click(function () {
                $roleAdd.hide();
                $roleAdd.show(1000);
            });

            $roleAdd.find('.role-btn-close').click(function () {
                $roleAdd.hide(1000);
            });

            $roleAdd.find('.role-btn-submit').click(function () {

                var roleName = $roleAdd.find('.role-name').val();
                if (roleName === null || roleName.trim() === '') {
                    layer.alert('请填写角色名 ', {icon: 0, skin: 'layer-ext-moon'});
                    return;
                }

                var $btn = $(this).button('loading');

                $.ajax({
                    'type': 'post',
                    'url': './../../role/save.shtml',
                    data: {
                        'name': roleName
                    },
                    success: function (data) {

                        $btn.button('reset');

                        if (data === 'success') {
                            This.getRoles();
                            layer.alert('已添加 ', {icon: 1, skin: 'layer-ext-moon'});
                        } else {
                            layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                        }
                    },
                    error: function () {
                        $btn.button('reset');
                        layer.alert('添加角色失败!系统出现异常 ', {icon: 0, skin: 'layer-ext-moon'});
                    }
                });
            });

        },

        //删除角色
        deleteRole: function () {

            var This = this;

            $('#role-show').find('.table-1').on('click', '.role-delete', function () {

                var roleId = $(this).attr('data-rid'),
                    roleName = $(this).attr('name');

                var index = layer.confirm('确定删除(' + roleName + ') 这个角色？', {
                    btn: ['确定', '取消']
                }, function () {

                    layer.close(index);
                    index = layer.msg('正在删除角色请稍后...', {icon: 16, shade: 0.01, time: 0});

                    $.ajax({
                        'type': 'get',
                        'url': './../../role/delete.shtml',
                        data: {
                            'id': roleId
                        },
                        success: function (data) {
                            if (data === 'success') {
                                layer.alert('角色已删除 ', {icon: 1, skin: 'layer-ext-moon'});
                                This.getRoles();
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

        //修改角色
        updateRole: function () {

            var roleId = null,
                $roleUpdate = $('#role-update'),
                This = this;

            $('#role-show').find('.table-1').on('click', '.role-update', function () {
                $roleUpdate.hide();
                $roleUpdate.show(1000);
                roleId = $(this).attr('data-rid');
                $roleUpdate.find('.role-name').val($(this).attr('name'));
            });

            $roleUpdate.find('.role-btn-submit').click(function () {

                var roleName = $roleUpdate.find('.role-name').val(),
                    index = layer.msg('正在修改角色请稍后...', {icon: 16, shade: 0.01, time: 0});

                $.ajax({
                    'type': 'post',
                    'url': './../../role/update.shtml',
                    data: {
                        'id': roleId,
                        'name': roleName
                    },
                    success: function (data) {
                        if (data === "success") {
                            This.getRoles();
                            layer.close(index);
                            layer.alert('角色已经修改完成 ', {icon: 1, skin: 'layer-ext-moon'});
                        } else {
                            layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                        }
                    },
                    error: function () {
                        layer.close(index);
                        layer.alert('修改角色失败!系统出现异常 ', {icon: 0, skin: 'layer-ext-moon'});
                    }
                });
            });

            $roleUpdate.find('.role-btn-close').click(function () {
                $roleUpdate.hide(1000);
            });

        },

        //为角色分配权限
        fppRole: function () {

            var $mp = $('#modal-permission');

            $('#role-show').find('.table-1').on('click', '.role-fpp', function () {

                var roleId = $(this).attr('data-rid'),
                    fppThis = this;

                if(!role.permissionFind){
                    layer.alert('无权限查询权限列表 ', {icon: 0, skin: 'layer-ext-moon'});
                    return;
                }

                if(!role.rolepermissionFindPermission){
                    layer.alert('无权限查询该角色所拥有的权限 ', {icon: 0, skin: 'layer-ext-moon'});
                    return;
                }

                if (!ztree.zNodes) {
                    layer.alert('权限列表正在获取中... ', {icon: 0, skin: 'layer-ext-moon'});
                    return;
                }
                if (!ztree.zNodes.length) {
                    layer.alert('系统中暂无权限列表 ', {icon: 0, skin: 'layer-ext-moon'});
                    return;
                }

                var index = layer.msg('正在获取角色权限请稍后...', {icon: 16, shade: 0.01, time: 0});

                $.ajax({
                    'type': 'post',
                    'url': './../../rolepermission/findrole.shtml',
                    dataType : 'json',
                    data: {
                        'roleId': roleId
                    },
                    success: function (jsonArray) {

                        setTimeout(function () {

                            for (var i = 0; i < ztree.zNodes.length; i++) {
                                ztree.zNodes[i].checked = false;
                                for (var j = 0; j < jsonArray.length; j++) {
                                    if (ztree.zNodes[i].id === jsonArray[j].id) {
                                        ztree.zNodes[i].checked = true;
                                        break;
                                    }
                                }

                            }

                            ztree.loadTree();
                            $mp.find('.modal-title').html($(fppThis).attr('name'));
                            layer.close(index);
                            $mp.attr('data-role-id', roleId).modal('show');

                        }, 500);

                    },
                    error: function () {
                        layer.close(index);
                        layer.alert('获取角色权限失败,无法显示用户拥有角色', {icon: 0, skin: 'layer-ext-moon'});
                    }
                });

            });

            $mp.find('.confirm').click(function () {

                var $btn = $(this).button('loading');

                var ztreeCheched = ztree.getZtreeChecked(),
                    roleId = $mp.attr('data-role-id'),
                    arrayRoleId = [];

                for (var i = 0; i < ztreeCheched.length; i++) {
                    var json = {};
                    json.permissionId = ztreeCheched[i];
                    arrayRoleId.push(JSON.stringify(json));
                }

                $.ajax({
                    'type': 'post',
                    'url': './../../rolepermission/save.shtml',
                    data: {
                        'roleId': roleId,
                        'arrayRoleId': '[' + arrayRoleId.toString() + ']'
                    },
                    success: function (data) {
                        if(data === 'success'){
                            $mp.modal('hide');
                            layer.alert('权限已分配', {icon: 1, skin: 'layer-ext-moon'});
                        }else{
                            layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                        }
                        $btn.button('reset');
                    },
                    error: function () {
                        layer.alert('权限分配失败!系统出现异常 ', {icon: 0, skin: 'layer-ext-moon'});
                        $btn.button('reset');
                    }
                });

            });
        },

        //获取角色
        getRoles: function () {
            var This = this;
            $.ajax({
                'type': 'get',
                'url': './../../role/find.shtml',
                dataType: 'json',
                success: function (jsonArray) {
                    This.setRoles(jsonArray);
                },
                error: function () {
                    layer.alert('获取角色失败!系统出现异常 ', {icon: 0, skin: 'layer-ext-moon'});
                }
            });
        },

        setRoles: function (jsonArray) {
            var s = '',
                This = this;
            $.each(jsonArray, function (i) {
                var cz = '';
                if (This.permissionUpdate) {
                    cz += '<button type="button" class="btn btn-warning btn-xs role-update" data-rid="' + jsonArray[i].id + '" name="' + jsonArray[i].name + '">修改</button>&nbsp;';
                }
                if (This.permissionDelete) {
                    cz += '<button type="button" class="btn btn-danger btn-xs role-delete" data-rid="' + jsonArray[i].id + '" name="' + jsonArray[i].name + '">删除</button>&nbsp;';
                }
                if (This.rolePermissionSave) {
                    cz += '<button type="button" class="btn btn-info btn-xs role-fpp" data-rid="' + jsonArray[i].id + '" name="' + jsonArray[i].name + '">权限</button>&nbsp;';
                }
                s += '<tr>' +
                    '<td>' + (i + 1) + '</td>' +
                    '<td>' + jsonArray[i].id + '</td>' +
                    '<td>' + jsonArray[i].name + '</td>' +
                    '<td>' + cz + '</td>' +
                    '</tr>';
            });
            $('#role-show').find('.table-1 tbody').html(s);
        }

    }.init();

    var ztree = {

        zNodes: [],

        setting: {
            check: {
                enable: true
            },
            data: {
                key: {
                    title: 'id'
                },
                simpleData: {
                    enable: true
                }
            }
        },

        init: function () {
            this.getPermissions();
            return this;
        },

        //获取ztree多选框选中的节点
        getZtreeChecked: function () {
            var nodesTrue = [];
            var nodes = $.fn.zTree.getZTreeObj("tree").getCheckedNodes(true);
            for (var i = 0; i < nodes.length; i++) {
                nodesTrue.push(nodes[i].id);
            }
            return nodesTrue;
        },

        //获取权限列表
        getPermissions: function () {

            var This = this;

            if(role.permissionFind){

                $.ajax({
                    'type': 'get',
                    'url': './../../permission/find.shtml',
                    dataType: 'json',
                    success: function (jsonArray) {
                        $.each(jsonArray, function (i) {
                            This.zNodes.push({
                                id :  jsonArray[i].id,
                                pId : jsonArray[i].pId,
                                name : jsonArray[i].name
                            });
                        });
                    },
                    error: function () {
                        layer.alert('获取权限列表失败!系统出现异常 ', {icon: 0, skin: 'layer-ext-moon'});
                    }
                });

            }

        },

        //加载ztree插件
        loadTree: function () {
            $.fn.zTree.init($('#tree'), this.setting, this.zNodes);
        }
    }.init();

})(jQuery);