package dal.interfaces;

import be.SpecialTicket;
import be.Ticket;

import java.util.List;

public interface ISpecialTicketDAO {
    List<SpecialTicket> getAllSpecialTickets();
    void createSpecialTicket(SpecialTicket specialTicket);
    void deleteSpecialTicket(int id);

    void updateSpecTicket(SpecialTicket selectedSpecTicket);

    void setUseForSpecTicket(int id, boolean used);

    /**
     * Creates special tickets inside the DB from a list.
     * @param tickets List of the special tickets.
     */
    void massCreateSpecTicket(List<SpecialTicket> tickets);
}
