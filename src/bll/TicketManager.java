package bll;

import be.Ticket;
import bll.interfaces.ITicketManager;
import dal.TicketDAO;
import dal.interfaces.ITicketDAO;

import java.util.ArrayList;
import java.util.List;

public class TicketManager implements ITicketManager {

    ITicketDAO ticketDAO;
    private List<Ticket> allTickets;
    public TicketManager(){
        ticketDAO = new TicketDAO();
        fillAllTickets();
    }

    private void fillAllTickets() {
        allTickets = ticketDAO.getAllTickets();
    }

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

    @Override
    public List<Ticket> getTicketsByCustomer(String name) {
        List<Ticket> customersTickets = new ArrayList<>();
        for (Ticket t : allTickets) {
            if (t.getCustomerName().equalsIgnoreCase(name))
                customersTickets.add(t);
        }
        return customersTickets;
    }

    @Override
    public List<Ticket> getTicketsByEmail(String email) {
        List<Ticket> emailTickets = new ArrayList<>();
        for (Ticket t : allTickets) {
            if (t.getCustomerEmail().equalsIgnoreCase(email))
                emailTickets.add(t);
        }
        return emailTickets;
    }

    @Override
    public List<Ticket> getTicketsByPrice(int price) {
        List<Ticket> priceTickets = new ArrayList<>();
        for (Ticket t : allTickets) {
            if (t.getTicketPrice() == price)
                priceTickets.add(t);
        }
        return priceTickets;
    }

    @Override
    public List<Ticket> getTicketsByUsed(Boolean used) {
        List<Ticket> customersTickets = new ArrayList<>();
        for (Ticket t : allTickets) {
            if (t.getUsed() == used)
                customersTickets.add(t);
        }
        return customersTickets;
    }
}
