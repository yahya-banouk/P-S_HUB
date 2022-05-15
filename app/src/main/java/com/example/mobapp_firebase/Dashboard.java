package com.example.mobapp_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard extends AppCompatActivity {

    Button btnLogout1,btnPslist,btnProgram,btnprofil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnprofil = (Button)findViewById(R.id.btnprofil);
        btnPslist = (Button)findViewById(R.id.btnPslist);
        btnProgram = (Button)findViewById(R.id.btnProgram);
        btnLogout1 = (Button)findViewById(R.id.btnLogout1);



        btnProgram.setOnClickListener(view ->
        {
            startActivity(new Intent(this, PDF.class));
        });




        btnLogout1.setOnClickListener(view ->{


            startActivity(new Intent(this, LoginActivity.class));
        });
        btnPslist.setOnClickListener(view ->{


            startActivity(new Intent(this, MainActivity.class));
        });
//        btnprofil.setOnClickListener(view ->{
//            mAuth.signOut();
//            setContentView(R.layout.activity_login);
//            startActivity(new Intent(this, LoginActivity.class));
//        });
//        btnProgram.setOnClickListener(view ->{
//            mAuth.signOut();
//            setContentView(R.layout.activity_login);
//            startActivity(new Intent(this, LoginActivity.class));
//        });

    }

}