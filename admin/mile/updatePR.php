<?php
include('../database/conn.php');
    $id = $_POST['id'];
    $price = $_POST['price'];
        if (mysqli_query($con, "UPDATE product set price='$price' where id='$id'")) {
            $response["success"] = 1;
            $response["message"] = " update was succesful";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to update";
        }
    echo json_encode($response);
    mysqli_close($con);