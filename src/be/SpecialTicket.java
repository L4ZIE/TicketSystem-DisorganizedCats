package be;


import javafx.scene.control.Button;

public class SpecialTicket extends TicketBase {
    private String ticketName;
    public SpecialTicket(int id, String ticketName, String qrCode, boolean used) {
        super(id, qrCode, used);
        this.ticketName = ticketName;

    }
    public String getTicketName() {
        return ticketName;
    }
    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }


    @Override
    public String toString() {
        return super.toString() + " " + getTicketName();
    }
}
