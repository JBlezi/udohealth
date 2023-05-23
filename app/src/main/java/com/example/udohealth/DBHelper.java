package com.example.udohealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    private DBHelper dbHelper;
    private static final String DATABASE_NAME = "myDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String MEDICATIONS_TABLE = "medications";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_ID = "userId";
    public static final String COLUMN_DOCTOR_ID = "doctorId";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CONSUMPTION_TIME = "consumption_time";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DOSAGE = "dosage";
    public static final String COLUMN_BRAND_NAME = "brand_name";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "email TEXT," +
                "password TEXT," +
                "phone TEXT," +
                "address TEXT)";

        String CREATE_DOCTORS_TABLE = "CREATE TABLE doctors (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "specialization TEXT," +
                "email TEXT," +
                "phone TEXT," +
                "address TEXT)";

        String CREATE_SYMPTOMS_TABLE = "CREATE TABLE symptoms (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "description TEXT," +
                "start_time TEXT," +
                "duration INTEGER," +
                "user_id INTEGER," +
                "FOREIGN KEY(user_id) REFERENCES users(id))";

        String CREATE_MEDICATIONS_TABLE = "CREATE TABLE medications (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "description TEXT," +
                "brand_name TEXT," +
                "dosage TEXT," +
                "consumption_time TEXT," +
                "user_id INTEGER," +
                "doctor_id INTEGER," +
                "FOREIGN KEY(user_id) REFERENCES users(id)," +
                "FOREIGN KEY(doctor_id) REFERENCES doctors(id))";

        String CREATE_APPOINTMENTS_TABLE = "CREATE TABLE appointments (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT," +
                "time TEXT," +
                "diagnosis TEXT," +
                "reason_of_visit TEXT," +
                "user_id INTEGER," +
                "doctor_id INTEGER," +
                "FOREIGN KEY(user_id) REFERENCES users(id)," +
                "FOREIGN KEY(doctor_id) REFERENCES doctors(id))";

        String CREATE_FILES_TABLE = "CREATE TABLE files (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "file BLOB," +
                "user_id INTEGER," +
                "doctor_id INTEGER," +
                "appointment_id INTEGER," +
                "FOREIGN KEY(user_id) REFERENCES users(id)," +
                "FOREIGN KEY(doctor_id) REFERENCES doctors(id)," +
                "FOREIGN KEY(appointment_id) REFERENCES appointments(id))";

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_DOCTORS_TABLE);
        db.execSQL(CREATE_SYMPTOMS_TABLE);
        db.execSQL(CREATE_MEDICATIONS_TABLE);
        db.execSQL(CREATE_APPOINTMENTS_TABLE);
        db.execSQL(CREATE_FILES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS doctors");
        db.execSQL("DROP TABLE IF EXISTS symptoms");
        db.execSQL("DROP TABLE IF EXISTS medications");
        db.execSQL("DROP TABLE IF EXISTS appointments");
        db.execSQL("DROP TABLE IF EXISTS files");

        // Create tables again
        onCreate(db);
    }

    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("email", user.getEmail());
        contentValues.put("password", user.getPassword());
        contentValues.put("phone", user.getPhone());
        contentValues.put("address", user.getAddress());
        long id = db.insert("users", null, contentValues);
        db.close();
        return id;
    }

    public long saveOrGetDoctor(String doctorName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("doctors", new String[]{"id", "name"},
                "name=?", new String[]{doctorName}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            long doctorId = cursor.getLong(0);
            cursor.close();
            return doctorId;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", doctorName);
        long doctorId = db.insert("doctors", null, contentValues);
        return doctorId;
    }

    public void saveAppointment(Appointment appointment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", appointment.getDate());
        contentValues.put("time", appointment.getTime());
        contentValues.put("diagnosis", appointment.getDiagnosis());
        contentValues.put("reason_of_visit", appointment.getReason());
        contentValues.put("user_id", appointment.getUserId());
        contentValues.put("doctor_id", appointment.getDoctorId());
        db.insert("appointments", null, contentValues);
        db.close();
    }

    public void saveMedication(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", medication.getName());
        contentValues.put("consumption_time", medication.getConsumptionTime());
        contentValues.put("brand_name", medication.getBrandName());
        contentValues.put("dosage", medication.getDosage());
        contentValues.put("user_id", medication.getUserId());
        contentValues.put("description", medication.getDescription());
        contentValues.put("doctor_id", medication.getDoctorId());
        db.insert("medications", null, contentValues);
        db.close();
    }

    public void saveSymptom(Symptom Symptom) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", Symptom.getName());
        contentValues.put("start_time", Symptom.getStartTime());
        contentValues.put("duration", Symptom.getDuration());
        contentValues.put("user_id", Symptom.getUserId());
        contentValues.put("description", Symptom.getDescription());
        db.insert("symptoms", null, contentValues);
        db.close();
    }

    public User getUser(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"id", "username", "email", "password", "phone", "address"},
                "id=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            User user = new User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
            cursor.close();
            return user;
        }
        return null;
    }

    public List<Medication> getAllMedications() {
        Log.d("DBHelper", "Getting all medications...");
        // List to store all the Medication objects
        List<Medication> medications = new ArrayList<>();

        // Get a readable database
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to select all records from the Medication table
        String selectQuery = "SELECT * FROM " + MEDICATIONS_TABLE;

        // Execute the query
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through the result set and create Medication objects
        if (cursor.moveToFirst()) {
            do {
                int userIdIndex = cursor.getColumnIndex(COLUMN_USER_ID);
                int doctorIdIndex = cursor.getColumnIndex(COLUMN_DOCTOR_ID);
                int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
                int consumptionTimeIndex = cursor.getColumnIndex(COLUMN_CONSUMPTION_TIME);
                int dosageIndex = cursor.getColumnIndex(COLUMN_DOSAGE);
                int brandNameIndex = cursor.getColumnIndex(COLUMN_BRAND_NAME);

                if (userIdIndex != -1 && doctorIdIndex != -1 && nameIndex != -1 && descriptionIndex != -1
                        && consumptionTimeIndex != -1 && dosageIndex != -1 && brandNameIndex != -1) {

                    long userId = cursor.getLong(userIdIndex);
                    long doctorId = cursor.getLong(doctorIdIndex);
                    String name = cursor.getString(nameIndex);
                    String description = cursor.getString(descriptionIndex);
                    String consumption_time = cursor.getString(consumptionTimeIndex);
                    String dosage = cursor.getString(dosageIndex);
                    String brand_name = cursor.getString(brandNameIndex);

                    // Create a new Medication object
                    Medication medication = new Medication(userId, doctorId, name, description, consumption_time, dosage, brand_name);

                    // Add the Medication object to the list
                    medications.add(medication);
                }
            } while (cursor.moveToNext());
        } else {
            Log.d("DBHelper", "No records found in the medications table.");
        }


        // Close the cursor and the database
        cursor.close();
        db.close();

        // Return the list of Medication objects
        return medications;
    }




}

