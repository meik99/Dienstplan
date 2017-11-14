package com.rynkbit.dienstplan.db.repository;

import com.rynkbit.dienstplan.entities.Post;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 11/12/17.
 */

public class PostRepository {
    private static PostRepository instance;

    public static PostRepository getInstance() {
        if(instance == null){
            instance = new PostRepository();
        }
        return instance;
    }

    private List<Post> postList;

    private PostRepository(){
        postList = new LinkedList<>();

        for(int i = 0; i < 3; i++){
            postList.add(new Post(
                    i, "COP " + i, Post.PostType.COP, Post.PostBurden.Medium
            ));
        }
    }

    public List<Post> getAll(){
        return postList;
    }

}
