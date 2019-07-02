$(function(){
	// body...
	//$('.second-level-menu-list').append('<li>ADD TO</li> ');
 	/*$('.second-level-menu-list').append('<button type="button" id="todo" class="btn btn-info btn-lg"> To-do</button>');
 	$('.second-level-menu-list').append('<button type="button" id="done" class="btn btn-success btn-lg">Done</button>');
 	//$('.second-level-menu-list').append('<input type="text" id ="tags">');
	$('.second-level-menu-list').append('<select id ="da" class="js-example-basic-multiple" name="states[]" multiple="multiple"><option value="dp">DP</option><option value="graph">Graphs</option><option value="Segment trees">Segment Trees</option></select>');*/
    $('.second-level-menu-list').append('<div class="code_tracker_style" style="display:inline-block;min-height:300px;position:fixed;"></div>');
	$('.code_tracker_style').append('<button type="button" id="todo" class="btn btn-info btn-lg" style="background:#ffa500;border-color:#ffa500;margin-right:3px;"> To-do</button>');
	$('.code_tracker_style').append('<button type="button" id="done" class="btn btn-success btn-lg" style="margin-right:3px;">Done</button>');
	$('.code_tracker_style').append('<select id ="da" class="js-example-basic-multiple" name="states[]" multiple="multiple" style="width:300px"><option value="dp">DP</option><option value="graph">Graphs</option><option value="Segment trees">Segment Trees</option></select>');
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