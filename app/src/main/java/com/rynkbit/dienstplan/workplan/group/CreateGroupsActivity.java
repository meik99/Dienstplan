package com.rynkbit.dienstplan.workplan.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.rynkbit.dienstplan.R;
import com.rynkbit.dienstplan.workplan.task.CreateTaskActivity;

public class CreateGroupsActivity extends AppCompatActivity {
    private CreateGroupsController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_groups);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        controller = new CreateGroupsController(this);

        controller.getModel().setButtonCancel(
                (Button) findViewById(R.id.btnCancel)
        );
        controller.getModel().setButtonSave(
                (Button) findViewById(R.id.btnSave)
        );

        controller.getModel().getButtonCancel().setOnClickListener(controller);
        controller.getModel().getButtonSave().setOnClickListener(controller);

        FloatingActionButton fabAddGroup = (FloatingActionButton) findViewById(R.id.fabAddGroup);
        fabAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateTaskActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
