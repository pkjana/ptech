<%-- 
    Document   : clients, client details page for website
    Created on : Jul 15, 2012, 11:28:02 PM
    Author     : Debasis Jana
--%>
<%@page import="org.pratyasha.erp.login.*"%>
<%@page import="org.pratyasha.erp.client.dao.*"%>
<%@page import="org.pratyasha.erp.client.entity.*"%>
<%@page import="org.pratyasha.erp.sql.*"%>
<%@page import="org.pratyasha.erp.util.*"%>
<%@page import="java.util.*"%>
<%!
//clients are horizontally listed
private static final int CLIENTS_HORIZONTAL_LIMIT = 3;
%>
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
<title>Client list</title>
</head>
<body>
<table  width="100%"><tr><td align="center" style="padding: 3px;">
<table>
<tr><td id="outer_td">
<table  id="frame" width="100%">
<tr><td valign="top">
<%if(login && loginDetails.getUserType().equals("E")) {%><table cellpadding="0" id="submenu"><tr><td>Add Client</td></tr></table><%}%>
</td><td valign="top"><table cellpadding="2" cellspacing="2" width="100%"><tr><td>
<table><tr><td><h1>Complete list of our clients, more header details</h1></td></tr>
<tr><td><table  id="data">
<%
List<Client> clients = new ClientDao(DBUtil.getDataSource("ptech")).clients();
int s = clients.size();
if(s > 0){
int c = 0;
outer:
while(c < s) {
%><tr><%
for(int i = 0; i < CLIENTS_HORIZONTAL_LIMIT; i++) {
Client client = clients.get(c++);
%>
<td><table><tr><td><a href="<%=client.getUrl()%>" target="_blank"><img style="cursor:pointer" src="<%=appName%>/client.htm?a=l&c=<%=client.getCode()%>" alt="logo" /></a></td><td valign="bottom"><%=client.getDescription()%></td></tr></table></td>
<%
if(c == s) break outer;
}%></tr><%
}
} else {
%><tr><td><span style="color:red;">No client list found, sorry!!</span></td></tr><%
}
%>
</table></td></tr></table>
</td></tr></table></td>
<td id="news" valign="top" align="right"></td>
</tr></table></td></tr></table></td></tr></table>
<script type="text/javascript">COMMON.loadNews(0);</script>
</body>
</html>
<jsp:include page="bottom.jsp" />