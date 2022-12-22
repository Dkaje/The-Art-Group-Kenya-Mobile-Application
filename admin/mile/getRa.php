<?php
include('../database/conn.php');
    $sup = $_POST['sup'];
    $quantity = $_POST['quantity'];
    $status = 'Approved';
    $description = $_POST['description'];
    $category = $_POST['category'];
    $type = $_POST['type'];
    $price = $_POST['price'];
    $reg_date = $_POST['date'];
        $cate = mysqli_query($con, "UPDATE supply set status='$status' where sup='$sup'");
        if ($cate) {
            if (mysqli_num_rows(mysqli_query($con, "SELECT * from raw where category='$category' and type='$type'")) > 0) {
                if (mysqli_query($con, "UPDATE raw set description='$description',qty=(qty+$quantity),quantity=(quantity+$quantity),price='$price' where category='$category' and type='$type'")) {
                    $response["success"] = 1;
                    $response["message"] = "  update was successful";
                } else {
                    $response["success"] = 0;
                    $response["message"] = " Failed to update";
                }
            } else {
            $image = mysqli_query($con, "SELECT image as img from supply where sup='$sup'");
            $photo = mysqli_fetch_assoc($image);
            if (mysqli_query($con,"INSERT INTO raw(category,type,description,image,qty,quantity,price,reg_date)
        VALUES('$category','$type','$description','$photo[img]','$quantity','$quantity','$price','$reg_date')")){
$response["success"] = 1;
$response["message"] = "Successful";
} else {
$response["success"] = 0;
$response["message"] = "  Operation failed";
}
        }

}
echo json_encode($response);
mysqli_close($con);