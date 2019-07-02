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

var options ={

type:'basic',
title:'Pending problems',
message:'You Have more than 10 pending problems !!',
iconUrl:'icon48.png',
};


function callback(){


}
//
//var timeinterval = getCookie('usertime');
//timeinterval = timeinterval*60*60*1000;
//console.log('timeinterval');

chrome.runtime.onMessage.addListener(function(request, sender, sendResponse){
 
if(request.work == 'settime')
{
let timeinterval = request.usertime;
//timeinterval = timeinterval*60*60*1000;

console.log(timeinterval);

timeinterval = timeinterval*60*1000;    
var oo = setInterval(function (){

if(getCookie('user')!=null)
{
$.ajax({
            type:"POST",
            url:"http://localhost/extension/chrome/pagescripts/data_fetch.php",
            data:{user:getCookie('user')},
            success:function(response){
                pending_data = JSON.parse(response);
                let tempcount = pending_data["count"];
                if(tempcount>=2)
                {
                chrome.notifications.create(options,callback);
                }

            }

    });
}

}, timeinterval);

}

});





