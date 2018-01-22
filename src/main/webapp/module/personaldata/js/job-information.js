/**
 * Created by GSN on 2017/5/18.
 * job-information.jsp
 */

(function ($, w) {

    //加载layer其他弹出框样式
    layer.config({extend: 'moon/style.css'});

    var companys = w.userAllMessage.companyBriefnessMessageList;

    if (companys.length === 0) {
        layer.alert('该账号无所属单位', {icon: 0, skin: 'layer-ext-moon'});
        return;
    }

    var s = '';

    $.each(companys, function (i) {

        var auditStatus = '';

        if (companys[i].check === 0) {
            auditStatus = '等待审核';
        } else if (companys[i].check === 1) {
            auditStatus = '审核通过';
        } else if (companys[i].check === 2) {
            auditStatus = '审核未通过';
        }

        s += '<div class="row animated fadeInLeft company" style="display: none;">' +
            '<div class="col-sm-12">' +
            '<div class="panel">' +
            '<div class="panel-content">' +
            '<div class="row">' +
            '<div class="col-md-12">' +
            '<form class="form-horizontal form-stripe">' +
            '<div class="form-group">' +
            '<label for="company-name-0" class="col-sm-2 control-label">单位名称：</label>' +
            '<div class="col-sm-10">' +
            '<input type="text" class="form-control" id="company-name-0" value="' + companys[i].name + '">' +
            '</div>' +
            '</div>' +
            '<div class="form-group">' +
            '<label for="company-name-0" class="col-sm-2 control-label">上级单位：</label>' +
            '<div class="col-sm-10">' +
            '<input type="text" class="form-control" id="company-name-0" value="' + companys[i].superName + '">' +
            '</div>' +
            '</div>' +
            '<div class="form-group">' +
            '<label for="company-type-name-0" class="col-sm-2 control-label">类型：</label>' +
            '<div class="col-sm-10">' +
            '<input type="text" class="form-control" id="company-type-name-0" value="' + companys[i].typeName + '">' +
            '</div>' +
            '</div>' +
            '<div class="form-group">' +
            '<label for="company-nature-name-0" class="col-sm-2 control-label">性质：</label>' +
            '<div class="col-sm-10">' +
            '<input type="text" class="form-control" id="company-nature-name-0" value="' + companys[i].natureName + '">' +
            '</div>' +
            '</div>' +
            '<div class="form-group">' +
            '<label for="company-region-name-0" class="col-sm-2 control-label">地区：</label>' +
            '<div class="col-sm-10">' +
            '<input type="text" class="form-control" id="company-region-name-0" value="' + companys[i].regionName + '">' +
            '</div>' +
            '</div>' +
            '<div class="form-group">' +
            '<label for="company-check-name-0" class="col-sm-2 control-label">审核状态：</label>' +
            '<div class="col-sm-10">' +
            '<input type="text" class="form-control" id="company-check-name-0" value="' + auditStatus + '">' +
            '</div>' +
            '</div>' +
            '<div class="form-group">' +
            '<label for="company-register-time-name-0" class="col-sm-2 control-label">注册时间：</label>' +
            '<div class="col-sm-10">' +
            '<input type="text" class="form-control" id="company-register-time-name-0" value="' + companys[i].regTime + '">' +
            '</div>' +
            '</div>' +
            '</form>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';

    });

    $('#model-1').append(s);
    $('.company').show();

})(jQuery, window);