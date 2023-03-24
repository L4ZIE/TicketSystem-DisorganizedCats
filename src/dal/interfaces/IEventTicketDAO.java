package dal.interfaces;

 bllmanager
import java.util.List;

public interface IEventTicketDAO {
    List<Integer> getTicketsByEventID(int eventID);

    List<Integer> getEventForTickets(int ticketID);

    void addTicketToEvent(int ticketID, int eventID);

public interface IEventTicketDAO {

}
