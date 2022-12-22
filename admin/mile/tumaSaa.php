<?php
include('../database/conn.php');
$mess=$_POST["message"];
$phone=$_POST["phone"];
$sen=$_POST["sen"];
$rec=$_POST['rec'];
$senid=$_POST["senid"];
$meme=$_POST["meme"];
$date=$_POST["date"];
$dater=$_POST["dater"];
$timing=$_POST["timing"];
$name=$_POST["name"];
if(mysqli_query($con,"INSERT into feedback(message,rec,meme,phone,sen,senid,dater,timing,date,name)
values('$mess','$rec','$meme','$phone','$sen','$senid','$dater','$timing','$date','$name')")){
    $res["success"]=1;
    $res["message"]="Message sent";
}else{
    $res["success"]=0;
    $res["message"]="Failed to send";
}
echo json_encode($res);