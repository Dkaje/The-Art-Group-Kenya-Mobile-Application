<?php
ob_start();
error_reporting(0);
session_start();
include('database/conn.php');
if (empty($_SESSION['admina'])) {
    header('location:login');
} else {
?>

<!DOCTYPE html>
<html lang="en" style="background-color: #fff">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>The Art Group</title>
    <link href="css/lib/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="css/helper.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body class="fix-header" style="background-color: #fff">
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" />
        </svg>
    </div>
    <div id="main-wrapper" style="background-color: #fff">
        <div class="header">
            <?php include 'nav.php';?>
        </div>
        <div class="left-sidebar" style="background-color: #fff">
            <div class="scroll-sidebar">
                <?php include("sider.php") ?>
            </div>
        </div>
        <div class="page-wrapper" style="height:100%">
            <div class="row page-titles">
                <div class="col-md-5 align-self-center">
                    <h3 class="text-primary">Dashboard</h3>
                </div>

            </div>
            <div class="container-fluid" style="background-color: #fff">
                <div class="row">

<div class="col-md-3">
    <div class="card p-30">
        <div class="media">
            <div class="media-left meida media-middle">
                <h2><?php echo mysqli_num_rows(mysqli_query($con, "SELECT * from staff where status='Pending' and role='Finance'")); ?>
                </h2>
                <p class="m-b-0">Finance Managers</p>
            </div>
            <div class="media-body media-text-right">
                <span><i class="fa fa-dollar f-s-40 color-primary" aria-hidden="true"></i></span>

            </div>
        </div>
    </div>
</div>

<div class="col-md-3">
    <div class="card p-30">
        <div class="media">
            <div class="media-left meida media-middle">
                <h2><?php echo mysqli_num_rows(mysqli_query($con, "SELECT * from staff where status='Pending' and role='Inventory'")); ?>
                </h2>
                <p class="m-b-0">Inventory Managers</p>
            </div>
            <div class="media-body media-text-right">
                <span><i class="fas fa-store-alt f-s-40 color-primary" aria-hidden="true"></i></span>

            </div>
        </div>
    </div>
</div>

<div class="col-md-3">
    <div class="card p-30">
        <div class="media">
            <div class="media-left meida media-middle">
                <h2><?php echo mysqli_num_rows(mysqli_query($con, "SELECT * from staff where status='Pending' and role='Designer'")); ?>
                </h2>
                <p class="m-b-0">System Designers</p>
            </div>
            <div class="media-body media-text-right">
                <span><i class="fas fa-bezier-curve f-s-40 color-primary" aria-hidden="true"></i></span>

            </div>
        </div>
    </div>
</div>

<div class="col-md-3">
    <div class="card p-30">
        <div class="media">
            <div class="media-left meida media-middle">
                <h2><?php echo mysqli_num_rows(mysqli_query($con, "SELECT * from staff where status='Pending' and role='Videographer'")); ?>
                </h2>
                <p class="m-b-0">System Videographers</p>
            </div>
            <div class="media-body media-text-right">
                <span><i class="fa fa-photo f-s-40 color-primary" aria-hidden="true"></i></span>

            </div>
        </div>
    </div>
</div>

<div class="col-md-3">
    <div class="card p-30">
        <div class="media">
            <div class="media-left meida media-middle">
                <h2><?php echo mysqli_num_rows(mysqli_query($con, "SELECT * from driver where status='Pending'")); ?>
                </h2>
                <p class="m-b-0">New Drivers</p>
            </div>
            <div class="media-body media-text-right">
                <span><i class="fa fa-truck f-s-40 color-danger"></i></span>
            </div>
        </div>
    </div>
</div>

                    <div class="col-md-3">
                        <div class="card p-30">
                            <div class="media">
                                <div class="media-left meida media-middle">
                                    <h2><?php echo mysqli_num_rows(mysqli_query($con, "SELECT * from supplier where status='Pending'")); ?>
                                    </h2>
                                    <p class="m-b-0">New Suppliers</p>
                                </div>
                                <div class="media-body media-text-right">
                                    <span><i class="fa fa-car f-s-40 color-success" aria-hidden="true"></i></span>

                                </div>
                            </div>
                        </div>
                    </div>

<div class="col-md-3">
    <div class="card p-30">
        <div class="media">
            <div class="media-left meida media-middle">
                <h2><?php echo mysqli_num_rows(mysqli_query($con, "SELECT * from customer where status='Pending'")); ?>
                </h2>
                <p class="m-b-0">New Customers</p>

            </div>
            <div class="media-body media-text-right">
                <span><i class="fa fa-bell f-s-40 color-danger"></i></span>
            </div>
        </div>
    </div>
</div>
<div class="col-md-3">
    <div class="card p-30">
        <div class="media">
            <div class="media-left meida media-middle">
                <h2><?php echo mysqli_num_rows(mysqli_query($con, "SELECT * from staff where role='Service' and status='Pending'")); ?>
                </h2>
                <p class="m-b-0">New Service Managers</p>
            </div>
            <div class="media-body media-text-right">
                <span><i class="fa fa-wrench f-s-40 color-danger"></i></span>
            </div>
        </div>
    </div>
</div>
                </div>
            </div>
            <?php include 'footer.php'; ?>
        </div>
    </div>
    <script src="js/lib/jquery/jquery.min.js"></script>
    <script src="js/lib/bootstrap/js/popper.min.js"></script>
    <script src="js/lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="js/jquery.slimscroll.js"></script>
    <script src="js/sidebarmenu.js"></script>
    <script src="js/lib/sticky-kit-master/dist/sticky-kit.min.js"></script>
    <script src="js/custom.min.js"></script>

</body>

</html>
<?php
}
?>