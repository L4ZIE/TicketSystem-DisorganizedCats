package bll.interfaces;

import be.Event;
import be.SpecialTicket;

import java.util.List;

public interface ISpecialTicketManager {
    List<SpecialTicket> getAllSpecTickets();

    void createSpecTicket(SpecialTicket specialTicket);
    void updateSpecTicket(SpecialTicket specTicket);
    int getMaxID();
    SpecialTicket getSpecTicketByID(int id);
    List<SpecialTicket> getSpecTicketsByUsed(boolean used);
    void deleteSpecialTicket(int id);
    List<SpecialTicket> getSpecTicketsByEventID(int id);
    List<SpecialTicket> searchForSpecTicket(String query);
     SpecialTicket getSpecTicketByName(String name);

    void setUseForSpecTicket(int id, boolean used);
    void massCreateSpecTicket(List<SpecialTicket> tickets);
}
