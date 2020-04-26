package com.example.COVID;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.COVID.Vue.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;
    @Before
    public void setUp() throws Exception
    {
        mActivity = mActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch()
    {
        View v = mActivity.findViewById(R.id.recycler_view);
        assertNotNull(v);
    }
    @After
    public void tearDown() throws Exception
    {
        mActivity = null;
    }
}