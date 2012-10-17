/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

var BLOG = {
    //appName: null,
    url: null,

    //intialization
    init: function() {
        //this.appName = appName;
        this.url = COMMON.appName + "/blog.htm";
    },

    //loads blog details for a given blog ID
    //t: table, u: current user code
    loadBlogDetails: function(id, t, u) {
        new Ajax.Request(this.url + "?a=bd&id=" + id , {
            onSuccess: function(response) {
                var d = eval(response.responseText); //JSON.prase should be used
                var s = d.length;
                if(s > 0) { //data found
                    var HTML = [];
                    HTML.push("<table border=0 cellpadding=0 cellspacing=0>");
                    for(var i = 0; i < s; i++) {
                        HTML.push("<tr><td nowrap><span style='font-weight: bold;'>" + d[i].user + "</span> on <span style='font-weight: blod;'>" + d[i].time + "</span></td></tr>");
                        HTML.push("<tr><td style='padding-bottom: 9px;'>" + d[i].message + "</td></tr>");
                    }
                    HTML.push("</table>");
                    t.innerHTML = HTML.join("");
                } else t.innerHTML = ''; //empty
            }, onFailure: function() {
                t.innerHTML = "<span style='color: red'>Blog details can't be loaded</span>";
            }
        });
        //t.innerHTML = "loading blog details...";
        COMMON.drawLoading(t, 'loading blog details...');
    }
};