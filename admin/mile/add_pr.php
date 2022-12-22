<?php
include('../database/conn.php');
$classification = $_POST['classification'];
    $category = $_POST['category'];
    $description = $_POST['description'];
    $quantity = $_POST['quantity'];
    $qty = $_POST['qty'];
    $bid = $_POST['bid'];
    $supplier = $_POST['supplier'];
    $type = $_POST['type'];
    $price = $_POST['price'];
    $profile = $_POST['image'];
    $filename = "IMG" . rand() . ".jpg";
    $reg_date = $_POST['reg_date'];
   
    $select = mysqli_query($con, "SELECT * from supply where classification='$classification' and category='$category' and type='$type' and price='$price' and supplier='$supplier' and status='Pending'");
    if (mysqli_num_rows($select) > 0) {
        $cate = mysqli_query($con, "UPDATE supply set description='$description', image='$filename',quantity=(quantity+$quantity) where classification='$classification' and category='$category' and type='$type' and price='$price' and supplier='$supplier' and status='Pending'");
        if ($cate) {
            file_put_contents("images/" . $filename, base64_decode($profile));
            mysqli_query($con,"UPDATE bidding set quantity=$qty where bid='$bid'");
            $response["success"] = 1;
            $response["message"] = "  update was succesful";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to update";
        }
    } else {
        $sql = "INSERT INTO supply(classification,category,type,bid,quantity,price,description,image,supplier,reg_date)
        VALUES('$classification','$category','$type','$bid','$quantity','$price','$description','$filename','$supplier','$reg_date')";
        if (mysqli_query($con, $sql)) {
            file_put_contents("images/" . $filename, base64_decode($profile));
            mysqli_query($con,"UPDATE bidding set quantity=$qty where bid='$bid'");
            $response["success"] = 1;
            $response["message"] = " Successful";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to upload product";
        }
    }
    echo json_encode($response);
    mysqli_close($con);