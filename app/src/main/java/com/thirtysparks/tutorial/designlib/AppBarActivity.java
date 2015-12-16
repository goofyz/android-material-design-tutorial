package com.thirtysparks.tutorial.designlib;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;


public class AppBarActivity extends AppCompatActivity {
    private List<Contact> mContacts;
    private ContactsAdapter adapter;
    private RecyclerView rvContacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mContacts = Contact.generateSampleList();

        RecyclerView.LayoutManager layoutManager;
        //layoutManager = new GridLayoutManager(this, 3);
        layoutManager = new LinearLayoutManager(this);

        rvContacts = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new ContactsAdapter(mContacts);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        rvContacts.addItemDecoration(itemDecoration);

        initFab();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_add:
                addContacts();
                return true;
            case R.id.action_remove:
                removeContacts();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initFab(){
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rvContacts, "I am snackbar", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new AlertDialog.Builder(AppBarActivity.this)
                                        .setMessage("Undo pressed")
                                        .setNeutralButton("OK", null)
                                        .show();
                            }
                        })
                        .show();
            }
        });
    }

    private void addContacts(){
        Contact contact = new Contact();
        contact.setName("Frodo Baggins");

        mContacts.add(1, contact);
        adapter.notifyItemInserted(1);
    }

    private void removeContacts(){
        mContacts.remove(mContacts.size() - 1);
        adapter.notifyItemRemoved(mContacts.size());
    }
}
