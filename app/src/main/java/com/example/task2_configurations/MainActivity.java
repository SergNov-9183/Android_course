package com.example.task2_configurations;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    Button goToAnotherActivityBtn, searchBtn;
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "Orientation: landscape", Toast.LENGTH_SHORT).show();
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "Orientation: Portait", Toast.LENGTH_SHORT).show();
        }

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