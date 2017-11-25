package com.rynkbit.dienstplan.workplan.task;

import com.rynkbit.dienstplan.db.contract.Task;
import com.rynkbit.dienstplan.entities.Post;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 11/25/17.
 */

public class TaskModel {
    private Date timeFrom;
    private Date timeTo;
    private Post post;
    private List<SmallTask> tasks;

    public TaskModel() {
        tasks = new LinkedList<>();
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<SmallTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<SmallTask> tasks) {
        this.tasks = tasks;
    }
}
