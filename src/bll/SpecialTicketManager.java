package bll;

import be.SpecialTicket;
import bll.interfaces.ISpecialTicketManager;
import dal.SpecialTicketDAO;
import dal.interfaces.IEventSpecTicketDAO;
import dal.interfaces.ISpecialTicketDAO;

import java.util.ArrayList;
import java.util.List;

public class SpecialTicketManager implements ISpecialTicketManager {
    ISpecialTicketDAO specialTicketDAO;
    IEventSpecTicketDAO eventSpecTicketDAO;
    private List<SpecialTicket> allSpecialTickets = new ArrayList<>();

    public SpecialTicketManager() {
        specialTicketDAO = new SpecialTicketDAO();
        fillAllSpecTickets();
    }

    private void fillAllSpecTickets() {
        allSpecialTickets = specialTicketDAO.getAllSpecialTickets();
    }

    @Override
    public List<SpecialTicket> getAllSpecTickets() {
        fillAllSpecTickets();
        return allSpecialTickets;
    }

    @Override
    public void createSpecTicket(SpecialTicket specialTicket) {
        allSpecialTickets.add(specialTicket);
        specialTicketDAO.createSpecialTicket(specialTicket);
    }

     @Override
    public void deleteSpecialTicket(int id) {
        SpecialTicket specialTicket = getSpecTicketByID(id);
        if (specialTicket != null) {
            allSpecialTickets.remove(specialTicket);
            specialTicketDAO.deleteSpecialTicket(id);
        }
    }

    @Override
    public void updateSpecTicket(SpecialTicket specTicket) {
        specialTicketDAO.updateSpecTicket(specTicket);
        allSpecialTickets = specialTicketDAO.getAllSpecialTickets();
    }
    @Override
    public int getMaxID() {
        int max = 0;

        for (SpecialTicket specialTicket : allSpecialTickets) {
            if(max < specialTicket.getId())
                max = specialTicket.getId();
        }
        return max;
    }

    @Override
    public SpecialTicket getSpecTicketByID(int id) {
        for (SpecialTicket specTicket : allSpecialTickets) {
            if (specTicket.getId() == id) {
                return specTicket;
            }
        }
        return null;
    }

    @Override
    public List<SpecialTicket> getSpecTicketsByUsed(boolean used) {
        List<SpecialTicket> usedSpecialTicket = new ArrayList<>();
        for (SpecialTicket specTicket : allSpecialTickets) {
            if (specTicket.getUsed() == true) {
                usedSpecialTicket.add(specTicket);
            }
        }
        return usedSpecialTicket;
    }
    @Override
    public List<SpecialTicket> getSpecTicketsByEventID(int id) {
        List<Integer> ticketID;
        List<SpecialTicket> listSpecTicketsByEvent = new ArrayList<>();

        ticketID = eventSpecTicketDAO.getSpecTicketsByEventID(id);
        for(int i : ticketID) {
            listSpecTicketsByEvent.add(getSpecTicketByID(i));
        }
        return listSpecTicketsByEvent;
    }
    public List<SpecialTicket> searchForSpecTicket(String query) {
        return null;
    }
}



