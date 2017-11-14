package com.rynkbit.dienstplan;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rynkbit.dienstplan.soldiers.SoldierActivity;

/**
 * Created by michael on 11/12/17.
 */

public class SoldierController implements Controller{

    private final Workplan workplan;

    RecyclerView listSoldiers;
    FloatingActionButton fabAddSoldier;

    public SoldierController(final Workplan workplan) {
        this.workplan = workplan;

        listSoldiers = (RecyclerView) workplan.findViewById(R.id.listWorkplan);
        fabAddSoldier = (FloatingActionButton) workplan.findViewById(R.id.fabWorkplan);

        listSoldiers.setLayoutManager(new LinearLayoutManager(workplan, LinearLayoutManager.VERTICAL, false));
        listSoldiers.setAdapter(new SoldierAdapter(workplan));

        fabAddSoldier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(workplan, SoldierActivity.class);
                workplan.startActivityForResult(intent, 20);
            }
        });
    }

    @Override
    public void update() {
        ((SoldierAdapter)listSoldiers.getAdapter()).update();
    }
}
