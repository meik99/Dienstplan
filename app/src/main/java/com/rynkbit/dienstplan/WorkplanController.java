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

    public WorkplanController(Workplan workplan) {
        listWorkplan = (RecyclerView) workplan.findViewById(R.id.listWorkplan);
        fabWorkplan = (FloatingActionButton) workplan.findViewById(R.id.fabWorkplan);

        listWorkplan.setLayoutManager(new LinearLayoutManager(workplan, LinearLayoutManager.HORIZONTAL, false));
        listWorkplan.setAdapter(new WorkplanAdapter(workplan));

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

    }
}
