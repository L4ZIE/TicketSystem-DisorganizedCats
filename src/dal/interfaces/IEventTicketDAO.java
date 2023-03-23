package dal.interfaces;

import java.util.List;

public interface IEventTicketDAO {
    List<Integer> getTicketsByEventID(int eventID);

    List<Integer> getEventForTickets(int ticketID);

    void addTicketToEvent(int ticketID, int eventID);
}
