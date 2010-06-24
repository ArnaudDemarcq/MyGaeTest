function callWicketEvent(wicketUrl, wicketArgs, wicketEventType){
    var finalUrl = wicketUrl + wicketArgs + "&EVENT_TYPE=" +wicketEventType;
    wicketAjaxGet(finalUrl , function() { }, function() { });
}

function getEventArguments(event){
    currentEventString = jsonToArgs(event,"Event");
    currentEventString += "&Event_GEN_start=" + event.start.getTime();
    return currentEventString;
}

function jsonToArgs(jsonObject,baseKey){
    var currentArgString = "";
    for(var key in jsonObject){
        currentArgString += "&" +baseKey +"_" +key + "="+ jsonObject[key];
    }
    return currentArgString;
}


function callWicketEventDrop(wicketUrl,event,dayDelta,minuteDelta,allDay,revertFunc){
    var eventDropArgs = getEventArguments(event) +
    "&dayDelta=" + dayDelta + "&minuteDelta=" + minuteDelta +
    "&allDay=" + allDay + "&revertFunc=" + revertFunc ;
    callWicketEvent(wicketUrl, eventDropArgs , "eventDrop");
}

function callWicketEventClick(wicketUrl,calEvent, jsEvent, view) {
    var eventClickArgs =getEventArguments(calEvent);
    callWicketEvent(wicketUrl, eventClickArgs , "eventClick");
}
