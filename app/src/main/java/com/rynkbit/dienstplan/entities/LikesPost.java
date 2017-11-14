package com.rynkbit.dienstplan.entities;

/**
 * Created by michael on 11/12/17.
 */

public class LikesPost {
    private Post post;
    private int value;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
