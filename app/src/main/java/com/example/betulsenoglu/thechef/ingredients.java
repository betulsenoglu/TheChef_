package com.example.betulsenoglu.thechef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ingredients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_start);
    }


    public void cbtnlicked(View v){

        CheckBox butter = (CheckBox) findViewById(R.id.check1);
        CheckBox eggs = (CheckBox) findViewById(R.id.check2);
        CheckBox milk = (CheckBox) findViewById(R.id.check3);
        CheckBox cheddar = (CheckBox) findViewById(R.id.check4);
        CheckBox parmesan = (CheckBox) findViewById(R.id.check5);
        CheckBox flour = (CheckBox) findViewById(R.id.check6);
        CheckBox pasta = (CheckBox) findViewById(R.id.check7);
        CheckBox swiss = (CheckBox) findViewById(R.id.check8);
        CheckBox cream = (CheckBox) findViewById(R.id.check9);
        CheckBox garlic = (CheckBox) findViewById(R.id.check10);
        CheckBox onion = (CheckBox) findViewById(R.id.check11);
        CheckBox lentil = (CheckBox) findViewById(R.id.check12);
        CheckBox parsley = (CheckBox) findViewById(R.id.check13);
        CheckBox potato = (CheckBox) findViewById(R.id.check14);
        CheckBox lettuce = (CheckBox) findViewById(R.id.check15);
        CheckBox paste = (CheckBox) findViewById(R.id.check16);
        CheckBox tomato = (CheckBox) findViewById(R.id.check17);
        CheckBox olive = (CheckBox) findViewById(R.id.check18);
        CheckBox carrots = (CheckBox) findViewById(R.id.check19);
        CheckBox broccoli = (CheckBox) findViewById(R.id.check20);
        CheckBox salads = (CheckBox) findViewById(R.id.check21);
        CheckBox sausage = (CheckBox) findViewById(R.id.check22);
        CheckBox beafsteak = (CheckBox) findViewById(R.id.check23);
        CheckBox bacon = (CheckBox) findViewById(R.id.check24);
        CheckBox beef = (CheckBox) findViewById(R.id.check25);
        CheckBox cornbeef = (CheckBox) findViewById(R.id.check26);
        CheckBox chicbroth = (CheckBox) findViewById(R.id.check27);


        Map<String, String> degerler = new HashMap<String,String>();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ingredients.this);
        String token = preferences.getString("TOKEN", "N/A");
        String username = preferences.getString("USERNAME", "N/A");
        degerler.put("param", "506");
        degerler.put("username", username);
        degerler.put("token", token);
        int butterInt = butter.isChecked() ? 1 : 0;
        if (butterInt == 1){
            degerler.put("butter",String.valueOf(butterInt));
        }

        int eggsInt = eggs.isChecked() ? 1 : 0;
        if (eggsInt == 1){
            degerler.put("eggs",String.valueOf(eggsInt));
        }

        int milkInt = milk.isChecked() ? 1 : 0;
        if (milkInt == 1){
            degerler.put("milk",String.valueOf(milkInt));
        }

        int cheddarInt = cheddar.isChecked() ? 1 : 0;
        if (cheddarInt == 1){
            degerler.put("cheddar",String.valueOf(cheddarInt));
        }

        int parmesanInt = parmesan.isChecked() ? 1 : 0;
        if (parmesanInt == 1){
            degerler.put("parmesan",String.valueOf(parmesanInt));
        }

        int flourInt = flour.isChecked() ? 1 : 0;
        if (flourInt == 1){
            degerler.put("flour",String.valueOf(flourInt));
        }

        int pastaInt = pasta.isChecked() ? 1 : 0;
        if (pastaInt == 1){
            degerler.put("pasta",String.valueOf(pastaInt));
        }

        int swissInt = swiss.isChecked() ? 1 : 0;
        if (swissInt == 1){
            degerler.put("swiss",String.valueOf(swissInt));
        }

        int creamInt = cream.isChecked() ? 1 : 0;
        if (creamInt == 1){
            degerler.put("cream",String.valueOf(creamInt));
        }

        int garlicInt = garlic.isChecked() ? 1 : 0;
        if (garlicInt == 1){
            degerler.put("garlic",String.valueOf(garlicInt));
        }

        int onionInt = onion.isChecked() ? 1 : 0;
        if (onionInt == 1){
            degerler.put("onion",String.valueOf(onionInt));
        }

        int lentilInt = lentil.isChecked() ? 1 : 0;
        if (lentilInt == 1){
            degerler.put("lentil",String.valueOf(lentilInt));
        }

        int parsleyInt = parsley.isChecked() ? 1 : 0;
        if (parsleyInt == 1){
            degerler.put("parsley",String.valueOf(parsleyInt));
        }

        int potatoInt = potato.isChecked() ? 1 : 0;
        if (potatoInt == 1){
            degerler.put("potato",String.valueOf(potatoInt));
        }

        int lettuceInt = lettuce.isChecked() ? 1 : 0;
        if (lettuceInt == 1){
            degerler.put("lettuce",String.valueOf(lettuceInt));
        }

        int pasteInt = paste.isChecked() ? 1 : 0;
        if (pasteInt == 1){
            degerler.put("paste",String.valueOf(pasteInt));
        }

        int tomatoInt = tomato.isChecked() ? 1 : 0;
        if (tomatoInt == 1){
            degerler.put("tomato",String.valueOf(tomatoInt));
        }

        int oliveInt = olive.isChecked() ? 1 : 0;
        if (oliveInt == 1){
            degerler.put("olive",String.valueOf(oliveInt));
        }

        int carrotsInt = carrots.isChecked() ? 1 : 0;
        if (carrotsInt == 1){
            degerler.put("carrots",String.valueOf(carrotsInt));
        }

        int broccoliInt = broccoli.isChecked() ? 1 : 0;
        if (broccoliInt == 1){
            degerler.put("broccoli",String.valueOf(broccoliInt));
        }

        int saladsInt = salads.isChecked() ? 1 : 0;
        if (saladsInt == 1){
            degerler.put("salads",String.valueOf(saladsInt));
        }

        int sausageInt = sausage.isChecked() ? 1 : 0;
        if (sausageInt == 1){
            degerler.put("sausage",String.valueOf(sausageInt));
        }

        int beafsteakInt = beafsteak.isChecked() ? 1 : 0;
        if (beafsteakInt == 1){
            degerler.put("beafsteak",String.valueOf(beafsteakInt));
        }

        int baconInt = bacon.isChecked() ? 1 : 0;
        if (baconInt == 1){
            degerler.put("bacon",String.valueOf(baconInt));
        }

        int beefInt = beef.isChecked() ? 1 : 0;
        if (beefInt == 1){
            degerler.put("beef",String.valueOf(beefInt));
        }

        int cornbeefInt = cornbeef.isChecked() ? 1 : 0;
        if (cornbeefInt == 1){
            degerler.put("cornbeef",String.valueOf(cornbeefInt));
        }

        int chicbrothInt = chicbroth.isChecked() ? 1 : 0;
        if (chicbrothInt == 1){
            degerler.put("chicbroth",String.valueOf(chicbrothInt));
        }

        Intent a = new Intent(ingredients.this,sonuc.class);
        a.putExtra("map", (Serializable) degerler);
        startActivity(a);

    }
}
