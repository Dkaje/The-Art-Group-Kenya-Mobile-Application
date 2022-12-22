<?php
include('../database/conn.php');
    $bid = $_POST['bid'];
        if (mysqli_query($con, "DELETE from bidding where bid='$bid'")) {
            $response["success"] = 1;
            $response["message"] = " Deleted succesfully";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to delete";
        }
    echo json_encode($response);
    mysqli_close($con);