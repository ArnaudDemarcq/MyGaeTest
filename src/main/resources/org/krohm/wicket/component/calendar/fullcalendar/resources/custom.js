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


function callWicketEventDrop(wicketUrl,event,dayDelta,minuteDelta,allDay,revertFunc)
{
    var eventDropArgs = getEventArguments(event) +
    "&dayDelta=" + dayDelta + "&minuteDelta=" + minuteDelta +
    "&allDay=" + allDay + "&revertFunc=" + revertFunc ;

    callWicketEvent(wicketUrl, eventDropArgs , "eventDrop");
}

function callWicketEventClick(wicketUrl,calEvent, jsEvent, view) {
    var eventClickArgs =getEventArguments(calEvent);

    callWicketEvent(wicketUrl, eventClickArgs , "eventClick");
}
