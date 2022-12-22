<?php
include('../database/conn.php');
    $custid = $_POST["custid"];
    $response = mysqli_query($con,"SELECT * FROM cart where entry='Pending' and custid='$custid' order by reg_date desc");

    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['reg'] = $row['reg'];
            $index['entry'] = $row['entry'];
            $index['custid'] = $row['custid'];
            $index['product'] = $row['product'];
            $index['category'] = $row['category'];
            $index['type'] = $row['type'];
            $index['price'] = $row['price'];
            $index['quantity'] = $row['quantity'];
            $index['image'] = $row['image'];
            $index['status'] = $row['status'];
            $index['reg_date'] = $row['reg_date'];
            array_push($results['victory'], $index);
        }
    } else {

        $results['trust'] = 0;
        $results['mine'] = "No Item";
    }
    echo json_encode($results);