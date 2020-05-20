package com.example.kolejtundrismail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Main9Activity extends AppCompatActivity {

    private EditText send_email;
    private EditText send_subject;
    private EditText send_msg;
    private ImageButton Logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_list2);

        send_email = findViewById(R.id.toEmail);
        send_subject = findViewById(R.id.toSubject);
        send_msg = findViewById(R.id.toMsg);

        Button buttonSend = findViewById(R.id.btnSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });

        Logout = (ImageButton) findViewById(R.id.btnHome);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(Main9Activity.this,MainActivity.class));
            }
        });
    }

    private void sendMail() {
        String recipientList = send_email.getText().toString();
        String[] recipients = recipientList.split( ",");
        //appdev855@gmail.com

        String subject = send_subject.getText().toString();
        String message = send_msg.getText().toString();

        Intent intent = new Intent (Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));

    }
}
