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
import mytunes.be.Song;

/**
 * @author pmj
 * @In this class we manage all traffic from and to our DB concerning our song objects. 
 */
public class SongDAO {
   ConnectionManager cm = new ConnectionManager();

    /**
     *
     * @return a list with our song objects after a DB query.
     */
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList();
        
        try (Connection con = cm.getConnection();) {
            Statement stmt = con.createStatement();
            ResultSet rs
                    = stmt.executeQuery("SELECT * FROM MytunesSongs2");
            while (rs.next()) {
                Song currentSong = new Song();
                currentSong.setId(rs.getInt("song_id"));
                currentSong.setTitle(rs.getString("song_title"));
                currentSong.setArtist(rs.getString("artist_name"));
                currentSong.setDuration(rs.getString("song_duration"));
                currentSong.setGenre(rs.getString("song_genre"));
                currentSong.setSongPath(rs.getString("song_path"));
                songs.add(currentSong);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return songs;
    }
    
    public Song getSongFromId(int id){
        Song currentSong = new Song();
        try (Connection con = cm.getConnection())
        {
            String selectQuery = "SELECT * FROM MytunesSongs2 WHERE song_id = ?;";
            PreparedStatement prepStmt = con.prepareStatement(selectQuery);
            prepStmt.setInt(1, id);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            currentSong.setId(rs.getInt("song_id"));
            currentSong.setTitle(rs.getString("song_title"));
            currentSong.setArtist(rs.getString("artist_name"));
            currentSong.setDuration(rs.getString("song_duration"));
            currentSong.setGenre(rs.getString("song_genre"));
            currentSong.setSongPath(rs.getString("song_path"));            
        } catch (SQLServerException ex) {
           Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        return currentSong;
    }

    /**
     *
     * @param song
     * @Receives a song object and then deletes it from our DB MytunesSongs2 table where the song_id matches.
     */
    public void remove(Song song){
        try (Connection con = cm.getConnection();) {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM MytunesSongs2 WHERE song_id="+song.getId());
        } catch (SQLServerException ex) {       
           Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
       }       
    }
    
    /**
     *
     * @param title
     * @param genre
     * @param duration
     * @param songPath
     * @param artist
     * @return
     * Create a new song object in our DB with the parameters given and returns a new song object
     */
    public Song createSong(String title, String genre, String duration, String songPath, String artist)
    {
        try (Connection con = cm.getConnection())
        {
            String sql = "INSERT INTO MyTunesSongs2 VALUES (?, ?, ?, ?, ?);";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, duration);
            statement.setString(4, genre);
            statement.setString(5, songPath);

            if(statement.executeUpdate() == 1);
            {
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                Song newSong = new Song(id, title, artist, duration, genre, songPath);
                return newSong;
            }
            
        } catch (SQLServerException ex) {
           Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }

    /**
     *
     * @param i
     * @param title
     * @param artist
     * @param genre
     * @Receives parameters for a song and edits the song parameters in our DB.
     */
    public void editSong(int i, String title, String artist, String genre){
        String query = "UPDATE MyTunesSongs2 SET song_title = ?, artist_name = ?, song_genre = ? WHERE song_id = ?;";
     try (Connection con = cm.getConnection())
     {
         PreparedStatement preparedStmt = con.prepareStatement(query);
         preparedStmt.setString(1, title);
         preparedStmt.setString(2, artist);
         preparedStmt.setString(3, genre);
         preparedStmt.setInt(4, i);
         preparedStmt.executeUpdate();
     } catch (SQLServerException ex) {   
           Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
       }   
    }         
}

