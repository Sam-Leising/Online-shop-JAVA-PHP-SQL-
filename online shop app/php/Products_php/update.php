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
     $name = $_POST["name"];
     $email = $_POST["email"];
     $contact = $_POST["contact"];
     $address = $_POST["address"];
     
     $sql = "UPDATE data SET  name = '$name', email = '$email', contact = '$contact', address = '$address' WHERE id = '$id' ";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Updated";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($connection);
     
        
?>