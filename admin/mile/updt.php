<?php
include('../database/conn.php');
    $id = $_POST['id'];
    $custom = $_POST['custom'];
    $date = $_POST['date'];
        if (mysqli_query($con, "UPDATE delivery set custom='$custom',custom_date='$date' where id='$id'")) {
            $response["success"] = 1;
            $response["message"] = " Delivery was succesful";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to update";
        }
    echo json_encode($response);
    mysqli_close($con);