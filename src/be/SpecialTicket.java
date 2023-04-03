package be;


import javafx.scene.control.Button;

public class SpecialTicket extends TicketBase {
    private String ticketName;
    private Button button;
    public SpecialTicket(int id, String ticketName, String qrCode, boolean used) {
        super(id, qrCode, used);
        this.ticketName = ticketName;
        this.button = new Button("Print");

    }
    public String getTicketName() {
        return ticketName;
    }
    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }
    public Button getButton() {
        return button;
    }public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getTicketName();
    }
}
