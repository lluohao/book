$(document).ready(init);

function init() {
	$("#screen").fullpage({
		verticalCentered: true,
		slidesColor: ['#f5f5f5', '#99FFEE', '#AABBEE', '#FFFF88'],
		anchors: ['page01', 'page02', 'page03', 'page04'],
		afterLoad: function(anchorLink, index) {
			$("#content" + index + " .ani").each(function() {
				var rel = $(this).attr("rel");
				var data = rel.split(",");
				console.log(data)
				$(this).css({
					"left": data[0] + "px",
					"top": data[1] + "px",
				});
				$(this).animate({
					"left": data[2] + "px",
					"top": data[3] + "px",
				}, 500);
			});
		}
	});
}