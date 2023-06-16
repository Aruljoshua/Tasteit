package com.example.tasteit;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.ImageView;


import com.google.firebase.auth.FirebaseAuth;


public class Signup extends AppCompatActivity {
//    private EditText firstNameEditText,  emailEditText, passwordEditText;
//    private Button signupButton;
//
//

    private ImageView btnBack;
//
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        // Initialize Firebase Firestore
//
//
        mAuth = FirebaseAuth.getInstance();
//
//        // Get references to views
//        firstNameEditText = findViewById(R.id.signup_fullname);
//
//        emailEditText = findViewById(R.id.signup_email);
//        passwordEditText = findViewById(R.id.signup_password);
//        signupButton = findViewById(R.id.signup_next_button);
//
//
//
//
//

        btnBack=findViewById(R.id.signup_back_button);
//
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup.this,Splash_Login.class);
                startActivity(intent);
            }
        });



        // Set click listener for signup button
//        signupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Get user input from EditText fields
//
////                String fullname = firstNameEditText.getText().toString().trim();
//
//                String email = emailEditText.getText().toString().trim();
//                String password = passwordEditText.getText().toString().trim();
//
//
//
//
//
//
//
////                // Create a map with user data
////                Map<String, Object> user = new HashMap<>();
////                user.put("firstName", firstName);
////                user.put("secondName", secondName);
////                user.put("email", email);
////                user.put("password", password);
//
//
//                if (TextUtils.isEmpty(email)){
//                    emailEditText.setError("Email cannot be empty");
//                    emailEditText.requestFocus();
//                }else if (TextUtils.isEmpty(password)){
//                    passwordEditText.setError("Password cannot be empty");
//                    passwordEditText.requestFocus();
//                }else {
//                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                Toast.makeText(Signup.this, "User registered successfully", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(Signup.this, MainActivity.class));
//                            } else {
//                                Toast.makeText(Signup.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//
//
//                }
//            }
//        });
    }
}