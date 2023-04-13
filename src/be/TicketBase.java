package be;

public abstract class TicketBase {
    private int id;
    private String qrCode;
    private Boolean used;

    public TicketBase(int id,String qrCode, boolean used ){
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

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }
}
