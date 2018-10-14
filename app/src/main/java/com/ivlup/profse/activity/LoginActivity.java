package com.ivlup.profse.activity;

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
import com.ivlup.profse.activity.MainActivity;
import com.ivlup.profse.backend.Answer;
import com.ivlup.profse.backend.Category;
import com.ivlup.profse.backend.Contractor;
import com.ivlup.profse.backend.DB;
import com.ivlup.profse.backend.Data;
import com.ivlup.profse.backend.DatabaseHelper;
import com.ivlup.profse.backend.Links;
import com.ivlup.profse.backend.User;

import org.json.JSONException;
import org.json.JSONObject;

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
                        Data.setContractors(response.body().сontractors);
                        Data.setLinks(response.body().links);
                        Data.setCategories(response.body().categories);
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
        if (Data.getCategories().length > 0){
            DatabaseHelper mDBHelper = new DatabaseHelper(this);
            mDBHelper.updateDataBase();
            SQLiteDatabase mDb;
            try {
                mDb = mDBHelper.getWritableDatabase();
            } catch (SQLException mSQLException) {
                throw mSQLException;
            }
            mDb.rawQuery("DELETE FROM categories", null);
            StringBuilder sql1 = new StringBuilder("INSERT INTO `categories`(`id`, `name`, `photo`, `parent_id`, `type`) VALUES ");
            for (Category cat:
                    Data.getCategories()) {
                sql1.append("(");
                sql1.append(cat.id).append(",\"").append(pretty(cat.name)).append("\",\"").append(pretty(cat.photo)).append("\",").append(cat.parent_id).append(",").append(cat.type);
                sql1.append("),");
            }
            mDb.rawQuery(sql1.toString().substring(0, sql1.toString().length()-1), null);

            mDb.rawQuery("DELETE FROM links", null);
            StringBuilder sql2 = new StringBuilder("INSERT INTO `links`(`id`, `contactor_id`, `category_id`) VALUES ");
            for (Links link:
                    Data.getLinks()) {
                sql2.append("(");
                sql2.append(link.id).append(",").append(link.contactor_id).append(",").append(link.category_id);
                sql2.append("),");
            }
            mDb.rawQuery(sql2.toString().substring(0, sql2.toString().length()-1), null);

            mDb.rawQuery("DELETE FROM сontractors", null);
            StringBuilder sql3 = new StringBuilder("INSERT INTO `сontractors`(`id`, `name`, `info`, `phone`, `address`, `site`, `vk`, `facebook`, `instagram`, `twitter`) VALUES ");
            for (Contractor cont:
                    Data.getContractors()) {
                sql3.append("(");
                sql3.append(cont.id).append(",\"").append(pretty(cont.name)).append("\",\"").append(pretty(cont.info)).append("\",\"").append(pretty(cont.phone)).append("\",\"").append(pretty(cont.address)).append("\",\"").append(pretty(cont.site)).append("\",\"").append(pretty(cont.vk)).append("\",\"").append(pretty(cont.facebook)).append("\",\"").append(pretty(cont.instagram)).append("\",\"").append(pretty(cont.twitter)).append("\"");
                sql3.append("),");
            }
            mDb.rawQuery(sql3.toString().substring(0, sql3.toString().length()-1), null);
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
                        if (Data.getContractors() != null) {
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
