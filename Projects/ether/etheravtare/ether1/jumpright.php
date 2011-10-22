<?php 
include("connect.inc.php");
session_start();
if($_SESSION['tid']!=''&&$_SESSION['pid']!=''&&$_SESSION['cid']!='')
{
$cid=$_SESSION['cid'];
$pid=$_SESSION['pid'];
$tid=$_SESSION['tid'];
if(($pid>0&&$pid<5)||($pid>5&&$pid<10)||($pid>10&&$pid<15)||($pid>15&&$pid<20)||($pid>20&&$pid<25)) $pid++;
else if($pid==5) $pid=1;
else if($pid==10) $pid=6;
else if($pid==15) $pid=11;
else if($pid==20) $pid=16;
else if($pid==25) $pid=21;
$_SESSION['pid']=$pid;
$sql2="update team set current_pid='$pid' where cid=$cid and tid=$tid";
$result2=mysql_query($sql2);
}
?>
<script>window.location="planet.php";</script>