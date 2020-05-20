package com.example.kolejtundrismail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity<user> extends AppCompatActivity {

    private ImageButton List;
    private ImageButton Logout;
    private ImageButton Merit;
    private ImageButton Inform;
    private ImageButton Report;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        List = (ImageButton) findViewById(R.id.btnList);
        Merit = (ImageButton) findViewById(R.id.btnMerit);
        Inform = (ImageButton) findViewById(R.id.btnInform);
        Report = (ImageButton) findViewById(R.id.btnReport);
        Logout = (ImageButton) findViewById(R.id.btnHome);

        List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
            }
        });

        Inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, Main10Activity.class);
                startActivity(intent);
            }
        });

        Merit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, Main5Activity.class);
                startActivity(intent);
            }
        });

        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, Main6Activity.class);
                startActivity(intent);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(Main2Activity.this,MainActivity.class));
            }
        });
    }
}
