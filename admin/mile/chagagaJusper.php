<?php
include('../database/conn.php');
$cust_id=$_POST['cust_id'];
    $response = mysqli_query($con,"SELECT * FROM notice where cust_id='$cust_id' order by reg_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['id'] = $row['id'];
            $index['cust_id'] = $row['cust_id'];
            $index['alert'] = $row['alert'];
            $index['reg_date'] = $row['reg_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Alerts";
    }
    echo json_encode($results);