/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

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
     * @return 
     */
    public int showSongsOnPlaylist(Playlist playlistSelected) {
        List<Song> songList = playlistSelected.getplaylistSongs();
        songsOnPlaylists.clear();
        songsOnPlaylists.addAll(songList);
        int a = songList.size();
        return a;
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
     */
    public void remove(Song selectedSongs){
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
     */
    public void addNewSong(String title, String genre, String duration, String newSongPath, String artist){
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
     */
    public void editSong(int i, String title, String artist, String genre){
        bllManager.editSong(i, title, artist, genre);
    }

    /**
     *
     * @param playlist
     */
    public void remove(Playlist playlist){
        playlists.remove(playlist);
        bllManager.remove(playlist);
    }

    /**
     *
     * @param name
     * @param i
     * @Edit the name of the selected playlist
     */
    public void editPlaylist(String name, int i){
        bllManager.editPlaylist(name, i);
    }

    /**
     *
     * @param selectedPlaylistId
     * @param selectedSongId
     * @ Add the selected song to the selected playlist
     */
    public void addSongToPlaylist(int selectedPlaylistId, int selectedSongId){
        bllManager.addSongToPlaylist(selectedPlaylistId, selectedSongId);
    }
    
    public void createNewPlaylist(String name){
    bllManager.createNewPlaylist(name);
    }

    void removeSongFromPlaylist(Playlist playlistSelected, Song songSelected) {
        getSongsOnPlaylist().remove(songSelected);
        bllManager.removeSongFromPlaylist(playlistSelected, songSelected);
    }
}
