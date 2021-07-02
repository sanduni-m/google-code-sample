package com.google;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class PlaylistLibrary {
    
  private HashMap<String, VideoPlaylist> playlists;

  PlaylistLibrary() {
    this.playlists = new HashMap<>();
  }
  
  VideoPlaylist getPlaylist(String playlistName) {
      return this.playlists.get(playlistName.toLowerCase());
  }

  Set<String> getPlaylistsKeySet() {
      return playlists.keySet();
  }

  Integer getNoPlaylists() {
      return playlists.size();
  }

  void addPlaylist(String playlistName) {
    playlists.put(playlistName.toLowerCase(), new VideoPlaylist(playlistName));
  }

  void removePlaylist(VideoPlaylist p) {
    playlists.remove(p.getPlaylistID(), p);
  }

  public void printPlaylistDoesNotExist(String action, String playlistName) {
      System.out.println("Cannot " + action + " " + playlistName + ": Playlist does not exist");
  }

}
