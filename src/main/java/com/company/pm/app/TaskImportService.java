package com.company.pm.app;

import com.company.pm.entity.Project;
import com.company.pm.entity.Task;
import io.jmix.core.DataManager;
import io.jmix.core.EntitySet;
import io.jmix.core.SaveContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("pm_TaskImportService")
public class TaskImportService {

    private static final Logger log = LoggerFactory.getLogger(TaskImportService.class);

    @Autowired
    private final DataManager dataManager;

    public TaskImportService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public int importTask() {
        List<String> taskNames = obtainExternalTaskNames();
        Project defaultProject = loadDefaultProject();

        List<Task> tasks = taskNames.stream()
                .map(name -> {
                    Task task = dataManager.create(Task.class);
                    task.setName(name);
                    task.setProject(defaultProject);
                    return task;
                }).collect(Collectors.toList());

        EntitySet entitySet = dataManager.save(new SaveContext().saving(tasks));
        log.info("{} tasks imported", entitySet.size());

        return entitySet.size();
    }

    private List<String> obtainExternalTaskNames() {
        return Stream.iterate(0, i -> i).limit(5)
                .map(i -> RandomStringUtils.randomAlphabetic(5))
                .collect(Collectors.toList());
    }

    @Nullable
    public Project loadDefaultProject() {
        return dataManager.load(Project.class)
                .query("select p from pm_Project p where p.defaultProject = :defaultProject1")
                .parameter("defaultProject1", true)
                .optional().orElse(null);
    }
}