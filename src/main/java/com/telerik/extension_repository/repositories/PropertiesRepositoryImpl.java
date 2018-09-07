package com.telerik.extension_repository.repositories;

import com.telerik.extension_repository.entities.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

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
    }

    @Override
    public Properties getProperties() {
        Properties p = em.createQuery("SELECT p from Properties p", Properties.class).getSingleResult();
        return p;
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
                    System.out.println(ie.getMessage());//TODO: logger
                }
            }

        }
    }

    @Override
    public void updateLastSuccSync(Date lastSuccSync) {

    }

    @Override
    public void updateLastFailedSync(Date lastFailedSync) {

    }

    @Override
    public void updateFailureDetails(String failureDetails) {

    }
}
