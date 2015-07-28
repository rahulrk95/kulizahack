<?php
require_once ('MysqliDb.php');
/* updates voting colums for upvote and downvote */

$pid =   //$POST_['pid'];    //poll id
$vote =  //$POST_['vote'];     //upvote(yes) or downvote(no)

$db = new MysqliDb ('localhost', 'root', 'hack123', 'colgpolls');

if($vote == 'yes')
	$data = Array ('upvote' => $db->inc(1));
else
	$data = Array ('downvote' => $db->inc(1));

$db->where ('pid', $pid);
$db->update ('polls', $data);

?>