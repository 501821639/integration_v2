(function() {
	CKEDITOR.plugins.add("jwplayer", {
		requires : [ "dialog" ],
		init : function(a) {
			a.addCommand("jwplayer", new CKEDITOR.dialogCommand("jwplayer"));
			a.ui.addButton("jwplayer", {
				label : "上传一个视频",// 调用dialog时显示的名称
				command : "jwplayer",
				icon : this.path + "tb3.jpg"// 在toolbar中的图标
			});
			CKEDITOR.dialog.add("jwplayer", this.path + "dialogs/jwplayer.js");
		}
	});
})();
