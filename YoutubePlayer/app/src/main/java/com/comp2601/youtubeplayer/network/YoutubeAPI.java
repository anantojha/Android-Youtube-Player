package com.comp2601.youtubeplayer.network;

import com.comp2601.youtubeplayer.models.ModelChannel;
import com.comp2601.youtubeplayer.models.ModelHome;
import com.comp2601.youtubeplayer.models.ModelPlaylist;
import com.comp2601.youtubeplayer.models.ModelVideos;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class YoutubeAPI {

    // API KEY -------------------------------------------------------------------
    public static final String APIKEY = "AIzaSyDSCyt1ytCsSJP9nL_Ry-GLbd4CEIcMfS4";
    // ---------------------------------------------------------------------------

    public static final String KEY = "key=";
    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    public static final String SEARCH = "search?";
    public static final String PLAYLIST = "playlists?";
    public static final String PLAYLISTITEMS = "playlistItems?";
    public static final String CHANNEL = "channels?";
    public static final String PLAYLISTID = "&playlistId=";
    public static final String MAX = "&maxResults=10";
    public static final String CID = "&channelId=UCWJ2lWNubArHWmf3FIHbfcQ";
    public static final String PART = "&part=snippet";
    public static final String PART_PLY = "&part=snippet,contentDetails";
    public static final String PART_CHNL = "&part=snippet,statistics,brandingSettings";
    public static final String PROFILE_ID = "&id=UCWJ2lWNubArHWmf3FIHbfcQ";
    public static final String ORDER_RELEVANCE = "&order=relevance";
    public static final String ORDER = "&order=date";
    public static final String QUERY = "&q=";
    public static final String TYPE = "&type=video";

    public interface HomeVideo {
        @GET
        Call<ModelHome> getYT(@Url String url);
    }

    public interface PlaylistVideo {
        @GET
        Call<ModelPlaylist> getYT(@Url String url);
    }

    public interface Videos {
        @GET
        Call<ModelVideos> getYT(@Url String url);
    }

    public interface ChannelInfo {
        @GET
        Call<ModelChannel> getYT(@Url String url);
    }

    private static HomeVideo homeVideo = null;
    private static PlaylistVideo playlistVideo = null;
    private static Videos videos = null;
    private static ChannelInfo channelInfo = null;

    public static HomeVideo getHomeVideo(){
        if(homeVideo == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            homeVideo = retrofit.create(HomeVideo.class);
        }
        return homeVideo;
    }

    public static PlaylistVideo getPlaylists(){
        if(playlistVideo == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            playlistVideo = retrofit.create(PlaylistVideo.class);
        }
        return playlistVideo;
    }

    public static Videos getPlaylistVideos(){
        if(videos == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            videos = retrofit.create(Videos.class);
        }
        return videos;
    }

    public static ChannelInfo getChannelInfo(){
        if(channelInfo == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            channelInfo = retrofit.create(ChannelInfo.class);
        }
        return channelInfo;
    }
}
