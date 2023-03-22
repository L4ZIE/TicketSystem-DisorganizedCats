package bll;

import be.Ticket;
import bll.interfaces.ITicketManager;
import dal.interfaces.ITicketDAO;

import java.util.ArrayList;
import java.util.List;

public class TicketManager implements ITicketManager {

    ITicketDAO ticketDAO;
    private List<Ticket> allTickets = new ArrayList<>();

    @Override
    public List<Ticket> getAllTickets() {
        return allTickets;
    }

    @Override
    public void createTicket(Ticket ticket) {
        allTickets.add(ticket);
        ticketDAO.createTicket(ticket);

    }
    @Override
    public void deleteTicket(int id) {
        Ticket ticket = getTicketByID(id);
        if(ticket!= null)
        {
            allTickets.remove(ticket);
            ticketDAO.deleteTicket(id);
        }
    }

    @Override
    public List<Ticket> searchTicketsByCustomerName(String query) {
        List<Ticket> filtered = new ArrayList<>();
        for (Ticket t : allTickets) {
            if (("" + t.getCustomerName()).equalsIgnoreCase(query)) {
                filtered.add(t);
            }
        }
        return filtered;

    }

    @Override
    public Ticket getTicketByID(int id) {
        for (Ticket ticket : allTickets)
        {
            if(ticket.getId()== id)
            {
                return ticket;
            }
        }
        return null;
    }

    @Override
    public void searchTicketByEvent(String query) {
    //TODO
    }
}
