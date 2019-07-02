$( document ).ready(function() {
	// body.
	
//$('h1').eq(1).append('<i style="font-size: 15px !important;">Code Tracker--</i>');
;
$('h1').eq(1).append('<a class="button b2 right" style="color:white !important;" id="done">DONE!</a>');
$('h1').eq(1).append('<a class="button b1 right" id="todo">TODO!</a>')
$('h1').eq(1).append('<i style=" float:right; font-size: 15px !important;"> Code Tracker 	:</i>');
$('h1').eq(1).append('<select id ="da" class="js-example-basic-multiple" name="states[]" multiple="multiple"><option value="dp">DP</option><option value="graph">Graphs</option><option value="Segment trees">Segment Trees</option></select>');

 //	$('.inline-list').eq(0).append('<button type="button" id="todo" class="btn btn-info btn-lg"> To-do</button>');
 //	$('#large-12 columns').append('<button type="button" id="done" class="btn btn-success btn-lg">Done</button>');
 	//$('.second-level-menu-list').append('<input type="text" id ="tags">');
	//$('#large-12 columns').append('<select id ="da" class="js-example-basic-multiple" name="states[]" multiple="multiple"><option value="dp">DP</option><option value="graph">Graphs</option><option value="Segment trees">Segment Trees</option></select>');

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