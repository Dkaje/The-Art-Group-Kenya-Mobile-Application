<?php
include('../database/conn.php');
$custid = $_POST['custid'];
if (mysqli_num_rows( mysqli_query($con,"SELECT * FROM cart WHERE custid='$custid' and status='Pending'")) > 0) {
    $total = mysqli_fetch_assoc(mysqli_query($con, "SELECT SUM(quantity*price) as orders from cart where custid='$custid' and status='Pending'"));
    $results['trust'] = 1;
    $results['victory'] = array();
    $index['orders'] = $total['orders'];
    $index['shipping'] = '310';
    $index['total'] = $total['orders'] + 310;
    array_push($results['victory'], $index);
} else {
    $results['trust'] = 0;
    $results['mine'] = "No items";
}
echo json_encode($results);