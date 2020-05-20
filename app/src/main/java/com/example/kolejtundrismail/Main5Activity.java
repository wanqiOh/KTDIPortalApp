package com.example.kolejtundrismail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {

    private static String pass_value;
    private DatabaseReference mDatabase, m2Database ;
    private ArrayList<merit> list = new ArrayList<>() ;
    private ListView listView ;
    private ImageButton Logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        FirebaseApp.initializeApp(Main5Activity.this);
        listView = findViewById(R.id.listview);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
       mDatabase = FirebaseDatabase.getInstance().getReference().child(MainActivity.pass_value);
        m2Database = FirebaseDatabase.getInstance().getReference().child("merit").child(MainActivity.pass_value);

        Logout = (ImageButton) findViewById(R.id.btnHome);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(Main5Activity.this,MainActivity.class));
            }
        });

        m2Database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String tot_merit = dataSnapshot.child("total_merit").getValue(String.class) ;
                TextView totmerit = (TextView) findViewById(R.id.valuetot) ;
                try{totmerit.setText(tot_merit);}
                catch(NullPointerException ignored){}
                //Log.d("CREATION",tot_merit);
            };

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    merit UD = new merit();

                    String title = ds.child("title").getValue(String.class);
                    String merit = ds.child("merit_score").getValue(String.class);
                    String desc = ds.child("desc").getValue(String.class);

                    UD.setTitle(title);
                    UD.setMerit(merit);
                    UD.setDesc(desc);

                    list.add(UD);

                    ArrayAdapter<merit> arrayAdapter = new ArrayAdapter<merit>(Main5Activity.this, R.layout.listview1, list) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            final merit details = list.get(position);
                            if (convertView == null) {
                                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview1, parent, false);
                            }

                            ((TextView) convertView.findViewById(R.id.activity)).setText(details.getTitle());
                            ((TextView) convertView.findViewById(R.id.mark)).setText(details.getMerit());
                            ((TextView) convertView.findViewById(R.id.description)).setText(details.getDesc());

                            return convertView;
                        }
                    };
                    try{listView.setAdapter(arrayAdapter);}
                    catch(NullPointerException ignored){}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

