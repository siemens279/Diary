package com.siemens.diary;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.siemens.diary.adapter.TabsPageFragmentAdapter;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private FirebaseAuth mAuth;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        initToolbar();
        initNavigationView();
        intTabs();
    }

    private void intTabs() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabsPageFragmentAdapter adapter = new TabsPageFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mExit:
                        mAuth.signOut();
                        startActivity(new Intent(MainActivity.this, SingInActivity.class));
                        break;
                }
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

}
