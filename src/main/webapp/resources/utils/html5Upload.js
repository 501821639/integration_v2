/**
 * Created by GSN on 2017/5/10.
 * html5上传插件
 * jQuery1.8版本以上
 */

;(function ($, w, d) {

    var utils = {

        //json是否为空
        isJsonEmpty : function (json) {
            for(var key in json){
                return false;
            }
            return true;
        }

    };

    var Upload = function (ele, opt) {

        //初始化配置信息
        this.defaults = {

            //上传地址
            url: '',

            //传值开启jquery取值
            jquerySelect : false,

            //传值
            data : null,

            //上传开始回调函数
            start : null,

            //上传成功回调函数
            success: null,

            //上传进度回调函数
            progress: null

        };

        //jQuery选择的元素
        this.$element = ele;

        //默认配置文件继承用户配置文件
        this.settings = $.extend({}, this.defaults, opt);

    };

    Upload.prototype = {

        init: function () {

            var $fileBut = File.create(),
                This = this;

            this.$element.click(function () {
                $fileBut.click();
            });

            $fileBut.change(function () {

                if (This.settings.start !== null) {
                    This.settings.start();
                }

                var file = $(this).get(0).files[0],
                    form = new FormData(),
                    xhr = new XMLHttpRequest();

                form.append("author", "hooyes");
                form.append("file", file);

                //上传完成
                xhr.onload = function () {

                    //防止上传同一个文件名事件不生效
                    $fileBut.val('');

                    //服务器返回结果
                    var result = this.responseText;

                    //如果配置上传成功回调函数进行调用
                    if (This.settings.success !== null) {
                        This.settings.success(result);
                    }

                };

                //上传进度
                xhr.upload.addEventListener("progress", function (e) {

                    /**
                     * 如果配置上传进度回调函数进行调用
                     *  e.loaded 当前
                     *  e.total 总计
                     */
                    if (This.settings.progress !== null) {
                        This.settings.progress(e.loaded, e.total);
                    }

                }, false);

                if(This.settings.data !== null && !utils.isJsonEmpty(This.settings.data)){

                    var json = This.settings.data,
                        arr = [];

                    if(This.settings.jquerySelect) {
                        for (var key in json) {
                            arr.push(key + '=' + $(json[key]).val());
                        }
                    }else{
                        for (var key in json) {
                            arr.push(key + '=' + json[key]);
                        }
                    }

                    for(var i = 0; i < arr.length; i++){
                        if(i === 0){
                            This.settings.url += '?' + arr[i];
                        }else{
                            This.settings.url += '&' + arr[i];
                        }
                    }

                }

                xhr.open("post", This.settings.url, true);
                xhr.setRequestHeader('X-Request-With', 'XMLHttpRequest');
                xhr.send(form);

            });

        }

    };

    var upload;

    $.fn.upload = function (options) {
        upload = new Upload(this, options);
        upload.init();
    };

    var File = {

        //按钮id
        buttonId: null,

        //创建上传按钮
        create: function () {

            if (this.buttonId !== null) {
                this.remove();
            }

            var id = 'file-' + new Date().getTime();

            $('<input/>', {
                'id': id,
                'type': 'file',
                'name': 'file'
            }).css({
                'display': 'none'
            }).insertAfter(upload.$element);

            this.buttonId = id;

            return $('#' + this.buttonId);

        },

        //删除上传按钮
        remove: function () {

            var $fileButton = $('#' + this.buttonId);

            if ($fileButton.length) {
                $fileButton.remove();
            }
        }

    };

})(jQuery, window, document);
