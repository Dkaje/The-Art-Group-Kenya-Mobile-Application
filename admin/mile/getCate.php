<?php
include('../database/conn.php');
    $response = mysqli_query($con,"SELECT distinct category FROM product where quantity!=0 order by reg_date asc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['category'] = $row['category'];
            $push = mysqli_fetch_assoc(mysqli_query($con, "SELECT image as img from product where category='$row[category]'"));
            $index['image'] = $push['img'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Product";
    }
    echo json_encode($results);