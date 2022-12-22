<?php
include('../database/conn.php');
    $bid = $_POST['bid'];
    $quantity = $_POST['quantity'];
    $date = $_POST['date'];
        if (mysqli_query($con, "UPDATE bidding set qty=$quantity,quantity=$quantity,update_date='$date' where bid='$bid'")) {
            $response["success"] = 1;
            $response["message"] = " update was succesful";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to update";
        }
    echo json_encode($response);
    mysqli_close($con);