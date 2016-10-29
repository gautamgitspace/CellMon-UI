package edu.buffalo.cse.ubwins.cellmon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by Gautam on 10/1/16.
 * MBP111.0138.B16
 * agautam2@buffalo.edu
 * University at Buffalo, The State University of New York.
 * Copyright © 2016 Gautam. All rights reserved.
 */

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private NavigationView navigationView;
    private DrawerLayout fullLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private int selectedNavItemId;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        /*ROOT LAYOUT*/
        fullLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        /**
         * {@link FrameLayout} to inflate the child's view. We could also use a {@link android.view.ViewStub}
         */
        FrameLayout activityContainer = (FrameLayout) fullLayout.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        /**
         * Note that we don't pass the child's layoutId to the parent,
         * instead we pass it our inflated layout.
         */
        super.setContentView(fullLayout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        if (useToolbar())
        {
            setSupportActionBar(toolbar);
        }
        else
        {
            toolbar.setVisibility(View.GONE);
        }

        setUpNavView();
    }

    protected boolean useToolbar()
    {
        return true;
    }

    protected void setUpNavView()
    {
        navigationView.setNavigationItemSelectedListener(this);

        if( useDrawerToggle())
        {
            drawerToggle = new ActionBarDrawerToggle(this, fullLayout, toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close);

            fullLayout.setDrawerListener(drawerToggle);
            drawerToggle.syncState();
        }
        else if(useToolbar() && getSupportActionBar() != null)
        {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(getResources()
                    .getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        }
    }

    protected boolean useDrawerToggle()
    {
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem)
    {
        fullLayout.closeDrawer(GravityCompat.START);
        selectedNavItemId = menuItem.getItemId();

        return onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.nav_about :
                Log.e("HERE", "HANDLE TWO");
                startActivity(new Intent(this, HandleTwo.class));
                return true;
            case R.id.nav_home :
                Log.e("HERE", "MAIN ACTIVITY");
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.nav_statistics :
                Log.e("HERE", "STATS ACTIVITY");
                startActivity(new Intent(this, PieChartActivity.class));
                return true;
            case R.id.nav_map_view :
                startActivity(new Intent(this, DatePickerFragment.class));
                return true;


        }

        return super.onOptionsItemSelected(item);
    }
}
