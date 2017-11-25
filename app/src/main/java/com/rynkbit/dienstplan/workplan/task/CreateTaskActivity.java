package com.rynkbit.dienstplan.workplan.task;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rynkbit.dienstplan.R;

public class CreateTaskActivity extends AppCompatActivity {
    TaskModel model = new TaskModel();

    RecyclerView listTasks;
    ListTasksAdapter listTasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listTasks = (RecyclerView) findViewById(R.id.listTasks);
        listTasksAdapter = new ListTasksAdapter(this);

        listTasks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listTasks.setAdapter(listTasksAdapter);

        FloatingActionButton fabAddTask = (FloatingActionButton) findViewById(R.id.fabAddTask);
        fabAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskDialog dialog = new TaskDialog(CreateTaskActivity.this);

                dialog.show();
            }
        });
    }

    public void addTask() {
        SmallTask smallTask = new SmallTask();
        smallTask.setPost(model.getPost());
        smallTask.setTimeFrom(model.getTimeFrom());
        smallTask.setTimeTo(model.getTimeTo());

        model.getTasks().add(smallTask);
        listTasksAdapter.notifyDataSetChanged();
    }
}
