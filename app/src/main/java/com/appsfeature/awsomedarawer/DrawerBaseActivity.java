package com.appsfeature.awsomedarawer;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setDrawerLayout();
    }

    protected void setDrawerLayout() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageButton ivToggle = toolbar.findViewById(R.id.iv_toggle);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final LinearLayout holder = findViewById(R.id.holder);
        CustomDrawerToggle toggle = new CustomDrawerToggle(
                this, ivToggle, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                float scaleFactor = 4f;
                float slideX = drawerView.getWidth() * slideOffset;

                holder.setTranslationX(slideX);
                holder.setScaleX(1 - (slideOffset / scaleFactor));
                holder.setScaleY(1 - (slideOffset / scaleFactor));

                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);// will remove all possible our aactivity's window bounds
        }


        drawer.addDrawerListener(toggle);

        drawer.setScrimColor(Color.TRANSPARENT);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
