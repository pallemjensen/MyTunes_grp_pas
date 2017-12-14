/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import com.microsoft.sqlserver.jdbc.SQLServerException;
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

    /**
     * BLLManager constructor
     */
    public BLLManager() {
    }
    
    /**
     *
     * @return list with all songs
     */
    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }
    
    /**
     *
     * @return list with all playlists
     */
    public List<Playlist> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    /**
     *
     * @param song
     * @throws SQLException
     * receives a song object and calls the remove song method in our songDAO DAl class.
     */
    public void remove(Song song) throws SQLException {
        songDAO.remove(song);
    }
    
    /**
     *
     * @param title
     * @param genre
     * @param duration
     * @param songPath
     * @param artist
     * @return
     * @throws SQLException
     * @Receives song parameters, calls the createSong method in songDAO, returns a newSong object.
     */
    public Song createSong(String title, String genre, String duration, String songPath, String artist) throws SQLException{
        Song newSong = songDAO.createSong(title, genre, duration, songPath, artist);
        return newSong;
    }

    /**
     *
     * @param i
     * @param title
     * @param artist
     * @param genre
     * @throws SQLServerException
     * @throws SQLException
     * @Receives song parameters, calls the editSong method in songDAO. Edits the song in our DB.
     */
    public void editSong(int i, String title, String artist, String genre) throws SQLServerException, SQLException {
        songDAO.editSong(i, title, artist, genre);
    }

    /**
     *
     * @param playlist
     * @throws SQLServerException
     * @throws SQLException
     * @Receives a playlist object. Calls the remove method in playlistDAO and deletes a playlist.
     */
    public void remove(Playlist playlist) throws SQLServerException, SQLException {
        playlistDAO.remove(playlist);
    }

    /**
     *
     * @param name
     * @param i
     * @throws SQLServerException
     * @throws SQLException
     * @Receives playlist parameters. Calls editPlaylist method in playlistDAO and edits the name in DB.
     */
    public void editPlaylist(String name, int i) throws SQLServerException, SQLException {
        playlistDAO.editPlaylist(name, i);
    }

    /**
     *
     * @param selectedPlaylistId
     * @param selectedSongId
     * @Recieves playlist id and song id and adds  song to a playlist in our DB via addSongToPlaylist in playlistDAO.
     */
    public void addSongToPlaylist(int selectedPlaylistId, int selectedSongId) throws SQLException {
        playlistDAO.addSongToPlaylist(selectedPlaylistId, selectedSongId);
    }
    
    public void createNewPlaylist(String name) throws SQLException{
    playlistDAO.createPlaylist(name);
    }

}