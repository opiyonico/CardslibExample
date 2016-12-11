package com.firebase.cardslib;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import adapters.CafeteriaAdapter;
import models.Cafeteria;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CafeteriaAdapter cafeteriaAdapter;
    DatabaseReference rootReference;
    ArrayList<Cafeteria> cafeteriaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cafeteriaArrayList = new ArrayList<>();

        rootReference = FirebaseDatabase.getInstance().getReference().child("cafeterias");
        rootReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){

                    Cafeteria cafeteria = singleSnapshot.getValue(Cafeteria.class);

                    cafeteriaArrayList.add(cafeteria);

                    Log.e("arraylist size",String.valueOf(cafeteriaArrayList.size()));

                }

                cafeteriaAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Database Error", databaseError.toString());
            }
        });

        cafeteriaAdapter = new CafeteriaAdapter(cafeteriaArrayList,MainActivity.this);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(cafeteriaAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Going to next Recycler View Type", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                Intent startSecond = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(startSecond);
            }
        });

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
}
