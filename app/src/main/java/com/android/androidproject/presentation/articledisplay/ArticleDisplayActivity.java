package com.android.androidproject.presentation.articledisplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.androidproject.presentation.articledisplay.home.fragment.HomeFragment;
import com.android.androidproject.R;
import com.android.androidproject.presentation.articledisplay.favorite.fragment.FavoriteFragment;
import com.android.androidproject.presentation.articledisplay.search.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ArticleDisplayActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private BottomNavigationView m_BottomNav;
    public static final List<Fragment> m_listFragment = new ArrayList<Fragment>() {{
        add(HomeFragment.newInstance());
        add(SearchFragment.newInstance());
        add(FavoriteFragment.newInstance());
    }};
    private static final int positionHomeFragment = 0;
    private static final int positionSearchFragment = 1;
    private static final int positionFavoriteFragment = 2;
    public int m_currentFragment = positionHomeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate starting");

        m_BottomNav = findViewById(R.id.bottom_navigation);
        m_BottomNav.setOnNavigationItemSelectedListener(navListerner);

        if(savedInstanceState != null) {
            m_currentFragment = savedInstanceState.getInt("currentPositionFragment");
        }
        else {
            m_currentFragment = positionHomeFragment;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter,
                m_listFragment.get(m_currentFragment)).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListerner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            m_currentFragment = positionHomeFragment;
                            break;
                        case R.id.nav_search:
                            m_currentFragment = positionSearchFragment;
                            break;
                        case R.id.nav_favorite:
                            m_currentFragment = positionFavoriteFragment;
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, m_listFragment.get(m_currentFragment)).commit();
                    return true;
                }
            };

    @Override
    protected void onSaveInstanceState(Bundle icicle) {
        Log.d(TAG, "onSaveInstanceSatete starting");
        super.onSaveInstanceState(icicle);
        icicle.putInt("currentPositionFragment", m_currentFragment);
    }
}

    