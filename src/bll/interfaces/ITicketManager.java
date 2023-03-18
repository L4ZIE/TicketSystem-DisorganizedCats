package bll.interfaces;

import be.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface ITicketManager {
    List<Ticket> getAllTickets() throws SQLException;

    void createTicket(Ticket ticket) throws SQLException;

    void deleteTicket(int id) throws SQLException;

    Ticket getTicketByID(int id) throws SQLException;

    List<Ticket> searchTicketsByCustomerName(String query) throws SQLException;
}
