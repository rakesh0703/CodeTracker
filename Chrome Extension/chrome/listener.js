let current_tab = null;
let added_links = [];
let solved_links = [];
let tags = [];
var inptags =[] ;
let current_info = null;
let current_tab_status = 'NA';

let tag_input_el = null;
let tag_list_el = null;
let tags_section = null;
let onscreen_tags = new Set();


chrome.runtime.onMessage.addListener(function(request, sender, sendResponse){
  
//initialize();

if(request.work!='settime')
{
  current_tab = sender.tab;
  inptags = request.tags;
  chrome.storage.sync.get(null, function (obj) {
        //query for the already stored data in chrome storage
        console.log(obj);
        console.log(inptags);
        current_tab_status = 'NA';
        var prev_added_links = obj.added_links;
        var prev_solved_links = obj.solved_links;

        var query = { active: true, currentWindow: true };
        
            //query for the current tab, and set the other variebles accordingly
    //       current_tab = tabs[0];
        if (!prev_added_links) {
                chrome.storage.sync.set({ 'added_links': [] }, function () {
                    console.log('set up \'added_links\' for the first time!');

                if (!prev_solved_links) {
                    chrome.storage.sync.set({ 'solved_links': [] }, function () {
                        console.log('set up \'solved_links\' for the first time!');
                                
                                            if (current_tab_status != 'NA') {
                                                current_info = obj[current_tab.url];
                                                console.log(current_info);
                                                }
added_links = [];
solved_links = [];
console.log(solved_links);
    console.log(added_links);
    console.log(current_tab_status);
    if (request.work == "todo"){

        console.log('to do works request');
        
        for (i=0;i<request.tags.length;i++)
        {alert(request.tags[i]);}
         add_link_handler();
    }
    if(request.work == "done"){
        console.log('done works request');
        mark_solved_handler();
    }




                    });

                }
                else {
                    //previous solved_links have to be used
                    solved_links = prev_solved_links;
                    added_links = [];
                    console.log('we are');
                    console.log(solved_links);
                    if (current_tab_status == 'NA') {
                        if (solved_links.indexOf(current_tab.url) != -1)
                            current_tab_status = 'SOLVED';
                    }
                                if (current_tab_status != 'NA') {
                                    current_info = obj[current_tab.url];
                                    console.log(current_info);
                                    }
console.log(solved_links);
    console.log(added_links);
    console.log(current_tab_status);
    if (request.work == "todo"){

        console.log('to do works request');
        
        for (i=0;i<request.tags.length;i++)
        {alert(request.tags[i]);}
         add_link_handler();
    }
    if(request.work == "done"){
        console.log('done works request');
        mark_solved_handler();
    }



                }

                });//prev added links
            }
            else {
                //previous added_links have to be used
                added_links = prev_added_links;
                                if (added_links.indexOf(current_tab.url) != -1)
                    current_tab_status = 'PENDING';


                if (!prev_solved_links) {
                    solved_links=[];
                    chrome.storage.sync.set({ 'solved_links': [] }, function () {
                        console.log('set up \'solved_links\' for the first time!');
                                        if (current_tab_status != 'NA') {
                                            current_info = obj[current_tab.url];
                                            console.log(current_info);
                                          //  display_tags();
                                        }
console.log(solved_links);
    console.log(added_links);
    console.log(current_tab_status);
    if (request.work == "todo"){

        console.log('to do works request');
        
        for (i=0;i<request.tags.length;i++)
        {alert(request.tags[i]);}
         add_link_handler();
    }
    if(request.work == "done"){
        console.log('done works request');
        mark_solved_handler();
    }


                    });
                }
                else {
                    //previous solved_links have to be used
                    solved_links = prev_solved_links;
                    console.log('we are');
                    console.log(solved_links);
                    if (current_tab_status == 'NA') {
                        if (solved_links.indexOf(current_tab.url) != -1)
                            current_tab_status = 'SOLVED';
                    }
                    if (current_tab_status != 'NA') {
                    current_info = obj[current_tab.url];
                    console.log(current_info);
                
              //  display_tags();
                    }

console.log(solved_links);
    console.log(added_links);
    console.log(current_tab_status);
    if (request.work == "todo"){

        console.log('to do works request');
        
        for (i=0;i<request.tags.length;i++)
        {alert(request.tags[i]);}
         add_link_handler();
    }
    if(request.work == "done"){
        console.log('done works request');
        mark_solved_handler();
    }




                }//prev solved links
                //we can change this later for better performance
                // we can assign the status separately only
            }//final else


         

    

    });//RETURNS PREVIOUS OBJECTS

}//settime

});



// initial setup


function add_link_handler() {
    if (current_tab_status == 'PENDING') {
        console.log('already added to pending')
    }
    else if (current_tab_status == 'SOLVED')
        {console.log('already solved bro');}///swal('You have solved this problem before!');
    else {
        current_tab_status = 'PENDING';
        added_links.push(current_tab.url);
        console.log(added_links);
     
        current_info = {
            'status': 'PENDING',
            'time_added': '',
            'tags':inptags
        };
        current_info.time_solved = new Date().toDateString();
        console.log('dooone');
        chrome.storage.sync.set({
            'added_links': added_links,
            [current_tab.url]: current_info
        }, function(){
             console.log('adlink handler completed');

          //  update_status();
            //display_tags();        
        });
    }
}

function remove_link_handler() {
    if (current_tab_status == 'NA') {
            console.log('not added vant remove')
            }
    else if (current_tab_status == 'SOLVED')
        { }///swal('You have solved this problem before!');
    else {
        added_links = added_links.filter(link => (link != cfurrent_tab.url));
        current_tab_status = 'NA';
        current_info = null;
        chrome.storage.sync.set({ 'added_links': added_links });
        //hide_tags();
        //update_status();
        chrome.storage.sync.remove(current_tab.url, function () {
            //swal('Problem removed!');
        });
    }
}

function mark_solved_handler() {
    if (current_tab_status == 'SOLVED') {
        console.log('You have solved this problem before!');
    }
    else {
        if (current_tab_status == 'PENDING') {
            //first remove it from added_links
            added_links = added_links.filter(link => (link != current_tab.url));
            console.log(added_links);
            chrome.storage.sync.set({ 'added_links': added_links }, function () {
                console.log('removed from added_links!');
            });
        }

        //if (!current_info)
        current_info = { 'tags': inptags };
    
        current_tab_status = 'SOLVED';
        current_info.status = 'SOLVED';
        current_info.time_solved = new Date().toDateString();
        console.log(current_info);
    
        solved_links.push(current_tab.url);
        chrome.storage.sync.set({
            'solved_links': solved_links,
            [current_tab.url]: current_info
        }, function () {
            console.log('Problem marked as solved!');
        });
    }
}

