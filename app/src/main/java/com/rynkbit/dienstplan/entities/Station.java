package com.rynkbit.dienstplan.entities;

import java.util.Date;

/**
 * Created by michael on 11/12/17.
 */

public class Station {
    private Date fromTime;
    private Date toTime;
    private Post post;

    public Station(Date fromTime, Date toTime, Post post) {
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.post = post;
    }

    public Station() {


    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
