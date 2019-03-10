package com.example.betulsenoglu.thechef;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaCodec;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper helper=new DatabaseHelper(this);
    RequestQueue sira;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = (Button) (findViewById(R.id.register));
        register.setOnClickListener((View.OnClickListener) this);
        sira            = Volley.newRequestQueue(getApplicationContext());
    }
    public void onClick(View v) {
        EditText username = (EditText) findViewById(R.id.editUser);
        EditText password = (EditText) findViewById(R.id.editPassword);
        EditText mail = (EditText) findViewById(R.id.editMail);



        final String usernamestr = username.getText().toString();
        final String mailstr = mail.getText().toString();
        final String passwordstr = password.getText().toString();


       if(!validateEmail(mailstr.toString()))
        {
            mail.setError("Invalid Email!");
            mail.requestFocus();
        }
        else {
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
                           Toast.makeText(getApplicationContext(),"Success!",Toast.LENGTH_LONG);
                           String token = json.getString("token");
                           editor.putString("USERNAME",usernamestr);
                           editor.putString("TOKEN",token);
                           editor.putBoolean("session",true);
                           editor.apply();
                           startActivity(new Intent(RegisterActivity.this, hellopage.class));

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
                   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(RegisterActivity.this);
                   String token = preferences.getString("TOKEN", "N/A");
                   String username = preferences.getString("USERNAME", "N/A");
                   degerler.put("param", "502");
                   degerler.put("username", usernamestr);
                   degerler.put("pass", passwordstr);
                   degerler.put("mail", mailstr);
                   return degerler;
               }
           };

           istek.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 5, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
           sira.add(istek);
       }

            //startActivity(new Intent(RegisterActivity.this, MainActivity.class));}

//    public void onButtonClick(View v){
//        if (v.getId()==R.id.register)
//        {
//            EditText username=(EditText)findViewById(R.id.editUser);
//            EditText password=(EditText)findViewById(R.id.editPassword);
//            EditText mail=(EditText)findViewById(R.id.editMail);
//
//            String usernamestr = username.getText().toString();
//            String mailstr = mail.getText().toString();
//            String passwordstr = password.getText().toString();
//
//            //insert
//            Contact c=new Contact();
//            c.setUserName(usernamestr);
//            c.setEmail(mailstr);
//            c.setPassword(passwordstr);
//
//            helper.insertContact(c);
//            startActivity(new Intent(RegisterActivity.this, logged.class));
//        }
//
//
//
//    }
}

        protected boolean validateEmail(String email){
        String emailPattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


        Pattern pattern=Pattern.compile(emailPattern);
                Matcher matcher=pattern.matcher(email);
                return matcher.matches();
    }


}

