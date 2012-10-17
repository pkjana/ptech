<%-- 
    Document   : top, This is the top page included in every page. It contains menu and company logo.
    Created on : Jul 15, 2012, 10:02:45 AM
    Author     : Debasis Jana
--%>
<%@page import="org.pratyasha.erp.login.*"%>
<%@page import="org.pratyasha.erp.util.*"%>
<%@page import="java.util.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<!DOCTYPE HTML>
<%
String appName = request.getContextPath(); //application name

LoginDetails loginDetails = PratyashaUtil.login(request);
boolean login = loginDetails != null;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String title = request.getParameter("title");
if(title == null) title = (String)request.getAttribute("title");
if(title == null) title="";
%>
<title>Pratayasha Technology<%=!title.isEmpty() ? (" (" + title + ")") : ""%></title>
<style type="text/css">
#logo_left {
--background: url('<%=appName%>/images/logo/company logo left 2.JPG') no-repeat left top;
width: 890px;
height: 200px;
}
<%--#logo_right {
background: url('<%=appName%>/images/logo/company logo right 2.JPG') repeat-x left top;
}--%>
#menutab td {
font-size: 12px;
font-family: Verdana, Arial, Helvetica, sans-serif;
font-weight: normal;
font-style: normal;
color: white;
background-color: #4c6baf;
padding-top: 7px;
padding-bottom: 7px;
padding-left: 22px;
padding-right: 22px;
}
#menutab td:hover {
color: black;
background-color: #fdd913;
cursor: pointer;
}
#menutab td.active {
color: black;
background-color: #fdd913;
}
<%--#menutab td.inactive {
color: white;
background-color: #4c6baf;
}--%>
</style>
<link href="<%=appName%>/css/common.css" rel="stylesheet" />
<script type="text/javascript" src="<%=appName%>/js/core/prototype.js"></script>
<script type="text/javascript" src="<%=appName%>/js/common/common.js"></script>
<script type="text/javascript">COMMON.init('<%=appName%>');</script>
<script type="text/javascript">
<%--
 var IMAGES = [];
IMAGES["Home"] = ["logo/company logo left 2.JPG"];
IMAGES["Careers"] = ["common/careers.jpg", "common/careers.jpg", "common/careers_graphic.jpg", "common/career.jpg"];
IMAGES["Training"] = ["common/training.jpg", "common/training-marquee.png", "common/header_training.jpg", "common/training 1.jpg", "common/training3.jpg", "common/career-banner.jpg"];
IMAGES["Consulting"] = ["common/mh_bldngbusiness.gif", "common/mh_bldngbusiness.gif", "common/consulting.jpg", "common/header_consulting.jpg", "common/positions-services-banner.jpg", "common/consulting 2.jpg", "common/A_solutions.jpg"];
IMAGES["Blogs"] = ["common/banner-blog.jpg"];
IMAGES["Milestones"] = ["common/milestones.jpg", "common/milestones.en.jpg", "common/vision.jpg", "common/products.png", "common/"];
IMAGES["Clients"] = ["common/partners.en.jpg", "common/Clients_banner.jpg", "common/clients-banner.jpg", "common/clients.jpg", "common/Innerpage_Banner_Testimonials.jpg",
                     "common/clients.en.jpg", "common/clients 1.jpg", "common/clients-header.jpg", "common/Clients_1.jpg", "common/support services.gif", "common/mh_clients.jpg",
                     "common/support.jpg", "common/customer_support.jpg", "common/IT-enabled-service.en.jpg", "common/services.en.jpg"];
IMAGES["Site Map"] = ["common/paverasoft-web-sitemap.jpg", "common/sitemap.jpg"];
IMAGES["About Us"] = ["common/about us2.png", "common/about-us.en.jpg", "common/about_imge.jpg", "common/our-team.en.jpg", "common/about_us.jpg"];
IMAGES["Contact"] = ["common/helpdesk.jpg"];
//menu details
var MENUS = [
{display: "Home", href: "<%=appName%>"},
{display: "Careers", href: ""},
{display: "Training", href: ""},
{display: "Consulting", href: ""},
{display: "Blogs", href: ""},
{display: "Milestones", href: ""},
{display: "Clients", href: ""},
{display: "Site Map", href: ""},
{display: "About Us", href: ""},
{display: "Contact", href: ""},
];
var ACTIVE_MENU = null; //active menu ref
//loads menu dynamically
function loadMenu() {
    var logo = $("logo_left");
    var l = MENUS.length;
    var t = $("menutab");
    var tr = t.insertRow(0);
    //menus drawing
    for(var i = 0; i < l; i++) {
        var td = tr.insertCell(i);
        //label
        var label = MENUS[i].display;
        td.innerHTML = label;
        //onclick handler to load respective page
        td.onclick = (function(){
            var idx = i;
            var TD = td;
            var LABEL = label;
            return function() {
                //deactivate previous menu
                if(ACTIVE_MENU != null) ACTIVE_MENU.className = "inactive";
                TD.className = 'active';
                ACTIVE_MENU = TD;
                //loads random image
                var imgs = IMAGES[LABEL];
                var r_idx = Math.round(Math.random() * (imgs.length -1) ); //random index
                logo.style.backgroundImage = 'url("<%=appName%>/images/' + imgs[r_idx] + '")';
                //loads actual page
                //window.location.href = MENUS[idx].href;
            };
        })();
    }
}
--%>
//loads page
//@param mc=>menu code
function loadMenu(url, mc) {
window.location.href = "<%=appName%>/jsp/" + url + "&mc=" + mc
}
</script>
<script type="text/javascript" src="<%=appName%>/js/common/calendar.js"></script>
</head>
<body onload="/*loadMenu();*/">
<table  width="100%"><tr><td align="center"><table>
<tr><td><table  width="100%"><tr><td><%if(!login){%><table><tr><td id="login_td1"><a style="padding-left:7px;font-weight:normal;font-size: 12px;" href="javascript:void(0);" onclick="$('login_td2').style.display='block';$('login_td1').style.display='none';$('forgotpass_td1').style.display='none';var u=$('login_user');u.value=$('login_pass').value='';u.focus();">Sign in</a></td><%--<td><span class="info" style="color:red;">${lem}</span></td>--%><td id="login_td2" style="display:none;"><form name="LoginForm" action="<%=appName%>/login.htm?a=l" method="POST"><table cellpadding="2"><tr><td class="label">User: </td><td><input type="text" size="13" name="user" id="login_user"/></td><td class="label">Password: </td><td><input type="password" size="13" name="pass" id="login_pass" /></td><td style="padding-left: 5px;"><input type="button" value=" Sign in " title="Click to sign in" onclick="this.form.submit();" /></td><td valign="middle"><%--<img src="<%=appName%>/images/common/close.jpg" style="width:15px;height:16px;cursor:pointer" title="cancel" alt="close" />--%><a href="javascript:void(0)" onclick="$('login_td2').style.display='none';$('login_td1').style.display='block';$('forgotpass_td1').style.display='block';" >back</a></td></tr></table></form></td>
<td><table><tr><td id="forgotpass_td1"><a style="padding-left:7px;font-weight:normal;font-size: 12px;" href="javascript:void(0);" onclick="$('forgotpass_td2').style.display='block';$('forgotpass_td1').style.display='none';$('login_td1').style.display='none';var b=$('code4pass');b.value='';b.focus();">Forgot password?</a></td><td id="forgotpass_td2" style="display:none;"><table><tr><td class=label>User: </td><td><input type="text" size="13" id="code4pass"/></td><td><input type="button" value=" Ok " onclick="COMMON.password(this, $('code4pass'));" /></td><td valign="middle"><%--<img src="<%=appName%>/images/common/close.jpg" style="width:15px;height:16px;cursor:pointer" title="cancel" alt="close" />--%><a href="javascript:void(0)" onclick="$('forgotpass_td2').style.display='none';$('forgotpass_td1').style.display='block';$('login_td1').style.display='block';">back</a></td></tr></table></td></tr></table></td></tr></table>
<%}else{%><table cellpadding="2"><tr><td class="label">Welcome to <span style="font-weight: bold;"><%=loginDetails.getUserName()%></span></td><td><a style="padding-left:7px;font-weight:normal;font-size: 12px;" href="<%=appName%>/login.htm?a=lo">Sign out</a></td><td><a style="padding-left:7px;font-weight:normal;font-size: 12px;" href="Javascript:void(0);" onclick="">Change password</a></td></tr></table><%}%></td><td align="right" style="padding-right: 7px;"><table cellpadding="2"><tr><td><input type="text" size="15" id="search-string" /></td><td>
<input type="button" value=" Search " title="Click to search" onclick=""/><%--<img src="<%=appName%>/images/common/Search.png" alt="search" style="width:30px;height:30px;cursor:pointer;" title="Click to search" />--%>
</td></tr></table></td></tr></table></td></tr><tr><td id="logo_left"></td><!--<td id="logo_right"></td>--></tr>
<tr><td><table cellpadding="0" cellspacing="1" id="menutab" width="100%"><tr><%
String menu_id = request.getParameter("mc"); //menu code
List<Map<String, Object>> menu_details = (List)application.getAttribute("MENU_DETAILS");
int s = menu_details.size();
//matched index
int f_idx = menu_id == null || menu_id.equals("0") ? 0 : -1; //0, hard coded (home page)
//menu drawing
for(int i = 0; i < s; i++) {
    Map<String, Object> menu = menu_details.get(i);
    boolean active = false; //menu active
    String menu_code = menu.get("menu_id").toString();
    if(f_idx == -1 && menu_code.equals(menu_id)) {
        f_idx = i;
        active = true;
    }
    String display = (String)menu.get("display"); //display name
    String tooltip = (String)menu.get("tooltip");
    String url = (String)menu.get("url");
    %><td class='<%=active ? "active" : ""%>' <%=!tooltip.isEmpty() ? ("title='" + tooltip + "'") : ""%> onclick="loadMenu('<%=url%>', <%=menu_code%>);"><%=display%></td><%
}
//random image set
int r_idx = -1;
String images[] = null;
if(f_idx != -1) {
    Map<String, Object> menu = menu_details.get(f_idx);
    images = ((String)menu.get("images")).split(",");
    Random rnd = new Random();
    r_idx = rnd.nextInt(images.length); //image random index
}
%></tr></table></td></tr>
</table></td></tr></table>
<script type="text/javascript">
$("logo_left").style.backgroundImage = "url('<%=appName%>/images/<%=r_idx != -1 ? images[r_idx] : "logo/company logo right 2.JPG"%>')";
</script>
</body></html>