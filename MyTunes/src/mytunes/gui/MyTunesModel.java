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
import mytunes.be.Song;
import mytunes.bll.BLLManager;

/**
 *
 * @author Anders
 */
public class MyTunesModel {

    private ObservableList<Song> songs
            = FXCollections.observableArrayList();
    private BLLManager bllManager = new BLLManager();
    
    public ObservableList<Song> getSongs() {
        return songs;
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

}