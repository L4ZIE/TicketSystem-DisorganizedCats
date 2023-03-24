package bll;

import be.Event;
import bll.interfaces.IEventManager;
import dal.interfaces.IEventDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventManager implements IEventManager {
    IEventDAO eventDAO;
    private List<Event> allEvents = new ArrayList<>();

    @Override
    public List<Event> getAllEvents() {
        return allEvents;
    }

    @Override
    public void createEvent(Event event){
        allEvents.add(event);
        try {
            eventDAO.createEvent(event);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteEvent(int id) {
        Event event = getEventByID(id);
        if (event != null) {
            allEvents.remove(event);
            try {
                eventDAO.deleteEvent(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
    public List<Event> getEventsByStartDateTime(String date){
        List<Event> eventsStartDate = new ArrayList<>();
        for (Event t : allEvents) {
            if (t.getStartDateTime().equalsIgnoreCase(date))
                eventsStartDate.add(t);
        }
        return eventsStartDate;
    }
    @Override
    public List<Event> getEventsByEndDate(String date){
        List<Event> eventsEndDate = new ArrayList<>();
        for (Event t : allEvents) {
            if (t.getEndDateTime().equalsIgnoreCase(date))
                eventsEndDate.add(t);
        }
        return eventsEndDate;
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
