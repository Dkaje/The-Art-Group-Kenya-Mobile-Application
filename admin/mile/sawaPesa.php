<?php
include('../database/conn.php');
$supplier = $_POST['supplier'];
$response = mysqli_query($con,"SELECT * FROM paysup WHERE supplier='$supplier'");
if (mysqli_num_rows($response) > 0) {
    $results['trust'] = 1;
    $results['victory'] = array();
    while ($row = mysqli_fetch_array($response)) {
        $index['mpesa'] = $row['mpesa'];
        $index['supplier'] = $row['supplier'];
        $index['fullname'] = $row['fullname'];
        $index['phone'] = $row['phone'];
        $index['amount'] = $row['amount'];
        $index['date'] = $row['date'];
        array_push($results['victory'], $index);
    } 
} else {
    $results['trust'] = 0;
    $results['mine'] = "No Record Found";
}
echo json_encode($results);