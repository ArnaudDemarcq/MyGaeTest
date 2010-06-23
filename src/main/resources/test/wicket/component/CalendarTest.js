window.onload = function () {

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    function callWicket() {

        //  var wcall = wicketAjaxGet("?wicket:interface=:1:calendarOne::IActivePageBehaviorListener:6:&wicket:ignoreIfNotActive=true" + "&hop=&foo=bar" , function() { }, function() { });
        var wcall = wicketAjaxGet("${eventBehaviourUrl}" + "&hop=&foo=bar" , function() { }, function() { });
    }

    function callWicketEvent(wicketUrl, wicketArgs, wicketEventType)
    {
        var finalUrl = wicketUrl + wicketArgs + "&EVENT_TYPE=" +wicketEventType;
        wicketAjaxGet(finalUrl , function() { }, function() { });
    }

    function getEventArguments(event)
    {
        var currentEventString = "";
        if (event.title != undefined)
        {
            currentEventString += "&eventTitle=" + event.title;
        }
        if (event.id != undefined)
        {
            currentEventString += "&eventId="+ event.id;
        }
        if (event.start != undefined)
        {
            currentEventString += "&eventStart="+ event.start;
        }
        return currentEventString;
    }

    $("#${markupId}").fullCalendar({

        editable: true,
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        //   events: function(start, end, callback) {
        //       $("#${markupId}").fireEvent("onclick", newEvt);
        // Todo : Ajax call  to wicket


        //  },
        events: [
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

        eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {

            var evenDropArgs = getEventArguments(event) +
            "&dayDelta=" + dayDelta + "&minuteDelta=" + minuteDelta +
            "&allDay=" + allDay + "&revertFunc=" + revertFunc ;

            //wicketAjaxGet("${eventBehaviourUrl}" + evenDropArgs , function() { }, function() { });
            callWicketEvent("${eventBehaviourUrl}", evenDropArgs , "eventDrop")
        }

    });
};
