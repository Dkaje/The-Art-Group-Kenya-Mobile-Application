<?php
include('../database/conn.php');
    $category = $_POST['category'];
    $description = $_POST['description'];
    $quantity = $_POST['quantity'];
    $size = $_POST['size'];
    $price = $_POST['price'];
    $rgb = $_POST['rgb'];
    $hexa = $_POST['hexa'];
    $red = $_POST['red'];
    $green = $_POST['green'];
    $blue = $_POST['blue'];
    $motive = $_POST['motive'];
    $phone = $_POST['phone'];
    $custid = $_POST['custid'];
    $type = $_POST['type'];
    $name = $_POST['name'];
    $date = $_POST['date']; 
    $dsc = $_POST['dsc'];
    if(mysqli_num_rows(mysqli_query($con,"SELECT * from self_ord where custid='$custid' and (status='Pending' or (status='Approved' and pay='Pending'))"))){
        $response["success"] = 0;
        $response["message"] = " Failed!! It seems you have another order request with pending status";
    }else{
    if($dsc=="7"){
        if($motive==1){
            if (mysqli_query($con,"INSERT INTO self_ord(category,type,dsc,quantity,size,description,image,custid,name,phone,date,rgb,hexa,red,green,blue,motive,price)
            VALUES('$category','$type','$dsc','$quantity','$size','$description','No','$custid','$name','$phone','$date','$rgb','$hexa','$red','$green','$blue','$motive','$price')")) {
                $response["success"] = 1;
                $response["message"] = "Request sent Successfully";
            } else {
                $response["success"] = 0;
                $response["message"] = "  Failed to send";
            }
        }else{
            if (mysqli_query($con,"INSERT INTO self_ord(category,type,dsc,quantity,size,description,image,custid,name,phone,date,motive,price)
            VALUES('$category','$type','$dsc','$quantity','$size','$description','No','$custid','$name','$phone','$date','$motive','$price')")) {
                $response["success"] = 1;
                $response["message"] = "Request sent Successfully";
            } else {
                $response["success"] = 0;
                $response["message"] = "  Failed to send";
            }
        }
        
    }elseif($dsc=="8"){
        $profile = $_POST['image'];
        $filename = "IMG" . rand() . ".jpg";
        if($motive==1){
            if (mysqli_query($con,"INSERT INTO self_ord(category,type,dsc,quantity,size,description,image,custid,name,phone,date,rgb,hexa,red,green,blue,motive,price)
            VALUES('$category','$type','$dsc','$quantity','$size','$description','$filename','$custid','$name','$phone','$date','$rgb','$hexa','$red','$green','$blue','$motive','$price')")) {
                file_put_contents("images/" . $filename, base64_decode($profile));
                $response["success"] = 1;
                $response["message"] = "Request sent Successfully";
            } else {
                $response["success"] = 0;
                $response["message"] = "  Failed to send";
            }
        }else{
            if (mysqli_query($con,"INSERT INTO self_ord(category,type,dsc,quantity,size,description,image,custid,name,phone,date,motive,price)
        VALUES('$category','$type','$dsc','$quantity','$size','$description','$filename','$custid','$name','$phone','$date','$motive','$price')")) {
            file_put_contents("images/" . $filename, base64_decode($profile));
            $response["success"] = 1;
            $response["message"] = "Request sent Successfully";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to send";
        }
        }
        
    }
}
    echo json_encode($response);
    mysqli_close($con);