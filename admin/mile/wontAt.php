<?php
include('../database/conn.php');
    $disb = $_POST["disb"];
    $payid = $_POST["payid"];
    $form = $_POST["form"];
    $driver_id = $_POST["driver_id"];
    $driver_name = $_POST["driver_name"];
    $driver_phone = $_POST["driver_phone"];
    $cust_id = $_POST["cust_id"];
    $cust_name = $_POST["cust_name"];
    $cust_phone = $_POST["cust_phone"];
    $location = $_POST["location"];
    $landmark = $_POST["landmark"];
    $date = $_POST["date"];
    if (mysqli_query($con, "UPDATE self_pay set disb='$disb' where slf='$payid'")) {
        mysqli_query($con,"INSERT into delivery(entry,form,driver_id,driver_name,driver_phone,cust_id,cust_name,cust_phone,location,landmark,reg_date)
        values('$payid','$form','$driver_id','$driver_name','$driver_phone','$cust_id','$cust_name','$cust_phone','$location','$landmark','$date')");
        $response["success"] = 1;
        $response["message"] = "Driver was added successfully";
    } else {
        $response["success"] = 0;
        $response["message"] = "Failed to add driver";
    }
    echo json_encode($response);
    mysqli_close($con);