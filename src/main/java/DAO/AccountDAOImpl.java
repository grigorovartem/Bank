package DAO;

import Entity.Account;
import Services.CommonService;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AccountDAOImpl extends CommonDAO implements AccountDAO {

    @Override
    public Account mergeAccount(Account account) {
        return super.merge(account);
    }

    @Override
    public void deleteAccount(Account account) {
        super.delete(account);
    }

    @Override
    public List<Account> getAll() {
        return super.getAll(Account.class);
    }

    @Override
    public Account getByID(Long accountId) {
        return super.getById(accountId, Account.class);
    }

    public List<Account> accountsByUser(Long userId) {
        EntityManager manager = CommonService.getManager();
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Account> querry = builder.createQuery(Account.class);
        Root<Account> root = querry.from(Account.class);
        return manager.createQuery(querry.select(root)
                .where(builder.equal(root.get("user"), userId))).getResultList();
    }
}
