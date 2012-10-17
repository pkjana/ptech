//Author: Debasis Jana

//calendar image styles
var __CALENDAR_IMAGE_STYLE__ = "style='width:15px;height:13px;cursor:pointer'";
var __CALENDAR_RANDOM_ID__ = Math.round(Math.random()* 100000);
//calendar window
var Calendar = {
    curDate: new Date(), //current date
    activeDate: null, //navigated date
    cal: $("__CALENDAR__" + __CALENDAR_RANDOM_ID__),
    box: null,
    //p->position: left, top, box: text box
    draw: function(box, p) {
        this.activeDate = this.curDate;
        this.box = box;
        var cal = this.cal;
        var closed = cal == null;
        if(closed) {
            cal = document.createElement("div");
            document.body.appendChild(cal);
            cal.className = "calendar";
            this.cal = cal;
        }

        cal.style.left = p.left + 'px';
        cal.style.top = p.top + 'px';
        if(!closed) return; //returns if calendar exists already

        this.paint();

        var y_el = $('year' + __CALENDAR_RANDOM_ID__);
        var yl_el = $('yl' + __CALENDAR_RANDOM_ID__);
        var yr_el = $('yr' + __CALENDAR_RANDOM_ID__);
        var m_el = $('month' + __CALENDAR_RANDOM_ID__);
        var ml_el = $('ml' + __CALENDAR_RANDOM_ID__);
        var mr_el = $('mr' + __CALENDAR_RANDOM_ID__);
        var c_el = $('close' + __CALENDAR_RANDOM_ID__);

        m_el.selectedIndex = this.curDate.getMonth(); //active month
        y_el.value = this.curDate.getFullYear(); //active year

        //year change
        y_el.observe("keyup", function(e){
            //var kc = e.keyCode | e.which; //keycode
            //if(kc == 13 || kc == 8 || kc == 27) return;
            var el = Event.element(e);
            var y = el.value;
            var l = y.length;
            //if(kc < 48 || kc > 57) el.value = y.substring(0, l - 1); //skips non-numeric char
            if(l == 4) {
                Calendar.activeDate.setYear(y);
                Calendar.reload();
            }
        });
        //year left mover
        yl_el.observe("click", function(e) {
            var y = y_el.value = y_el.value - 1;
            Calendar.activeDate.setYear(y);
            Calendar.reload();
        });
        //year right mover
        yr_el.observe("click", function(e) {
            var y = y_el.value = parseInt(y_el.value) + 1;
            Calendar.activeDate.setYear(y);
            Calendar.reload();
        });

        //month change
        m_el.observe("change", function(e) {
            var el = Event.element(e);
            Calendar.activeDate.setMonth(el.selectedIndex);
            Calendar.reload();
        });
        //month left mover
        ml_el.observe("click", function(e) {
            var m = m_el.value;
            if(m == 0){
                m = 11;
                var y = y_el.value = y_el.value - 1;
                Calendar.activeDate.setYear(y);
            } else m = m_el.value = m_el.value - 1;
            Calendar.activeDate.setMonth(m);
            Calendar.reload();
        });
        //month right mover
        mr_el.observe("click", function(e) {
            var m = m_el.value;
            if(m == 11) {
                m = 0;
                var y = y_el.value = parseInt(y_el.value) + 1;
                Calendar.activeDate.setYear(y);
            } else m = m_el.value = parseInt(m_el.value) + 1;
            Calendar.activeDate.setMonth(m);
            Calendar.reload();
        });

        //close
        c_el.observe("click", function(e){
            closeCal();
        });
    },
    //actual painting logic
    paint: function() {
        var HTML = [];
        HTML.push("<table border=0 cellpadding=2 cellspacing=0>");
        HTML.push("<tr><td><table border=0 cellpadding=0 cellspacing=0><tr>");
        var i_path = COMMON.appName + "/images/common/"; //image path
        HTML.push("<td><table border=0 cellpadding=1 cellspacing=0><tr><td id='yl" + __CALENDAR_RANDOM_ID__ + "'><img src='" + i_path + "prev.gif' " + __CALENDAR_IMAGE_STYLE__ + " title='Click to move year left' alt='prev' /></td><td><input type=text id='year" + __CALENDAR_RANDOM_ID__ + "' size=4 maxlength=4 /></td><td id='yr" + __CALENDAR_RANDOM_ID__ + "'><img src='" + i_path + "next.gif' " + __CALENDAR_IMAGE_STYLE__ + " title='Click to move year right' alt='next' /></td></tr></table></td>");
        HTML.push("<td align=right><table border=0 cellpadding1 cellspacing=0><tr><td id='ml" + __CALENDAR_RANDOM_ID__ + "'><img src='" + i_path + "prev.gif' " + __CALENDAR_IMAGE_STYLE__ + " title='Click to move month left' alt='prev' /></td><td>" + __MONTHS_HTML__ + "</td><td id='mr" + __CALENDAR_RANDOM_ID__ + "'><img src='" + i_path + "next.gif' " + __CALENDAR_IMAGE_STYLE__ + " title='Click to move month right' alt='next' /></td><td id='close" + __CALENDAR_RANDOM_ID__ + "'><img src='" + i_path + "icon-close.png' " + __CALENDAR_IMAGE_STYLE__ + " title='Click to close' alt='close' /></td></tr></table></td>");
        HTML.push("</tr></table></td></tr>");
        HTML.push("<tr><td id='days" + __CALENDAR_RANDOM_ID__ + "'>");
        var days = this.getDays();
        HTML.push(this.paintDates(days));
        HTML.push("</table></td></tr></table>");
        this.cal.innerHTML = HTML.join("");

        this.dateHandlers(days);
    },
    //get days count
    getDays: function() {
        var date = this.activeDate;
        var year = date.getFullYear();
        var month = date.getMonth();
        return Calendar.months[month].days + (month == 1 && isLeapYear(year) ? 1 : 0);
    },
    //painting of dates
    paintDates: function(days) {
        var HTML = [];
        var date = this.activeDate;
        var month = date.getMonth();
        var dt = date.getDate();
        var day = date.getDay();
        HTML.push("<table border=0 cellpadding=0 cellspacing=0 width='100%'>");
        HTML.push("<tr>" + __DAYS_HTML__ + "</tr><tr>");
        if(day > 0) {
            //previous month details
            var pm = month == 0 ? 11 : (month -1);
            var sd = Calendar.months[pm].days - day;
            for(var i = 0; i < day; i++) {
                HTML.push("<td class='calendar-data' style='color:gray;'>" + sd++ + "</td>");
            }
        }
        //first row
        var c = 0;
        for(var i = day; i < 7; i++) {
            HTML.push("<td class='calendar-data' style='cursor:pointer;' id='day" + ++c + "_" + __CALENDAR_RANDOM_ID__ + "'>" + c + "</td>");
        }
        //rest row
        outer:
        while(c < days) {
            HTML.push("<tr>");
            for(var i = 0; i < 7; i++) {
                HTML.push("<td id='day" + ++c + "_" + __CALENDAR_RANDOM_ID__ + "' class='calendar-data " + (c == dt ? "calendar-active-data" : "") + " ' style='cursor:pointer;' >" + c + "</td>");
                if(c == days) break outer;
            }
            HTML.push("</tr>");
        }
        //last row (if exists)
        if(i < 6) {
            sd = 0;
            for(; i < 6; i++) {
                HTML.push("<td class='calendar-data' style='color:gray;'>" + ++sd + "</td>");
            }
        }
        HTML.push("</tr>");
        return HTML.join("");
    },
    //date handler attachment
    dateHandlers: function(days) {
        //days event handler
        for(var i = 0; i < days; i++) {
            var d = $('day' + (i + 1) + "_" + __CALENDAR_RANDOM_ID__);
            d.observe("click", function(e) {
                var el = Event.element(e);
                var d = Calendar.activeDate;
                //yyy/mm/dd
                Calendar.box.value = d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + el.innerHTML;
                closeCal();
            });
        }
    },
    //reloads
    reload: function() {
        var days = this.getDays();
        $('days' + __CALENDAR_RANDOM_ID__).innerHTML = this.paintDates(days);
        this.dateHandlers(days);
    }
};

//closes calendar
function closeCal() {
    var cal = Calendar.cal;
    cal.parentNode.removeChild(cal);
    Calendar.cal = null;
}

//day details
Calendar.days = [{name: 'S', fullName: 'Sunday'},{name: 'M', fullName: 'Monday'},{name: 'T', fullName: 'Tuesday'},{name: 'W', fullName: 'Wednesday'},{name: 'T', fullName: 'Thurshday'},{name: 'F', fullName: 'Friday'},{name: 'S', fullName: 'Saturday'}];
var __DAYS_HTML__ = null;
(function(){
    var HTML = [];
    var l = Calendar.days.length;
    for(var i = 0; i < l; i++) {
        var d = Calendar.days[i];
        HTML.push("<td class='calendar-data' style='font-weight: bold;'>" + d.name + "</td>");
    }
    __DAYS_HTML__ = HTML.join("");
})();

//month details
Calendar.months = [{name: 'Jan', fullName: 'January', days: 31}, {name: 'Feb', fullName: 'February', days: 28},{name: 'Mar', fullName: 'March', days: 30},
                  {name: 'Apr', fullName: 'April', days: 31}, {name: 'May', fullName: 'May', days: 30},{name: 'Jun', fullName: 'June', days: 31},{name: 'Jul', fullName: 'July', days: 31},
                  {name: 'Aug', fullName: 'August', days: 31}, {name: 'Sep', fullName: 'September', days: 30},{name: 'Oct', fullName: 'October', days: 31},{name: 'Nov', fullName: 'November', days: 30},{name: 'Dec', fullName: 'December', days: 31}];
var __MONTHS_HTML__ = null;
(function() {
    var HTML = [];
    HTML.push("<select id='month" + __CALENDAR_RANDOM_ID__ + "'>");
    var l = Calendar.months.length;
    for(var i = 0; i < l; i++) {
        var m = Calendar.months[i];
        HTML.push("<option value='" + i + "'>" + m.name + "</option>");
    }
    HTML.push("</select>");
    __MONTHS_HTML__ = HTML.join("");
})();

//leap year check
function isLeapYear(y) {
    return (y%100 == 0 && y%400 == 0) || y%4 == 0;
};

//default date picker
function datePicker(e) {
    var el = Event.element(e);
    var p = new Element.Layout(el);
    Calendar.draw(el, {left: p.get('left'), top: p.get('top')});
};