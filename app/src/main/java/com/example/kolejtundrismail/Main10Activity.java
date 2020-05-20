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
import android.widget.SearchView;
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

public class Main10Activity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ArrayList<inform> list = new ArrayList<>();
    private ListView listView;
    private SearchView search;
    private Button btn;
    private ImageButton Logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        FirebaseApp.initializeApp(Main10Activity.this);
        listView = findViewById(R.id.listView2);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Info");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    inform UD = new inform();

                    String title = ds.child("Title").getValue(String.class);
                    String desc = ds.child("Description").getValue(String.class);
                    String place = ds.child("Place").getValue(String.class);
                    String date = ds.child("Date").getValue(String.class);
                    String note = ds.child("Note").getValue(String.class);
                    String time = ds.child("Time").getValue(String.class);
                    String pic = ds.child("PIC").getValue(String.class);

                    UD.setTitle(title);
                    UD.setDate(date);
                    UD.setDescription(desc);
                    UD.setPlace(place);
                    UD.setNote(note);
                    UD.setTime(time);
                    UD.setPIC(pic);

                    list.add(UD);

                    ArrayAdapter<inform> arrayAdapter = new ArrayAdapter<inform>(Main10Activity.this, R.layout.list_view2, list) {
                        @Override


                        public View getView(int position, View convertView, ViewGroup parent) {
                            final inform details = list.get(position);
                            // Check if an existing view is being reused, otherwise inflate the view
                            if (convertView == null) {
                                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view2, parent, false);
                            }
                            try{
                            ((TextView) convertView.findViewById(R.id.title)).setText(details.getTitle());
                            ((TextView) convertView.findViewById(R.id.date)).setText(details.getDate());
                            ((TextView) convertView.findViewById(R.id.place)).setText(details.getPlace());
                            ((TextView) convertView.findViewById(R.id.pic)).setText(details.getPIC());
                            ((TextView) convertView.findViewById(R.id.desc)).setText(details.getDescription());
                            ((TextView) convertView.findViewById(R.id.time)).setText(details.getTime());
                            ((TextView) convertView.findViewById(R.id.note)).setText(details.getNote());

                            btn = findViewById(R.id.btn);
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(Main10Activity.this, Main11Activity.class);
                                    startActivity(intent);
                                }
                            });}
                            catch(NullPointerException ignored){}
                            // Return the completed view to render on screen
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

        Logout = (ImageButton) findViewById(R.id.btnHome);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(Main10Activity.this,MainActivity.class));
            }
        });
    }

}
