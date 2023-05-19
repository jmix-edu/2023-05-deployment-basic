package com.company.pm.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "PM_TASK", indexes = {
        @Index(name = "IDX_PM_TASK_ASSIGNEE", columnList = "ASSIGNEE_ID"),
        @Index(name = "IDX_PM_TASK_PROJECT", columnList = "PROJECT_ID")
})
@Entity(name = "pm_Task")
public class Task {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @FutureOrPresent
    @Column(name = "DUE_DATE")
    private LocalDateTime dueDate;

    @JoinColumn(name = "ASSIGNEE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User assignee;

    @JoinColumn(name = "PROJECT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @Column(name = "PRIORITY")
    private Integer priority;

    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    @OneToMany(mappedBy = "task")
    private List<Subtask> subtasks;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    public TaskPriority getPriority() {
        return priority == null ? null : TaskPriority.fromId(priority);
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority == null ? null : priority.getId();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}