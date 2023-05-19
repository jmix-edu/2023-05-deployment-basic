package com.company.pm.screen.task;

import com.company.pm.app.TaskImportService;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.pm.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("pm_Task.browse")
@UiDescriptor("task-browse.xml")
@LookupComponent("tasksTable")
public class TaskBrowse extends StandardLookup<Task> {
    @Autowired
    private CollectionLoader<Task> tasksDl;

    @Autowired
    private TaskImportService taskImportService;
    @Autowired
    private Notifications notifications;

    @Subscribe("importBtn")
    public void onImportBtnClick(Button.ClickEvent event) {
        int imported = taskImportService.importTask();

        tasksDl.load();

        notifications.create()
                .withCaption(imported + " task imported")
                .show();
    }
}