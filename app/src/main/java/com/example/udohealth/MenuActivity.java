package com.example.udohealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setHeaderText("Menu");


    }
    public void setHeaderText(String text) {
        TextView headerText = findViewById(R.id.header_text);
        headerText.setText(text);
    }

}