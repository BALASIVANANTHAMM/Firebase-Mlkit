package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    TextView loTex;
    EditText user,pass;
    Button regBut;
    FirebaseAuth reg;
    String emailPattern = "[a-zA-Z0-9]+.[a-zA-Z0-9]+@gmail.com";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loTex=findViewById(R.id.logPage);
        user=findViewById(R.id.userReg);
        pass=findViewById(R.id.passReg);
        regBut=findViewById(R.id.registerButton);

        reg=FirebaseAuth.getInstance();

        loTex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLogin();
            }
        });

        regBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user.getText().toString();
                String password = pass.getText().toString();
                if (email.trim().length() == 0 || password.trim().length() == 0) {
                    return;
                }else if (!email.matches(emailPattern))
                {
                    user.setError("Enter Valid Email");
                }
                else if(password.length()<=8)
                {
                    pass.setError("Enter Valid Password");
                }
                reg.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        goLogin();
                        Toast.makeText(Register.this, "User Create Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void goLogin() {
        Intent goLog =new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goLog);
    }
}