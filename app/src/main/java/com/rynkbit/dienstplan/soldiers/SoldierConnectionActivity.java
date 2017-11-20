package com.rynkbit.dienstplan.soldiers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.rynkbit.dienstplan.R;
import com.rynkbit.dienstplan.db.facade.SoldierFacade;
import com.rynkbit.dienstplan.entities.Soldier;

public class SoldierConnectionActivity extends AppCompatActivity {

    private ListView listSoldierConnections;
    private Button btnSaveSoldierConnection;
    private Button btnCancelSoldierConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soldier_connection);

        listSoldierConnections = (ListView) findViewById(R.id.listSodierConnection);
        btnSaveSoldierConnection = (Button) findViewById(R.id.btnSoldierConnectionSave);
        btnCancelSoldierConnection = (Button) findViewById(R.id.btnSoldierConnectionCancel);

        listSoldierConnections.setAdapter(new BaseAdapter() {
            SoldierFacade facade = new SoldierFacade(SoldierConnectionActivity.this);

            @Override
            public int getCount() {
                return facade.getAll().size();
            }

            @Override
            public Object getItem(int i) {
                return facade.getAll().get(i);
            }

            @Override
            public long getItemId(int i) {
                return facade.getAll().get(i).getId();
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                View v = LayoutInflater.from(SoldierConnectionActivity.this)
                        .inflate(R.layout.item_likes_post, viewGroup, false);
                TextView txtLikesPos = v.findViewById(R.id.txtPostName);
                Spinner spinnerLikesPost = v.findViewById(R.id.spinnerLikesPost);
                Soldier soldier = facade.getAll().get(i);



                return null;
            }
        });
    }
}
