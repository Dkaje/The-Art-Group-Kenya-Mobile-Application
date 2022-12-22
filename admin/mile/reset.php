<?php
include('../database/conn.php');
$username = $_POST['username'];
$password = md5($_POST['password']);
$user = $_POST['user'];
if($user=='c'){
if(mysqli_num_rows(mysqli_query($con,"SELECT * from customer where username='$username'"))===1){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from customer where username='$username' and password='$password'"))===1){
        $result['status'] = 0;
        $result['message'] = "Try A different password";
    }else{
        mysqli_query($con,"UPDATE customer set password='$password' where username='$username'");
         $result['status'] = 1;
        $result['message'] =  "Reset was successful";
    }
}else{
    $result['status'] = 0;
    $result['message'] = "Account not found";
}
}elseif($user=='d'){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from driver where username='$username'"))===1){
        if(mysqli_num_rows(mysqli_query($con,"SELECT * from driver where username='$username' and password='$password'"))===1){
            $result['status'] = 0;
            $result['message'] = "Try A different password";
        }else{
            mysqli_query($con,"UPDATE driver set password='$password' where username='$username'");
             $result['status'] = 1;
            $result['message'] =  "Reset was successful";
        }
    }else{
        $result['status'] = 0;
        $result['message'] = "Account not found";
    }
    }
elseif($user=='v'){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from staff where username='$username' and role='Videographer'"))===1){
        if(mysqli_num_rows(mysqli_query($con,"SELECT * from staff where username='$username' and password='$password' and role='Videographer'"))===1){
            $result['status'] = 0;
            $result['message'] = "Try A different password";
        }else{
            mysqli_query($con,"UPDATE staff set password='$password' where username='$username' and role='Videographer'");
             $result['status'] = 1;
            $result['message'] =  "Reset was successful";
        } 
}else{
    $result['status'] = 0;
    $result['message'] = "Account not found";
}
}elseif($user=='rv'){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from staff where username='$username' and role='Service'"))===1){
        if(mysqli_num_rows(mysqli_query($con,"SELECT * from staff where username='$username' and password='$password' and role='Service'"))===1){
            $result['status'] = 0;
            $result['message'] = "Try A different password";
        }else{
            mysqli_query($con,"UPDATE staff set password='$password' where username='$username' and role='Service'");
             $result['status'] = 1;
            $result['message'] =  "Reset was successful";
        } 
}else{
    $result['status'] = 0;
    $result['message'] = "Account not found";
}
}elseif($user=='ds'){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from staff where username='$username' and role='Designer'"))===1){
        if(mysqli_num_rows(mysqli_query($con,"SELECT * from staff where username='$username' and password='$password' and role='Designer'"))===1){
            $result['status'] = 0;
            $result['message'] = "Try A different password";
        }else{
            mysqli_query($con,"UPDATE staff set password='$password' where username='$username' and role='Designer'");
             $result['status'] = 1;
            $result['message'] =  "Reset was successful";
        } 
}else{
    $result['status'] = 0;
    $result['message'] = "Account not found";
}
}elseif($user=='f'){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from staff where username='$username' and role='Finance'"))===1){
        if(mysqli_num_rows(mysqli_query($con,"SELECT * from staff where username='$username' and password='$password' and role='Finance'"))===1){
            $result['status'] = 0;
            $result['message'] = "Try A different password";
        }else{
            mysqli_query($con,"UPDATE staff set password='$password' where username='$username' and role='Finance'");
             $result['status'] = 1;
            $result['message'] =  "Reset was successful";
        } 
}else{
    $result['status'] = 0;
    $result['message'] = "Account not found";
}
}elseif($user=='i'){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from staff where username='$username' and role='Inventory'"))===1){
        if(mysqli_num_rows(mysqli_query($con,"SELECT * from staff where username='$username' and password='$password' and role='Inventory'"))===1){
            $result['status'] = 0;
            $result['message'] = "Try A different password";
        }else{
            mysqli_query($con,"UPDATE staff set password='$password' where username='$username' and role='Inventory'");
             $result['status'] = 1;
            $result['message'] =  "Reset was successful";
        } 
}else{
    $result['status'] = 0;
    $result['message'] = "Account not found";
}
}elseif($user=='su'){
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from supplier where username='$username'"))===1){
        if(mysqli_num_rows(mysqli_query($con,"SELECT * from supplier where username='$username' and password='$password'"))===1){
            $result['status'] = 0;
            $result['message'] = "Try A different password";
        }else{
            mysqli_query($con,"UPDATE supplier set password='$password' where username='$username'");
             $result['status'] = 1;
            $result['message'] =  "Reset was successful";
        } 
}else{
    $result['status'] = 0;
    $result['message'] = "Account not found";
}
}
echo json_encode($result);
mysqli_close($con);