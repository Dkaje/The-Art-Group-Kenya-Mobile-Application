<?php
include('../database/conn.php');
function test_input($data)
{
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data; 
}
    $mpesa = test_input($_POST["mpesa"]);
    $amount = $_POST['amount'];
    $category = $_POST['category'];
    $type = $_POST['type'];
    $description = $_POST['description'];
    $serv = $_POST['serv'];
    $custid = $_POST['custid'];
    $pay=$_POST['pay'];
    $name = $_POST['name'];
    $phone = $_POST['phone'];
    $landmark = $_POST['landmark'];
    $location = $_POST['location'];
    $reg_date = $_POST['reg_date'];
    $yia = date("Y");
    $per = '0DEFGHI3456JKLMNOPQRSTU12789ABCVWXYZ';
    $newS = substr(str_shuffle($per), 0, 4);
    $naming = "/^[A-Z0-9]{10,}$/";
    $caps = "/^[A-Z]{10,}$/";
    $nums = "/^[0-9]{10,}$/";
    if (mysqli_num_rows(mysqli_query($con,"SELECT * FROM ser_pay WHERE serv='$serv' and status=0")) > 0) {
        $response["success"] = 0;
        $response["message"] = "Failed!! You have another pending Order";
    }else{
        if ((preg_match($caps, $mpesa))||(preg_match($nums, $mpesa))) {
            $response["success"] = 6;
            $response["message"] = "Invalid MPESA code";
        } else {
            if (mysqli_num_rows(mysqli_query($con,"SELECT mpesa FROM payment WHERE mpesa='$mpesa'")) > 0) {
                $response["success"] = 6;
                $response["message"] = "MPESA code not accepted";
            } elseif (mysqli_num_rows(mysqli_query($con,"SELECT mpesa FROM ser_pay WHERE mpesa='$mpesa'")) > 0) {
                $response["success"] = 6;
                $response["message"] = "MPESA code not accepted";
            }else{
            $hits = file("pend.txt");
            $hits[0]++;
            $fp = fopen("pend.txt", "w");
            fputs($fp, "$hits[0]");
            fclose($fp);
            $reg = $hits[0];
            $entry = $reg . $newS . $yia;
            if (mysqli_query($con,"INSERT INTO ser_pay(serid,description,mpesa,amount,category,type,serv,custid,name,phone,location,landmark,reg_date)
                VALUES('$entry','$description','$mpesa','$amount','$category','$type','$serv','$custid','$name','$phone','$location','$landmark','$reg_date')")) {
                    mysqli_query($con, "UPDATE service set pay='$pay' where serv='$serv'");
                    $response["success"] = 1;
                    $response["message"] = "Order made successfully";
                } else {
                    $response["success"] = 0;
                    $response["message"] = "Failed to order";
                }
            }
        }
    }
    echo json_encode($response);
    mysqli_close($con);