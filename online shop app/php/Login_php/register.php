<?php
     if(isset($_POST['name']) && isset($_POST['email']) && isset($_POST['password'])){
        require_once "connection.php";
        require_once "validate.php";
        $name = $_POST['name'];
        $email = $_POST['email'];
        $password = $_POST['password'];

        
        $sql = "SELECT * FROM users where email = '$email'";
        $result = $connection->query($sql);
        if($result->num_rows > 0){
            echo "failure";
        }else{
            // $sql = "INSERT INTO 'users' VALUES (NULL, '$name', '$email', '".md5($password)."')";
            $sql="INSERT INTO `users` (`id`, `name`, `email`, `password`)
            VALUES (NULL, '$name', '$email', '".md5($password)."')";

            if(!$connection->query($sql)){
                echo "failure";
            }else{
                echo "success";
        }
        }
     }

?>