/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

/**
 * Common utilities goes here like, news loading
 * @author Debasis Jana
 */
var COMMON = {
    appName: null, //app name
    url: null, //common url

    /**
     * Initiation
     * @param appName app name
     */
    init: function(appName) {
        this.appName = appName;
        this.url = appName + "/common.htm";
    },

    /**
     * Loads news on every page
     * @param start Start index, starts from 0
     */
    loadNews: function(start) {
        var c = $("news"); //container
        new Ajax.Request(this.url + "?a=ne&w=" + start, {
            onSuccess: function(response) {
                var news = eval(response.responseText); //you should use JSON.parse
                //limit is 10
                c.innerHTML = COMMON.newsHTML(news, start + 10);
            }, onFailure: function() {
                c.innerHTML = "<span class=info style='color:red'>Error loading, news & events</span>";
            }
        });
        //c.innerHTML = "<span class=info>News & events loading....</span>";
        this.drawLoading(c, 'News & events loading....');
    },

    /**
     * Gets news and Events HTML
     * @param news News and Events data
     * @param nxt Next start index
     */
    newsHTML: function(news, nxt) {
        var HTML = [];
        HTML.push("<table border=0 cellpadding=1 cellspacing=0>");
        var l = news.length;
        if(l == 0) HTML.push("<tr><td><span class=info style='color:red'>No recent news and events available...</span></td></tr>");
        else {
            //data iteration
            for(var i = 0; i < l; i++) {
                var hdr = news[i].header;
                if(hdr.length > 45) hdr = hdr.substring(0, 45) + "...";
                var txt = news[i].details;
                if(txt.length > 75) txt = txt.substring(0, 75) + "....";
                var m_url = "news/details.jsp?id=" + news[i].id; //more URL
                HTML.push("<tr><td><a href='" + m_url + "'>" + hdr + "</a><br/><span class=info>" + txt + "</span><br/><a href='" + m_url + "'>more</a></td></tr>");
            }
            //limit is 10
            if(l == 10) {
                HTML.push("<tr><td align=right><a href='Javascript:void(0);' onclick='COMMON.loadNews(" + nxt + ");'>more news and events...</a></td></tr>");
            }
        }
        HTML.push("</table>");
        return HTML.join("");
    },

    //password retrieval
    //b->button ref
    //o->user box
    password: function(b, o) {
        new Ajax.Request(this.appName + "/login.htm?a=rp&c=" + o.value, {
            onSuccess: function(r) {
                //messsage
                alert(r.responseText);
                //resets button HTML
                b.innerHTML = bHTML;
            }, onFailure: function() {
                alert('Error in password retrieval');
                b.innerHTML = bHTML;
            }
        });
        o.value = '';
        var bHTML = b.innerHTML; //button HTML
        this.drawLoading(b, 'loading....');
    },

    //loading
    //c: container, txt: text to be shown
    drawLoading: function(c, txt) {
        c.innerHTML = "<img src='" + this.appName + "/images/common/loading.gif' /><span style='font-color: green;'>" + txt + "</span>";
    }
};