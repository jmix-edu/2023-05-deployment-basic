package com.company.pm.security;

import com.company.pm.entity.Task;
import com.company.pm.entity.TaskPriority;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;

import javax.annotation.Nonnull;

@Nonnull
@RowLevelRole(name = "ViewAssignedTasksRole", code = "view-assigned-tasks-role")
public interface ViewAssignedTasksRole {

    @JpqlRowLevelPolicy(entityClass = Task.class, where = "{E}.assignee.id = :current_user_id")
    void task();

    @PredicateRowLevelPolicy(entityClass = Task.class, actions = RowLevelPolicyAction.CREATE)
    default RowLevelBiPredicate<Task, ApplicationContext> taskPredicate() {
        return (task, applicationContext) ->
                task.getPriority() != TaskPriority.HIGH;
    }
}