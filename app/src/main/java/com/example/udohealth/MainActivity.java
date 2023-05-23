package com.example.udohealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;

import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView avatarImage = findViewById(R.id.avatar_image);
        avatarImage.setOnClickListener(v -> openMenuActivity());

        FloatingActionButton fabMain = findViewById(R.id.fab_main);
        fabMain.setOnClickListener(v -> showFabMenu(v));

        DBHelper dbHelper = new DBHelper(this);

        // Get the list of all medications from the database
        List<Medication> medications = dbHelper.getAllMedications();
        Log.d("Medications", "Size: " + medications.size());

        // Initialize the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // Initialize the MedicationSearchAdapter
        MedicationSearchAdapter medicationSearchAdapter = new MedicationSearchAdapter(medications);

        // Set the adapter on the RecyclerView
        recyclerView.setAdapter(medicationSearchAdapter);
        recyclerView.setVisibility(View.VISIBLE);


        // Set a LayoutManager on the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Find your SearchView by id
        androidx.appcompat.widget.SearchView searchBar = findViewById(R.id.search_view);

        // Set an OnQueryTextListener on the SearchView
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // For your use case, you probably don't need to do anything here, so just return false.
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // This method is called every time the text in the SearchView changes.
                // Here, you want to update the data in your MedicationSearchAdapter based on the new text.

                // Filter the medications based on the new text
                medicationSearchAdapter.getFilter().filter(newText);

                // Return false because you're not handling this event fully; you're just using it as a signal
                // to update your data.
                return false;
            }
        });
    }

    private void openMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void showFabMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.fab_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.new_appointment) {
                // Handle New Appointment click
                Intent intent = new Intent(MainActivity.this, NewAppointmentActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.new_medication) {
                // Handle New Medication click
                Intent intent = new Intent(MainActivity.this, NewMedicationActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.new_symptom) {
                // Handle New Symptom click
                Intent intent = new Intent(MainActivity.this, NewSymptomActivity.class);
                startActivity(intent);
            } else {
                return false;
            }
            return true;
        });

        popupMenu.show();
    }

}
