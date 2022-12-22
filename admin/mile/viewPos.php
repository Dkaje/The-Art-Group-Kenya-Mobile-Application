<?php
include('../database/conn.php');
$category=$_POST['category'];
$type=$_POST['type'];
    $response = mysqli_query($con,"SELECT * FROM raw where category='$category' and type='$type' order by reg_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['id'] = $row['id'];
            $index['category'] = $row['category'];
            $index['type'] = $row['type'];
            $index['description'] = $row['description'];
            $index['image'] = $row['image'];
            $index['qty'] = $row['qty'];
            $index['quantity'] = $row['quantity'];
            $index['price'] = $row['price'];
            $index['reg_date'] = $row['reg_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No raw material";
    }
    echo json_encode($results);