package DAO;

import Entity.Account;

import java.util.List;

public interface AccountDAO {

    Account mergeAccount(Account account);
    void deleteAccount(Account account);
    List<Account> getAll();
    Account getByID(Long accountId);
    List<Account> accountsByUser(Long userId);
}
