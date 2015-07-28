<?php
require_once ('MysqliDb.php');

//Extracts question from forms and stores into database

$question_username = "oliver";//$_POST['question_username'];
$question =  "college canteen needs to be improved";//$_POST['question'];
$description = "details";//$_POST['description'];
$type= "general";//$_POST['branch'];                //branch or general
$timestamp = date('Y-m-d H:i:s'); 

$db = new MysqliDb ('localhost', 'root', 'hack123', 'colgpolls');

$data = Array ("question" => $question,
				"description" => $description,
				"question_username" => $question_username,
				"timestamp"  => $timestamp
				);
               
$id = $db->insert ('polls', $data);  

	$db->where ("timestamp", $timestamp);
	$user = $db->getOne ("polls");
	$data = Array ("pid" => $user['pid']);

/* There are two types of question category : general or branch based
	Store values according catogery in database */	
	

if($type == 'general')
{
	$db->insert ('CSE', $data);
	$db->insert ('MECH', $data);
	$db->insert ('TCE', $data);
	$db->insert ('ECE', $data);
}
else if($type == 'CSE')
	$db->insert ('CSE', $data);
else if($type == 'TCE')
	$db->insert ('TCE', $data);
else if($type == 'MECH')
	$db->insert ('MECH', $data);
else if($type == 'ECE')
	$db->insert ('ECE', $data); 



?>
