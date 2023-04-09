package pl.models;

import be.Account;
import bll.AccountManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccountModel {
    AccountManager accountManager;

    public AccountModel(){
        accountManager = new AccountManager();
    }

    public ObservableList<Account> getAllAccounts(){
        return FXCollections.observableArrayList(accountManager.getAllAccounts());
    }
    public void createAccount(Account account){
        accountManager.creatAccount(account);
    }
    public void deleteAccount(int id){
        accountManager.deleteAccount(id);
    }
    public void updateAccount(Account account){
        accountManager.updateAccount(account);
    }
    public ObservableList<Account> getAccountsByType(boolean type){
        return FXCollections.observableArrayList(accountManager.getAccountsByType(type));
    }
    public Account getAccountByID(int id){
        return accountManager.getAccountById(id);
    }

    public int getMaxID(){
        return accountManager.getMaxID();
    }

}
