package dal.interfaces;

import java.util.List;

public interface IEventSpecTicketDAO {
    List<Integer> getSpecTicketsByEventID(int eventID);

    List<Integer> getEventForSpecTickets(int ticketID);

    void addSpecTicketToEvent(int ticketID, int eventID);

    void addEventsToSpecTicket(int ticketID, int eventID);
    void deleteEventFromSpecTicket(int ticketID, int eventID);
}
