package pl.models;

import be.SpecialTicket;
import bll.SpecialTicketManager;
import bll.interfaces.ISpecialTicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SpecialTicketModel {
    private ISpecialTicketManager specTicketManager;
    private ObservableList<SpecialTicket> listSpecialTickets ;

    public SpecialTicketModel() {
        specTicketManager = new SpecialTicketManager();
    }

    public ObservableList<SpecialTicket> getAllSpecTickets() {
        return listSpecialTickets = FXCollections.observableArrayList(
                specTicketManager.getAllSpecTickets());
    }

    public void createSpecTicket(SpecialTicket specialTicket) {
        specTicketManager.createSpecTicket(specialTicket);
        listSpecialTickets.add(specialTicket);
    }

    public void deleteSpecTicket(SpecialTicket specialTicket) {
        specTicketManager.deleteSpecialTicket(specialTicket.getId());
        listSpecialTickets.remove(listSpecialTickets.indexOf(specialTicket));
    }

    public void updateSpecTicket(SpecialTicket specialTicket) {
        specTicketManager.updateSpecTicket(specialTicket);
        listSpecialTickets = getAllSpecTickets();
    }

    public void searchForSpecTicket(String query) {
        List<SpecialTicket> searched = specTicketManager.searchForSpecTicket(query);
        listSpecialTickets.clear();
        listSpecialTickets.addAll(searched);
    }

    public int getMaxID() {
        return specTicketManager.getMaxID();
    }

    public SpecialTicket getSpecTicketByID(int id) {
        return specTicketManager.getSpecTicketByID(id);
    }

    public ObservableList<SpecialTicket> getSpecTicketsByUsed(Boolean used) {
        return FXCollections.observableArrayList(specTicketManager.getSpecTicketsByUsed(used));
    }

    public ObservableList<SpecialTicket> getSpecTicketsByEventID(int id) {
        return FXCollections.observableArrayList(specTicketManager.getSpecTicketsByEventID(id));
    }

    public SpecialTicket getSpecTicketByName(String name) {
        return specTicketManager.getSpecTicketByName(name);
    }

    public void setUseForSpecTicket(int id, boolean used) {
        specTicketManager.setUseForSpecTicket(id,used);
    }
    public void massCreateSpecTicket(List<SpecialTicket> tickets){specTicketManager.massCreateSpecTicket(tickets);}
    public void saveSpecEventConnections(int specTicketID, List<Integer> eventIDs){

    }
}
