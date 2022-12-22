<?php
include('../database/conn.php');
$cust_id=$_POST['cust_id'];
    $response = mysqli_query($con,"SELECT distinct category FROM refer where cust_id='$cust_id' order by reg_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['category'] = $row['category'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No uploaded Images";
    }
    echo json_encode($results);