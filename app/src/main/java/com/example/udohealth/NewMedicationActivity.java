package com.example.udohealth;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class NewMedicationActivity extends AppCompatActivity {
    private EditText nameEditText, consumptionTimeEditText, dosageEditText, brandNameEditText, doctorEditText, descriptionEditText;
    private Button submitButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medication);

        LinearLayout backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());


        dbHelper = new DBHelper(this);

        nameEditText = findViewById(R.id.name_edit_text);
        consumptionTimeEditText = findViewById(R.id.consumption_time_edit_text);
        dosageEditText = findViewById(R.id.dosage_edit_text);
        brandNameEditText = findViewById(R.id.brand_name_edit_text);
        doctorEditText = findViewById(R.id.doctor_edit_text);
        descriptionEditText = findViewById(R.id.description_edit_text);

        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm(v);
            }
        });

    }

    private void submitForm(View view) {
        Log.d("NewMedicationActivity", "submitForm called");
        String name = nameEditText.getText().toString();
        String consumption_time = consumptionTimeEditText.getText().toString();
        String dosage = dosageEditText.getText().toString();
        String brand_name = brandNameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String doctorName = doctorEditText.getText().toString();

        // Assuming you have a current user, replace "currentUserId" with the actual value
        long currentUserId = 1;

        // Save or retrieve doctor and get the doctorId
        long doctorId = dbHelper.saveOrGetDoctor(doctorName);

        // Save the medication
        Medication medication = new Medication(currentUserId, doctorId, name, description, consumption_time, dosage, brand_name);
        dbHelper.saveMedication(medication);
    }
}
