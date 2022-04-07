package com.example.homeworkandroid.homework004;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework004.models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Objects;

public class LoginPage extends AppCompatActivity {

    Button btnSignIn, btnRegister;
    RelativeLayout root_element;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework004_activity_login_page);

        findView();

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        setListeners();

    }

    private void setListeners() {
        btnRegister.setOnClickListener(v -> showRegisterWindow());
        btnSignIn.setOnClickListener(view -> showSignInWindow());
    }

    private void showSignInWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.sign_in_window);
        dialog.setMessage(R.string.welcome_sign_in);

        LayoutInflater inflater = LayoutInflater.from(this);
        final View sign_in_window = inflater.inflate(R.layout.homework004_sign_in_window, null);

        dialog.setView(sign_in_window);

        final MaterialEditText email = (MaterialEditText) sign_in_window.findViewById(R.id.emailField);
        final MaterialEditText pass = (MaterialEditText) sign_in_window.findViewById(R.id.passField);

        dialog.setNegativeButton(R.string.cancel, (dialogInterface, which) -> dialogInterface.dismiss());

        dialog.setPositiveButton(R.string.signIn, (dialogInterface, which) -> {

            if(TextUtils.isEmpty(Objects.requireNonNull(email.getText()).toString()))
            {
                Snackbar.make(root_element, R.string.warnung_email, Snackbar.LENGTH_LONG).show();
                return;
            }

            if(Objects.requireNonNull(pass.getText()).toString().length()<5)
            {
                Snackbar.make(root_element, R.string.warnung_pass, Snackbar.LENGTH_LONG).show();
                return;
            }

            auth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
            .addOnSuccessListener(authResult -> {
                startActivity(new Intent(LoginPage.this,MainActivityHW004.class));
                finish();
            })
            .addOnFailureListener(e -> Snackbar.make(root_element, R.string.error_sign + e.getMessage(), Snackbar.LENGTH_LONG).show());
        });

        dialog.show();
    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.registration);
        dialog.setMessage(R.string.welcome_registration);

        LayoutInflater inflater = LayoutInflater.from(this);
        final View register_window = inflater.inflate(R.layout.homework004_register_window, null);

        dialog.setView(register_window);

        final MaterialEditText email = (MaterialEditText) register_window.findViewById(R.id.emailField);
        final MaterialEditText pass = (MaterialEditText) register_window.findViewById(R.id.passField);
        final MaterialEditText login = (MaterialEditText) register_window.findViewById(R.id.loginField);
        final MaterialEditText phone = (MaterialEditText) register_window.findViewById(R.id.phoneField);

        dialog.setNegativeButton(R.string.cancel, (dialogInterface, which) -> dialogInterface.dismiss());

        dialog.setPositiveButton(R.string.addLogin, (dialogInterface, which) -> {

            if(TextUtils.isEmpty(Objects.requireNonNull(email.getText()).toString()))
            {
                Snackbar.make(root_element, R.string.warnung_email, Snackbar.LENGTH_LONG).show();
                return;
            }

            if(TextUtils.isEmpty(Objects.requireNonNull(login.getText()).toString()))
            {
                Snackbar.make(root_element, R.string.warnung_login, Snackbar.LENGTH_LONG).show();
                return;
            }

            if(TextUtils.isEmpty(Objects.requireNonNull(phone.getText()).toString()))
            {
                Snackbar.make(root_element, R.string.warnung_phone, Snackbar.LENGTH_LONG).show();
                return;
            }

            if(Objects.requireNonNull(pass.getText()).toString().length()<5)
            {
                Snackbar.make(root_element, R.string.warnung_pass, Snackbar.LENGTH_LONG).show();
                return;
            }

            auth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                    .addOnSuccessListener(authResult -> {
                        User user = new User();
                        user.setEmail(email.getText().toString());
                        user.setPass(pass.getText().toString());
                        user.setLogin(login.getText().toString());
                        user.setPhone(phone.getText().toString());

                        users.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                .setValue(user)
                                .addOnSuccessListener(unused -> Snackbar.make(root_element, R.string.user_add, Snackbar.LENGTH_LONG).show());
                    });
        });

        dialog.show();
    }

    private void findView()
    {
        btnSignIn = findViewById(R.id.btnSignIn);
        btnRegister = findViewById(R.id.btnRegister);

        root_element = findViewById(R.id.root_element);
    }
}