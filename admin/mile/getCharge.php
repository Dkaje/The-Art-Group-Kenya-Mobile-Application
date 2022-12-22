<?php
include('../database/conn.php');
    $serv = $_POST['serv'];
    $charge = $_POST['charge'];
    $status = $_POST['status'];
        if (mysqli_query($con, "UPDATE service set charge='$charge',status='$status' where serv='$serv'")) {
            $response["success"] = 1;
            $response["message"] = " update was succesful";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to update";
        }
    echo json_encode($response);
    mysqli_close($con);