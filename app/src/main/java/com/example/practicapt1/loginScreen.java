package com.example.practicapt1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;


public class loginScreen extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText login_email, login_password;
    private Button login;
    private TextView navigation_to_init;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        auth = FirebaseAuth.getInstance();
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        login = findViewById(R.id.login);
        navigation_to_init = findViewById(R.id.navigation_to_init);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = login_email.getText().toString().trim();
                String pass = login_password.getText().toString().trim();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(loginScreen.this, "Inicio de sesion bien", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(loginScreen.this, MainActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(loginScreen.this, "error en inicio de sesion", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        login_password.setError("Campo de password vacio");
                    }
                } else if (email.isEmpty()) {
                    login_email.setError("email vacio");
                } else {
                    login_email.setError("email incorrecto");
                }
            }
        });

        navigation_to_init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginScreen.this, initScreen.class));
            }
        });

    }
}