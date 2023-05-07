package com.example.university;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.university.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
Button btnSignin,btnRegister;
FirebaseAuth auth;
FirebaseDatabase db;
DatabaseReference users;
RelativeLayout root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignin = findViewById(R.id.btnSignin);
        btnRegister = findViewById(R.id.btnRegister);
        auth = FirebaseAuth.getInstance();
        root = findViewById(R.id.root_element);
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWindow();
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSigninWindow();
            }
        });

    }


    private void showSigninWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Введите  данные для входа");
        LayoutInflater inflater = LayoutInflater.from(this);
        View Signin_window = inflater.inflate(R.layout.sign_in_window,null);
        dialog.setView(Signin_window);
        final EditText email = Signin_window.findViewById(R.id.emailField);
        final EditText password = Signin_window.findViewById(R.id.passwordField);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root," Введите вашу почту",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(password.getText().toString().length()<3){
                    Snackbar.make(root," Введите пароль,который более 3 символов",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                    startActivity(new Intent(MainActivity.this,Menu.class));
                    finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(root,"Ошибка авторизации. " + e.getMessage(),Snackbar.LENGTH_SHORT).show();
                    }
                });



            }
        });
        dialog.show();


    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите все данные для регистрации");
        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register,null);
        dialog.setView(register_window);
        final EditText email = register_window.findViewById(R.id.emailField);
        final EditText password = register_window.findViewById(R.id.passwordField);
        final EditText name = register_window.findViewById(R.id.nameField);
        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

    dialog.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            if(TextUtils.isEmpty(email.getText().toString())){
                Snackbar.make(root," Введите вашу почту",Snackbar.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(name.getText().toString())){
                Snackbar.make(root," Введите ваше имя",Snackbar.LENGTH_SHORT).show();
                return;
            }
            if(password.getText().toString().length()<3){
                Snackbar.make(root," Введите пароль, который длинее 3 символов",Snackbar.LENGTH_SHORT).show();
                return;
            }
            // Регистрация пользователя
            auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                User user = new User();
                user.setEmail(email.getText().toString());
                user.setName(name.getText().toString());
                user.setPassword(password.getText().toString());
                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Snackbar.make(root,"Пользователь добавлен!", Snackbar.LENGTH_SHORT).show();
                    }
                });
                }
            });
        }
    });
    dialog.show();

    }
}