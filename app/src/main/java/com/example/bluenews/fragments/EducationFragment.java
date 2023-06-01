package com.example.bluenews.fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluenews.R;
import com.example.bluenews.adapters.NewsAdapter;
import com.example.bluenews.models.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class EducationFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<News> educationNewsList;

    private boolean isDataFetched = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_education, container, false);

        recyclerView = view.findViewById(R.id.educationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        educationNewsList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference newsRef = database.getReference("news");

        newsRef.orderByChild("category").equalTo("Education").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<News> newsList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Retrieve the news data and create a News object
                    String category = snapshot.child("category").getValue(String.class);
                    String content = snapshot.child("content").getValue(String.class);
                    String date = snapshot.child("date").getValue(String.class);
                    String headline = snapshot.child("headline").getValue(String.class);
                    String imageUrl = snapshot.child("imageUrl").getValue(String.class);
                    String source = snapshot.child("source").getValue(String.class);

                    News news = new News(category, content, date, headline, imageUrl, source);
                    newsList.add(news);
                }

                // Create an instance of NewsAdapter and pass the newsList to it
                NewsAdapter adapter = new NewsAdapter(newsList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error retrieving news data: " + databaseError.getMessage());
            }
        });


        newsAdapter = new NewsAdapter(educationNewsList);
        recyclerView.setAdapter(newsAdapter);

        return view;
    }
}



