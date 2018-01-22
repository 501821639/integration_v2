/**
 * Created by GSN on 2017/5/24.
 * company-update.jsp
 */

(function ($) {

    //加载layer其他弹出框样式
    layer.config({extend: 'moon/style.css'});

    //加载完成的数据条数
    var loadNumber = 0,

        //加载指定次数代表成功加载
        LoadComplete = 3;

    var region = {

        //地区集合
        regionJsonArray: null,

        init: function () {
            this.queryRegion();
            this.changeOption();
            this.resetData();
            return this;
        },

        queryRegion: function () {
            var This = this;
            $.ajax({
                'type': 'get',
                'url': '/region/find.shtml',
                dataType: 'json',
                success: function (jsonArray) {
                    This.regionJsonArray = jsonArray;
                    This.setData(jsonArray);
                    This.changeOption(jsonArray);
                    loadNumber++;
                },
                error: function () {
                    console.error('系统出现异常信息，无法查询地区');
                }
            });
        },

        setData: function (jsonArray) {

            var s = '<option value="0">请选择地区</option>';

            $.each(jsonArray, function (i) {
                if (jsonArray[i].pId === '0') {
                    s += '<option value="' + jsonArray[i].id + '" name="' + jsonArray[i].name + '">' + jsonArray[i].name + '</option>';
                }
            });

            $('#company-region').html(s);
        },

        setDefault: function (id) {

            var name = '',
                This = this;

            getSuper(id);

            $('#company-region').html('<option value="' + id + '">' + name + '</option>');

            function getSuper(rid) {
                var region = This.getRegion(rid);
                if (region !== null) {
                    name = region.name + name;
                    getSuper(region.pId);
                }
            }

        },

        getRegion: function (id) {
            var region = null,
                rja = this.regionJsonArray;
            $.each(rja, function (i) {
                if (rja[i].id === id) {
                    region = rja[i];
                    return false;
                }
            });
            return region;
        },

        //重选
        resetData: function () {
            var This = this;
            $('#company-region-reset').click(function () {
                This.setData(This.regionJsonArray);
            });
        },

        changeOption: function (jsonArray) {

            $('#company-region').on('change', function () {

                var name = $(this).find('option:selected').attr('name'),
                    id = $(this).val(),
                    s = '<option value="0">请选择(' + name + ')下级区域</option>',
                    b = false;

                if (id === '0') {
                    return;
                }

                $.each(jsonArray, function (i) {
                    if (jsonArray[i].pId === id) {
                        b = true;
                        s += '<option value="' + jsonArray[i].id + '" name="' + name + '-' + jsonArray[i].name + '">' + jsonArray[i].name + '</option>';
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
        }

    }.init();

    var companyNature = {

        //单位性质集合
        companyNatureJsonArray: null,

        init: function () {
            this.queryCompanyNature();
            return this;
        },

        queryCompanyNature: function () {
            var This = this;
            $.ajax({
                'type': 'get',
                'url': '/companyNature/find.shtml',
                dataType: 'json',
                success: function (jsonArray) {
                    This.companyNatureJsonArray = jsonArray;
                    This.setData(jsonArray);
                    loadNumber++;
                },
                error: function () {
                    console.error('系统出现异常信息，无法查询单位性质');
                }
            });
        },

        setData: function (jsonArray) {
            var s = '';
            $.each(jsonArray, function (i) {
                s += '<option value="' + jsonArray[i].id + '">' + jsonArray[i].name + '</option>';
            });
            $('#company-nature').html(s);
        },

        setDefault: function (id) {
            $('#company-nature').find('option').each(function () {
                if ($(this).val() === id) {
                    $(this).attr('selected', true);
                } else {
                    $(this).removeAttr('selected');
                }
            });
        }

    }.init();

    var companyType = {

        //单位类型集合
        companyTypeJsonArray: null,

        init: function () {
            this.queryCompanyType();
            return this;
        },

        //查询所有单位类型
        queryCompanyType: function () {
            var This = this;
            $.ajax({
                'type': 'get',
                'url': '/companyType/find.shtml',
                dataType: 'json',
                success: function (jsonArray) {
                    This.companyTypeJsonArray = jsonArray;
                    This.setData(jsonArray);
                    loadNumber++;
                },
                error: function () {
                    console.error('系统出现异常信息，无法查询单位类型');
                }
            });
        },

        setData: function (jsonArray) {
            var s = '';
            $.each(jsonArray, function (i) {
                s += '<option value="' + jsonArray[i].id + '">' + jsonArray[i].name + '</option>';
            });
            $('#company-type').html(s);
        },

        setDefault: function (id) {
            $('#company-type').find('option').each(function () {
                if ($(this).val() === id) {
                    $(this).attr('selected', true);
                } else {
                    $(this).removeAttr('selected');
                }
            });
        }

    }.init();

    var cbl = {

        init: function () {
            this.perview();
            this.uploadCBL();
            this.deleteCBL();
            this.mouseRight();
            return this;
        },

        mouseRight: function () {

            var lastThis = null;

            $('#company-business-license').on('contextmenu', '.identification-1', function () {

                if (lastThis === this) {
                    $(this).attr('data-select', 0).find('.cbl').css({
                        'border': 'none'
                    });
                    lastThis = null;
                    return false;
                }

                if (lastThis !== null) {
                    $(lastThis).attr('data-select', 0).find('.cbl').css({
                        'border': 'none'
                    });
                }

                $(this).attr('data-select', 1).find('.cbl').css({
                    'border': '4px solid grey'
                });

                lastThis = this;

                return false;
            });

        },

        deleteCBL: function () {

            var This = this;

            $('#delete-cbl').click(function () {

                var $cbl = $('#company-business-license').find('.identification-1[data-select=1]');

                if ($cbl.length === 0) {
                    layer.alert('请用鼠标右键点击要删除的单位凭证图片', {icon: 0, skin: 'layer-ext-moon'});
                    return false;
                }

                var index = layer.confirm('确认删除？', {
                    btn: ['是', '否']
                }, function () {

                    layer.close(index);
                    index = layer.msg('正在删除请稍后...', {icon: 16, shade: 0.01, time: 0});

                    $.ajax({
                        'type': 'get',
                        'url': '/companyBusinessLicense/delete.shtml',
                        data: {
                            'id': $cbl.find('.cbl').attr('data-id')
                        },
                        success: function (data) {

                            layer.close(index);

                            if (data === 'success') {
                                This.queryCBL($('#company-id').val());
                            } else {
                                layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                            }
                        },
                        error: function () {
                            layer.close(index);
                            layer.alert('系统出现异常信息，无法删除', {icon: 0, skin: 'layer-ext-moon'});
                        }
                    });

                }, function () {
                });

            });
        },

        uploadCBL: function () {

            var This = this,
                index = null;

            $('#upload-cbl').upload({
                url: '/companyBusinessLicense/save.shtml',
                'jquerySelect': true,
                data: {
                    'companyId': '#company-id'
                },
                /*progress: function (loaded, total) {
                 console.log(loaded + "/" + total);
                 },*/
                start: function () {
                    index = layer.msg('正在处理上传文件请稍后...', {icon: 16, shade: 0.01, time: 0});
                },
                success: function (data) {
                    if (data === 'success') {
                        layer.alert('单位证件已成功保存', {icon: 1, skin: 'layer-ext-moon'});
                        This.queryCBL($('#company-id').val());
                    } else {
                        layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                    }
                    layer.close(index);
                }
            });

        },

        queryCBL: function (companyId) {
            $.ajax({
                'type': 'get',
                'url': '/companyBusinessLicense/find.shtml',
                dataType: 'json',
                data: {
                    'companyId': companyId
                },
                success: function (jsonArray) {

                    var $cbl = $('#company-business-license');

                    $cbl.find('.identification-1').remove();

                    if (jsonArray.length === 0) {
                        $('#delete-cbl').hide();
                        $cbl.append(' <label  class="col-sm-3 text-left control-label identification-1">未上传任何相关证明</label>');
                    } else {

                        $('#delete-cbl').show();
                        var s = '';

                        $.each(jsonArray, function (i) {
                            s += '<div class="col-sm-1 identification-1">' +
                                '<img class="img-responsive cbl" title="' + jsonArray[i].quondamName + '" src="/' + jsonArray[i].url + '" data-name="' + jsonArray[i].quondamName + '" data-index="' + i + '" data-id="' + jsonArray[i].id + '">' +
                                '</div>';
                        });

                        $cbl.append(s);

                    }

                },
                error: function () {
                    console.error('系统出现异常信息，无法查询单位凭证');
                }
            });
        },

        //单位证件预览
        perview: function () {

            var $cbl = $('#company-business-license');

            $cbl.on('click', '.img-responsive', function () {

                var json = {
                    "title": "单位凭证",
                    "start": $(this).attr('data-index'),
                    "data": []
                };

                $cbl.find('.img-responsive').each(function () {
                    json.data.push({
                        'alt': $(this).attr('data-name'),
                        'src': $(this).attr('src')
                    });
                });

                layer.photos({
                    photos: json,
                    shift: Math.round(Math.random() * 6)
                });

            });
        }

    }.init();

    var company = {

        //可管理的单位集合
        companyJsonArray: null,

        //搜索的单位集合
        searchCompanyJsonArray: null,

        init: function () {
            this.queryUserCompany();
            this.remarkClick();
            this.updateClick();
            this.returnCompanyList();
            this.search();
            this.superCompany();
            this.submitData();
            return this;
        },

        //搜索单位事件
        search: function () {

            var This = this;

            $('#company-p-id-search').click(function () {

                var index = layer.prompt({title: '请输入单位名称', formType: 3}, function (text) {

                    if (text === null || text.trim() === '') {
                        return;
                    }

                    layer.close(index);
                    index = layer.msg('请稍后...', {icon: 16, shade: 0.01, time: 0});

                    $.ajax({
                        'type': 'post',
                        'url': '/company/findSuper.shtml',
                        dataType: 'json',
                        data: {
                            'name': text
                        },
                        success: function (jsonArray) {

                            layer.close(index);
                            This.searchCompanyJsonArray = jsonArray;

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

                                $('#modal-company-transverter').modal('show').find('.table').find('tbody').html(s);
                            }

                        },
                        error: function () {
                            layer.close(index);
                            layer.alert('系统出现异常信息,无法查询上级单位', {icon: 0, skin: 'layer-ext-moon'});
                        }
                    });
                });
            });
        },

        //上级单位其他事件
        superCompany: function () {

            var This = this,
                $mcl = $('#modal-company-transverter');

            //重置上级单位信息
            $('#company-p-id-reset').click(function () {
                $('#company-p-id').val('').attr('data-company-id', '0');
            });

            //上级单位弹出层确定事件
            $mcl.find('.confirm').click(function () {

                var $che = $('input[class="company-che"]:checked');

                if ($che.length) {

                    var check = $che.attr('data-check');

                    if (check === '0') {
                        layer.alert('该单位正在审核，不能使用', {icon: 0, skin: 'layer-ext-moon'});
                        return;
                    }

                    if (check === '2') {
                        layer.alert('该单位审核未通过，不能使用', {icon: 0, skin: 'layer-ext-moon'});
                        return;
                    }

                    $mcl.modal('hide');
                    $('#company-p-id').val($che.attr('name')).attr('data-company-id', $che.val());

                } else {
                    layer.alert('请选择一个单位', {icon: 0, skin: 'layer-ext-moon'});
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

            //查看上级单位
            $mcl.on('click', '.super-company', function () {

                var superCompany = null,
                    companyId = $(this).attr('data-company-id');

                $.each(This.searchCompanyJsonArray, function (i) {
                    if (This.searchCompanyJsonArray[i].id === companyId) {
                        superCompany = This.searchCompanyJsonArray[i];
                        return false;
                    }
                });

                if (superCompany.companySuperMessage) {

                    var s = '';

                    getCompany(superCompany);

                    layer.alert(s, {skin: 'layer-ext-moon'});

                    function getCompany(company) {

                        s += '<div style="width: 100%;" class="text-center">' + company.name + '</div>';

                        if (company.companySuperMessage) {
                            s += '<div style="width: 100%;" class="text-center">↓</div>';
                            getCompany(company.companySuperMessage);
                        }

                    }

                } else {
                    layer.alert('该单位无上级单位', {icon: 0, skin: 'layer-ext-moon'});
                }

            });
        },

        //获取用户可管理的单位
        queryUserCompany: function () {
            var This = this;
            $.ajax({
                'type': 'get',
                'url': '/company/findUC.shtml',
                dataType: 'json',
                success: function (jsonArray) {
                    This.companyJsonArray = jsonArray;
                    This.createTabCompany();
                },
                error: function () {
                    console.error('系统出现异常信息，无法查询单位');
                }
            });
        },

        //根据id获取单位
        getCompany: function (id) {
            var company = null,
                This = this;
            $.each(This.companyJsonArray, function (i) {
                if (This.companyJsonArray[i].id === id) {
                    company = This.companyJsonArray[i];
                    return false;
                }
            });
            return company;
        },

        //绑定原因事件
        remarkClick: function () {
            $('#tab-company-1').on('click', '.check-remark', function () {
                var remark = $(this).attr('data-val');
                layer.open({
                    type: 1,
                    skin: 'layui-layer-rim',
                    area: ['420px', '240px'],
                    content: '<div style="width: 100%;  padding: 20px;">' + remark + '</div>'
                });
            });
        },

        //绑定单位信息修改按钮事件
        updateClick: function () {

            var This = this;

            $('#tab-company-1').on('click', '.company-update', function () {

                if (loadNumber < LoadComplete) {
                    layer.alert('数据正在加载请稍后尝试修改', {icon: 0, skin: 'layer-ext-moon'});
                    return;
                }

                var companyId = $(this).attr('data-company-id');
                var company = This.getCompany(companyId);

                //查询单位证件
                cbl.queryCBL(companyId);

                $('#company-id').val(company.id);
                $('#company-name').val(company.name);
                $('#company-p-id').val(company.superCompanyName).attr('data-company-id', company.pId);
                companyType.setDefault(company.typeId);
                companyNature.setDefault(company.natureId);
                region.setDefault(company.regionId);
                $('#company-code').val(company.code);
                $('#company-phone').val(company.phone);

                This.showCompanyMessage();
            });
        },

        //返利列表按钮事件
        returnCompanyList: function () {
            var This = this;
            $('#return-company-transverter').click(function () {
                This.showCompanyList();
            });
        },

        //显示单位信息详细资料
        showCompanyMessage: function () {
            $('#row-1').fadeOut(500, function () {
                $('#row-2').fadeIn(500);
            });
        },

        //显示单位列表
        showCompanyList: function () {
            $('#row-2').fadeOut(500, function () {
                $('#row-1').show();
            });
        },

        //提交要修改的数据
        submitData: function () {

            var This = this;

            $('#submit-company').click(function () {

                var index = layer.confirm('单位信息修改之后会重新审核,是否修改？', {
                    btn: ['是', '否']
                }, function () {

                    layer.close(index);

                    var id = $('#company-id').val(),
                        pId = $('#company-p-id').attr('data-company-id'),
                        name = $('#company-name').val(),
                        typeId = $('#company-type').val(),
                        natureId = $('#company-nature').val(),
                        regionId = $('#company-region').val(),
                        code = $('#company-code').val(),
                        phone = $('#company-phone').val();

                    index = layer.msg('正在提交数据请稍后...', {icon: 16, shade: 0.01, time: 0});

                    $.ajax({
                        'type' : 'post',
                        'url' : '/company/update.shtml',
                        data : {
                            'id' : id,
                            'pId' : pId,
                            'name' : name,
                            'typeId' : typeId,
                            'natureId' : natureId,
                            'regionId' : regionId,
                            'code' : code,
                            'phone' : phone
                        },
                        success : function (data) {

                            layer.close(index);

                            if(data === 'success'){
                                This.queryUserCompany();
                                layer.alert('单位信息已修改,请耐心等待管理员审核', {icon: 1, skin: 'layer-ext-moon'});
                            }else{
                                layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                            }

                        },
                        error : function () {
                            layer.close(index);
                            layer.alert('系统出现异常信息,修改单位信息失败', {icon: 0, skin: 'layer-ext-moon'});

                        }
                    });

                }, function () {
                });

            });

        },

        //生成单位列表视图
        createTabCompany: function () {

            var s = '', This = this;

            $.each(This.companyJsonArray, function (i) {

                var checkRemark = '&nbsp;<a href="javascript:;" class="check-remark" data-val="' + This.companyJsonArray[i].checkRemark + '">原因?</a>',
                    check = '';

                if (This.companyJsonArray[i].check === 0) {
                    check = '等待审核';
                    checkRemark = '';
                }

                if (This.companyJsonArray[i].check === 1) {
                    check = '已审核';
                    checkRemark = '';
                }

                if (This.companyJsonArray[i].check === 2) {
                    check = '审核未通过';
                }

                s += '<tr>' +
                    '<td>' + (i + 1) + '</td>' +
                    '<td>' + This.companyJsonArray[i].name + '</td>' +
                    '<td>' + check + checkRemark + '</td>' +
                    '<td>' + This.companyJsonArray[i].regTime + '</td>' +
                    '<td><button type="button" class="btn btn-warning btn-xs company-update" data-company-id="' + This.companyJsonArray[i].id + '">修改</button></td>' +
                    '</tr>';
            });

            $('#tab-company-1').find('tbody').html(s);

        }

    }.init();

})(jQuery);