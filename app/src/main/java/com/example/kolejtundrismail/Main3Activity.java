package com.example.kolejtundrismail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ArrayList<activity> list = new ArrayList<>();
    private RecyclerView listView;
    private SearchView search;
    private ImageButton Logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        FirebaseApp.initializeApp(Main3Activity.this);
        listView = findViewById(R.id.listViewActivity);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        listView.setLayoutManager(manager);
        listView.setHasFixedSize(true);
        search = findViewById(R.id.searchBar);
        Logout = (ImageButton) findViewById(R.id.btnHome);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Activity");

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(Main3Activity.this,MainActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mDatabase != null){
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        list.clear();
                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                            activity a = new activity();
                            String var = ds.getKey();
                            String name = ds.child("Name").getValue(String.class);
                            String date = ds.child("Date").getValue(String.class);
                            String merit = ds.child("Merit").getValue(String.class);
                            String link = ds.child("Link").getValue(String.class);
                            String issue_by = ds.child("Issued_by").getValue(String.class);
                            String place = ds.child("Place").getValue(String.class);
                            String pic = ds.child("PIC").getValue(String.class);
                            String phone = ds.child("Phone_no").getValue(String.class);

                            a.setName(name);
                            a.setDate(date);
                            a.setMerit(merit);
                            a.setLink(link);
                            a.setIssued_by(issue_by);
                            a.setPlace(place);
                            a.setPIC(pic);
                            a.setPhone_no(phone);
                            a.setId(var);

                            list.add(a);
                        }
                        AdapterClass adapter = new AdapterClass(list);
                        listView.setAdapter(adapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Main3Activity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(search != null){
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }

    private void search(String str){
        ArrayList<activity> myList = new ArrayList<>();
        for(activity object : list){
            if(object.getName().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }

            else if(object.getDate().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }

            else if(object.getPlace().toUpperCase().contains(str.toUpperCase())){
                myList.add(object);
            }

            else if(object.getMerit().toUpperCase().contains(str.toUpperCase())){
                myList.add(object);
            }
        }
        AdapterClass adapterClass = new AdapterClass(myList);
        listView.setAdapter(adapterClass);
    }

}