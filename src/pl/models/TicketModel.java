package pl.models;

import be.Ticket;
import bll.TicketManager;
import bll.interfaces.ITicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TicketModel {
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
}
