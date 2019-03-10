package com.example.betulsenoglu.thechef;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class hellopage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hellopage);

        dl = (DrawerLayout) findViewById(R.id.drawerlayout);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }
        TextView txthello=(TextView) findViewById(R.id.txthello);

        txthello.setText("Thank you for your support!" +
                "Explore recipes!" +"\n" +
                "" +
                "Need some help deciding what to choose? " +"\n" +
                "" +
                "Take a look at our most popular recipes or check out the latest dishes we’ve added to the site."+"\n" +
                "" +"\n"+
                "ENJOY YOUR MEAL!");
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.recipes) {
            Intent i = new Intent(hellopage.this, logged.class);
            startActivity(i);
        } else if (id == R.id.ingredients) {
            Intent i = new Intent(hellopage.this, ingredients.class);
            startActivity(i);
        }
        else if (id == R.id.logout) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            final SharedPreferences.Editor editor = preferences.edit();
            AlertDialog.Builder builder=new AlertDialog.Builder(hellopage.this);
            builder.setTitle("LOG OUT ALERT");
            builder.setIcon(R.drawable.logo_chef);

            builder.setMessage("Are you sure you want to log out?")
                    .setCancelable(false)
                    .setPositiveButton("Log Out", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editor.putBoolean("session",false);
                            editor.apply();
                            Intent i = new Intent(hellopage.this, MainActivity.class);
                            startActivity(i);
                        }

                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert=builder.create();
            alert.show();



//         Intent i = new Intent(logged.this, Pop.class);
//         startActivity(i);
        }
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        if (abdt.onOptionsItemSelected(item)) {

            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
