<?php
include('../database/conn.php');
    $slf = $_POST['slf'];
    $price = $_POST['price'];
    $status = $_POST['status'];
    $quantity = $_POST['quantity'];
    $id = $_POST['id'];
    if($status=='Approved'){
        if (mysqli_query($con, "UPDATE self_ord set price='$price',status='$status' where slf='$slf'")) {
            mysqli_query($con, "UPDATE raw set quantity='$quantity' where id='$id'");
            $response["success"] = 1;
            $response["message"] = " Approval was succesful";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to update";
        }
    }else{
        if (mysqli_query($con, "UPDATE self_ord set status='$status' where slf='$slf'")) {
            $response["success"] = 1;
            $response["message"] = " Rejection was succesful";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to update";
        }
    }
       
    echo json_encode($response);
    mysqli_close($con);