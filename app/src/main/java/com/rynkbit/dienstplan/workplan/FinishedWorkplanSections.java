package com.rynkbit.dienstplan.workplan;

import android.app.ActionBar;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.WindowDecorActionBar;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.TabHost;

import com.rynkbit.dienstplan.db.DBHelper;
import com.rynkbit.dienstplan.db.contract.Task;
import com.rynkbit.dienstplan.db.facade.SoldierFacade;
import com.rynkbit.dienstplan.entities.Soldier;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by michael on 12/14/17.
 */

public class FinishedWorkplanSections extends FragmentPagerAdapter{
    DBHelper dbHelper;
    List<Integer> groups;
    Dictionary<Integer, List<Soldier>> soldierGroup;
    Context context;

    public FinishedWorkplanSections(FinishedWorkplanActivity activity, FragmentManager fm) {
        super(fm);
        SQLiteDatabase database;
        Cursor cursor;
        SoldierFacade soldierFacade = new SoldierFacade(activity);
        List<Soldier> soldiers = soldierFacade.getAll();
        Soldier[] soldierArray = new Soldier[soldiers.size()];
        final Random random = new Random();

        soldierGroup = new Hashtable<>();
        dbHelper = new DBHelper(activity);
        groups = new LinkedList<>();
        this.context = activity;

        database = dbHelper.getReadableDatabase();
        cursor = database.rawQuery(
                "select distinct " + Task.Columns.GROUP + " from " + Task.TABLE +
                    " where " + Task.Columns.SHIFT_ID + " = ?",
                new String[]{String.valueOf(
                        FinishedWorkplanDataHolder.getInstance()
                        .getShift().getId()
                )}
        );

        if(cursor.moveToFirst()){
            do {
                int group = cursor.getInt(0);
                groups.add(
                        group
                );
                List<Soldier> groupSoldiers = new LinkedList<>();

                if(soldiers.size() >= 2){
                    Arrays.sort(soldierArray, new Comparator<Soldier>() {
                        @Override
                        public int compare(Soldier o1, Soldier o2) {
                            return random.nextInt(3) - 1;
                        }
                    });
                    groupSoldiers.add(
                            soldiers.remove(0)
                    );
                    groupSoldiers.add(
                            soldiers.remove(0)
                    );

                    soldierArray = new Soldier[soldiers.size()];
                    soldierArray = soldiers.toArray(soldierArray);
                }else if(soldiers.size() == 1){
                    groupSoldiers.add(soldiers.remove(0));
                }

                soldierGroup.put(group, groupSoldiers);
            }while(cursor.moveToNext());

            FinishedWorkplanDataHolder.getInstance().setSoldierPool(soldierGroup);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return FinishedWorkplanFragment.getInstance(context, groups.get(position));
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.format(Locale.ENGLISH, "Gruppe %d", groups.get(position));
    }

    public void populateTabLayout(TabLayout tabLayout) {
        LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        );
        for (int i = 0; i < groups.size(); i++){
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(getPageTitle(i));
            tabLayout.addTab(
                    tab
            );
        }
    }
}
