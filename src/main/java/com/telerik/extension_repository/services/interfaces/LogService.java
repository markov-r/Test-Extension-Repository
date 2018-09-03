package com.telerik.extension_repository.services.interfaces;

import com.telerik.extension_repository.models.bindingModels.log.LogModel;
import com.telerik.extension_repository.models.viewModels.log.LogView;

import java.util.List;

public interface LogService {
    void persist(LogModel logModel);
    List<LogView> getAllByUsername(String username);
    void deleteAllLogs();
}
