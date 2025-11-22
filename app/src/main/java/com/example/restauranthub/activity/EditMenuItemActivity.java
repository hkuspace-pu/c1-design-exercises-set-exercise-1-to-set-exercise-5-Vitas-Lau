package com.example.restauranthub.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import com.example.restauranthub.R;
import java.io.File;
import java.io.IOException;

public class EditMenuItemActivity extends AppCompatActivity {

    private ImageButton btnBack, btnRemoveImage;
    private FrameLayout flImageContainer;
    private ImageView ivDishImage;
    private Uri selectedImageUri;
    private Uri photoUri; // For camera capture

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

    private final ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        selectedImageUri = photoUri;
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

        // Pre-fill from intent (example)
        String dishName = getIntent().getStringExtra("dishName");
        double price = getIntent().getDoubleExtra("price", 0.0);
        int imageRes = getIntent().getIntExtra("imageRes", R.drawable.placeholder_image);
        boolean available = getIntent().getBooleanExtra("available", true);
        // TODO: Set to EditTexts/CheckBox

        ivDishImage.setImageResource(imageRes);
        btnRemoveImage.setVisibility(View.VISIBLE);

        // Click to show dialog for gallery or camera to replace image
        flImageContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageSourceDialog();
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

        // TODO: Add logic for save, cancel
    }

    private void showImageSourceDialog() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Replace Photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    // Camera
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                        File photoFile = null;
                        try {
                            photoFile = createImageFile();
                        } catch (IOException ex) {
                            // Error
                        }
                        if (photoFile != null) {
                            photoUri = FileProvider.getUriForFile(EditMenuItemActivity.this,
                                    "com.example.restauranthub.fileprovider", photoFile);
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                            cameraLauncher.launch(cameraIntent);
                        }
                    }
                } else if (which == 1) {
                    // Gallery
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    galleryLauncher.launch(galleryIntent);
                } else {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private File createImageFile() throws IOException {
        String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }
}