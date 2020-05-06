package com.example.COVID;

import android.view.View;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import com.example.COVID.Vue.DetailActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class DetailActivityTest {
    /*
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;
     */
    @Rule
    public ActivityTestRule<DetailActivity> mDetailsActivityTestRule = new ActivityTestRule<DetailActivity>(DetailActivity.class);
    private DetailActivity mDetailActivity = null;
    @Before
    public void setUp() throws Exception {
        //mActivity = mMainActivityTestRule.getActivity();
        mDetailActivity = mDetailsActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        View v = mDetailActivity.findViewById(R.id.imageView);
        assertNotNull(v);
        v = mDetailActivity.findViewById(R.id.newCase);
        assertNotNull(v);
        v = mDetailActivity.findViewById(R.id.totalCase);
        assertNotNull(v);
        v = mDetailActivity.findViewById(R.id.newDead);
        assertNotNull(v);
        v = mDetailActivity.findViewById(R.id.totalDead);
        assertNotNull(v);
        v = mDetailActivity.findViewById(R.id.newRecovered);
        assertNotNull(v);
        v = mDetailActivity.findViewById(R.id.totalRecovered);
        assertNotNull(v);

    }

    @After
    public void tearDown() throws Exception {
        mDetailActivity = null;
        //mActivity = null;
    }
}