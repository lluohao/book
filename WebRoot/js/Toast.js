var Toast = {
	showMessage: function showMessage(mesg, time) {
		if(document.getElementById("toast-div") == null) {
			var eDiv = "<div id='toast-div' style='display:none;width:100%;text-align:center;position: fixed;top: 0px;left: 0px;background: #EF5B00;color: #FFF;font-size: 18px;'><p id='toast-p'></p></div>";
			$("body").append(eDiv);
		}
		$("#toast-p").text(mesg);
		$("#toast-div").fadeIn(100);
		setTimeout(function() {
			$("#toast-div").fadeOut(100);
		}, time);
	}
}
