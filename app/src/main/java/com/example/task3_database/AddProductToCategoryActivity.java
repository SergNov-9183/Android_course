package com.example.task3_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProductToCategoryActivity extends AppCompatActivity {
    private Button btn_add_product_to_category;
    private EditText text_add_product_name, text_add_product_category_id, text_add_product_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_to_category);

        text_add_product_name = findViewById(R.id.textAddProductName);
        text_add_product_category_id = findViewById(R.id.textAddProductIdCategory);
        text_add_product_price = findViewById(R.id.textAddProductPrice);

        btn_add_product_to_category = findViewById(R.id.btnAddProduct);
        btn_add_product_to_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProduct();
            }
        });
    }

    private void saveProduct() {
        final String sName = text_add_product_name.getText().toString().trim();
        final String sCategoryId = text_add_product_category_id.getText().toString().trim();
        final String sPrice = text_add_product_price.getText().toString().trim();

        if (sName.isEmpty()) {
            text_add_product_name.setError("Name required");
            text_add_product_name.requestFocus();
            return;
        }

        if (sCategoryId.isEmpty()) {
            text_add_product_category_id.setError("Name required");
            text_add_product_category_id.requestFocus();
            return;
        }

        if (sPrice.isEmpty()) {
            text_add_product_price.setError("Name required");
            text_add_product_price.requestFocus();
            return;
        }

        class SaveProductInfo extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a seller
                Product product = new Product();
                product.setName(sName);
                product.setCategory_id(sCategoryId);
                product.setPrice(sPrice);


                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().productDao().insert(product);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), UpdateCategoryActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveProductInfo saveProduct = new SaveProductInfo();
        saveProduct.execute();
    }
}