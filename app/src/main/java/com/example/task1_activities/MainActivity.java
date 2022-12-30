package com.example.task1_activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button goToAnotherActivityBtn, searchBtn;
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToAnotherActivityBtn = (Button) findViewById(R.id.AnotherActivityBtn);
        textView = (TextView) findViewById(R.id.resultTextView);
        searchBtn = findViewById(R.id.searchBtnId);


        goToAnotherActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);


            }
        });

        Intent rrecieverIntent = getIntent();
        String recieverData = rrecieverIntent.getStringExtra("KEY_SENDER");
        String previousMessage = textView.getText().toString();
        textView.setText(previousMessage + recieverData);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(Intent.ACTION_VIEW);
                search.addCategory(Intent.CATEGORY_BROWSABLE);
                search.setData(Uri.parse(recieverData));
                if (recieverData != null) {
                    startActivity(search);
                }
                Log.w(TAG, "Nothing opened, something's wrong");
            }
        });
    }
}