package com.comp2601.youtubeplayer.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.comp2601.youtubeplayer.R;
import com.comp2601.youtubeplayer.fragment.HomeFragment;
import com.comp2601.youtubeplayer.fragment.PlaylistFragment;
import com.comp2601.youtubeplayer.fragment.ProfileFragment;
import com.comp2601.youtubeplayer.fragment.SearchFragment;
import com.comp2601.youtubeplayer.fragment.VideosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PlaylistVideosActivity extends AppCompatActivity {

    private static final String TAG = "Playlist Videos Activity; ";
    private String playlistId;
    private VideosFragment videoFragment;
    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment = new HomeFragment();
    private PlaylistFragment playlistFragment = new PlaylistFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        bottomNavigationView = findViewById(R.id.navMenu);

        playlistId = getIntent().getStringExtra("id");
        videoFragment = new VideosFragment(playlistId);
        bottomNavigationView.setSelectedItemId(R.id.menuPlaylist);

        setFragment(videoFragment);

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
                            setFragment(playlistFragment);
                            getSupportActionBar().setTitle("Playlist");
                            return true;
                    }
                }
            }
        });
    }

    private void setFragment(Fragment videoFragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame2, videoFragment);
        ft.commit();

    }

}
