$( document ).ready(function() {	// body...
	//$('.second-level-menu-list').append('<li>ADD TO</li> ');
 	
 	//$('.second-level-menu-list').append('<input type="text" id ="tags">');
	$('#header').append('<select id ="da" class="js-example-basic-multiple" name="states[]" multiple="multiple"><option value="dp">DP</option><option value="graph">Graphs</option><option value="Segment trees">Segment Trees</option></select>');
 	$('.js-example-basic-multiple').select2();
	$('.select2-container').addClass('onc');
 	$('#header').append('<button type="button" id="done" class="btn b2 " style="color:white;">Done</button>');
 	$('#header').append('<button type="button" id="todo" class="btn b1" style="color:white;"> To-do</button>');

   
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