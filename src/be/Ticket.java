package be;

import com.microsoft.sqlserver.jdbc.SQLServerResource_it;

public class Ticket extends TicketBase{
    private int id;
    private String customerName;
    private String customerEmail;
    private int ticketType;
    private int ticketPrice;
    private String qrCode;
    private byte used;

    public Ticket( int id, String customerName, String customerEmail, int ticketType, int ticketPrice, String qrCode, byte used) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        this.qrCode = qrCode;
        this.used = used;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public byte getUsed() {
        return used;
    }

    public void setUsed(byte used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return id + " " + customerName+ " " + customerEmail+ " " + ticketType + " " + ticketPrice + " " + qrCode + " " + used;
    }

}


