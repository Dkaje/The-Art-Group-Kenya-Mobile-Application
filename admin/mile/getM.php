<?php
include('../database/conn.php');
    $response = mysqli_query($con,"SELECT * FROM volume");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['amount'] = $row['amount'];
            $index['ready'] = $row['ready'];
            $index['custom'] = $row['custom'];
            $index['service'] = $row['service'];
            $index['supplier'] = $row['supplier'];
            $index['balance'] = $row['balance'];
            $index['udate'] = $row['udate'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Account Record was Found";
    }
    echo json_encode($results);