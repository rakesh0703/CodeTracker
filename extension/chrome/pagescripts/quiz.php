<?php
   $con = mysqli_connect('localhost','root','','upsolve_tracker');
   $user = $_POST['user'];
   $id = $_POST['id'];
   $query = "select * from `upload` where `user` = '$user'";
   $result = mysqli_query($con,$query) or die(mysqli_error($con));
   $row = mysqli_fetch_array($result);
   $table = $row['tableId'];
   $query = "select * from $table where NOT `tag` = ''";
   $result = mysqli_query($con,$query) or die(mysqli_error($con));
   $row = mysqli_fetch_array($result);
   //$tag_array = [];
   $k = 0;
   while($row = mysqli_fetch_array($result))
   {
   	 $tag = $row['tag'];
     $tag = explode(',',$tag);
     $count = count($tag); 
     for($i=0;$i<$count;$i++)
     {
        $tag_array[$k] = $tag[$i];
        $k++;
     }
   }
  // print_r($tag_array);
   $key = array_rand($tag_array);
   $tag_select = $tag_array[$key];
   $query = "select * from `questions` where `tag` = '$tag_select' and not id = '$id'";
   $result = mysqli_query($con,$query) or die(mysqli_error($con));
   $row = mysqli_fetch_array($result);
   $json = array("question"=>$row['question'],"opA"=>$row["opA"],"opB"=>$row['opB'],"opC"=>$row['opC'],"opD"=>$row['opD'],"correctOp"=>$row['correctOp'],"id"=>$row['id']);
   $json = json_encode($json,JSON_FORCE_OBJECT);
   echo $json;
?>