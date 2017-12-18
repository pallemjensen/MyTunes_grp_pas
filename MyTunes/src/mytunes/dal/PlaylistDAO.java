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
 * @In this class we manage all traffic from and to our DB concerning our playlists.
 * @author pmj
 */
public class PlaylistDAO {
    ConnectionManager cm = new ConnectionManager();

    /**
     *@playlist constructor
     */
    public PlaylistDAO() {
    }
    
    /**
     * @return our playlists after a DB query
     */
    public List<Playlist> getAllPlaylists() {
        List<Playlist> playlists = new ArrayList();
        try (Connection con = cm.getConnection();) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM playlist");
            while (rs.next()) {
                Playlist nextPlaylist = new Playlist();
                nextPlaylist.setId(rs.getInt("playlist_id"));
                nextPlaylist.setName(rs.getString("playlist_name"));
                for (int i = 3; i <= 12; i++) {
                    int playlistSongId = rs.getInt(i);
                        if(playlistSongId != 0){
                            nextPlaylist.addIdToPlaylistSongIdsList(playlistSongId);
                        }
                }
                playlists.add(nextPlaylist);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlists;
    }
    
    /**
     * @param name
     * @return 
     * @throws com.microsoft.sqlserver.jdbc.SQLServerException 
     * @throws java.sql.SQLException 
     * @Create a new playlist in our DB with parameter name and returns a new playlist object
     */
    public Playlist createPlaylist(String name)
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
        } catch (SQLServerException ex) { 
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * @param playlist
     * @Receives a playlist object and deletes it in our DB
     */
    public void remove(Playlist playlist){
        try (Connection con = cm.getConnection();) {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM playlist WHERE playlist_id="+playlist.getId());
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    /**
     * @param name
     * @param i
     * @Receives parameters for a playlist and edits the playlist name in our DB.
     */
    public void editPlaylist(String name, int i )
            
    {
        String query = "UPDATE playlist SET playlist_name = ? WHERE playlist_id = ?;";
        try (Connection con = cm.getConnection())
        {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setInt(2, i);
            preparedStmt.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param selectedPlaylistId
     * @param selectedSongId
     * @@Recieves playlist id and song id and adds the song to the playlist in our DB.
     */
    public void addSongToPlaylist(int selectedPlaylistId, int selectedSongId) {
        try (Connection con = cm.getConnection())
        {
            int firstEmptyPlaylistSongIndex = 0;
            String selectQuery = "SELECT * FROM playlist WHERE playlist_id = ?;";
            PreparedStatement prepStmt = con.prepareStatement(selectQuery);
            prepStmt.setInt(1, selectedPlaylistId);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
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
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeSongFromPlaylist(Playlist playlistSelected, Song songSelected) {
        try (Connection con = cm.getConnection())
        {
            int songIndex = 0;
            String selectQuery = "SELECT * FROM playlist WHERE playlist_id = ?;";
            PreparedStatement prepStmt = con.prepareStatement(selectQuery);
            prepStmt.setInt(1, playlistSelected.getId());
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            for (int i = 3; i <= 12; i++) {
                if (rs.getInt(i) == songSelected.getId()) {
                    songIndex = i-2;
                    break;
                }
            }
            if(songIndex != 0){
            String column = "Song" + songIndex;
            String updateQuery = "UPDATE playlist SET "+column+" = 0 WHERE playlist_id = ?;"; 
            PreparedStatement prepStmtUpdate = con.prepareStatement(updateQuery);
            prepStmtUpdate.setInt(1, playlistSelected.getId());
            prepStmtUpdate.executeUpdate();
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
} 