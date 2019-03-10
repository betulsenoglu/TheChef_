package com.example.betulsenoglu.thechef;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper helper = new DatabaseHelper(this);

    RequestQueue sira;
    @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = (Button) (findViewById(R.id.login));
        Button toRegister = (Button) (findViewById(R.id.toregister));

        sira            = Volley.newRequestQueue(getApplicationContext());
        login.setOnClickListener(this);
        toRegister.setOnClickListener(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        Boolean session = preferences.getBoolean("session", false);
      /*  if (session){
            startActivity(new Intent(MainActivity.this, hellopage.class));
        }*/

        }
        public void onClick(View v) {
            if(v.getId()==R.id.login){
                EditText a = (EditText) findViewById(R.id.editUser);
                final String username = a.getText().toString();
                EditText b = (EditText) findViewById(R.id.editPassword);
                final String pass = b.getText().toString();



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
                                Toast.makeText(getApplicationContext(),"Success!",Toast.LENGTH_LONG).show();
                                String token = json.getString("token");
                                editor.putString("USERNAME",username);
                                editor.putString("TOKEN",token);
                                editor.putBoolean("session",true);
                                editor.apply();
                                startActivity(new Intent(MainActivity.this, hellopage.class));
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
                        degerler.put("param", "503");
                        degerler.put("username", username);
                        degerler.put("pass", pass);
                        return degerler;
                    }
                };

                istek.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 5, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                sira.add(istek);


            } else {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        }
    }

