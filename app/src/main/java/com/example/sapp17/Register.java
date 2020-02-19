package com.example.sapp17;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
EditText MFullname,MEmail,MPassword,MPhone;
Button MRegisterbtn;
TextView MLoginbtn;
FirebaseAuth firebaseAuth;
ProgressBar progressBar;
FirebaseFirestore firebaseFirestore;
String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    MFullname=findViewById(R.id.fullname);
    MEmail=findViewById(R.id.email);
    MPassword=findViewById(R.id.password);
    MPhone=findViewById(R.id.phone);
    MRegisterbtn=findViewById(R.id.loginbit);
    MLoginbtn=findViewById(R.id.creatText);
    firebaseFirestore=FirebaseFirestore.getInstance();
    firebaseAuth=FirebaseAuth.getInstance();
    progressBar=findViewById(R.id.progressBar);
    if(firebaseAuth.getCurrentUser()!=null)
    {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    MRegisterbtn .setOnClickListener(new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            final String email=MEmail.getText().toString().trim();
            String password=MPassword.getText().toString().trim();
            final String fullname=MFullname.getText().toString().trim();
            final String phone=MPhone.getText().toString().trim();

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
            if(phone.isEmpty() || phone.length() < 10){

                MPhone.setError("Enter a valid mobile");
                MPhone.requestFocus();
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                        userid=firebaseAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = firebaseFirestore.collection("users").document(userid);
                        Map<String,Object> user =new HashMap<>();
                        user.put("fullname",fullname);
                        user.put("email",email);
                        user.put("phone",phone);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG","Onsuccess:user profile is created for"+ userid);
                            }
                        });

                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(Register.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                    }
                }
            });
            Intent intent = new Intent(Register.this, VerifyPhoneActivity.class);
            intent.putExtra("email",email);
            intent.putExtra("password",password);
            intent.putExtra("mobile",phone);
            startActivity(intent);
        }
    });
        MLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });


    }
}
