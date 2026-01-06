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
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.restauranthub.R;

public class EditMenuItemActivity extends AppCompatActivity {

    private ImageButton btnBack, btnRemoveImage;
    private FrameLayout flImageContainer;
    private ImageView ivDishImage;
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
                        btnRemoveImage.setVisibility(View.VISIBLE);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu_item);

        btnBack = findViewById(R.id.btnBack);
        btnRemoveImage = findViewById(R.id.btnRemoveImage);
        flImageContainer = findViewById(R.id.flImageContainer);
        ivDishImage = findViewById(R.id.ivDishImage);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        // Pre-fill from intent (example)
        String dishName = getIntent().getStringExtra("dishName");
        double price = getIntent().getDoubleExtra("price", 0.0);
        int imageRes = getIntent().getIntExtra("imageRes", R.drawable.placeholder_image);
        boolean available = getIntent().getBooleanExtra("available", true);
        // TODO: Set to EditTexts/CheckBox

        ivDishImage.setImageResource(imageRes);
        btnRemoveImage.setVisibility(View.VISIBLE);

        flImageContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryLauncher.launch(galleryIntent);
            }
        });

        // Remove image
        btnRemoveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivDishImage.setImageResource(R.drawable.placeholder_image);
                selectedImageUri = null;
                btnRemoveImage.setVisibility(View.GONE);
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
                Toast.makeText(EditMenuItemActivity.this, "Menu item updated", Toast.LENGTH_SHORT).show();
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