package com.google;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Random;
import java.util.Collections;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private PlaylistLibrary playlistLibrary;
  private Video videoPlaying;
  private Boolean videoPaused = false;
  //private HashMap<String, VideoPlaylist> playlists;

  private void setVideoPlaying(Video v) {
    this.videoPlaying = v;
    setVideoPaused(false);
  }

  private void setVideoPaused(Boolean p) {
    this.videoPaused = p;
  }
/*
  private VideoPlaylist getPlaylist(String playlistName) {
    return this.playlists.get(playlistName.toLowerCase());
  }*/

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
    this.playlistLibrary = new PlaylistLibrary();
    //this.playlists = new HashMap<>();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    System.out.println("Here's a list of all available videos:");
    //videoLibrary.printVideoDetails();

    ArrayList<String> videoDetails = new ArrayList<String>();
    for (Video key : videoLibrary.getVideos()) {
      videoDetails.add(videoLibrary.getVideoDetails(key));
    }

    //SOURCE: https://www.w3schools.com/java/java_arraylist.asp
    Collections.sort(videoDetails);
    for (String v : videoDetails) {
      System.out.println(v);
    }

  }

  /**
   * stops any video that is currently playing
   * resets the boolean videoPaused to false
   */
  private void stopPlayingVideo() {
    if (videoPlaying != null) {
      System.out.println("Stopping video: " + videoPlaying.getTitle());
      setVideoPlaying(null);
    }
  }

  public void playVideo(String videoId) {
    Video videoToPlay = videoLibrary.getVideo(videoId);

    if (videoToPlay == null) {
      System.out.println("Cannot play video: Video does not exist");
    }
    else {
      stopPlayingVideo();
      setVideoPlaying(videoToPlay);
      System.out.println("Playing video: " + videoPlaying.getTitle());
    }
    

  }

  public void stopVideo() {
    if (videoPlaying == null) {
      System.out.println("Cannot stop video: No video is currently playing");
    }
    else stopPlayingVideo();
  }

  public void playRandomVideo() {
    ArrayList<String> videoIDs = videoLibrary.getVideoIDs();
    
    if (videoIDs.size() == 0) {
      System.out.print("No videos available");
    }
    else {
      Random rnd = new Random();
      playVideo(videoIDs.get(rnd.nextInt(videoIDs.size())));
    }

  }

  public void pauseVideo() {
    if (videoPlaying == null) {
      System.out.println("Cannot pause video: No video is currently playing");
    }
    else {
      if (videoPaused) {
        System.out.println("Video already paused: " + videoPlaying.getTitle());
      }
      else {
        setVideoPaused(true);
        System.out.println("Pausing video: " + videoPlaying.getTitle());
      }
    }
  }

  public void continueVideo() {
    //System.out.println("continueVideo needs implementation");
    if (videoPlaying == null) {
      System.out.println("Cannot continue video: No video is currently playing");
    }
    else {
      if (videoPaused) {
        setVideoPaused(false);
        System.out.println("Continuing video: " + videoPlaying.getTitle());
      }
      else {
        System.out.println("Cannot continue video: Video is not paused");
      }
    }
  }

  public void showPlaying() {
    String outputMsg;
    if (videoPlaying == null) {
      outputMsg = "No video is currently playing";
    }
    else {
      outputMsg = "Currently playing: " + videoLibrary.getVideoDetails(videoPlaying);
      if (videoPaused) {
        outputMsg += " - PAUSED";
      }
    }
    System.out.print(outputMsg);
  }
/*
mvn exec:java

*/
  public void createPlaylist(String playlistName) {
    if (playlistLibrary.getPlaylist(playlistName) != null) {
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
    }
    else {
      playlistLibrary.addPlaylist(playlistName);
      System.out.println("Successfully created new playlist: " + playlistName);
    }
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    //check if both playlist and video exist
    if (playlistLibrary.getPlaylist(playlistName) == null) {
      System.out.println("Cannot add video to " + playlistName + ": Playlist does not exist");
    }
    else if (videoLibrary.getVideo(videoId) == null) {
      System.out.println("Cannot add video to " + playlistName + ": Video does not exist");
    }

    //try to add video to playlist
    else if (playlistLibrary.getPlaylist(playlistName)
        .addToPlaylist(videoLibrary.getVideo(videoId))) {
      System.out.println("Added video to " + playlistName + ": " + videoLibrary.getVideo(videoId).getTitle());
    }
    else {
      System.out.println("Cannot add video to " + playlistName + ": Video already added");
    }
  }

  public void showAllPlaylists() {
    if (playlistLibrary.getNoPlaylists() == 0) {
      System.out.println("No playlists exist yet");
    }
    else {
      System.out.println("Showing all playlists:");
      for (String p : playlistLibrary.getPlaylistsKeySet()) {
        System.out.println(playlistLibrary.getPlaylist(p).getName());
      }
    }
  }

  public void showPlaylist(String playlistName) {
    if (playlistLibrary.getPlaylist(playlistName) == null) {
      System.out.println("Cannot show playlist " + playlistName + ": Playlist does not exist");
    } 
    else {
      System.out.println("Showing playlist: " + playlistName);
      if (playlistLibrary.getPlaylist(playlistName).getVideos().size() == 0) {
        System.out.println("No videos here yet");
      }
      else {
        for (Video v : playlistLibrary.getPlaylist(playlistName).getVideos()) {
          System.out.println(videoLibrary.getVideoDetails(v));
        }
      }
    }
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    if (playlistLibrary.getPlaylist(playlistName) == null) {
      System.out.println("Cannot remove video from " + playlistName + ": Playlist does not exist");
    }
    else if (videoLibrary.getVideo(videoId) == null) {
      System.out.println("Cannot remove video from " + playlistName + ": Video does not exist");
    }
    else if (!playlistLibrary.getPlaylist(playlistName).getVideos()
        .contains(videoLibrary.getVideo(videoId))) {
      System.out.println("Cannot remove video from " + playlistName + ": Video is not in playlist");
    }
    else {
      //try to remove video from playlist
      if (playlistLibrary.getPlaylist(playlistName)
          .removeFromPlaylist(videoLibrary.getVideo(videoId))) {
        System.out.println("Removed video from " + playlistName + ": " + videoLibrary.getVideo(videoId).getTitle());
      }
      else {
        System.out.println("Cannot remove video from " + playlistName + ": Video is not in playlist");
      }
    }
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}