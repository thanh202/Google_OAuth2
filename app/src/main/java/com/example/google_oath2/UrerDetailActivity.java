package com.example.google_oath2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class UrerDetailActivity extends AppCompatActivity {
    private GoogleSignInClient mGoogleignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urer_detail);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String name = intent.getStringExtra("name");
        String pictureUrl = intent.getStringExtra("picture");


        ImageView ivProfile = findViewById(R.id.iv_profile);
        Glide.with(this)
                .load(pictureUrl)
                .circleCrop()
                .into(ivProfile);

        TextView tvEmail = findViewById(R.id.tv_email);
        tvEmail.setText("Email: " + email);

        TextView tvName = findViewById(R.id.tv_name);
        tvName.setText("Name: " + name);

        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sign out of gg acc
                mGoogleignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //clear login info from SharedPreferences
                        SharedPreferences.Editor editor = getSharedPreferences("userdata", MODE_PRIVATE).edit();
                        editor.clear();
                        editor.apply();

                        //quay lai main
                        Intent intent = new Intent(UrerDetailActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

}