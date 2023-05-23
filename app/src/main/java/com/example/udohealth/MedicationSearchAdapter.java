package com.example.udohealth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MedicationSearchAdapter extends RecyclerView.Adapter<MedicationSearchAdapter.MedicationViewHolder> implements Filterable {

    private List<Medication> medications; // Full list of medications
    private List<Medication> filteredMedications; // Medications after filtering

    public MedicationSearchAdapter(List<Medication> medications) {
        this.medications = medications;
        this.filteredMedications = medications;
    }

    @NonNull
    @Override
    public MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_suggestion_item, parent, false);
        return new MedicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationViewHolder holder, int position) {
        Medication medication = filteredMedications.get(position);
        holder.bind(medication);
    }

    @Override
    public int getItemCount() {
        return filteredMedications.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchTerm = constraint.toString().toLowerCase(Locale.getDefault());
                if (searchTerm.isEmpty()) {
                    filteredMedications = medications;
                } else {
                    List<Medication> tempFilteredList = new ArrayList<>();
                    for (Medication medication : medications) {
                        if (medication.getName().toLowerCase(Locale.getDefault()).contains(searchTerm)) {
                            tempFilteredList.add(medication);
                        }
                    }
                    filteredMedications = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredMedications;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredMedications = (List<Medication>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    class MedicationViewHolder extends RecyclerView.ViewHolder {

        TextView medicationName;

        public MedicationViewHolder(@NonNull View itemView) {
            super(itemView);
            medicationName = itemView.findViewById(R.id.suggestion_text);
        }

        public void bind(Medication medication) {
            medicationName.setText(medication.getName());
        }
    }
}

