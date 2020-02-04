package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private TextView info;
    private TextView info2;

    ArrayList<String> listitem;
    ArrayAdapter adapter;
    ListView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        info = (TextView) findViewById(R.id.tvInfo2);
        info2 = (TextView) findViewById(R.id.tvInfo4);
        listitem = new ArrayList<>();


        Intent intent = getIntent();
        String username = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");

        viewData(username, password);


    }

    public void viewData(String username, String password) {
        dbManager db = new dbManager(this);
        String str = db.viewData(username, password);
        Log.d("data", str);
       // info.setText(str);
    }
}
