package com.example.kolejtundrismail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main11Activity extends AppCompatActivity {

    EditText Title, Name, Matric, Email, Expertise;
    Button submit;
    Spinner Position;
    private ImageButton Logout;
    private FirebaseAuth mAuth;
    String A, B, C, D, E, F;
    static String queryDate;
    //public String keyLc;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference title = database.getReference("Title");
    DatabaseReference name = database.getReference("Name");
    DatabaseReference matric = database.getReference("Matric");
    DatabaseReference email = database.getReference("Email");
    DatabaseReference position = database.getReference("Position");
    DatabaseReference expertise = database.getReference("Expertise");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        Title = findViewById(R.id.einput);
        Name = findViewById(R.id.finput);
        Matric = findViewById(R.id.minput);
        Email = findViewById(R.id.eeinput);
        Position = findViewById(R.id.pinput);
        Expertise = findViewById(R.id.last);
        submit = findViewById(R.id.submit);

        Logout = (ImageButton) findViewById(R.id.btnHome);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(Main11Activity.this,MainActivity.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            String date = new SimpleDateFormat("yyyymmdd", Locale.getDefault()).format(new Date());
            @Override
            public void onClick(View view) {

                A = Title.getText().toString();
                B = Name.getText().toString();
                C = Matric.getText().toString();
                D = Email.getText().toString();
                E = Position.getSelectedItem().toString();
                F = Expertise.getText().toString();
                queryDate = date+C;

                DatabaseReference DB = database.getInstance().getReference().child("crew").child(queryDate);

                DB.child("Title").setValue(A);
                DB.child("Name").setValue(B);
                DB.child("Matric").setValue(C);
                DB.child("Email").setValue(D);
                DB.child("Position").setValue(E);
                DB.child("Expertise").setValue(F);

            }
        });
    }
}
