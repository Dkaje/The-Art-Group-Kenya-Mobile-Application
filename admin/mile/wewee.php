<?php
include('../database/conn.php');
    $comment = $_POST["comment"];
    $serid = $_POST["serid"]; 
    $status = $_POST["status"];
    $service = $_POST["amount"];
    $date = $_POST["date"];
    if($status==1){
        if (mysqli_query($con, "UPDATE ser_pay set status='$status',comment='$comment' where serid='$serid'")) {
            if(mysqli_num_rows(mysqli_query($con,"SELECT * from volume"))>0){
                if(mysqli_query($con,"UPDATE volume set amount=(amount+$service),service=(service+$service),balance=(balance+$service),udate='$date'")){
                    $response["success"] = 1;
                    $response["message"] = "Payment Approved successfully";
                }else{
                    $response["success"] = 1;
                    $response["message"] = "Failed to approve payment";
                }
            }else{
                if(mysqli_query($con,"INSERT into volume(amount,service,balance,date,udate)values('$service','$service','$service','$date','$date')")){
                    $response["success"] = 1;
                    $response["message"] = "Payment Approved successfully";
                }else{
                    $response["success"] = 1;
                    $response["message"] = "Failed to approve payment";
                }
            }
        }
    }else{
        if (mysqli_query($con, "UPDATE ser_pay set status='$status',comment='$comment' where serid='$serid'")) {
            $response["success"] = 1;
            $response["message"] = "Payment Rejected successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "Failed to reject payment";
        }
    }
    echo json_encode($response);
    mysqli_close($con);