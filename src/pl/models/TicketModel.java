package pl.models;

import be.Ticket;
import bll.TicketManager;
import bll.interfaces.ITicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TicketModel {
    private ITicketManager ticketManager;

    public TicketModel() {

        ticketManager = new TicketManager();
    }

    public ObservableList<Ticket> getAllTickets() {
        return FXCollections.observableArrayList(ticketManager.getAllTickets());
    }

    public Ticket getTicketByID(int id) {
        return ticketManager.getTicketByID(id);
    }

    public ObservableList<Ticket> getTicketsByCustomer(String name) {
        return FXCollections.observableArrayList(ticketManager.getTicketsByCustomer(name));
    }

    public ObservableList<Ticket> getTicketsByEmail(String email) {
        return FXCollections.observableArrayList(ticketManager.getTicketsByEmail(email));
    }

    public ObservableList<Ticket> getTicketsByPrice(int price) {
        return FXCollections.observableArrayList(ticketManager.getTicketsByPrice(price));
    }

    public ObservableList<Ticket> getTicketsByUsed(Boolean used) {
        return FXCollections.observableArrayList(ticketManager.getTicketsByUsed(used));
    }

    public ObservableList<Ticket> getTicketsByEventID(int id) {

        return FXCollections.observableArrayList(ticketManager.getTicketsByEventID(id));
    }

    public void useTicket(int ticketID){
        ticketManager.useTicket(ticketID);
    }
    public void createTicket(Ticket ticket, int eventID) {
        ticketManager.createTicket(ticket, eventID);
    }

    public void deleteTicket(int id) {
        ticketManager.deleteTicket(id);
    }

    public void updateTicket(Ticket ticket) {
        ticketManager.updateTicket(ticket);
    }

    public ObservableList<Ticket> searchForTicket(String query) {
        ObservableList<Ticket> filtered = FXCollections.observableArrayList(ticketManager.searchForTicket(query));
        return filtered;
    }

    public int getMaxID() {
        return ticketManager.getMaxID();
    }
}

