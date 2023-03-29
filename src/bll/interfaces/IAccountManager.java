package bll.interfaces;

import be.Account;

import java.util.List;

public interface IAccountManager {
    List<Account> getAllAccounts();

    void creatAccount(Account account);

    void deleteAccount(int id);

    List<Account> getAccountsByAccountType();
}
