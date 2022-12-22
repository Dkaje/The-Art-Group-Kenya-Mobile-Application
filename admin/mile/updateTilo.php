<?php
include('../database/conn.php');
    $comment = $_POST["comment"];
    $payid = $_POST["payid"]; 
    $status = $_POST["status"];
    $ready = $_POST["amount"];
    $date = $_POST["date"];
    if($status==1){
        if (mysqli_query($con, "UPDATE payment set status='$status',comment='$comment' where payid='$payid'")) {
            if(mysqli_num_rows(mysqli_query($con,"SELECT * from volume"))>0){
                if(mysqli_query($con,"UPDATE volume set amount=(amount+$ready),ready=(ready+$ready),balance=(balance+$ready),udate='$date'")){
                    $response["success"] = 1;
                    $response["message"] = "Payment Approved successfully";
                }else{
                    $response["success"] = 1;
                    $response["message"] = "Failed to approve payment";
                }
            }else{
                if(mysqli_query($con,"INSERT into volume(amount,ready,balance,date,udate)values('$ready','$ready','$ready','$date','$date')")){
                    $response["success"] = 1;
                    $response["message"] = "Payment Approved successfully";
                }else{
                    $response["success"] = 1;
                    $response["message"] = "Failed to approve payment";
                }
            }
        }
    }else{
        if (mysqli_query($con, "UPDATE payment set status='$status',comment='$comment' where payid='$payid'")) {
            $response["success"] = 1;
            $response["message"] = "Payment Rejected successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "Failed to reject payment";
        }
    }
    echo json_encode($response);
    mysqli_close($con);