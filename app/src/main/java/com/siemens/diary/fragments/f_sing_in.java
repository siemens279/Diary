package com.siemens.diary.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.siemens.diary.MainActivity;
import com.siemens.diary.R;
import com.siemens.diary.SingInActivity;

public class f_sing_in extends Fragment {

    EditText etEmail, etPassword;
    Button bSignIn;
    TextView tvRegistration;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.f_sing_in, container, false);
        mAuth = FirebaseAuth.getInstance();
        etEmail = (EditText) view.findViewById(R.id.editTextEmail);
        etPassword = (EditText) view.findViewById(R.id.editTextPassword);
        bSignIn = (Button) view.findViewById(R.id.buttonSingIn);
        bSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etEmail.getText().toString().isEmpty()) {
                    if (!etPassword.getText().toString().isEmpty()) {
                        singIn(etEmail.getText().toString(), etPassword.getText().toString());
                    } else Toast.makeText(getContext(), "Пароль пустой", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getContext(), "Емейл пустой", Toast.LENGTH_SHORT).show();
            }
        });
        tvRegistration = (TextView) view.findViewById(R.id.textViewRegistration);
        tvRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                Class fragmentClass = f_registration.class;
                FragmentManager fragmentManager = getFragmentManager();
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
            }
        });
        return view;
    }

    public void singIn(String email, String password) {
        //Log.d("MyLog", "Email:"+email);
        //Log.d("MyLog", "Password:"+password);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //Toast.makeText(getContext(), "Goog avtorization", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                    //Log.d(TAG, task.toString());
                }
            }
        });
    }

}
