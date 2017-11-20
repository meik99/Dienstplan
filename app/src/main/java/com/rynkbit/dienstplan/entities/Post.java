package com.rynkbit.dienstplan.entities;

/**
 * Created by michael on 11/12/17.
 */

public class Post {
    private long id;
    private String name;
    private PostType postType;
    private PostBurden postBurden;

    @Deprecated
    public Post(long id, String name, PostType postType, PostBurden postBurden) {
        this(id, name, postBurden);
    }

    public Post(long id, String name, PostBurden postBurden){
        this.id = id;
        this.name = name;
        this.postBurden = postBurden;
    }

    public Post() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public PostBurden getPostBurden() {
        return postBurden;
    }

    public void setPostBurden(PostBurden postBurden) {
        this.postBurden = postBurden;
    }

    public enum PostType{
        Patrol, COP, MotPatrol, Checkpoint
    }

    public enum PostBurden {
        Hard, Medium, Light, None
    }
}
