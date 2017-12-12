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
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public void remove(Song song) throws SQLServerException, SQLException {
        try (Connection con = cm.getConnection();) {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM MytunesSongs2 WHERE song_id="+song.getId());
        }       
    }
    
    public Song createSong(String title, String genre, String duration, String songPath, String artist) throws SQLServerException, SQLException 
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

            if (statement.executeUpdate() == 1)
            {
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                Song newSong = new Song(id, title, artist, duration, genre, songPath);
                return newSong;
            }
            throw new RuntimeException("Can't create song");
        }
    }
    public void editSong(int i, String title, String artist, String genre) throws SQLServerException, SQLException{
        String query = "UPDATE MyTunesSongs2 SET song_title = ?, artist_name = ?, song_genre = ? WHERE song_id = ?;";
     try (Connection con = cm.getConnection())
     {
         PreparedStatement preparedStmt = con.prepareStatement(query);
         preparedStmt.setString(1, title);
         preparedStmt.setString(2, genre);
         preparedStmt.setString(3, artist);
         preparedStmt.setInt(4, i);
         preparedStmt.executeUpdate();
     }   
    }
    
 
    public void filterSongs(String oldVal, String newVal) {
       
    // If the number of characters in the text box is less than last time
    // it must be because the user pressed delete
    if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
        // Restore the lists original set of entries 
        // and start from the beginning
        list.setItems( entries );
    }
     
    // Break out all of the parts of the search text 
    // by splitting on white space
    String[] parts = newVal.toUpperCase().split(" ");
 
    // Filter out the entries that don't contain the entered text
    ObservableList<String> subentries = FXCollections.observableArrayList();
    for ( Object entry: list.getItems() ) {
        boolean match = true;
        String entryText = (String)entry;
        for ( String part: parts ) {
            // The entry needs to contain all portions of the
            // search string *but* in any order
            if ( ! entryText.toUpperCase().contains(part) ) {
                match = false;
                break;
            }
        }
 
        if ( match ) {
            subentries.add(entryText);
        }
    }
    list.setItems(subentries);
}
    
            
            
}

