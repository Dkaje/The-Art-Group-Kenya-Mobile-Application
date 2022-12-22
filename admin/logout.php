<?php

session_start();
unset($_SESSION['admina']);

echo "<script>window.open('login','_self')</script>";