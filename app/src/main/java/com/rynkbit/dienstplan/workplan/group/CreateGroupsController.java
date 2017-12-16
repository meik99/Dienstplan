package com.rynkbit.dienstplan.workplan.group;

import android.view.View;

import com.rynkbit.dienstplan.R;
import com.rynkbit.dienstplan.Workplan;
import com.rynkbit.dienstplan.db.facade.ShiftFacade;
import com.rynkbit.dienstplan.db.facade.TaskFacade;
import com.rynkbit.dienstplan.entities.Shift;
import com.rynkbit.dienstplan.entities.Task;
import com.rynkbit.dienstplan.workplan.WorkplanDataHolder;

import java.util.Enumeration;
import java.util.List;

/**
 * Created by michael on 11/25/17.
 */

class CreateGroupsController implements View.OnClickListener {
    private final CreateGroupsActivity activity;
    private CreateGroupsModel model;

    public CreateGroupsController(CreateGroupsActivity activity) {
        this.activity = activity;
        this.model = new CreateGroupsModel();
    }

    public CreateGroupsModel getModel() {
        return model;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCancel:
                activity.finish();
                break;
            case R.id.btnSave:
                saveTasks();
                activity.finish();
                break;
        }
    }

    private void saveTasks() {
        Enumeration<Integer> keys = WorkplanDataHolder
                .getInstance()
                .getTaskGroups()
                .keys();
        Shift shift = new Shift();
        ShiftFacade shiftFacade = new ShiftFacade(activity);
        TaskFacade taskFacade = new TaskFacade(activity);

        shiftFacade.merge(shift);
        shift = shiftFacade.getAll().get(
                shiftFacade.getAll().size()-1
        );

        while(keys.hasMoreElements()){
            int currentKey = keys.nextElement();
            List<Task> currentTask = WorkplanDataHolder
                    .getInstance()
                    .getTaskGroups()
                    .get(currentKey);

            for (Task task : currentTask){
                task.setShiftId(shift.getId());
                taskFacade.merge(task);
            }
        }
    }
}
