/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

/**
 * Class for constructing and managing Song objects
 * @author pmj
 */
public class Song {
    private int id;
    private String title;
    private String genre;
    private String duration;
    private String songPath;
    private String artist;

    /**
     *
     * @param id
     * @param title
     * @param genre
     * @param duration
     * @param songPath
     * @param artist
     * @Song constructor
     */
    public Song(int id, String title, String genre, String duration, String songPath, String artist) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.songPath = songPath;
        this.artist = artist;
    }

    /**
     *@Song constructor
     */
    public Song() {
    }
    /**
     *
     * @return id
     */
    public int getId() {
     return id;
    }

    /**
     *
     * @param id
     * set id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * set title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return genre
     * 
     */
    public String getGenre() {
        return genre;
    }

    /**
     *
     * @param genre
     * @set genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     *
     * @return duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     * set duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     *
     * @return songPath
     * 
     */
    public String getSongPath() {
        return songPath;
    }

    /**
     *
     * @param songPath
     * @set songPath
     */
    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    /**
     *
     * @return artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     *
     * @param artist
     * @set artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }
    
}
