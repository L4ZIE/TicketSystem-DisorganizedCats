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

    public AccountModel() {
        accountManager = new AccountManager();
    }


    public ObservableList<Account> getAllAccounts() {
        return FXCollections.observableArrayList(accountManager.getAllAccounts());

    }

    public void createAccount(Account account) {
        accountManager.creatAccount(account);
    }

    public void deleteAccount(int id) {
        accountManager.deleteAccount(id);

    }

    public void updateAccount(Account account) {
        accountManager.updateAccount(account);
    }

    public ObservableList<Account> getAccountsByType(boolean type) {
        return FXCollections.observableArrayList(accountManager.getAccountsByType(type));
    }

    public ObservableList<Account> searchForAccount(String query) {
        List<Account> filtered = accountManager.searchForAccount(query);
        return FXCollections.observableArrayList(filtered);
    }

    public Account getAccountByID(int id) {
        return accountManager.getAccountById(id);
    }

    public Boolean logInUser(String uName, String uPassword) {
        return accountManager.logInUser(uName, uPassword);
    }

    public int getMaxID() {
        return accountManager.getMaxID();
    }

    public Account getAccountByName(String name) {
        return accountManager.getAccountByName(name);
    }

}
