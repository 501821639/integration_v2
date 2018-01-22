CKEDITOR.dialog.add('articlepage', function(editor) {
	return {
		title : '添加分页',
		minWidth : 350,
		minHeight : 160,
		contents : [{
			id : 'info',
			label : '',
			title : '',
			expand : true,
			padding : 0,
			elements : [{
				type: 'textarea',
				required: true,
				style: 'width:350px;height:160px',
				rows: 6,
				id: 'mytxt',
				'default': '点击确定即可添加分页 ,分页点根据光标位置进行分页     注：关键字"当前文章分页段落"是分页标准，请勿手动添加！'
			}]
		}],
		buttons : [CKEDITOR.dialog.okButton, CKEDITOR.dialog.cancelButton],
		onOk : function() {
			//editor.setData(editor.getData()+'当前文章分页段落')
			CKEDITOR.instances.content.insertHtml("当前文章分页段落");
		}
	}
});