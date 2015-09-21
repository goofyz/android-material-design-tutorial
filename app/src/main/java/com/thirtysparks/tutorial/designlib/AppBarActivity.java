package com.thirtysparks.tutorial.designlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class AppBarActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar);

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.recyclerView);
        ContactsAdapter adapter = new ContactsAdapter(Contact.generateSampleList());
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}
