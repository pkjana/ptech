<%-- 
    Document   : profile, User profile
    Created on : Jul 19, 2012, 9:20:39 PM
    Author     : Debasis Jana
--%>
<%@page import="org.pratyasha.erp.user.command.*"%>
<jsp:include page="../../../jsp/top.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<%
String appName = request.getContextPath(); //application name
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Profile</title>
</head>
<body>
<table  width="100%"><tr><td align="center">
<table>
<tr><td id="outer_td"><table width="100%"><tr><td><table width="100%">
<tr><td><h1>User profile</h1></td></tr>
<tr><td><form:form action="/PratashyaTech/user.htm" method="POST" enctype="multipart/form-data" commandName="user" ><form:errors path="*" /><table cellpadding="3"><tr><td class="label">${user.email}</td></tr>
<tr><td class="label">Name<span style="color:red">*</span></td></tr><tr><td><form:textarea path="name" cols="17" rows="2" /></td></tr>
<tr><td><form:errors path="name" cssClass="error" /></td></tr>
<tr><td class="label">Sex<span style="color:red">*</span></td></tr><tr><td><form:select path="sex"><form:option value="" label="--Select--" /><form:option value="M" label="Male" /><form:option value="F" label="Female" /><form:option value="O" label="Others" /></form:select></td></tr>
<tr><td><form:errors path="sex" cssClass="error" /></td></tr>
<tr><td class="label">Phone</td></tr><tr><td><form:input path="phone" size="11" /></td></tr>
<tr><td><form:errors path="phone" cssClass="error" /></td></tr>
<tr><td class="label">Address</td></tr><tr><td><form:textarea path="address" rows="2" cols="17" /></td></tr>
<tr><td><form:errors path="address" cssClass="error" /></td></tr>
<tr><td class="label">Date of Birth<span style="color:red">*</span></td></tr><tr><td><input type="text" name="dob" size="10" onclick="datePicker(event);" readonly value='<fmt:formatDate pattern="yyyy/mm/dd" value="${user.dob}" />' /></td></tr>
<tr><td><table><tr><td class="label">Resume</td><td rowspan="2"><a href="<%=appName%>/common.htm?a=urd&c=${loginDetails.userID}" target="_blank"><img style="padding-left: 7px;" src="<%=appName%>/images/common/icon_resume.png" alt="resume" title="click to download resume" /></a></td></tr><tr><td><input type="file" name="resume" size="17" /></td></tr></table></td></tr>
<tr><td><form:errors path="resume" cssClass="error" /></td></tr>
<tr><td class="label">Photo</td></tr><tr><td ><input type="file" name="photo" size="17" /></td></tr>
<tr><td><form:errors path="photo" cssClass="error" /></td></tr>
<tr><td class="label">Sign</td></tr><tr><td ><input type="file" name="sign" size="17" /></td></tr>
<tr><td><form:errors path="sign" cssClass="error" /></td></tr>
<tr><td><input type="button" value=" Update " onclick="this.form.submit();" /><td></tr>
</table></form:form></td></tr>
</table></td>
<td id="news" valign="top" align="right"></td>
</tr></table></td></tr></table></td></tr></table>
<script type="text/javascript">COMMON.loadNews(0);</script>
</body>
</html>
<jsp:include page="../../../jsp/bottom.jsp" />