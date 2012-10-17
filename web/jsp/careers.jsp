<%--
    Document   : careers, career page for website
    Created on : Jul 15, 2012, 11:28:02 PM
    Author     : Debasis Jana
--%>
<%@page import="org.pratyasha.erp.login.*"%>
<%@page import="org.pratyasha.erp.career.dao.*"%>
<%@page import="org.pratyasha.erp.career.entity.*"%>
<%@page import="org.pratyasha.erp.sql.*"%>
<%@page import="org.pratyasha.erp.util.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%!
//opening details abbreviation limit
private static final int OPENING_DETAILS_LIMIT = 150;
//opening list limit
private static final int OPENING_LIST_LIMIT = 10;
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
<title>Craeer details</title>
</head>
<body>
<table  width="100%"><tr><td align="center" style="padding: 3px;">
<table>
<tr><td id="outer_td">
<table  id="frame" width="100%">
<tr><td valign="top">
<%if(login && loginDetails.getUserType().equals("E")) {%><table cellpadding="0" id="submenu"><tr><td>Create Post</td></tr></table><%}%>
</td><td valign="top"><table cellpadding="2" cellspacing="2" width="100%"><tr><td>
<table><tr><td><h1>Openings @ Pratasyha Technology, more header details</h1></td></tr>
<tr><td style="background-color: #ffffef;"><table  id="data">
<%
String start = request.getParameter("w"); //start index (from where ...)
int s_idx = new Integer(start == null ? "0" : start);
List<Job> openings = new CareerDao(DBUtil.getDataSource("ptech")).currentOpenings(s_idx);
int s = openings.size();
if(s == 0) {%><tr><td style="color:red"><%=s_idx == 0 ? "No recent openings found..." : ("No more openings found ..<a href='careers.jsp?w=" + (s_idx - OPENING_LIST_LIMIT) + "&mc=" + mc+ "'>&lt;&lt;back</a>")%></td></tr><%} else {
for(Job job: openings) {
//Calendar date = Calendar.getInstance();
//date.setTime(job.getDate());
Date date = job.getDate();
SimpleDateFormat sdf = new SimpleDateFormat("dd/dd/yyyy");
String details = job.getDetails();
String detailsHTML = null;
if(details.length() > OPENING_DETAILS_LIMIT) {
String s_details = PratyashaUtil.escapedHTML(details.substring(0, OPENING_DETAILS_LIMIT)); //short details
detailsHTML = "<span>" + s_details + "...</span>" + "<a href='javascript:void(0);' onclick=\"var more=this.innerHTML=='more';this.previousSibling.innerHTML=more?'" + PratyashaUtil.escapedHTML(details) + "':'" + PratyashaUtil.escapedHTML(s_details)+ "...';this.innerHTML=more?'hide':'more';\">more</a>";
} else detailsHTML = PratyashaUtil.escapedHTML(details);
%>
<tr><td nowrap ><span style="font-weight: bold;"><%=job.getPost()%></span> (Posted on: <span style="font-weight: bold;"><%=sdf.format(date)%></span>)</td></tr>
<tr><td style="padding-bottom: 13px;"><%=detailsHTML%></td></tr>
<%}%><tr><td style="padding-bottom: 7px;"><table><tr><%if(s_idx > 0){%><td style="padding-right: 7px;"><a href="careers.jsp?w=<%=s_idx%>&mc=<%=mc%>">&lt;&lt;back</a></td><%} else if(s == OPENING_LIST_LIMIT) {%><td><a href="careers.jsp?w=<%=(s_idx + OPENING_LIST_LIMIT)%>&mc=<%=mc%>">more jobs..</a></td><%}%></tr></table></td></tr><%}%>
</table></td></tr></table>
</td></tr><tr><td style="/*border-bottom: 1px solid gray;*/">
<table><tr><td><h1>Looking for a job, contact @ Pratasyha Technology</h1></td></tr>
<tr><td><img src="<%=appName%>/images/common/job search.jpg" alt="company profile" style="float: left;"/><span class="info">More details goes here ....</span></td></tr></table>
</td></tr><tr><td style="/*border-bottom: 1px solid gray;*/">
<table><tr><td><h1>Why Pratasyha Technology, more header details</h1></td></tr>
<tr><td><img src="<%=appName%>/images/common/company profile 1.jpg" alt="company profile" style="float: left;"/><span class="info">More details goes here ....</span></td></tr></table>
</td></tr><tr><td style="/*border-bottom: 1px solid gray;*/">
<table><tr><td><h1>Life @ Pratasyha Technology, more header details</h1></td></tr>
<tr><td><img src="<%=appName%>/images/common/corporate.jpg" alt="life" style="float: left;"/><span class="info">More details goes here ....</span></td></tr></table>
</td></tr></table></td>
<td id="news" valign="top" align="right"></td>
</tr></table></td></tr></table></td></tr></table>
<script type="text/javascript">COMMON.loadNews(0);</script>
</body>
</html>
<jsp:include page="bottom.jsp" />