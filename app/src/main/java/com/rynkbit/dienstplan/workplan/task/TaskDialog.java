package com.rynkbit.dienstplan.workplan.task;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;

import com.rynkbit.dienstplan.R;
import com.rynkbit.dienstplan.db.facade.PostFacade;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by michael on 11/25/17.
 */

public class TaskDialog extends AlertDialog implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private final CreateTaskActivity activity;
    Spinner spinnerPosts;
    EditText timeFrom;
    EditText timeTo;
    Button btnCancel;
    Button btnSave;
    PostFacade postFacade;

    public TaskDialog(@NonNull CreateTaskActivity createTaskActivity) {
        super(createTaskActivity);

        this.activity = createTaskActivity;
        postFacade = new PostFacade(createTaskActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_task);

        spinnerPosts = (Spinner) findViewById(R.id.spinnerPosts);
        timeFrom = (EditText) findViewById(R.id.txtTimeFrom);
        timeTo = (EditText) findViewById(R.id.txtTimeTo);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSave = (Button) findViewById(R.id.btnSave);

        spinnerPosts.setAdapter(new PostSpinnerAdapter(activity));
        spinnerPosts.setOnItemSelectedListener(this);

        timeFrom.setOnClickListener(
                this
        );
        timeTo.setOnClickListener(
                this
        );

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskDialog.this.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activity.model.getPost() == null){
                    activity.model.setPost(
                            postFacade.getAll().get(0)
                    );
                }

                activity.addTask();
                TaskDialog.this.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.txtTimeFrom ||
                v.getId() == R.id.txtTimeTo){
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog timePickerDialog = null;

            timePickerDialog = new TimePickerDialog(
                    activity,
                    getTimeClickListener(v.getId()),
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true);

            timePickerDialog.show();
        }
    }

    private TimePickerDialog.OnTimeSetListener getTimeClickListener(@IdRes final int id) {
        return new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String text = String.format(Locale.ENGLISH, "%02d:%02d", hourOfDay, minute);
                Date date = new Date(0);
                int offset = Calendar.getInstance().getTimeZone().getOffset(date.getTime());

                date.setTime(hourOfDay * 60 * 60 * 1000);
                date.setTime(date.getTime() + minute * 60 * 1000);
                date.setTime(date.getTime() + offset);

                if(id == R.id.txtTimeFrom){
                    TaskDialog.this.timeFrom.setText(text);
                    activity.model.setTimeFrom(date);
                }else{
                    TaskDialog.this.timeTo.setText(text);
                    activity.model.setTimeTo(date);
                }
            }
        };
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        activity.model.setPost(
                postFacade.getById(id)
        );
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
