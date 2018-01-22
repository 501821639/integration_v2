/**
 * Created by GSN on 2017/5/23.
 * company-register.html
 */

(function ($) {

    //加载layer其他弹出框样式
    layer.config({extend: 'moon/style.css'});

    var $company = $('#company'),

        //所有地区
        regionArrayJson = null,

        $mcl = $('#modal-company-transverter'),

        //查询到的所有单位
        companyJsonArray = null;

    //单位类型
    $.ajax({
        'type': 'get',
        'url': 'companyType/find.shtml',
        dataType: 'json',
        success: function (jsonArray) {
            var s = '<option value="0">请选择单位类型</option>';
            $.each(jsonArray, function (i) {
                s += '<option value="' + jsonArray[i].id + '">' + jsonArray[i].name + '</option>';
            });
            $company.find('.type').html(s);
        }
    });

    //单位性质
    $.ajax({
        'type': 'get',
        'url': 'companyNature/find.shtml',
        dataType: 'json',
        success: function (jsonArray) {
            var s = '<option value="0">请选择单位性质</option>';
            $.each(jsonArray, function (i) {
                s += '<option value="' + jsonArray[i].id + '">' + jsonArray[i].name + '</option>';
            });
            $company.find('.nature').html(s);
        }
    });

    //省市直辖区
    $.ajax({
        'type': 'get',
        'url': 'region/find.shtml',
        dataType: 'json',
        success: function (jsonArray) {
            regionArrayJson = jsonArray;
            setRegion(jsonArray);
        }
    });

    //插入地区数据
    function setRegion(jsonArray) {
        var s = '<option value="0">请选择地区</option>';
        $.each(jsonArray, function (i) {
            if (jsonArray[i].pId === '0') {
                s += '<option value="' + jsonArray[i].id + '" name="' + jsonArray[i].name + '">' + jsonArray[i].name + '</option>';
            }
        });
        $company.find('.region').html(s);
    }

    //地区改变事件
    $company.find('.region').on('change', function () {

        var name = $(this).find('option:selected').attr('name'),
            id = $(this).val(),
            s = '<option value="0">请选择(' + name + ')下级区域</option>',
            b = false;

        if (id === '0') {
            return;
        }

        $.each(regionArrayJson, function (i) {
            if (regionArrayJson[i].pId === id) {
                b = true;
                s += '<option value="' + regionArrayJson[i].id + '" name="' + name + '-' + regionArrayJson[i].name + '">' + regionArrayJson[i].name + '</option>';
            }
        });

        if (b) {
            $(this).html(s);
        } else {
            $(this).find('option:not(:selected)').remove();
            var $so = $(this).find('option:selected');
            $so.html($so.attr('name'));
        }

    });

    //重置地区
    $company.find('.reset-region').click(function () {
        setRegion(regionArrayJson);
    });

    //重置上级单位信息
    $company.find('.reset-company').click(function () {
        $company.find('.p-name').val('').attr('data-company-id', '0');
    });
    //搜索上级单位
    $company.find('.search').click(function () {

        var index = layer.prompt({title: '请输入单位名称', formType: 3}, function (text) {

            if (text === null || text.trim() === '') {
                return;
            }

            layer.close(index);

            index = layer.msg('请稍后...', {icon: 16, shade: 0.01, time: 0});

            $.ajax({
                'type': 'post',
                'url': './../../company/findSuper.shtml',
                dataType: 'json',
                data: {
                    'name': text
                },
                success: function (jsonArray) {

                    layer.close(index);
                    companyJsonArray = jsonArray;

                    if (jsonArray.length === 0) {
                        layer.alert(text + ' 未查询到该单位相关信息,请核对是否有错误单位名', {icon: 0, skin: 'layer-ext-moon'});
                    } else {

                        var s = '';

                        $.each(jsonArray, function (i) {

                            var checkName = '';

                            if (jsonArray[i].check === 0) {
                                checkName = '等待审核';
                            }

                            if (jsonArray[i].check === 1) {
                                checkName = '审核通过';
                            }

                            if (jsonArray[i].check === 2) {
                                checkName = '审核未通过';
                            }

                            s += '<tr>' +
                                '<td>' + (i + 1) + '</td>' +
                                '<td>' + jsonArray[i].name + '</td>' +
                                '<td>' + checkName + '</td>' +
                                '<td><a href="javascript:;" class="super-company" data-company-id="' + jsonArray[i].id + '">上级单位</a></td>' +
                                '<td>' +
                                '<input type="checkbox" class="company-che" value="' + jsonArray[i].id + '" name="' + jsonArray[i].name + '" data-check="' + jsonArray[i].check + '">' +
                                '</td>' +
                                '</tr>';
                        });

                        $mcl.modal('show').find('.table').find('tbody').html(s);
                    }

                },
                error: function () {
                    layer.close(index);
                    layer.alert('系统出现异常信息,无法查询上级单位', {icon: 0, skin: 'layer-ext-moon'});
                }
            });
        });
    });

    //查看上级单位
    $mcl.on('click', '.super-company', function () {

        var superCompany = null,
            companyId = $(this).attr('data-company-id');

        $.each(companyJsonArray, function (i) {
            if (companyJsonArray[i].id === companyId) {
                superCompany = companyJsonArray[i];
                return false;
            }
        });

        if (superCompany.companySuperMessage) {

            var s = '';

            getCompany(superCompany);

            layer.alert(s, { skin: 'layer-ext-moon'});

            function getCompany(company) {

                s += '<div style="width: 100%;" class="text-center">' + company.name + '</div>';

                if(company.companySuperMessage){
                    s += '<div style="width: 100%;" class="text-center">↓</div>';
                    getCompany(company.companySuperMessage);
                }

            }

        } else {
            layer.alert('该单位无上级单位', {icon: 0, skin: 'layer-ext-moon'});
        }

    });

    //上级单位弹出层复选框点击事件
    $mcl.on('click', '.company-che', function () {
        var This = this;
        $mcl.find('.company-che').each(function () {
            if (this !== This) {
                $(this).attr('checked', false);
            }
        });
    });
    //上级单位弹出层确定事件
    $mcl.find('.confirm').click(function () {

        var $che = $('input[class="company-che"]:checked');

        if ($che.length) {

            var check = $che.attr('data-check');

            if (check == 0) {
                layer.alert('该单位正在审核，不能使用', {icon: 0, skin: 'layer-ext-moon'});
                return;
            }

            if (check == 2) {
                layer.alert('该单位审核未通过，不能使用', {icon: 0, skin: 'layer-ext-moon'});
                return;
            }

            $mcl.modal('hide');
            $company.find('.p-name').val($che.attr('name')).attr('data-company-id', $che.val());
        } else {
            layer.alert('请选择一个单位', {icon: 0, skin: 'layer-ext-moon'});
        }
    });

    //获取验证码
    $company.find('.get-auth').click(function () {

        var index = layer.msg('请稍后...', {icon: 16, shade: 0.01, time: 0}),
            This = this;

        $.ajax({
            'type': 'post',
            'url': './../../company/auth.shtml',
            dataType: 'json',
            data: {
                'username': $company.find('.username').val()
            },
            success: function (json) {

                layer.close(index);

                if (json.code === 'success') {

                    layer.alert('验证码已发送至您的手机', {icon: 1, skin: 'layer-ext-moon'});

                    $(This).attr('disabled', 'disabled');
                    var step = 60;
                    index = setInterval(function () {
                        if (step <= 0) {
                            clearTimeout(index);
                            $(This).html('获取');
                            $(This).removeAttr('disabled');
                            return;
                        }
                        step--;
                        $(This).html('获取 (' + step + ')');
                    }, 1000);
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

    //提交单位信息
    $company.find('.btn-save').click(function () {

        var name = $company.find('.name').val(),
            pId = $company.find('.p-name').attr('data-company-id'),
            typeId = $company.find('.type').val(),
            natureId = $company.find('.nature').val(),
            regionId = $company.find('.region').val(),
            code = $company.find('.code').val(),
            phone = $company.find('.phone').val(),
            username = $company.find('.username').val(),
            userAuth = $company.find('.user-auth').val();

        var index = layer.msg('请稍后...', {icon: 16, shade: 0.01, time: 0});

        $.ajax({
            'type': 'post',
            'url': './../../company/register.shtml',
            data: {
                'name': name,
                'pId': pId,
                'typeId': typeId,
                'natureId': natureId,
                'regionId': regionId,
                'code': code,
                'phone': phone,
                'username': username,
                'userAuth': userAuth
            },
            success: function (data) {
                if (data === 'success') {
                    layer.close(index);
                    layer.alert('单位已注册，请登录系统完善资料', {icon: 1, skin: 'layer-ext-moon'});
                } else {
                    layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                }
            },
            error: function () {
                layer.close(index);
                layer.alert('系统出现异常信息,无法注册单位', {icon: 0, skin: 'layer-ext-moon'});
            }
        });

    });

})(jQuery);