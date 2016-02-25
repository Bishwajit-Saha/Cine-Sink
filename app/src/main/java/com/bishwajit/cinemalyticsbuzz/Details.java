package com.bishwajit.cinemalyticsbuzz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    TextView name,genre1,ReleaseDate1,Runtime1,Rating1,Description;
    Button youtube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (TextView) findViewById(R.id.name);
        genre1 = (TextView) findViewById(R.id.genre1);
        ReleaseDate1 = (TextView) findViewById(R.id.ReleaseDate1);
        Runtime1 = (TextView) findViewById(R.id.Runtime1);
        Rating1 = (TextView) findViewById(R.id.Rating1);
        youtube = (Button) findViewById(R.id.youtube);
        Description = (TextView) findViewById(R.id.Description);


       /* getIntent().getStringExtra("Id");
        getIntent().getStringExtra("Description");
        getIntent().getStringExtra("Genre");
        getIntent().getStringExtra("Rating");
        getIntent().getStringExtra("ReleaseDate");
        getIntent().getStringExtra("Runtime");
        getIntent().getStringExtra("PosterPath");
        getIntent().getStringExtra("TrailerLink");
       */
        name.setText(getIntent().getStringExtra("OriginalTitle"));
        genre1.setText(getIntent().getStringExtra("Genre"));
        ReleaseDate1.setText(getIntent().getStringExtra("ReleaseDate"));
        Runtime1.setText(getIntent().getStringExtra("OriginalTitle"));
        Rating1.setText(getIntent().getStringExtra("Rating"));
        Description.setText(getIntent().getStringExtra("Description"));
        final String link = getIntent().getStringExtra("TrailerLink");

        youtube.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uriUrl = Uri.parse(link);
                        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                        startActivity(launchBrowser);
                    }
                }
        );


    }



}
