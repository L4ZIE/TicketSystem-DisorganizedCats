package pl.models;

import be.Ticket;
import bll.TicketManager;
import bll.interfaces.ITicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TicketModel {
    private ObservableList<Ticket> tickets;
    private ITicketManager ticketManager;
    public TicketModel(){
        ticketManager = new TicketManager();
    }
    public ObservableList<Ticket> getAllTickets(){
        return FXCollections.observableArrayList(ticketManager.getAllTickets());
    }
    public Ticket getTicketByID(int id){
        return ticketManager.getTicketByID(id);
    }
    public ObservableList<Ticket> getTicketsByCustomer(String name){
        return FXCollections.observableArrayList(ticketManager.getTicketsByCustomer(name));
    }
    public ObservableList<Ticket> getTicketsByEmail(String email){
        return FXCollections.observableArrayList(ticketManager.getTicketsByEmail(email));
    }
    public ObservableList<Ticket> getTicketsByPrice(int price){
        return FXCollections.observableArrayList(ticketManager.getTicketsByPrice(price));
    }
    public ObservableList<Ticket> getTicketsByUsed(Boolean used){
        return FXCollections.observableArrayList(ticketManager.getTicketsByUsed(used));
    }
    public ObservableList<Ticket> getTicketsByEventID(int id){
        //waiting for backend
        return null;
    }

    public void createTicket(Ticket ticket){
        ticketManager.createTicket(ticket);
        tickets.add(ticket);
    }

    public void deleteTicket(Ticket ticket){
        ticketManager.deleteTicket(ticket.getId());
        tickets.remove(tickets.indexOf(ticket));
    }

    public void updateTicket(Ticket ticket){
        ticketManager.updateTicket(ticket);
        tickets = getAllTickets();
    }

    public void searchForTicket(String query){
        List<Ticket> filtered = ticketManager.searchForTicket(query,ticketManager.getAllTickets());
        tickets.clear();
        tickets.addAll(filtered);
    }

    public int getMaxID(){
        return ticketManager.getMaxID();
    }
}

