package pl.models;

import be.SpecialTicket;
import bll.SpecialTicketManager;
import bll.interfaces.ISpecialTicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SpecialTicketModel {
    private ISpecialTicketManager specTicketManager;

    public SpecialTicketModel(){
        specTicketManager = new SpecialTicketManager();
    }

    public ObservableList<SpecialTicket> getAllSpecTickets(){
        return FXCollections.observableArrayList(specTicketManager.getAllSpecTickets());
    }
    public SpecialTicket getSpecTicketByID(int id){
        return specTicketManager.getSpecTicketByID(id);
    }
    public ObservableList<SpecialTicket> getSpecTicketsByUsed(Boolean used){
        return FXCollections.observableArrayList(specTicketManager.getSpecTicketsByUsed(used));
    }
    public ObservableList<SpecialTicket> getSpecTicketsByEventID(int id){
       return FXCollections.observableArrayList(specTicketManager.getSpecTicketsByEventID(id));
    }
}
