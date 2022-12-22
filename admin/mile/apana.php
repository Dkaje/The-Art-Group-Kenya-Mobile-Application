<?php
include('../database/conn.php');
    $serv = $_POST['serv'];
    $description = $_POST['description'];
    $charge = $_POST['charge'];
        if (mysqli_query($con, "UPDATE service set description='$description',charge='$charge' where serv='$serv'")) {
            $response["success"] = 1;
            $response["message"] = " update was succesful";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to update";
        }
    echo json_encode($response);
    mysqli_close($con);