package com.siemens.diary.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siemens.diary.R;

public class f_tab1 extends Fragment {

    public static f_tab1 getInstance() {
        Bundle args = new Bundle();
        f_tab1 fragment = new f_tab1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.f_tab1, container, false);

        return view;
    }
}
