package com.rynkbit.dienstplan;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rynkbit.dienstplan.db.facade.SoldierFacade;
import com.rynkbit.dienstplan.entities.Soldier;
import com.rynkbit.dienstplan.soldiers.SoldierActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 11/12/17.
 */

public class SoldierAdapter extends RecyclerView.Adapter<SoldierAdapter.SoldierViewHolder> {
    private final Workplan workplan;
    private List<Soldier> soldiers;
    private SoldierFacade soldierFacade;

    public SoldierAdapter(Workplan workplan) {
        this.workplan = workplan;
        soldiers = new LinkedList<>();
        soldierFacade = new SoldierFacade();
        soldiers.addAll(soldierFacade.getAll());
    }

    public void update(){
        soldiers.clear();
        soldiers.addAll(soldierFacade.getAll());
        this.notifyDataSetChanged();
    }

    @Override
    public SoldierViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                android.R.layout.simple_list_item_1, parent, false
        );
        SoldierViewHolder viewHolder = new SoldierViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SoldierViewHolder holder, int position) {
        if(position >= 0 && position < soldiers.size()){
            final Soldier soldier = soldiers.get(position);
            holder.txtSoldierName.setText(soldier.getName());
            holder.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(
                                    holder.itemView.getContext(),
                                    SoldierActivity.class);
                            intent.putExtra("soldier", soldier.getId());
                            workplan.startActivityForResult(intent, 20);
                        }
                    }
            );
        }
    }

    @Override
    public int getItemCount() {
        return soldiers.size();
    }

    class SoldierViewHolder extends RecyclerView.ViewHolder{
        TextView txtSoldierName;

        public SoldierViewHolder(View itemView) {
            super(itemView);

            txtSoldierName = itemView.findViewById(android.R.id.text1);
        }
    }
}
