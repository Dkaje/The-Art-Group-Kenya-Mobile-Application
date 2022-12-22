<?php
include('../database/conn.php');
$status=$_POST['status'];
    $response = mysqli_query($con,"SELECT * FROM service where status!='$status' order by reg_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['serv'] = $row['serv'];
            $index['custid'] = $row['custid'];
            $index['name'] = $row['name'];
            $index['phone'] = $row['phone'];
            $index['location'] = $row['location'];
            $index['landmark'] = $row['landmark'];
            $index['category'] = $row['category'];
            $index['type'] = $row['type'];
            $index['description'] = $row['description'];
            $index['charge'] = $row['charge'];
            $index['status'] = $row['status'];
            $index['pay'] = $row['pay'];
            $index['reg_date'] = $row['reg_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No posted services";
    }
    echo json_encode($results);