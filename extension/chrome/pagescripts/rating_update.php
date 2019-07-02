<?php
    $con = mysqli_connect('localhost','root','','upsolve_tracker');
    $user = $_POST['user'];
    $query = "select * from `user` where `user`='$user'";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    $row = mysqli_fetch_array($result);
    $rating = $row['rating'] + 1;
    $query = "update `user` set rating = '$rating' where `user`='$user'";
    $result1 = mysqli_query($con,$query) or die(mysqli_error($con));
    if($result1 == true)
    {
    	echo "success";
    }
?>