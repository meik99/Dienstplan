package com.rynkbit.dienstplan.soldiers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.rynkbit.dienstplan.R;
import com.rynkbit.dienstplan.db.facade.SoldierFacade;
import com.rynkbit.dienstplan.entities.Soldier;
import com.rynkbit.dienstplan.entities.SoldierConnection;

public class SoldierActivity extends AppCompatActivity {
    private Soldier soldier;
    private SoldierFacade soldierFacade;
    private EditText txtName;
    private Switch switchWeak;
    private Switch switchIll;
    private TextView txtHours;
    private Button btnSoldierLikesPost;
    private Button btnCancelSoldier;
    private Button btnSoldierConnection;
    private Button btnSoldierSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soldier);

        soldierFacade = new SoldierFacade();

        if(this.getIntent().hasExtra("soldier")){
            soldier = soldierFacade.getById(this.getIntent().getLongExtra("soldier", -1l));
        }else{
            soldier = new Soldier();
        }

        txtName = (EditText) findViewById(R.id.txtName);
        switchWeak = (Switch) findViewById(R.id.switchWeak);
        switchIll = (Switch) findViewById(R.id.switchIll);
        txtHours = (TextView) findViewById(R.id.txtHourValue);
        btnSoldierLikesPost = (Button) findViewById(R.id.btnSoldierLikesPost);
        btnCancelSoldier = (Button) findViewById(R.id.btnCancelSoldier);
        btnSoldierConnection = (Button) findViewById(R.id.btnSoldierConnection);
        btnSoldierSave = (Button) findViewById(R.id.btnSaveSoldier);

        txtName.setText(soldier.getName());
        switchWeak.setChecked(soldier.isWeak());
        switchIll.setChecked(soldier.isIll());
        txtHours.setText(String.valueOf(soldier.getWorkingHours()));

        btnSoldierLikesPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    soldierFacade.update(soldier);
                    Intent intent = new Intent(SoldierActivity.this, LikesPostActivity.class);
                    intent.putExtra("soldier", soldier.getId());
                    view.getContext().startActivity(intent);
                }
        });
        btnCancelSoldier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoldierActivity.this.finish();
            }
        });
        btnSoldierSave.setOnClickListener(getSoldierSaveListener());

        switchIll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switchWeak.setChecked(!b);
            }
        });
        switchWeak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switchIll.setChecked(!b);
            }
        });
        btnSoldierConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soldierFacade.update(soldier);
                Intent intent = new Intent(SoldierActivity.this, SoldierConnectionActivity.class);
                intent.putExtra("soldier", soldier.getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    private View.OnClickListener getSoldierSaveListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtName.getText().toString().isEmpty() == false){
                    String name = txtName.getText().toString();
                    boolean isWeak = switchWeak.isChecked();
                    boolean isIll = switchIll.isChecked();

                    soldier.setName(name);
                    soldier.setWeak(isWeak);
                    soldier.setIll(isIll);

                    soldierFacade.update(soldier);
                    SoldierActivity.this.finish();
                }else{
                    txtName.setError("Name eingeben");
                }
            }
        };
    }
}
