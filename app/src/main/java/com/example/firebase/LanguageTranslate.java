package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

public class LanguageTranslate extends AppCompatActivity {

    Spinner fromS,toS;
    EditText sourceText;
    Button btn;
    TextView tranceText;

    //spinner data

    String[] fromLang={"from","English","Arabic","Tamil","German","French","Hindi"};
    String[] toLang={"to","English","Arabic","Tamil","German","French","Hindi"};


    private static final int REQUEST_CODE=1;
    String languageCode, fromLanguageCode,toLanguageCode;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_translate);

        fromS=findViewById(R.id.idFromSpinner);
        toS=findViewById(R.id.idToSpinner);
        sourceText=findViewById(R.id.idEdtSource);
        btn=findViewById(R.id.idTranslateButton);
        tranceText=findViewById(R.id.idTranslatedTV);

        //spinner1

        fromS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromLanguageCode=GetLanguageCode(fromLang[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter fromAdapter=new ArrayAdapter<>(this,R.layout.spinner_item,fromLang);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromS.setAdapter(fromAdapter);

        // spinner2
        toS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toLanguageCode=GetLanguageCode(toLang[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter toAdapter=new ArrayAdapter<>(this,R.layout.spinner_item,toLang);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toS.setAdapter(toAdapter);


        //translation
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tranceText.setText("");

                if (sourceText.getText().toString().isEmpty()){
                    Toast.makeText(LanguageTranslate.this, "Please Enter Your Text...", Toast.LENGTH_SHORT).show();
                } else if (fromLanguageCode.isEmpty()) {
                    Toast.makeText(LanguageTranslate.this, "Please Select Source Language", Toast.LENGTH_SHORT).show();
                } else if (toLanguageCode.isEmpty()) {
                    Toast.makeText(LanguageTranslate.this, "Please Select The Target Language", Toast.LENGTH_SHORT).show();
                }else {
                    TranslateText(fromLanguageCode,toLanguageCode,sourceText.getText().toString());
                }
            }
        });
    }

    private void TranslateText(String fromLanguageCode, String toLanguageCode, String src) {
        tranceText.setText("Downloading Language Model");

        try {
            TranslatorOptions options = new TranslatorOptions.Builder().setSourceLanguage(fromLanguageCode)
                    .setTargetLanguage(toLanguageCode).build();

            Translator translator = Translation.getClient(options);

            DownloadConditions conditions=new DownloadConditions.Builder().build();

            translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    tranceText.setText("Translating...");

                    translator.translate(src).addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            tranceText.setText(s);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LanguageTranslate.this, "Failed to translate", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LanguageTranslate.this, "fail to download the language", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //"English","Arabic","Tamil","German","French","Hindi"
    private String GetLanguageCode(String language) {
        String LanguageCode;

        switch (language){
            case "English":
                LanguageCode= TranslateLanguage.ENGLISH;
                break;
            case "Arabic":
                LanguageCode=TranslateLanguage.ARABIC;
                break;
            case "Tamil":
                LanguageCode=TranslateLanguage.TAMIL;
                break;
            case "German":
                LanguageCode=TranslateLanguage.GERMAN;
                break;
            case "French":
                LanguageCode=TranslateLanguage.FRENCH;
                break;
            case "Hindi":
                LanguageCode=TranslateLanguage.HINDI;
                break;
            default:
                LanguageCode="";
        }
        return LanguageCode;
    }
}