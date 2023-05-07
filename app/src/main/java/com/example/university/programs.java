package com.example.university;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class programs extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView mProgramsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Итмо");
        mProgramsList = (ListView) findViewById(R.id.programs_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_program, new ArrayList<String>());
        mProgramsList.setAdapter(adapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot programSnapshot : dataSnapshot.getChildren()) {
                    String programName = programSnapshot.getValue(String.class);
                    adapter.add(programName);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPrograms:onCancelled", databaseError.toException());
            }
        });
    }
}