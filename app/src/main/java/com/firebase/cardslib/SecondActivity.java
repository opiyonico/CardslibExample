package com.firebase.cardslib;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import models.Cafeteria;

public class SecondActivity extends AppCompatActivity {

    DatabaseReference rootReference;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rootReference = FirebaseDatabase.getInstance().getReference().child("cafeterias");

        RecyclerView recycler = (RecyclerView) findViewById(R.id.second_recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Cafeteria,CafeteriaHolder>(Cafeteria.class,R.layout.cafeteria_card,CafeteriaHolder.class,rootReference) {

            @Override
            protected void populateViewHolder(CafeteriaHolder viewHolder, Cafeteria model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setCafeteriaEmail(model.getEmail());
                viewHolder.setCafeteriaPhone(model.getPhone());
                viewHolder.setCafeteriaImage(model.getImage());
            }
        };

        recycler.setAdapter(firebaseRecyclerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent startSecond = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(startSecond);
            }
        });
    }

    public static class CafeteriaHolder extends RecyclerView.ViewHolder {
        private TextView cafeteriaName, cafeteriaPhone, cafeteriaEmail;
        private ImageView cafeteriaImage;
        View mainView;

        public CafeteriaHolder(View view) {
            super(view);
            this.mainView = view;
            cafeteriaName = (TextView) view.findViewById(R.id.cafeteriaName);
            cafeteriaEmail = (TextView) view.findViewById(R.id.cafeteriaEmail);
            cafeteriaPhone = (TextView) view.findViewById(R.id.cafeteriaPhone);
            cafeteriaImage = (ImageView) view.findViewById(R.id.cafeteriaImage);
        }

        public void setName(String name) {
            cafeteriaName.setText(name);
        }

        public void setCafeteriaEmail(String email) {
            cafeteriaEmail.setText(email);
        }

        public void setCafeteriaPhone(String phone){
            cafeteriaEmail.setText(phone);
        }

        public void setCafeteriaImage(String image){
            Glide.with(mainView.getContext()).load(image).into(cafeteriaImage);
        }
    }

}
