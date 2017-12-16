/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import java.text.DecimalFormat;
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
    private String totalDurationAsString;

    public String getTotalDurationAsString() {
        return totalDurationAsString;
    }
    private List<Integer> playlistSongIdsList;
    private int numberOfSongs = 0;

    /**
     * Get the value of numberOfSongs
     *
     * @return the value of numberOfSongs
     */
    public int getNumberOfSongs() {
        return numberOfSongs;
    }
    /**
     * Set the value of numberOfSongs
     *
     * @param numberOfSongs new value of numberOfSongs
     */
    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    /**
     * increses numberOfSongs by one
     */
    public void addOneToNumberOfSongs(){
        numberOfSongs = numberOfSongs + 1;
    }
    
    /**
     * get a list of songId of songs on the playlist
     * @return playListSongIdsList
     */
    public List<Integer> getPlaylistSongIdsList() {
        return playlistSongIdsList;
    }
    
    /**
     *adds a songId to playlistSongIdsList
     * @param id
     */
    public void addIdToPlaylistSongIdsList(int id){
        playlistSongIdsList.add(id);
    }

    /**
     * @Playlist constructor
     */
    public Playlist() {
        playlistSongs = new ArrayList<>();
        playlistSongIdsList = new ArrayList<>();
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
        playlistSongIdsList = new ArrayList<>();
    }

    /**
     *
     * @param song
     * @Add a song object to our playlist
     */
    public void addSongToPlaylist(Song song) {
        playlistSongs.add(song);
        numberOfSongs = numberOfSongs + 1;
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
        double td = Math.floor(totalDuration/60) + (totalDuration%60)/100;
        this.totalDuration = td;
        DecimalFormat df = new DecimalFormat("#.00");
        totalDurationAsString = df.format(td);
    }

}
