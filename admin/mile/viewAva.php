<?php
include('../database/conn.php');
$classification=$_POST['classification'];
    $query = "SELECT * FROM bidding where classification='$classification' and quantity!=0 order by entry_date desc";
    $response = mysqli_query($con, $query);
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['bid'] = $row['bid'];
            $index['classification'] = $row['classification'];
            $index['category'] = $row['category'];
            $index['type'] = $row['type'];
            $index['qty'] = $row['qty'];
            $index['quantity'] = $row['quantity'];
            $index['entry_date'] = $row['entry_date'];
            $index['update_date'] = $row['update_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No posted bids";
    }
    echo json_encode($results);