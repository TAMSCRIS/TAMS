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

<%-- 	 <button  class="button" style="float: right;"> <a href="<s:url action="verifyaction"/>">HOME</a></button>	 --%>
        <h2 align="center">STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (UTS)  </h2>
        <h3 align="center"> (Originating zone wise)  </h3>
        <h3 align="center"><s:property value="tamsmonth"/> - <s:property value=" tamsrpt"/>  </h3>
        <h3 align="right"> Figures in Units  </h3>

			<div>
			<table width="100%" border="2px">
			<th width="4%"></th>
				<th width="32%">INPUT RECORDS</th>
				<th width="32%">MATCH RECORDS</th>
				<th width="32%">UNMATCH RECORDS</th>
			
			
			
			
			</table>
				<table width="100%" border="2px">
					<thead>
						<tr style="background-color: #E0E0E1;">
							<th>RLY</th>
							<th>Records</th>
							<th>Full Fare</th>
							<th>Safety Charges</th>
							<th>Other Charges</th>
							<th>Records</th>
							<th>Full Fare</th>
							<th>Safety Charges</th>
							<th>Other Charges</th>
							<th>Records</th>
							<th>Full Fare</th>
							<th>Safety Charges</th>
							<th>Other Charges</th>
								

						</tr>
					</thead>
					<s:iterator value="dataList">
						<tr>
							<td align="center" style="font-weight:bold"><s:property value="rly" /></td>
							<td align="center"><s:property value="itotalrecords" /></td>
							<td align="center"><s:property value="ifare" /></td>
						    <td align="center"><s:property value="isafetycharges" /></td>
							<td align="center"><s:property value="iothercharges" /></td>
							<%-- <td align="center"><s:property value="icatgchg" /></td> --%>
							<td align="center"><s:property value="mtotalrecords" /></td>
							<td align="center"><s:property value="mfare" /></td>
							<td align="center"><s:property value="msafetycharges" /></td>
							<td align="center"><s:property value="mothercharges" /></td>
							<%-- <td align="center"><s:property value="mcatgchg" /></td> --%>
							<td align="center"><s:property value="utotalrecords" /></td>
							<td align="center"><s:property value="ufare" /></td>
							 <td align="center"><s:property value="usafetycharges" /></td>
							<td align="center"><s:property value="uothercharges" /></td>
							
						
						</tr>
					</s:iterator>
					<tr>
					 <td style="font-weight:bold">Total</td>
					 <td align="center" style="font-weight:bold"><s:property value="sum1" /></td>
					 <td align="center" style="font-weight:bold"><s:property value="sum2" /></td>
					<td align="center" style="font-weight:bold"><s:property value="sum3" /></td> 
					 <td align="center" style="font-weight:bold"><s:property value="sum4" /></td>
					 <td align="center" style="font-weight:bold"><s:property value="sum5" /></td>
					 <td align="center" style="font-weight:bold"><s:property value="sum6" /></td>
					<td align="center" style="font-weight:bold"><s:property value="sum7" /></td>
					<td align="center" style="font-weight:bold"><s:property value="sum8" /></td> 
					 <td align="center" style="font-weight:bold"><s:property value="sum9" /></td> 
					<td align="center" style="font-weight:bold"><s:property value="sum10" /></td>
					 <td align="center" style="font-weight:bold"><s:property value="sum11" /></td>
					 <td align="center" style="font-weight:bold"><s:property value="sum12" /></td>
				     <%-- <td align="center"><s:property value="sum13" /></td> 
					 <td align="center"><s:property value="sum14" /></td>
					 <td align="center"><s:property value="sum15" /></td> --%>
					
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