package com.example.firebase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BottomDialog extends BottomSheetDialogFragment {

    TextView title,link,visit;
    ImageView close;
    String Url;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_bottom_dialog,container,false);

        title=view.findViewById(R.id.txt_title);
        link=view.findViewById(R.id.txt_link);
        visit=view.findViewById(R.id.visit);
        close=view.findViewById(R.id.close);

        title.setText(Url);

        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c=new Intent("android.intent.action.VIEW");
                c.setData(Uri.parse(Url));
                startActivity(c);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;

    }
    public void fetchUrl(String url){
        ExecutorService service= Executors.newSingleThreadExecutor();
        Handler handler=new Handler(Looper.getMainLooper());
        service.execute(new Runnable() {
            @Override
            public void run() {
                Url=url;
            }
        });
    }
}
