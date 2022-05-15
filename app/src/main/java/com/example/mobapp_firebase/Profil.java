package com.example.mobapp_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Profil extends AppCompatActivity {
    EditText etProfEmail,etProfPass,etProfName,etProfSuel,etProfCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);


    }
}