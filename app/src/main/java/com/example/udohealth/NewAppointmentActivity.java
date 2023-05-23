package com.example.udohealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Calendar;

public class NewAppointmentActivity extends AppCompatActivity {

    private EditText dateEditText, timeEditText, diagnosisEditText, reasonEditText, doctorEditText;
    private Button submitButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);

        LinearLayout backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());


        dbHelper = new DBHelper(this);

        dateEditText = findViewById(R.id.date_edit_text);
        timeEditText = findViewById(R.id.time_edit_text);
        diagnosisEditText = findViewById(R.id.diagnosis_edit_text);
        reasonEditText = findViewById(R.id.reason_edit_text);
        doctorEditText = findViewById(R.id.doctor_edit_text);

        // Add date picker and time picker listeners for dateEditText and timeEditText
        dateEditText.setOnClickListener(v -> showDatePickerDialog());
        timeEditText.setOnClickListener(v -> showTimePickerDialog());

        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm(v);
            }
        });

    }

    private void submitForm(View view) {
        Log.d("NewAppointmentActivity", "submitForm called");
        String date = dateEditText.getText().toString();
        String time = timeEditText.getText().toString();
        String diagnosis = diagnosisEditText.getText().toString();
        String reason = reasonEditText.getText().toString();
        String doctorName = doctorEditText.getText().toString();

        // Assuming you have a current user, replace "currentUserId" with the actual value
        long currentUserId = 1;

        // Save or retrieve doctor and get the doctorId
        long doctorId = dbHelper.saveOrGetDoctor(doctorName);

        // Save the appointment
        Appointment appointment = new Appointment(currentUserId, doctorId, date, time, diagnosis, reason);
        dbHelper.saveAppointment(appointment);
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, month1, dayOfMonth) -> dateEditText.setText(year1 + "-" + (month1 + 1) + "-" + dayOfMonth),
                year, month, day);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute1) -> timeEditText.setText(hourOfDay + ":" + minute1),
                hour, minute, true);
        timePickerDialog.show();
    }
}
