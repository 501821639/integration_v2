/**
 * Created by GSN on 2017/5/16.
 * company-type.jsp
 */

(function ($) {

    //加载layer其他弹出框样式
    layer.config({extend: 'moon/style.css'});

    var companyType = {

        //table单位类型列表
        $tab1: $('#tab-1'),

        //添加单位类型区域id
        $scta: $('#show-company-type-add'),

        //修改单位类型区域id
        $sctu: $('#show-company-type-update'),

        //添加单位类型按钮
        $cta: $('#company-type-add'),

        //删除单位类型权限
        permissionCompanyTypeDelete: $('#company-type-delete').length,

        //修改单位类型权限
        permissionCompanyTypeUpdate: $('#company-type-update').length,

        //单位类型集合
        jsonArrayCompanyType: null,

        init: function () {
            this.findCompanyType();
            this.addCompanyType();
            this.deleteCompanyType();
            this.updateCompanyType();
            return this;
        },

        addCompanyType: function () {

            var This = this;

            This.$cta.click(function () {
                This.$scta.hide();
                This.$scta.show(1000);
            });

            This.$scta.find('.btn-close').click(function () {
                This.$scta.hide(1000);
            });

            This.$scta.find('.btn-submit').click(function () {

                var $btn = $(this).button('loading');

                $.ajax({
                    'type': 'post',
                    'url': '/companyType/save.shtml',
                    data: {
                        'name': This.$scta.find('.company-type-name').val()
                    },
                    success: function (data) {

                        $btn.button('reset');

                        if (data === 'success') {

                            This.findCompanyType();
                            layer.alert('已添加', {icon: 0, skin: 'layer-ext-moon'});

                        } else {
                            layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                        }

                    },
                    error: function () {
                        $btn.button('reset');
                        layer.alert('添加单位类型失败，系统出现异常', {icon: 0, skin: 'layer-ext-moon'});
                    }
                });

            });

        },

        deleteCompanyType: function () {

            var This = this,
                index = null;

            This.$tab1.on('click', '.company-type--delete', function () {

                var companyType = This.getCompanyType($(this).attr('data-cy-id'));

                index = layer.confirm('确定删除 ' + companyType.name + '？', {
                    btn: ['确定', '取消']
                }, function () {

                    layer.close(index);
                    index = layer.msg('正在删除单位类型请稍后...', {icon: 16, shade: 0.01, time: 0});

                    $.ajax({
                        'type': 'get',
                        'url': '/companyType/delete.shtml',
                        data: {
                            'id': companyType.id
                        },
                        success: function (data) {

                            layer.close(index);

                            if (data === 'success') {

                                This.findCompanyType();
                                layer.alert('已删除', {icon: 0, skin: 'layer-ext-moon'});

                            } else {
                                layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                            }

                        },
                        error: function () {
                            layer.close(index);
                            layer.alert('删除单位类型失败，系统出现异常', {icon: 0, skin: 'layer-ext-moon'});
                        }
                    });

                }, function () {
                });

            });

        },

        updateCompanyType: function () {

            var This = this,
                companyType = null;

            This.$tab1.on('click', '.company-type-update', function () {
                This.$sctu.hide();
                This.$sctu.show(1000);
                companyType = This.getCompanyType($(this).attr('data-cy-id'));
                This.$sctu.find('.company-type-name').val(companyType.name);
            });

            This.$sctu.find('.btn-close').click(function () {
                This.$sctu.hide(1000);
            });

            This.$sctu.find('.btn-submit').click(function () {

                var $btn = $(this).button('loading');

                $.ajax({
                    'type': 'post',
                    'url': '/companyType/update.shtml',
                    data: {
                        'id' : companyType.id,
                        'name': This.$sctu.find('.company-type-name').val()
                    },
                    success: function (data) {

                        $btn.button('reset');

                        if (data === 'success') {

                            This.findCompanyType();
                            layer.alert('已修改', {icon: 0, skin: 'layer-ext-moon'});

                        } else {
                            layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                        }

                    },
                    error: function () {
                        $btn.button('reset');
                        layer.alert('修改单位类型失败，系统出现异常', {icon: 0, skin: 'layer-ext-moon'});
                    }
                });

            });

        },

        getCompanyType: function (id) {

            var cy = null,
                This = this;

            $.each(This.jsonArrayCompanyType, function (i) {

                if (This.jsonArrayCompanyType[i].id === id) {
                    cy = This.jsonArrayCompanyType[i];
                    return true;
                }

            });

            return cy;

        },

        findCompanyType: function () {

            var This = this;

            $.ajax({
                'type': 'get',
                'url': '/companyType/find.shtml',
                dataType: 'json',
                success: function (jsonArray) {

                    This.jsonArrayCompanyType = jsonArray;

                    var s = '';

                    $.each(jsonArray, function (i) {

                        var cz = '';

                        if (This.permissionCompanyTypeUpdate) {
                            cz += '<button type="button" class="btn btn-warning btn-xs company-type-update" data-cy-id="' + jsonArray[i].id + '">修改</button>&nbsp;';
                        }

                        if (This.permissionCompanyTypeDelete) {
                            cz += '<button type="button" class="btn btn-danger btn-xs company-type--delete" data-cy-id="' + jsonArray[i].id + '">删除</button>';
                        }

                        s += '<tr>' +
                            '<td>' + (i + 1) + '</td>' +
                            '<td>' + jsonArray[i].id + '</td>' +
                            '<td>' + jsonArray[i].name + '</td>' +
                            '<td>' + cz + '</td>' +
                            '</tr>';

                    });

                    This.$tab1.find('tbody').html(s);

                }
            });

        }

    }.init();

})(jQuery);