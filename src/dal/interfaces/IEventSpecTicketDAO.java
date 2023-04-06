package dal.interfaces;

import java.util.List;

public interface IEventSpecTicketDAO {
    List<Integer> getSpecTicketsByEventID(int eventID);

    List<Integer> getEventForSpecTickets(int ticketID);

    void addSpecTicketToEvent(int ticketID, int eventID);
}
