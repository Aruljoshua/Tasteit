package com.example.tasteit.Loading_Animation;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.tasteit.R;

public class NoInternetDiaload extends Dialog {
    public NoInternetDiaload(@NonNull Context context){
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_diaload);
//        final AppCompatButton retryButton=findViewById(R.id.retry_button);
//        retryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
    }
}