package com.creditsapp.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.creditsapp.MyActivity;
import com.creditsapp.R;

public abstract class ActionBarMenuActivity extends Activity implements ListView.OnItemClickListener {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] menuText;
    private final String LOG_TAG = "ActionBarMenuActivity";
    public int clickOnMenuButton = 0;
    public final static int MAIN_BUTTON = 0;
    public final static int MY_CLIENTS_BUTTON = 1;
    public final static int MY_CREDITS_BUTTON = 2;
    View frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "go");
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "start");
        setContentView(R.layout.activity_action_bar_menu_main);
        Log.d(LOG_TAG, "1");
        FrameLayout frameLayout1 = (FrameLayout) findViewById(R.id.contentFrame);
        Log.d(LOG_TAG, "2");
        frameLayout1.addView(getContent());
         Log.d(LOG_TAG, "first");
        frameLayout = findViewById(R.id.contentFrame);
        menuText = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerList = (ListView) findViewById(R.id.rightDrawer);
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.requestDisallowInterceptTouchEvent(false);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.activity_action_bar_menu_drawer_list_item, menuText));
        Log.d(LOG_TAG, "second");
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        //switch-off tuch in drawerLayout
       mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
       // getActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d(LOG_TAG, "9");
         ActionBar actionBar = getActionBar();
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
        actionBar.setCustomView(R.layout.actionbar_custom_view_home);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setIcon(R.drawable.arrow_left);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Drawable d=getResources().getDrawable(R.drawable.yellow_menu_big);
        actionBar.setBackgroundDrawable(d);
        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    protected abstract View getContent();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(LOG_TAG, "3");
        getMenuInflater().inflate(R.layout.main, menu);
        return true;
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d(LOG_TAG, "4");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        clickOnMenuButton++;
        Log.d(LOG_TAG, "Worked");
        Log.d(LOG_TAG, "group id "+item.getGroupId());
        Log.d(LOG_TAG, "item id "+item.getItemId());
              if(item.getItemId() == R.id.actionMenu){
        if(clickOnMenuButton == 1){
            mDrawerLayout.openDrawer(Gravity.RIGHT);
        }

        if(clickOnMenuButton == 2){
            mDrawerLayout.closeDrawer(Gravity.RIGHT);
            clickOnMenuButton = 0;
        }

        if (item != null && item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                Log.d(LOG_TAG, "if");

            } else {
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                Log.d(LOG_TAG, "else");
            }
        }
    }
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return false;
    }

    private void selectItem(int position) {
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
        Log.d(LOG_TAG, "posit " + position);
        Log.d(LOG_TAG, "5");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        //  mDrawerToggle.syncState();
        Log.d(LOG_TAG, "6");
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
        Log.d(LOG_TAG,"7");
        Log.d(LOG_TAG,"position"+ position);
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);

            Log.d(LOG_TAG,"position"+ position);
            switch (position){
                case MAIN_BUTTON:
                    Intent mainIntent = new Intent(ActionBarMenuActivity.this, MyActivity.class);
                    startActivity(mainIntent);
                    break;
                case MY_CLIENTS_BUTTON:
                    Intent registrationIntent = new Intent(ActionBarMenuActivity.this, AuthorizationActivity.class);
                    startActivity(registrationIntent);
                    break;
            }
        }
    }
}