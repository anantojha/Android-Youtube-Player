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
import com.comp2601.youtubeplayer.models.PlayListItems;
import com.comp2601.youtubeplayer.models.VideoYT;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPlaylist extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "Adapter Playlist: ";
    private Context context;
    private List<PlayListItems> videoList;

    public AdapterPlaylist(Context context, List<PlayListItems> videoList){
        this.context = context;
        this.videoList = videoList;
    }

    class YoutubeHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView videoTitle;
        TextView videoCount1, videoCount2;

        public YoutubeHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.playlistThumbnail);
            videoTitle = itemView.findViewById(R.id.playlistName);
            videoCount1 = itemView.findViewById(R.id.playlistVideoCount1);
            videoCount2 = itemView.findViewById(R.id.playlistVideoCount2);
        }

        public void setdata(PlayListItems data) {
            String getTitle = data.getSnippet().getTitle();
            int getCount = data.getContentDetails().getItemCount();
            String getCaption = data.getSnippet().getPublishedAt();
            String getThumb = data.getSnippet().getThumbnails().getMedium().getUrl();
            String id = data.getId();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), PlaylistVideosActivity.class);
                    intent.putExtra("id", id);
                    view.getContext().startActivity(intent);
                }
            });

            videoTitle.setText(Html.fromHtml(getTitle));
            videoCount1.setText(getCount + " video(s)");
            videoCount2.setText(String.valueOf(getCount));

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
        View view = inflater.inflate(R.layout.row_item_playlist, parent, false);
        return new YoutubeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PlayListItems videoYT = videoList.get(position);
        YoutubeHolder youtubeHolder = (YoutubeHolder) holder;
        youtubeHolder.setdata(videoYT);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
