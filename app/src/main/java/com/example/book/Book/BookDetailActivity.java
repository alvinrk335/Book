// Purpose: book details
//Shows detailed info about a selected book.
// *Receives a Book object from BookAdapter via Intent.
// *Displays the book’s details (title, author, etc.).


package com.example.book.Book;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.book.R;

public class BookDetailActivity extends AppCompatActivity {

    private EditText addressInput, phoneInput;// The book being displayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // Back button setup
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, BookMainActivity.class));  // Removed "packageContext:"
            finish();
        });

        // Initialize views
        addressInput = findViewById(R.id.addressInput);
        phoneInput = findViewById(R.id.phoneInput);
        Button submitBtn = findViewById(R.id.submitBtn);
        ImageView bookCover = findViewById(R.id.bookImage);
        TextView bookTitle = findViewById(R.id.bookTitle);

        // Get book data from intent
        Book currentBook = getIntent().getParcelableExtra("book");
        if (currentBook != null) {
            bookCover.setImageResource(currentBook.getCoverImageId());
            bookTitle.setText(currentBook.getName());
        }

        submitBtn.setOnClickListener(v -> validateInputs());
    }

    private void validateInputs() {
        String address = addressInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();

        if (address.isEmpty()) {
            showDialog("Uh oh!", "Address cannot be empty", true);
        } else if (phone.isEmpty()) {
            showDialog("Uh oh!", "Phone number cannot be empty", true);
        } else if (!phone.matches("\\d+")) {
            showDialog("Uh oh!", "Phone must contain only numbers", true);
        } else {
            showDialog("Thank you!", "Confirmation email sent to your email", false);
        }
    }

    private void showDialog(String title, String message, boolean isError) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate custom layout - CORRECTED LINE:
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_custom, null);

        TextView titleView = dialogView.findViewById(R.id.dialog_title);
        TextView messageView = dialogView.findViewById(R.id.dialog_message);
        TextView closeBtn = dialogView.findViewById(R.id.dialog_close_button);

        // Style dialog
        titleView.setText(title);
        titleView.setTextColor(isError ? Color.RED : Color.GREEN);
        messageView.setText(message);

        builder.setView(dialogView)
                .setCancelable(false);

        AlertDialog dialog = builder.create();

        // Click listener for close button
        closeBtn.setOnClickListener(v -> {
            dialog.dismiss();
            if (!isError) {
                // Success action
            }
        });

        dialog.show();
    }
}