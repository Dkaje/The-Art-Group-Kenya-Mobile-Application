<?php
include('../database/conn.php');
$response=mysqli_query($con,"SELECT distinct supplier from supply where status='Approved' and pay='Pending'");
    if (mysqli_num_rows($response) > 0) {
        $cred=mysqli_fetch_assoc(mysqli_query($con,"SELECT distinct supplier as hi from supply where status='Approved' and pay!='Pending'"));
        $credes=mysqli_fetch_assoc(mysqli_query($con,"SELECT sum(quantity*price) as hi from supply where supplier='$cred[hi]' and status='Approved' and pay!='Pending'"));
        $acc=mysqli_fetch_assoc(mysqli_query($con,"SELECT pay as hi from supply where supplier='$cred[hi]' and status='Approved' and pay!='Pending'"));
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['creditor'] = $row['supplier'];
            $index['credit'] = $credes['hi'];
            $index['accredit'] = $acc['hi'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No record was Found";
    }
    echo json_encode($results);