<?php
include('../database/conn.php');
$cust_id=$_POST['cust_id'];
$category=$_POST['category'];
    $response = mysqli_query($con,"SELECT *FROM refer where category='$category' and cust_id='$cust_id' order by reg_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['category'] = $category;
            $index['type'] = $row['type'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No uploaded Images";
    }
    echo json_encode($results);