
<?php 
include("connect.inc.php");
session_start();
if($_SESSION['tid']!=''&&$_SESSION['pid']!=''&&$_SESSION['cid']!='')
{
$cid=$_SESSION['cid'];
$pid=$_SESSION['pid'];
$tid=$_SESSION['tid'];
$sql="select * from questionair where pid=$pid and cid=$cid";
$result=mysql_query($sql);
$row=mysql_fetch_array($result);
for($i=1;$i<=6;$i++)
{
$quest[$i]=$row['q'.$i];
$ans[$i]=htmlentities($_POST['ans'.$i]);
$sql1="select * from question where q_id=$quest[$i]";
$result1=mysql_query($sql1);
$row1=mysql_fetch_array($result1);
$ans1=$row1[2];
if(!strcasecmp($ans[$i],$ans1))
{
$score=$score+$row1[3];
$k='1'.$k;
}
else 
$k='0'.$k;
}
 $sql2="select * from status where pid=$pid and tid=$tid";
$result2=mysql_query($sql2);
$row2=mysql_fetch_array($result2);
if($row2)
{
$m=$k|$row2[0];
$score1=$row2[1]+$score;
$sql="update status set q_status='$m', p_score='$score1' where pid=$pid and tid=$tid";
}
else 
$sql="insert into status values ('".$k."', '$score', '', '$tid', '$pid')";
$result=mysql_query($sql);
$sql2="select * from team where cid=$cid and tid=$tid";
$result2=mysql_query($sql2);
$row2=mysql_fetch_array($result2);
if($row2)
{
$score1=$row2[1]+$score;
$sql="update team set tscore='$score1' where cid=$cid and tid=$tid";
$result=mysql_query($sql);
}
}
?>
<script>window.location="planet.php";</script>