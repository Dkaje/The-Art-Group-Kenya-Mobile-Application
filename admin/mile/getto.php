<?php
include('../database/conn.php');
$fina=$_POST["fina"];
$design=$_POST["design"];
    $response = mysqli_query($con,"SELECT * FROM self_ord where fina='$fina' and design='$design' order by date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['slf'] = $row['slf'];
            $index['dsc'] = $row['dsc'];
            $index['custid'] = $row['custid'];
            $index['name'] = $row['name'];
            $index['phone'] = $row['phone'];
            $index['category'] = $row['category'];
            $index['type'] = $row['type'];
            $index['description'] = $row['description'];//691620
            $index['image'] = $row['image'];//668850
            $index['size'] = $row['size'];
            $index['rgb'] = $row['rgb'];
            $index['hexa'] = $row['hexa'];
            $index['red'] = $row['red'];
            $index['green'] = $row['green'];
            $index['blue'] = $row['blue'];
            $index['motive'] = $row['motive'];
            $index['quantity'] = $row['quantity'];
            $index['price'] = $row['price'];
            $index['status'] = $row['status'];
            $index['pay'] = $row['pay'];
            $index['fina'] = $row['fina'];
            $index['image_desgn'] = $row['image_desgn'];
            $index['design'] = $row['design'];
            $index['date'] = $row['date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No requests";
    }
    echo json_encode($results);