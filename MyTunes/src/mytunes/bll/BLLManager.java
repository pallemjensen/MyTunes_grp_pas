/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

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
    List<Playlist> pl = playlistDAO.getAllPlaylists();
        for (Playlist playlist : pl) {
            double durationInSeconds = 0;
            List<Integer> idList = playlist.getPlaylistSongIdsList();
            for (Integer integer : idList) {
                if(integer != 0){
                    Song nextSong = songDAO.getSongFromId(integer);
                    if(!nextSong.getDuration().isEmpty()){
                    durationInSeconds = durationInSeconds + Double.parseDouble(nextSong.getDuration());
                }
                    playlist.addSongToPlaylist(nextSong);
                }
            }
        //    add total playtime to playlist;
            playlist.setTotalDuration(durationInSeconds);
        }
    return pl;
    }

    /**
     *
     * @param song
     */
    public void remove(Song song){
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
     * @Receives song parameters, calls the createSong method in songDAO, returns a newSong object.
     */
    public Song createSong(String title, String genre, String duration, String songPath, String artist){
        Song newSong = songDAO.createSong(title, genre, duration, songPath, artist);
        return newSong;
    }

    /**
     *
     * @param i
     * @param title
     * @param artist
     * @param genre
     * @Receives song parameters, calls the editSong method in songDAO. Edits the song in our DB.
     */
    public void editSong(int i, String title, String artist, String genre){
        songDAO.editSong(i, title, artist, genre);
    }

    /**
     *
     * @param playlist
     * @Receives a playlist object. Calls the remove method in playlistDAO and deletes a playlist.
     */
    public void remove(Playlist playlist){
        playlistDAO.remove(playlist);
    }

    /**
     *
     * @param name
     * @param i
     * @Receives playlist parameters. Calls editPlaylist method in playlistDAO and edits the name in DB.
     */
    public void editPlaylist(String name, int i){
        playlistDAO.editPlaylist(name, i);
    }

    /**
     *
     * @param selectedPlaylistId
     * @param selectedSongId
     * @Recieves playlist id and song id and adds  song to a playlist in our DB via addSongToPlaylist in playlistDAO.
     */
    public void addSongToPlaylist(int selectedPlaylistId, int selectedSongId){
        playlistDAO.addSongToPlaylist(selectedPlaylistId, selectedSongId);
    }
    
    public void createNewPlaylist(String name){
    playlistDAO.createPlaylist(name);
    }

    public void removeSongFromPlaylist(Playlist playlistSelected, Song songSelected) {
        playlistDAO.removeSongFromPlaylist(playlistSelected, songSelected);
    }

}