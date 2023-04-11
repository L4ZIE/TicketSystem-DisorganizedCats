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
        fillAllEvents();
        return allEvents;
    }

    @Override
    public void createEvent(Event event) {
        allEvents.add(event);
        eventDAO.createEvent(event);
    }

    @Override
    public void deleteEvent(int id) {
        eventDAO.deleteEvent(id);
        fillAllEvents();
    }

    @Override
    public Event getEventByID(int id) {
        for (Event event : allEvents) {
            if (event.getId() == id) {
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
    public int getMaxID() {
        int max = 0;

        for (Event e : allEvents) {
            if(max < e.getId())
                max = e.getId();
        }

        return max;
    }

    @Override
    public void updateEvent(Event event) {
        eventDAO.updateEvent(event);
        allEvents = eventDAO.getAllEvents();
    }
}



