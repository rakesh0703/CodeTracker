$(function(){
	$("body").css("background","#000");
	$('.tab-header').append('<div class="code_tracker_style" style="display:inline-block;"></div>');
	$('.code_tracker_style').append('<button type="button" id="todo" class="btn btn-info btn-lg" style="background:#ffa500;border-color:#ffa500;margin-right:3px;font-size:13px;"> Todo</button>');
	$('.code_tracker_style').append('<button type="button" id="done" class="btn btn-success btn-lg" style="margin-right:3px;font-size:13px;">Done</button>');
	$('.code_tracker_style').append('<select id ="da" class="js-example-basic-multiple" name="states[]" multiple="multiple" style="width:300px;font-size:13px;"><option value="dp">DP</option><option value="graph">Graphs</option><option value="Segment trees">Segment Trees</option></select>');
 	$('.js-example-basic-multiple').select2();  
	$('#todo').click(function(event) {
 		 //var tags = $('#tags').val().split(",");
 		console.log( $('#da').val());
 		 var tags = $('#da').val();
 		 chrome.runtime.sendMessage({work: "todo", tags :tags });
 	  	alert("This program was added to to-do.");
 	});

 	$('#done').click(function(event) {
 	 // var tags = $('#tags').val().split(",");
 	  var tags = $('#da').val();
 	  	console.log( $('#da').val());
 	  chrome.runtime.sendMessage({work: "done", tags :tags });
 	  alert("This program was added to done");
	});
});