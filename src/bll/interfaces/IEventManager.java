package bll.interfaces;

import be.Event;

import java.util.List;

public interface IEventManager {
    List<Event> getAllEvents();

    void createEvent(Event event);

    void deleteEvent(int id);

    Event getEventsByStartDate(String date);

    List<Event> getEventsByStartDateTime(String date);

    List<Event> getEventsByEndDate(String date);

    List<Event> getEventsByName(String name);
}
