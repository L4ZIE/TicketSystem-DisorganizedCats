package pl.models;

import be.Ticket;
import bll.TicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TicketModel {
    private TicketManager ticketManager;
    public TicketModel(){
        ticketManager = new TicketManager();
    }
    public ObservableList<Ticket> getAllTickets(){
        return FXCollections.observableArrayList(ticketManager.getAllTickets());
    }
    public Ticket getTicketByID(int id){
        return ticketManager.getTicketByID(id);}
    public ObservableList<Ticket> getTicketsByCustomer(String name){
        //waiting for backend
        return null;
    }
    public ObservableList<Ticket> getTicketsByEmail(String email){
        //waiting for backend
        return null;
    }
    public ObservableList<Ticket> getTicketsByPrice(int price){
        //waiting for backend
        return null;
    }
    public ObservableList<Ticket> getTicketsByUsed(Boolean used){
        //waiting for backend
        return null;
    }
    public ObservableList<Ticket> getTicketsByEventID(int id){
        //waiting for backend
        return null;
    }
}
