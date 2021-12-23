<?php 
// $host = "127.0.0.1";
// $username = "root";
// $password = "";   
// $dbname =  "online_shop";

// $connection=mysqli_connect($host,$username,$password,$dbname) or die(mysqli_connect_error());
// mysqli_set_charset($connection,"uft8");

// require_once "connection.php";
include "connection.php";

	// $connection = mysqli_connect("localhost","id11477192_arsltech","12345678","id11477192_employee_management_system");
	
	$result = array();
	$result['products'] = array();
	$select= "SELECT *from products";
	$responce = mysqli_query($connection,$select);
	
	while($row = mysqli_fetch_array($responce))
		{
			$index['id']      = $row['0'];
			$index['title']    = $row['1'];
			$index['describe']   = $row['2'];
			$index['price'] = $row['3'];
			$index['photo'] = $row['4'];
			$index['owner'] = $row['5'];
			$index['phone'] = $row['6'];
			
			array_push($result['products'], $index);
		}
			
			$result["success"]="1";
			echo json_encode($result);
			mysqli_close($connection);

 ?>