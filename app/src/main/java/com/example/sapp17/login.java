package com.example.sapp17;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText MEmail,MPassword;
    Button MLoginbtn;
    TextView MCreatBtn;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        MEmail=findViewById(R.id.email);
        MPassword=findViewById(R.id.password);
        MLoginbtn=findViewById(R.id.loginbit);
        MCreatBtn=findViewById(R.id.creatText);

        firebaseAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        MLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=MEmail.getText().toString().trim();
                String password=MPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    MEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    MPassword.setError("password is required");
                    return;
                }
                if(password.length()<6)
                {
                    MPassword.setError("pass must greater than 6 char");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(login.this, "login is successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else
                        {
                            Toast.makeText(login.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        MCreatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),slide1.class));
            }
        });




    }
}
