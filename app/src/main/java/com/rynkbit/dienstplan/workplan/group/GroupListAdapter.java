package com.rynkbit.dienstplan.workplan.group;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rynkbit.dienstplan.entities.Task;
import com.rynkbit.dienstplan.workplan.WorkplanDataHolder;
import com.rynkbit.dienstplan.workplan.task.CreateTaskActivity;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

/**
 * Created by michael on 12/4/17.
 */

class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.GroupViewHolder> {

    public class GroupViewHolder extends RecyclerView.ViewHolder{
        TextView txtView1;
        TextView txtView2;

        public GroupViewHolder(View itemView) {
            super(itemView);

            txtView1 = itemView.findViewById(android.R.id.text1);
            txtView2 = itemView.findViewById(android.R.id.text2);
        }
    }

    private final CreateGroupsController controller;

    public GroupListAdapter(CreateGroupsController controller) {
        this.controller = controller;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        GroupViewHolder holder = new GroupViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        final int pos = position;
        List<Task> tasks = WorkplanDataHolder.getInstance()
                .getTaskGroups()
                .get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateTaskActivity.class);
                intent.putExtra("group", pos);
                v.getContext().startActivity(intent);
            }
        });

        holder.txtView1.setText(String.format(Locale.ENGLISH, "Trupp %d", position+1));

        if(tasks != null){
            holder.txtView2.setText(String.format(Locale.ENGLISH, "Posten: %d", tasks.size()));
        }
    }

    @Override
    public int getItemCount() {
        return
                WorkplanDataHolder.getInstance()
                        .getTaskGroups()
                        .size();
    }

}
