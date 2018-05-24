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
/* 	background-color: #E0E0E1; */
}
bold{
font-weight: bold 
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
	  <button  class="button" style="float: right;"> <a href="<s:url action="verifyaction"/>">HOME</a></button>	 --%>	
     <%--    <h1 align="center"><s:property value="tamsrptlist" /></h1> --%>
     
     <h2 align ="center">SUMMARY OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (UTS) </h2>
     <h3 align = "center" > <s:property value="tamsmonth " /> - <s:property value=" tamsrpt" /> </h3>
     <h3 align = "right">Figures in 000s</h3>

			<div>
				<table width="100%" border="2px">
					<tr>
					<thead>
						<tr style="background-color: #E0E0E1;">
							<th>ORGRLY</th>
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
							<th>INWARD</th>
							<th>TOTAL APPORT EARNING</th> 
						</tr>
					</thead>
					
					<%-- <s:iterator value="dataList">
						<tr>
						<td align="center"><s:property value="zone" /></td>
						    <td align="center"><s:property value="tamsrlylist" /></td>
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
							<td align="center"><s:property value="retained" /></td>
							<td align="center"><s:property value="inward" /></td>
							<td align="center"><s:property value="totalappotioned" /></td>
							<td align="center"><s:property value="outward"/></td>
						</tr>
					</s:iterator>
					 --%>
					
			
													
					<tr>
					
						<td align="center" style="font-weight:bold">CR</td>
						<s:iterator value="dataList">
										
							<td align="center"><s:property value="CR" /></td>
								
							</s:iterator>
							<%int i=0; %>
							<s:iterator value="dataList">
							<%
							if(i == 0)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							i++;
							%>
							</s:iterator>
							<td align="center"><s:property value="cr" /></td>
							<td align="center"><s:property value="sumr1" /></td>
							
				   </tr>
				   
				   <tr>
					
						<td align="center" style="font-weight:bold">EC</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="EC" /></td>
								
							</s:iterator>
							<%int j=0; %>
								<s:iterator value="dataList">
							<%
							if(j==1)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							j++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="ec" /></td>
							<td align="center"><s:property value="sumr2" /></td>
							
							
				   </tr>
				   
				   <tr>
					
						<td align="center" style="font-weight:bold">EO</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="EO" /></td>
								
							</s:iterator>
								<%int k=0; %>
								<s:iterator value="dataList">
							<%
							if(k==2)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							k++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="eo" /></td>
							<td align="center"><s:property value="sumr3" /></td>
				   </tr>
				      <tr>
					
						<td align="center" style="font-weight:bold">ER</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="ER" /></td>
								
							</s:iterator>
								<%int l=0; %>
								<s:iterator value="dataList">
							<%
							if(l==3)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							l++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="er" /></td>
							<td align="center"><s:property value="sumr4" /></td>
				   </tr>
				      <tr>
					
						<td align="center" style="font-weight:bold">KR</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="KR" /></td>
								
							</s:iterator>
								<%int m=0; %>
								<s:iterator value="dataList">
							<%
							if(m==4)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							m++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="kr" /></td>
							<td align="center"><s:property value="sumr5" /></td>
				   </tr>
				      <tr>
					
						<td align="center" style="font-weight:bold">NC</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="NC" /></td>
							</s:iterator>
								<%int n=0; %>
								<s:iterator value="dataList">
							<%
							if(n==5)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							n++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="nc" /></td>
							<td align="center"><s:property value="sumr6" /></td>
				   </tr>
				      <tr>
					
						<td align="center" style="font-weight:bold">NE</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="NE" /></td>
							</s:iterator>
								<%int o=0; %>
								<s:iterator value="dataList">
							<%
							if(o==6)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							o++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="ne" /></td>
							<td align="center"><s:property value="sumr7" /></td>
				   </tr>
				      <tr>
					
						<td align="center" style="font-weight:bold">NF</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="NF" /></td>
							</s:iterator>
								<%int p=0; %>
								<s:iterator value="dataList">
							<%
							if(p==7)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							p++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="nf" /></td>
							<td align="center"><s:property value="sumr8" /></td>
				   </tr>
				     
				      <tr>
					
						<td align="center" style="font-weight:bold">NR</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="NR" /></td>
							</s:iterator>
								<%int q=0; %>
								<s:iterator value="dataList">
							<%
							if(q==8)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							q++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="nr" /></td>
							<td align="center"><s:property value="sumr9" /></td>
				   </tr>
				     
				      <tr>
					
						<td align="center" style="font-weight:bold">NW</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="NW" /></td>	
							</s:iterator>
								<%int r=0; %>
								<s:iterator value="dataList">
							<%
							if(r==9)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							r++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="nw" /></td>
							<td align="center"><s:property value="sumr10" /></td>
							
							
				   </tr>
				    
				      <tr>
					
						<td align="center" style="font-weight:bold">SB</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="SB" /></td>
								
							</s:iterator>
								<%int s=0; %>
								<s:iterator value="dataList">
							<%
							if(s==10)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							s++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="sb" /></td>
							<td align="center"><s:property value="sumr11" /></td>
				   </tr>
				     
				      <tr>
					
						<td align="center" style="font-weight:bold">SC</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="SC" /></td>
								
							</s:iterator>
								<%int t=0; %>
								<s:iterator value="dataList">
							<%
							if(t==11)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							t++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="sc" /></td>
							<td align="center"><s:property value="sumr12" /></td>
				   </tr>
				     
				      <tr>
					
						<td align="center" style="font-weight:bold">SE</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="SE" /></td>
								
							</s:iterator>
								<%int u=0; %>
								<s:iterator value="dataList">
							<%
							if(u==12)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							u++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="se" /></td>
							<td align="center"><s:property value="sumr13" /></td>
				   </tr>
				    
				      <tr>
					
						<td align="center" style="font-weight:bold">SR</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="SR" /></td>
								
							</s:iterator>
								<%int v=0; %>
								<s:iterator value="dataList">
							<%
							if(v==13)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							v++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="sr" /></td>
							<td align="center"><s:property value="sumr14" /></td>
				   </tr>
				     
				      <tr>
					
						<td align="center" style="font-weight:bold">SW</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="SW" /></td>
								
							</s:iterator>
								<%int w=0; %>
								<s:iterator value="dataList">
							<%
							if(w==14)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							w++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="sw" /></td>
							<td align="center"><s:property value="sumr15" /></td>
				   </tr>
				    
				      <tr>
					
						<td align="center" style="font-weight:bold">WC</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="WC" /></td>
								
							</s:iterator>
								<%int x=0; %>
								<s:iterator value="dataList">
							<%
							if(x==15)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							x++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="wc" /></td>
							<td align="center"><s:property value="sumr16" /></td>
				   </tr>
				    
				      <tr>
					
						<td align="center" style="font-weight:bold">WR</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="WR" /></td>
								
							</s:iterator>
								<%int y=0; %>
								<s:iterator value="dataList">
							<%
							if(y==16)
							{
							%>
							
							<td align="center"><s:property value="retained" /></td>
							<%}
							y++;
							%>
							
							</s:iterator>
							<td align="center"><s:property value="wr" /></td>
							<td align="center"><s:property value="sumr17" /></td>
				   </tr>
				   <tr>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td></td>
				   <td align="center"  style="font-weight:bold"><s:property value="sumr18" /></td>
				   <td align="center"  style="font-weight:bold"><s:property value="sumr20" /></td>
				   <td align="center"  style="font-weight:bold"><s:property value="sumr19" /></td>	
				   <%-- <td align="center"  style="font-weight:bold"><s:property value="sumr22" /></td>	    --%>
			   
				   
				   </tr>
				  
				  <%-- <tr>
					
						<td align="center" style="font-weight:bold">PLATFORM</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="platform" /></td>	
							</s:iterator>
							
						<td></td>
				   		<td></td>
				   		
				   		<td align="center"  style="font-weight:bold"><s:property value="platform_sum" /></td>
				   
				   	
				   </tr> --%>
				  
				     
				     <tr>
					
						<td align="center" style="font-weight:bold">Platform Tickets</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="platform" /></td>
								
							</s:iterator>
							
						<td></td>
				   		<td></td>
				   		
				   		<td align="center"  style="font-weight:bold"><s:property value="platform_sum" /></td>
				   
				   	
				   </tr>
				     
				     
				      <tr>
					
						<td align="center" style="font-weight:bold">OGA</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="oga" /></td>
								
							</s:iterator>
							
						<td></td>
				   		<td></td>
				   		
				   		<td align="center"  style="font-weight:bold"><s:property value="oga_sum" /></td>
				   
				   	
				   </tr>
				     
				      <tr>
					
						<td align="center" style="font-weight:bold">Retained Share</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="retained" /></td>
								
							</s:iterator>
							
						<td></td>
				   		<td></td>
				   		<td align="center"  style="font-weight:bold"><s:property value="sumr18" /></td>
				   			
				   </tr>
				   
				    <tr>
					
						<td align="center" style="font-weight:bold">Outward</td>
						<s:iterator value="dataList">
							<td align="center"><s:property value="outward" /></td>
								
							</s:iterator>
							
						<td></td>
						 <td></td>
						 <td align="center"  style="font-weight:bold"><s:property value="outward_sum" /></td>
						 	
				   </tr>
				   
				  <tr>
				  <td align="center" style="font-weight:bold">Inward</td>
				  	<td align="center"><s:property value="cr" /></td>
				  	<td align="center"><s:property value="ec" /></td>
				  	<td align="center"><s:property value="eo" /></td>
				  	<td align="center"><s:property value="er" /></td>
				  	<td align="center"><s:property value="kr" /></td>
				  	<td align="center"><s:property value="nc" /></td>
				  	<td align="center"><s:property value="ne" /></td>
				  	<td align="center"><s:property value="nf" /></td>
				  	<td align="center"><s:property value="nr" /></td>
				  	<td align="center"><s:property value="nw" /></td>
				  	<td align="center"><s:property value="sb" /></td>
				  	<td align="center"><s:property value="sc" /></td>
				  	<td align="center"><s:property value="se" /></td>
				  	<td align="center"><s:property value="sr" /></td>
				  	<td align="center"><s:property value="sw" /></td>
				  	<td align="center"><s:property value="wc" /></td>
				  	<td align="center"><s:property value="wr" /></td>
				  <td ></td>
				  <td></td>
				  <td align="center"  style="font-weight:bold"><s:property value="sumr20" /></td>
		
				  
				  </tr>
				  
				  
				  
				    <tr>
				  <td align="center"  style="font-weight:bold">Total apport earnings</td>
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
				    <td></td>
				    <td></td>
				    <td align="center"  style="font-weight:bold"><s:property value="sumr19" /></td>
		
				  
				  </tr>  
				  
				</table>
				
				  <p align="center"  style="font-weight:bold"> 	Note: Originating Fare : Total Originating Earnings of Individual Railway as per EDP Data </p>
				  <p align="center"  style="font-weight:bold">        Outward Share : Earnings distributed to other Railways  </p>					   
				  <p align="center"  style="font-weight:bold">   	  Retained Share : Earnings retained by Home Railway (Local Traffic + Part of Foreign Traffic)    </p>
				  <p align="center"  style="font-weight:bold">        Inward Share   : Earnings received from all Railways    </p>
				  <p align="center"  style="font-weight:bold">        Total Apportionment Earnings : Retained Share + Inward Share    </p>
				  <p align="center"  style="font-weight:bold">  Note 1- Unmatched records earnings share have been included in orginating railway apportioned earnings.  </p>
				  <p align="center"  style="font-weight:bold">  Note 2- MUTP,MRTS,MMTS,CIDCO charges, Reservation slip, superfast surcharge slip charges are included in the apportioned share of orginating railway(Home Railway). </p>
		
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
 