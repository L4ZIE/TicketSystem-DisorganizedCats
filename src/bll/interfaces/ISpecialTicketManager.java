package bll.interfaces;

import be.SpecialTicket;

import java.util.List;

public interface ISpecialTicketManager {
    List<SpecialTicket> getAllSpecTickets();

    SpecialTicket getSpecTicketByID(int id);

    List<SpecialTicket> getSpecTicketsByEventID(int id);

    void deleteSpecialTicket(int id);
}
