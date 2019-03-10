package com.example.betulsenoglu.thechef;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by betulsenoglu on 12/2/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";


    private static final String TABLENAME = "recipes";
    public static final String COLUMNID = "id";
    public static final String COLUMNINGREDIENTS = "ingredients";
    public static final String COLUMNNAME = "name";
    public static final String COLUMNRECIPE = "recipe";


    int COLUMN_USERNAME_INDEX=1;
    int COLUMN_PASSWORD_INDEX=2;

    SQLiteDatabase db;


    //private static final String TABLE_CREATE2= "create table recipes(id integer primary key not null,recipename text not null,body text not null);";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE = "create table contacts (id integer primary key not null," +
                "username text not null," +
                "password text not null," +
                "email text not null);";
//        String TABLE_CREATE1 = "create table recipes (id integer primary key not null auto_increment," +
//                "ingredients text not null," +
//                "name text not null," +
//                "recipe text not null" +
//                "liked boolean);";
//        String RECIPE_SET = "insert into recipes (ingredients,name,recipe,) values (Eggs, Milk"+"Perfect Scramble Eggs"+"BEAT eggs, milk, salt and pepper in bowl until blended." +
//                "HEAT butter in large nonstick skillet over medium heat until hot. POUR IN egg mixture." +
//                "As eggs begin to set, GENTLY PULL the eggs across the pan with an inverted turner, forming large soft curds.;";

        db.execSQL(TABLE_CREATE);
        //db.execSQL(TABLE_CREATE1);
        this.db=db;
    }

    public void insertContact(Contact c)
    {

        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        String query = "select * from contacts";
        Cursor cursor= db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_USERNAME,c.getUserName());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_PASSWORD, c.getPassword());

        db.insert(TABLE_NAME, null, values);
       // db.close();
        cursor.close();

    }



    public String searchPass(String username){
        db=this.getWritableDatabase();
        String query="select * from contacts";
        Cursor cursor=db.rawQuery(query, null);
        String a,b;
        b="Not Found";
        if(cursor.moveToFirst())
        {
            do{
                a=cursor.getString(COLUMN_USERNAME_INDEX);
                //b=cursor.getString(COLUMN_PASSWORD_INDEX);

                if(username.matches(a))
                {
                    b=cursor.getString(COLUMN_PASSWORD_INDEX);
                    break;

                }

            }
            while(cursor.moveToNext());
            cursor.close();
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
