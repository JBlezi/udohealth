package com.example.udohealth;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class NewSymptomActivity extends AppCompatActivity {
    private EditText nameEditText, startTimeEditText, durationEditText, descriptionEditText;
    private Button submitButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_symptom);

        LinearLayout backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());


        dbHelper = new DBHelper(this);

        nameEditText = findViewById(R.id.name_edit_text);
        startTimeEditText = findViewById(R.id.start_time_edit_text);
        durationEditText = findViewById(R.id.duration_edit_text);
        descriptionEditText = findViewById(R.id.description_edit_text);

        startTimeEditText.setOnClickListener(v -> showTimePickerDialog());

        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm(v);
            }
        });

    }

    private void submitForm(View view) {
        Log.d("NewSymptomActivity", "submitForm called");
        String name = nameEditText.getText().toString();
        String start_time = startTimeEditText.getText().toString();
        String duration = durationEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        // Assuming you have a current user, replace "currentUserId" with the actual value
        long currentUserId = 1;

        // Save the Symptom
        Symptom Symptom = new Symptom(currentUserId, name, description, start_time, duration);
        dbHelper.saveSymptom(Symptom);
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute1) -> startTimeEditText.setText(hourOfDay + ":" + minute1),
                hour, minute, true);
        timePickerDialog.show();
    }
}

