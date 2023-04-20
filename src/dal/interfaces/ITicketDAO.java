package dal.interfaces;

import be.Ticket;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ITicketDAO {
    List<Ticket> getAllTickets();
    void createTicket(Ticket ticket, int eventID) ;
    void deleteTicket(int id) ;
    void updateTicket(Ticket selectedTicket);
}
