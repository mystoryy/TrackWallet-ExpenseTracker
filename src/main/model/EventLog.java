package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

//Code from AlarmSystem repository given in lecture- "https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git"
//Represents a log of expense tracker events.There is only one eventlog and system has global access to a single
// instance of eventLog
public class EventLog implements Iterable<Event> {
    /**
     * the only EventLog in the system (Singleton Design Pattern)
     */
    private static EventLog theLog;
    private Collection<Event> events;

    //EFFECTS: constructs a collection of events preventing external construction (Singleton Design Pattern)
    private EventLog() {

        events = new ArrayList<Event>();
    }

    //EFFECTS: returns the instance of EventLog by getting an instance of it otherwise creates one if it doesn't exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }


    //EFFECTS: Adds an event to the Event Log
    public void logEvent(Event e) {
        events.add(e);
    }

    //EFFECTS: clears the event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }

}
