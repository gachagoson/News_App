package com.example.bluenews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    ImageView imageView_P, imageView_M;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        imageView_P = findViewById(R.id.pstN);
        imageView_M = findViewById(R.id.MngN);
        imageView_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the "Post News" activity
                Intent intent = new Intent(DashboardActivity.this, PostNews.class);
                startActivity(intent);
            }
        });

        imageView_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the "Post News" activity
                Intent intent = new Intent(DashboardActivity.this, PostNews.class);
                startActivity(intent);
            }
        });

    }
}