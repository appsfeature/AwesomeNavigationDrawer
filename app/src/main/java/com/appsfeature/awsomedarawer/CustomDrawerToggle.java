package com.appsfeature.awsomedarawer;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


public class CustomDrawerToggle implements DrawerLayout.DrawerListener {

    private DrawerLayout drawerLayout;
    private DrawerArrowDrawable arrowDrawable;
    private ImageButton toggleButton;
    private String openDrawerContentDesc;
    private String closeDrawerContentDesc;

    public CustomDrawerToggle(Activity activity, ImageButton toggleButton, DrawerLayout drawerLayout, Toolbar toolbar,
                              int openDrawerContentDescRes, int closeDrawerContentDescRes) {

        this.toggleButton = toggleButton;
        this.drawerLayout = drawerLayout;
        this.openDrawerContentDesc = activity.getString(openDrawerContentDescRes);
        this.closeDrawerContentDesc = activity.getString(closeDrawerContentDescRes);

        arrowDrawable = new DrawerArrowDrawable(toolbar.getContext());
        arrowDrawable.setDirection(DrawerArrowDrawable.ARROW_DIRECTION_START);
        arrowDrawable.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);

        arrowDrawable.setBarLength(35f);

//        toggleButton = new AppCompatImageButton(toolbar.getContext(), null,
//                R.attr.toolbarNavigationButtonStyle);
//        toolbar.addView(toggleButton, new Toolbar.LayoutParams(GravityCompat.START));
        toggleButton.setImageDrawable(arrowDrawable);
        toggleButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }

    public void syncState() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            setPosition(1f);
        } else {
            setPosition(0f);
        }
    }

    public void toggle() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    public void setPosition(float position) {
        if (position == 1f) {
            arrowDrawable.setVerticalMirror(true);
            toggleButton.setContentDescription(closeDrawerContentDesc);
        } else if (position == 0f) {
            arrowDrawable.setVerticalMirror(false);
            toggleButton.setContentDescription(openDrawerContentDesc);
        }
        arrowDrawable.setProgress(position);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        setPosition(Math.min(1f, Math.max(0, slideOffset)));
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        setPosition(1f);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        setPosition(0f);
    }

    @Override
    public void onDrawerStateChanged(int newState) {
    }
}