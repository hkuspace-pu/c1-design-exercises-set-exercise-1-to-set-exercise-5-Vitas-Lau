package com.example.restaurantapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.restaurantapp.model.MenuItem;

public class AddEditMenuItemActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    private ImageView dishImage;
    private TextView tapToUpload;
    private ImageView btnRemoveImage;
    private EditText etDishName, etDescription, etPrice;
    private CheckBox cbAvailable;
    private CheckBox cbVegetarian, cbVegan, cbGlutenFree, cbDairyFree, cbNutFree, cbSpicy;
    private Uri selectedImageUri;
    private MenuItem editingItem; // null if adding new

    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    updateImageView();
                }
            });

    private final ActivityResultLauncher<Intent> takePhotoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    updateImageView();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_menu_item);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initViews();

        // Check if editing existing item
        editingItem = (MenuItem) getIntent().getSerializableExtra("menu_item");
        if (editingItem != null) {
            toolbar.setTitle(getString(R.string.edit_menu_item));
            populateFields();
        }

        FrameLayout imageContainer = findViewById(R.id.image_container);
        imageContainer.setOnClickListener(v -> showImagePickerOptions());

        btnRemoveImage.setOnClickListener(v -> {
            selectedImageUri = null;
            updateImageView();
        });

        findViewById(R.id.btn_cancel).setOnClickListener(v -> finish());

        findViewById(R.id.btn_save).setOnClickListener(v -> saveMenuItem());
    }

    private void initViews() {
        dishImage = findViewById(R.id.dish_image);
        tapToUpload = findViewById(R.id.tap_to_upload);
        btnRemoveImage = findViewById(R.id.btn_remove_image);
        etDishName = findViewById(R.id.et_dish_name);
        etDescription = findViewById(R.id.et_description);
        etPrice = findViewById(R.id.et_price);
        cbAvailable = findViewById(R.id.cb_available);
        cbVegetarian = findViewById(R.id.cb_vegetarian);
        cbVegan = findViewById(R.id.cb_vegan);
        cbGlutenFree = findViewById(R.id.cb_gluten_free);
        cbDairyFree = findViewById(R.id.cb_dairy_free);
        cbNutFree = findViewById(R.id.cb_nut_free);
        cbSpicy = findViewById(R.id.cb_spicy);
    }

    private void showImagePickerOptions() {
        String[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // You need to handle file provider for real capture
                        takePhotoLauncher.launch(takePicture);
                    } else if (which == 1) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        pickImageLauncher.launch(pickPhoto);
                    }
                }).show();
    }

    private void updateImageView() {
        if (selectedImageUri != null) {
            dishImage.setImageURI(selectedImageUri);
            tapToUpload.setVisibility(View.GONE);
            btnRemoveImage.setVisibility(View.VISIBLE);
        } else {
            dishImage.setImageResource(R.drawable.placeholder_food);
            tapToUpload.setVisibility(View.VISIBLE);
            btnRemoveImage.setVisibility(View.GONE);
        }
    }

    private void populateFields() {
        etDishName.setText(editingItem.getName());
        etDescription.setText("Freshly baked artisan bread..."); // Set from item
        etPrice.setText(String.format("$%.2f", editingItem.getPrice()));
        cbAvailable.setChecked(editingItem.isAvailable());
        // Set dietary flags as needed
    }

    private void saveMenuItem() {
        String name = etDishName.getText().toString().trim();
        String desc = etDescription.getText().toString().trim();
        String priceStr = etPrice.getText().toString().replace("$", "").trim();

        if (name.isEmpty() || desc.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        boolean available = cbAvailable.isChecked();

        // TODO: Save to backend / local list
        Toast.makeText(this, editingItem == null ? "Item added!" : "Item updated!", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}