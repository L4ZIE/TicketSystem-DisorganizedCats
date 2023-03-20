package bll;

import be.Ticket;
import bll.interfaces.ITicketManager;
import dal.TicketDAO;
import dal.interfaces.ITicketDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketManager implements ITicketManager {
    ITicketDAO ticketDAO;

    @Override
    public List<Ticket> getAllTickets()  {
        return ticketDAO.getAllTickets();
    }
    @Override
    public void createTicket(Ticket ticket) {
        ticketDAO.createTicket(ticket);
    }

    @Override
    public void deleteTicket(int id)  {
        ticketDAO.deleteTicket(id);
    }
    @Override
    public Ticket getTicketByID(int id)  {
        return ticketDAO.getTicketByID(id);
    }

    @Override
    public List<Ticket> searchTicketsByCustomerName (String query) {
        List<Ticket> tickets = ticketDAO.getAllTickets();
        List<Ticket> filtered = new ArrayList<>();

        for (Ticket t : tickets) {
            if (("" + t.getCustomerName().toLowerCase()).contains(query.toLowerCase())) {
                filtered.add(t);
            }
        }
        return filtered;
    }
}
