package DAO;

import Entity.Transfer;
import Services.CommonService;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TransferDAOImpl extends CommonDAO implements TransferDAO {

    @Override
    public void mergeTransfer(Transfer transfer) {
        super.merge(transfer);
    }

    @Override
    public List<Transfer> transfersByUser(Long userId) {
        EntityManager manager = CommonService.getManager();
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Transfer> querry = builder.createQuery(Transfer.class);
        Root<Transfer> root = querry.from(Transfer.class);
        return manager.createQuery(querry.select(root)
                .where(builder.equal(root.get("user"), userId))).getResultList();
    }

    @Override
    public List<Transfer> getAll() {
        return super.getAll(Transfer.class);
    }

}
