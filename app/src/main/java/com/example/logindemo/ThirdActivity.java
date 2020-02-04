package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText name;
    private EditText age;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setUIViews();

    }

    private void setUIViews() {
        name = findViewById(R.id.etName);
        age = findViewById(R.id.etAge);
        username = findViewById(R.id.etUserName);
        password = findViewById(R.id.etPassword);
        Button register = findViewById(R.id.btnRegister);
        Button login = findViewById(R.id.btnLogin);
    }

    private Boolean validate() {
        boolean result = false;

        String Name = name.getText().toString();
        String Age = age.getText().toString();
        String Username = username.getText().toString();
        String Password = password.getText().toString();

        if (Name.isEmpty() || Age.isEmpty() || Username.isEmpty() || Password.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }

    public void goBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void addRecord(View view) {
        if (validate()) {
            dbManager db = new dbManager(this);
            String res = db.addUser(name.getText().toString(), age.getText().toString(), username.getText().toString(), password.getText().toString());

            Toast.makeText(this, res, Toast.LENGTH_LONG).show();

            name.setText("");
            age.setText("");
            username.setText("");
            password.setText("");
        }
    }
}
