<%-- 
    Document   : home, home page for website
    Created on : Jul 15, 2012, 11:28:02 PM
    Author     : Debasis Jana
--%>
<%@page import="org.pratyasha.erp.login.*"%>
<%@page import="org.pratyasha.erp.util.*"%>
<%@page import="org.pratyasha.erp.user.command.*"%>
<%
String appName = request.getContextPath(); //application name
String mc = request.getParameter("mc");
mc = mc == null ? "0" : mc;

LoginDetails loginDetails = PratyashaUtil.login(request);
boolean login = loginDetails != null;

//special tricks to bind user object initially
if(request.getAttribute("user") == null) request.setAttribute("user", new User());
%>
<jsp:include page="top.jsp">
<jsp:param name="mc" value="<%=mc%>" />
</jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page</title>
<link href="<%=appName%>/css/common.css" rel="stylesheet" />
<style type="text/css">
td.header {
font-size: 18px;
font-family: Verdana, Arial, Helvetica, sans-serif;
font-weight: normal;
font-style: normal;
color: black;
padding: 3px;
}
</style>
<script type="text/javascript" src="<%=appName%>/js/common/calendar.js"></script>
<script type="text/javascript">
//loads submenu
function loadSubmenu(url) {
window.location.href=url;
}
</script>
</head>
<body>
<table  width="100%"><tr><td align="center" style="padding: 3px;">
<table>
<tr><td id="outer_td">
<table  id="frame" width="100%">
<tr><td valign="top" style="background-color: #ffffef;">
<%if(login) {%><table cellpadding="0" id="submenu" style="width: 90px;"><tr><td onclick="loadSubmenu('<%=appName%>/query4user.htm?a=p');">Profile</td></tr><%
//String user_type = loginDetails.getUserType();
//String id = loginDetails.getUserId();
boolean adm = PratyashaUtil.adm(request);
if(adm) { //employee
%><tr><td>CV Sortlisting</td></tr><!--<tr><td>Employees</td></tr>--><%
}%></table><%
} else {%><form:form name="NewUserForm" action="/PratashyaTech/user.htm" method="POST" enctype="multipart/form-data" commandName="user" ><table cellpadding="3"><tr><td><h2>Sign up</h2></td></tr><tr><td class="label">email<span style="color:red">*</span></td></tr><tr><td><form:input path="email" size="13" /></td></tr>
<tr><td><form:errors path="email" cssClass="error" /></td></tr>
<tr><td class="label">Password<span style="color:red">*</span></td></tr><tr><td><input type="password" name="password" size="15" value="" /></td></tr>
<tr><td><form:errors path="password" cssClass="error" /></td></tr>
<tr><td class="label">Confirm Password<span style="color:red">*</span></td></tr><tr><td><input type="password" name="confirm" size="15" value="" /></td></tr>
<tr><td class="label">Name<span style="color:red">*</span></td></tr><tr><td><form:textarea path="name" cols="17" rows="2" /></td></tr>
<tr><td><form:errors path="name" cssClass="error" /></td></tr>
<tr><td class="label">Sex<span style="color:red">*</span></td></tr><tr><td><form:select path="sex"><form:option value="" label="--Select--" /><form:option value="M" label="Male" /><form:option value="F" label="Female" /><form:option value="O" label="Others" /></form:select></td></tr>
<tr><td><form:errors path="sex" cssClass="error" /></td></tr>
<tr><td class="label">Date of Birth<span style="color:red">*</span></td></tr><tr><td><input type="text" name="dob" size="10" onclick="datePicker(event);" readonly value='<fmt:formatDate pattern="yyyy/mm/dd" value="${user.dob}" />' /></td></tr>
<tr><td class="label">Resume<span style="color:red">*</span></td></tr><tr><td><input type="file" name="resume" size="17" /></td></tr>
<tr><td><form:errors path="resume" cssClass="error" /></td></tr>
<tr><td><input type="button" value=" Register " onclick="this.form.submit();" /><td></tr>
</table></form:form><%}%></td>
<td valign="top"><table cellpadding="2" cellspacing="2"><tr><td style="border-bottom: 1px solid gray;">
<table><tr><td><h1>Pratasyha Technology, more header details</h1></td></tr>
<tr><td><span class="info">More details goes here ....</span></td></tr></table>
</td></tr><tr><td><table cellspacing="3" cellpadding="0"><tr><td style="padding: 5px;background: url('<%=appName%>/images/common/bg_services.gif') repeat-x left top">
<table cellpadding="2" cellspacing="2"><tr><td style="background: url('<%=appName%>/images/common/bg_services_h1.gif') repeat-x left top" class="header">Careers</td></tr>
<tr><td><img src="<%=appName%>/images/common/service_recruiting.jpg" alt="careers" /></td></tr><tr><td><span class="info">Your brief info on careers ... <a href="<%=appName%>/jsp/careers.jsp?title=Careers&mc=1">more</a></span></td></tr>
</table></td><td style="padding: 5px;background: url('<%=appName%>/images/common/bg_services.gif') repeat-x left top">
<table cellpadding="2" cellspacing="2"><tr><td style="background: url('<%=appName%>/images/common/bg_services_h1.gif') repeat-x left top" class="header">Consulting</td></tr>
<tr><td><img src="<%=appName%>/images/common/service_consulting.jpg" alt="consulting" /></td></tr><tr><td><span class="info">Your brief info on consulting ... <a href="<%=appName%>/jsp/consulting.jsp?title=Consulting services&mc=3">more</a></span></td></tr>
</table></td></tr></table></td></tr></table></td>
<td id="news" valign="top" align="right"></td>
</tr></table></td></tr></table></td></tr></table>
<script type="text/javascript">COMMON.loadNews(0);</script>
</body>
</html>
<jsp:include page="bottom.jsp" />