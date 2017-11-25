package com.rynkbit.dienstplan.workplan.task;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rynkbit.dienstplan.workplan.group.CreateGroupsActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by michael on 11/25/17.
 */

class ListTasksAdapter extends RecyclerView.Adapter<ListTasksAdapter.SmallTaskViewHolder> {
    private final CreateTaskActivity activity;

    class SmallTaskViewHolder extends RecyclerView.ViewHolder{
        TextView postName;
        TextView time;

        public SmallTaskViewHolder(View itemView) {
            super(itemView);

            postName = itemView.findViewById(android.R.id.text1);
            time = itemView.findViewById(android.R.id.text2);
        }
    }

    public ListTasksAdapter(CreateTaskActivity createTaskActivity) {
        this.activity = createTaskActivity;
    }

    @Override
    public SmallTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        SmallTaskViewHolder smallTaskViewHolder = new SmallTaskViewHolder(v);
        return smallTaskViewHolder;
    }

    @Override
    public void onBindViewHolder(SmallTaskViewHolder holder, int position) {
        SmallTask smallTask = activity.model.getTasks().get(position);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(simpleDateFormat.format(smallTask.getTimeFrom()));
        stringBuilder.append(" - ");
        stringBuilder.append(simpleDateFormat.format(smallTask.getTimeTo()));

        holder.postName.setText(smallTask.getPost().getName());
        holder.time.setText(
                stringBuilder.toString()
        );
    }

    @Override
    public int getItemCount() {
        return activity.model.getTasks().size();
    }
}
