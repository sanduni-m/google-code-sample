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
import java.util.stream.Collectors;

import org.sonatype.aether.collection.CollectResult;

/**
 * A class used to represent a Video Library.
 */
class VideoLibrary {

  private final HashMap<String, Video> videos;

  VideoLibrary() {
    this.videos = new HashMap<>();
    try {
      File file = new File(this.getClass().getResource("/videos.txt").getFile());

      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] split = line.split("\\|");
        String title = split[0].strip();
        String id = split[1].strip();
        List<String> tags;
        if (split.length > 2) {
          tags = Arrays.stream(split[2].split(",")).map(String::strip).collect(
              Collectors.toList());
        } else {
          tags = new ArrayList<>();
        }
        this.videos.put(id, new Video(title, id, tags));
      }
    } catch (FileNotFoundException e) {
      System.out.println("Couldn't find videos.txt");
      e.printStackTrace();
    }
  }

  List<Video> getVideos() {
    return new ArrayList<>(this.videos.values());
  }

  /**
   * @return all the IDs of the videos as strings in an ArrayList
   */
  ArrayList<String> getVideoIDs() {
    ArrayList<String> IDs = new ArrayList<String>();
    for (Video v : getVideos()) {
      IDs.add(v.getVideoId());
    }
    return IDs;
  }

  /**
   * Get a video by id. Returns null if the video is not found.
   */
  Video getVideo(String videoId) {
    return this.videos.get(videoId);
  }

  /**
   * returns the video's details in the format “title (video_id) [tags]"
   */
  String getVideoDetails(Video v) {
    StringBuilder details = new StringBuilder();
    details.append(v.getTitle());
    details.append(" (" + v.getVideoId() + ") ");
    details.append(v.getTags().toString().replaceAll("[,]", ""));

    return details.toString();
  }

}
