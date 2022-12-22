<?php
include('../database/conn.php');
    $disb = $_POST["disb"];
    $serid = $_POST["serid"];
    $form = $_POST["form"];
    $driver_id = $_POST["driver_id"];
    $driver_name = $_POST["driver_name"];
    $driver_phone = $_POST["driver_phone"];
    $video_id = $_POST["video_id"];
    $video_name = $_POST["video_name"]; 
    $video_phone = $_POST["video_phone"];
    $cust_id = $_POST["cust_id"];
    $cust_name = $_POST["cust_name"];
    $cust_phone = $_POST["cust_phone"];
    $location = $_POST["location"];
    $landmark = $_POST["landmark"];
    $alert = $_POST["alert"];
    $date = $_POST["date"];
    if (mysqli_query($con, "UPDATE ser_pay set disb='$disb' where serid='$serid'")) {
        mysqli_query($con,"INSERT into delivery(entry,form,driver_id,driver_name,driver_phone,video_id,video_name,video_phone,cust_id,cust_name,cust_phone,location,landmark,reg_date)
        values('$serid','$form','$driver_id','$driver_name','$driver_phone','$video_id','$video_name','$video_phone','$cust_id','$cust_name','$cust_phone','$location','$landmark','$date')");
        mysqli_query($con,"INSERT into notice(cust_id,alert,reg_date)values('$cust_id','$alert','$date')");
        $response["success"] = 1;
        $response["message"] = "Attachment was successful";
    } else {
        $response["success"] = 0;
        $response["message"] = "Failed to attach";
    }
    echo json_encode($response);
    mysqli_close($con);