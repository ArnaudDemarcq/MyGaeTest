var jsonUrl = "${getListBehaviourUrl}&start=1275170400&end=1278799200";

var events = 14;


var date = new Date();
var d = date.getDate();
var m = date.getMonth();
var y = date.getFullYear();

var testEvents = [ {
    'title' : 'First Event Ever !',
    'start' :  1278799200
    
},

{
    'title' : 'Second Event Ever !',
    'start' : 1277739109
}
];

var initialEvents =  [
{
    title: 'All Day Event',
    start: new Date(y, m, 1)
},
{
    id: 999,
    title: 'Repeating Event',
    start: new Date(y, m, d-3, 16, 0),
    allDay: false
},
{
    id: 999,
    title: 'Repeating Event',
    start: new Date(y, m, d+4, 16, 0),
    allDay: false
},/*
 {
    title: 'Long Event',
    start: new Date(y, m, d-5),
    end: new Date(y, m, d-2)
},
{
    title: 'Meeting',
    start: new Date(y, m, d, 10, 30),
    allDay: false
},
{
    title: 'Lunch',
    start: new Date(y, m, d, 12, 0),
    end: new Date(y, m, d, 14, 0),
    allDay: false
},
{
    title: 'Birthday Party',
    start: new Date(y, m, d+1, 19, 0),
    end: new Date(y, m, d+1, 22, 30),
    allDay: false
},/**/
{
    title: 'Click for Google',
    start: new Date(y, m, 28),
    end: new Date(y, m, 29),
    url: 'http://google.com/'
}
];

        
function setEvents(eventList){
    events = eventList;
    alert(events);
}

function getEvents(){   
    return events;
}

function DumpObjectIndented(obj, indent)
{
    var result = "";
    if (indent == null) indent = "";

    for (var property in obj)
    {
        var value = obj[property];
        if (typeof value == 'string')
            value = "'" + value + "'";
        else if (typeof value == 'object')
        {
            if (value instanceof Array)
            {
                // Just let JS convert the Array to a string!
                value = "[ " + value + " ]";
            }
            else
            {
                // Recursive dump
                // (replace "  " by "\t" or something else if you prefer)
                var od = DumpObjectIndented(value, indent + "  ");
                // If you like { on the same line as the key
                //value = "{\n" + od + "\n" + indent + "}";
                // If you prefer { and } to be aligned
                value = "\n" + indent + "{\n" + od + "\n" + indent + "}";
            }
        }
        result += indent + "'" + property + "' : " + value + ",\n";
    }
    return result.replace(/,\n$/, "");
}


window.onload = function () {




    $("#${markupId}").fullCalendar({

        editable: true,
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        //   events: function(start, end, callback) {
        //       $("#calendarOne1").fireEvent("onclick", newEvt);
        // Todo : Ajax call  to wicket


        //  },
        events_OLD: [
        {
            title: 'All Day Event',
            start: new Date(y, m, 1)
        },
        {
            title: 'Long Event',
            start: new Date(y, m, d-5),
            end: new Date(y, m, d-2)
        },
        {
            id: 999,
            title: 'Repeating Event',
            start: new Date(y, m, d-3, 16, 0),
            allDay: false
        },
        {
            id: 999,
            title: 'Repeating Event',
            start: new Date(y, m, d+4, 16, 0),
            allDay: false
        },
        {
            title: 'Meeting',
            start: new Date(y, m, d, 10, 30),
            allDay: false
        },
        {
            title: 'Lunch',
            start: new Date(y, m, d, 12, 0),
            end: new Date(y, m, d, 14, 0),
            allDay: false
        },
        {
            title: 'Birthday Party',
            start: new Date(y, m, d+1, 19, 0),
            end: new Date(y, m, d+1, 22, 30),
            allDay: false
        },
        {
            title: 'Click for Google',
            start: new Date(y, m, 28),
            end: new Date(y, m, 29),
            url: 'http://google.com/'
        }
        ],

        events_OLD6: "./${getListBehaviourUrl}",
        
        events: function(start, end, callback) {
            $.getJSON("${getListBehaviourUrl}" + "&start=" + start.getTime() +"&end=" + end.getTime(),
                function(data){
                    var myString = DumpObjectIndented(initialEvents,"");
                    //alert("" +DumpObjectIndented(data,""));
                    //alert("" + DumpObjectIndented(testEvents,""));
                    //callback(initialEvents);
                    callback(data);
                    
                /*
                    $.each(data.items, function(i,item){
                        alert(i);
                    });
                    alert("hop !");/**/
                });
        /*
            callWicketGetEventList("${getListBehaviourUrl}" + "&start=" + start.getTime() +"&end=" + end.getTime() ,start,end);
            var currentEvent = getEvents();
            alert("hop ?");
            alert(currentEvent[0].title);
            alert("hop !");
            /**/
        },

        eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {
            callWicketEventDrop("${eventBehaviourUrl}",event,dayDelta,minuteDelta,allDay,revertFunc);
        },

        eventClick: function(calEvent, jsEvent, view) {
            callWicketEventClick("${eventBehaviourUrl}",calEvent, jsEvent, view);
        }
        
    });
};

