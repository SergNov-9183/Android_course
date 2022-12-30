package com.example.task1_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    Button saveAndReturnButton;
    EditText URLEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        saveAndReturnButton = findViewById(R.id.SaveAndReturnBtn);
        URLEdit = findViewById(R.id.TextInputId);

        saveAndReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(SecondActivity.this, MainActivity.class);
                intent2.putExtra("KEY_SENDER", URLEdit.getText().toString());
                startActivity(intent2);


            }
        });

    }
}