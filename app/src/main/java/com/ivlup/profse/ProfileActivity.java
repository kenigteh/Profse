package com.ivlup.profse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivlup.profse.backend.User;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    TextView profile_name;
    ImageView profile_avatar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_name = findViewById(R.id.profile_name);
        profile_avatar = findViewById(R.id.profile_avatar);

        profile_name.setText(User.name + " " + User.surname);
        Picasso.get().load(User.photo).into(profile_avatar);
    }
}
