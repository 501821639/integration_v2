/**
 * Created by GSN on 2017/5/12.
 * 暂无描述
 */

(function ($) {

    //加载layer其他弹出框样式
    layer.config({extend: 'moon/style.css'});

    var treeEvent = {

        node: null,

        onClick: function (event, treeId, treeNode) {

            if(region.isAU === null || region.isAU === 'add') {
                $('#region-p-id').val(treeNode.id).attr('title', treeNode.name);
                treeEvent.node = treeNode;
            }
        }

    };

    var tree = {

        zNodes: [],

        setting: {
            data: {
                key: {
                    title: 'postcode'
                },
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: treeEvent.onClick
            }
        },

        loadTree: function () {
            $.fn.zTree.init($("#tree"), this.setting, this.zNodes);
        }

    };

    var region = {

        /**
         * update : 修改
         * add : 删除
         */
        isAU : null,

        init: function () {

            $('#tree-show').show();

            this.getRegion();
            this.add();
            this.update();
            this.delete();
            this.close();
            return this;
        },

        getRegion: function () {

            $.ajax({
                'type': 'get',
                'url': '/region/find.shtml',
                dataType: 'json',
                success: function (jsonArray) {

                    treeEvent.node = null;
                    $('#region-p-id').val('').removeAttr('title');

                    if (jsonArray.length === 0) {
                        layer.alert('暂无数据', {icon: 0, skin: 'layer-ext-moon'});
                    } else {
                        tree.zNodes = jsonArray;
                        tree.loadTree();
                    }
                }
            });

        },

        add: function () {

            var This = this;

            $('#region-add').click(function () {

                $('#tree-show').show(1000);

                This.isAU = 'add';

                $('#region-name').val('');
                $('#region-postcode').val('');

                var $rsu = $('#region-save-update');

                $rsu.hide();

                $('#region-btn-update').hide();
                $('#region-btn-save').show();

                $rsu.show(1000);
            });

            $('#region-btn-save').click(function () {

                var name = $('#region-name').val(),
                    pId = $('#region-p-id').val(),
                    postcode = $('#region-postcode').val();

                var $btn = $(this).button('loading');

                $.ajax({
                    'type': 'post',
                    'url': '/region/save.shtml',
                    data: {
                        'name': name,
                        'pId': pId,
                        'postcode': postcode
                    },
                    success: function (data) {

                        $btn.button('reset');

                        if (data === 'success') {
                            layer.alert('节点已添加', {icon: 1, skin: 'layer-ext-moon'});
                            region.getRegion();
                        } else {
                            layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                        }
                    },
                    error : function () {
                        $btn.button('reset');
                        layer.alert('添加失败系统错误', {icon: 0, skin: 'layer-ext-moon'});
                    }
                });

            });

        },

        update: function () {

            var This = this;

            $('#region-update').click(function () {

                if(treeEvent.node === null ){
                    layer.alert('请点击要修改的节点', {icon: 0, skin: 'layer-ext-moon'});
                    return;
                }

                This.isAU = 'update';

                $('#tree-show').hide(1000);

                var $rsu = $('#region-save-update');

                $rsu.hide();

                $('#region-btn-save').hide();
                $('#region-btn-update').show();

                $('#region-id').val(treeEvent.node.id);
                $('#region-name').val(treeEvent.node.name);
                $('#region-p-id').val(treeEvent.node.pId ? treeEvent.node.pId : 0);
                $('#region-postcode').val(treeEvent.node.postcode);

                $rsu.show(1000);

            });

            $('#region-btn-update').click(function () {

                var id = treeEvent.node.id,
                    name = $('#region-name').val(),
                    pId = $('#region-p-id').val(),
                    postcode = $('#region-postcode').val();

                var $btn = $(this).button('loading');

                $.ajax({
                    'type': 'post',
                    'url': '/region/update.shtml',
                    data: {
                        'id' : id,
                        'name': name,
                        'pId': pId,
                        'postcode': postcode
                    },
                    success: function (data) {

                        $btn.button('reset');

                        if (data === 'success') {
                            $('#region-btn-close').click();
                            layer.alert('节点已修改', {icon: 1, skin: 'layer-ext-moon'});
                            region.getRegion();
                        } else {
                            layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                        }
                    },
                    error : function () {
                        $btn.button('reset');
                        layer.alert('修改失败系统错误', {icon: 0, skin: 'layer-ext-moon'});
                    }
                });

            });

        },

        delete: function () {

            $('#region-delete').click(function () {

                if(treeEvent.node === null ){
                    layer.alert('请点击要删除的节点', {icon: 0, skin: 'layer-ext-moon'});
                    return;
                }

                var index = layer.confirm('确定删除(' + treeEvent.node.name + ') 这个节点？', {
                    btn: ['确定', '取消']
                }, function () {

                    layer.close(index);
                    index = layer.msg('正在删除节点请稍后...', {icon: 16, shade: 0.01, time: 0});

                    $.ajax({
                        'type': 'post',
                        'url': '/region/delete.shtml',
                        data: {
                            'id' : treeEvent.node.id
                        },
                        success: function (data) {

                            layer.close(index);

                            if (data === 'success') {
                                layer.alert('节点已删除', {icon: 1, skin: 'layer-ext-moon'});
                                region.getRegion();
                            } else {
                                layer.alert(data, {icon: 0, skin: 'layer-ext-moon'});
                            }
                        },
                        error : function () {
                            layer.close(index);
                            layer.alert('删除失败系统错误', {icon: 0, skin: 'layer-ext-moon'});
                        }
                    });

                }, function () {
                });

            });

        },

        close: function () {

            $('#region-btn-close').click(function () {
                region.isAU = null;
                $('#tree-show').show(1000);
                $('#region-save-update').hide(1000);
            });

            $('#region-p-id-empty').click(function () {
                $('#region-p-id').val('').removeAttr('title');
            });

        }

    }.init();

})(jQuery);
