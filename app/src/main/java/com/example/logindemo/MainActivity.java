package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private TextView info;
    private Button login;
    private int counter=5;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUIViews();



    }

    private void setUIViews(){
        name=(EditText)findViewById(R.id.etName);
        password=(EditText)findViewById(R.id.etPassword);
        info=(TextView)findViewById(R.id.tvInfo);
        login=(Button)findViewById(R.id.btnLogin);

        info.setText("Number of attempts remaining: 5");
    }



    public void startDBApp(View view)
    {
        new dbManager(this);
        startActivity(new Intent(this,ThirdActivity.class));
    }

    public void validateUser(View view)
    {
        dbManager db=new dbManager(this);
        if(db.validate(name.getText().toString(),password.getText().toString()))
        {
            if(db.validateUser(name.getText().toString(),password.getText().toString()))
            {

                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("name", name.getText().toString());
                Log.d("Name",name.getText().toString());
                intent.putExtra("password",password.getText().toString());
                Log.d("Password",password.getText().toString());
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this,"Enter valid Username and Password!!", Toast.LENGTH_SHORT).show();

                counter--;
                info.setText("Number of attempts remaining: "+String.valueOf(counter));

                if(counter==0)
                {
                    login.setEnabled(false);
                }
            }
        }
        else
        {
            Toast.makeText(this,"Please enter all details", Toast.LENGTH_LONG).show();
            counter--;
            info.setText("Number of attempts remaining: "+String.valueOf(counter));

            if(counter==0)
            {
                login.setEnabled(false);
            }
        }
    }
}
