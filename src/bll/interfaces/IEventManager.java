package bll.interfaces;

import be.Event;

import java.util.List;

public interface IEventManager {
    List<Event> getAllEvents();

    void createEvent(Event event);

    void deleteEvent(int id);
    
    Event getEventByID(int id);
    public List<Event> getEventsByStartDate(String start);
    public List<Event> getEventsByEndDate(String end);
    public List<Event> getEventsByName(String name);


}
