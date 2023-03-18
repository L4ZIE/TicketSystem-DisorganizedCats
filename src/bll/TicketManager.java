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

    public TicketManager() {
        ticketDAO = new TicketDAO();}

    @Override
    public List<Ticket> getAllTickets() throws SQLException {
        return ticketDAO.getAllTickets();
    }
    @Override
    public void createTicket(Ticket ticket) throws SQLException {
        ticketDAO.createTicket(ticket);
    }

    @Override
    public void deleteTicket(int id) throws SQLException {
        ticketDAO.deleteTicket(id);
    }
    @Override
    public Ticket getTicketByID(int id) throws SQLException {
        return ticketDAO.getTicketByID(id);
    }

    @Override
    public List<Ticket> searchTicketsByCustomerName (String query) throws SQLException {
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
