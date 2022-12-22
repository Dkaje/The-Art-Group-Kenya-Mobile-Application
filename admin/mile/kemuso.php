<?php
include('../database/conn.php');
    $quota = $_POST["quota"];
    $id = $_POST["id"];
    $quantity = $_POST["quantity"];
    $reg = $_POST["reg"];
    $photo = mysqli_fetch_assoc(mysqli_query($con, "SELECT quantity as img from product where id='$id'"));
    $older=round((($photo['img'])+$quantity),0);
    if($quota>$older){
        $response["status"] = 6;
        $response["message"] = "Reduce Quantity";
    }else{
        $new=round(($older-$quota),0);
        if((mysqli_query($con, "UPDATE cart set quantity='$quota' where reg='$reg'")) & (mysqli_query($con, "UPDATE product set quantity='$new' where id='$id'"))){
            $response["status"] = 1;
            $response["message"] = "Cart modified successfully";
        }else {
            $response["status"] = 0;
            $response["message"] = " Failed to modify cart";
        }
    }
    echo json_encode($response);
    mysqli_close($con);