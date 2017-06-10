/**
*获取请求参数的jquery前缀
*/
$.extend({
	getParam:function(key){
		var url = location.href;//获得当前url地址

		var index = url.indexOf('?');

		var str = url.substring(index + 1);//截取问号后面的内容

		var arr = str.split('&');//按照&分解字符串组

		for (var i = 0; i < arr.length; i++) {
			// 根据=分解字符串
			var param = arr[i].split('=');

			// 如果传入的参数名和当前名称相等，返回参数值
			if(param[0] == key){
				return param[1];
			}
		};
	}
});