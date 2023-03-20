package dal.interfaces;

import be.Ticket;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ITicketDAO {
    List<Ticket> getAllTickets();

    List<Ticket> fillTickets(ResultSet resultSet);

    void createTicket(Ticket ticket) ;

    void deleteTicket(int id) ;

    Ticket getTicketByID(int id) ;


}
