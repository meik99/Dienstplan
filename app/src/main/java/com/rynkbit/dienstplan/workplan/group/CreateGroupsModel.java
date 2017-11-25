package com.rynkbit.dienstplan.workplan.group;

import android.view.View;
import android.widget.Button;

import com.rynkbit.dienstplan.entities.Shift;

/**
 * Created by michael on 11/25/17.
 */

public class CreateGroupsModel {
    private Shift shift;
    private Button buttonCancel;
    private Button buttonSave;

    public CreateGroupsModel() {
        this.shift = new Shift();
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public void setButtonCancel(Button buttonCancel) {
        this.buttonCancel = buttonCancel;
    }

    public Button getButtonCancel() {
        return buttonCancel;
    }

    public void setButtonSave(Button buttonSave) {
        this.buttonSave = buttonSave;
    }

    public Button getButtonSave() {
        return buttonSave;
    }
}
