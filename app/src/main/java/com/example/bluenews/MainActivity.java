package com.example.bluenews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import com.example.bluenews.adapters.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private ImageView addNews;

    private FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addNews = findViewById(R.id.add_news_button);
        addNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the "Post News" activity
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Set up the ViewPager with the FragmentAdapter
        viewPager = findViewById(R.id.view_pager);
        adapter = new FragmentAdapter(getSupportFragmentManager(), 0);
        viewPager.setAdapter(adapter);

        // Set up the TabLayout with the ViewPager
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Home");
        tabLayout.getTabAt(1).setText("Health");
        tabLayout.getTabAt(2).setText("Sports");
        tabLayout.getTabAt(3).setText("Education");
        tabLayout.getTabAt(4).setText("Science");
        tabLayout.getTabAt(5).setText("Tech");
        tabLayout.getTabAt(6).setText("National");
    }
}
