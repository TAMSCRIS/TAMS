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
<jsp:include page="/Jspforms/header.jsp"/>
<div style="height:680px; overflow:auto">
<body >
	<s:form name="tamsform" id="tamsform" action="" method="post">
		<div class="flex-container">
			<input type="hidden" name="tamsrlylist" value="<s:property value="tamsrlylist"/>" /> 
			<input type="hidden" name="tamsrpt" value="<s:property value="tamsrpt"/>" />
			 <input type="hidden" name="tamsproj" value="<s:property value="tamsproj"/>" />
			<input type="hidden" name="tamsrptlist" value="<s:property value="tamsrptlist"/>" /> 
			<input type="hidden"	name="tamsmonth" value="<s:property value="tamsmonth"/>" />
		<input type="hidden"	name="tamsconvertlist"  value="" id="tamsconvertlist"/>
	<%--  <button  class="button" style="float: right;"> <a href="<s:url action="verifyaction"/>">HOME</a></button>		 --%>
     <%--    <h1 align="center"><s:property value="tamsrptlist" /></h1> --%>
     
     <h2 align ="center">SUMMARY OF  APPORTIONMENT OF CATERING CHARGES FOR ALL INDIAN RAILWAYS (PRS)  </h2>
     <h3 align = "center" > <s:property value="tamsmonth " /> - <s:property value=" tamsrpt" /> </h3>
     <h3 align = "right">Figures in Units</h3>

			<div>
				<table width="100%" border="2px">
					<thead>
						<tr style="background-color: #E0E0E1;">
							<th>ZONE</th>
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
							<th>RETAINED SHARE</th>
							<th>INWARD SHARE</th>
							<th>TOTAL APPORT EARNING</th>
							<!-- <th>OUTWARD</th> -->

						</tr>
					</thead>
					<% int i=0; %>
					
					<s:iterator value="dataList">
						<tr>
						 <td align="center" style="font-weight:bold"><s:property value="zone" /></td>
						  <%--   <td align="center"><s:property value="tamsrlylist" /></td> --%>
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
							<%
							
							if(i==17)
							{
							%>
							
							<td align="center" style="font-weight:bold"><s:property value="retained" /></td>
							<td align="center" style="font-weight:bold"><s:property value="inward" /></td>
							<%}
							
							else
							{
							%>
							
							<td align="center" ><s:property value="retained" /></td>
							<td align="center" ><s:property value="inward" /></td>
							<%}
							%>
							
							
							
							<%
							
							if(i<17)
							{
							%>
							<td align="center"><s:property value="totalappotioned" /></td>
							<%}
							else
							{
							%>
							<td align="center" style="font-weight:bold"><s:property value="totalappotioned" /></td>
							<%}
							
							i++;%>
							<%-- <td align="center"><s:property value="outward"/></td> --%>
						</tr>
					</s:iterator>
					<%--  <tr>
					
					<td style="font-weight:bold">Unmatched Share</td>
					<td >0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
						<td align="center" style="font-weight:bold"><s:property value="sumr18" /></td>
							<td align="center" style="font-weight:bold"><s:property value="sumr19" /></td>
							<td align="center" style="font-weight:bold"><s:property value="sumr20" /></td>				
					</tr>
						<tr>
						<td align="center" style="font-weight:bold">Orignating Amount</td>
						    <td align="center"><s:property value="tamsrlylist" /></td>
							<td align="center"><s:property value="sumr1" /></td>
							<td align="center"><s:property value="sumr2" /></td>
							<td align="center"><s:property value="sumr3" /></td>
							<td align="center"><s:property value="sumr4" /></td>
							<td align="center"><s:property value="sumr5" /></td>
							<td align="center"><s:property value="sumr6" /></td>
							<td align="center"><s:property value="sumr7" /></td>
							<td align="center"><s:property value="sumr8" /></td>
							<td align="center"><s:property value="sumr9" /></td>
							<td align="center"><s:property value="sumr10" /></td>
							<td align="center"><s:property value="sumr11" /></td>
							<td align="center"><s:property value="sumr12" /></td>
							<td align="center"><s:property value="sumr13" /></td>
							<td align="center"><s:property value="sumr14" /></td>
							<td align="center"><s:property value="sumr15" /></td>
							<td align="center"><s:property value="sumr16" /></td>
							<td align="center"><s:property value="sumr17" /></td>
							<td align="center"><s:property value=" " /></td>	
							<td align="center"><s:property value=" " /></td>	
							<td align="center" style="font-weight:bold"><s:property value="sumr20" /></td>	
						
							</tr>
							<tr>
						<td align="center" style="font-weight:bold">Outward Share</td>
						    <td align="center"><s:property value="tamsrlylist" /></td>
							<td align="center"><s:property value="sum1" /></td>
							<td align="center"><s:property value="sum2" /></td>
							<td align="center"><s:property value="sum3" /></td>
							<td align="center"><s:property value="sum4" /></td>
							<td align="center"><s:property value="sum5" /></td>
							<td align="center"><s:property value="sum6" /></td>
							<td align="center"><s:property value="sum7" /></td>
							<td align="center"><s:property value="sum8" /></td>
							<td align="center"><s:property value="sum9" /></td>
							<td align="center"><s:property value="sum10" /></td>
							<td align="center"><s:property value="sum11" /></td>
							<td align="center"><s:property value="sum12" /></td>
							<td align="center"><s:property value="sum13" /></td>
							<td align="center"><s:property value="sum14" /></td>
							<td align="center"><s:property value="sum15" /></td>
							<td align="center"><s:property value="sum16" /></td>
							<td align="center"><s:property value="sum17" /></td>
							<td align="center"><s:property value=" " /></td>
							<td align="center"><s:property value=" " /></td>
							<td align="center" style="font-weight:bold"><s:property value="outwardsum" /></td>
							</tr>
			
					<tr>
						<td align="center" style="font-weight:bold">Retained</td>
						<s:iterator value="dataList">
						    <td align="center"><s:property value="tamsrlylist" /></td>
							<td align="center"><s:property value="retained" /></td>
								
							</s:iterator>
							<td><s:property value=" " /></td>
							<td><s:property value="  " /></td>
							<td align="center" style="font-weight:bold"><s:property value="sumr18" /></td>
				</tr>
					<tr>
						<td align="center" style="font-weight:bold">Inward Share</td>
						<s:iterator value="dataList">
						    <td align="center"><s:property value="tamsrlylist" /></td>
							<td align="center"><s:property value="inward" /></td>
							</s:iterator>
								<td><s:property value=" " /></td>
							<td><s:property value="  " /></td>
								<td align="center" style="font-weight:bold"><s:property value="sumr19" /></td>
							<td></td>
					</tr>
					<tr>
						<td align="center" style="font-weight:bold">Total Apport. Earnings</td>
						<s:iterator value="dataList">
						    <td align="center"><s:property value="tamsrlylist" /></td>
							<td align="center"><s:property value="totalappotioned" /></td>
							</s:iterator>
								<td><s:property value=" " /></td>
							<td><s:property value="  " /></td>
							<td align="center" style="font-weight:bold"><s:property value="sumr20" /></td>
					</tr> --%>
							
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
 