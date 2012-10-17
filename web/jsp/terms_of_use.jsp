<%-- 
    Document   : home, terms & condition details page
    Created on : Jul 16, 2012, 12:30 AM
    Author     : Debasis Jana
--%>
<%
//String appName = request.getContextPath(); //application name
//String mc = request.getParameter("mc");
//mc = mc == null ? "0" : mc;
%>
<jsp:include page="top.jsp" />
<%--<jsp:param name="mc" value="<%=mc%>" />
</jsp:include>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Terms & condition</title>
<%--<link href="<%=appName%>/css/common.css" rel="stylesheet" />--%>
</head>
<body>
<table  width="100%"><tr><td align="center">
<table>
<tr><td id="outer_td"><h1>Terms and condition</h1></td></tr>
<tr><td><span class="info">More details goes here</span></td></tr>
</table></td></tr></table>
</body>
</html>
<jsp:include page="bottom.jsp" />