<?php
include('../database/conn.php');
$rec=$_POST['rec'];
    $feeder = mysqli_query($con,"SELECT distinct senid FROM feedback where rec='$rec' order by dater desc");
    if (mysqli_num_rows($feeder) > 0) {
        $wanawida['trust'] = 1;
        $wanawida['victory'] = array();
        while ($row = mysqli_fetch_array($feeder)) {
            $index['senid'] = $row['senid'];
            $id = mysqli_fetch_assoc(mysqli_query($con, "SELECT max(id) as juakali from feedback where senid='$row[senid]' and rec='$rec'"));
            $count = mysqli_fetch_assoc(mysqli_query($con, "SELECT count(*) as juakali from feedback where senid='$row[senid]' and rec='$rec'"));
            $date = mysqli_fetch_assoc(mysqli_query($con, "SELECT date as juakali from feedback where senid='$row[senid]' and rec='$rec' and id='$id[juakali]'"));
            $sen = mysqli_fetch_assoc(mysqli_query($con, "SELECT sen as juakali from feedback where senid='$row[senid]' and rec='$rec' and id='$id[juakali]'"));
            $timing = mysqli_fetch_assoc(mysqli_query($con, "SELECT timing as juakali from feedback where senid='$row[senid]' and rec='$rec' and id='$id[juakali]'"));
            $message = mysqli_fetch_assoc(mysqli_query($con, "SELECT message as juakali from feedback where senid='$row[senid]' and rec='$rec' and id='$id[juakali]'"));
            $name = mysqli_fetch_assoc(mysqli_query($con, "SELECT name as juakali from feedback where senid='$row[senid]' and rec='$rec' and id='$id[juakali]'"));
            $phone = mysqli_fetch_assoc(mysqli_query($con, "SELECT phone as juakali from feedback where senid='$row[senid]' and rec='$rec' and id='$id[juakali]'"));
            $index['rec'] = $rec;
            $index['count'] = $count['juakali'];
            $index['phone'] = $phone['juakali'];
            $index['name'] = $name['juakali'];
            if(strlen($message['juakali'])<80){
                $index['message'] = $message['juakali'];
            }else{
                $index['message'] = substr($message['juakali'], 0, 79).' ...'; 
            }
            $index['date'] = $date['juakali'];
            $index['sen'] = $sen['juakali'];
            $index['timing'] = $timing['juakali'];
            $index['temple'] = strtoupper($name['juakali'][0]);
            array_push($wanawida['victory'], $index);
        }
    } else {
        $wanawida['trust'] = 0;
        $wanawida['mine'] = "No Messages";
    }
    echo json_encode($wanawida);