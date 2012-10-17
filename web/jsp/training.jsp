<%-- 
    Document   : training, training details page for website
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
<title>Tarining details</title>
<link href="<%=appName%>/css/common.css" rel="stylesheet" />
</head>
<body>
<table  width="100%"><tr><td align="center" style="padding: 3px;">
<table>
<tr><td id="outer_td">
<table  id="frame" width="100%">
<tr><td valign="top"><table cellpadding="2" cellspacing="2" width="100%"><tr><td style="/*border-bottom: 1px solid gray;*/">
<table><tr><td><h1>Training @ Pratasyha Technology, more header details</h1></td></tr>
<tr><td><img src="<%=appName%>/images/common/training 4.jpg" alt="training" style="float: left;"/><span class="info">More details goes here ....</span></td></tr></table>
</td></tr><tr><td>
<table><tr><td><h1>Top faculty list @ Pratasyha Technology, more header details</h1></td></tr>
<tr><td><img src="<%=appName%>/images/common/computer-instructor.jpg" alt="top faculty" style="float: left;"/><span class="info">More details goes here ....</span></td></tr></table>
</td></tr><%--<tr><td>
<table><tr><td><h1>Success stories of our trainees, more header details</h1></td></tr>
<tr><td><img src="<%=appName%>/images/common/success.png" alt="success stories" style="float: left;"/><span class="info">More details goes here ....</span></td></tr></table>
</td></tr>--%></table></td>
<td id="news" valign="top" align="right"></td>
</tr></table></td></tr></table></td></tr></table>
<script type="text/javascript">COMMON.loadNews(0);</script>
</body>
</html>
<jsp:include page="bottom.jsp" />