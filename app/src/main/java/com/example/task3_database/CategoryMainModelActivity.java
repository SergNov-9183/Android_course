package com.example.task3_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CategoryMainModelActivity extends AppCompatActivity {

    private FloatingActionButton add_category_btn;
    private RecyclerView categories_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_main_model);

        categories_view = findViewById(R.id.category_list);
        categories_view.setLayoutManager(new LinearLayoutManager(this));

        add_category_btn = findViewById(R.id.add_category);
        add_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryMainModelActivity.this, AddCategoryActivity.class);
                startActivity(intent);
            }
        });

        getCategories();
    }

    private void getCategories() {
        class GetCategories extends AsyncTask<Void, Void, List<Category>> {

            @Override
            protected List<Category> doInBackground(Void... voids) {
                List<Category> categories = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .categoryDao()
                        .getAll();
                return categories;
            }

            @Override
            protected void onPostExecute(List<Category> categories) {
                super.onPostExecute(categories);
                CategoriesAdapter adapter = new CategoriesAdapter(CategoryMainModelActivity.this, categories);
                categories_view.setAdapter(adapter);
            }
        }

        GetCategories getCategories = new GetCategories();
        getCategories.execute();
    }
}