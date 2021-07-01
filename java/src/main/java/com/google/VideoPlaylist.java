package com.google;
import java.util.ArrayList;
import java.util.List;

/** A class used to represent a Playlist */
class VideoPlaylist {

    private final String name;
    private final String playlistID;
    private ArrayList<Video> videos;

    VideoPlaylist(String name) {
        this.name = name;
        this.playlistID = name.toLowerCase();
        this.videos = new ArrayList<>();
    }

    String getName() {
        return this.name;
    }

    /**
     * @param video added to ArrayList videos if it isn't already present
     * @return whether the video was added or not
     */ 
    Boolean addToPlaylist(Video video) {
        if (videos.contains(video)) {
            return false;
        }
        else {
            videos.add(video);
            return true;
        }
    }

}
