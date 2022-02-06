package com.comp2601.youtubeplayer.fragment;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.comp2601.youtubeplayer.R;
import com.comp2601.youtubeplayer.models.ChannelList;
import com.comp2601.youtubeplayer.models.ModelChannel;
import com.comp2601.youtubeplayer.network.YoutubeAPI;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private static final String TAG = "Profile Fragment: ";
    private ImageView banner, logo;
    private TextView channelName, subscriber, description, videoCount, viewCount, publishedAt;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        banner = view.findViewById(R.id.profileBanner);
        logo = view.findViewById(R.id.profileIcon);
        channelName = view.findViewById(R.id.channelName);
        subscriber = view.findViewById(R.id.subscriberCount);
        description = view.findViewById(R.id.description);
        videoCount = view.findViewById(R.id.videoCount);
        viewCount = view.findViewById(R.id.viewCount);
        publishedAt = view.findViewById(R.id.publishedAt);

        getJsonAPI();

        return view;
    }

    private void getJsonAPI() {
        String url = YoutubeAPI.BASE_URL + YoutubeAPI.CHANNEL + YoutubeAPI.KEY + YoutubeAPI.APIKEY + YoutubeAPI.PROFILE_ID + YoutubeAPI.PART_CHNL;
        Call<ModelChannel> data = YoutubeAPI.getChannelInfo().getYT(url);
        data.enqueue(new Callback<ModelChannel>() {
            @Override
            public void onResponse(Call<ModelChannel> call, Response<ModelChannel> response) {
                if(response.errorBody() != null){
                    Log.w(TAG, "onResponse: " + response.errorBody());
                } else {
                    ModelChannel mp = response.body();
                    setData(mp.getItems().get(0));
                }
            }

            @Override
            public void onFailure(Call<ModelChannel> call, Throwable t) {
                Log.e(TAG, "onFailure playlist: ", t);
            }
        });

    }

    private void setData(ChannelList c) {
        channelName.setText(Html.fromHtml(c.getSnippet().getTitle()));
        subscriber.setText("Subscribers: " + c.getStatistics().getSubscriberCount());
        description.setText(Html.fromHtml(c.getSnippet().getDescription()));
        videoCount.setText("Videos: " + c.getStatistics().getVideoCount());
        viewCount.setText("Views: " + c.getStatistics().getViewCount());
        publishedAt.setText(c.getSnippet().getPublishedAt());

        Picasso.get()
                .load(c.getSnippet().getThumbnails().getMedium().getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(logo, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "Logo loaded successfully");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d(TAG, "Loading Logo failed");
                    }
                });

        Picasso.get()
                .load(c.getBrandingSettings().getImageBranding().getBannerImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(banner, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "Banner loaded successfully");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d(TAG, "Loading Banner failed");
                    }
                });
    }
}