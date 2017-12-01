package com.rynkbit.dienstplan.workplan.task;

import android.view.View;

import com.rynkbit.dienstplan.R;
import com.rynkbit.dienstplan.entities.Task;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 12/1/17.
 */

public class CreateTaskController implements View.OnClickListener {
    private final CreateTaskActivity activity;
    private final TaskModel model;

    public CreateTaskController(CreateTaskActivity activity){
        this.activity = activity;
        this.model = activity.model;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCancel){
            activity.finish();
        }else if(v.getId() == R.id.btnSave){
            List<Task> tasks = new LinkedList<>();

            for (SmallTask smalltask : model.getTasks()){
                Task task = new Task();

                task.setPostId(smalltask.getPost().getId());
                task.setTimeFrom(smalltask.getTimeFrom());
                task.setTimeTo(smalltask.getTimeTo());

                tasks.add(task);
            }
        }
    }
}
