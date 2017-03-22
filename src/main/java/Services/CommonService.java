package Services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CommonService {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPATest");
    private static EntityManager manager;

    public static EntityManager getManager() {
        if (manager == null) {
            return manager = factory.createEntityManager();
        }
        return manager;
    }

    public static void close() {
        manager.close();
        factory.close();
    }
}
