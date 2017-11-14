package com.rynkbit.dienstplan.db.facade;

import com.rynkbit.dienstplan.db.repository.PostRepository;
import com.rynkbit.dienstplan.entities.Post;

import java.util.List;

/**
 * Created by michael on 11/12/17.
 */

public class PostFacade {
    public List<Post> getAll(){
        return PostRepository.getInstance().getAll();
    }
}
