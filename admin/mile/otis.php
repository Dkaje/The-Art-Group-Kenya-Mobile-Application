<?php
include('../database/conn.php');
    $id = $_POST['id'];
    $video = $_POST['video'];
    $date = $_POST['date'];
        if (mysqli_query($con, "UPDATE delivery set video='$video',video_date='$date' where id='$id'")) {
            $response["success"] = 1;
            $response["message"] = " Confirmation was succesful";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to update";
        }
    echo json_encode($response);
    mysqli_close($con);