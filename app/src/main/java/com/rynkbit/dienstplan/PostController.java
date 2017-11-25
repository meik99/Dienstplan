package com.rynkbit.dienstplan;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rynkbit.dienstplan.post.PostAddPostClickListener;

/**
 * Created by michael on 11/12/17.
 */

class PostController implements Controller {
    RecyclerView listWorkplan;
    FloatingActionButton fabWorkplan;
    PostAdapter postAdapter;

    public PostController(Workplan workplan) {
        listWorkplan = (RecyclerView) workplan.findViewById(R.id.listWorkplan);
        fabWorkplan = (FloatingActionButton) workplan.findViewById(R.id.fabWorkplan);
        postAdapter = new PostAdapter(workplan);

        listWorkplan.setLayoutManager(new LinearLayoutManager(workplan, LinearLayoutManager.VERTICAL, false));
        listWorkplan.setAdapter(postAdapter);
        postAdapter.notifyDataSetChanged();

        fabWorkplan.setOnClickListener(new PostAddPostClickListener());
    }

    @Override
    public void update() {
        postAdapter.notifyDataSetChanged();
    }
}
