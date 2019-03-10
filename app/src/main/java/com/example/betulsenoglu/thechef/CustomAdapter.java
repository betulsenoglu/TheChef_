package com.example.betulsenoglu.thechef;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.betulsenoglu.thechef.R.id.list1;

/**
 * Created by betulsenoglu on 12/13/17.
 */

public class CustomAdapter extends BaseAdapter {

    Context c;
    String [] names={"CHEESE CRACKERS","GRITS","MAC'N CHEESE","POT ROAST","WILD ALASKA SALMON"};
    String [] ingredients={"1/2 cups whole-wheat flour,1/2 cup all-purpose flour,1/2 teaspoon salt,1 cup shredded Cheddar Pinch of cayenne pepper,Small pieces 1 large egg yolk",
            "1/4 Cup of Grits, Hot Water, Salt and Pepper to Taste",
            "10 ounces medium elbow macaroni, 2 tablespoons unsalted butter, plus more for brushing pan,2 1⁄4 cups grated extra-sharp cheddar cheese, divided.",
            "Salt and freshly ground black pepper,One 3- to 5-pound chuck roast,2 or 3 tablespoons olive oil, 3 cups beef broth",
            "2 sides of Alaskan salmon, each 3 1/2 pounds, Extra-virgin olive oil, Onion and Salt and Peppers to taste" };
    String [] recipes={"Pulse the flour, salt and pepper, then add butter and pulse until the mixture resembles coarse meal. Add grated cheese a little at a time until the mixture again resembles coarse meal.",
    "Bring the water and salt to a boil in a saucepan. Whisk in the grits and continue to whisk for 1 minute. When the mixture comes to a boil again, turn the heat to low. Remove the grits from the heat and add pepper, butter and cheese; mix well. Stir until the butter and cheese are melted.",
    "Cook macaroni according to the package directions. Drain. In a saucepan, melt butter or margarine over medium heat. Stir in enough flour to make a roux.",
    "Heat olive oil in a large Dutch oven over medium-high heat. Sprinkle chuck roast with salt and pepper. Add roast to pan; cook 5 minutes, turning to brown on all sides. Remove roast from pan. Add onion to pan; sauté 8 minutes or until tender.",
   "Combine all spices. Generously rub the spice mixture on the flesh side of the salmon and drizzle with a light coat of oil. Place the salmon on the grill, flesh side first. Let cook for about 7 to 10 minutes each side."
    };
    int [] images={R.drawable.crackers,R.drawable.grits, R.drawable.macncheese,R.drawable.potroast,R.drawable.salmon};



    public CustomAdapter(Context ctx){
        this.c=ctx;
    }
    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int pos) {
        return names[pos];
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.foods,null);
        }
        TextView txtname=(TextView)convertView.findViewById(R.id.textname);
        //TextView txting=(TextView)convertView.findViewById(R.id.texting);
        ImageView img=(ImageView)convertView.findViewById(R.id.imageView);

        txtname.setText(names[pos]);
//        txting.setText(ingredients[pos]);
        img.setImageResource(images[pos]);

        return convertView;
    }
}
