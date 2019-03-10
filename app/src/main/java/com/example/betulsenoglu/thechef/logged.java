package com.example.betulsenoglu.thechef;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class logged extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    ListView list1;

    LinearLayout tarifler;
    LayoutInflater inflater;
    private RequestQueue sira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        inflater = getLayoutInflater();
        sira = Volley.newRequestQueue(getApplicationContext());

        tarifler = findViewById(R.id.tariflerLL);

        dl = (DrawerLayout) findViewById(R.id.drawerlayout);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);



        list1=(ListView)findViewById(R.id.list1);

        tarifleriGetir();


    }




    public void yeniSatir(final String isim, final String foto, final String aciklama){
        View myLayout = inflater.inflate(R.layout.tarif_satir, tarifler, false);
        TextView isimtv = myLayout.findViewById(R.id.tarifText);
        isimtv.setText(isim);
        Button button = myLayout.findViewById(R.id.tarifButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(logged.this,tarifDetay.class);
                a.putExtra("baslik",isim);
                a.putExtra("aciklama",aciklama);
                a.putExtra("image",foto);
                startActivity(a);
            }
        });


        ImageView ivBasicImage = (ImageView) myLayout.findViewById(R.id.tarifImage);
        Picasso.with(getApplicationContext()).load(foto).into(ivBasicImage);
        tarifler.addView(myLayout);
    }




public void tarifleriGetir(){
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    final SharedPreferences.Editor editor = preferences.edit();
    final ProgressDialog dialog = ProgressDialog.show(this, "",
            "Lütfen Bekleyin...", false);
    StringRequest istek = new StringRequest(Request.Method.POST, "https://blackside.network/thechef/api.php", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                Log.d("API RESPONSE", response);
                JSONObject json = null;
                try {
                    json = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String durumKodu = json.getString("status");
                if (durumKodu.equals("101")){
                    JSONArray tarifs = json.getJSONArray("tarifler");
                    for(int i = 0; i < tarifs.length(); i++){
                        JSONObject tarif = tarifs.getJSONObject(i);
                        yeniSatir(tarif.getString("name"),tarif.getString("image"),tarif.getString("description"));
                    }

                } else {
                    Toast.makeText(getApplicationContext(),"Failed!",Toast.LENGTH_LONG);
                }
                dialog.hide();
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("FAIL", e.toString());
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> degerler = new HashMap<>();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(logged.this);
            String token = preferences.getString("TOKEN", "N/A");
            String username = preferences.getString("USERNAME", "N/A");
            degerler.put("param", "505");
            degerler.put("username", username);
            degerler.put("token", token);
            return degerler;
        }
    };

    istek.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 5, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    sira.add(istek);
}

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


     if (id == R.id.recipes) {
            Intent i = new Intent(logged.this, logged.class);
            startActivity(i);
        }
        else if (id == R.id.logout) {
         AlertDialog.Builder builder=new AlertDialog.Builder(logged.this);
         builder.setTitle("LOG OUT ALERT");
         builder.setIcon(R.drawable.logo_chef);
         builder.setMessage("Are you sure you want to log out?")
                 .setCancelable(false)
                 .setPositiveButton("Log Out", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         Intent i = new Intent(logged.this, MainActivity.class);
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
