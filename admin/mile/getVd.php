<?php
include('../database/conn.php');
    $response = mysqli_query($con,"SELECT * FROM staff where role='Videographer' and status='Approved' order by update_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['entry'] = $row['entry'];
            $index['fname'] = $row['fname'].' '.$row['lname'];
            $index['phone'] = $row['phone'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Driver was Found";
    }
    echo json_encode($results);