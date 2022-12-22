<?php
include('../database/conn.php');
    $refere = $_POST["refere"];
    $cust_id = $_POST["cust_id"];
    if (mysqli_num_rows(mysqli_query($con,"SELECT * FROM refer where refere='$refere' and cust_id='$cust_id'")) > 1) {
        $results['trust'] = 1;
        $results['mine'] = "Yes Good to go";
    } else {
        $results['trust'] = 0;
        $results['mine'] = "You must add at least tow images!";
    }
    echo json_encode($results);