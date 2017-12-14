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
 * @
 * @author Anders
 */

public class MyTunesModel {
    private final BLLManager bllManager = new BLLManager();

    private final ObservableList<Song> songs
            = FXCollections.observableArrayList();
    
    private final ObservableList<Playlist> playlists
            = FXCollections.observableArrayList();
       
    private final ObservableList<Song> songsOnPlaylists
            = FXCollections.observableArrayList();
    
    /**
     *
     * @return
     */
    public ObservableList<Song> getSongsOnPlaylist() {
        return songsOnPlaylists;
    }
    
    /**
     *
     * @param playlistSelected
     */
    public void showSongsOnPlaylist(Playlist playlistSelected) {
        List<Song> songList = playlistSelected.getplaylistSongs();
        songsOnPlaylists.clear();
        songsOnPlaylists.addAll(songList);
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Song> getSongs() {
        return songs;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Playlist> getPlaylists() {
        return playlists;
    }
    
    /**
     *@load our playlists from the DB
     */
    public void loadPlaylists() {
        List<Playlist> loadedPlaylists = bllManager.getAllPlaylists();
        playlists.clear();
        playlists.addAll(loadedPlaylists);
    }
    
    /**
     *@Load our songs from the DB and add them into our songs list.
     */
    public void loadSongs() {
        List<Song> loadedSongs = bllManager.getAllSongs();
        songs.clear();
        songs.addAll(loadedSongs);
    }

    /**
     *
     * @param selectedSongs
     * @throws SQLException
     */
    public void remove(Song selectedSongs) throws SQLException {
        songs.remove(selectedSongs);
        bllManager.remove(selectedSongs);
    }
    
    /**
     *
     * @param title
     * @param genre
     * @param duration
     * @param newSongPath
     * @param artist
     * @throws SQLException
     */
    public void addNewSong(String title, String genre, String duration, String newSongPath, String artist) throws SQLException{
        Song newSong =
        bllManager.createSong(title, genre, duration, newSongPath, artist);
        songs.add(newSong);
    }

    /**
     *
     * @param i
     * @param title
     * @param artist
     * @param genre
     * @throws SQLServerException
     * @throws SQLException
     */
    public void editSong(int i, String title, String artist, String genre) throws SQLServerException, SQLException {
        bllManager.editSong(i, title, artist, genre);
    }

    /**
     *
     * @param playlist
     * @throws SQLServerException
     * @throws SQLException
     */
    public void remove(Playlist playlist) throws SQLServerException, SQLException {
        playlists.remove(playlist);
        bllManager.remove(playlist);
    }

    /**
     *
     * @param name
     * @param i
     * @throws SQLServerException
     * @throws SQLException
     * @Edit the name of the selected playlist
     */
    public void editPlaylist(String name, int i) throws SQLServerException, SQLException {
        bllManager.editPlaylist(name, i);
    }

    /**
     *
     * @param selectedPlaylistId
     * @param selectedSongId
     * @throws java.sql.SQLException
     * @ Add the selected song to the selected playlist
     */
    public void addSongToPlaylist(int selectedPlaylistId, int selectedSongId) throws SQLException {
        bllManager.addSongToPlaylist(selectedPlaylistId, selectedSongId);
    }
    
    public void createNewPlaylist(String name) throws SQLException{
    bllManager.createNewPlaylist(name);
    }
}
