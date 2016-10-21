package ru.keke.sovetotkritki;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Razrab extends AppCompatActivity {
    String contact = "androidicus@mail.ru";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razrab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", contact, null));//наш емейл адрес
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");//тема емейла
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");//текст емейла
                startActivity(Intent.createChooser(emailIntent, "Send email..."));//выводится при выборе отправщика
            }
        });
    }

}
