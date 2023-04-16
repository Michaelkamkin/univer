package com.example.university;


import android.os.Bundle;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;



public class programms extends AppCompatActivity {

    private Button buttonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programms);

        buttonData = findViewById(R.id.button_data);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        String data = getIntent().getStringExtra("data");

        if (data.equals("Itmo")) {
            firestore.collection("Itmo")
                    .document("name")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String itmo = document.getString("Итмо");
                                buttonData.setText(itmo);
                            }
                        }
                    });
        }
    }
}