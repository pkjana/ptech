<%-- 
    Document   : blogs, blogs for website
    Created on : Jul 15, 2012, 11:28:02 PM
    Author     : Debasis Jana
--%>
<%@page import="org.pratyasha.erp.login.*"%>
<%@page import="org.pratyasha.erp.blog.dao.*"%>
<%@page import="org.pratyasha.erp.blog.entity.*"%>
<%@page import="org.pratyasha.erp.sql.*"%>
<%@page import="org.pratyasha.erp.util.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%!
//blog list limit
private static final int BLOG_LIST_LIMIT = 10;
//blog details list limit
//private static final int BLOG_DETAILS_LIST_LIMIT = 10;
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
<title>Blogs</title>
<script type="text/javascript" src="<%=appName%>/js/blog/blog.js"></script>
<script type="text/javascript">BLOG.init();</script>
</head>
<body>
<table  width="100%"><tr><td align="center" style="padding: 3px;">
<table>
<tr><td id="outer_td">
<table  id="frame" width="100%">
<tr><td valign="top">
<%if(login && loginDetails.getUserType().equals("E")) {%><table cellpadding="0" id="submenu"><tr><td>Create Blog</td></tr><tr><td>My Blogs</td></tr></table><%}%>
</td><td valign="top"><table cellpadding="2" cellspacing="2" width="100%"><tr><td>
<table><tr><td><h1>Blogs of Pratasyha Technology, more header details</h1></td></tr>
<tr><td style="background-color: #ffffef;"><table  id="data">
<%
String start = request.getParameter("w"); //start index (from where ...)
int s_idx = new Integer(start == null ? "0" : start);
List<Blog> blogs = new BlogDao(DBUtil.getDataSource("ptech")).blogs(s_idx);
int s = blogs.size();
if(s == 0) {%><tr><td style="color:red" nowrap><%=s_idx == 0 ? "No recent blogs found..." : ("No more blogs found ..<a href='blogs.jsp?w=" + (s_idx - BLOG_LIST_LIMIT) + "&mc=" + mc+ "'>&lt;&lt;back</a>")%></td></tr><%} else {
    int c = 0;
for(Blog blog: blogs) {
    c++;
//Calendar date = Calendar.getInstance();
//date.setTime(job.getDate());
Date date = blog.getTime();
SimpleDateFormat sdf = new SimpleDateFormat("dd/dd/yyyy");
%>
<tr><td nowrap ><span style="font-weight: bold;"><%=blog.getSubject()%></span> (Posted on: <span style="font-weight: bold;"><%=sdf.format(date)%></span>, by: <span style="font-weight: bold"><%=blog.getUser()%></span>)</td></tr>
<tr><td><%=blog.getBody()%></td></tr>
<tr><td><table><tr><td id="blog_details_<%=c%>"></td></tr><%if(login) {%><tr><td><%--<a href="Javascript:void(0);" onclick="BLOG.edit(this);">comment</a>--%></td></tr><%}%></table></td></tr>
<tr><td style="padding-bottom: 13px;"><a href="Javascript:void(0);" onclick="var t=$('blog_details_<%=c%>');BLOG.loadBlogDetails(<%=blog.getId()%>, t);var more = this.innerHTML.indexOf('more') != -1;this.innerHTML=more?'hide details..':'more details';t.style.display=more?'block':'none';">more details..</a></td></tr>
<%}%><tr><td style="padding-bottom: 7px;"><table><tr><%if(s_idx > 0) {%><td style="padding-right: 7px;"><a href="blogs.jsp?w=<%=s_idx%>&mc=<%=mc%>">&lt;&lt;back</a></td><%} else if(s == BLOG_LIST_LIMIT) {%><td><a href="blogs.jsp?w=<%=(s_idx + BLOG_LIST_LIMIT)%>&mc=<%=mc%>">more blogs..</a></td><%}%></tr></table></td></tr><%}%>
</table></td></tr></table>
</td></tr></table></td>
<td id="news" valign="top" align="right"></td>
</tr></table></td></tr></table></td></tr></table>
<script type="text/javascript">COMMON.loadNews(0);</script>
</body>
</html>
<jsp:include page="bottom.jsp" />