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
    private Event getEventByID(int id)
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


}
