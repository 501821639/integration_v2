/**
 * Created by GSN on 2017/5/10.
 * 暂无描述
 */

(function ($, w) {

    //加载layer其他弹出框样式
    layer.config({extend: 'moon/style.css'});

    $('#gh-portrait').upload({

        url: '/user/portrait.shtml',

        // progress: function (loaded, total) {
        //
        // },

        success: function (data) {
            if(data === 'success'){
                layer.alert('已完成头像设置,下次登录更新头像', {icon: 1, skin: 'layer-ext-moon'});
            }else{
                layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
            }
        }

    });

    var portrait = {

        init: function () {
            this.setUserPortrait();
            this.getHistory();
            return this;
        },

        getHistory: function () {

            $.ajax({
                'type': 'get',
                'url': '/user/history-portrait.shtml',
                dataType: 'json',
                success: function (array) {
                    var str = '',
                        row = '<div class="row" style="margin-top: 5px;">';
                    for (var i = 0; i < array.length; i++) {
                        if(i !== 0 && i % 6 === 0){
                            str += row + '</div>';
                            row = '<div class="row" style="margin-top: 5px;">';
                        }
                        row += '<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">' +
                            '<img src="'+ array[i] +'" class="img-responsive">' +
                            '</div>';
                    }
                    if(array.length % 6 !== 0){
                        str += row + '</div>';
                    }
                    $('#history-portrait').append(str);
                }
            });

        },

        //设置用户头像
        setUserPortrait: function () {
            $('#usre-portrait').attr('src', w.userAllMessage.portrait);
        }

    }.init();

})(jQuery, window);