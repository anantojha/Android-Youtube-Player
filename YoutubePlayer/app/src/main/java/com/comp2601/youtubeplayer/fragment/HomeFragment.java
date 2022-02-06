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
import com.comp2601.youtubeplayer.adapter.AdapterHome;
import com.comp2601.youtubeplayer.models.ModelHome;
import com.comp2601.youtubeplayer.models.VideoYT;
import com.comp2601.youtubeplayer.network.YoutubeAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private static final String TAG = "Home Fragment: ";
    private AdapterHome adapterHome;
    private LinearLayoutManager manager;
    private List<VideoYT> videoList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rv = view.findViewById(R.id.recyclerView);
        adapterHome = new AdapterHome(getContext(), videoList);
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(adapterHome);
        rv.setLayoutManager(manager);

        if(videoList.size() == 0){
            getJson();
        }
        return view;
    }

    private void getJson() {
        String url = YoutubeAPI.BASE_URL + YoutubeAPI.SEARCH + YoutubeAPI.KEY + YoutubeAPI.APIKEY + YoutubeAPI.CID + YoutubeAPI.MAX + YoutubeAPI.ORDER + YoutubeAPI.PART;
        Call<ModelHome> data = YoutubeAPI.getHomeVideo().getYT(url);
        data.enqueue(new Callback<ModelHome>() {
            @Override
            public void onResponse(Call<ModelHome> call, Response<ModelHome> response) {
                if(response.errorBody() != null){
                    Log.w(TAG, "onResponse: " + response.errorBody());
                } else {
                    ModelHome mh = response.body();
                    videoList.addAll(mh.getItems());
                    adapterHome.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelHome> call, Throwable t) {
                Log.e(TAG, "onfailure: " + t.getMessage());
            }
        });

    }
}