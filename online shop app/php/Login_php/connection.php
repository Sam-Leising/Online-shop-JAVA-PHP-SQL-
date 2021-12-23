<?php
    $host = "127.0.0.1";
    $username = "root";
    $password = "";   
    $dbname =  "online_shop";
    
    $connection=new mysqli($host,$username,$password,$dbname);
    if($connection->connect_error){
        die("Connection failed: ". $connection->connect_error);
    }
    
?>