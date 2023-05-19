package com.company.pm.screen.task;

import com.company.pm.app.TaskImportService;
import com.company.pm.entity.Project;
import com.company.pm.entity.User;
import io.jmix.core.usersubstitution.CurrentUserSubstitution;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.pm.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("pm_Task.edit")
@UiDescriptor("task-edit.xml")
@EditedEntityContainer("taskDc")
public class TaskEdit extends StandardEditor<Task> {

    @Autowired
    private TaskImportService taskImportService;
    @Autowired
    private CurrentUserSubstitution currentUserSubstitution;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Task> event) {
        User user = (User) currentUserSubstitution.getEffectiveUser();
        event.getEntity().setAssignee(user);

        event.getEntity().setProject(taskImportService.loadDefaultProject());
    }

    /*@Subscribe("projectField")
    public void onProjectFieldValueChange(HasValue.ValueChangeEvent<Project> event) {
        if (event.isUserOriginated()) {
            Project newProject = event.getValue();
            if (newProject != null) {
                getEditedEntity().setPriority(newProject.getDefaultTaskPriority());
            }
        }
    }*/

    @Subscribe(id = "taskDc", target = Target.DATA_CONTAINER)
    public void onTaskDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Task> event) {
        if ("project".equals(event.getProperty())) {
            Project newProject = ((Project) event.getValue());
            if (newProject != null) {
                event.getItem().setPriority(newProject.getDefaultTaskPriority());
            }
        }
    }
}