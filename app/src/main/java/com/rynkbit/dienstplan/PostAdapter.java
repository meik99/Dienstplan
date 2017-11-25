package com.rynkbit.dienstplan;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rynkbit.dienstplan.db.facade.PostFacade;
import com.rynkbit.dienstplan.entities.Post;
import com.rynkbit.dienstplan.post.PostActivity;

/**
 * Created by michael on 11/19/17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final Workplan workplan;
    private final PostFacade postFacade;

    public PostAdapter(Workplan workplan) {
        this.workplan = workplan;
        postFacade = new PostFacade(workplan);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        private final TextView txtPostName;
        private Post post;

        public PostViewHolder(View itemView) {
            super(itemView);
            txtPostName = itemView.findViewById(android.R.id.text1);
        }

        public void setPost(Post post) {
            this.post = post;
        }

        public Post getPost() {
            return post;
        }
    }


    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                android.R.layout.simple_list_item_1, parent, false
        );
        PostViewHolder viewHolder = new PostViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        final Post post = postFacade.getAll().get(position);
        holder.txtPostName.setText(post.getName());
        holder.setPost(post);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long postId = post.getId();
                Intent intent = new Intent(view.getContext(), PostActivity.class);
                intent.putExtra("post", postId);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postFacade.getAll().size();
    }
}
