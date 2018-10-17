package com.ivlup.profse.activity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ivlup.profse.backend.Category;
import com.ivlup.profse.backend.Data;
import com.ivlup.profse.fragment.FragmentCategoryCards;
import com.ivlup.profse.R;
import com.ivlup.profse.backend.User;
import com.ivlup.profse.contractor.Contractor;
import com.ivlup.profse.backend.DatabaseHelper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public static String chosen;
    public static String chosenClient;
    public static String chosenName;

    public static ArrayList<Contractor> contractors = new ArrayList();


    public static FragmentManager fm;
    public static FragmentTransaction ft;
    private       DatabaseHelper mDBHelper;
    private       SQLiteDatabase mDb;
    private       Fragment FragmentCategoryCards;
    private       DrawerLayout mDrawerLayout;
    public        FrameLayout frame;

    public static Map<String, Contractor> mapContractors = new HashMap<String, Contractor>(1000);



    public void openDrawer(){
        mDrawerLayout.openDrawer(mDrawerLayout);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onSupportNavigateUp(){

         if (getFragmentManager().getBackStackEntryCount() == 1) {
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

            finish();
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

        if (savedInstanceState == null) {
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
                        FragmentCategoryCards = new FragmentCategoryCards();
                    ft.add(R.id.fragment_container, FragmentCategoryCards, "detail");
                    ft.commit();

                   // ft.addToBackStack(null);
        }
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        LinearLayout profileHeader = (LinearLayout) headerview.findViewById(R.id.profile_header);

        TextView navName = headerview.findViewById(R.id.nav_name);
        navName.setText(User.name);


        profileHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });


    }

    public static void updateAdapter () {

      //




        //getSupportFragmentManager().beginTransaction().detach(FragmentCategoryCards).commitNowAllowingStateLoss();
        //getSupportFragmentManager().beginTransaction().attach(FragmentCategoryCards).commitAllowingStateLoss();
    }
}
