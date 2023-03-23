package bll;

import be.SpecialTicket;
import bll.interfaces.ISpecialTicketManager;
import dal.interfaces.ISpecialTicketDAO;

import java.util.ArrayList;
import java.util.List;

public class SpecialTicketManager implements ISpecialTicketManager {
    ISpecialTicketDAO specialTicketDAO;
    private List<SpecialTicket> allSpecialTickets = new ArrayList<>();

    @Override
    public List<SpecialTicket> getAllSpecTickets(){
        return allSpecialTickets;
    }
    @Override
    public SpecialTicket getSpecTicketByID(int id){
        for (SpecialTicket specTicket : allSpecialTickets) {
            if (specTicket.getId() == id) {
                return specTicket;
            }
        }
        return null;
    }

    public List<SpecialTicket> getSpecTicketsByUsed (boolean used){

        // TODO need help
        return null;
    }

    @Override
    public List<SpecialTicket> getSpecTicketsByEventID(int id){
        //TODO
        // i will implement later
        return null;
    }

    @Override
    public void deleteSpecialTicket(int id) {
        SpecialTicket specialTicket = getSpecTicketByID(id);
        if(specialTicket!= null)
        {
            allSpecialTickets.remove(specialTicket);
            specialTicketDAO.deleteSpecialTicket(id);
        }
    }
}


