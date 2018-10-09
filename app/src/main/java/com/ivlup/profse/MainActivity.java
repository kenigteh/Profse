package com.ivlup.profse;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ivlup.profse.tools.Client;
import com.ivlup.profse.tools.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public static String chosen;
    public static String chosenClient;
    public static String chosenName;
    public static ArrayList<Client> clients = new ArrayList();
    public static ArrayList<String> clientCategories = new ArrayList();
    public static FragmentManager fm;
    public static FragmentTransaction ft;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    private Fragment FragmentMenu;
    boolean doubleBackToExitPressedOnce = false;

    public static Map<String, Client> mapClients = new HashMap<String, Client>(1000);
    FrameLayout frame;


    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onSupportNavigateUp(){
        if(getFragmentManager().getBackStackEntryCount() == 1) {
            finish();
            moveTaskToBack(false);
        }
        else {
            super.onBackPressed();
        }
        return false;
    }

    @Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount() == 1) {

           //finish();
            moveTaskToBack(false);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        frame = (findViewById(R.id.fragment_container));
        mDBHelper = new DatabaseHelper(this);
        mDBHelper.updateDataBase();
        //Toast.makeText(this, "Downloaded", Toast.LENGTH_SHORT).show();

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        fetchClients();
        //if (savedInstanceState == null) {
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
                    FragmentMenu = new FragmentMenu();
                    ft.add(R.id.fragment_container, FragmentMenu, "detail");
                    ft.commit();

                   // ft.addToBackStack(null);
        //}


    }

    private void fetchClients() {


        Cursor cursor = mDb.rawQuery("SELECT * FROM new_clients", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            Client client = new Client(

                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9));

            clients.add(client);
            clientCategories.add(client.getCategory());
            mapClients.put(client.getName(),client);

            cursor.moveToNext();
        }
    }

}
