package com.siemens.diary.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.siemens.diary.R;
import com.siemens.diary.SingInActivity;

public class f_registration extends Fragment {

    private FirebaseAuth mAuth;
    EditText etEmail, etPassword, etNane, etAge;
    Button bRegistration, bBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.f_registration, container, false);
        mAuth = FirebaseAuth.getInstance();
        etEmail = (EditText) view.findViewById(R.id.editTextEmail);
        etPassword = (EditText) view.findViewById(R.id.editTextPassword);
        etNane = (EditText) view.findViewById(R.id.editTextName);
        etAge = (EditText) view.findViewById(R.id.editTextAge);
        bRegistration = (Button) view.findViewById(R.id.buttonRegistration);
        bRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });
        bBack = (Button) view.findViewById(R.id.buttonBack);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                Class fragmentClass = f_sing_in.class;
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
    public void registration(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Goog registration", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Не удалось зарегистрировать нового пользователя", Toast.LENGTH_SHORT).show();
                    //Log.d(TAG, task.getException().getMessage());
                }
            }
        });
    }
}
