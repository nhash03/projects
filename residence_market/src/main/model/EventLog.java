package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Represents a log of market app events
//change to using singleton
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    // MODIFIES : this
    // EFFECTS : create the single event log for the whole market
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // MODIFIES : this
    // EFFECTS : create an event log if there wasn't exist any or return the instance of the only one
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // MODIFIES : this
    // EFFECTS : add the given event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    // MODIFIES : this
    // EFFECTS : clear the event log and make a new event for clearing act
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
