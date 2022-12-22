<?php
	include('database/conn.php');
error_reporting(E_ERROR);
if(isset($_POST["register"])){
    $user=$_POST['username'];
    $pass=md5($_POST["password"]);
    $sql=mysqli_query($con,"SELECT * from admin where username='$user'");
        if(mysqli_num_rows($sql)>0){
            $updt=mysqli_query($con,"UPDATE admin set password='$pass' where username='$user'");
            if($updt){
                $notify='error';
                $show='Password reset successfully';
            header("Refresh:2;url=login");
            }else{
               $notify='error';
                $show='An error occurred'; 
            }
        }else{
            $notify='error';
            $show='Account not found';
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
   <h2><center><span class="glyphicon glyphicon-lock"></span> Reset PAssword</center></h2>
                        <hr>
                        <form method="POST" >
                        Username: <input type="text" name="username" class="form-control" required>
                        <div style="height: 10px;"></div>		
                        Password: <input type="password" name="password" class="form-control" required> 
                        <div style="height: 10px;"></div>
                        <button type="submit" class="btn btn-primary" name="register"><span class="glyphicon glyphicon-log-in"></span> Reset</button>
                        <a href="login">Login</a>
                    </form>
		
		<div style="height: 15px;"></div>
		<div style="color: red; font-size: 15px;">
		</div>
	</div>
</div>
</body>
</html>