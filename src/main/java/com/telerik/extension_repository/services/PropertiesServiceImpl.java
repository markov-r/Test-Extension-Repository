package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.Properties;
import com.telerik.extension_repository.models.PropertiesDto;
import com.telerik.extension_repository.repositories.PropertiesRepository;
import com.telerik.extension_repository.services.interfaces.PropertiesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PropertiesServiceImpl implements PropertiesService {

    @Autowired
    PropertiesRepository propertiesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void updateInterval(long updateInterval) {
        this.propertiesRepository.updateInterval(updateInterval * 60000);   //turn minutes to miliseconds
    }

    public PropertiesDto getProperties() {
        Properties properties = this.propertiesRepository.getProperties();
        return this.modelMapper.map(properties, PropertiesDto.class);
    }

    @Override
    public void updateFailureDetails(String failureDetails) {
        this.propertiesRepository.updateFailureDetails(failureDetails);
    }

    @Override
    public void updateLastFailedSync(Date date) {
        this.propertiesRepository.updateLastFailedSync(date);
    }

    @Override
    public void updateLastSuccSync(Date date) {
        this.propertiesRepository.updateLastSuccSync(date);
    }
}