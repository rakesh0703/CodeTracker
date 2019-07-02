<?php
  $con = mysqli_connect('localhost','root','','upsolve_tracker');
  //session_start();
  $user = $_POST['user'];
  if(isset($_POST['added_links']))
  {
  $added_links = $_POST['added_links'];
  $len_add = count($added_links);
  }
  if(isset($_POST['solved_links']))
  {
  $solved_links = $_POST['solved_links'];
  $len_solve = count($solved_links);
  }
  $data = $_POST['data'];
  $query = "select * from upload where user = '$user'";
  $result = mysqli_query($con,$query) or die(mysqli_error($con));
  $result = mysqli_fetch_array($result);
  $table = $result['tableId'];
  if(isset($_POST['added_links']))
  {
  for($i=0;$i<$len_add;$i++)
  {
	  $link = $added_links[$i];
	  //echo $link;
	  $info = $data[$link];
	  $tag = $info['tags'];
	  $tag = implode(" ",$tag);
	  $time = $info['time_solved'];
	  $status = $info['status'];
	  $query = "select * from `$table` where link = '$link' and status = 'PENDING'";
	  		 // echo $query;
	  $result = mysqli_query($con,$query) or die(mysqli_error($con));
	  $count = mysqli_num_rows($result);
	  if($count!=0)
	  {
		  $query = "Update $table set tag = '$tag',status = '$status',time = '$time' where link = '$link'";
		  $result2 = mysqli_query($con,$query) or die(mysqli_error($con)); 
	  }
	  else
	  {
		  $query = "insert into `$table`(link,tag,time,status) values('$link','$tag','$time','$status')";
		  $result = mysqli_query($con,$query) or die(mysqli_error($con));
	  }
  }
  }
  if(isset($_POST['solved_links']))
  {
  for($i=0;$i<$len_solve;$i++)
  {
	  $link = $solved_links[$i];
	 // echo $link;
	  $info = $data[$link];
	  $tag = $info['tags'];
	  $tag = implode(" ",$tag);
	  echo $tag;
	  $time = $info['time_solved'];
	  $status = $info['status'];
	  $query = "select * from `$table` where link = '$link' and status = 'SOLVED'";
	  		  //echo $query;
	  $result = mysqli_query($con,$query) or die(mysqli_error($con));
	  $count = mysqli_num_rows($result);
	  if($count!=0)
	  {
		  $query = "Update $table set tag = '$tag',status = '$status',time = '$time' where link = '$link'";
		  $result2 = mysqli_query($con,$query) or die(mysqli_error($con)); 
	  }
	  else
	  {
		  $query = "insert into `$table`(link,tag,time,status) values('$link','$tag','$time','$status')";
		  $result = mysqli_query($con,$query) or die(mysqli_error($con));
	  }
  }
  }
?>