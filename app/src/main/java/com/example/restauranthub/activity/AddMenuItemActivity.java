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
import android.widget.LinearLayout;
import android.widget.Spinner;
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
    private EditText etDishName, etDescription, etPrice;
    private Spinner spCategory;
    private CheckBox cbItemAvailable, cbVegetarian, cbVegan, cbGlutenFree, cbDairyFree, cbNutFree, cbSpicy;
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

        // Click to open gallery directly (Camera removed)
        flImageContainer.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(galleryIntent);
        });

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
}