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
import com.comp2601.youtubeplayer.adapter.AdapterPlaylistVideos;
import com.comp2601.youtubeplayer.models.ModelVideos;
import com.comp2601.youtubeplayer.models.VideoItem;
import com.comp2601.youtubeplayer.network.YoutubeAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideosFragment extends Fragment {

    private static final String TAG = "Playlist Videos; ";
    private AdapterPlaylistVideos adapter;
    private LinearLayoutManager manager;
    private List<VideoItem> videoList = new ArrayList<>();
    private String playlistId;

    public VideosFragment(String playlistId) {
        // Required empty public constructor
        this.playlistId = playlistId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rv = view.findViewById(R.id.recyclerView);
        adapter = new AdapterPlaylistVideos(getContext(), videoList);
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);

        if(videoList.size() == 0){
            getJson();
        }
        return view;
    }

    private void getJson() {

        String url = YoutubeAPI.BASE_URL + YoutubeAPI.PLAYLISTITEMS + YoutubeAPI.KEY + YoutubeAPI.APIKEY + YoutubeAPI.MAX + YoutubeAPI.ORDER + YoutubeAPI.PART + YoutubeAPI.PLAYLISTID + playlistId;
        Call<ModelVideos> data = YoutubeAPI.getPlaylistVideos().getYT(url);
        data.enqueue(new Callback<ModelVideos>() {
            @Override
            public void onResponse(Call<ModelVideos> call, Response<ModelVideos> response) {
                if(response.errorBody() != null){
                    Log.w(TAG, "onResponse: " + response.errorBody());
                } else {
                    ModelVideos mv = response.body();
                    videoList.addAll(mv.getItems());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelVideos> call, Throwable t) {

            }
        });

    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}
