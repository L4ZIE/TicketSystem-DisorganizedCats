package bll;

import be.Ticket;
import bll.interfaces.ITicketManager;
import dal.interfaces.ITicketDAO;

import java.util.ArrayList;
import java.util.List;

public class TicketManager implements ITicketManager { // REFACTO
    ITicketDAO ticketDAO;
    private List<Ticket> allTickets ;

    @Override
    public List<Ticket> getAllTickets() {
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
    public List<Ticket> searchTicketsByCustomerName (String query) {
        List<Ticket> tickets = ticketDAO.getAllTickets();// TODO
        List<Ticket> filtered = new ArrayList<>();

        for (Ticket t : tickets) {
            if (("" + t.getCustomerName().toLowerCase()).contains(query.toLowerCase())) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    @Override
    public Ticket getTicket(int id) {
        for (Ticket ticket: allTickets)
        {
            if(ticket.getId()==id)
            {
                return ticket;
            }
        }
        return null;
    }

    @Override
    public void searchTicketByEvent(String query) {
        // TODO
    }
}
