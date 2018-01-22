;(function( $ , d , w ){
	
	
	var OtherTools = function(){
		
	};
	
	OtherTools.prototype = {};
	
	//获取页面之间传值
	OtherTools.getHerfVal = function(){
		var json = {};
		var strHref = window.document.location.href;
		var intPos = strHref.indexOf('?'); 
		var strRight = strHref.substr(intPos + 1);
		var arrTmp = strRight.split('&'); 
		for(var i = 0; i < arrTmp.length; i++){
			var val = arrTmp[i].split('=');
			json[val[0]] = val[1];
		} 
		return json;
	}
	
	//获取操作系统
	OtherTools.detectOS = function() {
	    var sUserAgent = navigator.userAgent;
	    var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
	    var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh") || (navigator.platform == "MacIntel");
	    if (isMac) return "Mac";
	    var isUnix = (navigator.platform == "X11") && !isWin && !isMac;
	    if (isUnix) return "Unix";
	    var isLinux = (String(navigator.platform).indexOf("Linux") > -1);
	    if (isLinux) return "Linux";
	    if (isWin) {
	        var isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;
	        if (isWin2K) return "Win2000";
	        var isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 || sUserAgent.indexOf("Windows XP") > -1;
	        if (isWinXP) return "WinXP";
	        var isWin2003 = sUserAgent.indexOf("Windows NT 5.2") > -1 || sUserAgent.indexOf("Windows 2003") > -1;
	        if (isWin2003) return "Win2003";
	        var isWinVista= sUserAgent.indexOf("Windows NT 6.0") > -1 || sUserAgent.indexOf("Windows Vista") > -1;
	        if (isWinVista) return "WinVista";
	        var isWin7 = sUserAgent.indexOf("Windows NT 6.1") > -1 || sUserAgent.indexOf("Windows 7") > -1;
	        if (isWin7) return "Win7";
	    }
	    return "other";
	}
	
	//浏览器信息
	OtherTools.browser = function(){
		
		var browser = {appname: 'unknown', version: 0},  
	     	userAgent = window.navigator.userAgent.toLowerCase();  
		
		//IE,firefox,opera,chrome,netscape  
	    if ( /(msie|firefox|opera|chrome|netscape)\D+(\d[\d.]*)/.test( userAgent ) ){  
	        browser.appname = RegExp.$1;  
	        browser.version = RegExp.$2;  
	    } else if ( /version\D+(\d[\d.]*).*safari/.test( userAgent ) ){ // safari  
	        browser.appname = 'safari';  
	        browser.version = RegExp.$2;  
	    }
	    
	    return browser.appname + ' ' + browser.version; 
		
	}
	
	w.ot = OtherTools;
	
})( jQuery , document , window );