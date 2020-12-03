package com.android.androidproject.presentation.articledisplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.androidproject.presentation.articledisplay.MainApplication.fragment.HomeFragment;
import com.android.androidproject.R;
import com.android.androidproject.presentation.articledisplay.favorite.fragment.FavoriteFragment;
import com.android.androidproject.presentation.articledisplay.MainApplication.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ArticleDisplayActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private BottomNavigationView m_BottomNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate starting");

        m_BottomNav = findViewById(R.id.bottom_navigation);
        m_BottomNav.setOnNavigationItemSelectedListener(navListerner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter,
                HomeFragment.newInstance()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListerner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                private final Fragment m_homeFragment = HomeFragment.newInstance();
                private final Fragment m_searchFragment = SearchFragment.newInstance();
                private final Fragment m_favoriteFragment = new FavoriteFragment();

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = this.m_homeFragment;
                            break;
                        case R.id.nav_search:
                            selectedFragment = this.m_searchFragment;
                            break;
                        case R.id.nav_favorite:
                            selectedFragment = this.m_favoriteFragment;
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, selectedFragment).commit();
                    return true;
                }
            };
}

    