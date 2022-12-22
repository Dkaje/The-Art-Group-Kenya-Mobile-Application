<?php
include('../database/conn.php');
    $serv = $_POST['serv'];
        if (mysqli_query($con, "DELETE from service where serv='$serv'")) {
            $response["success"] = 1;
            $response["message"] = " Deleted succesfully";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to delete";
        }
    echo json_encode($response);
    mysqli_close($con);