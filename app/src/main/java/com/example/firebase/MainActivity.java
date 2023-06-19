package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    TextView regTex;
    EditText user,pass;
    Button login;
    FirebaseAuth users;
    FirebaseUser currentUser;
    private DatabaseReference fdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regTex=findViewById(R.id.regPage);
        user=findViewById(R.id.userLogin);
        pass=findViewById(R.id.password);
        login=findViewById(R.id.loginButton);

        users=FirebaseAuth.getInstance();
        currentUser=users.getCurrentUser();

        regTex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goReg=new Intent(getApplicationContext(), Register.class);
                startActivity(goReg);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth();
            }
        });

    }

    private void Auth() {
        String email=user.getText().toString();
        String password=pass.getText().toString();
    users.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
                sendProfile();
                Toast.makeText(MainActivity.this, "User Login Successfully", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, "Auth Error"+task.getException(), Toast.LENGTH_SHORT).show();
            }
        }
    });
    }

    private void sendProfile() {
        Intent userPro = new Intent(getApplicationContext(),UserProfile.class);
        userPro.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(userPro);
    }
}