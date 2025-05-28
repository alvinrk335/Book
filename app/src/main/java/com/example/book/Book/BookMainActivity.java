//Purpose: The Home Screen (for books)
//Displays tabs for fiction/non-fiction books and manages the RecyclerView.
// *Creates two book lists (fiction/non-fiction).
// *Initializes the BookAdapter with the fiction list by default.
// *Switches lists when tabs are clicked.

package com.example.book.Book;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book.R;
import com.example.book.StoreList.StoreListMainActivity;

import java.util.ArrayList;
import java.util.List;

public class BookMainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private final List<Book> fictionBooks = new ArrayList<>();
    private final List<Book> nonFictionBooks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_main);

        // Initialize Views
        recyclerView = findViewById(R.id.recyclerView);
        Button fictionTab = findViewById(R.id.fictionTab);
        Button nonFictionTab = findViewById(R.id.nonFictionTab);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BookAdapter(fictionBooks); // Default to fiction books
        recyclerView.setAdapter(bookAdapter);

        // Load Book Data
        initializeBooks();

        // Tab Listeners (simplified with lambdas)
        fictionTab.setOnClickListener(v -> bookAdapter.updateBooks(fictionBooks));
        nonFictionTab.setOnClickListener(v -> bookAdapter.updateBooks(nonFictionBooks));

        Button moveToStoreListButton = findViewById(R.id.moveToStoreListButton);
        moveToStoreListButton.setOnClickListener(v -> {
            Intent intent = new Intent(BookMainActivity.this, StoreListMainActivity.class);
            startActivity(intent);
        });
    }

    private void initializeBooks() {
        // Fiction Books
        fictionBooks.add(new Book("After Sappho", "Selby Wynn S.", "8", "$18", "ENG", R.drawable.after_sappho));
        fictionBooks.add(new Book("Dune", "Frank Herbert", "9", "$20", "ENG", R.drawable.dune));
        fictionBooks.add(new Book("Apartment", "Taddy Wayne", "8.5", "$19", "ENG", R.drawable.apartment));
        fictionBooks.add(new Book("The Upstairs Room", "Kate Murray B.", "9", "$20", "ENG", R.drawable.the_upstairs_room));
        fictionBooks.add(new Book("Bear", "F. De Cannes", "7", "$12", "ENG", R.drawable.bear));

        // Non-Fiction Books
        nonFictionBooks.add(new Book("Climate Optimism", "Zahrah Biabani", "9", "$15", "ENG", R.drawable.climate_optimism));
        nonFictionBooks.add(new Book("Wayfinding", "Michael Bond", "8", "$23", "ENG", R.drawable.wayfinding));
        nonFictionBooks.add(new Book("Human Acts", "Han Kang", "7.5", "$17", "ENG", R.drawable.human_acts));
        nonFictionBooks.add(new Book("Son of Sin", "Omar Sakr", "8.5", "$34", "ENG", R.drawable.son_of_sin));
        nonFictionBooks.add(new Book("Tin Man", "Sarah Winman", "8", "$21", "ENG", R.drawable.tin_man));
    }
}
