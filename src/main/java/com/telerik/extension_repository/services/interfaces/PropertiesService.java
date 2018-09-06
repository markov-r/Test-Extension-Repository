package com.telerik.extension_repository.services.interfaces;

import com.telerik.extension_repository.entities.Properties;
import com.telerik.extension_repository.models.PropertiesDto;

import java.util.Date;

public interface PropertiesService {

    public void updateInterval(long updateInterval);
    public PropertiesDto getProperties();
    public void updateFailureDetails(String failureDetails);
    public void updateLastFailedSync(Date date);
    public void updateLastSuccSync(Date date);
}
