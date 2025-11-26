package com.example.restauranthub.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
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

public class AddMenuItemActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private FrameLayout flImageContainer;
    private ImageView ivDishImage;
    private LinearLayout llUploadPrompt;
    private EditText etDishName, etDescription, etPrice;
    private Spinner spCategory;
    private CheckBox cbItemAvailable, cbVegetarian, cbVegan, cbGlutenFree, cbDairyFree, cbNutFree, cbSpicy;
    private Button btnSave, btnCancel;
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
                        llUploadPrompt.setVisibility(View.GONE);
                        ivDishImage.setVisibility(View.VISIBLE);
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
        etDishName = findViewById(R.id.etDishName);
        etDescription = findViewById(R.id.etDescription);
        etPrice = findViewById(R.id.etPrice);
        spCategory = findViewById(R.id.spCategory);
        cbItemAvailable = findViewById(R.id.cbItemAvailable);
        cbVegetarian = findViewById(R.id.cbVegetarian);
        cbVegan = findViewById(R.id.cbVegan);
        cbGlutenFree = findViewById(R.id.cbGlutenFree);
        cbDairyFree = findViewById(R.id.cbDairyFree);
        cbNutFree = findViewById(R.id.cbNutFree);
        cbSpicy = findViewById(R.id.cbSpicy);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        // Initial state: Show upload prompt, hide image
        ivDishImage.setVisibility(View.GONE);
        llUploadPrompt.setVisibility(View.VISIBLE);

        // Click to show dialog for gallery or camera
        flImageContainer.setOnClickListener(v -> showImageSourceDialog());

        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            if (validateInput()) {
                saveMenuItem();
            }
        });

        btnCancel.setOnClickListener(v -> finish());
    }

    private boolean validateInput() {
        if (etDishName.getText().toString().trim().isEmpty()) {
            etDishName.setError("Name is required");
            return false;
        }
        if (etPrice.getText().toString().trim().isEmpty()) {
            etPrice.setError("Price is required");
            return false;
        }
        return true;
    }

    private void saveMenuItem() {
        // In a real app, this would save to a database.
        // Return data to caller
        Intent resultIntent = new Intent();
        resultIntent.putExtra("dishName", etDishName.getText().toString());
        resultIntent.putExtra("description", etDescription.getText().toString());
        try {
            resultIntent.putExtra("price", Double.parseDouble(etPrice.getText().toString()));
        } catch (NumberFormatException e) {
            resultIntent.putExtra("price", 0.0);
        }
        resultIntent.putExtra("available", cbItemAvailable.isChecked());

        // Pass back dietary flags if needed
        resultIntent.putExtra("vegetarian", cbVegetarian.isChecked());
        resultIntent.putExtra("vegan", cbVegan.isChecked());
        resultIntent.putExtra("glutenFree", cbGlutenFree.isChecked());
        resultIntent.putExtra("dairyFree", cbDairyFree.isChecked());
        resultIntent.putExtra("nutFree", cbNutFree.isChecked());
        resultIntent.putExtra("spicy", cbSpicy.isChecked());
        
        // Pass category if we had the spinner set up, for now default
        // resultIntent.putExtra("category", spCategory.getSelectedItem().toString());

        setResult(RESULT_OK, resultIntent);
        Toast.makeText(this, "Menu item added!", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void showImageSourceDialog() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
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
                            photoUri = FileProvider.getUriForFile(AddMenuItemActivity.this,
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