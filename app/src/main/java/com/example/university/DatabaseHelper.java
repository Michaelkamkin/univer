package com.example.university;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    public static Task<List<String>> getNames() {
        FirebaseFirestore db = DatabaseUtils.getFirestore();
        CollectionReference collection = db.collection("Itmo");
        return collection.get().continueWith(task -> {
            List<String> names = new ArrayList<>();
            for (QueryDocumentSnapshot document : task.getResult()) {
                String name = document.getString("name");
                names.add(name);
            }
            return names;
        });
    }
}