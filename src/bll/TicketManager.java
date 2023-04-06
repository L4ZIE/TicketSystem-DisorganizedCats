package bll;

import be.Event;
import be.Ticket;
import bll.interfaces.ITicketManager;
import dal.TicketDAO;
import dal.interfaces.IEventTicketDAO;
import dal.interfaces.ITicketDAO;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class TicketManager implements ITicketManager {

    ITicketDAO ticketDAO;
    private IEventTicketDAO eventTicketDAO;
    private List<Ticket> allTickets = new ArrayList<>();


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
        for (int i = 0; i < allTickets.size(); i++) {
            if (allTickets.get(i).getId() == id) {
                allTickets.remove(allTickets.get(i));
                ticketDAO.deleteTicket(id);
                break;
            }
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
    public List <Ticket> getTicketsByUsed(Boolean used) {
        List<Ticket> listUsedTicket = new ArrayList<>();
        for(Ticket ticket : allTickets) {
            if(ticket.getUsed() == true) {
                listUsedTicket.add(ticket);
            }
        }
        return listUsedTicket;
    }

    @Override
    public List<Ticket> getTicketsByEventID(Event event){
        List<Integer> ticketID;
        List<Ticket> listTicketsByEvent = new ArrayList<>();

        ticketID = eventTicketDAO.getTicketsByEventID(event.getId());
        for(int j : ticketID) {
            listTicketsByEvent.add(getTicketByID(j));
        }
        return listTicketsByEvent;
    }
}