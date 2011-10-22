<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr"><head>



<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Content-Style-Type" content="text/css">
 
<link rel="stylesheet" href="fisubsilver.css" type="text/css">

<script type="text/javascript">
_uacct = "UA-954824-1";
urchinTracker();
</script>
<SCRIPT LANGUAGE="JavaScript">
<! >
<! >
<!-- Begin
function checkrequired(which) {
var pass=true;
if (document.images) {
for (i=0;i<which.length;i++) {
var tempobj=which.elements[i];
if (tempobj.name.substring(0,8)=="required") {
if (((tempobj.type=="text"||tempobj.type=="textarea")&&
tempobj.value=='')||(tempobj.type.toString().charAt(0)=="s"&&
tempobj.selectedIndex==0)) {
pass=false;
break;
         }
      }
   }
}
if (!pass) {
shortFieldName=tempobj.name.substring(8,30).toUpperCase();
alert("Please make sure the "+shortFieldName+" field was properly completed.");
return false;
}
else
return true;
}
//  End -->
</script>
</head><body>

<table class="bodyline" border="0" cellpadding="0" cellspacing="0" width="100%">
<tbody><tr>
<td>
<table class="topbkg" border="0" cellpadding="0" cellspacing="0" width="100%">
<tbody><tr> 

</tr>
</tbody></table>
<table border="0" cellpadding="10" cellspacing="0" width="100%">
<tbody><tr>
<td><form action="cat_main2.php" method="post" onsubmit="return checkrequired(this)">
  <table class="forumline" border="0" cellpadding="3" cellspacing="1" width="100%">
<tbody>
<tr>
<td width="100%" height="22" class="row2"><span class="maintitle">QuestionareSelecting page</span></td>
</tr>
</tbody></table>
<br>
<table class="forumline" border="0" cellpadding="3" cellspacing="1" width="100%">
<tbody>
<tr>
  <td width="38%" class="row1"><span class="explaintitle">PLANETS ID </span> *</td>
  <td width="62%" class="row2">
  <?php
 
         echo"<select name=\"pid\">";
		 $var="a";
        for($i=1;$i<=24;$i++)
		{
	      echo "<option value=\"",$i,"\">","$var","</option>";
		  $var++;
	  };
    
	echo"<option value=\"",$i,"\" selected=\"selected\">", "y","</option>";

echo"</select>";
?></td>
</tr>
<tr>
<td class="row1"><span class="explaintitle">CID</span></td>
<td class="row2"><label>
  <select name="cid">
    <option value="1">cat1</option>
    <option value="2">cat2</option>
    <option value="3">cat3</option>
    <option value="4">cat4</option>
    <option value="5">cat5</option>
        </select>
</label></td>
</tr>
<tr>
  <td class="row1"><span class="explaintitle">subcat for Q1 </span></td>
  <td class="row2"><label>
    <?php
 include('objConnMainPage.php');
 $dept_query="select * from subcat" ;
 $dept_result=mysql_query($dept_query) or die(mysql_error());
         echo"<select name=\"q1\">";
	    $num_res=mysql_num_rows($dept_result);
        for($i=1;$i<=$num_res-1;$i++)
		{
		$dept_row=mysql_fetch_array($dept_result);
      echo "<option value=\"",$i,"\">",$dept_row[subname],"</option>";
	  };
    $dept_row=mysql_fetch_array($dept_result);
	echo"<option value=\"",$i,"\" selected=\"selected\">", $dept_row[subname],"</option>";

echo"</select>";
?>
  </label></td>
</tr>
<tr>
  <td class="row1"><span class="explaintitle">subcat  for Q2</span></td>
  <td class="row2"><label>
  <?php
 include('objConnMainPage.php');
 $dept_query="select * from subcat" ;
 $dept_result=mysql_query($dept_query) or die(mysql_error());
         echo"<select name=\"q2\">";
	    $num_res=mysql_num_rows($dept_result);
        for($i=1;$i<=$num_res-1;$i++)
		{
		$dept_row=mysql_fetch_array($dept_result);
      echo "<option value=\"",$i,"\">",$dept_row[subname],"</option>";
	  };
    $dept_row=mysql_fetch_array($dept_result);
	echo"<option value=\"",$i,"\" selected=\"selected\">", $dept_row[subname],"</option>";

echo"</select>";
?>
    </label></td>
</tr>
<tr>
  <td class="row1"><span class="explaintitle">subcat  for Q3</span></td>
  <td class="row2"><label>
    <?php
 include('objConnMainPage.php');
 $dept_query="select * from subcat" ;
 $dept_result=mysql_query($dept_query) or die(mysql_error());
         echo"<select name=\"q3\">";
	    $num_res=mysql_num_rows($dept_result);
        for($i=1;$i<=$num_res-1;$i++)
		{
		$dept_row=mysql_fetch_array($dept_result);
      echo "<option value=\"",$i,"\">",$dept_row[subname],"</option>";
	  };
    $dept_row=mysql_fetch_array($dept_result);
	echo"<option value=\"",$i,"\" selected=\"selected\">", $dept_row[subname],"</option>";

echo"</select>";
?>
  </label></td>
</tr>
<tr>
  <td class="row1"><span class="explaintitle">subcat  for Q4</span></td>
  <td class="row2"><label>
    <?php
 include('objConnMainPage.php');
 $dept_query="select * from subcat" ;
 $dept_result=mysql_query($dept_query) or die(mysql_error());
         echo"<select name=\"q4\">";
	    $num_res=mysql_num_rows($dept_result);
        for($i=1;$i<=$num_res-1;$i++)
		{
		$dept_row=mysql_fetch_array($dept_result);
      echo "<option value=\"",$i,"\">",$dept_row[subname],"</option>";
	  };
    $dept_row=mysql_fetch_array($dept_result);
	echo"<option value=\"",$i,"\" selected=\"selected\">", $dept_row[subname],"</option>";

echo"</select>";
?>
  </label></td>
</tr>
<tr>
  <td class="row1"><span class="explaintitle">subcat  for Q5</span></td>
  <td class="row2"><label>
    <?php
 include('objConnMainPage.php');
 $dept_query="select * from subcat" ;
 $dept_result=mysql_query($dept_query) or die(mysql_error());
         echo"<select name=\"q5\">";
	    $num_res=mysql_num_rows($dept_result);
        for($i=1;$i<=$num_res-1;$i++)
		{
		$dept_row=mysql_fetch_array($dept_result);
      echo "<option value=\"",$i,"\">",$dept_row[subname],"</option>";
	  };
    $dept_row=mysql_fetch_array($dept_result);
	echo"<option value=\"",$i,"\" selected=\"selected\">", $dept_row[subname],"</option>";

echo"</select>";
?>
  </label></td>
</tr>
<tr>
  <td class="row1"><span class="explaintitle">subcat  for Q6</span></td>
  <td class="row2"><label>
    <?php
 include('objConnMainPage.php');
 $dept_query="select * from subcat" ;
 $dept_result=mysql_query($dept_query) or die(mysql_error());
         echo"<select name=\"q6\">";
	    $num_res=mysql_num_rows($dept_result);
        for($i=1;$i<=$num_res-1;$i++)
		{
		$dept_row=mysql_fetch_array($dept_result);
      echo "<option value=\"",$i,"\">",$dept_row[subname],"</option>";
	  };
    $dept_row=mysql_fetch_array($dept_result);
	echo"<option value=\"",$i,"\" selected=\"selected\">", $dept_row[subname],"</option>";

echo"</select>";
?>
  </label></td>
</tr>
<tr>
<td class="cat" colspan="2"><div align="center">
  <input name="submit" value="Submit" class="mainoption" type="submit">
  &nbsp;&nbsp;
  <input value="Reset" name="reset" class="button" type="reset">
</div></td>
</tr>
</tbody></table>
<br>


</form></td>
</tr>
</tbody></table>
</td></tr></tbody></table></body></html>