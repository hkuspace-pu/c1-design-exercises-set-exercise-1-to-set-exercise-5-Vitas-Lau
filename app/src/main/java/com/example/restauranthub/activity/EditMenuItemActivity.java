package com.example.restauranthub.activity;

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
    private Uri selectedImageUri;
    private EditText etDishName, etDescription, etPrice;
    private CheckBox cbItemAvailable, cbVegetarian, cbVegan, cbGlutenFree, cbDairyFree, cbNutFree, cbSpicy;
    private Button btnSave, btnCancel;

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
        etDishName = findViewById(R.id.etDishName);
        etDescription = findViewById(R.id.etDescription);
        etPrice = findViewById(R.id.etPrice);
        cbItemAvailable = findViewById(R.id.cbItemAvailable);
        cbVegetarian = findViewById(R.id.cbVegetarian);
        cbVegan = findViewById(R.id.cbVegan);
        cbGlutenFree = findViewById(R.id.cbGlutenFree);
        cbDairyFree = findViewById(R.id.cbDairyFree);
        cbNutFree = findViewById(R.id.cbNutFree);
        cbSpicy = findViewById(R.id.cbSpicy);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        // Pre-fill from intent
        String dishName = getIntent().getStringExtra("dishName");
        String description = getIntent().getStringExtra("description");
        double price = getIntent().getDoubleExtra("price", 0.0);
        int imageRes = getIntent().getIntExtra("imageRes", R.drawable.placeholder_dish);
        boolean available = getIntent().getBooleanExtra("available", true);

        etDishName.setText(dishName);
        etDescription.setText(description);
        etPrice.setText(String.valueOf(price));
        cbItemAvailable.setChecked(available);
        ivDishImage.setImageResource(imageRes);
        btnRemoveImage.setVisibility(View.VISIBLE);

        // Click to show dialog for gallery ONLY (Camera removed)
        flImageContainer.setOnClickListener(v -> {
            // Gallery only
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(galleryIntent);
        });

        // Remove image
        btnRemoveImage.setOnClickListener(v -> {
            ivDishImage.setImageResource(R.drawable.placeholder_dish);
            selectedImageUri = null;
            btnRemoveImage.setVisibility(View.GONE);
        });

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // Save
        btnSave.setOnClickListener(v -> {
            if (validateInput()) {
                saveMenuItem();
            }
        });

        // Cancel
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
        // Mock save - return data to caller
        Intent resultIntent = new Intent();
        resultIntent.putExtra("dishName", etDishName.getText().toString());
        resultIntent.putExtra("description", etDescription.getText().toString());
        try {
            resultIntent.putExtra("price", Double.parseDouble(etPrice.getText().toString()));
        } catch (NumberFormatException e) {
            resultIntent.putExtra("price", 0.0);
        }
        resultIntent.putExtra("available", cbItemAvailable.isChecked());
        
        // Pass back dietary flags
        resultIntent.putExtra("vegetarian", cbVegetarian.isChecked());
        resultIntent.putExtra("vegan", cbVegan.isChecked());
        resultIntent.putExtra("glutenFree", cbGlutenFree.isChecked());
        resultIntent.putExtra("dairyFree", cbDairyFree.isChecked());
        resultIntent.putExtra("nutFree", cbNutFree.isChecked());
        resultIntent.putExtra("spicy", cbSpicy.isChecked());

        setResult(RESULT_OK, resultIntent);
        Toast.makeText(this, "Item saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}