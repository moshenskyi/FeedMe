package com.example.nazariy.places.presentation.sign_in;

import android.content.Intent;
import android.os.Bundle;

import com.example.nazariy.places.R;
import com.example.nazariy.places.presentation.main.view.MainActivity;
import com.example.nazariy.places.presentation.sign_in.google.GoogleSignInMethod;
import com.google.android.gms.common.SignInButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    public static final int GOOGLE_REQUEST_CODE = 789;

    private GoogleSignInMethod googleSignInMethod;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        googleSignInMethod = new GoogleSignInMethod(this);

        if (googleSignInMethod.isSignedIn()) {
            MainActivity.start(this, googleSignInMethod.getName());
        }

        SignInButton btnGoogleSignIn = findViewById(R.id.btn_google_sign_in);
        btnGoogleSignIn.setOnClickListener(view -> signWithGoogle());
    }

    private void signWithGoogle() {
        googleSignInMethod.init();
        if (!googleSignInMethod.isSignedIn()) {
            startActivityForResult(googleSignInMethod.getSignIntent(), GOOGLE_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_REQUEST_CODE && resultCode == RESULT_OK) {
            MainActivity.start(this, googleSignInMethod.getName());
        }
    }
}
