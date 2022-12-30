package com.example.task2_configurations;

import static com.google.android.material.internal.ViewUtils.dpToPx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    Button saveAndReturnButton;
    EditText URLEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final View activityRootView = findViewById(android.R.id.content);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onGlobalLayout() {
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if (((heightDiff >  dpToPx(activityRootView.getContext(), 200))) && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) { //для ландшафтной ориентации данный метод не годится, другого найти не смог
                    Toast.makeText(SecondActivity.this, "Keyboard active", Toast.LENGTH_SHORT).show();
                }
                else if ((heightDiff <  dpToPx(activityRootView.getContext(), 200)) && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){ //для ландшафтной ориентации данный метод не годится, другого найти не смог
                    Toast.makeText(SecondActivity.this, "Keyboard inactive", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "Orientation: landscape", Toast.LENGTH_SHORT).show();
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "Orientation: Portrait", Toast.LENGTH_SHORT).show();
        }


        saveAndReturnButton = findViewById(R.id.SaveAndReturnBtn);
        URLEdit = findViewById(R.id.TextInputId);

        saveAndReturnButton.setOnClickListener((View view) -> {

            Intent intent2 = new Intent(SecondActivity.this, MainActivity.class);
            intent2.putExtra("KEY_SENDER", URLEdit.getText().toString());
            startActivity(intent2);
        });

    }
}