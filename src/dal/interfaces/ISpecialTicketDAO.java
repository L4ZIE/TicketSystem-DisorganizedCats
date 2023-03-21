package dal.interfaces;

import be.SpecialTicket;
import be.Ticket;

import java.util.List;

public interface ISpecialTicketDAO {
    List<SpecialTicket> getAllSpecialTickets();
    void createSpecialTicket(SpecialTicket specialTicket);
    void deleteSpecialTicket(int id);
}
