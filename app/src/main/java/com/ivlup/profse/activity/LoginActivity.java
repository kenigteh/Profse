package com.ivlup.profse.activity;
import android.content.Intent;
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
import com.ivlup.profse.backend.DB;
import com.ivlup.profse.backend.User;

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
                }
                @Override
                public void onFailure(@NonNull Call<Answer> call, @NonNull Throwable t) {
                    Log.i("MyLog","Change = " + t.getMessage());
                }
            });
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            mStatusTextView.setText(R.string.signed_out);
            Toast.makeText(getApplicationContext(), "Ошибка во время авторизации, повторите попытку позднее.", Toast.LENGTH_LONG).show();
        }
    }
}