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
     *@Playlist constructor with songs list
     */
    public Playlist() {
        playlistSongs = new ArrayList<>();
    }

    /**
     *
     * @param id
     * @param name
     * @param song1
     * @param song2
     * @param song3
     * @param song4
     * @param song5
     * @param song6
     * @param song7
     * @param song8
     * @param song9
     * @param song10
     * @Playlist constructor
     */
//    public Playlist(int id, String name, int song1, int song2, int song3, int song4, int song5, int song6, int song7, int song8, int song9, int song10) {
//        songs = new ArrayList<>();
//        this.id = id;
//        this.name = name;
//        this.song1 = song1;
//        this.song2 = song2;
//        this.song3 = song3;
//        this.song4 = song4;
//        this.song5 = song5;
//        this.song6 = song6;
//        this.song7 = song7;
//        this.song8 = song8;
//        this.song9 = song9;
//        this.song10 = song10;
//    }

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
