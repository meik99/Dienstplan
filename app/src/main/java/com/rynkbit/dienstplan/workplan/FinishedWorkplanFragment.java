package com.rynkbit.dienstplan.workplan;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rynkbit.dienstplan.R;
import com.rynkbit.dienstplan.SoldierAdapter;
import com.rynkbit.dienstplan.db.facade.TaskFacade;
import com.rynkbit.dienstplan.entities.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by michael on 12/14/17.
 */

public class FinishedWorkplanFragment extends Fragment{
    private List<Task> tasks;
    private int groupNumber;

    public FinishedWorkplanFragment(){
        tasks = new LinkedList<>();
    }

    public static FinishedWorkplanFragment getInstance(Context context, int groupNumber) {
        FinishedWorkplanFragment finishedWorkplanFragment = new FinishedWorkplanFragment();
        TaskFacade taskFacade = new TaskFacade(context);
        List<Task> groupedTasks = new LinkedList<>();
        List<Task> tasks;

        tasks = taskFacade
                .getByShiftId(
                        FinishedWorkplanDataHolder.getInstance()
                                .getShift()
                                .getId());

        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getGroupNumber() == groupNumber){
                groupedTasks.add(tasks.get(i));
            }
        }


        finishedWorkplanFragment.groupNumber = groupNumber;
        finishedWorkplanFragment.setTasks(groupedTasks);

        return finishedWorkplanFragment;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragementView = inflater.inflate(
                R.layout.fragment_finished_workplan,
                container,
                false
        );

        TextView txtSectionLabel = fragementView.findViewById(R.id.section_label);
        RecyclerView listSoldier = fragementView.findViewById(R.id.listSoldier);
        RecyclerView listTasks = fragementView.findViewById(R.id.listTasks);

        txtSectionLabel.setText(String.format(Locale.ENGLISH, "Gruppe: %d", groupNumber));

        listTasks.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        listSoldier.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        listTasks.setAdapter(new TaskListAdapter(tasks));


        return fragementView;
    }
}
