package com.telerik.extension_repository.repositories;

import com.telerik.extension_repository.entities.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;
import static javax.persistence.CacheRetrieveMode.BYPASS;

@Repository
public class PropertiesRepositoryImpl implements PropertiesRepository {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Autowired
    public PropertiesRepositoryImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @PostConstruct
    private void init() {
        this.em = emf.createEntityManager();
//        em.setProperty("javax.persistence.CacheRetrieveMode", "BYPASS");
    }

    @Override
    public Properties getProperties() {   //TODO: select works the first time only
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.setProperty("javax.persistence.CacheRetrieveMode", "javax.persistence.CacheStoreMode.BYPASS");
        List<Properties> p = em.createQuery("SELECT p from Properties p", Properties.class).getResultList()/*.getSingleResult()*/;
        et.commit();
        return p.get(0);
    }

    @Override
    public void updateInterval(long updateInterval) {

        for (int i = 0; i < 5; i++) {
            //long currentVersion = getProperties().getVersion();
            int updates = 0;

            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                long currentVersion = em.createQuery("SELECT p.version FROM Properties p", Long.class).getSingleResult();
                updates = em.createQuery("UPDATE Properties p SET p.updateInterval = :updateInterval, p.version = :newVersion WHERE p.version = :currVersion")
                        .setParameter("updateInterval", updateInterval)
                        .setParameter("currVersion", currentVersion)
                        .setParameter("newVersion", currentVersion + 1)
                        .executeUpdate();
                et.commit();
            } catch (RuntimeException e) {
                if (et.isActive()) {
                    et.rollback();
                }
            }
            if (updates == 1) {
                break;
            } else {
                try {
                    Thread.sleep((long) (Math.random() * 50));
                } catch (InterruptedException ie) {
                    System.out.println(ie.getMessage());//TODO: logger instead of system.out
                }
            }

        }
    }

    @Override
    public void updateLastSuccSync(Date lastSuccSync) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createQuery("UPDATE Properties p SET p.lastSuccSync = :lastSuccSync")
                .setParameter("lastSuccSync", lastSuccSync)
                .executeUpdate();
        et.commit();
    }

    @Override
    public void updateLastFailedSync(Date lastFailedSync) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createQuery("UPDATE Properties p SET p.lastFailedSync = :lastFailedSync")
                .setParameter("lastFailedSync", lastFailedSync)
                .executeUpdate();
        et.commit();
    }

    @Override
    public void updateFailureDetails(String failureDetails) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createQuery("UPDATE Properties p SET p.failureDetails = :failureDetails")
                .setParameter("failureDetails", failureDetails)
                .executeUpdate();
        et.commit();
    }
}
