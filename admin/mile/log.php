<?php
include('../database/conn.php');
$usr=$_POST["username"];
$pas = md5($_POST['password']);
$user=$_POST["user"];
if($user=='c'){
$sql=mysqli_query($con,"SELECT * from customer where username='$usr' and password='$pas'");//query
if(mysqli_num_rows($sql)===1){//data type & value equity
    $row=mysqli_fetch_array($sql);//fetch
    if($row['status']=='Pending'){//approval pending
        $res['status']=0;
        $res['message']="Kindly wait for account approval";
    }else{
        $res=array();
        $res['log']=array();
        if($row['status']=='Rejected'){//rejected
            $out['comment']=$row['remarks'];
            $res['status']=2;
            $res['message']="Account rejected";
        }else{//approved
            $out["user_id"]=$row['entry'];
            $out["fname"]=$row['fname'];
            $out["lname"]=$row['lname'];
            $out["username"]=$row['username'];
            $out["email"]=$row['email'];
            $out["phone"]=$row['phone'];
            $out["county"]=$row['county'];
            $out["reg_date"]=$row['reg_date'];
            $res['status']=1;
            $res['message']="Your login was successful";
        }//user_id, fname, lname, username, email, phone, county, reg_date
        array_push($res['log'],$out);
    }
}else{
    $res['status']=0;
    $res['message']="Access Denied!!! Account not found";
}
}elseif($user=='i'){
    $sql=mysqli_query($con,"SELECT * from staff where username='$usr' and password='$pas' and role='Inventory'");//query
    if(mysqli_num_rows($sql)===1){//data type & value equity
        $row=mysqli_fetch_array($sql);//fetch
        if($row['status']=='Pending'){//approval pending
            $res['status']=0;
            $res['message']="Kindly wait for account approval";
        }else{
            $res=array();
            $res['log']=array();
            if($row['status']=='Rejected'){//rejected
                $out['comment']=$row['remarks'];
                $res['status']=2;
                $res['message']="Account rejected";
            }else{//approved
                $out["user_id"]=$row['entry'];
                $out["fname"]=$row['fname'];
                $out["lname"]=$row['lname'];
                $out["username"]=$row['username'];
                $out["email"]=$row['email'];
                $out["phone"]=$row['phone'];
                $out["county"]=$row['role'];
                $out["reg_date"]=$row['reg_date'];
                $res['status']=1;
                $res['message']="Your login was successful";
            }//user_id, fname, lname, username, email, phone, county, reg_date
            array_push($res['log'],$out);
        }
    }else{
        $res['status']=0;
        $res['message']="Access Denied!!! Account not found";
    }
}elseif($user=='rv'){
    $sql=mysqli_query($con,"SELECT * from staff where username='$usr' and password='$pas' and role='Service'");//query
    if(mysqli_num_rows($sql)===1){//data type & value equity
        $row=mysqli_fetch_array($sql);//fetch
        if($row['status']=='Pending'){//approval pending
            $res['status']=0;
            $res['message']="Kindly wait for account approval";
        }else{
            $res=array();
            $res['log']=array();
            if($row['status']=='Rejected'){//rejected
                $out['comment']=$row['remarks'];
                $res['status']=2;
                $res['message']="Account rejected";
            }else{//approved
                $out["user_id"]=$row['entry'];
                $out["fname"]=$row['fname'];
                $out["lname"]=$row['lname'];
                $out["username"]=$row['username'];
                $out["email"]=$row['email'];
                $out["phone"]=$row['phone'];
                $out["county"]=$row['role'];
                $out["reg_date"]=$row['reg_date'];
                $res['status']=1;
                $res['message']="Your login was successful";
            }//user_id, fname, lname, username, email, phone, county, reg_date
            array_push($res['log'],$out);
        }
    }else{
        $res['status']=0;
        $res['message']="Access Denied!!! Account not found";
    }
}elseif($user=='v'){
    $sql=mysqli_query($con,"SELECT * from staff where username='$usr' and password='$pas' and role='Videographer'");//query
    if(mysqli_num_rows($sql)===1){//data type & value equity
        $row=mysqli_fetch_array($sql);//fetch
        if($row['status']=='Pending'){//approval pending
            $res['status']=0;
            $res['message']="Kindly wait for account approval";
        }else{
            $res=array();
            $res['log']=array();
            if($row['status']=='Rejected'){//rejected
                $out['comment']=$row['remarks'];
                $res['status']=2;
                $res['message']="Account rejected";
            }else{//approved
                $out["user_id"]=$row['entry'];
                $out["fname"]=$row['fname'];
                $out["lname"]=$row['lname'];
                $out["username"]=$row['username'];
                $out["email"]=$row['email'];
                $out["phone"]=$row['phone'];
                $out["county"]=$row['role'];
                $out["reg_date"]=$row['reg_date'];
                $res['status']=1;
                $res['message']="Your login was successful";
            }//user_id, fname, lname, username, email, phone, county, reg_date
            array_push($res['log'],$out);
        }
    }else{
        $res['status']=0;
        $res['message']="Access Denied!!! Account not found";
    }
}elseif($user=='ds'){
    $sql=mysqli_query($con,"SELECT * from staff where username='$usr' and password='$pas' and role='Designer'");//query
    if(mysqli_num_rows($sql)===1){//data type & value equity
        $row=mysqli_fetch_array($sql);//fetch
        if($row['status']=='Pending'){//approval pending
            $res['status']=0;
            $res['message']="Kindly wait for account approval";
        }else{
            $res=array();
            $res['log']=array();
            if($row['status']=='Rejected'){//rejected
                $out['comment']=$row['remarks'];
                $res['status']=2;
                $res['message']="Account rejected";
            }else{//approved
                $out["user_id"]=$row['entry'];
                $out["fname"]=$row['fname'];
                $out["lname"]=$row['lname'];
                $out["username"]=$row['username'];
                $out["email"]=$row['email'];
                $out["phone"]=$row['phone'];
                $out["county"]=$row['role'];
                $out["reg_date"]=$row['reg_date'];
                $res['status']=1;
                $res['message']="Your login was successful";
            }//user_id, fname, lname, username, email, phone, county, reg_date
            array_push($res['log'],$out);
        }
    }else{
        $res['status']=0;
        $res['message']="Access Denied!!! Account not found";
    }
    }elseif($user=='su'){
        $sql=mysqli_query($con,"SELECT * from supplier where username='$usr' and password='$pas'");//query
        if(mysqli_num_rows($sql)===1){//data type & value equity
            $row=mysqli_fetch_array($sql);//fetch
            if($row['status']=='Pending'){//approval pending
                $res['status']=0;
                $res['message']="Kindly wait for account approval";
            }else{
                $res=array();
                $res['log']=array();
                if($row['status']=='Rejected'){//rejected
                    $out['comment']=$row['remarks'];
                    $res['status']=2;
                    $res['message']="Account rejected";
                }else{//approved
                    $out["user_id"]=$row['entry'];
                    $out["fname"]=$row['fname'];
                    $out["lname"]=$row['lname'];
                    $out["username"]=$row['username'];
                    $out["email"]=$row['email'];
                    $out["phone"]=$row['phone'];
                    $out["county"]=$row['county'];
                    $out["reg_date"]=$row['reg_date'];
                    $res['status']=1;
                    $res['message']="Your login was successful";
                }//user_id, fname, lname, username, email, phone, county, reg_date
                array_push($res['log'],$out);
            }
        }else{
            $res['status']=0;
            $res['message']="Access Denied!!! Account not found";
        }
        }elseif($user=='d'){
            $sql=mysqli_query($con,"SELECT * from driver where username='$usr' and password='$pas'");//query
            if(mysqli_num_rows($sql)===1){//data type & value equity
                $row=mysqli_fetch_array($sql);//fetch
                if($row['status']=='Pending'){//approval pending
                    $res['status']=0;
                    $res['message']="Kindly wait for account approval";
                }else{
                    $res=array();
                    $res['log']=array();
                    if($row['status']=='Rejected'){//rejected
                        $out['comment']=$row['remarks'];
                        $res['status']=2;
                        $res['message']="Account rejected";
                    }else{//approved
                        $out["user_id"]=$row['entry'];
                        $out["fname"]=$row['fname'];
                        $out["lname"]=$row['lname'];
                        $out["username"]=$row['username'];
                        $out["email"]=$row['email'];
                        $out["phone"]=$row['phone'];
                        $out["county"]=$row['county'];
                        $out["reg_date"]=$row['reg_date'];
                        $res['status']=1;
                        $res['message']="Your login was successful";
                    }//user_id, fname, lname, username, email, phone, county, reg_date
                    array_push($res['log'],$out);
                }
            }else{
                $res['status']=0;
                $res['message']="Access Denied!!! Account not found";
            }
            }elseif($user=='f'){
                $sql=mysqli_query($con,"SELECT * from staff where username='$usr' and password='$pas' and role='Finance'");//query
                if(mysqli_num_rows($sql)===1){//data type & value equity
                    $row=mysqli_fetch_array($sql);//fetch
                    if($row['status']=='Pending'){//approval pending
                        $res['status']=0;
                        $res['message']="Kindly wait for account approval";
                    }else{
                        $res=array();
                        $res['log']=array();
                        if($row['status']=='Rejected'){//rejected
                            $out['comment']=$row['remarks'];
                            $res['status']=2;
                            $res['message']="Account rejected";
                        }else{//approved
                            $out["user_id"]=$row['entry'];
                            $out["fname"]=$row['fname'];
                            $out["lname"]=$row['lname'];
                            $out["username"]=$row['username'];
                            $out["email"]=$row['email'];
                            $out["phone"]=$row['phone'];
                            $out["county"]=$row['role'];
                            $out["reg_date"]=$row['reg_date'];
                            $res['status']=1;
                            $res['message']="Your login was successful";
                        }//user_id, fname, lname, username, email, phone, county, reg_date
                        array_push($res['log'],$out);
                    }
                }else{
                    $res['status']=0;
                    $res['message']="Access Denied!!! Account not found";
                }
                }
echo json_encode($res);
?>