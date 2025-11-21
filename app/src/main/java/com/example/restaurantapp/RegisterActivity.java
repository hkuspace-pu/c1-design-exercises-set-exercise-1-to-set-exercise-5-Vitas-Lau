package com.example.restaurantapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText fullNameEdit, emailEdit, phoneEdit, createPasswordEdit, confirmPasswordEdit;
    private CheckBox agreeTermsCheckbox;
    private Button createAccountButton;
    private TextView alreadyHaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Find views
        fullNameEdit = findViewById(R.id.full_name_edit);
        emailEdit = findViewById(R.id.email_edit);
        phoneEdit = findViewById(R.id.phone_edit);
        createPasswordEdit = findViewById(R.id.create_password_edit);
        confirmPasswordEdit = findViewById(R.id.confirm_password_edit);
        agreeTermsCheckbox = findViewById(R.id.agree_terms_checkbox);
        createAccountButton = findViewById(R.id.create_account_button);
        alreadyHaveAccount = findViewById(R.id.already_have_account);

        // Make "Terms & Conditions" clickable
        makeTermsClickable();

        // Make "Sign in" clickable
        makeSignInClickable();

        // Create Account button click
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
					// TODO: Register
					String username = fullNameEdit.getText().toString().split(" ")[0]; // Placeholder username
					Intent intent = new Intent(RegisterActivity.this, GuestDashboardActivity.class);
					intent.putExtra("username", username);
					startActivity(intent);
					finish();
				}
            }
        });
    }

    // Handle back arrow click
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Go back to previous activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void makeTermsClickable() {
        String termsText = getString(R.string.agree_terms);
        SpannableString spannable = new SpannableString(termsText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // TODO: Open Terms & Conditions (e.g., WebView or new activity)
                Toast.makeText(RegisterActivity.this, "Terms & Conditions clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#673AB7")); // Purple
                ds.setUnderlineText(false);
            }
        };
        int start = termsText.indexOf("Terms & Conditions");
        int end = start + "Terms & Conditions".length();
        spannable.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        agreeTermsCheckbox.setText(spannable);
        agreeTermsCheckbox.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void makeSignInClickable() {
        String alreadyText = getString(R.string.already_have_account);
        SpannableString spannable = new SpannableString(alreadyText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#673AB7")); // Purple
                ds.setUnderlineText(false);
            }
        };
        int start = alreadyText.indexOf("Sign in");
        int end = start + "Sign in".length();
        spannable.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        alreadyHaveAccount.setText(spannable);
        alreadyHaveAccount.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private boolean validateInputs() {
        String fullName = fullNameEdit.getText().toString().trim();
        String email = emailEdit.getText().toString().trim();
        String phone = phoneEdit.getText().toString().trim();
        String password = createPasswordEdit.getText().toString();
        String confirmPassword = confirmPasswordEdit.getText().toString();

        if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!agreeTermsCheckbox.isChecked()) {
            Toast.makeText(this, "You must agree to Terms & Conditions", Toast.LENGTH_SHORT).show();
            return false;
        }
        // TODO: Add more validation (e.g., email format, phone format)
        return true;
    }
}