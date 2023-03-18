package dal.interfaces;

import be.Ticket;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.SQLException;
import java.util.List;

public interface ITicketDAO {
    List<Ticket> getAllTickets() throws SQLException;

    void createTicket(Ticket ticket) throws SQLException;

    void deleteTicket(int id) throws SQLException;

    Ticket getTicketByID(int id) throws SQLException;
}
