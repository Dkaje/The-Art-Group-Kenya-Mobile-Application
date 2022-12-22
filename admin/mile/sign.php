<?php
include('../database/conn.php');
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $fname = $_POST["fname"];
    $lname = $_POST['lname'];
    $phone = $_POST['phone'];
    $email = $_POST['email'];
    $username = $_POST['username'];
    $password = md5($_POST['password']);
    $county = $_POST['county'];
    $user = $_POST['user'];
    $year = date("Y");
    if($user=='c'){
        $select = "SELECT email FROM customer WHERE email='$email'";
    $query = mysqli_query($con, $select);
    if (mysqli_num_rows($query) > 0) {
        $result["status"] = 0;
        $result["message"] = "Email already in use";
    } else {
        $select = "SELECT phone FROM customer WHERE phone='$phone'";
        $query = mysqli_query($con, $select);
        if (mysqli_num_rows($query) > 0) {
            $result["status"] = 0;
            $result["message"] = "Phone already in use";
        } else {
        $select = "SELECT username FROM customer WHERE username='$username'";
        $query = mysqli_query($con, $select);
        if (mysqli_num_rows($query) > 0) {
            $result["status"] = 0;
            $result["message"] = "Username taken";
        } else {
            $hits = file("text.txt");
            $hits[0]++;
            $fp = fopen("text.txt", "w");
            fputs($fp, "$hits[0]");
            fclose($fp);
            $values = $hits[0];
            $entry = $year.$values;
            $sql = "INSERT INTO customer(entry,fname,lname,email,username,phone,county,password)
    VALUES('$entry','$fname','$lname','$email','$username','$phone','$county','$password')";
            if (mysqli_query($con, $sql)) {
                $result["status"] = 1;
                $result["message"] = "Account was created succesfully";
            } else {
                $result["status"] = 0;
                $result["message"] = " Failed to create account";
            }
        }
    }
}
    }else if($user=='d'){
        $select = "SELECT email FROM driver WHERE email='$email'";
    $query = mysqli_query($con, $select);
    if (mysqli_num_rows($query) > 0) {
        $result["status"] = 0;
        $result["message"] = "Email already in use";
    } else {
        $select = "SELECT phone FROM driver WHERE phone='$phone'";
        $query = mysqli_query($con, $select);
        if (mysqli_num_rows($query) > 0) {
            $result["status"] = 0;
            $result["message"] = "Phone already in use";
        } else {
        $select = "SELECT username FROM driver WHERE username='$username'";
        $query = mysqli_query($con, $select);
        if (mysqli_num_rows($query) > 0) {
            $result["status"] = 0;
            $result["message"] = "Username taken";
        } else {
            $hits = file("text.txt");
            $hits[0]++;
            $fp = fopen("text.txt", "w");
            fputs($fp, "$hits[0]");
            fclose($fp);
            $values = $hits[0];
            $entry = $year.$values;
            $sql = "INSERT INTO driver(entry,fname,lname,email,username,phone,county,password)
    VALUES('$entry','$fname','$lname','$email','$username','$phone','$county','$password')";
            if (mysqli_query($con, $sql)) {
                $result["status"] = 1;
                $result["message"] = "Account was created succesfully";
            } else {
                $result["status"] = 0;
                $result["message"] = " Failed to create account";
            }
        }
    }
}
    }
    elseif ($user=='s'){
$select = "SELECT email FROM staff WHERE email='$email'";
    $query = mysqli_query($con, $select);
    if (mysqli_num_rows($query) > 0) {
        $result["status"] = 0;
        $result["message"] = "Email already in use";
    } else {
        $select = "SELECT phone FROM staff WHERE phone='$phone'";
        $query = mysqli_query($con, $select);
        if (mysqli_num_rows($query) > 0) {
            $result["status"] = 0;
            $result["message"] = "Phone already in use";
        } else {
        $select = "SELECT username FROM staff WHERE username='$username'";
        $query = mysqli_query($con, $select);
        if (mysqli_num_rows($query) > 0) {
            $result["status"] = 0;
            $result["message"] = "Username taken";
        } else {
            $hits = file("text.txt");
            $hits[0]++;
            $fp = fopen("text.txt", "w");
            fputs($fp, "$hits[0]");
            fclose($fp);
            $values = $hits[0];
            $entry = $year.$values;
            $sql = "INSERT INTO staff(entry,fname,lname,phone,email,username,password,role)
    VALUES('$entry','$fname','$lname','$phone','$email','$username','$password','$county')";
            if (mysqli_query($con, $sql)) {
                $result["status"] = 1;
                $result["message"] = "Account was created succesfully";
            } else {
                $result["status"] = 0;
                $result["message"] = " Failed to create account";
            }
        }
    }
}
    
    }elseif($user=='su'){
        $select = "SELECT email FROM supplier WHERE email='$email'";
    $query = mysqli_query($con, $select);
    if (mysqli_num_rows($query) > 0) {
        $result["status"] = 0;
        $result["message"] = "Email already in use";
    } else {
        $select = "SELECT phone FROM supplier WHERE phone='$phone'";
        $query = mysqli_query($con, $select);
        if (mysqli_num_rows($query) > 0) {
            $result["status"] = 0;
            $result["message"] = "Phone already in use";
        } else {
        $select = "SELECT username FROM supplier WHERE username='$username'";
        $query = mysqli_query($con, $select);
        if (mysqli_num_rows($query) > 0) {
            $result["status"] = 0;
            $result["message"] = "Username taken";
        } else {
            $hits = file("text.txt");
            $hits[0]++;
            $fp = fopen("text.txt", "w");
            fputs($fp, "$hits[0]");
            fclose($fp);
            $values = $hits[0];
            $entry = $year.$values;
            $sql = "INSERT INTO supplier(entry,fname,lname,phone,email,username,password,county)
    VALUES('$entry','$fname','$lname','$phone','$email','$username','$password','$county')";
            if (mysqli_query($con, $sql)) {
                $result["status"] = 1;
                $result["message"] = "Account was created succesfully";
            } else {
                $result["status"] = 0;
                $result["message"] = " Failed to create account";
            }
        }
    }
}
    
    }
    echo json_encode($result);
    mysqli_close($con);
}