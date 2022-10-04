package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText et_email,et_password;
    private Button btn_login;
   
    private String email,password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_email = findViewById(R.id.edittext_email);
        et_password = findViewById(R.id.edittext_password);
        btn_login = findViewById(R.id.login_btn);
        mAuth = FirebaseAuth.getInstance();

        onClickOnSignIn();
    }

    /**
     * Get Sign in Credentials
     */
    public void getData(){
        email = et_email.getText().toString();
        password = et_password.getText().toString();
    }

    /**
     * On click on  Sign In button
     */
    public void onClickOnSignIn(){
        btn_login.setOnClickListener(this::onClick);
    }

    /**
     * For Testing Purpose Only
     * @return all user credentials
     */
    @Override
    public String toString() {
        return "Login{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                loginEmailPass();
                break;
        }
    }

    /**
     * Login Process
     */
    private void loginEmailPass() {
        String email = et_email.getText().toString();
        String pass = et_password.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
            Toast.makeText(Login.this, "Enter Email or Password...", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(Login.this,Home.class));
                    }
                    else
                        Toast.makeText(Login.this, "Login Failed...", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}