package com.example.betulsenoglu.thechef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class food_activity extends AppCompatActivity {

    int pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_activity);


        Intent intent=getIntent();
        pos= intent.getExtras().getInt("Position");

        final CustomAdapter adapter= new CustomAdapter(this);
        final ImageView img=(ImageView)findViewById(R.id.imageView);
        final TextView name=(TextView)findViewById(R.id.textname);
        final TextView ing=(TextView)findViewById(R.id.texting);
        final TextView recipe=(TextView)findViewById(R.id.textrecipe);

        img.setImageResource(adapter.images[pos]);
        name.setText(adapter.names[pos]);
        ing.setText("  Ingredients: "+adapter.ingredients[pos]);
        recipe.setText("  Recipe: "+adapter.recipes[pos]);

        Button btnnext=(Button)findViewById(R.id.btn1);
        Button btnprevious=(Button)findViewById(R.id.btn2);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position=pos+1;
                img.setImageResource(adapter.images[position]);
                name.setText(adapter.names[position]);
                ing.setText("  Ingredients: "+adapter.ingredients[position]);
                recipe.setText("  Recipe: "+adapter.recipes[position]);

                if(!(position>= adapter.getCount()-1)){
                    pos+=1;

                }else
                {pos=-1;
                 }

            }
        });

        btnprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(food_activity.this, logged.class));

            }
        });

    }
}
