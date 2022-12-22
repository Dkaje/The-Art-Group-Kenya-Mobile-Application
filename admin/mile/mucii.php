<?php
include('../database/conn.php');
    $comment = $_POST["comment"];
    $payid = $_POST["payid"]; 
    $status = $_POST["status"];
    $custom = $_POST["amount"];
    $date = $_POST["date"];
    $slf = $_POST["slf"];
    $fina = $_POST["fina"];
    if($status==1){
        if (mysqli_query($con, "UPDATE self_pay set status='$status',comment='$comment' where payid='$payid'")) {
            mysqli_query($con, "UPDATE self_ord set fina='$fina' where slf='$slf'");
            if(mysqli_num_rows(mysqli_query($con,"SELECT * from volume"))>0){
                if(mysqli_query($con,"UPDATE volume set amount=(amount+$custom),custom=(custom+$custom),balance=(balance+$custom),udate='$date'")){
                    $response["success"] = 1;
                    $response["message"] = "Payment Approved successfully";
                }else{
                    $response["success"] = 1;
                    $response["message"] = "Failed to approve payment";
                }
            }else{
                if(mysqli_query($con,"INSERT into volume(amount,custom,balance,date,udate)values('$custom','$custom','$custom','$date','$date')")){
                    $response["success"] = 1;
                    $response["message"] = "Payment Approved successfully";
                }else{
                    $response["success"] = 1;
                    $response["message"] = "Failed to approve payment";
                }
            }
        }
    }else{
        if (mysqli_query($con, "UPDATE self_pay set status='$status',comment='$comment' where payid='$payid'")) {
            mysqli_query($con, "UPDATE self_ord set fina='$fina' where slf='$slf'");
            $response["success"] = 1;
            $response["message"] = "Payment Rejected successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "Failed to reject payment";
        }
    }
    echo json_encode($response);
    mysqli_close($con);