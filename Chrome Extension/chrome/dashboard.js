console.log('Background running');
let stored_data = null;
let data_source = "chrome";
let pending_data = null;
let solved_data = null;





window.onload = function () {
			
		function settimecookie(){

				if(getCookie('usertime')!=null)
					{
						eraseCookie('usertime');
					}
				
				let ttt = $('#inptime').val();
				setCookie('usertime',ttt);

				console.log(getCookie('usertime'));
				chrome.runtime.sendMessage({work: "settime", usertime :ttt });
			}

		  document.getElementById("settime").addEventListener("click", settimecookie);
		  document.getElementById("qs").addEventListener("click", ansverify);

		  
		  function ansverify(){
		  var radioValue = $("input[name='q1']:checked").val();
		  let selectedans;
		  if(radioValue == 'a')
		  	selectedans = $('#q1a').html();
		  if(radioValue == 'b')
		  	selectedans = $('#q1b').html();
		  if(radioValue == 'c')
		  	selectedans = $('#q1c').html();
		  if(radioValue == 'd')
		  	selectedans = $('#q1d').html();
		  console.log(anstoqu);
		  console.log(radioValue);
		  console.log(selectedans);
		  if(!selectedans)
		  	alert('first select an option');
		  else{
		  	if(selectedans.localeCompare(anstoqu) == 0){
		  		alert('you are correct bro');
		  	
		  		$.ajax({
		        type: 'POST',
		        url: "http://localhost/extension/chrome/pagescripts/rating_update.php",
		        data: {user:getCookie('user')},
		        success: function(response) {
					 $.ajax({
		        			type: 'POST',
		        			url: "http://localhost/extension/chrome/pagescripts/quiz.php",
		        			data: {user:getCookie('user'),id:prevquestionid},
		        			success: function(response) {
					
					//alert(data_source);

							var  k = JSON.parse(response);
							console.log(k);
							$('#qui').html(k.question);
							$('#q1a').html(k.opA);
							$('#q1b').html(k.opB);
							$('#q1c').html(k.opC);
							$('#q1d').html(k.opD);
							anstoqu = k.correctOp;
							prevquestionid =k.id;

		        	}	



					});
		        	}	
					});
	
		  	}//else
		  	else{

		  			$('#qui').html('try again sometime later');
							$('.answers').remove();
							

		  	}

		  }
		}
	
		var anstoqu ;
		var prevquestionid ;
		if(getCookie('user')!=null){
		  $.ajax({
		        type: 'POST',
		        url: "http://localhost/extension/chrome/pagescripts/quiz.php",
		        data: {user:getCookie('user'),id:null},
		        success: function(response) {
					
					//alert(data_source);
							var  k = JSON.parse(response);
							console.log(k);
							$('#qui').html(k.question);
							$('#q1a').html(k.opA);
							$('#q1b').html(k.opB);
							$('#q1c').html(k.opC);
							$('#q1d').html(k.opD);
							anstoqu = k.correctOp;
							prevquestionid = k.id;
		        	}	



					});
			}
		



		chrome.storage.sync.get(null, function (obj) {
      
		stored_data = obj;
		upload_data = obj;
        console.log(obj);
      	if(getCookie('user')===null)
 				{	console.log('no cookie found');       
            		 add_pending_problems();
        			 add_solved_problems();
        		//	 window.location.reload();
        		}
        		else
        		{	console.log('error');
        	$('.table_code_tracker').remove();
			$('.col-sm-8').append('<center><div class="table_code_tracker"><div id ="pending"></div><br><br><div id="solved"></div></div></center>');
     	    $.ajax({
		        type: 'POST',
		        url: "http://localhost/extension/chrome/pagescripts/upload.php",
		        data: { added_links: upload_data.added_links,solved_links: upload_data.solved_links, data : upload_data,user:getCookie('user')},
		        success: function(response) {
					document.getElementById("overlay").style.display="none";
					document.getElementById("login_div").style.display="none";
					document.getElementById("user_div").style.display="block";
					data_source = "db";
					add_pending_problems();
					add_solved_problems();
					//alert(data_source);
		        					}
					});


        		}

    });	


    chrome.storage.onChanged.addListener(function (changes, type) {



        if (type == "sync")
        	{
        		chrome.storage.sync.get(null, function (obj) {
      
		stored_data = obj;
		upload_data = obj;
        console.log(obj);
      	if(getCookie('user')===null)
 				{	console.log('no cookie found');       
            		
        			 window.location.reload();
        		}
        		else
        		{	console.log('error');
        	$('.table_code_tracker').remove();
			$('.col-sm-8').append('<center><div class="table_code_tracker"><div id ="pending"></div><br><br><div id="solved"></div></div></center>');
     	    $.ajax({
		        type: 'POST',
		        url: "http://localhost/extension/chrome/pagescripts/upload.php",
		        data: { added_links: upload_data.added_links,solved_links: upload_data.solved_links, data : upload_data,user:getCookie('user')},
		        success: function(response) {
					document.getElementById("overlay").style.display="none";
					document.getElementById("login_div").style.display="none";
					document.getElementById("user_div").style.display="block";
					data_source = "db";
					add_pending_problems();
					add_solved_problems();
					//alert(data_source);
		        					}
					});


        		}

    });	
        	}
    });
    console.log(getCookie('user'));
    
  //  document.getElementById("clear_stats").addEventListener("click", clear_stats);
 //   document.getElementById("pp").addEventListener("click",pp);
    
	var login = document.getElementById("login");
    if(login)
   {
	 login.addEventListener("click",function(){
     //alert("login");
	 //console.log("test");
     var user = document.getElementById("email").value;
	 var pass = document.getElementById("password").value;
	 /*var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if(xhttp.responseText=="login")
	        {
	               document.getElementById("login_div").style.display="none";
				   document.getElementById("user_div").style.display="block";
				   //alert(stored_data.added_links);
				   //alert("solse");
	        }
       }
    };
	 xhttp.open("GET","http://localhost/extension/Project/pagescripts/login.php?user="+user+"&pass="+pass+"",true);
	 xhttp.send();*/

	 $.ajax({
		 type: "POST",
		 url: "http://localhost/extension/chrome/pagescripts/login.php",
		 data: {user: user,pass: pass},
		 success: function(response){
			 if(response=="login")
			 {	setCookie('user',user,7);
			console.log(getCookie('user'));
				 document.getElementById("overlay").style.display="block";
				 document.getElementById("skip").addEventListener("click",function(){
					   chrome.storage.sync.get(null, function (obj){
											console.log(obj);
											//stored_data = obj;
											let toRemove =[];
											  $.each(obj, function(index, value)
    										{
        // CHANGE: add key to array
      										  toRemove.push(index);         
  										    });

											chrome.storage.sync.remove(toRemove, function(){
												//after removing
											 	$('.table_code_tracker').remove();
			$('.col-sm-8').append('<center><div class="table_code_tracker"><div id ="pending"></div><br><br><div id="solved"></div></div></center>');
											data_source = "db";
											add_pending_problems();
											add_solved_problems();
											document.getElementById("login_div").style.display="none";
											document.getElementById("user_div").style.display="block";
											document.getElementById("overlay").style.display="none";
											alert("ok");
											});
								//after getting obj0
							});	
					 
				
				 });
			 }
		 }
	 });
	 
  });
	  // alert("solere");
   }
   var upload = document.getElementById("upload");
    if(upload)
	{
       upload.addEventListener("click",function(){
		   		//alert("ok");
		//alert(upload_data.solved_links);
	    $.ajax({
        type: 'POST',
        url: "http://localhost/extension/chrome/pagescripts/upload.php",
        data: { added_links: upload_data.added_links,solved_links: upload_data.solved_links, data : upload_data,user:getCookie('user')},
        success: function(response) {
			document.getElementById("overlay").style.display="none";
			document.getElementById("login_div").style.display="none";
			document.getElementById("user_div").style.display="block";
			data_source = "db";
			add_pending_problems();
			add_solved_problems();
			//alert(data_source);
        }
    });
		   
	   });
	}
	var logout = document.getElementById("logout");
	if(logout)
	{
		 

		logout.addEventListener("click",function(){
			//$('.dataTables_wrapper').remove();
			$('.table_code_tracker').remove();
			$('.col-sm-8').append('<center><div class="table_code_tracker"><div id ="pending"></div><br><br><div id="solved"></div></div></center>');
			document.getElementById("login_div").style.display="block";
				   document.getElementById("user_div").style.display="none";    
					eraseCookie('user');
					data_source='chrome';
		
		});
	}
	var register = document.getElementById("register");
    if(register)
	{
		register.addEventListener("click",function(){
		document.getElementById("login_div").style.display="none";
	    document.getElementById("register_div").style.display="block"; 	
		var submit = document.getElementById("registerSubmit");
		submit.addEventListener("click",function(){
			var email = document.getElementById("email_register").value;
			var password_register = document.getElementById("password_register").value;
			var re_password_register = document.getElementById("confirm-password").value;
			$.ajax({
				type:"POST",
				url: "http://localhost/extension/chrome/pagescripts/register.php",
				data:{email:email,pass:password_register},
				success:function(response){
					if(response=="success")
					{	setCookie('user',email,7);
					    document.getElementById("user_div").style.display="block";
                        document.getElementById("register_div").style.display="none";	
                        data_source = "chrome";			
                        add_pending_problems();
			            add_solved_problems();						
					}
				}
			});
		});
		});
	}
	var pass_register = document.getElementById("confirm-password");
	if(pass_register)
	{
		pass_register.addEventListener("keyup",function(){
		var password_reg = document.getElementById("password_register").value;
        var re_password_reg = document.getElementById("confirm-password").value;	
		if(password_reg!=re_password_reg)
		{
			document.getElementById("error").style.display = "block";
		}
		else if(password_reg==re_password_reg)
		{
			document.getElementById("error").style.display = "none";
		}
		});
	}
	//var resetChrome = document.getElementById("resetChrome");
	var resetchromewithoutlogin = document.getElementById("clear_stats");

	if(resetchromewithoutlogin)
	{
			resetchromewithoutlogin.addEventListener("click",function(){
			chrome.storage.sync.get(null, function (obj){
											console.log(obj);
											//stored_data = obj;
											let toRemove =[];
											  $.each(obj, function(index, value)
    										{
        // CHANGE: add key to array
      										  toRemove.push(index);         
  										    });
											  chrome.storage.sync.remove('solved_links');
											chrome.storage.sync.remove(toRemove, function(){
												//after removing
											console.log('here');
											data_source = "chrome";
											stored_data = null;
										//	upload_data = null;
											add_pending_problems();
											add_solved_problems();
											document.getElementById("login_div").style.display="block";
											//document.getElementById("user_div").style.display="block";
											document.getElementById("overlay").style.display="none";

											//alert("ok");
											});
								//after getting obj0
							});	
		});

	}
	/*
	if(resetChrome)
	{
		resetChrome.addEventListener("click",function(){
			chrome.storage.sync.get(null, function (obj){
											console.log(obj);
											//stored_data = obj;
											let toRemove =[];
											  $.each(obj, function(index, value)
    										{
        // CHANGE: add key to array
      										  toRemove.push(index);         
  										    });

											chrome.storage.sync.remove(toRemove, function(){
												//after removing
											data_source = "db";
											add_pending_problems();
											add_solved_problems();
											document.getElementById("login_div").style.display="none";
											document.getElementById("user_div").style.display="block";
											document.getElementById("overlay").style.display="none";

											alert("ok");
											});
								//after getting obj0
							});	
		});
	}
	*/
	var resetData = document.getElementById("reset");
	if(resetData)
	{
		resetData.addEventListener("click",function(){
			$.ajax({
			type:"POST",
			url: "http://localhost/extension/chrome/pagescripts/reset.php",
			data:{user:getCookie('user')},
			success: function(response){
				if(response=="success")
				{
				   data_source = "db";
				  // chrome.storage.sync.clear();
				   add_pending_problems();
	               add_solved_problems();	
				}
			}
		});
		});
	}
};

function pp(){
       $('#stb').DataTable(); 
	   $('#ptb').DataTable();
}

function add_pending_problems() {
	if(data_source=="chrome")
	{
	chrome.storage.sync.get(null, function (obj) {
        console.log(obj);
        stored_data = obj;

	$('#ptb').remove();
	$('#pca').remove();
    let pending_p = document.getElementById('pending');
    if (!stored_data.added_links || stored_data.added_links.length == 0) {
        let t = document.createTextNode("No pending problems :)");
       	let pca = document.createElement('p');
       	pca.setAttribute('id','pca');
       	pca.appendChild(t);
        pending_p.appendChild(pca);
        $('#ptb').remove();
        //console.log('ass');
    }
    else {
        //let ul_added = document.createElement("ul");
    let i = 1;
        //pending_p.appendChild(ul_added);
 $('<table class="table table-striped" id ="ptb"><thead ><tr><th scope="col">#</th><th scope="col">First</th><th scope="col">last</th><th scope="col">Handle</th></tr></thead><tbody>').appendTo($('#pending'));
        for (link of stored_data.added_links) {
            console.log(link);
            let txt = document.createTextNode(link);
            let urltxt = document.createElement('a');
            urltxt.setAttribute('href', link);
            urltxt.setAttribute('target', '_blank');
            urltxt.appendChild(txt);
           // let li = document.createElement('li');
           // li.appendChild(urltxt);
            let info = stored_data[link];
            //$(li).append('<br/>' + '<b>Added on</b> : ' + info["time_added"]);
            let tagstring = extract_tagstring(info)
            
            $('<tr><th scope="row">'+ i +'</th>'+'<td>'+'<a href ="'+link+ '"target=_blank>'+link+'</a></td>'+'<td>'+info["time_solved"]+'</td>'+'<td>'+tagstring+'</td></tr>').appendTo($('#ptb'));
            i = i+1;
            console.log(i);
            //$('#ptb').DataTable();
            //$(li).append('<br/><b>Tags</b> : ' + tagstring);
            //ul_added.appendChild(li);
        }
      //   $('#ptb').DataTable();
    }

      });

    }
	else if(data_source=="db")
	{
		$('#ptb').remove();
		$.ajax({
			type:"POST",
			url:"http://localhost/extension/chrome/pagescripts/data_fetch.php",
			data:{user:getCookie('user')},
			success:function(response){
				pending_data = JSON.parse(response);
				console.log(JSON.parse(response));
				console.log(pending_data.added_links.length);
				let pending_p = document.getElementById('pending');
				$("#ptb").remove();
				$('#pca').remove();
    if (!pending_data.added_links ||pending_data["count"]== 0) {
       // let t = document.createTextNode("No pending problems :)");
       // pending_p.appendChild(t);
       // console.log('ass');
        let t = document.createTextNode("No pending problems :)");
       	let pca = document.createElement('p');
       	pca.setAttribute('id','pca');
       	pca.appendChild(t);
        pending_p.appendChild(pca);
        $('#countpending').html('');
        $('#countpending').append('0');

        //$('#ptb').remove();
    }
    else {
        //let ul_added = document.createElement("ul");
    let i = 1;
        //pending_p.appendChild(ul_added);
 $('<table class="table table-striped" id ="ptb"><thead ><tr><th scope="col">#</th><th scope="col">First</th><th scope="col">last</th><th scope="col">Handle</th></tr></thead><tbody>').appendTo($('#pending'));
        var count = 0;
		var length = pending_data["count"];
		$('#countpending').html('');
		$('#countpending').append(length);
		for (count=0;count<length;count++) {
			link = pending_data.added_links[count];
            console.log(link);
            let txt = document.createTextNode(link);
            let urltxt = document.createElement('a');
            urltxt.setAttribute('href', link);
            urltxt.setAttribute('target', '_blank');
            urltxt.appendChild(txt);
           // let li = document.createElement('li');
           // li.appendChild(urltxt);
            let info = pending_data[link];
            //$(li).append('<br/>' + '<b>Added on</b> : ' + info["time_added"]);
            let tagstring = info["tags"];
            
            $('<tr><th scope="row">'+ i +'</th>'+'<td>'+'<a href ="'+link+ '"target=_blank>'+link+'</a></td>'+'<td>'+info["time_solved"]+'</td>'+'<td>'+tagstring+'</td></tr>').appendTo($('#ptb'));
            i = i+1;
            console.log(i);
            //$('#ptb').DataTable();
            //$(li).append('<br/><b>Tags</b> : ' + tagstring);
    	       //ul_added.appendChild(li);
        	}
        	$('#ptb').DataTable();

    		}
			
			}
			//}
		});
	}
}

function add_solved_problems() {
	//alert("ok");


	if(data_source=="chrome")
	{
    
   		chrome.storage.sync.get(null, function (obj) {
        console.log(obj);
        stored_data = obj;
			

    $('#stb').remove();
    $('#sca').remove();
    let solved_p = document.getElementById('solved');
    if (!stored_data.solved_links || stored_data.solved_links.length == 0) {
        let t = document.createTextNode("You haven't solved any problems yet!");
       
        let sca = document.createElement('p');
       	sca.setAttribute('id','sca');
       	sca.appendChild(t);
        solved_p.appendChild(sca);
    //    $('#stb').remove();
    }
    else {
      
        $('<table class="table table-striped" id ="stb"><thead ><tr><th scope="col">#</th><th scope="col">First</th><th scope="col">last</th><th scope="col">Handle</th></tr></thead><tbody>').appendTo($('#solved'));
        
        //$("eee").appendTo($('#solved'));
        //let ul_added = document.createElement("ul");
        console.log('thisis');
        console.log(stored_data);
	        //solved_p.appendChild(ul_added);
        let i = 1;
        for (link of stored_data.solved_links) {
            //$('<tr><th scope="row">'+ i +'</th>'+'<td>'+urltxt+'</td>'+'<td>'+info["time_solved"]+'</td>'+'<td>'+tagstring+'</td></tr>').appendTo($('#stb'));
            //$(''+i).appendTo($('#'));
            //$('</th>').appendTo($('#stb'));
            let txt = document.createTextNode(link);
            let urltxt = document.createElement('a');
            urltxt.setAttribute('href', link);
            urltxt.setAttribute('target', '_blank');
            urltxt.appendChild(txt);
            console.log(urltxt);
           // $('<td>'+urltxt+'</td>').appendTo($('#stb'));
            console.log(link);
            let info = stored_data[link];

           // $('<td>'+info["time_solved"]+'</td>').appendTo($('#stb'));
            let tagstring = extract_tagstring(info);
           // $('<td>'+tagstring+'</td></tr>').appendTo($('#stb'));
           
           // let li = document.createElement('li');
            $('<tr><th scope="row">'+ i +'</th>'+'<td>'+'<a href ="'+link+ '"target=_blank>'+link+'</a></td>'+'<td>'+info["time_solved"]+'</td>'+'<td>'+tagstring+'</td></tr>').appendTo($('#stb'));
            i = i+1;
            console.log(i);
            //$('#stb').DataTable();
            //li.appendChild(urltxt);
            
            //$(li).append('<br/>' + '<b>Solved on</b> : ' + info["time_solved"]);
            
           // $(li).append('<br/><b>Tags</b> : ' + tagstring);
           // ul_added.appendChild(li);
        }

        //$('</tbody></table>').appendTo($('#stb'));
       // $('#stb').DataTable();
    }		
       
   		 });	

    }
	else if(data_source=="db")
	{
		$('#stb').remove();
		$.ajax({
			type:"POST",
			url:"http://localhost/extension/chrome/pagescripts/data_fetch_solved.php",
			data:{user:getCookie('user')},
			success:function(response){
				solved_data = JSON.parse(response);
				let solved_p = document.getElementById('solved');
				$('#stb').remove();
				$('#sca').remove();
    if (!solved_data.solved_links || solved_data['count'] == 0) {
        let t = document.createTextNode("You haven't solved any problems yet!");
        let sca = document.createElement('p');
       	sca.setAttribute('id','sca');
       	sca.appendChild(t);
        solved_p.appendChild(sca);
        $('#stb').remove();
        $('#countdone').html('');
        $('#countdone').append('0');

    }
	else {
      
        $('<table class="table table-striped" id ="stb"><thead ><tr><th scope="col">#</th><th scope="col">First</th><th scope="col">last</th><th scope="col">Handle</th></tr></thead><tbody>').appendTo($('#solved'));
        console.log(solved_data);
        //$("eee").appendTo($('#solved'));
        //let ul_added = document.createElement("ul");
        //solved_p.appendChild(ul_added);
        let i = 1;
		var count = 0;
		var length = solved_data['count'];
		$('#countdone').html('');
		$('#countdone').append(length);
        for (count=0;count<length;count++) {
            //$('<tr><th scope="row">'+ i +'</th>'+'<td>'+urltxt+'</td>'+'<td>'+info["time_solved"]+'</td>'+'<td>'+tagstring+'</td></tr>').appendTo($('#stb'));
            //$(''+i).appendTo($('#'));
            //$('</th>').appendTo($('#stb'));
			link = solved_data.solved_links[count];
			console.log(link);
            let txt = document.createTextNode(link);
            let urltxt = document.createElement('a');
            urltxt.setAttribute('href', link);
            urltxt.setAttribute('target', '_blank');
            urltxt.appendChild(txt);
           // $('<td>'+urltxt+'</td>').appendTo($('#stb'));
            let info = solved_data[link];
			console.log(info);
           // $('<td>'+info["time_solved"]+'</td>').appendTo($('#stb'));
           
           if(info != 'undefined')
           tagstring = info["tags"];
           // $('<td>'+tagstring+'</td></tr>').appendTo($('#stb'));
           
           // let li = document.createElement('li');
            $('<tr><th scope="row">'+ i +'</th>'+'<td>'+'<a href ="'+link+ '"target=_blank>'+link+'</a></td>'+'<td>'+info["time_solved"]+'</td>'+'<td>'+tagstring+'</td></tr>').appendTo($('#stb'));
            i = i+1;
            console.log(i);
            //
            //li.appendChild(urltxt);
            
            //$(li).append('<br/>' + '<b>Solved on</b> : ' + info["time_solved"]);
             
           // $(li).append('<br/><b>Tags</b> : ' + tagstring);
           // ul_added.appendChild(li);
        }

		$('#stb').DataTable();
        //$('</tbody></table>').appendTo($('#stb'));
   
    	}
    	
			}
		});
		/*let solved_p = document.getElementById('solved');
    if (!stored_data.solved_links || stored_data.solved_links.length == 0) {
        let t = document.createTextNode("You haven't solved any problems yet!");
        solved_p.appendChild(t);
    }
    else {
      
        $('<table class="table table-striped" id ="stb"><thead ><tr><th scope="col">#</th><th scope="col">First</th><th scope="col">last</th><th scope="col">Handle</th></tr></thead><tbody>').appendTo($('#solved'));
        
        //$("eee").appendTo($('#solved'));
        //let ul_added = document.createElement("ul");
        //solved_p.appendChild(ul_added);
        let i = 1;
        for (link of stored_data.solved_links) {
            //$('<tr><th scope="row">'+ i +'</th>'+'<td>'+urltxt+'</td>'+'<td>'+info["time_solved"]+'</td>'+'<td>'+tagstring+'</td></tr>').appendTo($('#stb'));
            //$(''+i).appendTo($('#'));
            //$('</th>').appendTo($('#stb'));
            let txt = document.createTextNode(link);
            let urltxt = document.createElement('a');
            urltxt.setAttribute('href', link);
            urltxt.setAttribute('target', '_blank');
            urltxt.appendChild(txt);
           // $('<td>'+urltxt+'</td>').appendTo($('#stb'));
            let info = stored_data[link];
           // $('<td>'+info["time_solved"]+'</td>').appendTo($('#stb'));
            let tagstring = extract_tagstring(info);
           // $('<td>'+tagstring+'</td></tr>').appendTo($('#stb'));
           
           // let li = document.createElement('li');
            $('<tr><th scope="row">'+ i +'</th>'+'<td>'+urltxt+'</td>'+'<td>'+info["time_solved"]+'</td>'+'<td>'+tagstring+'</td></tr>').appendTo($('#stb'));
            i = i+1;
            console.log(i);
            //li.appendChild(urltxt);
            
            //$(li).append('<br/>' + '<b>Solved on</b> : ' + info["time_solved"]);
            
           // $(li).append('<br/><b>Tags</b> : ' + tagstring);
           // ul_added.appendChild(li);
        }

        //$('</tbody></table>').appendTo($('#stb'));

    }*/
	}
}

function extract_tagstring(info) {
    let tagstring = '';
    if (!info.tags || info.tags.length == 0)
        tagstring += 'None';
    else {
        tagstring += info.tags[0];
        for (let i = 1; i < info.tags.length; i++) {
            tagstring += ', ' + info.tags[i];
        }
    }
    return tagstring;
}

function clear_stats() {
    chrome.storage.sync.clear();
    console.log('successfully cleared');
}


function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
function eraseCookie(name) {   
    document.cookie = name+'=; Max-Age=-99999999;';  
}