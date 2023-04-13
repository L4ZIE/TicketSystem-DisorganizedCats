package bll.interfaces;

import be.Event;
import be.SpecialTicket;

import java.util.List;

public interface IEventManager {
    List<Event> getAllEvents();

    void createEvent(Event event);

    void deleteEvent(int id);
    Event getEventByID(int id);
    List<Event> getEventsByStartDate(String start);
    List<Event> getEventsByEndDate(String end);
    Event getEventByName(String name);
    List<Event> searchForEvent(String query, List<Event> allEvents);
    int getMaxID();
    void updateEvent(Event event);
    List<Event> getEventsBySpecTicketID(int id);

    void addEventToSpecTicket(int ticketID, int eventID);
    void deleteEventFromSpecTicket(int ticketID, int eventID);
}
