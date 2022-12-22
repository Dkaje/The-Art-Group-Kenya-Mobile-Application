<?php
include('../database/conn.php');
    $supplier=$_POST["supplier"];
    $query = "SELECT * FROM supply where supplier='$supplier' order by reg_date desc";
    $response = mysqli_query($con, $query);
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['sup'] = $row['sup'];
            $index['bid'] = $row['bid'];
            $index['classification'] = $row['classification'];
            $index['category'] = $row['category'];
            $index['type'] = $row['type'];
            $index['quantity'] = $row['quantity'];
            $index['price'] = $row['price'];
            $index['description'] = $row['description'];
            $index['image'] = $row['image'];
            $index['supplier'] = $row['supplier'];
            $index['status'] = $row['status'];
            $index['pay'] = $row['pay'];
            $index['reg_date'] = $row['reg_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No products";
    }
    echo json_encode($results);