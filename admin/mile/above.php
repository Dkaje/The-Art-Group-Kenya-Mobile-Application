<?php
include('../database/conn.php');
    $refere = $_POST['refere'];
    $category = $_POST['category'];
    $cust_id = $_POST['cust_id'];
    $type = $_POST['type'];
    $profile = $_POST['image'];
    $filename = "IMG" . rand() . ".jpg";
    $reg_date = $_POST['reg_date'];
   if (mysqli_query($con,"INSERT INTO refer(refere,category,type,image,cust_id,reg_date)
        VALUES('$refere','$category','$type','$filename','$cust_id','$reg_date')")) {
            file_put_contents("images/" . $filename, base64_decode($profile));
            $response["success"] = 1;
            $response["message"] = " Image upload was Successful";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to upload Image";
        }
    echo json_encode($response);
    mysqli_close($con);