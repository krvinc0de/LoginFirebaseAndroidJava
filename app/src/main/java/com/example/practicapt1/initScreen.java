package com.example.practicapt1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

public class initScreen extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText register_email, register_password;
    private Button register;
    private TextView navigation_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_screen);

        auth = FirebaseAuth.getInstance();
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        register = findViewById(R.id.register);
        navigation_to_login = findViewById(R.id.navigation_to_login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = register_email.getText().toString().trim();
                String pass = register_password.getText().toString().trim();
                if (user.isEmpty()){
                    register_email.setError("Email vacio");
                } else if (pass.isEmpty()) {
                    register_password.setError("conta vacia");
                } else {
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(initScreen.this, " correcto", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(initScreen.this, loginScreen.class));
                            } else {
                                Toast.makeText(initScreen.this, " incorrecto" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        navigation_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(initScreen.this, loginScreen.class));
            }
        });

    }
}