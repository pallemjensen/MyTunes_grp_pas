/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

/**
 *
 * @author pmj
 */
public class Song {
    
    private int id;
    private String title;
    private String genre;
    private String duration;
    private String songPath;
    private String artist;

    public Song(int id, String title, String genre, String duration, String songPath, String artist) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.songPath = songPath;
        this.artist = artist;
    }

    public Song() {
    }
    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getGenre() {
        return genre;
    }

    /**
     *
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     *
     * @return
     */
    public String getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     *
     * @return
     */
    public String getSongPath() {
        return songPath;
    }

    /**
     *
     * @param songPath
     */
    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    /**
     *
     * @return
     */
    public String getArtist() {
        return artist;
    }

    /**
     *
     * @param artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }
    
}
