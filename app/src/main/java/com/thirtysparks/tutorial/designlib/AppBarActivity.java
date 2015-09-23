package com.thirtysparks.tutorial.designlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class AppBarActivity extends AppCompatActivity {
    private List<Contact> mContacts;
    private ContactsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mContacts = Contact.generateSampleList();

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new ContactsAdapter(mContacts);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
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
