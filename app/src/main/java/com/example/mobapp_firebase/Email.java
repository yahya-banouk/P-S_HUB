package com.example.mobapp_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends AppCompatActivity {
    EditText et_to,et_dubject,et_message;
    Button bt_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);


        et_to  = findViewById(R.id.et_to);
        et_dubject = findViewById(R.id.et_subject);
        et_message = findViewById(R.id.et_message);
        bt_send = findViewById(R.id.bt_Send);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto: "+et_to.getText().toString()));
                intent.putExtra(intent.EXTRA_SUBJECT,et_dubject.getText().toString());
                intent.putExtra(intent.EXTRA_TEXT,et_message.getText().toString());
                startActivity(intent);
            }
        });

    }
}