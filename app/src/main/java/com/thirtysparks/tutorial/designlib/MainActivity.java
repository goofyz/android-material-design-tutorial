package com.thirtysparks.tutorial.designlib;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String NAV_ITEM_ID = "nav_index";

    DrawerLayout drawerLayout;
    TextView contentView;

    private int navItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawerLayout();
        initNavView(savedInstanceState);
        initFab();
        initEditText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initDrawerLayout(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contentView = (TextView) findViewById(R.id.content_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void initNavView(Bundle savedInstanceState){
        NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Snackbar.make(contentView, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                navigateTo(menuItem);

                drawerLayout.closeDrawers();
                return true;
            }
        });

        if(null != savedInstanceState){
            navItemId = savedInstanceState.getInt(NAV_ITEM_ID, R.id.navigation_item_1);
        }
        else{
            navItemId = R.id.navigation_item_1;
        }

        navigateTo(view.getMenu().findItem(navItemId));
    }

    private void initFab(){
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(contentView, "I am snackbar", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new AlertDialog.Builder(MainActivity.this)
                                        .setMessage("Undo pressed")
                                        .setNeutralButton("OK", null)
                                        .show();
                            }
                        })
                        .show();
            }
        });
    }

    private void navigateTo(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.navigation_item_2:
                startActivity(new Intent(this, AnotherFabActivity.class));
                break;
            case R.id.navigation_item_3:
                startActivity(new Intent(this, AppBarActivity.class));
                break;
            default:
                contentView.setText(menuItem.getTitle());

                navItemId = menuItem.getItemId();
                menuItem.setChecked(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_ID, navItemId);
    }

    private void initEditText(){
        final TextInputLayout nameLayout = (TextInputLayout)findViewById(R.id.til_et_name);
        final TextInputLayout messageLayout = (TextInputLayout)findViewById(R.id.til_et_message);
        final EditText nameEditText = (EditText)findViewById(R.id.et_name);
        final EditText messageEditText = (EditText)findViewById(R.id.et_message);
        final Button button = (Button) findViewById(R.id.btn_submit);

        nameLayout.setErrorEnabled(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameEditText.length() == 0){
                    nameLayout.setError("Error in name input");
                }
                else{
                    nameLayout.setError(null);
                }
            }
        });
    }
}
