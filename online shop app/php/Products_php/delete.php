<?php
// $host = "127.0.0.1";
// $username = "root";
// $password = "";   
// $dbname =  "online_shop";

// $connection=mysqli_connect($host,$username,$password,$dbname) or die(mysqli_connect_error());
// mysqli_set_charset($connection,"uft8");
include "connection.php";

    // $connection = mysqli_connect("localhost","id11477192_arsltech","12345678","id11477192_employee_management_system");
    
     $id = $_POST["id"];
     
     $sql = "DELETE FROM products WHERE id='$id'";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Deleted";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($connection);
     


?>