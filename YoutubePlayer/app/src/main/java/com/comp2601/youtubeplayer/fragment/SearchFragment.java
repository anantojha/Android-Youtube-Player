package com.comp2601.youtubeplayer.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class SearchFragment extends Fragment {

    private static final String TAG = "Search Fragment: ";
    private EditText searchEdit;
    private Button searchButton;
    private AdapterHome adapter;
    private LinearLayoutManager manager;
    private List<VideoYT> videoList = new ArrayList<>();

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchButton = view.findViewById(R.id.searchButton);
        searchEdit = view.findViewById(R.id.searchQuery);
        RecyclerView rv = view.findViewById(R.id.recyclerSearch);

        adapter = new AdapterHome(getContext(), videoList);
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(searchEdit.getText().toString())) {
                    getJson(searchEdit.getText().toString());
                } else {
                    Toast.makeText(getContext(), "Enter text to search", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void getJson(String query) {
        String url = YoutubeAPI.BASE_URL + YoutubeAPI.SEARCH + YoutubeAPI.KEY + YoutubeAPI.APIKEY /*+ YoutubeAPI.CID*/ + YoutubeAPI.MAX + YoutubeAPI.ORDER_RELEVANCE + YoutubeAPI.PART
                + YoutubeAPI.QUERY + query + YoutubeAPI.TYPE;
        Call<ModelHome> data = YoutubeAPI.getHomeVideo().getYT(url);
        data.enqueue(new Callback<ModelHome>() {
            @Override
            public void onResponse(Call<ModelHome> call, Response<ModelHome> response) {
                if(response.errorBody() != null){
                    Log.w(TAG, "onResponse: " + response.errorBody());
                } else {
                    ModelHome mh = response.body();
                    if(mh.getItems().size() != 0) {
                        videoList.clear();
                        videoList.addAll(mh.getItems());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "No video", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelHome> call, Throwable t) {
                Log.e(TAG, "onfailure: " + t.getMessage());
            }
        });
    }
}