<?php
include('../database/conn.php');
    $query = "SELECT distinct classification FROM bidding where quantity!=0 order by entry_date desc";
    $response = mysqli_query($con, $query);
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['classification'] = $row['classification'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No posted bids";
    }
    echo json_encode($results);