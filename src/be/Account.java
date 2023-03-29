package be;

public class Account {
    private int id;
    private String uName ;
    private String uPassword;
    private boolean accountType;
    //region getters and setters
    public int getId() {
        return id;
    }
    public String getUName(){return uName;}
    public void setUName(String uName){this.uName = uName;}
    public String getUPassword(){return uPassword;}
    public void setUPassword(String uPassword){this.uPassword = uPassword;}

    /**
     * this method returns if the user is an Admin or and Event Coordinator.
     * @return if true, then user is an Admin , on false user is Event Coordinator.
     */
    public boolean getAccountType(){return accountType;}
    public void setAccountType(boolean accountType){this.accountType = accountType;}
    //endregion

    public Account(int id, String uName, String uPassword, boolean accountType){
        this.id = id;
        this.uName = uName;
        this.uPassword = uPassword;
        this.accountType = accountType;
    }
    @Override
    public String toString() {
        return id + " " + uName + " " + uPassword + " " + accountType;
    }
}
