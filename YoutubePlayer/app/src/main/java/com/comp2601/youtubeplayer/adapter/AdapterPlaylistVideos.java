package com.comp2601.youtubeplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.comp2601.youtubeplayer.R;
import com.comp2601.youtubeplayer.activity.PlayVideoActivity;
import com.comp2601.youtubeplayer.activity.PlaylistVideosActivity;
import com.comp2601.youtubeplayer.models.VideoItem;
import com.comp2601.youtubeplayer.models.VideoYT;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPlaylistVideos extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "Adapter Playlist Videos: ";
    private Context context;
    private List<VideoItem> videoList;

    public AdapterPlaylistVideos(Context context, List<VideoItem> videoList){
        this.context = context;
        this.videoList = videoList;
    }

    class YoutubeHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView videoTitle;
        TextView videoCaption;

        public YoutubeHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.videoThumbnail);
            videoTitle = itemView.findViewById(R.id.videoTitle);
            videoCaption = itemView.findViewById(R.id.videoCaption);
        }

        public void setdata(VideoItem data) {
            String getTitle = data.getSnippet().getTitle();
            String getCaption = data.getSnippet().getPublishedAt();
            String getThumb = data.getSnippet().getThumbnails().getMedium().getUrl();
            String getVideoId = data.getSnippet().getResourceId().getVideoId();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), PlayVideoActivity.class);
                    intent.putExtra("id", getVideoId);
                    intent.putExtra("title", getTitle);
                    intent.putExtra("caption", getCaption);
                    view.getContext().startActivity(intent);
                }
            });

            videoTitle.setText(Html.fromHtml(getTitle));
            videoCaption.setText(Html.fromHtml(getCaption));
            Picasso.get()
                    .load(getThumb)
                    .placeholder(R.mipmap.ic_launcher)
                    .fit()
                    .centerCrop()
                    .into(thumbnail, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "Thumbnails loaded successfully");
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d(TAG, "Loading thumbnails failed");
                        }
                    });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_item_video, parent, false);
        return new YoutubeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VideoItem videoItem = videoList.get(position);
        YoutubeHolder youtubeHolder = (YoutubeHolder) holder;
        youtubeHolder.setdata(videoItem);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
