package DAO;

import Services.CommonService;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class CommonDAO {

    private EntityManager manager = CommonService.getManager();
    private CriteriaBuilder builder = manager.getCriteriaBuilder();

    public <T> T merge(T entity) {
        T entityDB = null;
        manager.getTransaction().begin();
        try {
            entityDB = manager.merge(entity);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            manager.getTransaction().rollback();
        } finally {
            manager.clear();
        }
        return entityDB;
    }

    public <T> void delete(T entity) {
        manager.getTransaction().begin();
        try {
            manager.remove(entity);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            manager.getTransaction().rollback();
        }
        manager.clear();
    }

    public <T> List<T> getAll(Class<T> clazz) {
        CriteriaQuery<T> querry = builder.createQuery(clazz);
        Root<T> root = querry.from(clazz);
        return manager.createQuery(querry.select(root)).getResultList();
    }

    public <T> T getById(Object id, Class<T> clazz) {
        return manager.find(clazz, id);
    }
}
