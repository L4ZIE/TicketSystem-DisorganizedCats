package be;

public abstract class TicketBase {
    private int id;
    private String qrCode;

    TicketBase(int id,String qrCode ){
        this.id = id;
        this.qrCode = qrCode;
    }

    abstract int getId();
    abstract String getQrCode();
    abstract void setQrCode(String qrCode);

}
