package com.company.pm.app;

import com.company.pm.entity.Project;

import javax.annotation.Nullable;

public interface TaskImportService {
    int importTask();

    @Nullable
    Project loadDefaultProject();
}
