<?php
include('../database/conn.php');
    $slf = $_POST['slf'];
    $image_desgn = $_POST['image_desgn'];
    $filename = "IMG" . rand() . ".jpg";
    $design = $_POST['design'];
        if(mysqli_query($con,"UPDATE self_ord set image_desgn='$filename',design='$design' where slf='$slf'")){
            mysqli_query($con,"UPDATE self_pay set design='$design' where slf='$slf'"); 
            file_put_contents("images/" . $filename, base64_decode($image_desgn));
            $response["success"] = 1;
            $response["message"] = "Request sent Successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to send";
        }
    echo json_encode($response);
    mysqli_close($con);