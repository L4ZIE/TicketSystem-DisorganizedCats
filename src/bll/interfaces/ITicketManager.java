package bll.interfaces;

import be.Event;
import be.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface ITicketManager {
    List<Ticket> getAllTickets() ;
    void createTicket(Ticket ticket) ;
    void deleteTicket(int id);
    List<Ticket> searchTicketsByCustomerName(String query);
    Ticket getTicketByID(int id);
    List<Ticket>searchTicketByEvent(String query);
    List<Ticket> getTicketsByCustomer(String name);

    List<Ticket> getTicketsByEmail(String email);

    List<Ticket> getTicketsByPrice(int price);

    List<Ticket>  getTicketsByUsed(Boolean used);

    List<Ticket> getTicketsByEventID(Event event);
}
