package com.rynkbit.dienstplan;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rynkbit.dienstplan.Workplan;
import com.rynkbit.dienstplan.db.contract.SoldierGroup;
import com.rynkbit.dienstplan.db.facade.ShiftFacade;
import com.rynkbit.dienstplan.db.facade.SoldierFacade;
import com.rynkbit.dienstplan.db.facade.TaskFacade;
import com.rynkbit.dienstplan.entities.Shift;
import com.rynkbit.dienstplan.workplan.FinishedWorkplanActivity;
import com.rynkbit.dienstplan.workplan.FinishedWorkplanDataHolder;

/**
 * Created by michael on 11/21/17.
 */

public class WorkplanAdapter extends RecyclerView.Adapter<WorkplanAdapter.WorklpanViewHolder>{
    class WorklpanViewHolder extends RecyclerView.ViewHolder{
        TextView text1, text2;
        public WorklpanViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
    private final ShiftFacade shiftFacade;
    private final TaskFacade taskFacade;

    public WorkplanAdapter(Context context) {
        shiftFacade = new ShiftFacade(context);
        taskFacade = new TaskFacade(context);
    }

    @Override
    public WorklpanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WorklpanViewHolder worklpanViewHolder;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);

        worklpanViewHolder = new WorklpanViewHolder(view);

        return worklpanViewHolder;
    }

    @Override
    public void onBindViewHolder(WorklpanViewHolder holder, int position) {
        final Shift shift = shiftFacade.getAll().get(position);
        int taskCount = taskFacade.getByShiftId(shift.getId()).size();

        holder.text1.setText(shift.getName());
        holder.text2.setText("Posten: " + taskCount);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinishedWorkplanDataHolder.getInstance()
                        .setShift(shift);
                Intent intent = new Intent(v.getContext(), FinishedWorkplanActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shiftFacade.getAll().size();
    }
}
