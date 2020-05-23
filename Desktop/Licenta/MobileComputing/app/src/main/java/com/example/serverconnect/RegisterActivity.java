package com.example.serverconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
public class RegisterActivity extends AppCompatActivity {

    EditText emailId;
    EditText password;
    EditText confirmPassword;
    Button btnSignIn;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth=FirebaseAuth.getInstance();
        emailId=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
        confirmPassword = findViewById((R.id.editText5));
        btnSignIn=findViewById(R.id.button2);
        tvSignIn =findViewById(R.id.textView);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailId.getText().toString();
                String pwd=password.getText().toString();
                String confPwd = confirmPassword.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter email");
                    emailId.requestFocus();
                }else if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                }else if(email.isEmpty() && pwd.isEmpty() && confPwd.isEmpty() ){
                    Toast.makeText(RegisterActivity.this,"Empty fields",Toast.LENGTH_SHORT).show();
                }
                if(!pwd.equals(confPwd)){
                    confirmPassword.setError("Your confirmation password doesn't match");
                }else if(!(email.isEmpty() && pwd.isEmpty() && confPwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"SignUp unsuccesful",Toast.LENGTH_SHORT).show();
                            }else{
                                startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegisterActivity.this,"Error occured",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
