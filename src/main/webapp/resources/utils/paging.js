/**
 * Created by GSN on 2017/4/6.
 * jquery.js
 * layer.js
 * laypage.js
 */
;(function ($) {

    var defaults = {

        //加载
        layerLoadIndex: null,

        //页码
        page: 0,

        //获取页码请求连接
        jQueryAjaxUrlNumber: null,

        //获取数据请求连接
        jQueryAjaxUrlDate: null,

        //查询条件
        jQueryAjaxData: {
            length: 10
        },

        //返回数据回调函数
        callbackFindJson: null

    };

    var Paging = function (ele, opt) {
        this.defaults = defaults;
        this.element = ele;
        this.settings = $.extend({}, this.defaults, opt);
    };

    Paging.prototype = {

        //初始化
        init: function () {
            this.settings.page = 0;
            this.getNumber();
        },

        //获取页码
        getNumber: function () {
            var This = this;
            $.ajax({
                'type': 'post',
                'url': This.settings.jQueryAjaxUrlNumber,
                data: This.settings.jQueryAjaxData,
                success: function (page) {
                    This.settings.page = page;
                    This.loadLayerPage();
                },
                error : function () {
                    alert('页码查询出现异常');
                }
            });
        },

        //加载插件
        loadLayerPage: function () {
            var This = this;
            laypage({
                cont: This.element,
                pages: This.settings.page,
                //skip: true,
                //skin: '#AF0000',
                skin: 'yahei',
                groups: 10,
                jump: function (obj) {
                    This.settings.page = obj.curr - 1;
                    This.getDate();
                }
            });
        },

        //获取数据
        getDate: function () {
            var This = this,
                newJson = $.extend({}, {page: This.settings.page}, This.settings.jQueryAjaxData);
            This.settings.layerLoadIndex = layer.msg('正在加载中...', {icon: 16, shade: 0.01, time: 0});
            $.ajax({
                'type': 'post',
                'url': This.settings.jQueryAjaxUrlDate,
                dataType: 'json',
                data: newJson,
                success: function (jsonArray) {
                    setTimeout(function () {
                        layer.close(This.settings.layerLoadIndex);
                    }, 500);
                    This.settings.callbackFindJson(jsonArray, This.settings.page);
                },
                error : function () {
                    alert('数据查询出现异常');
                }
            });
        }

    };

    $.fn.paging = function (options) {
        new Paging(this, options).init();
    }

})(jQuery);
