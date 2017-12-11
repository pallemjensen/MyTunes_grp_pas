/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.BLLManager;

/**
 *
 * @author Anders
 */
public class MyTunesModel {   
    
    private final ObservableList<Song> songs
            = FXCollections.observableArrayList();
    private final ObservableList<Playlist> playlists
            = FXCollections.observableArrayList();
    private final BLLManager bllManager = new BLLManager();
    
    public ObservableList<Song> getSongs() {
        return songs;
    }
    
    public ObservableList<Playlist> getPlaylists() {
        return playlists;
    }
    
    public void loadPlaylists() {
        List<Playlist> loadedPlaylists = bllManager.getAllPlaylists();
        playlists.clear();
        playlists.addAll(loadedPlaylists);
    }
    
    public void loadSongs() {
        List<Song> loadedSongs = bllManager.getAllSongs();
        songs.clear();
        songs.addAll(loadedSongs);
    }

    public void remove(Song selectedSongs) throws SQLException {
        songs.remove(selectedSongs);
        bllManager.remove(selectedSongs);
    }
    
    public void addNewSong(String title, String genre, String duration, String newSongPath, String artist) throws SQLException{
        Song newSong =
        bllManager.createSong(title, genre, duration, newSongPath, artist);
        songs.add(newSong);
    }

    public void editSong(int i, String title, String artist, String genre) throws SQLServerException, SQLException {
        bllManager.editSong(i, title, artist, genre);
    }

}