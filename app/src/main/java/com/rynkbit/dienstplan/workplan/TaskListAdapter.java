package com.rynkbit.dienstplan.workplan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rynkbit.dienstplan.db.facade.PostFacade;
import com.rynkbit.dienstplan.entities.Post;
import com.rynkbit.dienstplan.entities.Task;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by michael on 12/14/17.
 */

class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder> {
    private final List<Task> tasks;
    private PostFacade postFacade;

    public TaskListAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    class TaskListViewHolder extends RecyclerView.ViewHolder{
        TextView text1, text2;

        public TaskListViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }

    @Override
    public TaskListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        TaskListViewHolder holder = new TaskListViewHolder(v);

        if(postFacade == null){
            postFacade = new PostFacade(parent.getContext());
        }

        return holder;

    }

    @Override
    public void onBindViewHolder(TaskListViewHolder holder, int position) {
        Task task = tasks.get(position);
        Post post = postFacade.getById(task.getPostId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:MM");

        holder.text1.setText(post.getName());
        holder.text2.setText(
                String.format(
                        "%s - %s",
                        simpleDateFormat.format(task.getTimeFrom()),
                        simpleDateFormat.format(task.getTimeTo()))
        );
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
