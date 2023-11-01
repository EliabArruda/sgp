$(document).ready(function() {
    $(".popup").fancybox({
		'minWidth': "10%",
		'minHeight': "10%",
		'type': 'iframe',
		'onClosed': function() {
			alert("Page reloaded!"); 
			console.log("Page reloaded!"); 
			parent.location.reload(true);
		},
		'onCleanup': function() {
			alert("Page reloaded after!"); 
			console.log("Page reloaded after!"); 
			return window.location.reload();
		},
		'afterClose': function() {
			parent.location.href = parent.location.href;
		},
	});
});
