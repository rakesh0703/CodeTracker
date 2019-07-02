<?php
   $con = mysqli_connect('localhost','root','','upsolve_tracker');
   $user = $_POST['user'];
   $pass = $_POST['pass'];
   $pass = filter_var($pass);
   $pass = hash("sha256",$pass);
  // echo $user;
   //echo $pass;
   $query = "select * from user where user='$user' and password='$pass'";
   $result = mysqli_query($con,$query) or die(mysqli_error($con));
   $count = mysqli_num_rows($result);
   if($count!=0)
   {
	   //header("location:../dashboard.html");
	  // session_start();
	   //$_SESSION['user'] = $user;
	   echo "login";
	   
   }
   else
   {
	   echo "fail";
   }
?>