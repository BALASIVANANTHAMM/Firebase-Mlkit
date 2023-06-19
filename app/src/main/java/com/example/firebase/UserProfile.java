package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserProfile extends AppCompatActivity {

    CardView im2tex,langT,qrSca,faceDet,soon1,soon2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        im2tex=findViewById(R.id.cardIm2T);
        langT=findViewById(R.id.cardLangTran);
        qrSca=findViewById(R.id.cardQrS);
        faceDet=findViewById(R.id.cardFaDet);
        soon1=findViewById(R.id.cardOcr);
        soon2=findViewById(R.id.cardSome);

        im2tex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2t=new Intent(getApplicationContext(), Image2Text.class);
                startActivity(i2t);
            }
        });
        langT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lgt=new Intent(getApplicationContext(), LanguageTranslate.class);
                startActivity(lgt);
            }
        });
        qrSca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qr=new Intent(getApplicationContext(), QrScanner.class);
                startActivity(qr);
            }
        });
        faceDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fd=new Intent(getApplicationContext(), FaceDetection.class);
                startActivity(fd);
            }
        });

    }
}