package com.siemens.diary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.siemens.diary.adapter.TabsPageFragmentAdapter;
import com.siemens.diary.fragments.f_add_diary;
import com.siemens.diary.fragments.f_sing_in;

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
//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
//        TabsPageFragmentAdapter adapter = new TabsPageFragmentAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
//
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
//        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mAdd:
                        setFragment(f_add_diary.class, null);
                        break;
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

    public void setFragment(Class clas, Bundle bundle) {
        //Class fragmentClass = clas;
        Fragment fragment = null;
        try {
            fragment = (Fragment) clas.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bundle!=null) {
            fragment.setArguments(bundle);
        }
        if (fragment!=null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_exit:
                        Toast.makeText(MainActivity.this, "Good", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }


}
