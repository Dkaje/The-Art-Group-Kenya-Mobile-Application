<?php
include('../database/conn.php');
    $category = $_POST['category'];
    $description = $_POST['description'];
    $type=$_POST['type'];
    $reg_date=$_POST['reg_date'];
    $charge=$_POST['charge'];
    $custid = $_POST['custid'];
    $name = $_POST['name'];
    $phone = $_POST['phone'];
    $landmark = $_POST['landmark'];
    $location = $_POST['location'];
    if (mysqli_num_rows(mysqli_query($con, "SELECT * from service where custid='$custid' and status='Pending'")) > 0) {
        $response["success"] = 0;
        $response["message"] = "Failed!! You a pending service order";
    } elseif (mysqli_num_rows(mysqli_query($con, "SELECT * from service where custid='$custid' and status='Approved' and pay='Pending'")) > 0) {
        $response["success"] = 0;
        $response["message"] = "Failed!! You an approved but UNPAID service order";
    }elseif (mysqli_query($con,"INSERT INTO service(category,type,description,reg_date,custid,name,phone,location,landmark,charge)
        VALUES('$category','$type','$description','$reg_date','$custid','$name','$phone','$location','$landmark','$charge')")) {
            $response["success"] = 1;
            $response["message"] = " service submitted successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to submit";
        }
    echo json_encode($response);
    mysqli_close($con);