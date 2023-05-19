package com.company.pm.security;

import com.company.pm.entity.Project;
import com.company.pm.entity.Subtask;
import com.company.pm.entity.Task;
import com.company.pm.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

import javax.annotation.Nonnull;

@Nonnull
@ResourceRole(name = "TaskCreator", code = "task-creator")
public interface TaskCreatorRole {

    @EntityAttributePolicy(entityClass = Task.class, attributes = "dueDate", action = EntityAttributePolicyAction.VIEW)
    @EntityAttributePolicy(entityClass = Task.class, attributes = {"id", "name", "project", "priority", "subtasks", "deletedBy", "deletedDate", "assignee"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Task.class, actions = {EntityPolicyAction.CREATE, EntityPolicyAction.UPDATE, EntityPolicyAction.READ})
    void task();

    @EntityAttributePolicy(entityClass = Project.class, attributes = {"name", "description"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Project.class, actions = EntityPolicyAction.READ)
    void project();

    @EntityAttributePolicy(entityClass = User.class, attributes = {"firstName", "lastName", "username"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();

    @EntityAttributePolicy(entityClass = Subtask.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Subtask.class, actions = EntityPolicyAction.ALL)
    void subtask();

    @MenuPolicy(menuIds = {"pm_Task.browse", "pm_Project.browse"})
    @ScreenPolicy(screenIds = {"pm_Task.browse", "pm_Task.edit", "pm_User.browse", "pm_Subtask.edit", "pm_Project.browse"})
    void screens();
}