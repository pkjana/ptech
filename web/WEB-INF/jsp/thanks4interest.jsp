<%-- 
    Document   : thanks4interest, Thanking message when query submitted from a user
    Created on : Jul 17, 2012, 8:01:40 PM
    Author     : Debasis Jana
--%>
<%
String appName = request.getContextPath(); //application name
%>
<jsp:include page="../../jsp/top.jsp">
<jsp:param name="mc" value="0" />
</jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thanks for your interest</title>
</head>
<body>
<table  width="100%"><tr><td align="center" style="padding: 3px;">
<table>
<tr><td id="outer_td"><table  width="100%"><tr><td align="center"><img src="<%=appName%>/images/common/thankyou_200.jpg" alt="thank you" /></td></tr><tr><td align="center"><h1>Our whole team thanking you for your interest in our company</h1></td></tr><tr><td align="center"><span class="info"><%=request.getAttribute("message")%></span></td></tr></table></td></tr></table></td></tr></table>
</body>
</html>
<jsp:include page="../../jsp/bottom.jsp" />