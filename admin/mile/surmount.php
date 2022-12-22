<?php
include('../database/conn.php');
    $supplier = $_POST["supplier"];
    $mpesa = $_POST["mpesa"];
    $amount = $_POST["amount"];
    $phone = $_POST["phone"];
    $fullname = $_POST["fullname"];
    $date = $_POST["date"];
    $caps = "/^[A-Z]{10,}$/";
    $nums = "/^[0-9]{10,}$/";
    if (preg_match($caps, $mpesa)) {
        $response["success"] = 0;
        $response["message"] = "Your MPESA code is invalid";
    } elseif (preg_match($nums, $mpesa)) {
        $response["success"] = 0;
        $response["message"] = "Your MPESA code is invalid";
    } elseif(mysqli_num_rows(mysqli_query($con,"SELECT * from volume"))>0){
    $get=mysqli_fetch_assoc(mysqli_query($con,"SELECT balance as bal from volume"));
    $ava=$get['bal'];
    if($ava<$amount){
        $response["success"]=0;
        $response["message"]="Failed!! due to lack of funds";
    }else{
        $rem=round(($ava-$amount),0);
        if (mysqli_num_rows(mysqli_query($con,"SELECT mpesa FROM paysup WHERE mpesa='$mpesa'")) > 0) {
            $response["success"] = 0;
            $response["message"] = "MPESA not Accepted";
        } else {
                if (mysqli_query($con, "INSERT into paysup(fullname,phone,mpesa,supplier,amount,date)
                values('$fullname','$phone','$mpesa','$supplier','$amount','$date')")) {
                    if (mysqli_query($con,"UPDATE supply set pay='Paid' where supplier='$supplier' and status='Approved' and pay='Pending'")) {
                        mysqli_query($con,"UPDATE volume set supplier=(supplier+$amount),balance=(balance-$amount),udate='$date'");
                        $response["success"] = 1;
                        $response["message"] = "Supplier payment Successfully Disbursed";
                    } else {
                        $response["success"] = 0;
                        $response["message"] = " Failed to disburse";
                    }
                } else {
                    $response["success"] = 0;
                    $response["message"] = "An error occurred";
                }
            }
    }
}else{
    $response["success"] = 0;
    $response["message"] = "The system has low funds to commit payment!";
}
    
    echo json_encode($response);
    mysqli_close($con);