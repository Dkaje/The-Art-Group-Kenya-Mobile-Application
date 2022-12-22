<?php
include('../database/conn.php');
    $id = $_POST['id'];
    $custid = $_POST['custid'];
    $price = $_POST['price'];
    $quantity = $_POST['quantity'];
    $type = $_POST['type'];
    $category = $_POST['category'];
    $quant = $_POST['quant'];
    $date = $_POST['date'];
        if (mysqli_num_rows(mysqli_query($con, "SELECT * FROM cart WHERE product='$id' and category='$category' and type='$type' and entry='Pending' and custid='$custid'")) > 0) {
            if (mysqli_query($con, "UPDATE cart set quantity=(quantity+'$quantity') where product='$id' and category='$category' and type='$type' and entry='Pending' and custid='$custid'")) {
                if (mysqli_query($con, "UPDATE product set quantity='$quant' where id='$id'")) {
                    $response["status"] = 1;
                    $response["message"] = "Cart updated successfully";
                } else {
                    $response["status"] = 0;
                    $response["message"] = " Operation failed ";
                }
            }
        } else {
            $photo = mysqli_fetch_assoc(mysqli_query($con, "SELECT image as img from product where id='$id'"));
            if (mysqli_query($con, "INSERT INTO cart(custid,product,category,type,price,quantity,image,reg_date)
            VALUES('$custid','$id','$category','$type','$price','$quantity','$photo[img]','$date')")) {
                if (mysqli_query($con, "UPDATE product set quantity='$quant' where id='$id'")) {
                    $response["status"] = 1;
                    $response["message"] = "Product insert was successful";
                } else {
                    $response["status"] = 0;
                    $response["message"] = "Could not insert cart";
                }
            } else {
                $response["status"] = 0;
                $response["message"] = "An error Occurred";
            }
        }
    echo json_encode($response);
    mysqli_close($con);
