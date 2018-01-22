/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
		
		config.filebrowserBrowseUrl = '/resources/plugin/ckfinder/ckfinder.html';
		config.filebrowserImageBrowseUrl = '/resources/plugin/ckfinder/ckfinder.html?type=Images';
		config.filebrowserFlashBrowseUrl = '/resources/plugin/ckfinder/ckfinder.html?type=Flash';
		config.filebrowserUploadUrl = '/resources/plugin/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
		config.filebrowserImageUploadUrl = '/resources/plugin/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
		config.filebrowserFlashUploadUrl = '/resources/plugin/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
		config.filebrowserWindowWidth = '1000';
		config.filebrowserWindowHeight = '700';
		config.language = "zh-cn";
		config.enterMode = CKEDITOR.ENTER_BR;
		config.image_previewText='';
		//config.extraPlugins = "articlepage,jwplayer";
	};


