package com.comp2601.youtubeplayer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.comp2601.youtubeplayer.R;
import com.comp2601.youtubeplayer.fragment.HomeFragment;
import com.comp2601.youtubeplayer.fragment.PlaylistFragment;
import com.comp2601.youtubeplayer.fragment.ProfileFragment;
import com.comp2601.youtubeplayer.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment = new HomeFragment();
    private PlaylistFragment playlistFragment = new PlaylistFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navMenu);
        setFragment(homeFragment);
        bottomNavigationView.setSelectedItemId(R.id.menuHome);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.isChecked()){
                    return true;
                } else {
                    switch (item.getItemId()) {
                        case R.id.menuHome:
                            setFragment(homeFragment);
                            getSupportActionBar().setTitle("Home");
                            return true;
                        case R.id.menuPlaylist:
                            setFragment(playlistFragment);
                            getSupportActionBar().setTitle("Playlist");
                            return true;
                        case R.id.menuSearch:
                            setFragment(searchFragment);
                            getSupportActionBar().setTitle("Search");
                            return true;
                        case R.id.menuProfile:
                            setFragment(profileFragment);
                            getSupportActionBar().setTitle("Profile");
                            return true;
                        default:
                            setFragment(homeFragment);
                            getSupportActionBar().setTitle("Home");
                            return true;
                    }
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, fragment);
        ft.commit();

    }


}