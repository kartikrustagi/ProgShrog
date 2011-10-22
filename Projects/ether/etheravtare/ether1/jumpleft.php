<?php 
include("connect.inc.php");
session_start();
if($_SESSION['tid']!=''&&$_SESSION['pid']!=''&&$_SESSION['cid']!='')
{
$cid=$_SESSION['cid'];
$pid=$_SESSION['pid'];
$tid=$_SESSION['tid'];
if(($pid>1&&$pid<6)||($pid>6&&$pid<11)||($pid>11&&$pid<16)||($pid>16&&$pid<21)||($pid>21&&$pid<26)) $pid--;
else if($pid==1) $pid=5;
else if($pid==6) $pid=10;
else if($pid==11) $pid=15;
else if($pid==16) $pid=20;
else if($pid==21) $pid=25;
$_SESSION['pid']=$pid;
$sql2="update team set current_pid='$pid' where cid=$cid and tid=$tid";
$result2=mysql_query($sql2);
}
?>
<script>window.location="planet.php";</script>