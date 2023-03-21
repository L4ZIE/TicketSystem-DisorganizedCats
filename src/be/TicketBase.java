package be;

public abstract class TicketBase {
    private int id;
    private String qrCode;
    private byte used;

    public TicketBase(int id,String qrCode, byte used ){
        this.id = id;
        this.qrCode = qrCode;
        this.used = used;
    }

    public int getId(){
        return id;
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
    public boolean equals(Object obj) {
        TicketBase compTicketBase = (TicketBase) obj;
        return compTicketBase.getId() == this.getId();
    }

   @Override
    public String toString() {
        return getId() + " " + getQrCode() + " " + getUsed();
    }
}
