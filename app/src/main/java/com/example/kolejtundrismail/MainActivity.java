package com.example.kolejtundrismail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public static String pass_value,password, email;
    private EditText Email_in, Password_in;
    private Button Login;
    private int Counter = 3;
    private ProgressBar progressBar;

    private FirebaseAuth user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email_in = (EditText) findViewById(R.id.txtEmail);
        Password_in= (EditText) findViewById(R.id.txtPass);
        Login = (Button) findViewById(R.id.btnSignin);
        user = FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login(Email_in.getText().toString(), Password_in.getText().toString());
            }
        });
    }
    public void Login(String email, String password) {
        pass_value = password ;
        if (email.isEmpty()) {
            Email_in.setError("Email is required");
            Password_in.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email_in.setError("Please enter a valid email");
            Password_in.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            Password_in.setError("Password is required");
            Password_in.requestFocus();
            return;
        }

        if (password.length() < 3) {
            Password_in.setError("Minimum length of password should be 6");
            Password_in.requestFocus();
            return;
        }

        user.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success");

                    Intent nextPage = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(nextPage);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
                }
        });
    }
}
