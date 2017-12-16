package com.rynkbit.dienstplan.workplan.task;

import android.view.View;

import com.rynkbit.dienstplan.R;
import com.rynkbit.dienstplan.Workplan;
import com.rynkbit.dienstplan.db.facade.TaskFacade;
import com.rynkbit.dienstplan.entities.Task;
import com.rynkbit.dienstplan.workplan.WorkplanDataHolder;
import com.rynkbit.dienstplan.workplan.group.CreateGroupsActivity;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 12/1/17.
 */

public class CreateTaskController implements View.OnClickListener {
    private final CreateTaskActivity activity;
    private final TaskModel model;
    private final TaskFacade facade;
    private int groupNumber = -1;

    public CreateTaskController(CreateTaskActivity activity){
        this.activity = activity;
        this.model = activity.model;
        this.facade = new TaskFacade(activity);

        if(activity.getIntent().hasExtra("group")){
            groupNumber = activity.getIntent().getIntExtra("group", -1);
        }else{
            groupNumber = WorkplanDataHolder.getInstance()
                    .getTaskGroups().size();
            model.setTasks(WorkplanDataHolder.getInstance()
            .getTaskGroups().get(groupNumber));
        }

        model.setGroupNumber(groupNumber);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCancel){
            activity.finish();
        }else if(v.getId() == R.id.btnSave){
            WorkplanDataHolder.getInstance()
                    .getTaskGroups()
                    .put(groupNumber, model.getTasks());

            activity.finish();
        }
    }
}
