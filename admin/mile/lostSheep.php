<?php
include('../database/conn.php');
    $sup = $_POST['sup'];
    $bid=$_POST["bid"];
    $quantity=$_POST["quantity"];
        $cate = mysqli_query($con, "UPDATE supply set status='Rejected' where sup='$sup'");
        if ($cate) {
            mysqli_query($con, "UPDATE bidding set quantity=(quantity+$quantity) where bid='$bid'");
            $response["success"] = 1;
            $response["message"] = "Rejection was sucessful";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to reject";
        }
    echo json_encode($response);
    mysqli_close($con);