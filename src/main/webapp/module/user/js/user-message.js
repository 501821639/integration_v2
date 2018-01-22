/**
 * Created by GSN on 2017/5/2.
 * 用户信息
 */

(function ($, w) {

    var getHerfVal = function () {
        var json = {};
        var strHref = window.document.location.href;
        var intPos = strHref.indexOf('?');
        var strRight = strHref.substr(intPos + 1);
        var arrTmp = strRight.split('&');
        for (var i = 0; i < arrTmp.length; i++) {
            var val = arrTmp[i].split('=');
            json[val[0]] = val[1];
        }
        return json;
    };

    //控制每个用户显示的左侧导航菜单
    var nav = {

        setMenu: function (arrayPermission) {

            var permissionId = getHerfVal().open;

            var nodes = childNodes('0');

            var node1String = '';

            for (var i = 0; i < nodes.length; i++) {

                var openItem1 = 'close-item';

                var node2String = '<ul class="nav child-nav level-1">';
                for (var j = 0; j < nodes[i].child.length; j++) {
                    var activeItem = '';
                    if(permissionId === nodes[i].child[j].data.id){
                        openItem1 = 'open-item';
                        activeItem = 'active-item';
                    }
                    node2String += '<li class="'+ activeItem +'"><a href="' + nodes[i].child[j].data.control + '?open='+ nodes[i].child[j].data.id +'">' + nodes[i].child[j].data.name + '</a></li>';
                }
                node2String += '</ul>';

                node1String += '<li class="has-child-item '+ openItem1 +'">' +
                    '<a><i class="fa '+ nodes[i].data.icon +'" aria-hidden="true"></i><span>' + nodes[i].data.name + '</span></a>'+ node2String + '</li>';
            }

            $('#main-nav').html(node1String);

            function childNodes(id) {
                var arr = [];
                for (var i = 0; i < arrayPermission.length; i++) {
                    if (arrayPermission[i].pId === id) {
                        var json = {};
                        json.data = arrayPermission[i];
                        json.child = childNodes(arrayPermission[i].id);
                        arr.push(json);
                    }
                }
                return arr;
            }

        }

    };

    var user = {

        data : null,

        init : function () {
            this.getUserData();
            nav.setMenu(this.data.permissionList);
            this.setUserPortrait();
            return this;
        },

        getUserData : function () {

            var This = this;

            $.ajax({
                'type': 'get',
                'url': '/user/allMessage.shtml',
                dataType: 'json',
                async : false,
                success: function (userJson) {
                    w.userAllMessage = This.data = userJson;
                }
            })

        },

        //设置用户头像
        setUserPortrait : function () {
            $('#user-headerbox').find('.user-photo img').attr('src', this.data.portrait);
        }

    }.init();


})(jQuery, window);