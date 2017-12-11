/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.sql.SQLException;
import java.util.List;
import mytunes.be.Song;
import mytunes.dal.SongDAO;

/**
 *
 * @author pmj
 */
public class BLLManager {

    private final SongDAO songDAO = new SongDAO();
    
    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }

    public void remove(Song song) throws SQLException {
        songDAO.remove(song);
    }
    
    public void createSong(int id, String title, String genre, String duration, String songPath, String artist) throws SQLException{
        songDAO.createSong(id, title, genre, duration, songPath, artist);
    }
}