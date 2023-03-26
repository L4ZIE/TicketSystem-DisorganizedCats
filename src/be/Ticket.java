package be;

import com.microsoft.sqlserver.jdbc.SQLServerResource_it;

public class Ticket extends TicketBase{
    private int id;
    private String customerName;
    private String customerEmail;
    private int ticketType;
    private int ticketPrice;
    private String qrCode;
    private boolean used;

    public Ticket( int id, String customerName, String customerEmail, int ticketType, int ticketPrice, String qrCode, boolean used) {
        super(id, qrCode,used);
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public int getTicketType() {
        return ticketType;
    }
    public void setTicketType(int ticketType) {
        this.ticketType = ticketType;
    }
    public int getTicketPrice() {
        return ticketPrice;
    }
    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return super.toString() + " " + customerName + " " + customerEmail+ " " + ticketType + " " + ticketPrice;
    }
}


