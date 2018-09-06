package com.telerik.extension_repository.repositories;

import com.telerik.extension_repository.entities.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties, Long> {

    @Query (value = "SELECT p from Properties p")
    Properties getProperties();

    @Transactional
    @Modifying
    @Query(value =
            "UPDATE Properties p " +
                    "SET p.updateInterval = :updateInterval WHERE id = 1")
    void updateInterval(@Param("updateInterval") long updateInterval);

    @Transactional
    @Modifying
    @Query(value =
            "UPDATE Properties p " +
                    "SET p.lastSuccSync = :lastSuccSync WHERE id = 1")
    void updateLastSuccSync(@Param("lastSuccSync") Date lastSuccSync);

    @Transactional
    @Modifying
    @Query(value =
            "UPDATE Properties p " +
                    "SET p.lastFailedSync = :lastFailedSync WHERE id = 1")
    void updateLastFailedSync(@Param("lastFailedSync") Date lastFailedSync);

    @Transactional
    @Modifying
    @Query(value =
            "UPDATE Properties p " +
                    "SET p.failureDetails = :failureDetails WHERE id = 1")
    void updateFailureDetails(@Param("failureDetails") String failureDetails);

}
