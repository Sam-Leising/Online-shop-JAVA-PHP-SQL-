<?php
    if(isset($_POST['email']) && isset($_POST['password'])){
        require_once "connection.php";
        require_once "validate.php";
        $email = validate($_POST['email']);
        $password = validate($_POST['password']);

        // $sql = "SELECT * FROM users where email = '$email' and password='" . md5($password) . "'";
        $autoLogin = validate($_POST['autoLogin']);
        if($autoLogin == "true"){
            $sql = "SELECT * FROM users where email = '$email' and password='$password'";
        }else{
            $sql = "SELECT * FROM users where email = '$email' and password='" . md5($password) . "'";
        }

        $result = $connection->query($sql);
        if($result->num_rows > 0){
            // echo "success";
            $userResult = array();
            $userResult['users'] = array();
            $select= "SELECT * FROM `users` WHERE `email` = '$email'";
            // $responce = mysqli_query($connection,$select);
            $responce = $connection->query($select);
            
            while($row = mysqli_fetch_array($responce))
            {
                $index['id']      = $row['0'];
                $index['name']    = $row['1'];
                $index['email']   = $row['2'];
                $index['password'] = $row['3'];
                
                array_push($userResult['users'], $index);
            }
			$userResult["success"]="1";
        }else{
            $userResult["success"]="0";
            
        }
        echo json_encode($userResult);
            mysqli_close($connection);
    }
?>