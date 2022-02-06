package com.comp2601.youtubeplayer.fragment;

import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.comp2601.youtubeplayer.R;
import com.comp2601.youtubeplayer.adapter.AdapterPlaylist;
import com.comp2601.youtubeplayer.models.ModelPlaylist;
import com.comp2601.youtubeplayer.models.PlayListItems;
import com.comp2601.youtubeplayer.network.YoutubeAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistFragment extends Fragment {

    private static final String TAG = "Playlist Fragment; ";
    private AdapterPlaylist adapter;
    private LinearLayoutManager manager;
    private List<PlayListItems> videoList = new ArrayList<>();



    public PlaylistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        RecyclerView rv = view.findViewById(R.id.recycler_playlist);
        adapter = new AdapterPlaylist(getContext(), videoList);
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);
        if(videoList.size() == 0){
            getJson();
        }
        return view;
    }

    private void getJson() {
        String url = YoutubeAPI.BASE_URL + YoutubeAPI.PLAYLIST + YoutubeAPI.KEY + YoutubeAPI.APIKEY + YoutubeAPI.PART_PLY + YoutubeAPI.CID;
        Call<ModelPlaylist> data = YoutubeAPI.getPlaylists().getYT(url);
        data.enqueue(new Callback<ModelPlaylist>() {
            @Override
            public void onResponse(Call<ModelPlaylist> call, Response<ModelPlaylist> response) {
                if(response.errorBody() != null){
                    Log.w(TAG, "onResponse: " + response.errorBody());
                } else {
                    ModelPlaylist mp = response.body();
                    videoList.addAll(mp.getItems());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelPlaylist> call, Throwable t) {
                Log.e(TAG, "onFailure playlist: ", t);
            }
        });
    }
}