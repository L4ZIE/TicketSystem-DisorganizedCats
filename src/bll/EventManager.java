package bll;

import be.Event;
import bll.interfaces.IEventManager;
import dal.EventDAO;
import dal.EventSpecTicketDAO;
import dal.interfaces.IEventDAO;
import dal.interfaces.IEventSpecTicketDAO;

import java.util.ArrayList;
import java.util.List;

public class EventManager implements IEventManager {
    IEventDAO eventDAO;
    IEventSpecTicketDAO eventSpecTicketDAO;
    private List<Event> allEvents;

    public EventManager() {
        eventDAO = new EventDAO();
        eventSpecTicketDAO = new EventSpecTicketDAO();
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
    public Event getEventByName(String name) {
        for (Event e : allEvents) {
            if (e.getEventName().equals(name))
                return e;
        }
        return null;
    }

    @Override
    public List<Event> searchForEvent(String query, List<Event> allEvents) {
        List<Event> filtered = new ArrayList<>();

        for (Event e : allEvents) {
            if (e.getEventName().toLowerCase().contains(query.toLowerCase()) ||
                    e.getStartDateTime().toLowerCase().contains(query.toLowerCase()) ||
                    e.getEndDateTime().toLowerCase().contains(query.toLowerCase()) ||
                    e.getLocationGuidance().toLowerCase().contains(query.toLowerCase()) ||
                    e.getLocation().toLowerCase().contains(query.toLowerCase()) ||
                    e.getNotes().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(e);
            }
        }
        return filtered;
    }

    @Override
    public int getMaxID() {
        int max = 0;
        for (Event e : allEvents) {
            if (max < e.getId())
                max = e.getId();
        }
        return max;
    }

    @Override
    public void updateEvent(Event event) {
        eventDAO.updateEvent(event);
        allEvents = eventDAO.getAllEvents();
    }

    @Override
    public List<Event> getEventsBySpecTicketID(int id) {
        List<Integer> eventID;
        List<Event> listEventsBySpecTicketID = new ArrayList<>();

        eventID = eventSpecTicketDAO.getEventForSpecTickets(id);
        for (int i : eventID) {
            listEventsBySpecTicketID.add(getEventByID(i));
        }
        return listEventsBySpecTicketID;
    }

    @Override
    public void addEventToSpecTicket(int ticketID, int eventID) {
        eventSpecTicketDAO.addEventsToSpecTicket(ticketID, eventID);
    }

    @Override
    public void deleteEventFromSpecTicket(int ticketID, int eventID) {
        eventSpecTicketDAO.deleteEventFromSpecTicket(ticketID, eventID);
    }
}



