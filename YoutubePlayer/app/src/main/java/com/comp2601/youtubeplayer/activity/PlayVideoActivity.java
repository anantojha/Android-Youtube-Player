package com.comp2601.youtubeplayer.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.comp2601.youtubeplayer.R;
import com.comp2601.youtubeplayer.network.YoutubeAPI;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayVideoActivity extends YouTubeBaseActivity {

    Button playButton;
    TextView titleView;
    TextView captionView;
    private YouTubePlayerView youtubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    String title;
    String caption;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        caption = getIntent().getStringExtra("caption");

        titleView = (TextView) findViewById(R.id.videoPlayerTitle);
        captionView = (TextView) findViewById(R.id.videoPlayerCaption);

        titleView.setText(Html.fromHtml(title));
        captionView.setText(Html.fromHtml(caption));

        youtubePlayerView = (YouTubePlayerView) findViewById(R.id.YoutubeView);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(id);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        playButton = findViewById(R.id.PlayButton);
        playButton.setTextColor(Color.WHITE);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youtubePlayerView.initialize(YoutubeAPI.APIKEY,onInitializedListener);
            }
        });
    }
}