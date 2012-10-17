<%-- 
    Document   : goals, milestone(goal) details page for website
    Created on : Jul 15, 2012, 11:28:02 PM
    Author     : Debasis Jana
--%>
<%
String appName = request.getContextPath(); //application name
String mc = request.getParameter("mc");
mc = mc == null ? "0" : mc;
%>
<jsp:include page="top.jsp">
    <jsp:param name="mc" value="<%=mc%>" />
</jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Our goals</title>
<link href="<%=appName%>/css/common.css" rel="stylesheet" />
</head>
<body>
<table  width="100%"><tr><td align="center" style="padding: 3px;">
<table>
<tr><td id="outer_td">
<table  id="frame" width="100%">
<tr><td valign="top"><table cellpadding="2" cellspacing="2" width="100%"><tr><td style="/*border-bottom: 1px solid gray;*/">
<table><tr><td><h1>Goals of Pratasyha Technology, more header details</h1></td></tr>
<tr><td><img src="<%=appName%>/images/common/vision 1.jpg" alt="goals" style="float: left;"/><span class="info">More details goes here ....</span></td></tr></table>
</td></tr><tr><td>
<table><tr><td><h1>Our resources to reach hard targets, more header details</h1></td></tr>
<tr><td><img src="<%=appName%>/images/common/resources.png" alt="resources" style="float: left;"/><span class="info">More details goes here ....</span></td></tr></table>
</td></tr></table></td>
<td id="news" valign="top" align="right"></td>
</tr></table></td></tr></table></td></tr></table>
<script type="text/javascript">COMMON.loadNews(0);</script>
</body>
</html>
<jsp:include page="bottom.jsp" />