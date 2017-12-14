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
    private List<Song> songs;
    private double totalDuration;
    private int song1;
    private int song2;
    private int song3;
    private int song4;
    private int song5;
    private int song6;
    private int song7;
    private int song8;
    private int song9;
    private int song10;
    

    public Playlist() {
        songs = new ArrayList<>();
    }

    public Playlist(int id, String name, int song1, int song2, int song3, int song4, int song5, int song6, int song7, int song8, int song9, int song10) {
        songs = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.song1 = song1;
        this.song2 = song2;
        this.song3 = song3;
        this.song4 = song4;
        this.song5 = song5;
        this.song6 = song6;
        this.song7 = song7;
        this.song8 = song8;
        this.song9 = song9;
        this.song10 = song10;
    }
    public Playlist(int id, String name){
        this.id=id;
        this.name=name;
        songs = new ArrayList<>();
    }
    
    public void addSongToPlaylist(Song song){
        songs.add(song);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(double totalDuration) {
        this.totalDuration = totalDuration;
    }
    
    
    
    
    
    
}
