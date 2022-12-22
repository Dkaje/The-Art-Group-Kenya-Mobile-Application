<?php
include('../database/conn.php');
    $category = $_POST['category'];
    $charge = $_POST['charge'];
    $description = $_POST['description'];
    $type=$_POST['type'];
    $reg_date=$_POST['reg_date'];
    if (mysqli_num_rows(mysqli_query($con, "SELECT * from service where category='$category' and type='$type'")) > 0) {
        $response["success"] = 0;
        $response["message"] = "  Failed!! you cannot overwite an existing service";
    } else {
        if (mysqli_query($con,"INSERT INTO service(category,type,description,charge,reg_date)
        VALUES('$category','$type','$description','$charge','$reg_date')")) {
            $response["success"] = 1;
            $response["message"] = " service uploaded successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to upload";
        }
    }
    echo json_encode($response);
    mysqli_close($con);