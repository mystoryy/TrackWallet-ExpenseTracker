package model;

import java.util.Calendar;
import java.util.Date;

//Represents an expense tracker event
public class Event {

    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    //EFFECTS: creates an event with the given description of the event and current date/time stamp
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    //EFFECTS: returns the date of the event including time
    public Date getDate() {
        return dateLogged;
    }

    //EFFECTS: returns the description of the event
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }

    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}


