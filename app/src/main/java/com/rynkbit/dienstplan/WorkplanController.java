package com.rynkbit.dienstplan;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rynkbit.dienstplan.workplan.group.CreateGroupsActivity;


/**
 * Created by michael on 11/12/17.
 */

class WorkplanController implements Controller {
    private final RecyclerView listWorkplan;
    private final FloatingActionButton fabWorkplan;
    private final WorkplanAdapter workplanAdapter;

    public WorkplanController(Workplan workplan) {
        listWorkplan = (RecyclerView) workplan.findViewById(R.id.listWorkplan);
        fabWorkplan = (FloatingActionButton) workplan.findViewById(R.id.fabWorkplan);
        workplanAdapter = new WorkplanAdapter(workplan);

        listWorkplan.setLayoutManager(new LinearLayoutManager(workplan, LinearLayoutManager.VERTICAL, false));
        listWorkplan.setAdapter(workplanAdapter);

        fabWorkplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateGroupsActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }



    @Override
    public void update() {
        workplanAdapter.notifyDataSetChanged();
    }
}
