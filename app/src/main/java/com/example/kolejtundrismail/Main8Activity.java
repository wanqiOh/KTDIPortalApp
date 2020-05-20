package com.example.kolejtundrismail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main8Activity extends AppCompatActivity {

    private DatabaseReference cDatabase;
    private ArrayList<complaint> list = new ArrayList<>();
    private ListView listView;
    private ImageButton Logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_list);
        FirebaseApp.initializeApp(Main8Activity.this);
        listView = findViewById(R.id.listComplaint);

        cDatabase = FirebaseDatabase.getInstance().getReference().child("complaint");
        Logout = (ImageButton) findViewById(R.id.btnHome);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(Main8Activity.this,MainActivity.class));
            }
        });
        cDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    complaint CD = new complaint();

                    String name = ds.child("Name").getValue(String.class);
                    String phone = ds.child("Phone_Number").getValue(String.class);
                    String date = ds.child("Date").getValue(String.class);
                    String building = ds.child("Building").getValue(String.class);
                    String floor = ds.child("Floor").getValue(String.class);
                    String room = ds.child("Room").getValue(String.class);
                    String problem = ds.child("Problem Section").getValue(String.class);
                    String desc = ds.child("Description").getValue(String.class);
                    String status = ds.child("Status").getValue(String.class);

                    CD.setName(name);
                    CD.setPhone(phone);
                    CD.setDate(date);
                    CD.setBuilding(building);
                    CD.setFloor(floor);
                    CD.setRoom(room);
                    CD.setProblem(problem);
                    CD.setDesc(desc);
                    CD.setStatus(status);

                    list.add(CD);

                    ArrayAdapter<complaint> arrayAdapter = new ArrayAdapter<complaint>(Main8Activity.this, R.layout.activity_complaint_list1, list) {
                        public View getView(int position, View convertView, ViewGroup parent){
                            final complaint details = list.get(position);

                            if (convertView == null){
                                convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_complaint_list1, parent, false);
                            }

                            ((TextView) convertView.findViewById(R.id.name)).setText(details.getName());
                            ((TextView) convertView.findViewById(R.id.phone)).setText(details.getPhone());
                            ((TextView) convertView.findViewById(R.id.building)).setText(details.getBuilding());
                            ((TextView) convertView.findViewById(R.id.floor)).setText(details.getFloor());
                            ((TextView) convertView.findViewById(R.id.room)).setText(details.getRoom());
                            ((TextView) convertView.findViewById(R.id.problem)).setText(details.getProblem());
                            ((TextView) convertView.findViewById(R.id.desc)).setText(details.getDesc());
                            ((TextView) convertView.findViewById(R.id.date)).setText(details.getDate());
                            ((TextView) convertView.findViewById(R.id.status)).setText(details.getStatus());

                            Button button = convertView.findViewById(R.id.btnRemind);
                            button.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(Main8Activity.this, Main9Activity.class);
                                    startActivity(intent);
                                }
                            });
                            return convertView;
                        }
                    };
                    listView.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}
