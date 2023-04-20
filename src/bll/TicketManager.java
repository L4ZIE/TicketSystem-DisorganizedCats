package bll;

import be.Event;
import be.Ticket;
import bll.interfaces.ITicketManager;
import dal.EventTicketDAO;
import dal.TicketDAO;
import dal.interfaces.IEventTicketDAO;
import dal.interfaces.ITicketDAO;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class TicketManager implements ITicketManager {

    ITicketDAO ticketDAO;
    private IEventTicketDAO eventTicketDAO;
    private List<Ticket> allTickets;


    public TicketManager() {
        ticketDAO = new TicketDAO();
        eventTicketDAO = new EventTicketDAO();
        fillAllTickets();
    }

    private void fillAllTickets() {
        allTickets = ticketDAO.getAllTickets();
    }

    @Override
    public List<Ticket> getAllTickets() {
        fillAllTickets();
        return allTickets;
    }

    @Override
    public void createTicket(Ticket ticket, int eventID) {
        allTickets.add(ticket);
        ticketDAO.createTicket(ticket, eventID);
    }

    @Override
    public void deleteTicket(int id) {
        allTickets.remove(getTicketByID(id));
        ticketDAO.deleteTicket(id);
    }

    public void updateTicket(Ticket ticket) {
        ticketDAO.updateTicket(ticket);
        allTickets = ticketDAO.getAllTickets();
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
        for (Ticket ticket : allTickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    @Override
    public List<Ticket> getTicketsByCustomer(String name) {
        List<Ticket> ticketsByCustomer = new ArrayList<>();
        for (Ticket ticket : allTickets) {
            if (ticket.getCustomerName() == name) {
                ticketsByCustomer.add(ticket);
            }
        }
        return ticketsByCustomer;
    }

    @Override
    public List<Ticket> getTicketsByEmail(String email) {
        List<Ticket> listTicketsEmail = new ArrayList<>();
        for (Ticket ticket : allTickets) {
            if (ticket.getCustomerEmail() == email) {
                listTicketsEmail.add(ticket);
            }
        }
        return listTicketsEmail;
    }

    @Override
    public List<Ticket> getTicketsByPrice(int price) {
        List<Ticket> listTicketPrice = new ArrayList<>();
        for (Ticket t : allTickets) {
            if (t.getTicketPrice() == price) {
                listTicketPrice.add(t);
            }
        }

        return listTicketPrice;
    }

    @Override
    public List<Ticket> getTicketsByUsed(Boolean used) {
        List<Ticket> listUsedTicket = new ArrayList<>();
        for (Ticket ticket : allTickets) {
            if (ticket.getUsed() == true) {
                listUsedTicket.add(ticket);
            }
        }
        return listUsedTicket;
    }

    @Override
    public List<Ticket> getTicketsByEventID(int id) {
        fillAllTickets();
        List<Ticket> listTicketsByEvent = new ArrayList<>();

        for (Ticket t : allTickets) {
            if (t.getEventID() == id)
                listTicketsByEvent.add(t);
        }
        return listTicketsByEvent;
    }

    @Override
    public void useTicket(int id) {
        Ticket ticket = getTicketByID(id);
        ticket.setUsed(true);
        updateTicket(ticket);
    }

    @Override
    public int getMaxID() {
        int max = 0;

        for (Ticket ticket : allTickets) {
            if (max < ticket.getId())
                max = ticket.getId();
        }
        return max;
    }

    public List<Ticket> searchForTicket(String query) {
        List<Ticket> filtered = new ArrayList<>();

        for (Ticket t : allTickets) {
            if (t.getCustomerName().toLowerCase().contains(query.toLowerCase()) ||
                    t.getCustomerEmail().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    @Override
    public List<Ticket> searchTicketByEvent(String query) {
        return null;
    }

}