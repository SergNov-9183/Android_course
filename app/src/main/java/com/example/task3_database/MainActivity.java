package com.example.task3_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button edit_categories_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_categories_btn = findViewById(R.id.edit_categories);

        edit_categories_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editCategoriesIntent = new Intent(MainActivity.this, CategoryMainModelActivity.class);
                startActivity(editCategoriesIntent);
            }
        });

    }
}