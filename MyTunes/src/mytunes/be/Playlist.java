/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pmj
 */
public class Playlist {
    private int id;
    private String name;
    private List<Song> playlistSongs;
    private double totalDuration;
//    private int song1;
//    private int song2;
//    private int song3;
//    private int song4;
//    private int song5;
//    private int song6;
//    private int song7;
//    private int song8;
//    private int song9;
//    private int song10;
    
    /**
     *@Playlist constructor
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
    public Playlist(int id, String name){
        this.id=id;
        this.name=name;
        playlistSongs = new ArrayList<>();
    }
    
    /**
     *
     * @param song
     * @Add a song object to our songs-list
     */
    public void addSongToPlaylist(Song song){
        playlistSongs.add(song);
    }

    /**
     *
     * @return
     */
    public List<Song> getSongs() {
        return playlistSongs;
    }

    /**
     *
     * @param songs
     */
    public void setSongs(List<Song> songs) {
        this.playlistSongs = songs;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public double getTotalDuration() {
        return totalDuration;
    }

    /**
     *
     * @param totalDuration
     */
    public void setTotalDuration(double totalDuration) {
        this.totalDuration = totalDuration;
    }
    
    
    
    
    
    
}
