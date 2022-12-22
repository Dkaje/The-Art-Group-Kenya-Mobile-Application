<?php
include('../database/conn.php');
    $quantity = $_POST["quantity"];
    $id = $_POST["id"];
    $reg = $_POST["reg"];
    if (mysqli_query($con, "DELETE from cart where reg='$reg'")) {
        mysqli_query($con, "UPDATE product set quantity=(quantity+$quantity) where id='$id'");
        $response["status"] = 1;
        $response["message"] = "Item removed successfully";
    } else {
        $response["status"] = 0;
        $response["message"] = " Failed to drop item";
    }
    echo json_encode($response);
    mysqli_close($con);