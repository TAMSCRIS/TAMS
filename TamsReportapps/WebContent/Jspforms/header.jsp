 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.header{

 padding: 1em;
    color: white;
    background-color: Coral;
    clear: left;
    text-align: center;

}

.div {
    background-color: 8F8074;
}
</style>

<body>
<div  class="header" style="background-color:lightblue">
<img src="../CRIS_200.png"  width="100px" height="100px" align="right"> 
<img src="../Indian_Railway.png"  width="100px" height="100px" align="left"> 

<h1 align="center">Traffic Account Management System </h1>
<table>
<tr>

<td width="800px"><a href="<s:url action="homeaction"/>"><p style="color:Black"><b>Home</b></p></a></td>
<td width="800px"><a href="<s:url action="logout_action"/>"><p style="color:Black"><b>Logout</b></p></a></td>


</tr>
</table>
</div>

</body>