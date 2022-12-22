<?php
include("mover.php");
if (empty($_SESSION['admina'])) {
    header('location:login');
} else {

    if (isset($_GET['idi'])) {
        $idi = $_GET['idi'];
        $status = 'Approved';
        mysqli_query($con, "UPDATE staff set status='$status',remarks='' where id='$idi'");
        header('location:inveR.php');
    }
?>

<!DOCTYPE html>
<html lang="en">

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

<body class="fix-header">
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" />
        </svg>
    </div>
    <div id="main-wrapper">
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
                    <h3 class="text-primary">Rejected StoreKeepers</h3>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">

                    <a href="inveP"><button class="btn btn-warning"> Pending</button></a>
                    <a href="inveA"><button class="btn btn-primary"> Approved</button></a>
                    <a href="inveR"><button class="btn btn-danger"> Rejected</button></a>
                           
                                <div class="table-responsive m-t-40">
                                    <table id="myTable" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                            <th>EntryNO</th>
                                                <th>Action</th>
                                                <th>Fname</th>
                                                <th>Lname</th>
                                                <th>Username</th>
                                                <th>Email</th>
                                                <th>Phone</th>
                                                <th>Status</th>
                                                <th>RegDate</th>

                                            </tr>
                                        </thead>
                                        <tbody>


                                            <?php
                                                $sql = "SELECT * FROM staff where status='Rejected' and role='Inventory' order by entry desc";
                                                $query = mysqli_query($con, $sql);

                                                if (!mysqli_num_rows($query) > 0) {
                                                    echo '<td colspan="10"><center>No Record was Found</center></td>';
                                                } else {
                                                    while ($rows = mysqli_fetch_array($query)) {
                                                ?> <tr>
                                                <td><?php echo $rows['entry']; ?></td>
                                                <td class="center">
                                                <a href="inveR.php?idi=<?php echo htmlentities($rows['id']); ?>"
                                                        onclick="return confirm('Are you sure you want to Approve this Employee?');">
                                                        <button class="btn btn-primary"> Approve</button></a>
                                                </td>
                                                <td><?php echo $rows['fname']; ?></td>
                                                <td><?php echo $rows['lname']; ?></td>
                                                <td><?php echo $rows['username']; ?></td>
                                                <td><?php echo $rows['email']; ?></td>
                                                <td><?php echo $rows['phone']; ?></td>
                                                <td><?php echo $rows['status']; ?></td>
                                                <td><?php echo $rows['reg_date']; ?></td>
                                                
                                            </tr>
                                            <?php }
                                                }
                                                ?>
                                        </tbody>
                                        <tfoot class="bg-warning">
                                            <tr>
                                            <th>EntryNO</th>
                                                <th>Action</th>
                                                <th>Fname</th>
                                                <th>Lname</th>
                                                <th>Username</th>
                                                <th>Email</th>
                                                <th>Phone</th>
                                                <th>Status</th>
                                                <th>RegDate</th>

                                            </tr>
                                        </tfoot>
                                    </table>
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
    <script src="js/lib/datatables/datatables.min.js"></script>
    <script src="js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
    <script src="js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.flash.min.js"></script>
    <script src="js/lib/datatables/cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
    <script src="js/lib/datatables/cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
    <script src="js/lib/datatables/cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
    <script src="js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
    <script src="js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>
    <script src="js/lib/datatables/datatables-init.js"></script>

</body>

</html>
<?php
}
?>