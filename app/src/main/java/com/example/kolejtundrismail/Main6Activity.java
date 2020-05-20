package com.example.kolejtundrismail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class Main6Activity extends AppCompatActivity implements View.OnClickListener {
    private CardView card_View1, card_View2;
    private ImageButton Logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        card_View1 = (CardView) findViewById(R.id.cardView1);
        card_View2 = (CardView) findViewById(R.id.cardView2);

        try{card_View1.setOnClickListener(this);
            card_View2.setOnClickListener(this);}
        catch(NullPointerException ingored){}

        Logout = (ImageButton) findViewById(R.id.btnHome);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(Main6Activity.this,MainActivity.class));
            }
        });


    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.cardView1 : i = new Intent(this, Main7Activity.class); startActivity(i); break;
            case R.id.cardView2 : i = new Intent (this, Main8Activity.class); startActivity(i); break;
            default: break;
        }

    }
}
