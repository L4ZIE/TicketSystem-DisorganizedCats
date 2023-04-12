package pl.models;

import be.Account;
import bll.AccountManager;
import bll.interfaces.IAccountManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.List;

public class AccountModel {
    IAccountManager accountManager;

    public AccountModel(){
        accountManager = new AccountManager();
    }
    ObservableList<Account> allAccounts;

    public ObservableList<Account> getAllAccounts(){
        return allAccounts = FXCollections.observableArrayList(accountManager.getAllAccounts());

    }
    public void createAccount(Account account){
        accountManager.creatAccount(account);
        allAccounts.add(account);
    }
    public void deleteAccount(int id){
        accountManager.deleteAccount(id);
        allAccounts.remove(id);
    }
    public void updateAccount(Account account){
        accountManager.updateAccount(account);
        allAccounts = getAllAccounts();
    }
    public ObservableList<Account> getAccountsByType(boolean type){
        return FXCollections.observableArrayList(accountManager.getAccountsByType(type));
    }
    public void searchForAccount(String query){
        List<Account> filtered = accountManager.searchForAccount(query,accountManager.getAllAccounts());
        allAccounts.clear();
        allAccounts.addAll(filtered);
    }
    public Account getAccountByID(int id){
        return accountManager.getAccountById(id);
    }
    public void logInUser(String uName, String uPassword) {
        accountManager.logInUser(uName,uPassword);
    }

    public int getMaxID(){
        return accountManager.getMaxID();
    }

}
