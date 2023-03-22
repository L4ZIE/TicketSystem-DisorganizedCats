package bll.interfaces;

import be.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface ITicketManager {
    List<Ticket> getAllTickets() ;
    void createTicket(Ticket ticket) ;
    void deleteTicket(int id);
    List<Ticket> searchTicketsByCustomerName(String query);
    Ticket getTicketByID(int id);
    void searchTicketByEvent(String query);
}
