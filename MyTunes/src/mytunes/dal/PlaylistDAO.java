/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Playlist;
import mytunes.be.Song;

/**
 *
 * @author pmj
 */
public class PlaylistDAO {
    ConnectionManager cm = new ConnectionManager();
    
    public List<Playlist> getAllPlaylists() {
        List<Playlist> playlists = new ArrayList();
        
        try (Connection con = cm.getConnection();) {
            Statement stmt = con.createStatement();
            ResultSet rs
                    = stmt.executeQuery("SELECT * FROM playlist");
            while (rs.next()) {
                Playlist currentPlaylist = new Playlist();
                currentPlaylist.setId(rs.getInt("playlist_id"));
                currentPlaylist.setName(rs.getString("playlist_name"));
               
                playlists.add(currentPlaylist);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlists;
    }
    
    public Playlist createPlaylist(String name, int song1, int song2, int song3, int song4, int song5, int song6, int song7, int song8, int song9, int song10) throws SQLServerException, SQLException 
    {   
        
        try (Connection con = cm.getConnection())
        {
            String sql = "INSERT INTO playlist VALUES (?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, name);
            statement.setInt(2, song1);
            statement.setInt(3, song2);
            statement.setInt(4, song3);
            statement.setInt(5, song4);
            statement.setInt(6, song5);
            statement.setInt(7, song6);
            statement.setInt(8, song7);
            statement.setInt(9, song8);
            statement.setInt(10, song9);
            statement.setInt(11, song10);

            if (statement.executeUpdate() == 1)
            {
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                Playlist newPlaylist = new Playlist(id, name, song1, song2, song3, song4, song5, song6, song7, song8, song9, song10);
                return newPlaylist; 
                
            }
            throw new RuntimeException("Can't create playlist");
        }
    }
}
