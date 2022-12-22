<?php
include('../database/conn.php');
$rec=$_POST['rec'];
$senid=$_POST['senid'];
    $feeder = mysqli_query($con,"SELECT * FROM feedback where rec='$rec' and senid='$senid' order by dater asc");
    if (mysqli_num_rows($feeder) > 0) {
        $wanawida['success'] = 1;
        $wanawida['victory'] = array();
        while ($row = mysqli_fetch_array($feeder)) {
            $index['sen'] = $row['sen'];
            $index['senid'] = $row['senid'];
            $index['phone'] = $row['phone'];
            $index['name'] = $row['name'];
            $index['rec'] = $row['rec'];
            $index['message'] = $row['message'];
            $index['timing'] = $row['timing'];
            $index['date'] = $row['date'];
            $index['temple'] = strtoupper($row['name'][0]);
            $index['temple2'] = strtoupper($row['rec'][0]);
            $index['meme']=$row['meme'];
            array_push($wanawida['victory'], $index);
        }
    } else {
        $wanawida['success'] = 0;
        $wanawida['mine'] = "No Messages";
    }
    echo json_encode($wanawida);