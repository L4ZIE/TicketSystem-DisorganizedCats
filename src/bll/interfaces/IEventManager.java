package bll.interfaces;

import be.Event;

import java.util.List;

public interface IEventManager {
    List<Event> getAllEvents();

    void createEvent(Event event);

    void deleteEvent(int id);
    Event getEventByID(int id);
    List<Event> getEventsByStartDate(String start);
    List<Event> getEventsByEndDate(String end);
    List<Event> getEventsByName(String name);
    List<Event> searchEventByName(String query);
    int getMaxID();
    void updateEvent(Event event);
}
