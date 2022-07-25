<?php
    $host = "127.0.0.1";
    $username = "root";
    $password = "";   
    $dbname =  "online_shop";

    $arp=`arp -a`;
	// $server_ip = substr($arp , 70 , 15);
    $server_ip = '192.168.141.160';
    // echo $arp;
    
    $connection=new mysqli($host,$username,$password,$dbname);
    if($connection->connect_error){
        die("Connection failed: ". $connection->connect_error);
    }
    
?>