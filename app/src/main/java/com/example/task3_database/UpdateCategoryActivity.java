package com.example.task3_database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCategoryActivity extends AppCompatActivity {
    private EditText text_update_category_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category);

        text_update_category_name = findViewById(R.id.text_update_category_name);


        final Category category = (Category) getIntent().getSerializableExtra("category");

        loadCategory(category);

        findViewById(R.id.update_category).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                updateCategory(category);
            }
        });

        findViewById(R.id.delete_category).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateCategoryActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteCategory(category);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });

        findViewById(R.id.add_product_to_category).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addProductToCategoryIntent = new Intent(UpdateCategoryActivity.this, AddProductToCategoryActivity.class);
                startActivity(addProductToCategoryIntent);
            }
        });
    }

    private void loadCategory(Category task) {
        text_update_category_name.setText(task.getName());
    }

    private void updateCategory(final Category category) {
        final String sName = text_update_category_name.getText().toString().trim();

        if (sName.isEmpty()) {
            text_update_category_name.setError("Name required");
            text_update_category_name.requestFocus();
            return;
        }


        class UpdateCategory extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                category.setName(sName);
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .categoryDao()
                        .update(category);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateCategoryActivity.this, CategoryMainModelActivity.class));
            }
        }

        UpdateCategory updateCategory = new UpdateCategory();
        updateCategory.execute();
    }


    private void deleteCategory(final Category task) {
        class DeleteCategory extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .categoryDao()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateCategoryActivity.this, CategoryMainModelActivity.class));
            }
        }

        DeleteCategory deleteCategory = new DeleteCategory();
        deleteCategory.execute();

    }
}