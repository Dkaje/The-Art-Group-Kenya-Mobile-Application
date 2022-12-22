<?php
include('../database/conn.php');
$drive=$_POST["drive"];
$cust_id=$_POST["cust_id"];
$form=$_POST["form"];
$video=$_POST["video"];
    $response = mysqli_query($con,"SELECT * FROM delivery where form='$form' and video='$video' and cust_id='$cust_id' and drive='$drive' order by reg_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['id'] = $row['id'];
            $index['form'] = $row['form'];
            $index['entry'] = $row['entry'];
            $index['driver_id'] = $row['driver_id'];
            $index['driver_name'] = $row['driver_name'];
            $index['driver_phone'] = $row['driver_phone'];
            $index['drive'] = $row['drive'];
            $index['drive_date'] = $row['drive_date'];
            $index['video_id'] = $row['video_id'];
            $index['video_name'] = $row['video_name'];
            $index['video_phone'] = $row['video_phone'];
            $index['video'] = $row['video'];
            $index['video_date'] = $row['video_date'];
            $index['cust_id'] = $row['cust_id'];
            $index['cust_name'] = $row['cust_name'];
            $index['cust_phone'] = $row['cust_phone'];
            $index['location'] = $row['location'];
            $index['landmark'] = $row['landmark'];
            $index['custom'] = $row['custom'];
            $index['custom_date'] = $row['custom_date'];
            $index['reg_date'] = $row['reg_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Record was Found";
    }
    echo json_encode($results);