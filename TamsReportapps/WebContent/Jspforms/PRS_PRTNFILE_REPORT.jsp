 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fetch Report Data Details:</title>
<style type="text/css">
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

.scroll {
 
    width: 1330px;
    height: 400px;
    overflow: scroll; 
}

.divstyle {
	max-width: 1100px;
	height: 50px;
	/* background-color: #E0E0E1; */
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
	//	alert(tamsconvertlist);
		var frm = document.getElementById("tamsform") || null;
		
			frm.action = 'downloaddata';
			document.getElementById("tamsform").submit();
		
	}
</script>
</head>
<div>
<jsp:include page="/Jspforms/header.jsp"/>
</div>
<div style="height:680px; overflow:auto">
<body >
<div >
	<s:form name="tamsform" id="tamsform" action="" method="post">
		<div class="flex-container">
			<input type="hidden" name="tamsrlylist" value="<s:property value="tamsrlylist"/>" /> 
			<input type="hidden" name="tamsrpt" value="<s:property value="tamsrpt"/>" />
			 <input type="hidden" name="tamsproj" value="<s:property value="tamsproj"/>" />
			<input type="hidden" name="tamsrptlist" value="<s:property value="tamsrptlist"/>" /> 
			<input type="hidden"	name="tamsmonth" value="<s:property value="tamsmonth"/>" />
		<input type="hidden"	name="tamsconvertlist"  value="" id="tamsconvertlist"/>

     
     <h2 align ="center">PRS PRTNFILE REPORT  </h2>
      <h3 align = "center" > <s:property value="FullZoneName" /></h3>
     <h3 align = "center"  >Passenger Apportionment of PRS for the month of  <s:property value="tamsmonth " /> - <s:property value=" tamsrpt" /> </h3>
     <h3 align = "right">Figures in Rupees</h3>

			<div>
				<table  width="100%" border="2px" >
					<thead>
						<tr style="background-color: #E0E0E1;">
							<th>RLY TO</th>
							<th>GAUGE CODE</th>
							<th>AMOUNT</th>
							<th>CR</th>
							<th>EC</th>
							<th>EO</th>
							<th>ER</th>
							<th>KR</th>
							<th>NC</th>
							<th>NE</th>
							<th>NF</th>
							<th>NR</th>
							<th>NW</th>
							<th>SB</th>
							<th>SC</th>
							<th>SE</th>
							<th>SR</th>
							<th>SW</th>
							<th>WC</th>
							<th>WR</th>
							<th>TOTAL SHARED</th>
							<th>DIFF</th>
						</tr>
					</thead>
					
					<% int i =0 ; %>
					<s:iterator value="dataList">
					
					<% if(i%4==3 && i!=0) 
					{
					%>
					<tr>
						<td align="center" style="font-weight:bold"><s:property value="torly" /></td>
							
							<td align="center"> <b><s:property value="orgg" /> </b></td>
							<td align="center"><b><s:property value="amount" /></b></td>
							<td align="center"><b><s:property value="CR" /></b></td>
							<td align="center"><b><s:property value="EC" /></b></td>
							<td align="center"><b><s:property value="EO" /></b></td>
							<td align="center"><b><s:property value="ER" /></b></td>
							<td align="center"><b><s:property value="KR" /></b></td>
							<td align="center"><b><s:property value="NC" /></b></td>
							<td align="center"><b><s:property value="NE" /></b></td>
							<td align="center"><b><s:property value="NF" /></b></td>
							<td align="center"><b><s:property value="NR" /></b></td>
							<td align="center"><b><s:property value="NW" /></b></td>
							<td align="center"><b><s:property value="SB" /></b></td>
							<td align="center"><b><s:property value="SC" /></b></td>
							<td align="center"><b><s:property value="SE" /></b></td>
							<td align="center"><b><s:property value="SR" /></b></td>
							<td align="center"><b><s:property value="SW" /></b></td>
							<td align="center"><b><s:property value="WC" /></b></td>
							<td align="center"><b><s:property value="WR" /></b></td>
							<td align="center"><b><s:property value="total_shared" /></b></td>
							<td align="center"><b><s:property value="diff" /></b></td>
							
							
					</tr>
					<%
					}
					else
					{
					%>
					
					<tr>
						<td align="center" style="font-weight:bold"><s:property value="torly" /></td>
							
							<td align="center"> <b><s:property value="orgg" /> </b></td>
							<td align="center"><s:property value="amount" /></td>
							<td align="center"><s:property value="CR" /></td>
							<td align="center"><s:property value="EC" /></td>
							<td align="center"><s:property value="EO" /></td>
							<td align="center"><s:property value="ER" /></td>
							<td align="center"><s:property value="KR" /></td>
							<td align="center"><s:property value="NC" /></td>
							<td align="center"><s:property value="NE" /></td>
							<td align="center"><s:property value="NF" /></td>
							<td align="center"><s:property value="NR" /></td>
							<td align="center"><s:property value="NW" /></td>
							<td align="center"><s:property value="SB" /></td>
							<td align="center"><s:property value="SC" /></td>
							<td align="center"><s:property value="SE" /></td>
							<td align="center"><s:property value="SR" /></td>
							<td align="center"><s:property value="SW" /></td>
							<td align="center"><s:property value="WC" /></td>
							<td align="center"><s:property value="WR" /></td>
							<td align="center"><s:property value="total_shared" /></td>
							<td align="center"><s:property value="diff" /></td>
							
							
					</tr>
					
					
						
					<%
					}
					
					i++;
					%>
					
					
					</s:iterator>
					
							
				</table>
				<br>
				</div>
				
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
 