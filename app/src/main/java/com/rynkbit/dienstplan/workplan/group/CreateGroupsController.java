package com.rynkbit.dienstplan.workplan.group;

import android.view.View;

import com.rynkbit.dienstplan.R;

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
        }
    }
}
