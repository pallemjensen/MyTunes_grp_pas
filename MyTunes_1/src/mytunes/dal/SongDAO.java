/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Song;

/**
 *
 * @author pmj
 */
public class SongDAO {
    ConnectionManager cm = new ConnectionManager();

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList();
        
        try (Connection con = cm.getConnection();) {
            Statement stmt = con.createStatement();
            ResultSet rs
                    = stmt.executeQuery("SELECT * FROM Songs");
            while (rs.next()) {
                Song currentSong = new Song();
                currentSong.setId(rs.getInt("song_id"));
                currentSong.setTitle(rs.getString("song_title"));
                currentSong.setArtist(rs.getString("artist_name"));
                currentSong.setDuration(rs.getFloat("song_duration"));
                currentSong.setGenre(rs.getString("song_genre"));
                currentSong.setFile(rs.getString("song_path"));
                songs.add(currentSong);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cars;
    }

    public void remove(Song song) {
        try (Connection con = cm.getConnection();) {
            Statement stmt = con.createStatement();
            stmt.execute(
                "DELETE FROM Songs WHERE id="+song.getId());
        }
        catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
