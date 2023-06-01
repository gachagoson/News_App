package com.example.bluenews;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.bluenews.models.News;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostNews extends AppCompatActivity {

    private static final int REQUEST_PERMISSIONS = 1;
    private static final int REQUEST_IMAGE = 2;

    private EditText editHeadline;

    private ImageView imageView;
    private Uri imageUri;

    private EditText editContent;
    private EditText editDate;
    private EditText editSource;
    private Button btnPost;
    private Spinner spinnerCategory;
    private String selectedCategory;

    private DatabaseReference newsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_news);

        // Initialize the ImageView
        imageView = findViewById(R.id.image_news);

        // Initialize the Spinner
        spinnerCategory = findViewById(R.id.spinner_category);

        // Set an OnItemSelectedListener for the Spinner
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the selected category
                selectedCategory = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected
                selectedCategory = "";
            }
        });

        // Initialize Firebase Database reference
        newsRef = FirebaseDatabase.getInstance().getReference("news");

        editHeadline = findViewById(R.id.edit_headline);
        editContent = findViewById(R.id.edit_content);
        editDate = findViewById(R.id.edit_date);
        editSource = findViewById(R.id.edit_source);
        btnPost = findViewById(R.id.btn_post);

        // Set an OnClickListener for the image selection button
        Button btn_select_image = findViewById(R.id.btn_select_image);
        btn_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the necessary permissions are granted
                if (checkPermissions()) {
                    // Launch the image selection intent
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQUEST_IMAGE);
                } else {
                    // Request the necessary permissions
                    requestPermissions();
                }
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the values from the input fields
                String headline = editHeadline.getText().toString().trim();
                String content = editContent.getText().toString().trim();
                String date = editDate.getText().toString().trim();
                String source = editSource.getText().toString().trim();

                // Perform any necessary validation

                // Create a unique key for the news item
                String newsId = newsRef.push().getKey();

                // Get the image URL from the imageUri
                String imageUrl = (imageUri != null) ? imageUri.toString() : null;

                // Create a NewsItem object with the input values
                News news = new News(headline, content, date, source, imageUrl, selectedCategory);

                // Save the news item to Firebase Realtime Database
                newsRef.child(newsId).setValue(news);

                // Show a success message
                Toast.makeText(PostNews.this, "News posted successfully!", Toast.LENGTH_SHORT).show();

                // Finish the activity
                finish();
            }
        });
    }

    // Check if the necessary permissions are granted
    private boolean checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int readPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            int writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return readPermission == PackageManager.PERMISSION_GRANTED && writePermission == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    // Request the necessary permissions
    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, REQUEST_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with your logic
                // Launch the image selection intent
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE);
            } else {
                // Permission denied, handle accordingly (e.g., show a message or disable functionality)
                Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
            // Retrieve the image URI
            imageUri = data.getData();

            // Set the image URI to the ImageView
            imageView.setImageURI(imageUri);
        }
    }
}
