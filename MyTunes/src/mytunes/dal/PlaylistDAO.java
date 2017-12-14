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

/**
 *
 * @author pmj
 */
public class PlaylistDAO {
    ConnectionManager cm = new ConnectionManager();

    public PlaylistDAO() {
    }
    
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
    
    public Playlist createPlaylist(String name) throws SQLServerException, SQLException 
    {   
        try (Connection con = cm.getConnection())
        {
            String sql = "INSERT INTO playlist(playlist_name) VALUES (?);";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, name);

            if (statement.executeUpdate() == 1)
            {
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                Playlist newPlaylist = new Playlist(id, name);
                return newPlaylist; 
                
            }
            throw new RuntimeException("Can't create playlist");
        }
    }
    
    public void remove(Playlist playlist) throws SQLServerException, SQLException {
        try (Connection con = cm.getConnection();) {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM playlist WHERE playlist_id="+playlist.getId());
        }       
    }
    public void editPlaylist(String name, int i ) throws SQLServerException, SQLException{
        String query = "UPDATE playlist SET playlist_name = ? WHERE playlist_id = ?;";
     try (Connection con = cm.getConnection())
     {
         PreparedStatement preparedStmt = con.prepareStatement(query);
         preparedStmt.setString(1, name);
         preparedStmt.setInt(2, i);
         preparedStmt.executeUpdate();
}
    }

    public void addSongToPlaylist(int selectedPlaylistId, int selectedSongId) {
        // To Doo
    }
     }   
