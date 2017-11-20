package com.rynkbit.dienstplan.db.facade;

import android.content.Context;

import com.rynkbit.dienstplan.db.repository.PostRepository;
import com.rynkbit.dienstplan.entities.Post;

import java.util.List;

/**
 * Created by michael on 11/12/17.
 */

public class PostFacade {
    private final Context context;

    public PostFacade(Context context){
        this.context = context;
    }

    public List<Post> getAll(){
        return PostRepository.getInstance(context).getAll();
    }

    public void merge(Post post) {
        PostRepository.getInstance(context).merge(post);
    }

    public Post getById(long postId) {
        return PostRepository.getInstance(context).getById(postId);
    }
}
