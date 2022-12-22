<?php
include('../database/conn.php');
    $classification = $_POST['classification'];
    $category = $_POST['category'];
    $quantity = $_POST['quantity'];
    $type=$_POST['type'];
    $date=$_POST['date'];
    $select = mysqli_query($con, "SELECT * from bidding where classification='$classification' and category='$category' and type='$type'");
    if (mysqli_num_rows($select) > 0) {
        $cate = mysqli_query($con, "UPDATE bidding set qty=(qty+$quantity),quantity=(quantity+$quantity),update_date='$date' where classification='$classification' and category='$category'and type='$type'");
        if ($cate) {
            $response["success"] = 1;
            $response["message"] = " publish was succesful";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to update";
        }
    } else {
        $sql = "INSERT INTO bidding(classification,category,type,qty,quantity,entry_date,update_date)
        VALUES('$classification','$category','$type',$quantity,'$quantity','$date','$date')";
        if (mysqli_query($con, $sql)) {
            $response["success"] = 1;
            $response["message"] = " publish successful";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to publish bidding";
        }
    }
    echo json_encode($response);
    mysqli_close($con);