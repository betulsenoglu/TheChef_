package com.example.betulsenoglu.thechef;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class loggedStart extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Button btngo = (Button) (findViewById(R.id.btnGo));
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    Contact c=new Contact();
   // DatabaseHelper helper = new DatabaseHelper(this);
    CheckBox check1,check2,check3,check4,check5,check6,check7,check8,check9,check10,check11,check12,check13,check14,check15,check16,check17,check18,check19,check20,check21,check22,check23,check24,check25,check26,check27;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_start);
        dl = (DrawerLayout) findViewById(R.id.drawerlayout);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        Button btngo = (Button) (findViewById(R.id.btnGo));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }
    }
//
//    public void checks()
//    {
//        check1 = (CheckBox) findViewById(R.id.check1);
//        check2 = (CheckBox) findViewById(R.id.check2);
//        check3 = (CheckBox) findViewById(R.id.check3);
//        check4 = (CheckBox) findViewById(R.id.check4);
//        check5 = (CheckBox) findViewById(R.id.check5);
//        check6 = (CheckBox) findViewById(R.id.check6);
//        check7 = (CheckBox) findViewById(R.id.check7);
//        check8 = (CheckBox) findViewById(R.id.check8);
//        check9 = (CheckBox) findViewById(R.id.check9);
//        check10 = (CheckBox) findViewById(R.id.check10);
//        check11 = (CheckBox) findViewById(R.id.check11);
//        check12 = (CheckBox) findViewById(R.id.check12);
//        check13 = (CheckBox) findViewById(R.id.check13);
//        check14 = (CheckBox) findViewById(R.id.check14);
//        check15 = (CheckBox) findViewById(R.id.check15);
//        check16 = (CheckBox) findViewById(R.id.check16);
//        check17 = (CheckBox) findViewById(R.id.check17);
//        check18 = (CheckBox) findViewById(R.id.check18);
//        check19 = (CheckBox) findViewById(R.id.check19);
//        check20 = (CheckBox) findViewById(R.id.check20);
//        check21 = (CheckBox) findViewById(R.id.check21);
//        check22 = (CheckBox) findViewById(R.id.check22);
//        check23 = (CheckBox) findViewById(R.id.check23);
//        check24 = (CheckBox) findViewById(R.id.check24);
//        check25 = (CheckBox) findViewById(R.id.check25);
//        check26 = (CheckBox) findViewById(R.id.check26);
//        check27 = (CheckBox) findViewById(R.id.check27);
//        //btngo.setOnClickListener(btngolistener);
//    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



       if (id == R.id.recipes) {
            Intent i = new Intent(loggedStart.this, logged.class);
            startActivity(i);
        }
        return true;
    }



}

