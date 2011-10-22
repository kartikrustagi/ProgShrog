<?php 
include("connect.inc.php");
session_start();
if($_SESSION['tid']!=''&&$_SESSION['pid']!=''&&$_SESSION['cid']!='')
{
$cid=$_SESSION['cid'];
$pid=$_SESSION['pid'];
$tid=$_SESSION['tid'];
if($pid<21) $pid=$pid+5;
else $pid=$pid-20;
$_SESSION['pid']=$pid;
$sql2="update team set current_pid='$pid' where cid=$cid and tid=$tid";
$result2=mysql_query($sql2);
}
?>
<script>window.location="planet.php";</script>