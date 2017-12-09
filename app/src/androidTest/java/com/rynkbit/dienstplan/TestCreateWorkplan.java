package com.rynkbit.dienstplan;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.ViewInteraction_Factory;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TimePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestCreateWorkplan {
    @Rule
    public ActivityTestRule<Workplan> workplanActivityTestRule =
            new ActivityTestRule<Workplan>(Workplan.class, true, true);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.rynkbit.dienstplan", appContext.getPackageName());
    }

    @Test
    public void addGroups() {
        onView(withId(R.id.fabWorkplan))
                .perform(click());

        addGroup();
        addGroup();

        onView(withId(R.id.btnSave))
                .perform(click());
    }

    private void addGroup(){
        onView(withId(R.id.fabAddGroup))
                .perform(click());

        addTask();
        addTask();

        onView(withId(R.id.btnSave))
                .perform(click());
    }

    private void addTask(){
        onView(withId(R.id.fabAddTask))
                .perform(click());
        onView(withId(R.id.txtTimeFrom))
                .perform(click());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(setTime(11, 00));
        onView(withId(android.R.id.button1))
                .perform(click());
        onView(withId(R.id.txtTimeTo))
                .perform(click())
                .perform(click());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(setTime(12, 00));
        onView(withId(android.R.id.button1))
                .perform(click());
        onView(withId(R.id.btnSave))
                .perform(click());
    }

    public static ViewAction setTime(final int hour, final int minute){
        return new ViewAction() {
            @Override
            public org.hamcrest.Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(TimePicker.class);
            }

            @Override
            public String getDescription() {
                return "Set time on TimePicker";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TimePicker tp = (TimePicker) view;
                tp.setCurrentHour(hour);
                tp.setCurrentMinute(minute);
            }
        };
    }
}
