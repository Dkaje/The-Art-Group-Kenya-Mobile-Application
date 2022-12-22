<?php
	include('database/conn.php');
	session_start();
	error_reporting(0);
error_reporting(E_ERROR);
	 ob_start();
    session_start();
    if(isset($_POST["login"])){
        $username = $_POST['username'];
        $password = md5($_POST['password']);
        $loginquery = "SELECT * from admin where username='$username' and password='$password'";
        $result = mysqli_query($con, $loginquery);
        $row = mysqli_num_rows($result);
        if($row>0){
            $notify='error';
            $show='Your login was successful';
            $_SESSION['admina']=$username;
            header("Refresh:2;url=index");
        }else{
            {
            $notify='error';
            $show='invalid login credentials';
        }
        }
    }
    ob_end_flush();
    
    if(isset($_POST["register"])){
        $pass=md5($_POST["password"]);
        $user=$_POST['username'];
       
     $sql = mysqli_query($con,"INSERT INTO admin(username,password)
        VALUES('$user','$pass')");
            if($sql){
                $notify='error';
                $show='Account created';
                header("Refresh:2;url=login");
            }else{
                $notify='error';
                $show='An error occurred';
            }
        }
    ?>
<!DOCTYPE html>
<html>
<head>
	<title>Artgroup</title>
	<script src="js/custom-sweeralert.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
	#login_form{
		width:350px;
		height:350px;
		position:relative;
		top:50px;
		margin: auto;
		padding: auto;
	}
</style>
</head>
<body >
<?php
if($notify=='error'){
  echo'<script>swal("","'.$show.'");</script>';
}
?>
<div class="container">
	<div id="login_form" class="well">
    <?php
                    if(mysqli_num_rows(mysqli_query($con,"SELECT * from admin"))>0){
                        echo '<h2><center><span class="glyphicon glyphicon-lock"></span>Login</center></h2>
                        <hr>
                        <form method="POST" >
                        Username: <input type="text" name="username" class="form-control" required>
                        <div style="height: 10px;"></div>		
                        Password: <input type="password" name="password" class="form-control" required> 
                        <div style="height: 10px;"></div>
                        <button type="submit" class="btn btn-primary" name="login"><span class="glyphicon glyphicon-log-in"></span> Login</button>
                        </form>
                        <a href="forgot">Forgot Password</a>
                        ';//one registered admin--->>>login
                    }else{
                        echo '<h2><center><span class="glyphicon glyphicon-lock"></span> Register</center></h2>
                        <hr>
                        <form method="POST" >
                        Username: <input type="text" name="username" class="form-control" required>
                        <div style="height: 10px;"></div>		
                        Password: <input type="password" name="password" class="form-control" required> 
                        <div style="height: 10px;"></div>
                        <button type="submit" class="btn btn-primary" name="register"><span class="glyphicon glyphicon-log-in"></span> Register</button>
                        </form>';//no registered person--->>>create account
                    }
                    ?>
		
		<div style="height: 15px;"></div>
		<div style="color: red; font-size: 15px;">
		</div>
	</div>
</div>
</body>
</html>