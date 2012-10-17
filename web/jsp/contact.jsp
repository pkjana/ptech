<%-- 
    Document   : contact, contact us page for website
    Created on : Jul 15, 2012, 11:28:02 PM
    Author     : Debasis Jana
--%>
<%@page import="org.pratyasha.erp.login.*"%>
<%@page import="org.pratyasha.erp.util.*"%>
<%
String appName = request.getContextPath(); //application name
String mc = request.getParameter("mc");
mc = mc == null ? "0" : mc;

LoginDetails loginDetails = PratyashaUtil.login(request);
boolean login = loginDetails != null;
%>
<jsp:include page="top.jsp">
<jsp:param name="mc" value="<%=mc%>" />
</jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact us</title>
</head>
<body>
<table  width="100%"><tr><td align="center" style="padding: 3px;">
<table>
<tr><td id="outer_td">
<table  id="frame" width="100%">
<tr><td valign="top"><table cellpadding="2" cellspacing="2" width="100%"><tr><td style="/*border-bottom: 1px solid gray;*/">
<table><tr><td><h1>Helpdesk @ Pratasyha Technology, more header details</h1></td></tr>
<tr><td><img src="<%=appName%>/images/common/helpdesk 1.jpg" alt="helpdesk" style="float: left;"/><span class="info">More details goes here ....</span></td></tr></table>
</td></tr><%if(!login) {%><tr><td>
<table><tr><td><h1>Contact us (Submit your query) , more header details</h1></td></tr><tr><td><form action="<%=appName%>/common.htm?a=q" method="POST">
<table>
<tr><td class="label">Name</td></tr><tr><td><textarea name="name" cols="17" rows="2"></textarea></td></tr>
<tr><td class="label">email</td></tr><tr><td><input type="text" name="email" /></td></tr>
<tr><td class="label">Phone</td></tr><tr><td><input type="text" name="phone" /></td></tr>
<tr><td class="label">Message</td></tr><tr><td><textarea name="message" rows="11" cols="37"></textarea></td></tr>
<tr><td style="padding-top: 5px;"><input type="button" value=" Submit Query " onclick=this.form.submit();"" /></td></tr>
</table></form></td></tr>
<tr><td></td></tr></table>
</td></tr><%}%></table></td>
<td id="news" valign="top" align="right"></td>
</tr></table></td></tr></table></td></tr></table>
<script type="text/javascript">COMMON.loadNews(0);</script>
</body>
</html>
<jsp:include page="bottom.jsp" />