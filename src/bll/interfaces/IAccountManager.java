package bll.interfaces;

import be.Account;

import java.util.List;

public interface IAccountManager {
    List<Account> getAllAccounts();
    void creatAccount(Account account);
    void deleteAccount(int id);
    void updateAccount(Account account);
    List<Account> getAccountsByType(boolean type);
    Account getAccountById(int id);

    int getMaxID();
}
