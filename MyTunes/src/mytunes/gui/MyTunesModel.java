/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.BLLManager;
import mytunes.dal.PlaylistDAO;

/**
 *
 * @author Anders
 */
public class MyTunesModel {
    private BLLManager bllManager = new BLLManager();
    private PlaylistDAO playlistDAO = new PlaylistDAO();

    private ObservableList<Song> songs
            = FXCollections.observableArrayList();
    
    public ObservableList<Song> getSongs() {
        return songs;
    }

    public void loadSongs() {
        List<Song> loadedSongs = bllManager.getAllSongs();
        songs.clear();
        songs.addAll(loadedSongs);
    }
    
    private ObservableList<Playlist> playlists
            = FXCollections.observableArrayList();
    
    ObservableList<Playlist> getPlaylists() {
        return playlists;
    }
    
    public void loadPlaylists() {
        List<Playlist> loadedPlaylists = playlistDAO.getAllPlaylists();
        playlists.clear();
        playlists.addAll(loadedPlaylists);
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

    

}