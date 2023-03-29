package bll;

import be.Account;
import bll.interfaces.IAccountManager;
import dal.AccountDAO;
import dal.interfaces.IAccountDAO;

import java.util.ArrayList;
import java.util.List;

public class AccountManager implements IAccountManager {
    IAccountDAO accountDAO;
    private List<Account> allAccounts;
    public AccountManager(){
        accountDAO = new AccountDAO();
        fillAllAccounts();
    }
    public void fillAllAccounts(){allAccounts = accountDAO.getAllAccounts();}

    @Override
    public List<Account> getAllAccounts(){return allAccounts;}

    @Override
    public void creatAccount(Account account) {
        allAccounts.add(account);
        accountDAO.creatAccount(account);
    }
    @Override
    public void deleteAccount(int id) {
        Account account = getAccountById(id);
        if (account != null){
        allAccounts.remove(account);
        accountDAO.deleteAccount(id);
        }
    }
    private Account getAccountById(int id) {
        for (Account account : allAccounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }
    @Override
    public List<Account> getAccountsByAccountType() {
        List<Account> result = new ArrayList<>();
        for (Account e : allAccounts) {
            if (e.getAccountType())
                result.add(e);
        }
        return result;
    }
}
