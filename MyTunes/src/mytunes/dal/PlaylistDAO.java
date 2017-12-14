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

    /**
     *
     */
    public PlaylistDAO() {
    }
    
    /**
     *
     * @return
     */
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
    
    /**
     *
     * @param name
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
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
    
    /**
     *
     * @param playlist
     * @throws SQLServerException
     * @throws SQLException
     */
    public void remove(Playlist playlist) throws SQLServerException, SQLException {
        try (Connection con = cm.getConnection();) {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM playlist WHERE playlist_id="+playlist.getId());
        }       
    }

    /**
     *
     * @param name
     * @param i
     * @throws SQLServerException
     * @throws SQLException
     */
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

    /**
     *
     * @param selectedPlaylistId
     * @param selectedSongId
     */
    public void addSongToPlaylist(int selectedPlaylistId, int selectedSongId) throws SQLServerException, SQLException {
        try (Connection con = cm.getConnection())
        {
            int firstEmptyPlaylistSongIndex = 0;
            String selectQuery = "SELECT * FROM playlist WHERE playlist_id = ?;";
            PreparedStatement prepStmt = con.prepareStatement(selectQuery);
            prepStmt.setInt(1, selectedPlaylistId);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            System.out.println(rs.getString(2));
            for (int i = 3; i <= 12; i++) {
                if (rs.getInt(i) == 0) {
                    firstEmptyPlaylistSongIndex = i-2;
                    break;
                }
            }
            if(firstEmptyPlaylistSongIndex != 0){
            String column = "Song" + firstEmptyPlaylistSongIndex;
            String updateQuery = "UPDATE playlist SET "+column+" = ? WHERE playlist_id = ?;"; 
            PreparedStatement prepStmtUpdate = con.prepareStatement(updateQuery);
           
            prepStmtUpdate.setInt(1, selectedSongId);
            prepStmtUpdate.setInt(2, selectedPlaylistId);
            prepStmtUpdate.executeUpdate();
                System.out.println("column " + column);
                System.out.println("songid: "+ selectedSongId + " playlistid: "+ selectedPlaylistId);
            }
        }
    }
     }   
