package com.emirleroglu.appcentdemo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String description;

    private Date taskDate;

    private Boolean complated = false;
    @NotBlank
    @OneToOne
    private AppUser user;

    public Task(String description, Date taskDate, Boolean complated, AppUser user) {
        this.description = description;
        this.taskDate = taskDate;
        this.complated = complated;
        this.user = user;
    }

    public Task() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public Boolean getComplated() {
        return complated;
    }

    public void setComplated(Boolean complated) {
        this.complated = complated;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id) && description.equals(task.description) && taskDate.equals(task.taskDate) && complated.equals(task.complated) && user.equals(task.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, taskDate, complated, user);
    }
}
