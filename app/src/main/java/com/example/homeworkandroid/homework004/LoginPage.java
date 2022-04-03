package com.example.homeworkandroid.homework004;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework004.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginPage extends AppCompatActivity {

    Button btnSignIn, btnRegister;
    RelativeLayout root_element;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        findView();

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        setListeners();

    }

    private void setListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWindow();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignInWindow();
            }
        });
    }

    private void showSignInWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.sign_in_window);
        dialog.setMessage(R.string.welcome_sign_in);

        LayoutInflater inflater = LayoutInflater.from(this);
        final View sign_in_window = inflater.inflate(R.layout.sign_in_window, null);

        dialog.setView(sign_in_window);

        final MaterialEditText email = (MaterialEditText) sign_in_window.findViewById(R.id.emailField);
        final MaterialEditText pass = (MaterialEditText) sign_in_window.findViewById(R.id.passField);

        dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton(R.string.signIn, new DialogInterface.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                if(TextUtils.isEmpty(email.getText().toString()))
                {
                    Snackbar.make(root_element, R.string.warnung_email, Snackbar.LENGTH_LONG).show();
                    return;
                }

                if(pass.getText().toString().length()<5)
                {
                    Snackbar.make(root_element, R.string.warnung_pass, Snackbar.LENGTH_LONG).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(LoginPage.this,MainActivityHW004.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(root_element, R.string.error_sign + e.getMessage(), Snackbar.LENGTH_LONG).show();
                        return;
                    }
                });
            }
        });

        dialog.show();
    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.registration);
        dialog.setMessage(R.string.welcome_registration);

        LayoutInflater inflater = LayoutInflater.from(this);
        final View register_window = inflater.inflate(R.layout.register_window, null);

        dialog.setView(register_window);

        final MaterialEditText email = (MaterialEditText) register_window.findViewById(R.id.emailField);
        final MaterialEditText pass = (MaterialEditText) register_window.findViewById(R.id.passField);
        final MaterialEditText login = (MaterialEditText) register_window.findViewById(R.id.loginField);
        final MaterialEditText phone = (MaterialEditText) register_window.findViewById(R.id.phoneField);

        dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton(R.string.addLogin, new DialogInterface.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                if(TextUtils.isEmpty(email.getText().toString()))
                {
                    Snackbar.make(root_element, R.string.warnung_email, Snackbar.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(login.getText().toString()))
                {
                    Snackbar.make(root_element, R.string.warnung_login, Snackbar.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(phone.getText().toString()))
                {
                    Snackbar.make(root_element, R.string.warnung_phone, Snackbar.LENGTH_LONG).show();
                    return;
                }

                if(pass.getText().toString().length()<5)
                {
                    Snackbar.make(root_element, R.string.warnung_pass, Snackbar.LENGTH_LONG).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setEmail(email.getText().toString());
                                user.setPass(pass.getText().toString());
                                user.setLogin(login.getText().toString());
                                user.setPhone(phone.getText().toString());

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Snackbar.make(root_element, R.string.user_add, Snackbar.LENGTH_LONG).show();
                                            }
                                        });
                            }
                        });
            }
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