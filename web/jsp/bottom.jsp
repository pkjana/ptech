<%-- 
    Document   : bottom, bototm page included in every page
    Created on : Jul 15, 2012, 11:22:07 PM
    Author     : Debasis Jana
--%>
<%@page import="org.pratyasha.erp.login.*"%>
<%@page import="java.util.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%
String appName = request.getContextPath(); //application name
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Footer</title>
<%--<link href="<%=appName%>/css/common.css" rel="stylesheet" />--%>
<style type="text/css">
<%--#linktab a{
font-size: 10px;
font-family: Helvetica, sans-serif;
font-weight: bold;
font-style: normal;
color: #6c9ab5;
}--%>
#linktab td {
padding-right: 27px;
}
#linktab span {
--border-left: solid gray 1px;
}
#footer {
font-size: 12px;
font-family: Helvetica, sans-serif;
font-weight: normal;
font-style: normal;
color: gray;
}
</style>
</head>
<body>
<table  width="100%" style="margin: 35px 0px 25px 0px;"><tr><td align="center">
<table>
<tr><td style="border-top: 1px solid gray;" id="outer_td">
<table cellpadding="1"><tr><td style="padding: 5px;"><span id="footer">&copy; 2012 Pratyasha Technology (Kaharagpur - 722140), All rights are reserved.</span></td></tr>
<tr><td><table  id="linktab"><tr><%
List<Map<String, Object>> menu_details = (List)application.getAttribute("MENU_DETAILS");
int s = menu_details.size();
//menu drawing
for(int i = 0; i < s; i++) {
    Map<String, Object> menu = menu_details.get(i);
    String menu_code = menu.get("menu_id").toString();
    String display = (String)menu.get("display"); //display name
    String tooltip = (String)menu.get("tooltip");
    String url = (String)menu.get("url");
    %><td><a href="<%=appName%>/jsp/<%=url%>&mc=<%=menu_code%>" <%=!tooltip.isEmpty() ? ("title='" + tooltip + "'") : ""%> ><%=display%></a></td><%
}%><td><a href="<%=appName%>/jsp/terms_of_use.jsp?title=Terms or use">Terms of Use</a></td><td><a href="<%=appName%>/jsp/privacy_policy.jsp?title=Privacy policy">Privacy Policy</a></td></tr></table></td></tr></table>
</td></tr></table></td></tr></table>
</body>
</html>