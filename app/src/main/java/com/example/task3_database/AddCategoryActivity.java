package com.example.task3_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCategoryActivity extends AppCompatActivity {
    private Button save_category_btn;
    private EditText text_save_category_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        text_save_category_name = findViewById(R.id.text_add_category_name);

        save_category_btn = findViewById(R.id.save_category);
        save_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCategory();
            }
        });
    }

    private void saveCategory() {
        final String sName = text_save_category_name.getText().toString().trim();

        if (sName.isEmpty()) {
            text_save_category_name.setError("Name required");
            text_save_category_name.requestFocus();
            return;
        }

        class SaveCategory extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a seller
                Category category = new Category();
                category.setName(sName);


                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().categoryDao().insert(category);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), CategoryMainModelActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveCategory saveCategory = new SaveCategory();
        saveCategory.execute();
    }
}