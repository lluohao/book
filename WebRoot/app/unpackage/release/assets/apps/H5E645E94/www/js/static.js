var base = "http://192.168.191.1/code"
var hy = {
	get: function(name) {
		return plus.navigator.getCookie(name);
	},
	put: function(name, value) {
		return plus.navigator.setCookie(name, value);
	}
}