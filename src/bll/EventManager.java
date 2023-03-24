package bll;

import be.Event;
import bll.interfaces.IEventManager;
import dal.EventDAO;
import dal.interfaces.IEventDAO;

import java.util.ArrayList;
import java.util.List;

public class EventManager implements IEventManager {
    IEventDAO eventDAO;
    private List<Event> allEvents;

    public EventManager() {
        eventDAO = new EventDAO();
        fillAllEvents();
    }

    private void fillAllEvents() {
        allEvents = eventDAO.getAllEvents();
    }

    @Override
    public List<Event> getAllEvents() {
        return allEvents;
    }

    @Override
    public void createEvent(Event event) {
        allEvents.add(event);
        eventDAO.createEvent(event);
    }

    @Override
    public void deleteEvent(int id) {
        Event event = getEventByID(id);
        if (event != null) {
            allEvents.remove(event);
            eventDAO.deleteEvent(id);

        }
    }

    public Event getEventByID(int id)
    {
        for (Event event : allEvents)
        {
            if (event.getId() == id)
            {
                return event;
            }
        }
        return null;
    }
    @Override
    public Event getEventsByStartDate(String date){
         for (Event event : allEvents)
         {
             if (event.getStartDateTime() == date)
             {
                 return event;
             }
         }
        return null;
    }

    @Override

    public List<Event> getEventsByStartDate(String start) {
        List<Event> result = new ArrayList<>();
        for (Event e : allEvents) {
            if (e.getStartDateTime().equalsIgnoreCase(start))
                result.add(e);
        }
        return result;
    }

    @Override
    public List<Event> getEventsByEndDate(String end) {
        List<Event> result = new ArrayList<>();
        for (Event e : allEvents) {
            if (e.getEndDateTime().equalsIgnoreCase(end))
                result.add(e);
        }
        return result;
    }

    @Override
    public List<Event> getEventsByName(String name) {
        List<Event> result = new ArrayList<>();
        for (Event e : allEvents) {
            if (e.getEventName().equalsIgnoreCase(name))
                result.add(e);
        }
        return result;
    }



    @Override
    public List<Event> getEventsByName(String name){
        List<Event> eventsName = new ArrayList<>();
        for (Event t : allEvents){
            if (t.getEventName().equalsIgnoreCase(name)){
                eventsName.add(t);
            }
        }
        return eventsName;
    }
}
