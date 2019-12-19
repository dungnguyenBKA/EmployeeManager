package com.example.qunlnhns.activities.mainAcitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.qunlnhns.R;
import com.example.qunlnhns.db.DbManager;
import com.example.qunlnhns.ui.frag1.Frag1;
import com.example.qunlnhns.ui.frag2.Frag2;
import com.example.qunlnhns.ui.frag3.Frag3;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "dung_test";

    BottomNavigationView bottomNavigationView;

    DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new Frag1())
                .commit();

        // create data base
        dbManager = new DbManager(this);
        dbManager.createDatabase();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;

                switch (menuItem.getItemId()){
                    case R.id.manage_employee:
                        selectedFragment = new Frag1();
                        break;

                    case R.id.manage_project:

                        selectedFragment = new Frag2();
                        break;

                    case R.id.manage_room:
                        selectedFragment = new Frag3();
                        break;
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                }

                return true;
            }
        });
    }

}
