package com.rynkbit.dienstplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class Workplan extends AppCompatActivity {

    private FrameLayout mContent;
    private Controller mController;

    private View mView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_soldier:
                    mController = new SoldierController(Workplan.this);
                    return true;
                case R.id.navigation_workplan:
                    mController = new WorkplanController(Workplan.this);
                    return true;
                case R.id.navigation_posts:
                    mController = new PostController(Workplan.this);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplan);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        mView =  Workplan.this.getLayoutInflater().inflate(R.layout.content_workplan, null);
        mContent = (FrameLayout) findViewById(R.id.content);

        mContent.removeAllViewsInLayout();
        mContent.addView(mView);

        mController = new WorkplanController(this);

        navigation.setSelectedItemId(R.id.navigation_workplan);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mController.update();
    }
}
