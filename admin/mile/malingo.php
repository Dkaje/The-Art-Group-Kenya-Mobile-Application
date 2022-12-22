<?php
include('../database/conn.php');
    $status = $_POST["status"];
    $response = mysqli_query($con,"SELECT * FROM payment where status='$status' order by reg_date asc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['payid'] = $row['payid'];
            $index['entry'] = $row['entry'];
            $index['mpesa'] = $row['mpesa'];
            $index['amount'] = $row['amount'];
            $index['orders'] = $row['orders'];
            $index['ship'] = $row['ship'];
            $index['custid'] = $row['custid'];
            $index['name'] = $row['name'];
            $index['phone'] = $row['phone'];
            $index['location'] = $row['location'];
            $index['landmark'] = $row['landmark'];
            if ($row['status'] == 0) {
                $index['status'] = 'Pending';
            } elseif ($row['status'] == 1) {
                $index['status'] = 'Approved';
            } else {
                $index['status'] = 'Rejected';
            }
            $index['comment'] = $row['comment'];
            if ($row['disb'] == 0) {
                $index['disb'] = 'Pending';
            }else {
                $index['disb'] = 'Disbursed';
            }
            $index['reg_date'] = $row['reg_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Payment Record";
    }
    echo json_encode($results);