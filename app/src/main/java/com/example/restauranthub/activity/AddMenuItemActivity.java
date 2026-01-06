package com.example.restauranthub.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restauranthub.R;

public class AddMenuItemActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private FrameLayout flImageContainer;
    private ImageView ivDishImage;
    private LinearLayout llUploadPrompt;
    private Button btnSave, btnCancel;
    private Uri selectedImageUri;

    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        selectedImageUri = result.getData().getData();
                        ivDishImage.setImageURI(selectedImageUri);
                        llUploadPrompt.setVisibility(View.GONE);
                        ivDishImage.setVisibility(View.VISIBLE);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item);

        btnBack = findViewById(R.id.btnBack);
        flImageContainer = findViewById(R.id.flImageContainer);
        ivDishImage = findViewById(R.id.ivDishImage);
        llUploadPrompt = findViewById(R.id.llUploadPrompt);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        // Initial state: Show upload prompt, hide image
        ivDishImage.setVisibility(View.GONE);
        llUploadPrompt.setVisibility(View.VISIBLE);

        // Click to launch gallery
        flImageContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryLauncher.launch(galleryIntent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement actual save logic
                Toast.makeText(AddMenuItemActivity.this, "Menu item saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}