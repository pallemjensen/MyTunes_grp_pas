/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for constructing and managing playlists.
 *
 * @author pmj
 */
public class Playlist {

    private int id;
    private String name;
    private final List<Song> playlistSongs;
    private double totalDuration;

    /**
     * @Playlist constructor
     */
    public Playlist() {
        playlistSongs = new ArrayList<>();
    }

    /**
     *
     * @param id
     * @param name
     * @Playlist constructor
     */
    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
        playlistSongs = new ArrayList<>();
    }

    /**
     *
     * @param song
     * @Add a song object to our playlist
     */
    public void addSongToPlaylist(Song song) {
        playlistSongs.add(song);
    }

    /**
     *
     * @return playlist
     */
    public List<Song> getplaylistSongs() {
        return playlistSongs;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * @set name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * @set id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return totalduration
     */
    public double getTotalDuration() {
        return totalDuration;
    }

    /**
     *
     * @param totalDuration
     * @set totalDuration
     */
    public void setTotalDuration(double totalDuration) {
        this.totalDuration = totalDuration;
    }

}
