var jsonUrl = "${getListBehaviourUrl}&start=1275170400&end=1278799200";

var events = 14;
function setEvents(eventList){
    events = eventList;
    alert(events);
}

function getEvents(){   
    return events;
}

window.onload = function () {

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();



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

        events_OLD4: "./${getListBehaviourUrl}",
        
        events: function(start, end, callback) {
            $.getJSON("${getListBehaviourUrl}" + "&start=" + start.getTime() +"&end=" + end.getTime(),
                function(data){
                    alert("hop ?");
                    $.each(data.items, function(i,item){
                        alert(i);
                    });
                    alert("hop !");
                });
        /*
            callWicketGetEventList("${getListBehaviourUrl}" + "&start=" + start.getTime() +"&end=" + end.getTime() ,start,end);
            var currentEvent = getEvents();
            alert("hop ?");
            alert(currentEvent[0].title);
            alert("hop !");
            callback(getEvents());/**/
        },

        eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {
            callWicketEventDrop("${eventBehaviourUrl}",event,dayDelta,minuteDelta,allDay,revertFunc);
        },

        eventClick: function(calEvent, jsEvent, view) {
            callWicketEventClick("${eventBehaviourUrl}",calEvent, jsEvent, view);
        }
        
    });
};

