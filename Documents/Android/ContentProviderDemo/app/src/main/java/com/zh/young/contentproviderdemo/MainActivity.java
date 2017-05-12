package com.zh.young.contentproviderdemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 本demo用来学习ContentProvider
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver resolver = getContentResolver();
        new Uri.Builder()
                .scheme("content")
                .authority("")
        Cursor query = resolver.query(uri, null, null, null, null);

    }
}
