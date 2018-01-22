/**
 * Created by GSN on 2017/5/17.
 * company-nature.jsp
 */

(function ($) {

    //加载layer其他弹出框样式
    layer.config({extend: 'moon/style.css'});

    var companyNature = {

        //table单位性质列表
        $tab1: $('#tab-1'),

        //添加单位性质区域id
        scna: $('#show-company-nature-add'),

        //修改单位性质区域id
        scnu: $('#show-company-nature-update'),

        //添加单位性质按钮
        $cta: $('#company-nature-add'),

        //删除单位性质权限
        permissionCompanyNatureDelete: $('#company-nature-delete').length,

        //修改单位性质权限
        permissionCompanyNatureUpdate: $('#company-nature-update').length,

        //单位性质集合
        jsonArrayCompanyNature: null,

        init: function () {
            this.findCompanyNature();
            this.addCompanyNature();
            this.deleteCompanyNature();
            this.updateCompanyNature();
            return this;
        },

        addCompanyNature: function () {

            var This = this;

            This.$cta.click(function () {
                This.scna.hide();
                This.scna.show(1000);
            });

            This.scna.find('.btn-close').click(function () {
                This.scna.hide(1000);
            });

            This.scna.find('.btn-submit').click(function () {

                var $btn = $(this).button('loading');

                $.ajax({
                    'type': 'post',
                    'url': '/companyNature/save.shtml',
                    data: {
                        'name': This.scna.find('.company-nature-name').val()
                    },
                    success: function (data) {

                        $btn.button('reset');

                        if (data === 'success') {

                            This.findCompanyNature();
                            layer.alert('已添加', {icon: 0, skin: 'layer-ext-moon'});

                        } else {
                            layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                        }

                    },
                    error: function () {
                        $btn.button('reset');
                        layer.alert('添加单位性质失败，系统出现异常', {icon: 0, skin: 'layer-ext-moon'});
                    }
                });

            });

        },

        deleteCompanyNature: function () {

            var This = this,
                index = null;

            This.$tab1.on('click', '.company-nature--delete', function () {

                var companyNature = This.getCompanyNature($(this).attr('data-cy-id'));

                index = layer.confirm('确定删除 ' + companyNature.name + '？', {
                    btn: ['确定', '取消']
                }, function () {

                    layer.close(index);
                    index = layer.msg('正在删除单位性质请稍后...', {icon: 16, shade: 0.01, time: 0});

                    $.ajax({
                        'type': 'get',
                        'url': '/companyNature/delete.shtml',
                        data: {
                            'id': companyNature.id
                        },
                        success: function (data) {

                            layer.close(index);

                            if (data === 'success') {

                                This.findCompanyNature();
                                layer.alert('已删除', {icon: 0, skin: 'layer-ext-moon'});

                            } else {
                                layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                            }

                        },
                        error: function () {
                            layer.close(index);
                            layer.alert('删除单位性质失败，系统出现异常', {icon: 0, skin: 'layer-ext-moon'});
                        }
                    });

                }, function () {
                });

            });

        },

        updateCompanyNature: function () {

            var This = this,
                companyNature = null;

            This.$tab1.on('click', '.company-nature-update', function () {
                This.scnu.hide();
                This.scnu.show(1000);
                companyNature = This.getCompanyNature($(this).attr('data-cy-id'));
                This.scnu.find('.company-nature-name').val(companyNature.name);
            });

            This.scnu.find('.btn-close').click(function () {
                This.scnu.hide(1000);
            });

            This.scnu.find('.btn-submit').click(function () {

                var $btn = $(this).button('loading');

                $.ajax({
                    'type': 'post',
                    'url': '/companyNature/update.shtml',
                    data: {
                        'id' : companyNature.id,
                        'name': This.scnu.find('.company-nature-name').val()
                    },
                    success: function (data) {

                        $btn.button('reset');

                        if (data === 'success') {

                            This.findCompanyNature();
                            layer.alert('已修改', {icon: 0, skin: 'layer-ext-moon'});

                        } else {
                            layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                        }

                    },
                    error: function () {
                        $btn.button('reset');
                        layer.alert('修改单位性质失败，系统出现异常', {icon: 0, skin: 'layer-ext-moon'});
                    }
                });

            });

        },

        getCompanyNature: function (id) {

            var cn = null,
                This = this;

            $.each(This.jsonArrayCompanyNature, function (i) {

                if (This.jsonArrayCompanyNature[i].id === id) {
                    cn = This.jsonArrayCompanyNature[i];
                    return true;
                }

            });

            return cn;

        },

        findCompanyNature: function () {

            var This = this;

            $.ajax({
                'type': 'get',
                'url': '/companyNature/find.shtml',
                dataType: 'json',
                success: function (jsonArray) {

                    This.jsonArrayCompanyNature = jsonArray;

                    var s = '';

                    $.each(jsonArray, function (i) {

                        var cz = '';

                        if (This.permissionCompanyNatureUpdate) {
                            cz += '<button type="button" class="btn btn-warning btn-xs company-nature-update" data-cy-id="' + jsonArray[i].id + '">修改</button>&nbsp;';
                        }

                        if (This.permissionCompanyNatureDelete) {
                            cz += '<button type="button" class="btn btn-danger btn-xs company-nature--delete" data-cy-id="' + jsonArray[i].id + '">删除</button>';
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
