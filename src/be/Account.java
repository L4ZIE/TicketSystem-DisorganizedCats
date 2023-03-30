package be;

public class Account {
    private int id;
    private String username;
    private String password;
    private boolean accountType;
    //region getters and setters
    public int getId() {
        return id;
    }
    public String getUsername(){return username;}
    public void setUsername(String uName){this.username = uName;}
    public String getPassword(){return password;}
    public void setPassword(String uPassword){this.password = uPassword;}

    /**
     * This method returns if the user is an Admin or and Event Coordinator.
     * @return if true, then user is an Admin, on false user is Event Coordinator.
     */
    public boolean getAccountType(){return accountType;}

    /**
     * This method sets the account rights.
     * @param accountType True: Admin, False: Event Coordinator.
     */
    public void setAccountType(boolean accountType){this.accountType = accountType;}
    //endregion

    public Account(int id, String username, String password, boolean accountType){
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }
    @Override
    public String toString() {
        return id + " " + username + " " + password + " " + accountType;
    }
}
