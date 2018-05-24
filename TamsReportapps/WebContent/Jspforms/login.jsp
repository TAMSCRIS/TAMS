<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TAMS User Login Page</title>

<style>

.errorMessage {
  font-weight: bold;
  color: red;
}

.div_style{
    width:300px;
    height:200px;
    position:absolute;
    left:50%;
    top:50%;
    margin:-100px 0 0 -150px;
}


input:required {
	border: 1px solid red;
}
h1 {
	/* color : #000000; */
	text-align : center;
	font-family: "SIMPSON";
}
form {
	width: 300px;
	margin: 0 auto;
}
body {
	/* background-color : #484848; */
	margin: 0;
	padding: 0;
}
.pic{
/* 	
  background-image: url("../swach_banner1.jpg");  
  background-repeat: no-repeat;
    background-size: 50% 100%;
 */
}
</style>


<script type="text/javascript"> 
history.forward(); 
</script> 




</head>
<jsp:include page="/Jspforms/header1.jsp"/>
<body class="pic">


<div align = "center" class="div_style">

 <s:form action="verifyaction" method="post">
 
 
 
 <label for="username"><b >User Name&nbsp;&nbsp;&nbsp;</b></label>
    <input type="text" placeholder="Enter User name" name="username" required>
<br>

<br>
    <label for="password"><b  >Password &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
    
<br>
<br>
    
<label><b  >Select zone &nbsp;&nbsp;</b> </label>
<select name="zone_name" placeholder = "select zone">
									
<option value="IR">IR &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
									<option value="CR">CR</option>
									<option value="EC">EC</option>
									<option value="EO">EO</option>
									<option value="ER">ER</option>
									
									<option value="KR">KR</option>
									
									<option value="NC">NC</option>
									<option value="NE">NE</option>
									<option value="NF">NF</option>
									<option value="NR">NR</option>
									<option value="NW">NW</option>
									<option value="SB">SB</option>
									<option value="SC">SC</option>
									<option value="SE">SE</option>
									<option value="SR">SR</option>
									<option value="SW">SW</option>
									<option value="WC">WC</option>
									<option value="WR">WR</option>									
</select>

<br>
<br>

 <button type="submit">Login</button>
 

<br>
<b><s:fielderror/> </b>							
 
</s:form>

</div>





<%-- 

<table align="center" cellpadding="0" cellspacing="0" width="500">

<tr>
    <td>
        <s:form action="verifyaction" method="post">
	   <table width="100%" align="center" cellpadding="2" cellspacing="0" border="0" height="5" >

							
<br>
<br><br><br><br><br><br><br><br>

							<tr>
								 <s:textfield name="username" label="User Name"/>
								
							</tr>
							
							<tr>
								<s:password name="password" label="Password"/>
							</tr>
							
							<tr>
							Zone:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<select name="zone_name" >
									<option value="-1">&nbsp;Select &nbsp;  zone &nbsp;&nbsp; &nbsp; &nbsp;  &nbsp;  &nbsp; </option>
									<option value="IR">IR</option>
									<option value="CR">CR</option>
									<option value="EC">EC</option>
									<option value="EO">EO</option>
									<option value="ER">ER</option>
									
									<option value="KR">KR</option>
									
									<option value="NC">NC</option>
									<option value="NE">NE</option>
									<option value="NF">NF</option>
									<option value="NR">NR</option>
									<option value="NW">NW</option>
									<option value="SB">SB</option>
									<option value="SC">SC</option>
									<option value="SE">SE</option>
									<option value="SR">SR</option>
									<option value="SW">SW</option>
									<option value="WC">WC</option>
									<option value="WR">WR</option>
									
									
									
										
				
								</select>
								
							
							</tr>
							
							
							<tr>
						<s:submit key="label.submit.login" align="center" name="submit" value="Submit"></s:submit>

							</tr>
							
							<tr width="50%"><td id="error"><b bgcolor="#FF0000"><s:fielderror/></b></td></tr>


						</table>

					</s:form>
				</td>
</tr>

</table> --%>

<jsp:include page="/Jspforms/footer.jsp"/>
</body>


 
 
</html>