package com.rynkbit.dienstplan.workplan.task;

import com.rynkbit.dienstplan.entities.Post;

import java.util.Date;

/**
 * Created by michael on 11/25/17.
 */

public class SmallTask {
    private Date timeFrom;
    private Date timeTo;
    private Post post;

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
}
