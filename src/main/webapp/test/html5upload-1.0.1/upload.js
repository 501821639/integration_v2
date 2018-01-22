/**
 *  Created by GSN on 2017/9/8.
 *  IE10以下版本不兼容
 *  依赖组件 jquery1.12.4, bootstrap3.3.7
 */

;(function ($, w, d) {

    /**
     * 工具类
     * @type {{isJsonEmpty: isJsonEmpty, randomCreateString: randomCreateString}}
     */
    var Utils = {

        /**
         * 判断json数据是否为空
         * @param json
         * @returns {boolean}
         */
        isJsonEmpty: function (json) {
            for (var key in json) {
                return false;
            }
            return true;
        },

        /**
         * 随机生成六位大写字母加时间戳一组字符串
         * @returns {string}
         */
        randomCreateString: function () {
            var date = new Date(),
                chars = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'],
                res = '';
            for (var i = 0; i < 6; i++) {
                res += chars[Math.ceil(Math.random() * 25)];
            }
            return res + date.getTime();
        },

    };

    /**
     * 浏览器版本检测工具
     * @type {{init: init, searchString: searchString, searchVersion: searchVersion, dataBrowser: [*], dataOS: [*]}}
     */
    var BrowserDetect = {
        init: function () {
            this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
            this.version = this.searchVersion(navigator.userAgent)
                || this.searchVersion(navigator.appVersion)
                || "an unknown version";
            this.OS = this.searchString(this.dataOS) || "an unknown OS";
        },
        searchString: function (data) {
            for (var i = 0; i < data.length; i++) {
                var dataString = data[i].string;
                var dataProp = data[i].prop;
                this.versionSearchString = data[i].versionSearch || data[i].identity;
                if (dataString) {
                    if (dataString.indexOf(data[i].subString) != -1)
                        return data[i].identity;
                }
                else if (dataProp)
                    return data[i].identity;
            }
        },
        searchVersion: function (dataString) {
            var index = dataString.indexOf(this.versionSearchString);
            if (index == -1) return;
            return parseFloat(dataString.substring(index + this.versionSearchString.length + 1));
        },
        dataBrowser: [
            {
                string: navigator.userAgent,
                subString: "Chrome",
                identity: "Chrome"
            },
            {
                string: navigator.userAgent,
                subString: "OmniWeb",
                versionSearch: "OmniWeb/",
                identity: "OmniWeb"
            },
            {
                string: navigator.vendor,
                subString: "Apple",
                identity: "Safari",
                versionSearch: "Version"
            },
            {
                prop: window.opera,
                identity: "Opera"
            },
            {
                string: navigator.vendor,
                subString: "iCab",
                identity: "iCab"
            },
            {
                string: navigator.vendor,
                subString: "KDE",
                identity: "Konqueror"
            },
            {
                string: navigator.userAgent,
                subString: "Firefox",
                identity: "Firefox"
            },
            {
                string: navigator.vendor,
                subString: "Camino",
                identity: "Camino"
            },
            {		// for newer Netscapes (6+)
                string: navigator.userAgent,
                subString: "Netscape",
                identity: "Netscape"
            },
            {
                string: navigator.userAgent,
                subString: "MSIE",
                identity: "Internet Explorer",
                versionSearch: "MSIE"
            },
            {
                string: navigator.userAgent,
                subString: "Gecko",
                identity: "Mozilla",
                versionSearch: "rv"
            },
            {		 // for older Netscapes (4-)
                string: navigator.userAgent,
                subString: "Mozilla",
                identity: "Netscape",
                versionSearch: "Mozilla"
            }
        ],
        dataOS: [
            {
                string: navigator.platform,
                subString: "Win",
                identity: "Windows"
            },
            {
                string: navigator.platform,
                subString: "Mac",
                identity: "Mac"
            },
            {
                string: navigator.userAgent,
                subString: "iPhone",
                identity: "iPhone/iPod"
            },
            {
                string: navigator.platform,
                subString: "Linux",
                identity: "Linux"
            }
        ]

    };


    /**
     * 上传插件
     * @param ele
     * @param opt
     * @constructor
     */
    var Upload = function (ele, opt) {

        //初始化配置信息
        this.defaults = {

            title: 'title',

            //上传文件地址
            url: '',

            //文件上传类型
            type: ['image/jpg', 'image/jpeg', 'image/png'],

            //上传文件传值
            data: null,

            //附加自定义数据
            customData: {

                //后台地址
                url: null,

                //ajax发送数据key键标识
                key: 'id',

                //jquery取值方式默认为val(), 还有html()
                get: 'val',

                //jquery创建的元素集合
                create: [],


            },

        };

        //jQuery选择的元素
        this.$element = ele;

        //默认配置文件继承用户配置文件
        this.settings = $.extend({}, this.defaults, opt);

    };

    Upload.prototype = {

        //bootstrap弹出层id
        modalId: null,

        //存放生成的file文件上传元素
        divFile: null,

        //选择文件id
        selectFileId: null,

        init: function () {

            this.verification();
            this.insertElement();
            this.thisEventBinding();
            this.selectButtonEventBinding();
            this.startUploadEventBinding();
            this.imagesEventClick();
            this.deleteEventBinding();
        },

        verification: function () {

            if ($('body').length <= 0) {
                throw '页面检测不到body标签,无法进行插入数据';
            }

            //初始化
            BrowserDetect.init();

            //浏览器与版本检测
            if (BrowserDetect.browser === 'Internet Explorer') {
                if (BrowserDetect.version < 10) {
                    alert('请使用IE10及以上版本浏览器！否则upload上传插件无法运行(当前版本: ' + BrowserDetect.browser + ' ' + BrowserDetect.version + ' )');
                    throw '浏览器不兼容upload插件';
                }
            }
        },

        /**
         * 默认插入页面中的一些元素
         */
        insertElement: function () {

            this.divFile = Utils.randomCreateString();

            $('body').prepend('<div id="' + this.divFile + '" style="display: none;"></div>');

            this.modalId = Utils.randomCreateString();

            this.selectFileId = Utils.randomCreateString();

            var str = '<div class="modal fade bs-example-modal-lg" id="' + this.modalId + '" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">' +
                '<div class="modal-dialog modal-lg" role="document">' +
                '<div class="modal-content">' +
                '<div class="modal-header">' +
                '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
                '<h4 class="modal-title" id="myModalLabel">' + this.settings.title + '</h4>' +
                '</div>' +
                '<div class="modal-body">' +
                '<div class="alert alert-success" role="alert" style="display: none;"></div>' +
                '<div class="container-fluid">' +
                '<div class="row images">' +
                '<div class="col-xs-3"><img id="' + this.selectFileId + '" style="cursor: pointer; margin-top: 10px; width: 200px; height: 200px;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAARgAAAEECAMAAADERNteAAAABGdBTUEAALGPC/xhBQAAAAFzUkdCAK7OHOkAAABXUExUReXl5WhoaFNTU8XFxXd3d+Xl5Y2NjXd3d4SEhNjY2F9fX05OToCAgK6urnBwcHh4eE9PT1xcXJubm2JiYoKCgktLS0dHR2xsbFdXV46OjlVVVUxMTERERNpr+JcAAAAcdFJOUzOZzEAtLWYzMzOZzGZNjYDZslmlc+XygL9ZstlYIOCXAAAC/klEQVR42u3Z2VabUAAFUAZ7rcMlTLG25f+/s3AJSl3mqV2GhH0e8Dq8uBecHGN2P+d7lmUPzm/nuwzGGRj5JA9gzsOkm0c+5hsYMGDAKF8wYAw8MGDAKF8x8MCAAaN8wYAx8MCAASPK18ADAwaM8gUDxsADA0bAKF8DDwwYMMoXDBgDD4yAAaN8DTwwYMAoXzBgDDwwAgaM8jXwwIABo3zBgDHwBAwYMMrXwAMDBozyBQNGDDwwYMAoXwMPDBgwyheMgDHwwNwwzPPjDzCf5XF4Ur7bhdngwAMDBsw/5ufzlF/D7/TxWfkueRr+ChgwV7ZjDDwwYMAoX8t3G287+C8BGDC3Wr5gwFwrjIABA0b5GnhgwIBRvv8nh7j6pAzjJfZnf7hN13wXMEVYK3XjpaqWb60zfyVdh/3B1PGQ50WTj4kngJPScCmYi2UNE5pshHnJVzBVPZ4Pe4c5Tuf2dfl0BDgcQwhNvmAck9kwXcLNw6QHJ/VpN4QwPklF+kK6S6oiFGVfvN0ldXhLvHmYKv2e46kviglmfpJmmPFVqH85lh86Zh8v1++PUldO59hk749SW9VNPBRNfxmYiw68dcdM5y4Pyy00lHlfduOt0+Zlggkv69funcFU6UHq6uXxKfM6LOfQzGj1HmHmBVy0M0boY1bGBWYqnq99ojYE0x/Kk8uE0VavRdcuMHX8WpjNLN/pPD46Xd2uh1y/7JiwgNw4TEhtcpx3TBpsJ6TqOP8dMOTvGdKK2QdMDOvE1d1TNl2CWX17hIlVdgGYjaXczPsxAgbMdb9cbzveDAcDBozyBQPGwAMDBozyFQMPDBgwyhcMGAMPDBgwonwNPDBgwChfMGAMPDBgBIzyNfDAgAGjfMGAMfDACBgwytfAAwMGjPIFA8bAAyNgwChfAw8MGDDKFwwYA0/AgAGjfA08MGDAKF8wYMTAAwMGjPI18MCAAaN8wQgYAw8MGDDK18ADAwaM8gUDBoyBBwYMGOVr4F0zzH2iebif4zznDswZmD/pBESeAxLzIQAAAABJRU5ErkJggg==" class="img-responsive center-block img-thumbnail"></div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="modal-footer">' +
                '<button type="button" class="btn btn-danger delete-file" style="display: none;">删除选中图片</button>' +
                '<button type="button" class="btn btn-default upload-file">保存</button>' +
                '<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';

            $('body').prepend(str);

            var eleArray = this.settings.customData.create;

            for (var i = 0, length = eleArray.length; i < length; i++) {

                var row = $('<div>').attr({
                    'class': 'row'
                }).css({
                    'margin-top': '20px'
                });

                var col = $('<div>').attr({
                    'class': 'col-xs-12'
                });

                eleArray[i].appendTo(col);

                col.appendTo(row);

                row.appendTo($('#' + this.modalId).find('.container-fluid'));

            }

        },


        //绑定删除选中文件事件
        deleteEventBinding: function () {

            var $modal = $('#' + this.modalId);

            $modal.find('.delete-file').click(function () {

                $modal.find('.upload-img[data-delete=1]').each(function () {
                    $(this).parent().remove();
                    $('#' + $(this).attr('data-id')).remove();
                });

                $modal.find('.delete-file').hide();

            });

        },

        //绑定保存按钮事件
        startUploadEventBinding: function () {

            var This = this,
                modalId = '#' + this.modalId;

            $(modalId).find('.upload-file').click(function () {

                var $files = $('#' + This.divFile).find('.file');

                if ($files.length === 0) {
                    $('#' + This.modalId).find('.alert').show().html(' 请选择一张图片');
                    return;
                }

                var $btn = $(this).button('loading'),
                    divStr = '',
                    files = [],
                    filesIndex = 0;

                $files.each(function () {

                    var file = $(this).get(0).files[0];

                    if (file) {

                        divStr += '<div class="' + $(this).attr('id') + '">' +
                            '<span class="file-name">' + file.name + '</span>' +
                            '<span class="state">  等待上传</span>' +
                            '</div>';

                        files.push({
                            'id': $(this).attr('id'),
                            'file': file
                        });
                    } else {
                        //删除创建file上传元素没有选择文件的file元素
                        $(this).remove();
                    }
                });

                //插入准备上传文件视图
                $('#' + This.modalId).find('.alert').show().html(divStr);

                recursion();

                //递归上传文件
                function recursion() {

                    if (filesIndex < files.length) {

                        var file = files[filesIndex].file,
                            form = new FormData(),
                            xhr = new XMLHttpRequest(),
                            fileId = files[filesIndex].id;

                        form.append("author", "hooyes");
                        form.append("file", file);

                        //上传完成执行的函数
                        xhr.onload = function () {

                            //服务器返回结果
                            var result = this.responseText;

                            if (result === 'success') {
                                $('#' + fileId).remove();
                                $('#' + This.modalId).find('.alert').find('.' + fileId).find('span.state').append('  上传完成');
                            } else {
                                $('#' + This.modalId).find('.alert').find('.' + fileId).find('span.state').append('  ' + result);
                            }

                            filesIndex++;
                            recursion();

                        };

                        /**
                         *  上传进度执行的函数
                         *  e.loaded 当前
                         *  e.total 总计
                         */
                        xhr.upload.addEventListener("progress", function (e) {
                            $('#' + This.modalId).find('.alert').find('.' + fileId).find('span.state').html('  (' + e.loaded + ' / ' + e.total + ')');
                        }, false);

                        //插件中配置传值参数
                        if (This.settings.data !== null && !Utils.isJsonEmpty(This.settings.data)) {
                            var json = This.settings.data;
                            for (var key in json) {
                                form.append(key, json[key]);
                            }
                        }

                        xhr.open("post", This.settings.url, true);
                        xhr.setRequestHeader('X-Request-With', 'XMLHttpRequest');
                        xhr.send(form);

                    } else {

                        var eleArray = This.settings.customData.create,
                            ajaxData = {},
                            key = This.settings.customData.key;

                        for (var i = 0, length = eleArray.length; i < length; i++) {
                            ajaxData[eleArray[i].attr(key)] = $('#' + eleArray[i].attr('id')).val();
                        }

                        $.ajax({
                            'type': 'post',
                            'url': This.settings.customData.url,
                            data: ajaxData,
                            success: function (data) {
                                $('#' + This.modalId).find('.alert').append('<div class="zdy" style="margin-top: 10px;"><span class="file-name">URL : ' + This.settings.customData.url + '</span><span class="state"> 数据已发送</span></div>');
                            },
                            error: function () {
                                $('#' + This.modalId).find('.alert').append('<div class="zdy" style="margin-top: 10px;"><span class="file-name">URL : ' + This.settings.customData.url + '</span><span class="state"> 数据发送失败,请查看控制台日志</span></div>');
                            },
                            complete: function () {
                                $btn.button('reset');
                            }
                        });

                    }

                };

            });

        },

        /**
         * 绑定弹出层选择文件按钮事件
         */
        selectButtonEventBinding: function () {

            var This = this,
                fileTypeArray = this.settings.type,
                uploadFileId = null;

            $('#' + This.divFile).on('change', '.file', function () {

                var file = d.getElementById($(this).attr("id")).files[0];

                $('<img>').attr({
                    'src': w.URL.createObjectURL(file),
                    'class': 'img-responsive center-block img-thumbnail upload-img',
                    'data-id': uploadFileId
                }).css({
                    width: 200,
                    height: '200',
                    cursor: 'pointer'
                }).appendTo($('<div>').attr({
                    'class': 'col-xs-3'
                }).css({
                    'margin-top': '10px'
                }).appendTo($('#' + This.modalId).find('.container-fluid .images')));
            });

            $('#' + this.selectFileId).click(function () {

                uploadFileId = Utils.randomCreateString();
                var fileStr = '<input type="file" name="file" class="file" id="' + uploadFileId + '" accept="' + fileTypeArray + '"/>';

                $('#' + This.divFile).append(fileStr);
                $('#' + uploadFileId).click();

            });

        },

        /**
         * 绑定图片点击事件
         */
        imagesEventClick: function () {

            var $modal = $('#' + this.modalId);

            $modal.find('.container-fluid').on('click', '.upload-img', function () {
                if ($(this).attr('data-delete') === '1') {
                    $(this).css({
                        'border': '1px solid #ddd'
                    }).attr('data-delete', 0);

                    if ($modal.find('.upload-img[data-delete=1]').length === 0) {
                        $modal.find('.delete-file').hide();
                    }

                } else {
                    $(this).css({
                        'border': '3px solid red'
                    }).attr('data-delete', 1);

                    $modal.find('.delete-file').show();

                }
            });

        },

        /**
         * 绑定jquery元素点击事件
         */
        thisEventBinding: function () {
            var This = this;
            this.$element.click(function () {
                $('#' + This.modalId).modal('show');
            });
        }

    };

    $.fn.html5upload = function (options) {
        new Upload(this, options).init();
    };


})(jQuery, window, document);