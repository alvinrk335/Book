package com.example.book.StoreList;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.book.R;

public class StoreListMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_list_main);

        Button backButton = findViewById(R.id.backButton_store);
        backButton.setOnClickListener(v -> {
            finish();
        });
    }
}
