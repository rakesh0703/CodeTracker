<?php
  $con = mysqli_connect('localhost','root','','upsolve_tracker');
  //session_start();
  $user = $_POST['user'];
  $query = "select * from upload where user = '$user'";
  $result = mysqli_query($con,$query) or die(mysqli_error($con));
  $row = mysqli_fetch_array($result);
  $table = $row['tableId'];
  $query = "delete from `$table`";
  $result = mysqli_query($con,$query) or die(mysqli_error($con));
  echo "success";
?>