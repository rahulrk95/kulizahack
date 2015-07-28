<?php
//gets input from RegisterPage of app and stores into the database
require_once ('MysqliDb.php');
$username = 'anoop dv';     //$_POST['username'];
$email_id = 'cool.pool@gmail.com'; //$_POST['email_id'];
$password = 'sadsadsads';		//$_POST['password'];
$branch = 'TCE';				//$_POST['branch'];
$db = new MysqliDb ('localhost', 'root', 'hack123', 'colgpolls');

$data = Array ("username" => $username,
				"email_id" => $email_id,
				"password" => $password,
				"branch"  => $branch
               
);
$id = $db->insert ('users', $data);

?>