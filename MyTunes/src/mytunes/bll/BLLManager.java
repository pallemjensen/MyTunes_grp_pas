/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.sql.SQLException;
import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.dal.PlaylistDAO;
import mytunes.dal.SongDAO;

/**
 *
 * @author pmj
 */
public class BLLManager {

    private final SongDAO songDAO = new SongDAO();
    private final PlaylistDAO playlistDAO = new PlaylistDAO();
    
    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }
    
    public List<Playlist> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    public void remove(Song song) throws SQLException {
        songDAO.remove(song);
    }
    
    public Song createSong(String title, String genre, String duration, String songPath, String artist) throws SQLException{
        Song newSong = songDAO.createSong(title, genre, duration, songPath, artist);
        return newSong;
    }

}