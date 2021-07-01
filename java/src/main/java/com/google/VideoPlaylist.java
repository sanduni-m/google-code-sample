package com.google;
import java.util.ArrayList;
import java.util.List;

/** A class used to represent a Playlist */
class VideoPlaylist {

    private final String name;
    private final String playlistID;
    private ArrayList<Video> videoIDs;

    VideoPlaylist(String name) {
        this.name = name;
        this.playlistID = name.toLowerCase();
        this.videoIDs = new ArrayList<>();
    }

    String getName() {
        return this.name;
    }

    Boolean addToPlaylist(Video video) {
        if (videoIDs.contains(video)) {
            return false;
        }
        else {
            videoIDs.add(video);
            return true;
        }
    }

}
