<%-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>

<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@ taglib uri="/WEB-INF/lib/jstl" prefix="c"%>
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<HEAD>
<title>Redirect to TAMS</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<!-- <logic:redirect forward="Welcome" /> -->
</head>
<body>

<c:redirect url="/Jspforms/login.jsp"/>


<%-- <%

    response.sendRedirect("http://localhost:9080/TamsReportapps/Jspforms/login.jsp");
%> --%>
</body>
</html>