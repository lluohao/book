var base = "http://192.168.191.1/code"
var hy = {
	get: function(name) {
		return plus.navigator.getCookie(name);
	},
	put: function(name, value) {
		return plus.navigator.setCookie(name, value);
	}
}
$(document).ready(function() {
	$.ajax({
		type: "get",
		url: "http://119.29.175.13/index.jsp",
		async: false,
		success: function(re) {
			base = re;
			console.log(base); 
		}
	});
}); 