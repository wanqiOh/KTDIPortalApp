package com.example.kolejtundrismail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main7Activity extends AppCompatActivity {

    EditText nameC, phoneC, roomC, descC;
    CalendarView dateC;
    Spinner spinner1C, spinner2C, spinner3C;
    Button buttonC;
    private ImageButton Logout;
    private FirebaseAuth mAuth;
    //DatabaseReference ref;
    //ComplaintDetails complaint;
    String name;
    String phone_no;
    long date;
    String building;
    String floor;
    String room;
    String desc;
    String prob_type;
    static String queryDate;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference nameE = database.getReference("Name");
    DatabaseReference phoneE = database.getReference("Phone_No");
    DatabaseReference buldingE = database.getReference("Building");
    DatabaseReference floorE = database.getReference("Floor");
    DatabaseReference roomE = database.getReference("Room");
    DatabaseReference probE = database.getReference("Prob_Sec");
    DatabaseReference descE = database.getReference("Description");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_form);


        nameC = (EditText)findViewById(R.id.name);
        phoneC = (EditText)findViewById(R.id.phone);
        dateC = (CalendarView)findViewById(R.id.date);
        spinner1C = (Spinner)findViewById(R.id.spinner1);
        spinner2C = (Spinner)findViewById(R.id.spinner2);
        roomC = (EditText)findViewById(R.id.room);
        spinner3C = (Spinner)findViewById(R.id.spinner3);
        descC = (EditText)findViewById(R.id.desc);
        buttonC = (Button)findViewById(R.id.btnSubmit);
        //complaint = new ComplaintDetails();
        //ref = FirebaseDatabase.getInstance().getReference().child("complaint");

        Logout = (ImageButton) findViewById(R.id.btnHome);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(Main7Activity.this,MainActivity.class));
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            String date = new SimpleDateFormat("yyyymmdd", Locale.getDefault()).format(new Date());
            @Override
            public void onClick(View view) {

                name = nameC.getText().toString();
                phone_no = phoneC.getText().toString();
                building = spinner1C.getSelectedItem().toString();
                floor = spinner2C.getSelectedItem().toString();
                room = roomC.getText().toString();
                prob_type = spinner3C.getSelectedItem().toString();
                desc = descC.getText().toString();
                queryDate = date;

                DatabaseReference ref = database.getInstance().getReference().child("complaint").child(queryDate);
                ref.child ("Name").setValue(name);
                ref.child ("Phone_No").setValue(phone_no);
                ref.child ("Date").setValue("1 August 2019");
                ref.child ("Building").setValue(building);
                ref.child ("Floor").setValue(floor);
                ref.child ("Room").setValue(room);
                ref.child ("Prob_Sec").setValue(prob_type);
                ref.child ("Description").setValue(desc);
                ref.child ("Status").setValue("Complaint Submitted");
                Toast.makeText(Main7Activity.this,"Complaint Submitted", Toast.LENGTH_LONG).show();
            }
        });
    }
}
