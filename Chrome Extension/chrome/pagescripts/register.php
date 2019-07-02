<?php
   $con = mysqli_connect('localhost','root','','upsolve_tracker');
   $email = $_POST['email'];
   $password = $_POST['pass'];
   $password = filter_var($password);
   $password = hash("sha256",$password);
   $query = "Insert into user(`user`,`password`) values('$email','$password')";
   $result1 = mysqli_query($con,$query) or die(mysqli_error($con));
   $tableid = uniqid();
   $query = "create table `$tableid`(`link` varchar(255) primary key,`tag` varchar(255),`time` varchar(255),`status` varchar(255))";
   $result2 = mysqli_query($con,$query) or die(mysqli_error($con));
   $query = "insert into upload(`user`,`tableId`) values('$email','$tableid')";
   $result3 = mysqli_query($con,$query) or die(mysqli_error($con));

   if($result1==true&&$result2==true&&$result3==true)
   { // session_start();
	   //$_SESSION['user'] = $email;
	   echo "success";
   }
   ?>