package com.telerik.extension_repository.repositories;

import com.telerik.extension_repository.entities.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

public interface PropertiesRepository {

    Properties getProperties();

    void updateInterval(@Param("updateInterval") long updateInterval);

    void updateLastSuccSync(@Param("lastSuccSync") Date lastSuccSync);

    void updateLastFailedSync(@Param("lastFailedSync") Date lastFailedSync);

    void updateFailureDetails(@Param("failureDetails") String failureDetails);

}
