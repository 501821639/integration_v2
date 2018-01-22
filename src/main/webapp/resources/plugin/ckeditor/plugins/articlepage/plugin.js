(function() {
	CKEDITOR.plugins.add("articlepage", {
		requires : [ "dialog" ],
		init : function(a) {
			a.addCommand("articlepage", new CKEDITOR.dialogCommand("articlepage"));
			a.ui.addButton("articlepage", {
				label : "添加一段分页",// 调用dialog时显示的名称
				command : "articlepage",
				icon : this.path + "tb2.jpg"// 在toolbar中的图标
			});
			CKEDITOR.dialog.add("articlepage", this.path + "dialogs/articlepage.js");
		}
	});
})();
