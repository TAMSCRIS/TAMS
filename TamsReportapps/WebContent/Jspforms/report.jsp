<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>


<%-- <%
String displaymodule="";
displaymodule=request.getAttribute("currentmodule")!=null?request.getAttribute("currentmodule").toString():"";
/* message=request.getAttribute("msg")!=null?request.getAttribute("msg").toString():""; */
%> --%>

<script type="text/javascript">



history.forward(); 


function gettamscode(x){
var h=document.getElementById("tamsrpt").value;
	//alert(h);
	
document.getElementById("tamsyearcode").value=x;
var frm = document.getElementById("tamsform") || null;
if(frm) {
    frm.action = 'tamsmonthaction';
    document.getElementById("tamsform").submit();
}

}

function gettamsmonth(y){
	
	 document.getElementById("tamsmonthcode").value=y;
	var frm = document.getElementById("tamsform") || null;
	if(frm) {
		
	    frm.action = 'tamsearnaction';
	
	    document.getElementById("tamsform").submit();
	 }

	}

function gettamsgauge(y){
	
	 document.getElementById("gaugelist").value=y;
	
	
	}  
 
 function gettamsproj(y){
	//alert("1  "+y);
	
	 document.getElementById("tamsprojcode").value=y;
	 //alert(" tamsprojcode"+tamsprojcode);
	var frm = document.getElementById("tamsform") || null;
	//alert("2  "+x);
	if(frm) {
		//alert("3  "+x);
	    frm.action = 'tamsreportaction';
	   // alert("4  "+x);
	    document.getElementById("tamsform").submit();
	   // alert("5  "+x);
	 }

	} 

function gettamsprojlist(z){

	 document.getElementById("tamslist").value=z;

			var frm = document.getElementById("tamsform") || null;

	//alert("2  "+x);
	if(frm) {
		//alert("3  "+x);
	    frm.action = 'tamsrlyaction';
	   // alert("4  "+x);
	    document.getElementById("tamsform").submit();
	   // alert("5  "+x);
	 }

	}


function gettamsrlylist(a){
//	alert("1  "+z);
	 document.getElementById("trlylist").value=a;
	//alert("trlylist"+trlylist);
	var frm = document.getElementById("tamsform") || null;
	//alert("2  "+x);
	if(frm) {
		//alert("3  "+x);
	    frm.action = 'tamsconvertaction';
	   // alert("4  "+x);
	    document.getElementById("tamsform").submit();

	 }

	}


 function clickReport() {
	
     var errormsg="";
	 var year = document.getElementById("tamsrpt");
	 if (year.value=="")
	 {
		 
		 errormsg="Please Select an year";
		 document.getElementById("errorBox").innerHTML = errormsg;
		 //document.getElementById("tamsform").submit();
	     return false;
	 }

	 var month = document.getElementById("tamsmonth");
	 //var result = document.getElementById('tamsrpt').value;
	 if (month.value=="")
	 {
		 
		 errormsg="Please Select a Month";
		 document.getElementById("errorBox").innerHTML = errormsg;
		 //document.getElementById("tamsform").submit();
	     return false;
	 }

	 var proj = document.getElementById("tamsproj");
	 //var result = document.getElementById('tamsrpt').value;
	 if (proj.value=="")
	 {
		 
		 errormsg="Please Select a Project";
		 document.getElementById("errorBox").innerHTML = errormsg;
		 //document.getElementById("tamsform").submit();
	     return false;
	 }

	 var proj = document.getElementById("tamsproj");
	 //var result = document.getElementById('tamsrpt').value;
	 if (proj.value=="")
	 {
		 
		 errormsg="Please Select a Project";
		 document.getElementById("errorBox").innerHTML = errormsg;
		 //document.getElementById("tamsform").submit();
	     return false;
	 }
	 var report = document.getElementById("tamsrptlist");
	 //var result = document.getElementById('tamsrpt').value;
	 if (report.value=="")
	 {
		 
		 errormsg="Please Select a Report";
		 document.getElementById("errorBox").innerHTML = errormsg;
		 //document.getElementById("tamsform").submit();
	     return false;
	 }
	 var rly = document.getElementById("tamsrlylist");
	 //var result = document.getElementById('tamsrpt').value;
	 if (rly.value=="")
	 {
		 
		 errormsg="Please Select a Zone";
		 document.getElementById("errorBox").innerHTML = errormsg;
		 //document.getElementById("tamsform").submit();
	     return false;
	 }

	 var e = document.getElementById("generaterpt");
		var frm = document.getElementById("tamsform") || null;
		//alert("2  "+x);
		if(frm) {
			//alert("3  "+x);
		    frm.action = 'generateaction';
		   // alert("4  "+x);
		    document.getElementById("tamsform").submit();
}

}

function gettamsfun() {
	if(document.getElementById("tamslist").value=="PRS Statement 6A part 1"|| document.getElementById("tamslist").value=="PRS Statement 6A part 2" || document.getElementById("tamslist").value=="UTS Statement 6A part 1"|| document.getElementById("tamslist").value=="UTS Statement 6A part 2"||document.getElementById("tamslist").value=="PRS Statement 6A part 2 Item_6"||document.getElementById("tamslist").value=="UTS Statement 6A part 2 Item_6")
	{
     document.getElementById("gauge").style.visibility="visible";
	}
	else
			{
			document.getElementById("gauge").style.visibility="hidden";
			}

	
var ab = document.getElementById("tamsyearcode").value;
document.getElementById("tamsrpt").value=ab;

var bc = document.getElementById("tamsmonthcode").value;
document.getElementById("tamsmonth").value=bc;

var cd = document.getElementById("tamsprojcode").value;
document.getElementById("tamsproj").value=cd;
 
var xy = document.getElementById("tamslist").value;
document.getElementById("tamsrptlist").value=xy;

var xyz = document.getElementById("trlylist").value;
document.getElementById("tamsrlylist").value=xyz;

var abc = document.getElementById("convertlist").value;
document.getElementById("tamsconvertlist").value=abc;


var abg = document.getElementById("gaugelist").value;
//alert(abg);
document.getElementById("gaugevalue").value=abg;



}




	
</script>





<html>
<head>
<style type="text/css">

#button {
    width: 5em;
    border: 2px solid green;
    background: #ffe;
    border-radius: 1px;
}


</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>tams Report</title>


</head>
<jsp:include page="/Jspforms/header.jsp"/>
<body onload="gettamsfun()" >

<!-- <table border="2"  width ="50%" height=50> -->
<div class="flex-container">
<s:form name = "tamsform" id="tamsform" action="" method="post">
<s:hidden label="tamsmonthcode" id="tamsmonthcode" name="tamsmonthcode"/> 
<s:hidden label="tamsyearcode" id="tamsyearcode" name="tamsyearcode"/> 
<s:hidden label="tamscode" id="tamscode" name="tamscode"/>
<s:hidden label="tamsprojcode" id="tamsprojcode" name="tamsprojcode"/>
<s:hidden label="tamslist" id="tamslist" name="tamslist"/>
<s:hidden label="trlylist" id="trlylist" name="trlylist"/>
<s:hidden label="convertlist" id="convertlist" name="convertlist"/>
<s:hidden label="gaugelist" id="gaugelist" name="gaugelist"/>
<s:hidden label="zone_name" id="zone_name" name="zone_name"/>
<!-- <table border="2"  width ="70%" height=100> -->
<!-- <div  style="border:3px solid black;padding:15px;font-size:18px" align="center"> -->
<div  align="center">
<div id="errorBox" style="font-size: 13px; color: red;"></div>
<br>
				<b>Select Year: <font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
				<select style="border-radius:200px;" style="width:100px" name="tamsrpt" id="tamsrpt" onchange="gettamscode(this.value)" <font color="red">*</font>>
				<option  value="">[Select Year]</option>
				<s:iterator value="yearlist" >
				<option value='<s:property value="reportyear"/>'><s:property value="reportyear"/>
				</option>
				</s:iterator>
				</select>
		<br>
		<br>
				<b>Select Month:<font color="red">*</font>&nbsp;&nbsp;&nbsp;</b>
				<select style="border-radius: 10px;" name="tamsmonth" id="tamsmonth" onchange="gettamsmonth(this.value)">
				<option  value="">[Select Month]</option>
				<s:iterator value="monthlist" >
				<option value='<s:property value="reportmonth"/>'><s:property value="reportmonth"/>
				</option>
				</s:iterator>
				</select>
		<br>
		<br>
				<b>Project Type:<font color="red">*</font>&nbsp;&nbsp;&nbsp;</b>
				<select style="border-radius: 10px;" name="tamsproj" id="tamsproj" onchange="gettamsproj(this.value)">
				<option  value="">[Select Project]</option>
				<s:iterator value="projectlist" >
				<option value='<s:property value="earnprojecttype"/>'><s:property value="earnprojecttype"/>
				</option>
				</s:iterator>
				</select>
			<br><br>
				<b>Select Report:<font color="red">*</font>&nbsp;&nbsp;&nbsp;</b>
				<select style="border-radius: 10px;"  name="tamsrptlist" id="tamsrptlist" onchange="gettamsprojlist(this.value)">
				<option value="">[Select Report]</option>
				<s:iterator value="reportlist">
				<!-- <option value="4">Select Report</option> -->
				<option value= '<s:property value="reporttype"/>'><s:property value="reporttype"/>
				</option>
			    </s:iterator>
				</select>
			<br>
	
			<br>	
				<b>Select Zone:<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
				<select style="border-radius: 10px;" name="tamsrlylist" id="tamsrlylist" onchange="gettamsrlylist(this.value)">
				<option value="">[Select Railway]</option>
				<s:iterator value="tamsrailaylist">
				<option value= '<s:property value="zone"/>'><s:property value="zone"/>
				</option>
				</s:iterator>
				</select>
			<br>
			<br>	
				<%-- <b>Format Type:&nbsp;&nbsp;&nbsp;</b>
				<select style="border-radius:5px;" name="tamsconvertlist" id="tamsconvertlist" onchange="gettamsconvertlist(this.value)">
				<option value="">[Select Format]</option>
				<s:iterator value="tamsconvertreportlist">
				<option value= '<s:property value="reportconverttype"/>'><s:property value="reportconverttype"/>
				</option>
				</s:iterator>
				</select> --%>
			<div id="gauge">
			<br>
			<b>Gauge:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
			<select style="border-radius: 10px;"  onchange="gettamsgauge(this.value)" name="gaugevalue" id="gaugevalue">
				<option value="">[Select gauge]</option>
				<option value="B">Broad Gauge</option>
				<option value="M">Metre Gauge</option>
				<option value="N">Narrow Gauge</option>
				</select>
			<br>
			<br>
			<br>
			</div>
			<button align="centre" onclick="return clickReport()" id="generaterpt" style="height:2em; width:150px;">View Report</button>
		</div>
		<br>
		<br>
			
		</s:form>
		</div>
		<br>
		<br>
		<br>
		
</body>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>


<jsp:include page="/Jspforms/footer.jsp"/>

</html>