package com.ivlup.profse.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.GsonBuilder;
import com.ivlup.profse.R;
import com.ivlup.profse.backend.Answer;
import com.ivlup.profse.backend.Category;
import com.ivlup.profse.backend.Contractor;
import com.ivlup.profse.backend.DB;
import com.ivlup.profse.backend.Data;
import com.ivlup.profse.backend.DatabaseHelper;
import com.ivlup.profse.backend.Link;
import com.ivlup.profse.backend.User;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener{

    GoogleSignInClient mGoogleSignInClient;
    private TextView mStatusTextView;

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.profse.pro/")
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .build();

            DB request = retrofit.create(DB.class);

            request.update().enqueue(new Callback<Answer>() {
                @Override
                public void onResponse(@NonNull Call<Answer> call, @NonNull Response<Answer> response) {
                    if (response.body() != null) {
                        Log.i("Test", "Tewqd");
                        Data.setContractors(new ArrayList<>(Arrays.asList(response.body().сontractors)));
                        Data.setLinks(new ArrayList<>(Arrays.asList(response.body().links)));
                        Data.setCategories(new ArrayList<>(Arrays.asList(response.body().categories)));
                        localUpdate();
                    }
                    else {
                        Log.i("MyLog","Какого хуя он пустой!?");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Answer> call, @NonNull Throwable t) {
                    Log.i("MyLog","Error = " + t.getMessage());
                }
            });
        }
        else{
            DatabaseHelper mDBHelper = new DatabaseHelper(this);
            mDBHelper.updateDataBase();
            SQLiteDatabase mDb;
            try {
                mDb = mDBHelper.getWritableDatabase();
            } catch (SQLException mSQLException) {
                throw mSQLException;
            }

            String sql1 = "SELECT * FROM сontractors";
            String sql2 = "SELECT * FROM links";
            String sql3 = "SELECT * FROM categories";

            Cursor cursor1 = mDb.rawQuery(sql1, null);
            cursor1.moveToFirst();
            ArrayList<Contractor> contractors = new ArrayList<>();
            while (!cursor1.isAfterLast()) {
                Contractor contractor = new Contractor();
                contractor.id = Integer.valueOf(cursor1.getString(0));
                contractor.name = cursor1.getString(1);
                contractor.info = cursor1.getString(2);
                contractor.phone = cursor1.getString(3);
                contractor.address = cursor1.getString(4);
                contractor.site = cursor1.getString(5);
                contractor.vk = cursor1.getString(6);
                contractor.facebook = cursor1.getString(7);
                contractor.twitter = cursor1.getString(8);
                contractor.instagram = cursor1.getString(9);
                contractors.add(contractor);

                cursor1.moveToNext();
            }
            Data.setContractors(contractors);

            Cursor cursor2 = mDb.rawQuery(sql2, null);
            cursor2.moveToFirst();
            ArrayList<Link> links = new ArrayList<>();
            while (!cursor2.isAfterLast()) {
                Link link = new Link();
                link.id = Integer.valueOf(cursor2.getString(0));
                link.contactor_id = Integer.valueOf(cursor2.getString(1));
                link.category_id = Integer.valueOf(cursor2.getString(2));
                links.add(link);

                cursor2.moveToNext();
            }
            Data.setLinks(links);

            Cursor cursor3 = mDb.rawQuery(sql3, null);
            cursor3.moveToFirst();
            ArrayList<Category> categories = new ArrayList<>();
            while (!cursor3.isAfterLast()) {
                Category category = new Category();
                category.id = Integer.valueOf(cursor3.getString(0));
                category.name = cursor3.getString(1);
                category.photo = cursor3.getString(2);
                category.parent_id = Integer.valueOf(cursor3.getString(3));
                category.type = Integer.valueOf(cursor3.getString(4));

                categories.add(category);

                cursor3.moveToNext();
            }
            Data.setCategories(categories);
        }



        mStatusTextView = findViewById(R.id.status);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    String pretty(String s){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '"'){
                ans.append("\n");
            }
            else {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    void localUpdate(){
        if (Data.getCategories().size() > 0){
            DatabaseHelper mDBHelper = new DatabaseHelper(this);
            SQLiteDatabase mDb;
            try {
                mDb = mDBHelper.getWritableDatabase();
            } catch (SQLException mSQLException) {
                throw mSQLException;
            }

            mDb.delete("categories", null, null);
            for (Category cat:
                    Data.getCategories()) {
                ContentValues insertValues1 = new ContentValues();

                insertValues1.put("id", cat.id);
                insertValues1.put("name", cat.name);
                insertValues1.put("photo", cat.photo);
                insertValues1.put("parent_id", cat.parent_id);
                insertValues1.put("type", cat.type);

                mDb.insert("categories", null, insertValues1);
            }

            mDb.delete("links", null, null);
            for (Link link:
                    Data.getLinks()) {
                ContentValues insertValues2 = new ContentValues();

                insertValues2.put("id", link.id);
                insertValues2.put("contactor_id", link.contactor_id);
                insertValues2.put("category_id", link.category_id);

                mDb.insert("links", null, insertValues2);
            }

            mDb.delete("сontractors", null, null);
            for (Contractor cont:
                    Data.getContractors()) {
                ContentValues insertValues3 = new ContentValues();

                insertValues3.put("id", cont.id);
                insertValues3.put("name", cont.name);
                insertValues3.put("info", cont.info);
                insertValues3.put("phone", cont.phone);
                insertValues3.put("address", cont.address);
                insertValues3.put("site", cont.site);
                insertValues3.put("vk", cont.vk);
                insertValues3.put("facebook", cont.facebook);
                insertValues3.put("instagram", cont.instagram);
                insertValues3.put("twitter", cont.twitter);

                mDb.insert("сontractors", null, insertValues3);

            }
            mDb.close();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.

            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {
            User.name = account.getGivenName();
            User.surname = account.getFamilyName();
            User.email = account.getEmail();
            User.google_id = account.getId();
            User.photo = account.getPhotoUrl().toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.profse.pro/")
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .build();

            DB request = retrofit.create(DB.class);

            request.login(User.google_id, User.name, User.surname, User.email, User.photo).enqueue(new Callback<Answer>() {
                @Override
                public void onResponse(@NonNull Call<Answer> call, @NonNull Response<Answer> response) {
                    if (response.body() != null) {
                        Log.i("MyLog","Change = " + response.body().ans);
                    }
                    else {
                        Log.i("MyLog","Какого хуя он пустой!?");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Answer> call, @NonNull Throwable t) {
                    Log.i("MyLog","Change = " + t.getMessage());
                }
            });

            final Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (Data.getCategories() != null) {
                            next();
                            break;
                        } else {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        } else {
            mStatusTextView.setText(R.string.signed_out);
            Toast.makeText(getApplicationContext(), "Ошибка во время авторизации, повторите попытку позднее.", Toast.LENGTH_LONG).show();
        }
    }
    void next(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }
}
