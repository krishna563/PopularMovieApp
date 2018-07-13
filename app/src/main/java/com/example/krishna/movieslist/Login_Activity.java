/*
package com.example.krishna.movieslist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener{

public EditText edt_Username,edt_Password;
public Button Btn_Login;
public CardView Card_View;
SharedPreferences sharedpreferences;

    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Password = "passKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         com.facebook.stetho.Stetho.initializeWithDefaults(this);
         okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder()
    .addNetworkInterceptor(new com.facebook.stetho.okhttp3.StethoInterceptor())
    .build();

        edt_Username = (EditText) findViewById(R.id.input_name);
        edt_Password = (EditText) findViewById(R.id.input_password);

        edt_Username.setText("edt_Username");
    //    edt_Username.setSelection(edt_Username.getText().length());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                edt_Username.setSelection(edt_Username.getText().length());
            }
        },100);
        Btn_Login = (Button) findViewById(R.id.btn_login);


        Btn_Login.setOnClickListener(this);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            edt_Username.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Password)) {
            edt_Password.setText(sharedpreferences.getString(Password, ""));

        }

    }

    public void Save(View view) {
        String n = edt_Username.getText().toString();
        String e = edt_Password.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Password, e);
        editor.commit();
    }

    public void clear(View view) {
        edt_Username = (EditText) findViewById(R.id.input_name);
        edt_Password = (EditText) findViewById(R.id.input_password);
        edt_Username.setText("");
        edt_Password.setText("");

    }

    public void Get(View view) {
        edt_Username = (EditText) findViewById(R.id.input_name);
        edt_Password = (EditText) findViewById(R.id.input_password);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            edt_Username.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Password)) {
            edt_Password.setText(sharedpreferences.getString(Password, ""));

        }
    }

    @Override
    public void onClick(View view) {
        if (!validate()) {

            Toast.makeText(Login_Activity.this, "Success..!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(Login_Activity.this, MovieList_Activity.class);
            startActivity(i);
            finish();

        }

            String n = edt_Username.getText().toString();
            String e = edt_Password.getText().toString();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(Name, n);
            editor.putString(Password, e);
            editor.commit();

    }

        private boolean validate() {
            if (edt_Username.getText().toString().trim().length() <= 0) {
                Toast.makeText(Login_Activity.this, "Please Enter User Name", Toast.LENGTH_LONG).show();
                return true;
            } else if (edt_Password.getText().toString().trim().length() <= 0) {
                Toast.makeText(Login_Activity.this, "Please Enter password", Toast.LENGTH_LONG).show();
                return true;
            }
            return false;
        }
}
*/
