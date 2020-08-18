package com.example.syafiqalipah.weatherapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {


    private EditText Email;
    private EditText Password;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Email = (EditText)findViewById(R.id.userEmail);
        Password = (EditText)findViewById(R.id.userPassword);
        Button login = (Button) findViewById(R.id.btnLogin);
        TextView userRegistration = (TextView) findViewById(R.id.tvregister);


        firebaseAuth = FirebaseAuth.getInstance();           //connect to firebase
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){

            finish();
            startActivity(new Intent(Login.this , MainActivity.class));


        }

        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());



            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(Login.this , Registration.class));
            }
        });
    }



    private void validate(String userEmail , String userPassword){
        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Login process may take some time , please wait");     // set progress bar
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Toast.makeText(Login.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this , MainActivity.class));

                }else{
                    progressDialog.dismiss();

                    Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    }



