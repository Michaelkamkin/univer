package com.example.university;

import com.google.firebase.firestore.FirebaseFirestore;

public class DatabaseUtils {
    public static FirebaseFirestore getFirestore() {
        return FirebaseFirestore.getInstance();
    }
}

