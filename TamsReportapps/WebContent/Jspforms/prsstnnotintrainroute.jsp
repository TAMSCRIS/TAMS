<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fetch Report Data Details:</title>
<style type="text/css">
.divstyle {
	max-width: 1100px;
	height: 50px;
/* 	background-color: #E0E0E1; */
}

.scroll {
 
    width: 1330px;
    height: 400px;
    overflow: scroll; 
}


.button {
    background-color:#F0F8FF;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}

</style>

<script type="text/javascript">






	function clickReport(val) {

		//var e = document.getElementById("generaterptPDF");
	
		if(val=="PDF"){
			document.getElementById("tamsconvertlist").value="PDF";
			}
		else if(val=="EXCEL"){
			document.getElementById("tamsconvertlist").value="EXCEL";
			}
		else{
			document.getElementById("tamsconvertlist").value="TEXT";
			}
	
		var frm = document.getElementById("tamsform") || null;
		
			frm.action = 'downloaddata';
			document.getElementById("tamsform").submit();
		
	}
</script>
</head>
<jsp:include page="/Jspforms/header.jsp"/>
<div style="height:680px; overflow:auto">
<body>
	<s:form name="tamsform" id="tamsform" action="" method="post">
		<div class="flex-container">
			<input type="hidden" name="tamsrlylist" value="<s:property value="tamsrlylist"/>" /> 
			<input type="hidden" name="tamsrpt" value="<s:property value="tamsrpt"/>" />
			 <input type="hidden" name="tamsproj" value="<s:property value="tamsproj"/>" />
			<input type="hidden" name="tamsrptlist" value="<s:property value="tamsrptlist"/>" /> 
			<input type="hidden"	name="tamsmonth" value="<s:property value="tamsmonth"/>" />
		<input type="hidden"	name="tamsconvertlist"  value="" id="tamsconvertlist"/>
	<%-- 
	 <button  class="button" style="float: right;"> <a href="<s:url action="verifyaction"/>">HOME</a></button> --%>
        <h2 align ="center">PRS List of Stations not lying inTrain Route </h2>
        <h3 align = "center">
     <%int i =1; %>
     <s:iterator value="dataList">
			<%
			
			if(i==1)
				{
				%>			
			<s:property value="d" />
				<%
				}
			i++;
			%>	
			
	</s:iterator>
     
     </h3>
        
        <h3 align = "center" > <s:property value="tamsmonth " /> - <s:property value=" tamsrpt" /> </h3>
    
     
     
     <h3 align = "right">Figures in Units</h3>

			<div>
				<table width="100%" border="2px">
					<thead>
						<tr style="background-color: #E0E0E1;">
							<th>Train Number</th>
							<th>From Station</th>
							<th>From Station Code</th>
							<th>To Station</th>
							<th>To Station Code</th>
							<th>Enroute Station</th>
							<th>Distance</th>
							<th>Fare</th>

						</tr>
					</thead>
					<s:iterator value="dataList">
						<tr>
							<td align="center"><s:property value="trainno" /></td>
							<td align="center"><s:property value="fromStn" /></td>
							<td align="center"><s:property value="fromstncode" /></td>
							<td align="center"><s:property value="tostn" /></td>
							<td align="center"><s:property value="tostncode" /></td>
							<td align="center"><s:property value="entstn" /></td>
							<td align="center"><s:property value="dist" /></td>
							<td align="center"><s:property value="fare" /></td>
						</tr>
					</s:iterator>
					<tr> </tr>
					
							
													
				</table>
				<br>
				<br>
				<table width="100%" border="2px">
				<tr> 
					<td align="center"><b>Total Fare</b></td>
					<td align="center"> <b> <s:property value="sumr1" /> </b> </td>
					
				</tr>			
				</table>
				<br>
				
			</div>

		</div>
	</s:form>
</body>
</div>
<div class="divstyle">
					<b>Download Report as:</b>
					<button onclick="clickReport(this.value)" value="PDF" id="generatePDF" style="height: 2em; width: 50px;">PDF</button>
					<button onclick="clickReport(this.value)" value="EXCEL" id="generateExcel" style="height: 2em; width: 50px;">Excel</button>
					<button onclick="clickReport(this.value)" value="TEXT" id="generateText" style="height: 2em; width: 50px;">Text</button>
				</div>
<jsp:include page="/Jspforms/footer_1.jsp"/>
</html>
